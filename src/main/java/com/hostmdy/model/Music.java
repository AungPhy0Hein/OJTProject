package com.hostmdy.model;

import java.time.LocalDate;

public class Music {

	private Integer id;
	private String name;
	private String artist;
	private String time;
	private String genre;
	private LocalDate releasedate;
	public Music(Integer id, String name, String artist, String time, String genre, LocalDate releasedate) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.time = time;
		this.genre = genre;
		this.releasedate = releasedate;
	}
	public Music(String name, String artist, String time, String genre, LocalDate releasedate) {
		super();
		this.name = name;
		this.artist = artist;
		this.time = time;
		this.genre = genre;
		this.releasedate = releasedate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public LocalDate getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(LocalDate releasedate) {
		this.releasedate = releasedate;
	}
	@Override
	public String toString() {
		return "Music [id=" + id + ", name=" + name + ", artist=" + artist + ", time=" + time + ", genre=" + genre
				+ ", releasedate=" + releasedate + "]";
	}
	
	
	
	
}
