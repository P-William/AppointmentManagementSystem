package com.group3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;


public class CalendarScreen extends Application {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML public GridPane calendarGrid;
    @FXML public Label monthYearLabel;
    @FXML public TextField searchField;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;
    private YearMonth displayYearMonth = YearMonth.now();
    private LocalDate searchResultDate = null;


    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
        createToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            createDropdown.setVisible(newVal);
            createDropdown.setManaged(newVal);
        });

        populateCalendar(displayYearMonth);
    }
    private void populateCalendar(YearMonth yearMonth) {
        calendarGrid.getChildren().clear();

        monthYearLabel.setText(
                yearMonth.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + ", " + yearMonth.getYear()
        );

        LocalDate firstOfMonth = yearMonth.atDay(1);
        int dayOfWeekOfFirst = firstOfMonth.getDayOfWeek().getValue() % 7;
        LocalDate calendarDate = firstOfMonth.minusDays(dayOfWeekOfFirst);

        DayOfWeek[] days = DayOfWeek.values();
        for (int i = 0; i < days.length; i++) {
            Label header = new Label(days[i].getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase());
            header.getStyleClass().add("calendar-day-header");
            calendarGrid.add(header, i, 0);
        }

        int rowStart = 1;

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                StackPane dayCell = new StackPane();
                dayCell.getStyleClass().add("calendar-day");

                VBox dayContent = new VBox();
                dayContent.setSpacing(2);

                // Day number
                Text dayNum = new Text(String.valueOf(calendarDate.getDayOfMonth()));
                dayNum.getStyleClass().add("calendar-day-number");

                if (!calendarDate.getMonth().equals(yearMonth.getMonth())) {
                    dayNum.getStyleClass().add("calendar-day-outside");
                }

                dayContent.getChildren().add(dayNum);

                if (calendarDate.equals(searchResultDate)) {
                    Button appointment = new Button("Brooke Cronin\nDr. Smith\nin Room 1\n@ 13:00–13:30");
                    appointment.getStyleClass().add("calendar-appointment");
                    appointment.setMaxWidth(Double.MAX_VALUE);
                    appointment.setOnAction(e -> {
                        try {
                            viewAppointment();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    dayContent.getChildren().add(appointment);
                }

                if (calendarDate.equals(LocalDate.now())) {
                    dayCell.getStyleClass().add("calendar-today");
                }

                dayCell.getChildren().add(dayContent);
                calendarGrid.add(dayCell, col, row + rowStart);
                calendarDate = calendarDate.plusDays(1);
            }
        }
    }

    private void viewAppointment() throws IOException {
        switchScene("appointmentView");
    }

    @FXML
    public void goToPreviousMonth() {
        displayYearMonth = displayYearMonth.minusMonths(1);
        populateCalendar(displayYearMonth);
    }

    @FXML
    public void goToNextMonth() {
        displayYearMonth = displayYearMonth.plusMonths(1);
        populateCalendar(displayYearMonth);
    }

    @FXML
    public void goToPreviousYear() {
        displayYearMonth = displayYearMonth.minusYears(1);
        populateCalendar(displayYearMonth);
    }

    @FXML
    public void goToNextYear() {
        displayYearMonth = displayYearMonth.plusYears(1);
        populateCalendar(displayYearMonth);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/calendarLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1280, 720);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/calendarStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        primaryStage.setTitle("Doctor Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: " + searchField.getText());

        if (searchField.getText().toLowerCase().contains("brooke")) {
            searchResultDate = LocalDate.of(displayYearMonth.getYear(), displayYearMonth.getMonth(), 8);
            populateCalendar(displayYearMonth);
        } else {
            searchResultDate = null;
            populateCalendar(displayYearMonth);
        }
    }
    private void switchScene(String baseName) throws IOException {
        String fxmlPath = String.format("/com/group3/%sLayout.fxml", baseName);
        String cssPath = String.format("/com/group3/%sStyle.css", baseName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        BorderPane root = loader.load();

        Scene scene = new Scene(root, 1280, 720);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());

        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void selectDashboard(ActionEvent actionEvent) throws IOException {
        switchScene("dashboard");
    }

    public void selectCalendar(ActionEvent actionEvent) throws IOException {
        switchScene("calendar");
    }

    public void selectPatients(ActionEvent actionEvent) throws IOException {
        switchScene("patientsSearch");
    }

    public void selectDoctors(ActionEvent actionEvent) throws IOException {
        switchScene("doctorsSearch");
    }

    public void selectRooms(ActionEvent actionEvent) throws IOException {
        switchScene("roomsSearch");
    }

    public void createAppointment(ActionEvent actionEvent) throws IOException {
        switchScene("createAppointment");
    }

    public void createPatient(ActionEvent actionEvent) throws IOException {
        switchScene("createPatient");
    }

    public void createDoctor(ActionEvent actionEvent) throws IOException {
        switchScene("createDoctor");
    }

    public void createRoom(ActionEvent actionEvent) throws IOException {
        switchScene("createRoom");
    }

    @FXML
    public void goToToday() {
        displayYearMonth = YearMonth.now();
        populateCalendar(displayYearMonth);
    }

}
