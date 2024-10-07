package com.sqrt.kafka_sample_artifact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaSampleArtifactApplication implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(KafkaSampleArtifactApplication.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "hck-topic", groupId = "hck-group")
	public void listen(String message) {
		log.info("received message {} ", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaSampleArtifactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		kafkaTemplate.send("hck-topic", "Sample message");
	}
}
