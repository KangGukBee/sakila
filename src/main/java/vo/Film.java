package vo;

public class Film {
	private int FID;
	private String title;
	private String description;
	private String category;
	private double price;
	private int length;
	private String rating;
	private String actor;
	@Override
	public String toString() {
		return "film [FID=" + FID + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", length=" + length + ", rating=" + rating + ", actor=" + actor + "]";
	}
	public int getFID() {
		return FID;
	}
	public void setFID(int fID) {
		FID = fID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getActors() {
		return actor;
	}
	public void setActors(String actor) {
		this.actor = actor;
	}
	
}
