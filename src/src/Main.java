package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=InvoiceSystem;" + "encrypt=true;"
				+ "trustServerCertificate=true";

		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();

			String sql = "CREATE TABLE Shop (" + "shop_name VARCHAR(25) NOT NULL," + "tel_Number int NOT NULL,"
					+ "Fax_Number int NOT NULL," + "Email VArchar(255)NOT NULL," + "Website Varchar(255) NOT NULL,"
					+ ");";

			st.executeUpdate(sql);

			String sql1 = "CREATE TABLE Item (" + "Item_Id int PRIMARY KEY," + "Item_Name Varchar(255) NOT NULL ,"
					+ "UnitPrice Decimal NOT NULL, " + "Quantity Decimal NOT NULL ," + "QtyPrice Decimal NOT NULL "
					+ ");";

			st.executeUpdate(sql1);

			String sql2 = "CREATE TABLE Invoice (" + "InvNo int PRIMARY KEY," + "Customer_Name Varchar(255) NOT NULL, "
					+ "Phone int NOT NULL ," + "Date Date, " + "NumberOfItem int ," + "TotalPrice Decimal ,"
					+ "Paid_Amount Decimal ," + "Balance Decimal " + ");";

			st.executeUpdate(sql2);

			Statement statement = con.createStatement();
			statement.executeUpdate(sql);

			con.close();
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public static int getId(String message) {
		while (true) {
			String id = scan.next();
			try {
				Integer idinput = Integer.parseInt(id);
				return idinput;
			} catch (Exception ex) {
				System.out.println("Invalid Input");
			}
			System.out.print(message);
		}
	}
}
