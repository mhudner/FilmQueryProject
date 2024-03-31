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
		Film film = new Film();

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

			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, film.language_id,language.name"
					+ " FROM film JOIN language ON film.language_id = language.id WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			System.out.println(stmt);

//			title, year, rating, description, language, list of actors

//			|---------------------|
//			| 1 View full details |
//			| 2 return to menu    |
//          |---------------------|

			ResultSet idResult = stmt.executeQuery();
			if (idResult.next()) {

				int _filmId = idResult.getInt("id");
				String title = idResult.getString("title");
				String description = idResult.getString("description");
				short releaseYear = idResult.getShort("release_year");
				int languageId = idResult.getInt("language_id");
				int rentalDuration = idResult.getInt("rental_duration");
				double rentalRate = idResult.getDouble("rental_rate");
				int lengthOfFilm = idResult.getInt("length");
				double replacementCost = idResult.getDouble("replacement_cost");
				String rating = idResult.getString("rating");
				String specialFeatures = idResult.getString("special_features");
				String filmLanguage = idResult.getString("");
				film = new Film(_filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						lengthOfFilm, replacementCost, rating, specialFeatures, filmLanguage);

//				film.add(film);

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
	public List<Actor> findActorByFilmId(int filmId) {

		List<Actor> actors = new ArrayList<>();

		// Actor actor = null;
		String user = "student";
		String pass = "student";
		// Connect to DBMS
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			// Title, year, rating, description, actors
			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name, actor.first_name, actor.last_name"
					+ "FROM film" + "JOIN language ON film.language_id = language.id" + "WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);
			System.out.println(stmt);

			ResultSet filmIdResult = stmt.executeQuery();
			while (filmIdResult.next()) {
				int actorId = filmIdResult.getInt("actor_id");
				String firstName = filmIdResult.getString("first_name");
				String lastName = filmIdResult.getString("last_name");
				Actor actor = new Actor(actorId, firstName, lastName);

				int _filmId = filmIdResult.getInt("id");
				String title = filmIdResult.getString("title");
				String description = filmIdResult.getString("description");
				short releaseYear = filmIdResult.getShort("release_year");
				int languageId = filmIdResult.getInt("language_id");
				int rentalDuration = filmIdResult.getInt("rental_duration");
				double rentalRate = filmIdResult.getDouble("rental_rate");
				int lengthOfFilm = filmIdResult.getInt("length");
				double replacementCost = filmIdResult.getDouble("replacement_cost");
				String rating = filmIdResult.getString("rating");
				String specialFeatures = filmIdResult.getString("special_features");
				String filmLanguage = filmIdResult.getString("");
				Film film = new Film(_filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						lengthOfFilm, replacementCost, rating, specialFeatures, filmLanguage);

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

	public List<Film> findFilmByKeyword(String keyword) {

		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.releaseYear, film.rating, film.description";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int lengthOfFilm = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String filmLanguage = rs.getString("");
				Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						lengthOfFilm, replacementCost, rating, specialFeatures, filmLanguage);
				films.add(film);
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
		List<Actor> actor1 = new ArrayList<>();

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

			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, film.language_id,language.name"
					+ " FROM film JOIN language ON film.language_id = language.id WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			System.out.println(stmt);

			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {

				int _filmId = actorResult.getInt("id");
				String title = actorResult.getString("title");
				String description = actorResult.getString("description");
				short releaseYear = actorResult.getShort("release_year");
				int languageId = actorResult.getInt("language_id");
				int rentalDuration = actorResult.getInt("rental_duration");
				double rentalRate = actorResult.getDouble("rental_rate");
				int lengthOfFilm = actorResult.getInt("length");
				double replacementCost = actorResult.getDouble("replacement_cost");
				String rating = actorResult.getString("rating");
				String specialFeatures = actorResult.getString("special_features");
				String filmLanguage = actorResult.getString("");
				String actorFirstName = actorResult.getString("first_name");
				actor1.add(actor);

				actorResult.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor1;

	}

}
