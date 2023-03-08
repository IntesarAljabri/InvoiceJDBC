package src;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Item {
	private static final String Item_Id = null;

	static ArrayList<String> ItemList = new ArrayList<String>();

	// Load Data
	public static void insert_in_Table(Statement statment) {
		String message = "How Many Item Do You want to Insert: ";
		System.out.print(message);

		double qtyprice = 0;
		double unitPrice = 0;
		String item_Name = null;
		double quantity = 0;

		try {
			String sql = "INSERT INTO Item (Item_Id, item_Name, unitPrice, quantity) " + "VALUES (" + Item_Id + ", '"
					+ item_Name + "', " + unitPrice + "," + quantity + ")";

			int rows = statment.executeUpdate(sql);
			System.out.println(rows + " row(s) inserted.");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// Delete Item by ID
	public static void delete_Item(Statement statment) {
		String message = "select Items Do You Want To Delete by id: ";
		System.out.print(message);
		int userinput = Main.getId(message);
		try {
			String sql = "Delete From Item Where id = " + userinput + ";";
			statment.executeUpdate(sql);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// Change Price by enter the id
	public static void ChangePrice(Statement statment) {
		String message = "select Item Do You Want To change the Price by id ";
		System.out.print(message);
		int userinput = Main.getId(message);
		try {
			String sql = "Update Item" + "set Price = GETPRICE" + "WHERE id = " + userinput + ";";
			statment.executeUpdate(sql);

			sql = "Select * from Item Where id = " + userinput + ";";

			ResultSet result = statment.executeQuery(sql);
			while (result.next()) {
				System.out.println("|------------------------------|");
				System.out.println("|        ITEM UPDATE           |");
				System.out.println("|------------------------------|");
				System.out.println("|" + result.getString("Item_Id   |"));
				System.out.println("|" + result.getString("item_Name |"));
				System.out.println("|" + result.getString("unitPrice |"));
				System.out.println("|" + result.getString("quantity  |"));
				System.out.println("|" + result.getString("qtyprice  |"));
				System.out.println("|------------------------------|");

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// select All data
	public static void ReadFromItemTable(Statement statment) {
		String message = "How many Item Do You Want To Print: ";
		System.out.print(message);
		int userinput = Main.getId(message);
		try {
			String sql = "Select (" + userinput + ") * from Item";

			ResultSet result = statment.executeQuery(sql);
			for (int i = 0; i < Item.ItemList.size(); i++) {
				while (result.next())
			    System.out.println("|-------------------------------------|");
				System.out.println("|"+result.getString("Item_Id          |"));
				System.out.println("|"+result.getString("item_Name        |"));
				System.out.println("|"+result.getString("unitPrice        |"));
				System.out.println("|"+result.getString("quantity         |"));
				System.out.println("|"+result.getString("qtyprice         |"));
				System.out.println("|"+result.getString("invNO            |"));
				System.out.println("|"+result.getString("costumerName     |"));
				System.out.println("|"+result.getString("phone            |"));
				System.out.println("|"+result.getString("date             |"));
				System.out.println("|"+result.getString("numberOfItems    |"));
				System.out.println("|"+result.getString("totalAmount      |"));
				System.out.println("|"+result.getString("paidAmount       |"));
				System.out.println("|-------------------------------------|");

			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
