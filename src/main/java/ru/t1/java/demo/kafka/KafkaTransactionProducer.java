package ru.t1.java.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.exception.KafkaSendException;
import ru.t1.java.demo.model.dto.TransactionDto;

@Component
@Slf4j
public class KafkaTransactionProducer {

    private static final String TOPIC_NAME = "t1_demo_transaction";
    @Qualifier("transaction")
    private final KafkaTemplate<String, Object> transactionKafkaTemplate;

    public KafkaTransactionProducer(@Qualifier("transaction") KafkaTemplate<String, Object> transactionKafkaTemplate) {
        this.transactionKafkaTemplate = transactionKafkaTemplate;
    }

    public TransactionDto send(TransactionDto transactionDto) {
        try {
            transactionKafkaTemplate.send(TOPIC_NAME, transactionDto);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new KafkaSendException("KafkaTransactionProducer sending error", ex);
        } finally {
            transactionKafkaTemplate.flush();
        }
        return transactionDto;
    }
}
