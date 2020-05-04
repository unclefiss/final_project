package ua.nure.chernev.FinalTask.db.entity;

/**
 * Universal entity for all status-like tables in DB
 * 
 * @author K. Chernev
 */
public class Status extends Entity {

	private static final long serialVersionUID = -5153572287532800577L;
	

	private String name;
	
	private String nameRu;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	@Override
	public String toString() {
		return "Status [name=" + name + ", nameRu=" + nameRu + "]";
	}

	

}
