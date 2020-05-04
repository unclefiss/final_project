package ua.nure.chernev.FinalTask.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.db.entity.Status;

public class StatusNameTag extends TagSupport {

	private List<Status> status;

	private String lang;

	private int statusId;

	private static final Logger LOG = Logger.getLogger(StatusNameTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -4306475923395020298L;

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();// returns the instance of JspWriter
		try {
			for (Status st : status) {
				if (st.getId() == statusId) {
					if (lang.equals("en")) {
						out.print(st.getName());
					} else {
						out.print(st.getNameRu());
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return SKIP_BODY;// will not evaluate the body content of the tag
	}
}