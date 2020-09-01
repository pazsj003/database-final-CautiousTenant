package pm4.model;



public class Recommendations {
	protected int RecommendationId;
	protected Houses House;
	protected Users Users;
	public Recommendations(int recommendationId, Houses house, Users users) {
		
		RecommendationId = recommendationId;
		House = house;
		Users = users;
	}
	
public Recommendations(Houses house, Users users) {
		
		House = house;
		Users = users;
	}
	
public Recommendations(int recommendationId) {
	
	    RecommendationId = recommendationId;

}

public int getRecommendationId() {
	return RecommendationId;
}

public void setRecommendationId(int recommendationId) {
	RecommendationId = recommendationId;
}

public Houses getHouse() {
	return House;
}

public void setHouse(Houses house) {
	House = house;
}

public Users getUsers() {
	return Users;
}

public void setUsers(Users users) {
	Users = users;
}

	
	
}
