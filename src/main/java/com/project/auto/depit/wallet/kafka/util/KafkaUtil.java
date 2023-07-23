package com.project.auto.depit.wallet.kafka.util;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaUtil {
public static void send_message(String msg) {
		String topic="send_data";
		String bootstrapserver = "127.0.0.1:9092";
		Properties pro = new Properties();
		pro.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapserver);
		pro.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		pro.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		
		KafkaProducer<String, String> first = new KafkaProducer<String, String>(pro);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
		first.send(record);
		first.flush();
		first.close();
	}
}
