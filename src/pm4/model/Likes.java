package pm4.model;

public class Likes {
	protected int LikeId;
	
	protected Houses House;
	protected Users Users;
	protected boolean Liked;
	public Likes(int likeId, Houses house, Users users, boolean liked) {
		
		LikeId = likeId;
		House = house;
		Users = users;
		Liked = liked;
	}
	
public Likes( Houses house, Users users, boolean liked) {
		
		
		House = house;
		Users = users;
		Liked = liked;
	}

public Likes(int likeId) {
	
	
	LikeId = likeId;
 }

public int getLikeId() {
	return LikeId;
}

public void setLikeId(int likeId) {
	LikeId = likeId;
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

public boolean isLiked() {
	return Liked;
}

public void setLiked(boolean liked) {
	Liked = liked;
}


}
