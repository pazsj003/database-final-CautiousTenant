package pm4.tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import pm4.dal.CreditDao;
import pm4.dal.ApartmentDao;
import pm4.dal.HousesDao;
import pm4.dal.LikesDao;
import pm4.dal.OwnersDao;
import pm4.dal.RecommendationsDao;
import pm4.dal.ReservationsDao;
import pm4.dal.ReviewsDao;
import pm4.dal.SingleFamilyHouseDao;
import pm4.dal.TenantsDao;
import pm4.dal.TownHouseDao;
import pm4.dal.UsersDao;
import pm4.model.Apartment;
import pm4.model.Credit;
import pm4.model.Houses;
import pm4.model.Likes;
import pm4.model.Owners;
import pm4.model.Recommendations;
import pm4.model.Reservations;
import pm4.model.Reviews;
import pm4.model.SingleFamilyHouse;
import pm4.model.Tenants;
import pm4.model.TownHouse;
import pm4.model.Users;



public class Inserter {
	public static void main(String[] args) throws SQLException {
		
		
		
		
		
		
		UsersDao usersDao = UsersDao.getInstance();
		CreditDao creditDao = CreditDao.getInstance();
		TenantsDao tenantsDao = TenantsDao.getInstance();
		OwnersDao ownersDao = OwnersDao.getInstance();
		
		Users user = new Users("JS","123","Jimmy","Smith","js@gmail.com","123-888-8888",25,"233 Fifth Avenue","Apt 101",
				"San Jose","CA",95000);
		user = usersDao.create(user);
		Users user2 = new Users("JS2","123","Jimmy","Smith","js@gmail.com","123-888-8888",25,"233 Fifth Avenue","Apt 101",
				"San Jose","CA",95000);
		user2 = usersDao.create(user2);
		Credit credit = new Credit(10000000L,12,700,user);
		credit = creditDao.create(credit);;
		
		Tenants tenant = new Tenants("tenant1","123","Jimmy","Smith","js@gmail.com","123-888-8888",25,"233 Fifth Avenue","Apt 101",
				"San Jose","CA",95000,true,365,2000,true,4,true,false);
		tenant = tenantsDao.create(tenant);
		Owners owner = new Owners("owner1","123","Jimmy","Smith","js@gmail.com","123-888-8888",25,"233 Fifth Avenue","Apt 101",
				"San Jose","CA",95000,3);
		owner = ownersDao.create(owner);
		
		Users u1 = usersDao.getUserByUserName("JS");
		System.out.format("Reading user: u:%s f:%s l:%s \n",
				u1.getUserName(), u1.getFirstName(), u1.getLastName());
		Tenants t1 = tenantsDao.getTenantFromUserName("tenant1");
		System.out.format("Reading tenant: u:%s f:%s l:%s \n",
				t1.getUserName(), t1.getFirstName(), t1.getLastName());
		Owners o1 = ownersDao.getOwnerFromOwnerId(1);
		System.out.format("Reading owner: u:%s \n",
				o1.getUserName());
		List<Users> uList = usersDao.getUsersFromFirstName("Jimmy");
		for(Users u : uList) {
			System.out.format("Looping users: u:%s \n",
				u.getUserName());
		}
		List<Tenants> tList = tenantsDao.getTenantsFromFirstName("Jimmy");
		for(Tenants t : tList) {
			System.out.format("Looping tenants: u:%s \n",
				t.getUserName());
		}
		Credit c1 = creditDao.getCreditBySsn(10000000L);
		System.out.format("Reading credit: s:%s h:%s \n",
				c1.getSsn(), c1.getCreditHistoryMonth());
//		Credit c2 = creditDao.getCreditByUserName("JS");
//		System.out.format("Reading credit: s:%s h:%s \n",
//				c2.getSsn(), c2.getCreditHistoryMonth());
		
		
		
		HousesDao housedao = HousesDao.getInstance();
		SingleFamilyHouseDao sfhDao = SingleFamilyHouseDao.getInstance();
		TownHouseDao thDao = TownHouseDao.getInstance();
		ApartmentDao aDao = ApartmentDao.getInstance();
		
		//create update get delete
		//create update get delete
		Houses h1 = new Houses("as", "as", "as", "as", 21, false, new Date(), o1, "as", 23, 32, 31, 123, 31, false, false, true, 2, 1);
		h1 = housedao.create(h1);
//		
//		Houses h1 = housedao.getHouseFromHouseId(h.getHouseId());
//		System.out.format("Reading house: b:%d b:%d d:%d l:%s c:%s\n",
//			h1.getBathrooms(),h1.getBathrooms(),h1.getDeposit(),h1.getCity(),h1.getDescription());
//
//		housedao.delete(h1);
//		h1 = housedao.getHouseFromHouseId(h1.getHouseId());
//		System.out.println("User deleted:"+(h1==null));
		Houses h2 = new Houses("as33", "as22", "as", "as", 21, false, new Date(), o1, "as", 23, 32, 31, 123, 31, false, false, true, 2, 2);
		h2 = housedao.create(h2);
		Houses h3 = new Houses("as323", "as", "as", "as", 21, false, new Date(), o1, "as", 23, 32, 31, 123, 31, false, false, true, 2, 3);
		h3 = housedao.create(h3);

		//create update get delete
		SingleFamilyHouse sfh = new SingleFamilyHouse("as33dd", "as22", "as", "as", 21, false, new Date(), o1, "as", 23, 32, 31, 123, 31, false, false, true, 2, 10, 30, 0, false);
		sfh = sfhDao.create(sfh);
		
		System.out.println(sfh.getHouseId());
		
		sfh = sfhDao.getSingleFamilyHouseFromHouseId(sfh.getHouseId());
		System.out.format("Reading house: b:%d b:%d d:%d l:%s c:%s\n",
			sfh.getBathrooms(),sfh.getBathrooms(),sfh.getDeposit(),sfh.getCity(),sfh.getDescription());

		sfhDao.delete(sfh);
		sfh = sfhDao.getSingleFamilyHouseFromHouseId(sfh.getHouseId());
		System.out.println("User deleted:"+(sfh==null));		
		//
		
		TownHouse th = new TownHouse("as33dd", "as22", "as", "as", 21, false, new Date(), o1, "as", 23, 32, 31, 123, 31, false, false, true, 2, 10, 0, false);
		th = thDao.create(th);
		System.out.println(th.getHouseId());
		
		th = thDao.getTownHouseFromHouseId(th.getHouseId());
		System.out.format("Reading house: b:%d b:%d d:%d l:%s c:%s\n",
			th.getBathrooms(),th.getBathrooms(),th.getDeposit(),th.getCity(),th.getDescription());

		
//		thDao.delete(th);		
//		th = thDao.getTownHouseFromHouseId(th.getHouseId());
//		System.out.println("User deleted:"+(th==null));	
		//
		
//		Apartment a = new Apartment("as33dd", "as22", "as", "as", 21, false, new Date(), o1, "as", 23, 32, 31, 123, 31, false, false, true, 2, 10, 30, 0, "da");
//		a = aDao.create(a);
//		
//		System.out.println(a.getHouseId());
//		
//		a = aDao.getApartmentFromHouseId(a.getHouseId());
//		System.out.format("Reading house: b:%d b:%d d:%d l:%s c:%s\n",
//			a.getBathrooms(),a.getBathrooms(),a.getDeposit(),a.getCity(),a.getDescription());

//		aDao.delete(a);		
//		a= aDao.getApartmentFromHouseId(a.getHouseId());
//		System.out.println("User deleted:"+(a==null));	
		
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		ReservationsDao reservationsDao= ReservationsDao.getInstance();
		LikesDao likesDao = LikesDao.getInstance();
		ReviewsDao reviewDao = ReviewsDao.getInstance();
		
		Likes like1 = new Likes(h1,user,true);
		Likes like2 = new Likes(h2,user2,false);
		likesDao.create(like1);
		likesDao.create(like2);
		
		likesDao.getLikesById(like1.getLikeId());
		likesDao.getLikesByUserName(user.getUserName());
		likesDao.getLikesByHouseId(h1.getHouseId());
		
		
			
		Reviews review1 = new Reviews(new Date(),"great",(float) 4.3,h1,user);
		Reviews review2 = new Reviews(new Date(),"bad",(float) 3.0,h2,user2);
		reviewDao.create(review1);
		reviewDao.create(review2);
		
		
		Recommendations recommendation1 = new Recommendations(h1,user);
		Recommendations recommendation2 = new Recommendations(h2,user2);
		recommendationsDao.create(recommendation1);
		recommendationsDao.create(recommendation2);
		
		
	
		
		Reservations reservation1 = new Reservations(new Date(),new Date(),3,h1,tenant);
		reservationsDao.create(reservation1);
		

		List<Recommendations> recommendationsList = recommendationsDao.getRecommendationsByUserName(user.getUserName());
		for(Recommendations r : recommendationsList) {
			System.out.format("Looping Recommendations for someone: RecommendationId:%s "
					+ "UserName:%s HouseId:%s  \n",		
					r.getRecommendationId(),r.getUsers().getUserName(),r.getHouse().getHouseId()
				);

		}
		
		List<Reviews> reviewsList = reviewDao.getReviewsByUserName(user.getUserName());
		for(Reviews r : reviewsList) {
			System.out.format("Looping Reviews for someone: ReviewId:%s "
					+ "Created:%s Content:%s Rating:%s HouseId:%s  UserName:%s  \n",		
					r.getReviewId(),r.getCreated() ,r.getContent(),r.getRating(), r.getHouse().getHouseId(),  r.getUsers().getUserName()
				);

		}
		
		List<Reservations> reservationsList = reservationsDao.getReservationsByUserName(tenant.getUserName());
		for(Reservations r : reservationsList) {
			System.out.format("Looping Reservations for someone: ReservationId:%s "
					+ "Start:%s End:%s Size:%s HouseId:%s  UserName:%s   \n",		
					r.getReservationId(),r.getStart() ,r.getEnd(),r.getSize(),   r.getHouses().getHouseId(),  r.getTenants().getUserName()
				);

		}
		
		List<Likes> likesList = likesDao.getLikesByUserName(user.getUserName());
		for(Likes r : likesList) {
			System.out.format("Looping Reservations for someone: LikeId:%s "
					+ "UserName:%s HouseId:%s  Liked  \n",		
					r.getLikeId(),r.getUsers().getUserName(),r.getHouse().getHouseId(), r.isLiked()
				);

		}
		
		
		
//		reviewDao.delete(review1);
//		Reviews delReviews1 = reviewDao.getReviewById(review1.getReviewId());
//		System.out.format("delete Reviews test:%s \n", delReviews1==null);
//		
//		recommendationsDao.delete(recommendation1);
//		Recommendations delrecommendations1 = recommendationsDao.getRecommendationById(recommendation1.getRecommendationId());
//		System.out.format("delete Recommendations test:%s \n", delrecommendations1==null);
//		
//		reservationsDao.delete(reservation1);
//		Reservations delreservation1 = reservationsDao.getReservationById(reservation1.getReservationId());
//		System.out.format("delete Reservations test:%s \n", delreservation1==null);
//		
//		likesDao.delete(like1);
//		Likes delLike1 = likesDao.getLikesById(like1.getLikeId());
//		System.out.format("delete Likes test:%s \n", delLike1==null);
		
		
	}
}
