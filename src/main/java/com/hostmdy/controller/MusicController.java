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
import java.util.Optional;

import javax.sql.DataSource;

import org.eclipse.tags.shaded.org.apache.xalan.transformer.MsgMgr;

import com.hostmdy.model.Music;
import com.hostmdy.model.MusicDAO;

import com.hostmdy.model.User;

/**
 * Servlet implementation class MusicController
 */
public class MusicController extends HttpServlet {
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
	public MusicController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			String mode = request.getParameter("mode");

			if (mode == null) {
				mode = "LIST";
			}

			switch (mode) {
			case "LIST":
				showMusic(request, response);
				break;
			case "CREATE":
				createMusic(request, response);
				break;
			case "LOAD":
				loadMusic(request, response);
				break;
			case "UPDATE":
				updateMusic(request, response);
				break;
			case "DELETE":
				loadBin(request, response);
				break;
			case "DLOAD":
				load(request, response);
				break;
			case "REMOVE":
				deleteDownloadMusic(request, response);
				break;
			case "DPAGE":
				showDonwloadMusic(request, response);
				break;
			case "BPAGE":
				showBinMusic(request, response);
				break;
			case "RECOVER":
				loadRe(request, response);
				break;
			case "BIN":
				deleteBinMusic(request, response);
				break;
			case "PAGE":
				loadPage(request, response);
				break;
			default:
				showMusic(request, response);
				break;
			}
		} else {
			response.sendRedirect("login");
		}
	}

	protected void showMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Music> musicList = musicDAO.getAllMusic();

		request.setAttribute("musicList", musicList);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");

		dispatch.forward(request, response);
	}

	protected void createMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Integer id=Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String artist = request.getParameter("artist");
		String time = request.getParameter("time");
		String genre = request.getParameter("genre");
		String rawDate = request.getParameter("date");
		LocalDate releasedate = LocalDate.parse(rawDate);

		Music music = new Music(id,name, artist, time, genre, releasedate);

		int rowUpdated = musicDAO.createMusic(music);

		if (rowUpdated > 0) {
			showMusic(request, response);
		} else {
			request.setAttribute("errorTitle", "Music Creation Fail");
			request.setAttribute("errorMessage", "Please Check your data and create again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void updateMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Integer id=Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String artist = request.getParameter("artist");
		String time = request.getParameter("time");
		String genre = request.getParameter("genre");
		String rawDate = request.getParameter("date");
		LocalDate releasedate = LocalDate.parse(rawDate);

		Music music = new Music(id,name, artist, time, genre, releasedate);

		int rowUpdated = musicDAO.updateMusic(music);

		if (rowUpdated > 0) {
			showMusic(request, response);
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
	
	protected void load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Optional<Music> musicOp = Optional.of(musicDAO.getMusicById(id));
		Music music = musicOp.get();
		Optional<Music> addDownload =musicDAO.adddownMusic(music);
		showMusic(request, response);
		request.setAttribute("music", music);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("fav-page.jsp");
		dispatch.forward(request, response);
	}
	protected void loadBin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Optional<Music> musicOp = Optional.of(musicDAO.getMusicById(id));
		Music music = musicOp.get();
		Optional<Music> addBin =musicDAO.addBinMusic(music);
		showMusic(request, response); 
		request.setAttribute("music", music);
		musicDAO.deleteMusic(id);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("recyclebin_page.jsp");
		dispatch.forward(request, response);
	}
	protected void loadRe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Optional<Music> musicOp = Optional.of(musicDAO.getReMusicById(id));
		Music music = musicOp.get();
		Optional<Music> addRe =musicDAO.addReMusic(music);
		showBinMusic(request, response);
		request.setAttribute("music", music);
		musicDAO.deleteBinMusic(id);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
		dispatch.forward(request, response);
	}


	protected void loadMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Music music = musicDAO.getMusicById(id);
		request.setAttribute("music", music);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("music-update.jsp");
		dispatch.forward(request, response);
	}


	protected void deleteDownloadMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Integer id = Integer.parseInt(request.getParameter("id"));

		int rowUpdated = musicDAO.deleteDownloadMusic(id);

		if (rowUpdated > 0) {
			showDonwloadMusic(request, response);
		} else {
			request.setAttribute("errorTitle", "Music Delete Fail");
			request.setAttribute("errorMessage", "Please Check your data and delete again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void showDonwloadMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Music> downloadList = musicDAO.getDownloadMusic();

		request.setAttribute("downloadList", downloadList);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("fav-page.jsp");

		dispatch.forward(request, response);
	}

	protected void showBinMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Music> binList = musicDAO.getBinMusic();

		request.setAttribute("binList", binList);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("recyclebin_page.jsp");

		dispatch.forward(request, response);
	}
	

	protected void deleteBinMusic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Integer id = Integer.parseInt(request.getParameter("id"));

		int rowUpdated = musicDAO.deleteBinMusic(id);

		if (rowUpdated > 0) {
			showBinMusic(request, response);
		} else {
			request.setAttribute("errorTitle", "Music Delete Fail");
			request.setAttribute("errorMessage", "Please Check your data and delete again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}
	protected void loadPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("music-register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
