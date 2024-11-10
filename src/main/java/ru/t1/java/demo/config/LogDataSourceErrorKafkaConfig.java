package ru.t1.java.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.t1.java.demo.dto.DataSourceErrorLogDto;
import ru.t1.java.demo.kafka.KafkaLogDataSourceErrorProducer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class LogDataSourceErrorKafkaConfig<T> {

    @Value("${t1.kafka.bootstrap.server}")
    private String servers;
    @Value("${t1.kafka.topic.demo_metrics}")
    private String demoMetricTopic;

    @Bean("logDataSourceError")
    public KafkaTemplate<String, T> kafkaLogDataSourceErrorTemplate(@Qualifier("producerLogDataSourceErrorFactory") ProducerFactory<String, T> producerPatFactory) {
        return new KafkaTemplate<>(producerPatFactory);
    }

    @Bean
    @ConditionalOnProperty(value = "t1.kafka.producer.enable",
            havingValue = "true",
            matchIfMissing = true)
    public KafkaLogDataSourceErrorProducer producerLogDataSourceError(@Qualifier("logDataSourceError") KafkaTemplate<String, DataSourceErrorLogDto> template) {
        template.setDefaultTopic(demoMetricTopic);
        return new KafkaLogDataSourceErrorProducer(template);
    }

    @Bean("producerLogDataSourceErrorFactory")
    public ProducerFactory<String, T> producerLogDataSourceErrorFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1000);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);
        return new DefaultKafkaProducerFactory<>(props);
    }
}
