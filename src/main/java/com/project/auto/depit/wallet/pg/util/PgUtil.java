package com.project.auto.depit.wallet.pg.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class PgUtil {
	public  static Connection getPgConnection() {
		Connection connection=null;
		try {
			Class.forName("org.postgresql.Driver");


			String url = "jdbc:mysql://localhost:3306/maha";
			String username = "root";
			String password = "password";

			 connection = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
