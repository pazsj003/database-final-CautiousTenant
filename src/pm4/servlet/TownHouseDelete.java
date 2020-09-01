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



@WebServlet("/townhousedelete")
public class TownHouseDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete TownHouse");        
        req.getRequestDispatcher("/TownHouseDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String houseId = req.getParameter("houseid");
        if (houseId == null || houseId.trim().isEmpty()) {
            messages.put("title", "Invalid HouseId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	int hid = Integer.valueOf(houseId);
        	
	        TownHouse townHouse = new TownHouse(hid);
	        try {
	        	townHouse = townHouseDao.delete(townHouse);
	        	// Update the message.
		        if (townHouse == null) {
		            messages.put("title", "Successfully deleted " + hid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + hid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TownHouseDelete.jsp").forward(req, resp);
    }
}

