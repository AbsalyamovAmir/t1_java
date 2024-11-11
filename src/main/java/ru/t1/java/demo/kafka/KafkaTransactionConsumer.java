package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.model.dto.TransactionDto;
import ru.t1.java.demo.service.TransactionService;
import ru.t1.java.demo.util.TransactionMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaTransactionConsumer {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @KafkaListener(id = "${t1.kafka.consumer.group-id}", topics = "${t1.kafka.topic.demo_transactions}", containerFactory = "kafkaTransactionListenerContainerFactory")
    public void transactionConsumerListener(TransactionDto transactionDto,
                                            Acknowledgment ack) {
        log.debug("Transaction consumer: Обработка новых сообщений");

        try {
            Transaction savedTransaction = transactionService.saveTransaction(transactionDto);
            log.info("Account has been saved to DB: {}", savedTransaction);
        } catch (Exception e) {
            log.error("Error occurred while saving account : {}", transactionDto, e);
        } finally {
            ack.acknowledge();
        }
    }
}
