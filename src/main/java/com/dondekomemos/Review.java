package com.dondekomemos;

public class Review {
	private Integer rating;
	private String title;
	private String detail;
	private User user;

	public Review(Integer rating, String title, String detail, User user) {
		this.rating = rating;
		this.title = title;
		this.detail = detail;
		this.user = user;
	}

	public Integer getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return user.toString() + ": " + title + " - " + rating + "/10\n\t" + detail;
	}
}
