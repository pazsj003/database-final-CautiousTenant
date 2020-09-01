package pm4.model;

import java.util.Date;



public class Reviews {
	protected int ReviewId;
	protected Date Created;
	protected String Content;
	protected float Rating;
	protected Houses House;
	protected Users Users;
	public Reviews(int reviewId, Date created, String content, float rating, Houses house,
			Users users) {
		super();
		ReviewId = reviewId;
		Created = created;
		Content = content;
		Rating = rating;
		House = house;
		Users = users;
	}
	
	public Reviews( Date created, String content, float rating, Houses house,
			Users users) {

		Created = created;
		Content = content;
		Rating = rating;
		House = house;
		Users = users;
	}
	
	public Reviews(int reviewId) {

		ReviewId = reviewId;
	}

	public int getReviewId() {
		return ReviewId;
	}

	public void setReviewId(int reviewId) {
		ReviewId = reviewId;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
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
