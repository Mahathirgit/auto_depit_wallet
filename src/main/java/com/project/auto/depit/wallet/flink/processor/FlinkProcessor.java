package com.project.auto.depit.wallet.flink.processor;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.json.JSONObject;

public class FlinkProcessor {
public static void runProcess() {
	StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

	Properties properties = new Properties();
	properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "tests");
	properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, String.valueOf(true));


	FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<String>("sendData",
			new SimpleStringSchema(), properties);

	DataStreamSource<String> stage1 = env.addSource(flinkKafkaConsumer);
	DataStream<JSONObject> statge2=stage1.flatMap(new FlinkSerilzerTransformer());
	statge2.addSink(new DepitMoneyFromAccount());


}
}
