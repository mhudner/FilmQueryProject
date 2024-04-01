package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
		// app.showMenu();
	}
//
//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		Scanner kb = new Scanner(System.in);

		System.out.println("Welcome to the Skill Distillery Film Database!");

		int userInput;
		do {
			System.out.println();
			System.out.println("Please select an option from the menu below: ");
			System.out.println();
			System.out.println(" --------------------MENU----------------------");
			System.out.println("|       1. Look up film by Id                  |");
			System.out.println("|       2. Look up a film by a search keyword  |");
			System.out.println("|       3. Exit the Application                |");
			System.out.println(" -----------------------------------------------");

			userInput = kb.nextInt();

			switch (userInput) {
			case 1:
				System.out.println("Please enter the film id # you are trying to locate: ");

				int filmId = kb.nextInt();
				Film film = db.findFilmById(filmId);

				// Title, year, rating and description
				System.out.println("Film id #: " + film.getFilmId() + "\nFilm Title: " + film.getTitle()
						+ "\nYear Released: " + film.getReleaseYear() + "\nFilm Rating: " + film.getRating()
						+ "\nFilm Description: " + film.getDescription());
				// film.actorById to get actor

//				else {
//					
//				}
				continue;

			case 2:
				kb.nextLine();
				System.out.println("Please enter a keyword to search: ");
				String keyword = kb.nextLine();
				List<Film> films = db.findFilmByKeyword(keyword);
				if (films.size() > 0) {
					for (Film film2 : films) {
						System.out.println("Film id #: " + film2.getFilmId() + "Film Title: " + film2.getTitle()
								+ "Year Released: " + film2.getReleaseYear() + "Film Rating: " + film2.getRating()
								+ "Film Description: " + film2.getDescription());
					}
				} else {
					System.out.println("We have no film matching that criteria, please try again or EXIT the program.");
					System.out.println("Thank you!");
				}
				continue;
			case 3:
				System.out.println("Sorry you couldn't find the film you were looking for!");
				System.out.println("Have a great day and visit us again soon!");
				break;

			default:
				continue;

			}

		} while (userInput != 3);
	}

}
