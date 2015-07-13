package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SingleTripPaneController implements Initializable {

	@FXML
	 ImageView img;

	@FXML
	 Label cena;

	@FXML
	 Label nazwa;

	@FXML
	 Button opisOferty;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SingleTripPane.setControl(this);
	}

}
