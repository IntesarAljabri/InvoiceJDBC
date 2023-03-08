package src;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {

	static ArrayList<String> invoiceList = new ArrayList<String>();

	static int invNO;
	static String costumerName;
	static int phone;
	static Date date;
	static int numberOfItems;
	static double totalAmount;
	static double paidAmount;
	static double balance;

	public static double Insert_Invoice(Statement statment) {
		/*
		 * String message = "How Many Invoice Do You want to Insert: ";
		 * System.out.print(message);
		 */

		try {
			String sql = "INSERT INTO Invoice (invNO, costumerName, phone, date ,numberOfItems   , totalAmount) "
					+ "VALUES (" + invNO + ", '" + costumerName + "', " + phone + "," + date + "', " + numberOfItems
					+ "', " + totalAmount + "', " + "')";
			System.out.println("|---------------------------------------------------------|");
			System.out.println("|                   INVOICE DETAILS                       |");
			System.out.println("|---------------------------------------------------------|");
			System.out.println("|Invoice ID :               " + Invoice.invNO + "         |");
			System.out.println("|Invoice customerName :     " + Invoice.costumerName + "  |");
			System.out.println("|Invoice phone :            " + Invoice.phone + "         |");
			System.out.println("|Invoice date :             " + Invoice.date + "          |");
			System.out.println("|Invoice numberOfItems :    " + Invoice.numberOfItems + " |");
			System.out.println("|Invoice totalAmount :      " + Invoice.totalAmount + "   |");
			System.out.println("|Invoice paidAmount :       " + Invoice.paidAmount + "    |");
			System.out.println("-----------------------------------------------------------");

			int rows = statment.executeUpdate(sql);
			System.out.println(rows + " row(s) inserted.");

			double unitPrice = 0;
			double quantity = 0;
			double qtyprice = unitPrice * quantity;

			// return qtyprice;

			double totalAmount = qtyprice;
			if (totalAmount == qtyprice) {
				System.out.println(" The Total_Amount : " + Invoice.totalAmount);

			} else if (qtyprice > totalAmount) {
				balance = totalAmount - paidAmount;
				return balance;
			} else {

				System.out.println("Check You Balance Plz");
			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		return balance;

	}

	// Select All data of invoice
	public static void ReadFromItemTable(Statement statment) {
		String message = "How many Invoice Do You Want To Print: ";
		System.out.print(message);
		int userinput = Main.getId(message);
		try {
			String sql = "Select (" + userinput + ") * from Invoice ";

			ResultSet result = statment.executeQuery(sql);

			if (invNO == Invoice.invNO) {
				for (int i = 0; i < Invoice.invoiceList.size(); i++) {
					while (result.next())
						System.out.println("|----------------------------------|");
					System.out.println("|" + result.getString("invNO         |"));
					System.out.println("|" + result.getString("costumerName  |"));
					System.out.println("|" + result.getString("phone         |"));
					System.out.println("|" + result.getString("date          |"));
					System.out.println("|" + result.getString("numberOfItems |"));
					System.out.println("|" + result.getString("totalAmount   |"));
					System.out.println("|" + result.getString("paidAmount    |"));
					System.out.println("|" + result.getString("Item_Id       |"));
					System.out.println("|" + result.getString("item_Name     |"));
					System.out.println("|" + result.getString("unitPrice     |"));
					System.out.println("|" + result.getString("quantity      |"));
					System.out.println("|" + result.getString("qtyprice      |"));
					System.out.println("|----------------------------------|");

				}
			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
