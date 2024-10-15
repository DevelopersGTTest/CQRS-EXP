package com.sqrt.kafka_sample_artifact;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaSampleArtifactApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	private static Logger log = LoggerFactory.getLogger(KafkaSampleArtifactApplication.class);

	@KafkaListener(
			id = "hckId",
			autoStartup = "false",
			topics = "hck-topic",
			containerFactory = "listenerContainerFactory",
			groupId = "hck-group",
			properties =  {
				"max.poll.interval.ms:4000", "max.poll.records:10"
			}
	)
	public void listen(List<ConsumerRecord<String, String>> messages) {
		log.info("start reading batch...");
		for (ConsumerRecord message : messages) {
			log.info("message received, partition = {}, offset = {}, key = {}, value = {}",
					message.partition(),
					message.offset(),
					message.key(),
					message.value()
			);
		}

		log.info("complete batch...");
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaSampleArtifactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for(int i =0; i < 100; i++) {
			kafkaTemplate
					.send("hck-topic", String.valueOf("key-" + i), String.format("sample message %d ", i));
		}
		log.info("waiting to start...");
		Thread.sleep(5000);
		log.info("starting...");
		kafkaListenerEndpointRegistry.getListenerContainer("hckId").start();
		log.info("stop...");
		kafkaListenerEndpointRegistry.getListenerContainer("hckId").stop();

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
