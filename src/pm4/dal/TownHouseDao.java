package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pm4.model.Houses;
import pm4.model.Owners;
import pm4.model.SingleFamilyHouse;
import pm4.model.TownHouse;

public class TownHouseDao extends HousesDao{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static TownHouseDao instance = null;
	protected TownHouseDao() {
		connectionManager = new ConnectionManager();
	}
	public static TownHouseDao getInstance() {
		if(instance == null) {
			instance = new TownHouseDao();
		}
		return instance;
	}
	
	public TownHouse create(TownHouse house) throws SQLException {
		// Insert into the superclass table first.
		Houses h = create(new Houses(house.getStreet1(), house.getStreet2(),house.getCity(),house.getState(),house.getZip(),house.getAvailability(),house.getAvailableDate(),house.getOwner(),house.getDescription(),house.getFlooring(),house.getBedrooms(),
				house.getBathrooms(),house.getDeposit(),house.getRating(), house.getPets(),house.getParking(),
				house.getLaundry(), house.getYearOfBuilder(),house.getRent()));

		String insertTH = "INSERT INTO TownHouses(HouseId,HouseValue,Furnished) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTH);
			insertStmt.setInt(1, h.getHouseId());
			insertStmt.setInt(2,house.getHouseValue());
			insertStmt.setBoolean(3,house.getFurnished());
			insertStmt.executeUpdate();
			
			house.setHouseId(h.getHouseId());
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
	 * Update the LastName of the Administrators instance.
	 * This runs a UPDATE statement.
	 */
	public TownHouse updateDescription(TownHouse house, String newDescription) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateDescription(house, newDescription);
		return house;
	}

	/**
	 * Delete the Administrators instance.
	 * This runs a DELETE statement.
	 */
	public TownHouse delete(TownHouse house) throws SQLException {
		String deleteHouse = "DELETE FROM TownHouses WHERE HouseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHouse);
			deleteStmt.setInt(1, house.getHouseId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Administrators first.
			super.delete(house);

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
	
	public TownHouse getTownHouseFromHouseId(int houseId) throws SQLException {
		// To build an Administrator object, we need the Persons record, too.
		String selectHouse =
			"SELECT TownHouses.HouseId AS HouseId, Street1,Street2,City,State,Zip,Availability,AvailableDate,OwnerId,Description,Flooring,Bedrooms,Bathrooms,Deposit,Rating,Pets,Parking,Laundry,YearOfBuild,Rent,HouseValue,Furnished " +
			"FROM TownHouses INNER JOIN Houses " +
			"  ON TownHouses.HouseId = Houses.HouseId " +
			"WHERE TownHouses.HouseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHouse);
			selectStmt.setInt(1, houseId);
			results = selectStmt.executeQuery();
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
				int houseValue = results.getInt("HouseValue");
				boolean furnished = results.getBoolean("Furnished");
				
				TownHouse house = new TownHouse(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
						bathrooms,deposit,rating, pets,parking,laundry, yearOfBuild,rent,houseValue,furnished);
				
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

	public List<TownHouse> getTownHouseFromCity(String city)
			throws SQLException {
		List<TownHouse> houses = new ArrayList<TownHouse>();
		String selectedHouses =
			"SELECT TownHouses.HouseId AS HouseId, Street1,Street2,City,State,Zip,Availability,AvailableDate,OwnerId,Description,Flooring,Bedrooms,Bathrooms,Deposit,Rating,Pets,Parking,Laundry,YearOfBuild,Rent,HouseValue,Furnished " +
			"FROM TownHouses INNER JOIN Houses " +
			"  ON TownHouses.HouseId = Houses.HouseId " +
			"WHERE Houses.City=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectedHouses);
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
				int houseValue = results.getInt("HouseValue");
				boolean furnished = results.getBoolean("Furnished");
				
				TownHouse house = new TownHouse(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
						bathrooms,deposit,rating, pets,parking,laundry, yearOfBuild,rent,houseValue,furnished);
				//System.out.println(" house Id" + house.getHouseId());
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
