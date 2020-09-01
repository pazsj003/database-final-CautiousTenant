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

import pm4.dal.TenantsDao;
import pm4.model.Tenants;

@WebServlet("/tenantcreate")
public class TenantCreate extends HttpServlet {
protected TenantsDao tenantsDao;
	
	@Override
	public void init() throws ServletException {
		tenantsDao = TenantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/TenantCreate.jsp").forward(req, resp);
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
            messages.put("success", "Invalid TenantName");
        } else {
        	// Create the Tenant.
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
        	boolean pets = Boolean.parseBoolean("pets");
        	int rentdays = Integer.parseInt(req.getParameter("rentdays"));
        	int rent = Integer.parseInt(req.getParameter("rent"));
        	boolean parking = Boolean.parseBoolean("parking");
        	int quietlevel = Integer.parseInt(req.getParameter("quietlevel"));
        	boolean bathroom = Boolean.parseBoolean("bathroom");
        	boolean roomshare = Boolean.parseBoolean("roomshare");
	        try {
	        	Tenants tenant = new Tenants(userName, password, firstName, lastName, email, phone, age, street1, street2,
	        			city, state, zip, pets, rentdays, rent, parking, quietlevel, bathroom, roomshare);
	        	tenant = tenantsDao.create(tenant);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TenantCreate.jsp").forward(req, resp);
    }
}
