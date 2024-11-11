package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import ru.t1.java.demo.model.dto.AccountDto;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.service.AccountService;
import ru.t1.java.demo.util.AccountMapper;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaAccountConsumer {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @KafkaListener(id = "${t1.kafka.consumer.group-id}",
            topics = "${t1.kafka.topic.demo_accounts}",
            containerFactory = "kafkaAccountListenerContainerFactory")
    public void accountConsumerListener(AccountDto accountDto,
                                        Acknowledgment ack) {
        log.debug("Account consumer: Обработка новых сообщений");

        try {
            Account savedAccount = accountService.saveAccount(accountDto);
            log.info("Account has been saved to DB: {}", savedAccount);
        } catch (Exception e) {
            log.error("Error occurred while saving account : {}", accountDto, e);
        } finally {
            ack.acknowledge();
        }
    }
}
