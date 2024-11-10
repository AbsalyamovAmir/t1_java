package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.DataSourceErrorLogDto;
import ru.t1.java.demo.exception.KafkaSendException;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaLogDataSourceErrorProducer<T extends DataSourceErrorLogDto> {

    @Qualifier("logDataSourceError")
    private final KafkaTemplate logDataSourceErrorTemplate;

    public void send(DataSourceErrorLogDto dataSourceErrorLogDto) {
        try {
            logDataSourceErrorTemplate.sendDefault(UUID.randomUUID().toString(), dataSourceErrorLogDto).get();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new KafkaSendException("Kafka sending error", ex);
        } finally {
            logDataSourceErrorTemplate.flush();
        }
    }

    public void sendTo(String topic, Object o) {
        try {
            logDataSourceErrorTemplate.send(topic, o).get();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            logDataSourceErrorTemplate.flush();
        }
    }
}
