package com.project.auto.depit.wallet;

import org.json.JSONObject;

import com.project.auto.depit.wallet.flink.processor.FlinkProcessor;
import com.project.auto.depit.wallet.kafka.util.KafkaUtil;

public class App {

	public static void main(String[] args) {
		JSONObject data=GetCustamerDetails.getDetails();
		KafkaUtil.send_message(data.toString());
		FlinkProcessor.runProcess();
	}

}
