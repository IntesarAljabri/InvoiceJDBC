package src;

import java.sql.Connection;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

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

			String sql = " IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Shop')" + "BEGIN"
					+ "	CREATE TABLE Shop(" + "shop_name VARCHAR(25) NOT NULL, " + " tel_Number int NOT NULL, "
					+ " Fax_Number int NOT NULL, " + " Email VArchar(255) NOT NULL, "
					+ " Website Varchar(255) NOT NULL" +  ") " +
		             "END";
			
	        st.executeUpdate(sql);

	        
			String sql1 = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Item ')" +"BEGIN"
					+ "	CREATE TABLE  Item (" + "Item_Id int PRIMARY KEY," + "Item_Name Varchar(255) NOT NULL ,"
					+ "UnitPrice decimal(5) NOT NULL, " + "Quantity decimal(5) NOT NULL ,"
					+ "QtyPrice decimal(5) NOT NULL " +  ") " +
		             "END";

			st.executeUpdate(sql1);
			

			String sql2 = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Invoice ')" +"BEGIN"
					+ "	CREATE TABLE  Invoice (" + "InvNo int PRIMARY KEY," + "Customer_Name Varchar(255) NOT NULL, "
					+ "Phone int NOT NULL ," + "Date Date, " + "NumberOfItem int ," + "TotalPrice decimal(5) ,"
					+ "Paid_Amount decimal(5) ," + "Balance decimal(5) "+  ") " +
		             "END";

			st.executeUpdate(sql2);

			st.close();
			con.close();
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

		boolean repeat = true;
		boolean running = true;

		// Main Menu
		while (running) {
			System.out.println("|----------------------------|");
			System.out.println("|1.Shop Setting              |");
			System.out.println("|2.Manage Shop               |");
			System.out.println("|3.Create New Invoice        |");
			System.out.println("|4.Search Invoice By Id      |");
			System.out.println("|5.Exit !!!                  |");
			System.out.println("|----------------------------|");

			String op = scan.next();

			switch (op) {

			case "1":
				// Sub menu of shop setting
				boolean setupWhile = true;
				while (setupWhile) {
					System.out.println("|-------------------------------------------------|");
					System.out.println("|             Shop Settings                       |");
					System.out.println("|-------------------------------------------------|");
					System.out.println("|[1].Load Data (Items and invoices)               |");
					System.out.println("|[2].SetInformation(name/Tel/Fax/Email/Website    |");
					System.out.println("|[3].Go back                                      |");
					System.out.println("|-------------------------------------------------|");

					int op1 = scan.nextInt();

					switch (op1) {
					case 1:
						// Load data
						Item.Load_Data();
						break;

					case 2:
						Invoice.Set_Information();
						break;

					case 3:
						// Go back
						setupWhile = false;
						break;
					}
				}
				break;

			case "2":
				// Manage Shop sub menu
				System.out.println("|---------------------------------|");
				System.out.println("|         Manage Shop :           |");
				System.out.println("|---------------------------------|");
				System.out.println("|[1]. addItem                     |");
				System.out.println("|[2]. deleteItem                  |");
				System.out.println("|[3]. changeItemPrice             |");
				System.out.println("|[4]. reportAllItems              |");
				System.out.println("|[5]. Go Back \n                  |");
				System.out.println("|---------------------------------|");
				System.out.println("    Choose an option :             ");
				System.out.println("|---------------------------------|");
				String op2 = scan.next();

				while (repeat) {
					switch (op2) {
					case "1":
						// Add Item
						Item.AddItem();
						break;

					case "2":
						// Delete Item
						Item.deleteItem();
						break;

					case "3":
						// Change Item price
						Item.ChangePrice();
						break;

					case "4":
						// Report ALL Item
						Item.ReadFromItemTable();
						break;

					case "5":
						// Go Back
						repeat = false;
						break;
					}

				}
			case "3":
				// Create New Invoicing
				Invoice.Insert_Invoice();
				break;

			case "4":
				// search Invoice By ID
				Invoice.InvoiceSearchById();
				break;

			case "5":
				running = false;
				break;

			}

		}

		// Record the completion time
		long completionTime = System.currentTimeMillis();

		// Calculate the time taken to run the code
		long elapsedTime = completionTime - startTime;

		// Print out the elapsed time
		System.out.println("Time taken: " + elapsedTime + " milliseconds");

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
