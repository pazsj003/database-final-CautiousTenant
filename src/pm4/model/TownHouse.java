package pm4.model;

import java.util.Date;

public class TownHouse extends Houses{
//	  HouseId INT,
//	  HouseValue INT,
//	  Furnished BOOL,
	int houseValue;
	boolean furnished;
	
	public TownHouse(int houseId, String street1, String street2, String city, String state,
			int zip,boolean availability,Date availableDate, Owners owner, String description, int flooring, int bedrooms,
			int bathrooms, int deposit, float rating, boolean pets, boolean parking,
			boolean laundry, int yearOfBuild, int rent,int houseValue, boolean furnished) {
		
		super(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
				bathrooms,deposit,rating, pets,parking,
				laundry, yearOfBuild,rent);
		
		this.houseValue=houseValue;
		this.furnished=furnished;
	}
	
	public TownHouse(String street1, String street2, String city, String state,
			int zip,boolean availability,Date availableDate, Owners owner, String description, int flooring, int bedrooms,
			int bathrooms, int deposit, float rating, boolean pets, boolean parking,
			boolean laundry, int yearOfBuild, int rent,int houseValue, boolean furnished) {
		
		super(street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
				bathrooms,deposit,rating, pets,parking,
				laundry, yearOfBuild,rent);
		
		this.houseValue=houseValue;
		this.furnished=furnished;
	}
	
	public TownHouse(int houseId) {
		super(houseId);
	}
	
	public int getHouseValue() {
		return houseValue;
	}
	
	public void setHouseValue(int houseValue) {
		this.houseValue=houseValue;
	}
	
	public boolean getFurnished() {
		return furnished;
	}
	
	public void setFurnished(boolean furnished) {
		this.furnished=furnished;
	}
}
