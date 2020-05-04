package ua.nure.chernev.FinalTask.db.entity;


/**
 * Diagnosis entity
 * 
 */
public class Diagnosis extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3004219341722000214L;

	private String comment;
	
	private int cardId;
	
	private int statusId;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Diagnosis [comment=" + comment + ", cardId=" + cardId + ", statusId=" + statusId + "]";
	}
	
}
