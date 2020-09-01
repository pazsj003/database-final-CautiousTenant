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

import pm4.dal.LikesDao;

import pm4.model.Likes;


@WebServlet("/likedelete")
public class LikeDelete  extends HttpServlet{
	protected LikesDao  likesDao;
		
		@Override
		public void init() throws ServletException {
			likesDao = LikesDao.getInstance();
		}
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        // Provide a title and render the JSP.
	        messages.put("title", "Delete Like");        
	        req.getRequestDispatcher("/LikeDelete.jsp").forward(req, resp);
		}
		
		@Override
	    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve and validate name.
	        String liekeId = req.getParameter("likeid");
	        if (liekeId == null || liekeId.trim().isEmpty()) {
	            messages.put("title", "Invalid liekeId");
	            messages.put("disableSubmit", "true");
	        } else {
	        	// Delete the Reviews.
	        	int LiekeId = Integer.parseInt(liekeId);
	        	
		        try {
		        	
		        	Likes like = likesDao.getLikesById(LiekeId);
		        	like = likesDao.delete(like);
		        	// Update the message.
			        if (like == null) {
			            messages.put("title", "Successfully deleted " + LiekeId);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete " + LiekeId);
			        	messages.put("disableSubmit", "false");
			        }
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        } 
	        req.getRequestDispatcher("/LikeDelete.jsp").forward(req, resp);
	    }
}
