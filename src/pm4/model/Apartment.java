package pm4.model;

import java.util.Date;

public class Apartment extends Houses{

	int capacity;
	String apartmentName;
	
	public Apartment(int houseId, String street1, String street2, String city, String state,
			int zip, boolean availability,Date availableDate,Owners owner, String description, int flooring, int bedrooms,
			int bathrooms, int deposit, float rating, boolean pets, boolean parking,
			boolean laundry, int yearOfBuild, int rent,int capacity,String apartmentName) {
		
		super(houseId,street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
				bathrooms,deposit,rating, pets,parking,
				laundry, yearOfBuild,rent);
		
		this.capacity=capacity;
		this.apartmentName=apartmentName;
	}
	
	public Apartment(String street1, String street2, String city, String state,
			int zip, boolean availability,Date availableDate,Owners owner, String description, int flooring, int bedrooms,
			int bathrooms, int deposit, float rating, boolean pets, boolean parking,
			boolean laundry, int yearOfBuild, int rent,int capacity,String apartmentName) {
		
		super(street1, street2,city,state,zip,availability,availableDate,owner,description,flooring,bedrooms,
				bathrooms,deposit,rating, pets,parking,
				laundry, yearOfBuild,rent);
		
		this.capacity=capacity;
		this.apartmentName=apartmentName;
	}
	
	public Apartment(int houseId) {
		super(houseId);
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	
	public String getApartmentName() {
		return apartmentName;
	}
	
	public void setApartmentName(String apartmentName) {
		this.apartmentName=apartmentName;
	}
}
