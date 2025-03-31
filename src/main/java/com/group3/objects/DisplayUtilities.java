package com.group3.objects;

import javafx.scene.control.Separator;

public class DisplayUtilities {
    public static Separator addSeparator() {
        Separator separator = new Separator();
        separator.getStyleClass().add("table-seperator");
        return separator;
    }
}
