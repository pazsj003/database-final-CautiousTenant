package pm4.model;

import java.util.Date;

public class Houses {

	protected int houseId,zip,flooring,bedrooms,bathrooms,deposit,rent,yearOfBuild;
	protected String street1,street2,city,state,description;
	protected boolean availability,pets,parking,laundry;
	protected Date availableDate;
	protected float rating;
	protected Owners owner;
	
	public Houses(int houseId, String street1, String street2, String city, String state,
			int zip,boolean availability,Date availableDate, Owners owner, String description, int flooring, int bedrooms,
			int bathrooms, int deposit, float rating, boolean pets, boolean parking,
			boolean laundry, int yearOfBuild, int rent) {
		
		this.houseId=houseId;
		this.street1=street1;
		this.street2=street2;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.owner=owner;
		this.description=description;
		this.flooring=flooring;
		this.bedrooms=bedrooms;
		this.bathrooms=bathrooms;
		this.deposit=deposit;
		this.rating=rating;
		this.pets=pets;
		this.parking=parking;
		this.laundry=laundry;
		this.yearOfBuild=yearOfBuild;
		this.rent=rent;
		this.availability=availability;
		this.availableDate=availableDate;
		
	}
	public Houses(String street1, String street2, String city, String state,
			int zip,boolean availability,Date availableDate, Owners owner, String description, int flooring, int bedrooms,
			int bathrooms, int deposit, float rating, boolean pets, boolean parking,
			boolean laundry, int yearOfBuild, int rent) {
		this.street1=street1;
		this.street2=street2;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.owner=owner;
		this.description=description;
		this.flooring=flooring;
		this.bedrooms=bedrooms;
		this.bathrooms=bathrooms;
		this.deposit=deposit;
		this.rating=rating;
		this.pets=pets;
		this.parking=parking;
		this.laundry=laundry;
		this.yearOfBuild=yearOfBuild;
		this.rent=rent;
		this.availability=availability;
		this.availableDate=availableDate;
	}
	
	public Houses(int houseId) {
		this.houseId=houseId;
	}
	
	public int getHouseId() {
		return houseId;
	}
	
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	
	public int getZip() {
		return zip;
	}
	
	public void setZip(int zip) {
		this.zip=zip;
	}
	
	public int getFlooring() {
		return flooring;
	}
	
	public void setFlooring(int flooring) {
		this.flooring = flooring;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	public int getBathrooms() {
		return bathrooms;
	}
	
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	
	public int getDeposit() {
		return deposit;
	}
	
	public void setDeposit(int deposit) {
		this.deposit=deposit;
	}
	
	public int getRent() {
		return rent;
	}
	
	public void set(int rent) {
		this.rent = rent;
	}
	
	public String getStreet1() {
		return street1;
	}
	
	public void setStreet1(String street1) {
		this.street1=street1;
	}
	
	public String getStreet2() {
		return street2;
	}
	
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state=state;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public boolean getParking() {
		return parking;
	}
	
	public void setParking(boolean parking) {
		this.parking=parking;
	}
	
	public void setLaundry(boolean laundry) {
		this.laundry=laundry;
	}
	
	public boolean getLaundry() {
		return laundry;
	}
	
	public void setAvailability(boolean availability) {
		this.availability=availability;
	}
	
	public boolean getAvailability() {
		return availability;
	}

	public void setPets(boolean pets) {
		this.pets=pets;
	}
	
	public boolean getPets() {
		return pets;
	}
	
	public void setAvailableDate(Date availableDate) {
		this.availableDate=availableDate;
	}
	
	public Date getAvailableDate() {
		return availableDate;
	}
	
	public void setYearOfBuild(int yearOfBuild) {
		this.yearOfBuild = yearOfBuild;
	}
	
	public int getYearOfBuilder() {
		return yearOfBuild;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating) {
		this.rating=rating;
	}
	
	public Owners getOwner() {
		return owner;
	}
	
	public void setOwner(Owners owner) {
		this.owner=owner;
	}
}
