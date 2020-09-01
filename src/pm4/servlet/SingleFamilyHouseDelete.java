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

import pm4.dal.SingleFamilyHouseDao;
import pm4.model.SingleFamilyHouse;



@WebServlet("/singlefamilyhousedelete")
public class SingleFamilyHouseDelete extends HttpServlet {
	
	protected SingleFamilyHouseDao singleFamilyHouseDao;
	
	@Override
	public void init() throws ServletException {
		singleFamilyHouseDao = SingleFamilyHouseDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete SingleFamilyHouse");        
        req.getRequestDispatcher("/SingleFamilyHouseDelete.jsp").forward(req, resp);
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
        	
	        SingleFamilyHouse singleFamilyHouse = new SingleFamilyHouse(hid);
	        try {
	        	singleFamilyHouse = singleFamilyHouseDao.delete(singleFamilyHouse);
	        	// Update the message.
		        if (singleFamilyHouse == null) {
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
        
        req.getRequestDispatcher("/SingleFamilyHouseDelete.jsp").forward(req, resp);
    }
}

