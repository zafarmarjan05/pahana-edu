package model;

public class book {
	
	private int id;
	private String title;
	private String category;
	private String author;
	private String language;
	private double price;
	
	
	
	public book() {}

	public book(int id, String title, String category, String author, String language, double price) {
	    this.id = id;
	    this.title = title;
	    this.category = category;
	    this.author = author;
	    this.language = language;
	    this.price = price;
	}

	// Getters and Setters

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getTitle() {
	    return title;
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
	}

	public String getAuthor() {
	    return author;
	}

	public void setAuthor(String author) {
	    this.author = author;
	}

	public String getLanguage() {
	    return language;
	}

	public void setLanguage(String language) {
	    this.language = language;
	}

	public double getPrice() {
	    return price;
	}

	public void setPrice(double price) {
	    this.price = price;
	}


}
