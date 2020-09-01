package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import pm4.dal.ReviewsDao;
import pm4.model.Reviews;

@WebServlet("/reviewread")
public class ReviewRead extends HttpServlet { 
	protected ReviewsDao  reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "Reviews for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Reviews> reviews = new ArrayList<Reviews>();
        try {
        	
        	reviews = reviewsDao.getReviewsByUserName(userName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/ReviewRead.jsp").forward(req, resp);
	}
	
	
	
}
