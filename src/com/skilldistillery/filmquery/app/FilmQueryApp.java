package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner kb = new Scanner(System.in);
	List<Film> films = new ArrayList<>();

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

		startUserInterface();

	}

	private void startUserInterface() {

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
			kb.nextLine();

			switch (userInput) {
			case 1:
				System.out.println("Please enter the film id # you are trying to locate: ");

				int filmId = kb.nextInt();
				kb.nextLine();
				// System.out.println("film_id" + filmId);
				Film film = db.findFilmById(filmId);
				// Title, year, rating and description
				if (film == null) {
					System.out.println("We have no film matching that criteria, please try again or EXIT the program.");
					System.out.println("Thank you!");
				} else {
					// PrintStream printStream = new PrintStream(null);
					System.out.println(film.printBasicInfo());

					List<Actor> actors = db.findActorsByFilmId(filmId);
					System.out.println("Cast:\n");
					for (Actor actor : actors) {
						actor.printActors();
					}
					runSubMenu();
				}

				break;

			case 2:
				System.out.println("Please enter a keyword to search: ");
				String keyword = kb.nextLine();
				films = db.findFilmsByKeyword(keyword);
				if (films.size() > 0) {
					for (Film filmInList : films) {

						System.out.println(filmInList.printBasicInfo());
					}

					runSubMenu();
					// break;
				}

				else {
					System.out.println(
							"********************************We have no film matching that criteria, please try again or EXIT the program.");
					System.out.println("Thank you!");
					break;
				}

			}

		} while (userInput != 3);

	}

	public void runSubMenu() {
		// Film film = new Film();

		// Return to the main menu. View all film details
		System.out.println(" ---------------------------------------");
		System.out.println("|      1. View all film details         |");
		System.out.println("|      2. Return to main menu           |");
		System.out.println(" --------------------------------------- ");

		int userInput = kb.nextInt();
		kb.nextLine();
		if (userInput == 1) {
			System.out.println(films.toString());
		} else {
			startUserInterface();
		}

	}

}
