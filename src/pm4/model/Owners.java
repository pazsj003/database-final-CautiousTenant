package pm4.model;

public class Owners extends Users {
	protected int ownerId;
	protected int houseOwned;
	
	public Owners(String userName, String passWord, String firstName, String lastName, String email, String phone, int age,
			String street1, String street2, String city, String state, int zip, int houseOwned) {
		super(userName, passWord, firstName, lastName, email, phone, age, street1, street2, city, state, zip);
		this.houseOwned = houseOwned;
	}
	
//	public Owners(String userName, String firstName, String lastName, int houseOwned) {
//		super(userName, firstName, lastName);
//		this.houseOwned = houseOwned;
//	}
	
	public Owners(String userName, String passWord, String firstName, String lastName, String email, String phone, int age,
			String street1, String street2, String city, String state, int zip, int ownerId, int houseOwned) {
		super(userName, passWord, firstName, lastName, email, phone, age, street1, street2, city, state, zip);
		this.houseOwned = houseOwned;
		this.ownerId = ownerId;
	}
	
	public Owners(String userName) {
		super(userName);
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	public int getHouseOwned() {
		return houseOwned;
	}
}
