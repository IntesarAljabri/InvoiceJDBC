package src;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Invoice {

	private static final int UnitPrice = 0;
	private static final int Quantity = 0;
	static ArrayList<String> invoiceList = new ArrayList<String>();
	static Scanner scan = new Scanner(System.in);

	static int InvNO;
	static String Costumer_Name;
	static int Phone;
	static Date Date;
	static int NumberOfItem;
	static double TotalPrice;
	static DecimalFormat PaidAmount;
	static DecimalFormat Balance;
	private static Statement statment;

	public static void Insert_Invoice() {
	    Scanner scan = new Scanner(System.in);
	    boolean repeat = true;
	    String url = "jdbc:sqlserver://localhost:1433;" +"databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";
	    String user = "sa";
	    String pass = "root";
	    Connection con = null;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(url, user, pass);
	        Statement statement = con.createStatement();

	        System.out.println("|-------------------------------------------|");
	        System.out.println("|                INVOICE DETAILS            |");
	        System.out.println("|-------------------------------------------|");

	        // Get input values from the user
	        System.out.print("Enter Invoice Id: ");
	        int invNO = scan.nextInt();
	        System.out.print("Enter customer name: ");
	        String customerName = scan.next();
	        System.out.print("Enter Customer phone: ");
	        int phone = scan.nextInt();
	        System.out.print("Enter date: ");
	        String date = scan.next();
	        System.out.print("Enter number of items: ");
	        int numberOfItems = scan.nextInt();
	        System.out.print("Enter total price: ");
	        double totalPrice = scan.nextDouble();
	        System.out.print("Enter paid amount: ");
	        double paidAmount = scan.nextDouble();
	        System.out.print("Enter balance: ");
	        double balance = scan.nextDouble();

	        String sql = "INSERT INTO Invoice (invNO, Customer_Name, phone, date, numberOfItems, totalAmount) "
	                + "VALUES (" + InvNO + ", '" + Costumer_Name + "', " + Phone + ", '" + Date + "', " + NumberOfItem
	                + ", " + TotalPrice + ")";
	        // Output the invoice details to the console
	        System.out.println("|------------------------------------------|");
	        System.out.println("|Invoice ID: " + InvNO);
	        System.out.println("|Customer Name: " + Costumer_Name);
	        System.out.println("|customer Phone: " + Phone);
	        System.out.println("|Invoice Date: " + Date);
	        System.out.println("|Number of Items: " + NumberOfItem);
	        System.out.println("|Total Amount: " + TotalPrice);
	        System.out.println("|paidAMount: " + PaidAmount);
	        System.out.println("|Balance: " + Balance);
	        System.out.println("|------------------------------------------|");

	        int rowsAffected = statement.executeUpdate(sql);
	        System.out.println(rowsAffected + " row(s) inserted.");

	        if (numberOfItems <= 0 || totalPrice < 0) {
	            throw new IllegalArgumentException("Invalid input values");
	        }

	        double total = totalPrice;

	        // Use a DecimalFormat object to format the total amount to two decimal places
	        DecimalFormat df = new DecimalFormat("#.##");
	        df.setRoundingMode(RoundingMode.HALF_UP);
	        String formattedTotal = df.format(total);

	        // Output the total amount to the console
	        System.out.println("The Total Amount: " + formattedTotal);

	        // Convert the formatted total amount back to a double for comparison
	        double totalAmountValue = Double.parseDouble(formattedTotal);

	        // Check if the paid amount is greater than or equal to the total amount
	        if (paidAmount >= totalAmountValue) {
	            double balanceValue = paidAmount - totalAmountValue;
	            System.out.println("Balance: " + balanceValue);
	        } else {
	            throw new IllegalArgumentException("Insufficient payment amount");
	        }
		} catch (Exception ex) {
			System.out.println(ex);
		}

		while (true) {
			System.out.print("Do you want to insert new invoice? (y/n): ");
			String select = scan.next();
			if (select.equals("N") || select.equals("n")) {
				repeat = false;
				break;
			} else if (select.equals("y") || select.equals("Y")) {
				break;
			} else {
				System.out.println("Invalid Input");
			}
		}
	}

	
	// Select All data of invoice
	public static void ReadFromItemTable() {
		String url = "jdbc:sqlserver://localhost:1433;" +"databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();

		
			System.out.print("Enter Invoice Id To Print ");
			int invNO=scan.nextInt();

			String sql = "Select * from Invoice where invNO =  "+ invNO;

			ResultSet result = statment.executeQuery(sql);

			if (invNO == Invoice.InvNO) {
				for (int i = 0; i < Invoice.invoiceList.size(); i++) {
					while (result.next())
						System.out.println("|------------------------------------|");
					System.out.println("|" + result.getString("invNO         |"));
					System.out.println("|" + result.getString("costumerName  |"));
					System.out.println("|" + result.getString("date          |"));
					System.out.println("|" + result.getString("numberOfItems |"));
					System.out.println("|" + result.getString("totalAmount   |"));
					System.out.println("|------------------------------------|");
					// System.out.println("|" + result.getString("phone |"));
					// System.out.println("|" + result.getString("paidAmount |"));
					// System.out.println("|" + result.getString("Item_Id |"));
					// System.out.println("|" + result.getString("item_Name |"));
					// System.out.println("|" + result.getString("unitPrice |"));
					// System.out.println("|" + result.getString("quantity |"));
					// System.out.println("|" + result.getString("qtyprice |"));
					// System.out.println("|----------------------------------|");

				}
			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static double InvoiceSearchById() {
		System.out.println("Enter invoice ID to search : ");
		int id = scan.nextInt();

		double totalAmount = Quantity * UnitPrice;

		try {

			String sql = "SELECT * FROM invoices WHERE invoice_id = " + id;

			ResultSet result = statment.executeQuery(sql);

			if (id == Invoice.invoiceList.size()) {
				for (int i = 0; i < Invoice.invoiceList.size(); i++) {
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|             Invoice Number :" + InvNO + "              |");
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|Invoice NO: " + result.getInt("InvNO") + "              |");
					System.out.println("|Customer Name: " + result.getString("Costumer_Name") + " |");
					System.out.println("|Invoice Date: " + result.getDate("Date") + "            |");
					System.out.println("|Total Amount: " + result.getDouble("totalAmount") + "   |");
					System.out.println("|--------------------------------------------------------|");
				}
			} else {

				System.out.println("No invoice there !! Sorry....");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalAmount;
	}
}
