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

import pm4.dal.RecommendationsDao;

import pm4.model.Recommendations;


@WebServlet("/recommendationdelete")
public class RecommendationDelete  extends HttpServlet{
	 protected RecommendationsDao recommendationsDao;
		
		@Override
		public void init() throws ServletException {
			recommendationsDao = RecommendationsDao.getInstance();
		}
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        // Provide a title and render the JSP.
	        messages.put("title", "Delete Recommendation");        
	        req.getRequestDispatcher("/RecommendationDelete.jsp").forward(req, resp);
		}
		
		@Override
	    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve and validate name.
	        String recommendationId = req.getParameter("recommendationid");
	        if (recommendationId == null || recommendationId.trim().isEmpty()) {
	            messages.put("title", "Invalid recommendationid");
	            messages.put("disableSubmit", "true");
	        } else {
	        	// Delete the Reviews.
	        	int RecommendationId = Integer.parseInt(recommendationId);
	        	
		        try {
		        	
		        	Recommendations recommendation = recommendationsDao.getRecommendationById(RecommendationId);
		        	recommendation = recommendationsDao.delete(recommendation);
		        	// Update the message.
			        if (recommendation == null) {
			            messages.put("title", "Successfully deleted " + RecommendationId);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete " + RecommendationId);
			        	messages.put("disableSubmit", "false");
			        }
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        } 
	        req.getRequestDispatcher("/RecommendationDelete.jsp").forward(req, resp);
	    }
}
