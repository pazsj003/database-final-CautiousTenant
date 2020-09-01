package pm4.model;


public class Credit {
	protected int creditId;
	protected Long ssn;
	protected int creditHistoryMonth;
	protected int creditScore;
	protected Users user;
	
	public Credit(Long ssn, int creditHistoryMonth, int creditScore, Users user) {
		this.ssn = ssn;
		this.creditHistoryMonth = creditHistoryMonth;
		this.creditScore = creditScore;
		this.user = user;
	}
	
	public Credit(int creditId, Long ssn, int creditHistoryMonth, int creditScore, Users user) {
		this.creditId = creditId;
		this.ssn = ssn;
		this.creditHistoryMonth = creditHistoryMonth;
		this.creditScore = creditScore;
		this.user = user;
	}
	
	public int getCreditId() {
		return creditId;
	}
	
	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}
	
	public Long getSsn() {
		return ssn;
	}
	public int getCreditHistoryMonth() {
		return creditHistoryMonth;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public Users getUser() {
		return user;
	}
}
