package com.netflix.api.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.netflix.api.utility.DateHandler;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "netflix_data")
public class NetflixData {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "show_Id")
	private String showId;
	@Column(name = "type_Desc")
	private String type;
	private String title;
	private String director;
	@Column(name = "cast_Desc")
	private String cast;
	private String country;
	@Column(name = "date_Added")
	private String dateAdded;
	@Column(name = "release_Year")
	private Integer releaseYear;
	private String rating;
	private String duration;
	@Column(name = "listed_In")
	private String listedIn;
	private String description;
	
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDateAdded() throws ParseException {
		return this.dateAdded;
	}
	public void setDateAdded(String dateAdded) throws ParseException {
		
		this.dateAdded = dateAdded;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getListedIn() {
		return listedIn;
	}
	public void setListedIn(String listedIn) {
		this.listedIn = listedIn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getShowId()+" "+getType();
	}
	
	
	
}

