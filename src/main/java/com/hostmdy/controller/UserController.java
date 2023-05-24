package com.hostmdy.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Music;
import com.hostmdy.model.User;
import com.hostmdy.model.UserDAO;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/musicStore")
	private DataSource dataSource;
	
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode == null) {
			mode = "PAGE";
		}
		
		switch (mode) {
		case "PAGE":
			loadPage(request, response);
			break;
		case "LIST":
			showUser(request, response);
			break;
		case "CREATE":
			createUser(request,response);
			break;
		case "DELETE":
			deleteUser(request, response);
			break;
		case "LOAD":
			loadUser(request, response);
			break;
		case "EDIT":
			updateUser(request, response);
			break;
		default:
			loadPage(request,response);
			break;
		}
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Boolean rawRole = Boolean.parseBoolean(request.getParameter("role"));
		
		String role = rawRole?"admin":"user";
		
		User user = new User(username, email, password, role);
		
		int rowUpdated = userDAO.createUser(user);
		
		if(rowUpdated > 0)
			loadPage(request, response);
		else {
			out.print("fail to create user");
		}
		
	}
	private void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList = userDAO.getAllUser();

		request.setAttribute("userList", userList);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("user.jsp");
		dispatch.forward(request, response);
		
	}
	
	
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Integer id = Integer.parseInt(request.getParameter("id"));

		int rowUpdated = userDAO.deleteUser(id);

		if (rowUpdated > 0) {
			showUser(request, response);
		} else {
			request.setAttribute("errorTitle", "User Delete Fail");
			request.setAttribute("errorMessage", "Please Check your data and delete again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}
	

	protected void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String role=request.getParameter("role");
		User user1=new User(role);

		int rowUpdated = userDAO.updateUser(user1);

		if (rowUpdated > 0) {
			showUser(request, response);
		} else {
			request.setAttribute("errorTitle", "Music Update Fail");
			request.setAttribute("errorMessage", "Please Check your data and update again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}
	protected void loadUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		User user1 = userDAO.getUserByid(id);
		request.setAttribute("user", user1);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("user_update.jsp");
		dispatch.forward(request, response);
	}

	private void loadPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
