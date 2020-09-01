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
import pm4.dal.ReservationsDao;
import pm4.model.Likes;
import pm4.model.Reservations;

@WebServlet("/reservationdelete")
public class ReservationDelete extends HttpServlet{
	  protected ReservationsDao  reservationsDao;
	
	@Override
	public void init() throws ServletException {
		reservationsDao = ReservationsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Reservation");        
        req.getRequestDispatcher("/ReservationDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reservationId = req.getParameter("reservationid");
        if (reservationId == null || reservationId.trim().isEmpty()) {
            messages.put("title", "Invalid reservationId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Reviews.
        	int ReservationId = Integer.parseInt(reservationId);
        	
	        try {
	        	
	        	Reservations reservations = reservationsDao.getReservationById(ReservationId);
	        	reservations = reservationsDao.delete(reservations);
	        	// Update the message.
		        if (reservations == null) {
		            messages.put("title", "Successfully deleted " + ReservationId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + ReservationId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        } 
        req.getRequestDispatcher("/ReservationDelete.jsp").forward(req, resp);
    }
}
