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
			System.out.println(idResult);
			if (idResult.next()) {

				int filmId1 = idResult.getInt("id");
				String title = idResult.getString("title");
				String description = idResult.getString("description");
				int languageId = idResult.getInt("language_id");
				String rating = idResult.getString("rating");
				int releaseYear = idResult.getInt("release_year");
				String filmLanguage = idResult.getString("name");
				film = new Film(filmId1, title, description, releaseYear, languageId, rating, filmLanguage);
				film.setFilmId(filmId1);
				film.setTitle(title);
				film.setDescription(description);
				film.setLanguageId(languageId);
				film.setRating(rating);
				film.setReleaseYear(releaseYear);
				film.setlanguage(filmLanguage);

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

		Film film1 = null;
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.title, film.description, film.release_year, film.language_id, film.rating, film.description, film.rating, language.name"
					+ " FROM film " + "JOIN language ON film.language_id = language.id"
					+ "WHERE title LIKE '?' AND OR description LIKE '?'";
			// System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			// System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film1 = new Film();
				// int filmId = rs.getInt("film_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int languageId = rs.getInt("language_id");
				String rating = rs.getString("rating");
				String filmLanguage = rs.getString("name");

				Film film = new Film(title, description, releaseYear, languageId, rating, filmLanguage);

				film.setTitle(title);
				film.setDescription(description);
				film.setReleaseYear(releaseYear);
				film.setLanguageId(languageId);
				film.setRating(rating);
				film.setlanguage(filmLanguage);
				films.add(film1);
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

			String sql = "SELECT actor.last_name, actor.first_name"
					+ "FROM film_actor JOIN actor ON actor.id=film_actor.actor_id" + "WHERE actor.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			System.out.println(stmt);

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
