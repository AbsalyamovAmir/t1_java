package ru.t1.java.demo.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
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
            DataSourceErrorLog dataSourceErrorLog = new DataSourceErrorLog();
            dataSourceErrorLog.setExceptionStackTrace(Arrays.toString(throwable.getStackTrace()));
            dataSourceErrorLog.setMethodSignature(methodSignature);
            dataSourceErrorLog.setErrorMessage(throwable.getMessage());
            dataSourceErrorLogRepository.save(dataSourceErrorLog);
        }
        return result;
    }
}
