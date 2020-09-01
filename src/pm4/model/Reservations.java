package pm4.model;

import java.util.Date;


public class Reservations {
	protected int ReservationId;
	protected Date Start;
	protected Date End;
	protected int  Size;
	protected Houses Houses;
	protected Tenants Tenants;
	public Reservations(int reservationId, Date start, Date end, int size, Houses houses,
			Tenants tenants) {
		
		ReservationId = reservationId;
		Start = start;
		End = end;
		Size = size;
		Houses = houses;
		Tenants = tenants;
	}
	
	
	public Reservations( Date start, Date end, int size, Houses houses,
			Tenants tenants) {
		
		
		Start = start;
		End = end;
		Size = size;
		Houses = houses;
		Tenants = tenants;
	}
	
	public Reservations(int reservationId) {
		
		ReservationId = reservationId;
	
	}


	public int getReservationId() {
		return ReservationId;
	}


	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}


	public Date getStart() {
		return Start;
	}


	public void setStart(Date start) {
		Start = start;
	}


	public Date getEnd() {
		return End;
	}


	public void setEnd(Date end) {
		End = end;
	}


	public int getSize() {
		return Size;
	}


	public void setSize(int size) {
		Size = size;
	}


	public Houses getHouses() {
		return Houses;
	}


	public void setHouses(Houses houses) {
		Houses = houses;
	}


	public Tenants getTenants() {
		return Tenants;
	}


	public void setTenants(Tenants tenants) {
		Tenants = tenants;
	}
	
	
}
