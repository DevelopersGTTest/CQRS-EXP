package com.sqrt.kafka_sample_artifact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaSampleArtifactApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static Logger log = LoggerFactory.getLogger(KafkaSampleArtifactApplication.class);

	@KafkaListener(
			topics = "hck-topic",
			containerFactory = "listenerContainerFactory",
			groupId = "hck-group",
			properties =  {
				"max.poll.interval.ms:4000", "max.poll.records:10"
			}
	)
	public void listen(List<String> messages) {
		log.info("start reading batch...");
		for (String message : messages) {
			log.info("message received = {}", message);
		}

		log.info("complete batch...");
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaSampleArtifactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for(int i =0; i < 100; i++) {
			kafkaTemplate.send("hck-topic", String.format("sample message %d ", i));
		}

		/**
		 *
		 * kafkaTemplate
				.send("hck-topic", "Sample message")
				.get(100, TimeUnit.MICROSECONDS); // Async producer */

		/**
		 *
		 * [UPDATE] SUPPORT FOR JAVA 17 for callback behavior
		 *
		 * CompletableFuture<SendResult<String, String>> future =  kafkaTemplate
				.send("hck-topic", "Sample message");

		future.whenComplete((result, ex) -> {
			log.info("message = {} ", result.getRecordMetadata().offset());

			if(ex != null) {
				log.error("error = {}", ex);
			}
		}); */
	}
}
