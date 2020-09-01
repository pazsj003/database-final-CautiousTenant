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


import pm4.dal.ReviewsDao;
import pm4.model.Reviews;


@WebServlet("/reviewdelete")
public class ReviewDelete extends HttpServlet{
   protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = reviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Review");        
        req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Reviews.
        	int reviewID = Integer.parseInt(reviewId);
        	
	        try {
	        	
	        	Reviews review = reviewsDao.getReviewById(reviewID);
	        	review = reviewsDao.delete(review);
	        	// Update the message.
		        if (review == null) {
		            messages.put("title", "Successfully deleted " + reviewId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + reviewId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
    }
}
