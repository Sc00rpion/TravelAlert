package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import Parser.Parser;
import SourcePage.SourcePage;
import Trip.Trip;

public class SampleController implements Initializable {

	@FXML private VBox centerVBox;
	@FXML private ProgressIndicator progres;

	@FXML
	private void addAction(ActionEvent e) {
		// MyPane p = new MyPane(new Trip("","","",0));
		SingleTripPane tp = new SingleTripPane();
		Trip t = new Trip(
				"Grecja",
				"http://www.travelplanet.pl/wczasy/egipt/hurghada/hurghada/triton-empire,12072015ALFA11800.html?czas=7:7&amp;wyzywienie=2&amp;osoby=2&amp;lotnisko=Warszawa&amp;sortowanie=1&amp;kolejnosc=up&amp;dojazd=F",
				"http://cdntravelplanet.pl/53245A/NPL/sommer/miniature275x155/grecja/kreta/ammoudara/studios-la-palma_0.jpg",
				949);
		Pane p = tp.getPane(t);
		centerVBox.getChildren().add(p);

		//

		SingleTripPane tps = new SingleTripPane();
		Trip ts = new Trip(
				"Egipt",
				"http://www.travelplanet.pl/wczasy/egipt/hurghada/hurghada/triton-empire,12072015ALFA11800.html?czas=7:7&amp;wyzywienie=2&amp;osoby=2&amp;lotnisko=Warszawa&amp;sortowanie=1&amp;kolejnosc=up&amp;dojazd=F",
				"http://cdntravelplanet.pl/53245A/NPL/sommer/miniature275x155/grecja/kreta/ammoudara/studios-la-palma_0.jpg",
				900);
		Pane ps = tps.getPane(ts);
		centerVBox.getChildren().add(ps);
	}

	@FXML
	private void aboutAction(ActionEvent e) {
		System.out.println("OK");
	}

	@FXML
	private void settingAction(ActionEvent e) {
		System.out.println("Ustawienia");
	}

	@FXML
	private void refreshAction(ActionEvent e) {
		progres.setVisible(true);
		Thread thread = new Thread(() -> {
		String source = null;
		try {
			source = SourcePage
					.downloadSource("http://www.travelplanet.pl/wycieczki-objazdowe/oferty/?wylot=13.07.2015&przylot=01.09.2015&osoby=2&czas=6:8&lotnisko=Warszawa,Warszawa%20-%20Modlin&wyzywienie=1,6,2,5&dojazd=F&sortowanie=1&kolejnosc=up");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Parser tmp = new Parser();
		ArrayList<Trip> tripArray = tmp.parse(source, 2500);
		for (Trip q : tripArray) {
			SingleTripPane t = new SingleTripPane();
			Pane p = t.getPane(q);
			Platform.runLater(() ->	centerVBox.getChildren().add(p));
		}
		Platform.runLater(() -> progres.setVisible(false));
		});
		thread.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
