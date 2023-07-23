package com.project.auto.depit.wallet.flink.processor;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;
import org.json.JSONObject;

public class FlinkSerilzerTransformer extends RichFlatMapFunction<String, JSONObject> {

	@Override
	public void flatMap(String value, Collector<JSONObject> out) throws Exception {
		JSONObject data = new JSONObject(value);
		out.collect(data);
	}

}
