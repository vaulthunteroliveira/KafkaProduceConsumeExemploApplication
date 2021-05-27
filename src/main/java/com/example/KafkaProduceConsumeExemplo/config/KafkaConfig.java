package com.example.KafkaProduceConsumeExemplo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.example.KafkaProduceConsumeExemplo.model.SimpleModel;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaConfig {
	private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

	@Bean
	public ProducerFactory<String, SimpleModel> producerFactory(){
		
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		log.info("producerFactory method called");
		
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	@Bean
	public KafkaTemplate<String, SimpleModel> kafkaTemplate(){
		log.info("kafkaTemplate method called");
		return new KafkaTemplate<>(producerFactory());
	}
	
	
}
