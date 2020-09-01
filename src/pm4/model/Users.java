package pm4.model;

public class Users {
	protected String userName;
	protected String passWord;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phone;
	protected int age;
	protected String street1;
	protected String street2;
	protected String city;
	protected String state;
	protected int zip;
	
	public Users(String userName, String passWord, String firstName, String lastName, String email, String phone, int age,
			String street1, String street2, String city, String state, int zip) {
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public Users(String userName, String firstName, String lastName) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Users(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getStreet1() {
		return street1;
	}
	
	public String getStreet2() {
		return street2;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public int getZip() {
		return zip;
	}
}
