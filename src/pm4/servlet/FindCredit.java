package pm4.servlet;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.CreditDao;
import pm4.model.Credit;




@WebServlet("/findcredit")
public class FindCredit extends HttpServlet {
	
	protected CreditDao creditDao;
	
	@Override
	public void init() throws ServletException {
		creditDao = CreditDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List <Credit> credits = new ArrayList<>();
        
        // Retrieve and validate name.
        // username is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		credits = creditDao.getCreditByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousFirstName", userName);
        }
        req.setAttribute("credits", credits);
        
        req.getRequestDispatcher("/FindCredit.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List <Credit> credits = new ArrayList<>();
        
        // Retrieve and validate name.
        // username is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		credits = creditDao.getCreditByUserName(userName);
        		System.out.println(credits);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        }
        req.setAttribute("credits", credits);
        
        req.getRequestDispatcher("/FindCredit.jsp").forward(req, resp);
    }
}
