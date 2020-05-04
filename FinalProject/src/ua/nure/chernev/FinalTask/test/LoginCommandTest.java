package ua.nure.chernev.FinalTask.test;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;
import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.UserDao;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.web.command.LoginCommand;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class LoginCommandTest extends TestCase {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;

	@Mock
	RequestDispatcher rd;

	@Before
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		 HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		  HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		  HttpSession session = Mockito.mock(HttpSession.class);
		  RequestDispatcher rd= Mockito.mock(RequestDispatcher.class);
		  
		  when(request.getParameter("login")).thenReturn("admin");
		  when(request.getParameter("password")).thenReturn("admin");
		  when(request.getSession()).thenReturn(session);
		  when(request.getRequestDispatcher("/Controller")).thenReturn(rd);
		  

		  StringWriter sw = new StringWriter();
		  PrintWriter pw = new PrintWriter(sw);
		  
		  when(response.getWriter()).thenReturn(pw);
		  
		  new LoginCommand().execute(request, response);
		  
		  User user = new UserDao().findUserByLogin("admin");
		  
		  verify(session).setAttribute("user", user);
		  
		  verify(request).setAttribute("redirect", Path.REDIRECT_DOCTORS_LIST);
		  
		  String result = sw.getBuffer().toString().trim();

		  System.out.println("Result: "+result);
		  
		  assertEquals("Login successfull...", result);
	}
}
