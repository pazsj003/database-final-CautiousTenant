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
import pm4.model.Reservations;
import pm4.model.Tenants;



public class ReservationsDao {
	protected ConnectionManager connectionManager;

	private static ReservationsDao instance = null;
	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationsDao getInstance() {
		if(instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}
	
	public Reservations create(Reservations reservation) throws SQLException{
		 String insertreservation =
					"INSERT INTO Reservations(Start,End,Size,UserName,HouseId) " +
					"VALUES(?,?,?,?,?);";
				Connection connection = null;
				PreparedStatement insertStmt = null;
				ResultSet resultKey = null;
				try {
					connection = connectionManager.getConnection();
					insertStmt = connection.prepareStatement(insertreservation,
						Statement.RETURN_GENERATED_KEYS);
					
					insertStmt.setTimestamp(1, new Timestamp(reservation.getStart().getTime()));
					insertStmt.setTimestamp(2, new Timestamp(reservation.getEnd().getTime()));
					insertStmt.setInt(3, reservation.getSize());
					insertStmt.setString(4, reservation.getTenants().getUserName());
					insertStmt.setInt(5, reservation.getHouses().getHouseId());
					insertStmt.executeUpdate();
					
					// Retrieve the auto-generated key and set it, so it can be used by the caller.
					resultKey = insertStmt.getGeneratedKeys();
					int ReservationId = -1;
					if(resultKey.next()) {
						ReservationId = resultKey.getInt(1);
					} else {
						throw new SQLException("Unable to retrieve auto-generated key.");
					}
					reservation.setReservationId(ReservationId);
					return reservation;
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
					if(resultKey != null) {
						resultKey.close();
					}
				}
	}
	
	
	public Reservations getReservationById(int reservationId) throws SQLException{
		String selectReservations =
				"SELECT ReservationId,Start,End,Size,UserName,HouseId " +
				"FROM Reservations " +
				"WHERE ReservationId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReservations);
				selectStmt.setInt(1, reservationId);
			
				results = selectStmt.executeQuery();
				
				TenantsDao tenantsDao = TenantsDao.getInstance();
				HousesDao houseDao = HousesDao.getInstance();
				if(results.next()) {
					
					int resultreservationId = results.getInt("ReservationId");
					Date Start = new Date(results.getTimestamp("Start").getTime());
					Date End = new Date(results.getTimestamp("End").getTime());
					int Size = results.getInt("Size");
					String UserName = results.getString("UserName");
					int HouseId = results.getInt("HouseId");
					
					
					
					Tenants User = tenantsDao.getTenantFromUserName(UserName);
					Houses Houses = houseDao.getHouseFromHouseId(HouseId);
					Reservations Reservations = new Reservations(resultreservationId,Start, End,Size,Houses,User);
					return Reservations;
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
	
	
	public List<Reservations> getReservationsByUserName(String userName)throws SQLException{
		List<Reservations> ReservationsList = new ArrayList<Reservations>();
		String selectReservations =
				"SELECT ReservationId,Start,End,Size,UserName,HouseId " +
				"FROM Reservations " +
				"WHERE UserName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReservations);
				selectStmt.setString(1, userName);
			
				results = selectStmt.executeQuery();
				TenantsDao tenantsDao = TenantsDao.getInstance();
				HousesDao houseDao = HousesDao.getInstance();
				while(results.next()) {
					
					int resultreservationId = results.getInt("ReservationId");
					Date Start = new Date(results.getTimestamp("Start").getTime());
					System.out.println(Start);
					Date End = new Date(results.getTimestamp("End").getTime());
					int Size = results.getInt("Size");
					String UserName = results.getString("UserName");
                    int HouseId = results.getInt("HouseId");
					
					
					
					Tenants User = tenantsDao.getTenantFromUserName(UserName);
					Houses Houses = houseDao.getHouseFromHouseId(HouseId);
					Reservations Reservations = new Reservations(resultreservationId,Start, End,Size,Houses,User);
					ReservationsList.add(Reservations);
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
			return ReservationsList;
	}
	
	
	public List<Reservations> getReservationsByHouseId(int HouseId)throws SQLException{
		List<Reservations> ReservationsList = new ArrayList<Reservations>();
		String selectReservations =
				"SELECT ReservationId,Start,End,Size,UserName,HouseId " +
				"FROM Reservations " +
				"WHERE HouseId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReservations);
				selectStmt.setInt(1, HouseId);
			
				results = selectStmt.executeQuery();
				TenantsDao tenantsDao = TenantsDao.getInstance();
				HousesDao houseDao = HousesDao.getInstance();
				while(results.next()) {
					
					int resultreservationId = results.getInt("ReservationId");
					Date Start = new Date(results.getTimestamp("Start").getTime());
					Date End = new Date(results.getTimestamp("End").getTime());
					int Size = results.getInt("Size");
					String UserName = results.getString("UserName");
					int houseId = results.getInt("HouseId");

					Tenants User = tenantsDao.getTenantFromUserName(UserName);
					Houses Houses = houseDao.getHouseFromHouseId(houseId);
					Reservations Reservations = new Reservations(resultreservationId,Start, End,Size,Houses,User);
					
					ReservationsList.add(Reservations);
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
			return ReservationsList;
	}
	
	
	public Reservations delete(Reservations reservation) throws SQLException{
		String deletereservation = "DELETE FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletereservation);
			deleteStmt.setInt(1, reservation.getReservationId());
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
}
