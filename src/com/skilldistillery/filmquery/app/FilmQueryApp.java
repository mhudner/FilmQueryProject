package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
		// app.showMenu();
	}

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
		System.out.println();
		System.out.println("Please select an option from the menu below: ");
		System.out.println();
		System.out.println();
		System.out.println(" --------------------MENU----------------------");
		System.out.println("|       1. Look up film by Id                  |");
		System.out.println("|       2. Look up a film by a search keyword  |");
		System.out.println("|       3. Exit the Application                |");
		System.out.println(" -----------------------------------------------");

		int userInput = kb.nextInt();

		if (userInput == 1) {

		} else if (userInput == 2) {

		} else {

		}

		kb.close();

	}

}
