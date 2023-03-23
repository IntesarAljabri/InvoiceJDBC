package src;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;
import java.text.DecimalFormat;

public class Item {
	static int Item_Id;
	static String Item_Name;
	static DecimalFormat UnitPrice;
	static DecimalFormat Quantity;
	static DecimalFormat QtyPrice;

	private static Statement statement;

	static Scanner scan = new Scanner(System.in);
	static ArrayList<String> ItemList = new ArrayList<String>();

	// Load Data
	public static void Load_Data() {

		String url = "jdbc:sqlserver://localhost:1433;" + "encrypt=true;" +"databaseName=InvoiceSystem;" + "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			boolean repeat = true;
			while (repeat) {
				System.out.print("Enter ItemID: ");
				int Item_Id = scan.nextInt();
				System.out.print("Enter item name: ");
				String Item_Name = scan.next();
				System.out.print("Enter unit price: ");
				double UnitPrice = scan.nextDouble();
				System.out.print("Enter quantity: ");
				double Quantity = scan.nextDouble();
				System.out.print("Enter quantity price : ");
				double QtyPrice = scan.nextDouble();

				String sql = "INSERT INTO Item (Item_Id, Item_Name, UnitPrice, Quantity,QtyPrice ) VALUES (" + Item_Id
						+ ", '" + Item_Name + "', " + UnitPrice + "," + Quantity + "," + QtyPrice + ")";
//					System.out.println("|--------------------------------------|");
//					System.out.println("|           Item Details               |");
//					System.out.println("|--------------------------------------|");
//					System.out.println("|Item Id   :"+ Item_Id );
//					System.out.println("|Item Name  :"+ Item_Name );
//					System.out.println("|Item Price :"+UnitPrice );
//					System.out.println("|Item Quantity:"+Quantity);
//					System.out.println("|--------------------------------------|");
				
				int rowsAffected = statement.executeUpdate(sql);
				
				System.out.println(rowsAffected + " row(s) inserted.");
                
				System.out.print("Do you want to insert another item? (y/n): ");
				String select = scan.next();
				if (select.equalsIgnoreCase("n")) {
					repeat = false;
				}
			}
			System.out.println("Done inserting items.");
			con.close();
			scan.close();
		} catch (SQLException ex) {
			System.out.println("Error connecting to the database.");
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			System.out.println("JDBC driver not found.");
			ex.printStackTrace();
		}
		 
	}

	static void AddItem() {
		boolean program = true;
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();

			while (program) {
				System.out.println("Enter ItemID ");
				int Item_Id = scan.nextInt();
				System.out.println("Enter item_Name ");
				String Item_Name = scan.next();
				System.out.println("Enter UnitPrice ");
				double UnitPrice = scan.nextDouble();
				System.out.println("Enter Quantity ");
				double Quantity = scan.nextDouble();

				// Cast the newItem to your actual item class
				// Item Item = new Item();
				// Create an SQL INSERT statement
				String sql = "INSERT INTO Items (item_Id, item_Name,UnitPrice, Quantity , QtyPrice) VALUES (" + Item_Id
						+ " , " + Item_Name + ", " + UnitPrice + " , " + Quantity + " )";
				System.out.println("|--------------------------------------|");
				System.out.println("|           Item Details               |");
				System.out.println("|--------------------------------------|");
				System.out.println("|Item Id   :" + Item_Id);
				System.out.println("|Item Name  :" + Item_Name);
				System.out.println("|Item Price :" + UnitPrice);
				System.out.println("|Item Quantity:" + Quantity);
				System.out.println("|--------------------------------------|");
				 
	           int rowsAffected = statement.executeUpdate(sql);
			   System.out.println(rowsAffected + " row(s) inserted.");

				while (true) {
					System.out.print("Do you want to add Item ? (y/n):    ");
					String select = scan.next();
					if (select.equals("N") || select.equals("n")) {
						program = false;
						break;
					} else if (select.equals("y") || select.equals("Y")) {
						break;
					} else {
						System.out.println("Invalid Input ");
					}
				}

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// Delete Item by ID
	public static void deleteItem() {
	    String url = "jdbc:sqlserver://localhost:1433;" +"databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";
	    String user = "sa";
	    String pass = "root";
	    Connection con = null;
	    boolean repeat = true;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(url, user, pass);
	        while (repeat) {
	            System.out.println("Enter item ID to delete: ");
	            int itemId = scan.nextInt();

	            String sql = "DELETE FROM Item WHERE Item_Id =" + itemId;
	            PreparedStatement statement = con.prepareStatement(sql);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println(rowsDeleted + " row(s) deleted.");
	            } else {
	                System.out.println("No rows deleted.");
	            }

	            while (true) {
	                System.out.print("Do you want to delete another item? (y/n): ");
	                String select = scan.next();
	                if (select.equals("N") || select.equals("n")) {
						repeat = false;
						break;
					} else if (select.equals("y") || select.equals("Y")) {
						break;
					} else {
						System.out.println("Invalid Input ");
					}
	            }
	        }
	    }  catch (ClassNotFoundException | SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	// Change Price by enter the id
	public static void ChangePrice() {
	    String url = "jdbc:sqlserver://localhost:1433;" +"databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";
	    String user = "sa";
	    String pass = "root";
	    Connection con = null;
	    Scanner scan = new Scanner(System.in);

	    boolean again = true;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(url, user, pass);
	        Statement statement = con.createStatement();

	        while (again) {
	            System.out.print("Enter the ID of the item you want to update: ");
	            int itemId = scan.nextInt();
	            System.out.print("Enter the new price: ");
	            double newPrice = scan.nextDouble();
	            String sql = "UPDATE Item SET UnitPrice = " + newPrice + " WHERE Item_Id = " + itemId;
	            statement.executeUpdate(sql);

	            sql = "SELECT * FROM Item WHERE Item_Id = " + itemId;
	            ResultSet result = statement.executeQuery(sql);
	            while (result.next()) {
	                System.out.println("|--------------------------------|");
	                System.out.println("|        ITEM UPDATE             |");
	                System.out.println("|--------------------------------|");
	                System.out.println("| ID         | " + result.getString("Item_Id"));
	                System.out.println("| Name       | " + result.getString("Item_Name"));
	                System.out.println("| Unit Price | " + result.getString("UnitPrice"));
	                System.out.println("| Quantity   | " + result.getString("Quantity"));
	                System.out.println("| Qty Price  | " + result.getString("QtyPrice"));
	                System.out.println("|--------------------------------|");
	            }

	            while (true) {
	                System.out.print("Do you want to update another item? (y/n): ");
	                String select = scan.next();
	                if (select.equalsIgnoreCase("n")) {
	                    again = false;
	                    break;
	                } else if (select.equalsIgnoreCase("y")) {
	                    break;
	                } else {
	                    System.out.println("Invalid input.");
	                }
	            }
	        }

	        con.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}

		
	
	// select All data
	public static void ReadFromItemTable() {
	    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=InvoiceSystem;" + "encrypt=true;" + "trustServerCertificate=true";

	    String user = "sa";
	    String pass = "root";
	    Connection con = null;
	    Scanner scan = new Scanner(System.in);
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(url, user, pass);
	        Statement statement = con.createStatement();

	        System.out.println("Enter Item Id You Want To Print: ");
	        int Item_Id = scan.nextInt();

	        String sql = "Select * from Item where Item_Id = " + Item_Id;

	        ResultSet result = statement.executeQuery(sql);
	        while (result.next()) {
	            System.out.println("|----------------------------------------------------|");
	            System.out.println("| item number " + Item_Id + "                        |");
	            System.out.println("|----------------------------------------------------|");
	            System.out.println("|Item Id " + result.getString("Item_Id") + "         |");
	            System.out.println("|Item Name " + result.getString("Item_Name") + "     |");
	            System.out.println("|Unit Price " + result.getString("UnitPrice") + "    |");
	            System.out.println("|Quantity " + result.getString("Quantity") + "       |");
	            System.out.println("|Qty Price" + result.getString("QtyPrice") + "       |");
	            System.out.println("|----------------------------------------------------|");
	        }
	    } catch (Exception ex) {
	        System.out.println(ex);
	    } 
	}
}