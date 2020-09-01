package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pm4.model.Houses;
import pm4.model.Owners;


public class HousesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static HousesDao instance = null;
	protected HousesDao() {
		connectionManager = new ConnectionManager();
	}
	public static HousesDao getInstance() {
		if(instance == null) {
			instance = new HousesDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Houses create(Houses house) throws SQLException {
		String insertHouse = "INSERT INTO Houses(Street1,Street2,City,State,Zip,Availability,AvailableDate,OwnerId,Description,Flooring,Bedrooms,Bathrooms,Deposit,Rating,Pets,Parking,Laundry,YearOfBuild,Rent) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHouse);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt = connection.prepareStatement(insertHouse,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, house.getStreet1());
			insertStmt.setString(2, house.getStreet2());
			insertStmt.setString(3, house.getCity());
			insertStmt.setString(4, house.getState());
			insertStmt.setInt(5, house.getZip());
			insertStmt.setBoolean(6, house.getAvailability());
			insertStmt.setTimestamp(7, new Timestamp(house.getAvailableDate().getTime()));
			insertStmt.setInt(8, house.getOwner().getOwnerId());
			insertStmt.setString(9, house.getDescription());
			insertStmt.setInt(10, house.getFlooring());
			insertStmt.setInt(11, house.getBedrooms());
			insertStmt.setInt(12, house.getBathrooms());
			insertStmt.setInt(13, house.getDeposit());
			insertStmt.setFloat(14, house.getRating());
			insertStmt.setBoolean(15, house.getPets());
			insertStmt.setBoolean(16, house.getParking());
			insertStmt.setBoolean(17, house.getLaundry());
			insertStmt.setInt(18, house.getYearOfBuilder());
			insertStmt.setInt(19, house.getRent());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int houseId = -1;
			if(resultKey.next()) {
				houseId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			house.setHouseId(houseId);
			return house;
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
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 */
	public Houses updateDescription(Houses house, String newDescription) throws SQLException {
		String updatePerson = "UPDATE Houses SET Description=? WHERE HouseId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, house.getHouseId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			house.setDescription(newDescription);
			return house;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Houses delete(Houses house) throws SQLException {
		String deletePerson = "DELETE FROM Houses WHERE HouseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1, house.getHouseId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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

	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Houses getHouseFromHouseId(int houseId) throws SQLException {
		String selectPerson = "SELECT HouseId,Street1,Street2,City,State,Zip,Availability,AvailableDate,OwnerId,Description,Flooring,Bedrooms,Bathrooms,Deposit,Rating,Pets,Parking,Laundry,YearOfBuild,Rent FROM Houses WHERE HouseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, houseId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
//				String houseId=results.getString("HouseId");
				String street1=results.getString("Street1");
				String street2=results.getString("Street2");
				String city=results.getString("City");
				String state=results.getString("State");
				int zip = results.getInt("Zip");
				boolean availability = results.getBoolean("Availability");
				Date availableDate = new Date(results.getTimestamp("AvailableDate").getTime());			
				int ownerId = results.getInt("OwnerId");
				OwnersDao ownerDao = new OwnersDao();
				Owners owner = ownerDao.getOwnerFromOwnerId(ownerId);
				String description=results.getString("Description");
				int flooring = results.getInt("Flooring");
				int bedrooms=results.getInt("Bedrooms");
				int bathrooms=results.getInt("Bathrooms");
				int deposit=results.getInt("Deposit");
				float rating=results.getFloat("Rating");
				boolean pets=results.getBoolean("Pets");
				boolean parking=results.getBoolean("Parking");
				boolean laundry=results.getBoolean("Laundry");
				int yearOfBuild=results.getInt("YearOfBuild");
				int rent=results.getInt("Rent");
				
				Houses house = new Houses(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
						bathrooms,deposit,rating, pets,parking,
						laundry, yearOfBuild,rent);
				return house;
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

	/**
	 * Get the matching Persons records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Persons.
	 */
	public List<Houses> getHousesFromCity(String city) throws SQLException {
		List<Houses> houses = new ArrayList<Houses>();
		String selectPersons =
			"SELECT HouseId,Street1,Street2,City,State,Zip,Availability,AvailableDate,OwnerId,Description,Flooring,Bedrooms,Bathrooms,Deposit,Rating,Pets,Parking,Laundry,YearOfBuild,Rent FROM Houses WHERE City=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersons);
			selectStmt.setString(1, city);
			results = selectStmt.executeQuery();
			while(results.next()) {
				
				
				int houseId=results.getInt("HouseId");
				String street1=results.getString("Street1");
				String street2=results.getString("Street2");
//				String city=results.getString("City");
				String state=results.getString("State");
				int zip = results.getInt("Zip");
				boolean availability = results.getBoolean("Availability");
				Date availableDate;
				try {
					availableDate= new Date(results.getTimestamp("AvailableDate").getTime());
				}
				catch(Exception e) {
					availableDate = new Date();
				}		
				int ownerId = results.getInt("OwnerId");
				OwnersDao ownerDao = new OwnersDao();
				Owners owner = ownerDao.getOwnerFromOwnerId(ownerId);
				String description=results.getString("Description");
				int flooring = results.getInt("Flooring");
				int bedrooms=results.getInt("Bedrooms");
				int bathrooms=results.getInt("Bathrooms");
				int deposit=results.getInt("Deposit");
				float rating=results.getFloat("Rating");
				boolean pets=results.getBoolean("Pets");
				boolean parking=results.getBoolean("Parking");
				boolean laundry=results.getBoolean("Laundry");
				int yearOfBuild=results.getInt("YearOfBuild");
				int rent=results.getInt("Rent");
				
				Houses house = new Houses(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
						bathrooms,deposit,rating, pets,parking,
						laundry, yearOfBuild,rent);
				houses.add(house);
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
		return houses;
	}
	
	/**
	 * Get the matching Persons records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Persons.
	 */
	public List<Houses> getCheapestHousesFromCity(String city) throws SQLException {
		List<Houses> houses = new ArrayList<Houses>();
		String selectPersons =
			"SELECT HouseId,Street1,Street2,City,State,Zip,Availability,AvailableDate,OwnerId,Description,Flooring,Bedrooms,Bathrooms,Deposit,Rating,Pets,Parking,Laundry,YearOfBuild,Rent FROM Houses WHERE Availability = 1 AND City=? ORDER BY Rent LIMIT 50;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		 
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersons);
			selectStmt.setString(1, city);
			results = selectStmt.executeQuery();
			while(results.next()) {
				
				
				int houseId=results.getInt("HouseId");
				 
				String street1=results.getString("Street1");
				String street2=results.getString("Street2");
//				String city=results.getString("City");
				String state=results.getString("State");
				int zip = results.getInt("Zip");
				boolean availability = results.getBoolean("Availability");
				Date availableDate;
				try {
					availableDate= new Date(results.getTimestamp("AvailableDate").getTime());
				}
				catch(Exception e) {
					availableDate = new Date();
				}		
				int ownerId = results.getInt("OwnerId");
				OwnersDao ownerDao = new OwnersDao();
				
				Owners owner = ownerDao.getOwnerFromOwnerId(ownerId);
				String description=results.getString("Description");
				int flooring = results.getInt("Flooring");
				int bedrooms=results.getInt("Bedrooms");
				int bathrooms=results.getInt("Bathrooms");
				int deposit=results.getInt("Deposit");
				float rating=results.getFloat("Rating");
				boolean pets=results.getBoolean("Pets");
				boolean parking=results.getBoolean("Parking");
				boolean laundry=results.getBoolean("Laundry");
				int yearOfBuild=results.getInt("YearOfBuild");
				int rent=results.getInt("Rent");
				
				Houses house = new Houses(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
						bathrooms,deposit,rating, pets,parking,
						laundry, yearOfBuild,rent);
				houses.add(house);
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
		return houses;
	}
}
