package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import Trip.Trip;

public class SingleTripPane {
	private static SingleTripPaneController control;
	private Pane p;
	
	public SingleTripPane(){
		Pane p = null;
		try {
			p = (Pane) FXMLLoader.load(getClass().getResource("SingleTripPane.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.p = p;
	}

	public  Pane getPane(Trip trip) {
		control.img.setImage(new Image(trip.img));
		control.cena.setText(Integer.toString(trip.price));
		control.nazwa.setText(trip.country);
		control.opisOferty.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI(trip.link));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		return p;
		
	}

	public static void setControl(SingleTripPaneController o){
		control = o;
	}
	

}
