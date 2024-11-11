package ru.t1.java.demo.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.dto.DataSourceErrorLogDto;
import ru.t1.java.demo.exception.KafkaSendException;
import ru.t1.java.demo.kafka.KafkaLogDataSourceErrorProducer;
import ru.t1.java.demo.model.DataSourceErrorLog;
import ru.t1.java.demo.repository.DataSourceErrorLogRepository;

import java.util.Arrays;

/**
 * Аспект логирующий сообщения об исключениях, если в результате CRUD-операций над сущностями возникла ошибка
 */
@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class DataSourceErrorLogAspect {

    private final DataSourceErrorLogRepository dataSourceErrorLogRepository;
    private final KafkaLogDataSourceErrorProducer kafkaLogDataSourceErrorProducer;

    /**
     * Метод для обработки перехвата метода
     * Если происходит ошибка, создаем запись для БД
     * @param proceedingJoinPoint Вызов метода
     * @return Результат выполнения метода
     */
    @Around("@annotation(ru.t1.java.demo.aop.DataSourceErrorLogTrack)")
    public Object logDataSourceError(ProceedingJoinPoint proceedingJoinPoint) {
        String methodSignature = proceedingJoinPoint.getSignature().toShortString();
        log.warn("Вызов метода: {}", methodSignature);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            log.warn("Что-то пошло не так: {}", throwable.getMessage());
            DataSourceErrorLogDto errorLogDto = DataSourceErrorLogDto.builder()
                    .exceptionStackTrace(Arrays.toString(throwable.getStackTrace()))
                    .errorMessage(throwable.getMessage())
                    .methodSignature(methodSignature)
                    .build();
            try {
                kafkaLogDataSourceErrorProducer.send(errorLogDto);
            } catch (KafkaSendException e) {
                log.error("Kafka sending error: {}", e.getMessage());
                DataSourceErrorLog dataSourceErrorLog = DataSourceErrorLog.builder()
                        .exceptionStackTrace(Arrays.toString(throwable.getStackTrace()))
                        .errorMessage(throwable.getMessage())
                        .methodSignature(methodSignature)
                        .build();
                dataSourceErrorLogRepository.save(dataSourceErrorLog);
                log.warn("Error has been saved in DB, {}", dataSourceErrorLog);
            }
        }
        return result;
    }
}
