package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Tenants;
import pm4.model.Users;

public class TenantsDao extends UsersDao {
	private static TenantsDao instance = null;
	protected TenantsDao() {
		super();
	}
	public static TenantsDao getInstance() {
		if(instance == null) {
			instance = new TenantsDao();
		}
		return instance;
	}

	public Tenants create(Tenants tenant) throws SQLException {
		// Insert into the superclass table first.
		create(new Users(tenant.getUserName(),tenant.getPassWord(),tenant.getFirstName(),tenant.getLastName(),tenant.getEmail(),tenant.getPhone(),
				tenant.getAge(),tenant.getStreet1(),tenant.getStreet2(),tenant.getCity(),tenant.getState(),tenant.getZip()));

		String insertTenant = "INSERT INTO Tenants(UserName,Pets,RentDays,Rent,Parking,QuietLevel,Bathroom,RoomShare) "
				+ "VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTenant);
			insertStmt.setString(1, tenant.getUserName());
//			insertStmt.setString(2, tenant.getPassWord());
//			insertStmt.setString(3, tenant.getFirstName());
//			insertStmt.setString(4, tenant.getLastName());
//			insertStmt.setString(5, tenant.getEmail());
//			insertStmt.setString(6, tenant.getPhone());
//			insertStmt.setInt(7, tenant.getAge());
//			insertStmt.setString(8, tenant.getStreet1());
//			insertStmt.setString(9, tenant.getStreet2());
//			insertStmt.setString(10, tenant.getCity());
//			insertStmt.setString(11, tenant.getState());
//			insertStmt.setInt(12, tenant.getZip());
//			insertStmt.setBoolean(13, tenant.getPets());
//			insertStmt.setInt(14, tenant.getRentDays());
//			insertStmt.setInt(15, tenant.getRent());
//			insertStmt.setBoolean(16, tenant.getParking());
//			insertStmt.setInt(17, tenant.getQuietLevel());
//			insertStmt.setBoolean(18, tenant.getBathroom());
//			insertStmt.setBoolean(19, tenant.getRoomShare());
			insertStmt.setBoolean(2, tenant.getPets());
			insertStmt.setInt(3, tenant.getRentDays());
			insertStmt.setInt(4, tenant.getRent());
			insertStmt.setBoolean(5, tenant.getParking());
			insertStmt.setInt(6, tenant.getQuietLevel());
			insertStmt.setBoolean(7, tenant.getBathroom());
			insertStmt.setBoolean(8, tenant.getRoomShare());
			insertStmt.executeUpdate();
			return tenant;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Delete the Tenants instance.
	 * This runs a DELETE statement.
	 */
	public Tenants delete(Tenants tenant) throws SQLException {
		String deleteTenant = "DELETE FROM Tenants WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTenant);
			deleteStmt.setString(1, tenant.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + tenant.getUserName());
			}

			super.delete(tenant);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Tenants getTenantFromUserName(String userName) throws SQLException {
		// To build an Tenant object, we need the Persons record, too.
		String selectTenant =
			"SELECT Tenants.UserName AS UserName,PassWord,FirstName,LastName,Email,Phone,Age,Street1,Street2,"
			+ "City,State,Zip,Pets,RentDays,Rent,Parking,QuietLevel,Bathroom,RoomShare " +
			"FROM Tenants INNER JOIN Users " +
			"  ON Tenants.UserName = Users.UserName " +
			"WHERE Tenants.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTenant);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String passWord = results.getString("PassWord");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int age = results.getInt("Age");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				Boolean pets = results.getBoolean("Pets");
				int rentDays = results.getInt("RentDays");
				int rent = results.getInt("Rent");
				Boolean parking = results.getBoolean("Parking");
				int quietLevel = results.getInt("QuietLevel");
				Boolean bathroom = results.getBoolean("Bathroom");
				Boolean roomShare = results.getBoolean("RoomShare");
				Tenants tenant = new Tenants(resultUserName, passWord, firstName, lastName, email, phone, age, street1, street2, city, state, zip, pets, rentDays, rent, parking, quietLevel,
						bathroom, roomShare);
				return tenant;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Tenants> getTenantsFromFirstName(String firstName)
			throws SQLException {
		List<Tenants> tenantList = new ArrayList<Tenants>();
		String selectTenants =
			"SELECT Tenants.UserName AS UserName,PassWord,FirstName,LastName,Email,Phone,Age,Street1,Street2," +
			"City,State,Zip,Pets,RentDays,Rent,Parking,QuietLevel,Bathroom,RoomShare " +
			"FROM Tenants INNER JOIN Users " +
			"  ON Tenants.UserName = Users.UserName " +
			"WHERE Users.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTenants);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String passWord = results.getString("PassWord");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				int age = results.getInt("Age");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				Boolean pets = results.getBoolean("Pets");
				int rentDays = results.getInt("RentDays");
				int rent = results.getInt("Rent");
				Boolean parking = results.getBoolean("Parking");
				int quietLevel = results.getInt("QuietLevel");
				Boolean bathroom = results.getBoolean("Bathroom");
				Boolean roomShare = results.getBoolean("RoomShare");
				Tenants tenant = new Tenants(userName, passWord, resultFirstName, lastName, email, phone, age, street1, street2, city, state, zip, pets, rentDays, rent, parking, quietLevel,
						bathroom, roomShare);
				tenantList.add(tenant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return tenantList;
	}
}
