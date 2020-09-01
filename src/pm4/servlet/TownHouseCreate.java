package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.HousesDao;
import pm4.dal.OwnersDao;
import pm4.dal.TownHouseDao;
import pm4.model.Houses;
import pm4.model.Owners;
import pm4.model.TownHouse;


@WebServlet("/townhousecreate")
public class TownHouseCreate extends HttpServlet {
	protected TownHouseDao townHousesDao;
	
	@Override
	public void init() throws ServletException {
		townHousesDao = TownHouseDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/TownHouseCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the BlogUser.
        	String street1 = req.getParameter("street1");
          	String street2 = req.getParameter("street2");
          	String city = req.getParameter("city");
          	String state = req.getParameter("state");
        	int zip = Integer.valueOf(req.getParameter("zip"));
        	boolean availability = Boolean.valueOf(req.getParameter("availability"));
        	String username = req.getParameter("username");
        	OwnersDao ownersDao = OwnersDao.getInstance();
        	Owners owner;
			try {
				owner = ownersDao.getOwnerFromUserName(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new IOException(e1);
			}
        	String description = req.getParameter("description");
        	int flooring = Integer.valueOf(req.getParameter("flooring"));
        	int bedrooms = Integer.valueOf(req.getParameter("bedrooms"));
        	int bathrooms = Integer.valueOf(req.getParameter("bathrooms"));
        	int deposit = Integer.valueOf(req.getParameter("deposit"));
        	float rating = Float.valueOf(req.getParameter("rating"));
        	boolean pets = Boolean.valueOf(req.getParameter("pets"));
        	boolean parking = Boolean.valueOf(req.getParameter("parking"));
        	boolean laundry = Boolean.valueOf(req.getParameter("laundry"));
        	int yearOfBuild = Integer.valueOf(req.getParameter("yearOfBuild"));
        	int rent = Integer.valueOf(req.getParameter("rent"));
        	int houseValue = Integer.valueOf(req.getParameter("houseValue"));
        	boolean furnished = Boolean.valueOf(req.getParameter("furnished"));
        	// dob must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String availabledate = req.getParameter("availabledate");
        	Date ad = new Date();
        	try {
        		ad = dateFormat.parse(availabledate);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	TownHouse house= new TownHouse(street1, street2, city, state, zip, availability, ad, owner, description, flooring, bedrooms, bathrooms, deposit, rating, pets, parking, laundry, yearOfBuild, rent,houseValue,furnished);
	        	house = townHousesDao.create(house);
	        	messages.put("success", "Successfully created " + house.getHouseId());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TownHouseCreate.jsp").forward(req, resp);
    }

}
