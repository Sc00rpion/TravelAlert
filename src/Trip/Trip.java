package Trip;

public class Trip {
	public String country;
	public String img;
	public int price;
	public String link;
	
	public Trip(String c, String l, String i, int p){
		country = c;
		link = l;
		img = i;
		price = p;
	}
	
	@Override
	public String toString() {
		return country + ": " + Integer.toString(price) + " z³\nLink: "+link;
	}
	
}
