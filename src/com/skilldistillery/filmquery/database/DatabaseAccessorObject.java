package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.*,language.*,category.*,film_category.*"
					+ " FROM film JOIN language ON film.language_id = language.id"
					+ " JOIN category ON film_category.id = category.name" + " WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			// System.out.println(stmt);

//			title, year, rating, description, language, list of actors

//			|---------------------|
//			| 1 View full details |
//			| 2 return to menu    |
//          |---------------------|

			ResultSet idResult = stmt.executeQuery();
			// System.out.println(idResult);
			if (idResult.next()) {

				String title = idResult.getString("title");
				String description = idResult.getString("description");
				short releaseYear = idResult.getShort("release_year");
				int languageId = idResult.getInt("language_id");
				int rentalDuration = idResult.getInt("rental_duration");
				double rentalRate = idResult.getInt("rental_rate");
				double lengthOfFilm = idResult.getInt("length");
				double replacementCost = idResult.getInt("replacement_cost");
				String rating = idResult.getString("rating");
				String specialFeatures = idResult.getString("special_features");
				String filmLanguage = idResult.getString("name");
				String filmCategory = idResult.getString("category");

				// int filmId, String title, String description, int releaseYear,
				// int languageId, int rentalDuration,
				// double rentalRate, double lengthOfFilm, double replacementCost, String
				// rating, String specialFeatures,
				// String filmLanguage

				film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						lengthOfFilm, replacementCost, rating, specialFeatures, filmLanguage, filmCategory);
				// System.out.println("**************************************************************"
				// + film);

				idResult.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		List<Actor> actors = new ArrayList<>();

		// Actor actor = null;
		String user = "student";
		String pass = "student";
		// Connect to DBMS
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			// Title, year, rating, description, actors
			String sql = "SELECT actor.*"
					+ " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);
			// System.out.println(stmt);

			ResultSet filmIdResult = stmt.executeQuery();
			while (filmIdResult.next()) {
				int actorId = filmIdResult.getInt("id");
				String firstName = filmIdResult.getString("first_name");
				String lastName = filmIdResult.getString("last_name");
				Actor actor = new Actor(actorId, firstName, lastName);

				actors.add(actor);

			}
			filmIdResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

	public List<Film> findFilmsByKeyword(String keyword) {

		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			// actor.* FROM
			String sql = "SELECT film.*, language.*, category.*" + " FROM film"
					+ " JOIN language ON film.language_id = language.id"
					+ " JOIN category ON film_category WHERE film.title LIKE ? OR film.description LIKE ?";

			// System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			// System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// title, year, rating, description, and language
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getInt("rental_rate");
				double lengthOfFilm = rs.getInt("length");
				double replacementCost = rs.getInt("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String filmLanguage = rs.getString("name");
				String filmCategory = rs.getString("category");

				Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						lengthOfFilm, replacementCost, rating, specialFeatures, filmLanguage, filmCategory);

				films.add(film);
				// System.out.println(film);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public Actor findActorById(int actorId) {
//		public Actor(int id, String firstName, String lastName)
		Actor actor = null;

		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			// Title, year, rating and description
//			SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name 
//			FROM film 
//			JOIN language 
//			ON film.language_id = language.id  
//			WHERE film.id = 1;

			String sql = "SELECT actor.*" + " FROM actor WHERE actor.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			// System.out.println(stmt);

			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("actor_id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

//				set actor first and last name 

				actorResult.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

}
