package com.project.auto.depit.wallet.flink.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.json.JSONObject;

import com.project.auto.depit.wallet.pg.util.PgUtil;

public class DepitMoneyFromAccount extends RichSinkFunction<JSONObject> {

	public void invoke(JSONObject value) {

		String accountNumber = value.getString("accountNumber");
		String pinNumber = value.getString("pinNumber");
		Double amount = Double.valueOf(value.getString("amount"));
		String query = String.format("Select amount form walletCredential where accNumber=%s and pin_number=%s",
				accountNumber, pinNumber);
		Connection con = PgUtil.getPgConnection();
		Double walletAmount = null;// db call
		try {
			Statement st=con.createStatement();

			ResultSet rs=st.executeQuery(query);
			
			
			while(rs.next())
			{
				walletAmount=rs.getDouble("Wallet");
				if (amount <= walletAmount) {
					float remainingBalance=(float) (walletAmount-amount);
					
					PreparedStatement ps=con.prepareStatement("UPDATE walletCredential SET walletAmount = ?");
					ps.setFloat(0, remainingBalance);

					ps.executeUpdate();

				} else {
					System.out.println("your wallet balace is low");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
