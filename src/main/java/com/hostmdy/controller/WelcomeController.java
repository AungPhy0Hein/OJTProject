package com.hostmdy.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Music;
import com.hostmdy.model.MusicDAO;

/**
 * Servlet implementation class WelcomeController
 */
public class WelcomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/musicStore")
	private DataSource dataSource;

	private MusicDAO musicDAO;

	@Override
	public void init() throws ServletException {
		musicDAO = new MusicDAO(dataSource);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Music> musicList = musicDAO.getAllMusic();
		request.setAttribute("musicList", musicList);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
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
