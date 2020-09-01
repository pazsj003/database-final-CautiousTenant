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

import pm4.dal.RecommendationsDao;

import pm4.model.Recommendations;

@WebServlet("/recommendationread")
public class RecommendationRead extends HttpServlet {

	
protected RecommendationsDao  recommendationsDao;
	
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
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "Recommendations for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Recommendations> Recommendations = new ArrayList<Recommendations>();
        try {
        	
        	Recommendations = recommendationsDao.getRecommendationsByUserName(userName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("recommendations", Recommendations);
        req.getRequestDispatcher("/RecommendationRead.jsp").forward(req, resp);
	}
	
	
}
