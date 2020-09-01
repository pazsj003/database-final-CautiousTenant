package pm4.servlet;



import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.OwnersDao;
import pm4.model.Owners;

@WebServlet("/ownercreate")
public class OwnerCreate extends HttpServlet {
protected OwnersDao ownersDao;
	
	@Override
	public void init() throws ServletException {
		ownersDao = OwnersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/OwnerCreate.jsp").forward(req, resp);
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
            messages.put("success", "Invalid OwnerName");
        } else {
        	// Create the Owner.
        	String password = req.getParameter("password");
        	String firstName = req.getParameter("firstname");
        	String lastName = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String phone = req.getParameter("phone");
        	int age = Integer.parseInt(req.getParameter("age"));
        	String street1 = req.getParameter("street1");
        	String street2 = req.getParameter("street2");
        	String city = req.getParameter("city");
        	String state = req.getParameter("state");
        	int zip = Integer.parseInt(req.getParameter("zip"));
        	int houseOwned = Integer.parseInt(req.getParameter("houseOwned"));

	        try {
	        	Owners owner = new Owners(userName, password, firstName, lastName, email, phone, age, street1, street2,
	        			city, state, zip, houseOwned);
	        	owner = ownersDao.create(owner);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/OwnerCreate.jsp").forward(req, resp);
    }
}
