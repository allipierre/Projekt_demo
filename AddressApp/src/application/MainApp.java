package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modell.Person;

public class MainApp extends Application {
	private static Stage primaryStage;
	private BorderPane rootLayout;
	// private String rootB = "view/RootLayout.fxml";
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public MainApp() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Atlantic GmbH");
		initRootLayout();
		showPersonOverview();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initRootLayout() {
		try {
			// Load Root Layout from fxml File
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("bal.png")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}
}
