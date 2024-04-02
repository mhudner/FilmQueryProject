package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner kb = new Scanner(System.in);

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
				if (userInput == filmId) {
					System.out.println("***Film id #: " + film.getId() + "\nFilm Title: " + film.getTitle()
							+ "\nYear Released: " + film.getReleaseYear() + "\nFilm Rating: " + film.getRating()
							+ "\nFilm Description: " + film.getDescription());

				} else {
					System.out.println("We have no film matching that criteria, please try again or EXIT the program.");
					System.out.println("Thank you!");
				}

				break;

			case 2:
				// kb.nextLine();
				System.out.println("Please enter a keyword to search: ");
				String keyword = kb.nextLine();
				List<Film> films = db.findFilmByKeyword(keyword);
				if (films.size() > 0) {
					Film film1 = new Film();
					System.out.println("***Film id #: " + film1.getId() + "\nFilm Title: " + film1.getTitle()
							+ "\nYear Released: " + film1.getReleaseYear() + "\nFilm Rating: " + film1.getRating()
							+ "\nFilm Description: " + film1.getDescription());
					// Film film2 = new Film();
					// System.out.println(film2.toString());
					runSubMenu(films);
					break;
				}

				else {
					System.out.println(
							"********************************We have no film matching that criteria, please try again or EXIT the program.");
					System.out.println("Thank you!");
					break;
				}
			}

			// }

		} while (userInput != 3);
	}

	public void runSubMenu(List<Film> films) {
		// Film film = new Film();

		// Return to the main menu. View all film details
		System.out.println(" ---------------------------------------");
		System.out.println("|      1. View all film details         |");
		System.out.println("|      2. Return to main menu           |");
		System.out.println(" ---------------------------------------");

		int userInput = kb.nextInt();
		if (userInput == 1) {
			System.out.println(films.toString());
		} else {
			startUserInterface(kb);
		}

	}

}
