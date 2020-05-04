package ua.nure.chernev.FinalTask.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.db.DAO.StatusDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Status;
import ua.nure.chernev.FinalTask.db.entity.User;

public class ShowRoleTag extends TagSupport {

	private User user;

	private Doctor doctor;

	private String lang;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4306475923395020298L;
	

	private static final Logger LOG = Logger.getLogger(ShowRoleTag.class);

	public void setUser(User user) {
		this.user = user;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}

	public int doStartTag() throws JspException {

		
		JspWriter out = pageContext.getOut();// returns the instance of JspWriter
		try {
			
			List<Status> roles = new StatusDao().findRoles();
			
			List<Status> positions = new StatusDao().findPositions();
			if(doctor == null) {
				out.print("(");
				for(Status r: roles) {
					if(user.getRoleId() == r.getId()) {
						if(lang.equals("en")) {
							out.print(r.getName());
						} else {
							out.print(r.getNameRu());
						}
					}
				}
			} else {
				out.print("(");
				for(Status p: positions) {
					if(doctor.getPositionId() == p.getId()) {
						if(lang.equals("en")) {
							out.print(p.getName());
						} else {
							out.print(p.getNameRu());
						}
					}
				}
			}
			out.print(")");

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return SKIP_BODY;// will not evaluate the body content of the tag
	}
}