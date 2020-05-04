package ua.nure.chernev.FinalTask.db.entity;

/**
 * Patient entity
 */
public class Patient extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6570970515024863949L;

	private String birthday;
	
	private String dateRegistration;
	
	private String dateDischarge;
	
	private String phonenumber;
	
	private int cardId;
	
	private int userId;
	
	private int statusId;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(String dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public String getDateDischarge() {
		return dateDischarge;
	}

	public void setDateDischarge(String dateDischarge) {
		this.dateDischarge = dateDischarge;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getCardId() {
		return cardId;
	}
	
	public void setCardId(int card_id) {
		this.cardId = card_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}
	
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
	@Override
	public String toString() {
		return "Patient [birthday=" + birthday + ", phonenumber=" + phonenumber + ", card_id=" + cardId + ", user_id="
				+ userId + "]";
	}

	

	
}
