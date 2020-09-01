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

import pm4.dal.LikesDao;

import pm4.model.Likes;


@WebServlet("/likeread")
public class LikeRead  extends HttpServlet{

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
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "Likes for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Likes> Likes = new ArrayList<Likes>();
        try {
        	
        	Likes = likesDao.getLikesByUserName(userName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("likes", Likes);
        req.getRequestDispatcher("/LikeRead.jsp").forward(req, resp);
	}
	
}
