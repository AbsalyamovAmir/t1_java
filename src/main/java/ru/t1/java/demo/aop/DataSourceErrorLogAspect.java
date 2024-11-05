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

@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class DataSourceErrorLogAspect {

    private final DataSourceErrorLogRepository dataSourceErrorLogRepository;

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
