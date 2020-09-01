package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.TownHouseDao;
import pm4.model.TownHouse;


@WebServlet("/townhouseupdate")
public class TownHouseUpdate extends HttpServlet {
	
	protected TownHouseDao townHouseDao;
	
	@Override
	public void init() throws ServletException {
		townHouseDao = TownHouseDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String houseId = req.getParameter("houseid");
        if (houseId == null || houseId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HouseId.");
        } else {
        	try {
        		int hid = Integer.valueOf(houseId);
        		TownHouse house = townHouseDao.getTownHouseFromHouseId(hid);

        		if(house == null) {
        			messages.put("success", "HouseId does not exist.");
        		}
        		req.setAttribute("townHouse", house);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TownHouseUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String houseId = req.getParameter("houseid");
        if (houseId == null || houseId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HouseId.");
        } else {
        	try {
        		int hid = Integer.valueOf(houseId);
        		TownHouse house = townHouseDao.getTownHouseFromHouseId(hid);
        		if(house == null) {
        			messages.put("success", "HouseId does not exist. No update to perform.");
        		} else {
        			String newDescription = req.getParameter("description");
        			if (newDescription == null || newDescription.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	house = townHouseDao.updateDescription(house, newDescription);
        	        	messages.put("success", "Successfully updated " + house.getHouseId());
        	        }
        		}
        		req.setAttribute("townHouse", house);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TownHouseUpdate.jsp").forward(req, resp);
    }
}

