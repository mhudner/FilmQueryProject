package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int filmId;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private double lengthOfFilm;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	private String filmLanguage;

	public Film(int filmId, String title, String description, int releaseYear, int languageId, String rating,
			String filmLanguage) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rating = rating;
		this.filmLanguage = filmLanguage;

	}

	public Film() {
		super();
	}

	public Film(int filmId, String title, int releaseYear, String description, int languageId, String rating,
			String filmLanguage) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rating = rating;
		this.filmLanguage = filmLanguage;
		// TODO Auto-generated constructor stub
	}

	public Film(int filmId, String title, short releaseYear, int languageId, String rating, String description,
			String filmLanguage) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rating = rating;
		this.filmLanguage = filmLanguage;
		// TODO Auto-generated constructor stub
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getLengthOfFilm() {
		return lengthOfFilm;
	}

	public void setLengthOfFilm(double lengthOfFilm) {
		this.lengthOfFilm = lengthOfFilm;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getlanguage() {
		return filmLanguage;
	}

	public void setlanguage(String filmLanguage) {
		this.filmLanguage = filmLanguage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, filmId, languageId, lengthOfFilm, rating, releaseYear, rentalDuration,
				replacementCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description)
				&& filmId == other.filmId && languageId == other.languageId && lengthOfFilm == other.lengthOfFilm
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film ID: " + filmId + "/nTitle: " + title + "/nDescription: " + description + "/nRelease Year: "
				+ releaseYear + "/nLanguage Id: " + languageId + "/nRental Duration: " + rentalDuration
				+ "/nRental Rate: " + rentalRate + "/nLength Of Film: " + lengthOfFilm + "/nReplacement Cost: "
				+ replacementCost + "/nRating: " + rating + "/nSpecial Features: " + specialFeatures;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

}
