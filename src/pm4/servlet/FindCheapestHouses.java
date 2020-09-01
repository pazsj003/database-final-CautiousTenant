package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.HousesDao;
import pm4.model.Houses;



@WebServlet("/findcheapesthouses")
public class FindCheapestHouses extends HttpServlet {
	
	protected HousesDao housesDao;
	
	@Override
	public void init() throws ServletException {
		housesDao = HousesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Houses> houses = new ArrayList<Houses>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid city.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		houses = housesDao.getCheapestHousesFromCity(city);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + city);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousCity", city);
        }
        req.setAttribute("cheapesthouses", houses);
        
        req.getRequestDispatcher("/FindCheapestHouses.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Houses> houses = new ArrayList<Houses>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid city.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		houses = housesDao.getCheapestHousesFromCity(city);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + city);
        }
        req.setAttribute("cheapesthouses", houses);
        
        req.getRequestDispatcher("/FindCheapestHouses.jsp").forward(req, resp);
    }
}