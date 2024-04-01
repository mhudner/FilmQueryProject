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
				int languageId = idResult.getInt("language_id");
				String rating = idResult.getString("rating");
				int releaseYear = idResult.getInt("release_year");
				String filmLanguage = idResult.getString("");
				// film = new Film(_filmId, title, description, releaseYear, languageId, rating,
				// filmLanguage);
				film.setFilmId(filmId);
				film.setTitle(title);
				film.setDescription(description);
				film.setLanguageId(languageId);
				film.setRating(rating);
				film.setReleaseYear(releaseYear);
				film.setlanguage(filmLanguage);

				idResult.close();
				stmt.close();
				conn.close();
			} else {

				System.out.println("Boobies :)");
			}
		} catch (SQLException e) {
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
			sql += " rating " + " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
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
				String rating = rs.getString("rating");
				String filmLanguage = rs.getString("");
				Film film = new Film(filmId, title, releaseYear, languageId, rating, description, filmLanguage);
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
//		public Actor(int id, String firstName, String lastName)
		Actor actor = new Actor();

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

			String sql = "SELECT DISTINCT actor.last_name, actor.first_name FROM film_actor JOIN actor ON actor.id=film_actor.actor_id where actor.id=?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			System.out.println(stmt);

			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {

				String actorFirstName = actorResult.getString("first_name");
				String actorLastName = actorResult.getString("last_name");

//				set actor first and last name 
				actor.setId(actorId);
				actor.setFirstName(actorFirstName);
				actor.setLastName(actorLastName);

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
