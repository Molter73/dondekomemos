package com.dondekomemos;

import java.util.ArrayList;

public class Restaurant {
	private ArrayList<Review> reviews;
	private String name;
	private String address;

	public Restaurant(String name, String address) {
		this.name = name;
		this.address = address;
		this.reviews = new ArrayList<>();
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		String output = name + "\n" + address + "\n";
		Integer averageRating = 0;
		String reviewsString = "";

		for (Review r : reviews) {
			averageRating += r.getRating();
			reviewsString += r.toString() + "\n";
		}

		output += "Calificación promedio: " + averageRating / reviews.size() + "\n";
		output += reviewsString;

		return output;
	}
}
