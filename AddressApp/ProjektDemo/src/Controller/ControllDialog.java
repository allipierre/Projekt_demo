package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllDialog {
	@FXML
	private DatePicker DP;
	
	 @FXML
	public void initialize() {
		setDatePicker();
		performSelection();

	}

	public ControllDialog start() {
		try {
			// ModellHome m = new ModellHome();
			// setId(m.getIdm()) ;
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Dialog.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("bal.png")));
			primaryStage.setScene(scene);

			primaryStage.initModality(Modality.WINDOW_MODAL);
			primaryStage.initOwner(MainApp.getPrimaryStage());
			primaryStage.setTitle("My Dialog");
			primaryStage.setResizable(false);
			primaryStage.show();

			return loader.getController();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@FXML
	public void setDatePicker() {
		DP.setShowWeekNumbers(false);
	}

	
	@FXML
	public void performSelection(){
		DP.setOnAction(event -> {
		    LocalDate date = DP.getValue();
		    System.out.println("Selected date: " + date);
		});
	}

}
