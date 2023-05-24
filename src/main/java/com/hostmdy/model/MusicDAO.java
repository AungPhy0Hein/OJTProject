package com.hostmdy.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class MusicDAO {
	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public MusicDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int createMusic(Music music) {
		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();

			pStmt = connection.prepareStatement("INSERT INTO `music` "
					+ "(`id`,`name`, `artist`, `time`, `genre`, `releasedate`) " + "VALUES (?,?,?,?,?,?);");
			pStmt.setInt(1, music.getId());
			pStmt.setString(2, music.getName());
			pStmt.setString(3, music.getArtist());
			pStmt.setString(4, music.getTime());
			pStmt.setString(5, music.getGenre());
			Date date = Date.valueOf(music.getReleasedate());
			pStmt.setDate(6, date);

			rowUpdated = pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowUpdated;

	}

	public List<Music> getAllMusic() {
		List<Music> musicList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from music;");

			while (rs.next()) {
				musicList.add(
						new Music(rs.getInt("id"), rs.getString("name"), rs.getString("artist"), rs.getString("time"),
								rs.getString("genre"), LocalDate.parse(rs.getDate("releasedate").toString())));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return musicList;
	}

	public Music getMusicById(Integer id) {
		Music music = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from music where id='" + id + "';");

			while (rs.next()) {
				music = new Music(rs.getInt("id"), rs.getString("name"), rs.getString("artist"), rs.getString("time"),
						rs.getString("genre"), LocalDate.parse(rs.getDate("releasedate").toString()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return music;
	}

	public int updateMusic(Music music) {
		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();

			pStmt = connection.prepareStatement("UPDATE `music` SET `name` =?," + " `artist` =?,"
					+ "`time` =?, `genre` =?, `releasedate` =? WHERE (`id` =?);");
			pStmt.setString(1, music.getName());
			pStmt.setString(2, music.getArtist());
			pStmt.setString(3, music.getTime());
			pStmt.setString(4, music.getGenre());
			Date date = Date.valueOf(music.getReleasedate());
			pStmt.setDate(5, date);
			pStmt.setInt(6, music.getId());
			rowUpdated = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowUpdated;

	}

	public int deleteMusic(Integer id) {
		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from music where id=?;");
			pStmt.setInt(1, id);
			rowUpdated = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowUpdated;
	}

	public List<Music> getDownloadMusic() {
		List<Music> downloadList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from download;");

			while (rs.next()) {
				downloadList.add(
						new Music(rs.getInt("id"), rs.getString("name"), rs.getString("artist"), rs.getString("time"),
								rs.getString("genre"), LocalDate.parse(rs.getDate("releasedate").toString())));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return downloadList;
	}
	
	public Optional<Music> adddownMusic(Music music) {
		Optional<Music> musicOp = Optional.empty();

		try {
			connection = dataSource.getConnection();

			pStmt = connection.prepareStatement("INSERT INTO `download` (`id`, `name`, `artist`,"
					+ " `time`, `genre`, `releasedate`) VALUES (?, ?, ?, ?, ?, ?);"
					);
			pStmt.setInt(1, music.getId());
			pStmt.setString(2, music.getName());
			pStmt.setString(3, music.getArtist());
			pStmt.setString(4, music.getTime());
			pStmt.setString(5, music.getGenre());
			Date date = Date.valueOf(music.getReleasedate());
			pStmt.setDate(6, date);
		

			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return musicOp;
	}


	
	
	public int deleteDownloadMusic(Integer id) {
		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from download where id=?;");
			pStmt.setInt(1, id);
			rowUpdated = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowUpdated;
	}
	


	public int deleteBinMusic(Integer id) {
		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from recent where id=?;");
			pStmt.setInt(1, id);
			rowUpdated = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowUpdated;
	}

	public List<Music> getBinMusic() {
		List<Music> binList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from recent;");

			while (rs.next()) {
				binList.add(
						new Music(rs.getInt("id"), rs.getString("name"), rs.getString("artist"), rs.getString("time"),
								rs.getString("genre"), LocalDate.parse(rs.getDate("releasedate").toString())));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return binList;
	}
	
	public Optional<Music> addBinMusic(Music music) {
		Optional<Music> musicOp = Optional.empty();

		try {
			connection = dataSource.getConnection();

			pStmt = connection.prepareStatement("INSERT INTO `recent` (`id`, `name`, `artist`,"
					+ " `time`, `genre`, `releasedate`) VALUES (?,?,?,?,?,?);"
					);
			pStmt.setInt(1, music.getId());
			pStmt.setString(2, music.getName());
			pStmt.setString(3, music.getArtist());
			pStmt.setString(4, music.getTime());
			pStmt.setString(5, music.getGenre());
			Date date = Date.valueOf(music.getReleasedate());
			pStmt.setDate(6, date);
		

			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return musicOp;
	}
	public Music getReMusicById(Integer id) {
		Music music = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from recent where id='" + id + "';");

			while (rs.next()) {
				music = new Music(rs.getInt("id"), rs.getString("name"), rs.getString("artist"), rs.getString("time"),
						rs.getString("genre"), LocalDate.parse(rs.getDate("releasedate").toString()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return music;
	}

	public Optional<Music> addReMusic(Music music) {
		Optional<Music> musicOp = Optional.empty();

		try {
			connection = dataSource.getConnection();

			pStmt = connection.prepareStatement("INSERT INTO `music` (`id`, `name`, `artist`,"
					+ " `time`, `genre`, `releasedate`) VALUES (?,?,?,?,?,?);"
					);
			pStmt.setInt(1, music.getId());
			pStmt.setString(2, music.getName());
			pStmt.setString(3, music.getArtist());
			pStmt.setString(4, music.getTime());
			pStmt.setString(5, music.getGenre());
			Date date = Date.valueOf(music.getReleasedate());
			pStmt.setDate(6, date);
		

			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return musicOp;
	}

	



}
