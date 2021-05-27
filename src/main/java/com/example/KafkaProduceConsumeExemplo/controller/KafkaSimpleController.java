package com.example.KafkaProduceConsumeExemplo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.KafkaProduceConsumeExemplo.model.SimpleModel;

@RestController
@RequestMapping("/api/kafka")
public class KafkaSimpleController {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaSimpleController.class);

	private KafkaTemplate<String, SimpleModel> kafkaTemplate;
	
	@Autowired
	public KafkaSimpleController(KafkaTemplate<String, SimpleModel> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping
	public void post(@RequestBody SimpleModel simpleModel){
		kafkaTemplate.send("myTopic", simpleModel);
	}
	
	
	@KafkaListener(topics = "myTopic")
	public void getSimpleModel(SimpleModel simpleModel) {
		log.info(String.valueOf(simpleModel));
	}
	
}
