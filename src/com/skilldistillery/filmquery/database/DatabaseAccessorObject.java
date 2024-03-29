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
		return null;
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

			String sql = "SELECT *, FROM actor WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// bind variable no. 1 and pass actorId
			stmt.setInt(1, filmId);
			System.out.println(stmt);

			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				int id = actorResult.getInt("id");
				String firstName = actorResult.getString("first_name");
				String lastName = actorResult.getString("last_name");
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

	public List<Film> findFilmByKeyword(int keyword) {

		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
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
				Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						lengthOfFilm, replacementCost, rating, specialFeatures);
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
		// TODO Auto-generated method stub
		return null;
	}

}
