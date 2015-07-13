package SourcePage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SourcePage {
	public static String downloadSource(String link) throws MalformedURLException, IOException {
		StringBuilder source = new StringBuilder("");
		URL akt = new URL(link);
		BufferedReader in = new BufferedReader(new InputStreamReader(akt.openStream(), "utf-8"));
		String linia;
		while ((linia = in.readLine()) != null)
			source.append(linia).append("\n");
		in.close();
		return source.toString();
	}

}
