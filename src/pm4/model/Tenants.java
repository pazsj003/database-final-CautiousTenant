package pm4.model;

public class Tenants extends Users {
	protected boolean pets;
	protected int rentDays;
	protected int rent;
	protected boolean parking;
	protected int quietLevel;
	protected boolean bathroom;
	protected boolean roomShare;
	
	public Tenants (String userName, String passWord, String firstName, String lastName, String email, String phone, int age,
			String street1, String street2, String city, String state, int zip, boolean pets, int rentDays, int rent,
			boolean parking, int quietLevel, boolean bathroom, boolean roomShare) {
		super(userName, passWord, firstName, lastName, email, phone, age, street1, street2, city, state, zip);
		this.pets = pets;
		this.rentDays = rentDays;
		this.rent = rent;
		this.parking = parking;
		this.quietLevel = quietLevel;
		this.bathroom = bathroom;
		this.roomShare = roomShare;
	}
	
//	public Tenants (String userName, String firstName, String lastName, boolean pets, int rentDays, int rent,
//			boolean parking, int quietLevel, boolean bathroom, boolean roomShare) {
//		super(userName, firstName, lastName);
//		this.pets = pets;
//		this.rentDays = rentDays;
//		this.rent = rent;
//		this.parking = parking;
//		this.quietLevel = quietLevel;
//		this.bathroom = bathroom;
//		this.roomShare = roomShare;
//	}
	
	public Tenants(String userName) {
		super(userName);
	}
	
	public boolean getPets() {
		return pets;
	}
	
	public boolean getParking() {
		return parking;
	}
	
	public boolean getBathroom() {
		return bathroom;
	}
	
	public boolean getRoomShare() {
		return roomShare;
	}
	
	public int getRentDays() {
		return rentDays;
	}
	
	public int getRent() {
		return rent;
	}
	
	public int getQuietLevel() {
		return quietLevel;
	}
}
