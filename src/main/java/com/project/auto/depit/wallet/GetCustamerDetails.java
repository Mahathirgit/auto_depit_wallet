package com.project.auto.depit.wallet;

import java.util.Scanner;

import org.json.JSONObject;

public class GetCustamerDetails {
	public static JSONObject getDetails() {
		JSONObject coustamerData = new JSONObject();
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter Account Holder Name");
		coustamerData.put("name", s.next());
		System.out.println("Enter Account Nunmber");
		coustamerData.put("accountNumber", s.next());
		System.out.println("Enter   Amount");
		coustamerData.put("amount", s.next());
		System.out.println("Enter   Pin Nmber");
		coustamerData.put("pinNumber", s.next());
		return coustamerData;
	}
}
