package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.DataSourceErrorLogDto;
import ru.t1.java.demo.exception.KafkaSendException;
import ru.t1.java.demo.model.DataSourceErrorLog;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaLogDataSourceErrorProducer {

    private final KafkaTemplate logDataSourceErrorTemplate;

    public void send(DataSourceErrorLogDto dataSourceErrorLogDto) {
        Message<DataSourceErrorLogDto> message = MessageBuilder.withPayload(dataSourceErrorLogDto)
                .setHeader(KafkaHeaders.TOPIC, "t1_demo_metrics")
                .setHeader("errorType", "DATA_SOURCE")
                .build();
        try {
            logDataSourceErrorTemplate.send(message);
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
