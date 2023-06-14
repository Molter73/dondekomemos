package com.dondekomemos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

enum MenuOptions {
	PRINT_ALL {
		@Override
		public String toString() {
			return "Imprimir todos los restaurantes";
		}
	},
	ADD_REVIEW {
		@Override
		public String toString() {
			return "Agregar una reseña";
		}
	},
	EXIT {
		@Override
		public String toString() {
			return "Salir";
		}
	},
}

public class App {
	UserInterface ui;
	Map<String, Restaurant> restaurants;

	public App() {
		ui = new UserInterface();
		restaurants = new HashMap<>();

		Restaurant restaurant = new Restaurant("MoltoBene!!", "Bella Strada 42");
		Review review = new Review(10, "Increíble!", "La mejor pasta que he comido en mi vida!!",
				new User("Massimo Supremo"));
		restaurant.getReviews().add(review);
		restaurants.put("MoltoBene!!", restaurant);

		restaurant = new Restaurant("Decente", "Gran Via -7");
		review = new Review(5, "Ni muy muy, ni tan tan", "Estuvo decente, sin nada que destacar.",
				new User("Cosme Fulanito"));
		restaurant.getReviews().add(review);
		restaurants.put("Decente", restaurant);

		restaurant = new Restaurant("Fatal...", "Calle Falsa 123");
		review = new Review(0, "La peor cena que tuve en mucho tiempo",
				"Y el ambiente también dejaba mucho que desear, el show en vivo no ayuda.", new User("Anónimo"));
		restaurant.getReviews().add(review);
		restaurants.put("Fatal...", restaurant);
	}

	public static void main(String[] args) {
		App app = new App();

		while (true) {
			MenuOptions option;

			try {
				option = app.ui.Menu();
			} catch (Exception e) {
				System.err.println("Failed to get menu option: " + e.getMessage());
				continue;
			}

			switch (option) {
				case PRINT_ALL:
					for (Entry<String, Restaurant> r : app.restaurants.entrySet()) {
						app.ui.printRestaurant(r.getValue());
					}
					break;

				case ADD_REVIEW:
					String name;

					try {
						name = app.ui.pickRestaurant(app.restaurants);
					} catch (NumberFormatException | IOException e) {
						System.err.println("Failed to pick a restaurant: " + e.getMessage());
						continue;
					}

					app.ui.addReview(app.restaurants.get(name));
					break;
				case EXIT:
				default:
					return;
			}
		}
	}
}
