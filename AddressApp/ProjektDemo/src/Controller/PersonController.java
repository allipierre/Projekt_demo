package Controller;

import Util.DateUtil;
import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modell.Person;

public class PersonController {
	@FXML
	private TableView<Person> Tablev;
	@FXML
	private TableColumn<Person, String> IDFIRSTNAME;
	@FXML
	private TableColumn<Person, String> IDLASTNAME;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;
	// Reference to the main application.
	private MainApp mainApp;

	public PersonController() {

	}

	@FXML
	private void initialize() {
		setdata();
		Listener();
	}

	public void performDialoge() {

		new ControllDialog().start();

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		Tablev.setItems(mainApp.getPersonData());
	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());

			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			// Person is null, remove all the text.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	public void setdata() {
		// Initialize the person table with the two columns.
		IDFIRSTNAME.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		IDLASTNAME.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}

	public void Listener() {

		// Clear person details.
		showPersonDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		Tablev.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		try {
			int selectedIndex = Tablev.getSelectionModel().getSelectedIndex();
			Tablev.getItems().remove(selectedIndex);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("You could not delete empty Row!");

			alert.showAndWait();
		}
	}

}
