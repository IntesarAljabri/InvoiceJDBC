package src;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Shop {

	static String shop_name;
	static String tel_Number;
	static String Fax_Number;
	static String email;
	static String website;
	static ArrayList<String> invoiceList = new ArrayList<String>();

//	// To insert invoice details in shop
//	public static void insertInShop_INVOICEDETAILS(Statement statment) throws SQLException {
//		String message = "How Many Invoice Do You want to Insert: ";
//		System.out.print(message);
//       System.out.println("I")
//		// To insert the data into the invoice table
//		String sql = "INSERT INTO Invoice (shop_name, tel_number, fax_number, email, website) VALUES (" + shopName
//				+ ", '" + telNumber + "', " + faxNumber + "'," + email + "'," + website + "'," + ")";
//
//		// Execute the statement and get the number of rows affected
//		int rows = statment.executeUpdate(sql);
//		System.out.println(rows + " row(s) inserted.");
//
//	}
}
