package Parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SourcePage.SourcePage;
import Trip.Trip;

public class Parser {

	private final Pattern countryPattern = Pattern.compile("<a class=\"br-item\" href=\"[^<]*</a>");
	// <a class="br-item"
	// href="http://www.travelplanet.pl/wczasy/oferty/grecja/"
	// title="Grecja">Grecja</a>

	private final Pattern linkPattern = Pattern.compile("<a href=\"[^>]*class=\"trip-link\">");
	// <a
	// href="http://www.travelplanet.pl/wczasy/egipt/hurghada/hurghada/triton-empire,12072015ALFA11800.html?czas=7:7&amp;wyzywienie=2&amp;osoby=2&amp;lotnisko=Warszawa&amp;sortowanie=1&amp;kolejnosc=up&amp;dojazd=F"
	// title="Triton Empire" class="trip-link">

	private final Pattern imgPattern = Pattern.compile("<img src=\"[^>]*/>");
	// <img
	// src="http://cdntravelplanet.pl/53245A/NPL/sommer/miniature275x155/grecja/kreta/ammoudara/studios-la-palma_0.jpg"
	// alt="Studios La Palma" />

	private final Pattern pricePattern = Pattern.compile("<span class=\"trip-price\">[^>]*</span>");

	// <span class="trip-price"> 949 z³</span>

	public static void main(String[] args) throws Exception {
		String source = SourcePage
				.downloadSource("http://www.travelplanet.pl/wycieczki-objazdowe/oferty/?wylot=13.07.2015&przylot=01.09.2015&osoby=2&czas=6:8&lotnisko=Warszawa,Warszawa%20-%20Modlin&wyzywienie=1,6,2,5&dojazd=F&sortowanie=1&kolejnosc=up");
		Parser tmp = new Parser();
		ArrayList<Trip> tripArray = tmp.parse(source, 1800);
		for (Trip e : tripArray)
			System.out.println(e);
			
	}

	public ArrayList<Trip> parse(String source, int maxPrice) {
		ArrayList<Trip> tripList = new ArrayList<>();
		Matcher m = countryPattern.matcher(source);
		while (m.find()) {
			Trip tripTmp = createTrip(m);
			if(tripTmp.price > maxPrice)
				return tripList;
			tripList.add(tripTmp);
		}
		return tripList;
	}

	private Trip createTrip(Matcher m) {
		// country
		String country = pullCountry(m.group());

		// link
		String link = pullLink(findPattern(m, linkPattern));
		
		// img
		String img = pullImg(findPattern(m, imgPattern));

		// price
		int price = pullPrice(findPattern(m, pricePattern));
		
		m.usePattern(countryPattern);
		
		return new Trip(country, link, img, price);
	}
	
	private String findPattern(Matcher m, Pattern p){
		m.usePattern(p);
		m.find();
		return m.group();
	}
	private String pullCountry(String s){
		return s.split("\"")[5];
	}
	private String pullLink(String s){
		return s.split("\"")[1];
	}
	private String pullImg(String s){
		return s.split("\"")[1];
	}
	private int pullPrice(String s){
		s = s.substring(25);
		s = s.substring(0, s.length()-9);
		return Integer.parseInt(s.replace(" ", ""));
	}
}
