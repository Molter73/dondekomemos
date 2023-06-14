package com.dondekomemos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class UserInterface {
	private static final String RESTAURANT_SEPARATOR = "===============================================================";

	BufferedReader br;

	public UserInterface() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public void printRestaurant(Restaurant restaurant) {
		System.out.println(restaurant.toString());
		System.out.println(RESTAURANT_SEPARATOR);
	}

	public String getUserLine() throws IOException {
		return br.readLine();
	}

	public Integer getUserRating() throws NumberFormatException, IOException {
		return Integer.parseInt(br.readLine());
	}

	public MenuOptions Menu() throws NumberFormatException, IOException {
		int i = 1;
		for (MenuOptions option : MenuOptions.values()) {
			System.out.println(i++ + ". " + option.toString());
		}

		int selected = Integer.parseInt(br.readLine()) - 1;

		return MenuOptions.values()[selected];
	}

	public void addReview(Restaurant restaurant) {
		User user;
		String title;
		Integer rating;
		String detail;
		System.out.print("Ingrese su usuario: ");
		try {
			user = new User(getUserLine());
		} catch (Exception e) {
			System.err.println("Failed to get username: " + e.getMessage());
			return;
		}

		System.out.print("Ingrese un título: ");
		try {
			title = getUserLine();
		} catch (IOException e) {
			System.err.println("Failed to get title: " + e.getMessage());
			return;
		}

		System.out.print("Ingrese su calificación: ");
		try {
			rating = getUserRating();
		} catch (NumberFormatException | IOException e) {
			System.err.println("Failed to get user rating: " + e.getMessage());
			return;
		}

		System.out.print("Ingrese el detalle de su reseña: ");
		try {
			detail = getUserLine();
		} catch (IOException e) {
			System.err.println("Failed to get user review: " + e.getMessage());
			return;
		}

		Review review = new Review(rating, title, detail, user);
		restaurant.getReviews().add(review);
	}

	public Restaurant newRestaurant() throws IOException {
		System.out.print("Ingrese el nombre del restaurante: ");
		String name = br.readLine();

		System.out.print("Ingrese la dirección: ");
		String address = br.readLine();

		return new Restaurant(name, address);
	}

	public String pickRestaurant(Map<String, Restaurant> restaurants) throws NumberFormatException, IOException {
		System.out.println("Elija una opción:");

		int i = 1;
		for (String name : restaurants.keySet()) {
			System.out.println(i++ + ". " + name);
		}

		System.out.println(i + ". Nuevo restaurante");

		Integer choice = Integer.parseInt(br.readLine()) - 1;

		if (choice >= restaurants.size()) {
			Restaurant restaurant = newRestaurant();
			restaurants.put(restaurant.getName(), restaurant);

			return restaurant.getName();
		}

		return (String) restaurants.keySet().toArray()[choice];
	}
}
