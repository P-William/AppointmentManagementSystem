package com.group3.objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Separator;

public class DisplayUtilities {
    public static Separator addSeparator() {
        Separator separator = new Separator();
        separator.getStyleClass().add("table-seperator");
        return separator;
    }

    public static void displaySuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Record Created Successfully");
        alert.show();
    }
}
