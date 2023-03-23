package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	static Scanner scan = new Scanner(System.in);
	static String shop_name;
	static String tel_Number;
	static String Fax_Number;
	static String email;
	static String website;
	static ArrayList<String> invoiceList = new ArrayList<String>();
	public static void Set_Information() {

		String url = "jdbc:sqlserver://localhost:1433;" +"databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();

			System.out.println("Enter Shop Name ");
			String shopName = scan.next();
			System.out.println("Enter telNumber ");
			int telNumber = scan.nextInt();
			System.out.println("Enter faxNumber");
			int faxNumber = scan.nextInt();
			System.out.println("Enter Email ");
			String email = scan.next();
			System.out.println("Enter website");
			String website = scan.next();
			
			
			
			if (Shop.tel_Number == null) {
			    // handle the case where the tel_Number is null
			} else {
			    String sql = "INSERT INTO Shop(ShopName, Fax, email, website, tel_Number) " +
			        "VALUES ('" + Shop.shop_name + "', '" + Shop.Fax_Number + "', '" +
			        Shop.email + "', '" + Shop.website + "', " + Shop.tel_Number + ")";

				System.out.println("|--------------------------------|");
				System.out.println("|      Shop Information          |");
				System.out.println("|--------------------------------|");
				System.out.println("|Shop Name:"+ Shop.shop_name);
				System.out.println("|Tel Number:"+ Shop.tel_Number);
				System.out.println("|Fax Number:"+Shop.Fax_Number);
				System.out.println("|Shop Email:"+Shop.email);
				System.out.println("|Shop website:"+ Shop.website);
				System.out.println("|---------------------------------|");

			    int rowsAffected = statement.executeUpdate(sql);
			    System.out.println(rowsAffected + " row(s) inserted.");
			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
	}

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
