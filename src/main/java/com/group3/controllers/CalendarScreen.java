package com.group3.controllers;

import com.group3.App;
import com.group3.objects.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class CalendarScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML public GridPane calendarGrid;
    @FXML public Label monthYearLabel;
    @FXML public TextField searchField;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;
    @FXML private VBox page;
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

                addAppointments(dayContent, calendarDate);

                if (calendarDate.equals(searchResultDate)) {
                    Button appointment = new Button("Brooke Cronin\nDr. Smith\nin Room 1\n@ 13:00–13:30");
                    appointment.getStyleClass().add("calendar-appointment");
                    appointment.setMaxWidth(Double.MAX_VALUE);
                    appointment.setOnAction(e -> {
                        try {
                            viewAppointment(null);
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

    private void addAppointments(VBox vBox, LocalDate date) {
        List<Appointment> matchingAppointments = new java.util.ArrayList<>(applicationState.getAppointments()
                .stream()
                .filter(appointment -> appointment.getAppointmentAt().toLocalDate().equals(date))
                .toList());

        matchingAppointments.sort(Comparator.comparing(Appointment::getAppointmentAt));

        for (Appointment appointment : matchingAppointments) {
            Button button = new Button(appointment.getPatient().getName() + "\n" + appointment.getDoctor().getName() + "\n" + appointment.getRoomBooked().getRoomName() + "\n" + appointment.getAppointmentAt().toString());
            button.getStyleClass().add("calendar-appointment");
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(e -> {
                try {
                    viewAppointment(appointment);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            vBox.getChildren().add(button);
        }
    }

    private void viewAppointment(Appointment appointment) throws IOException {
        App.loadAppointmentView(appointment);
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

    @FXML
    public void goToToday() {
        displayYearMonth = YearMonth.now();
        populateCalendar(displayYearMonth);
    }

}
