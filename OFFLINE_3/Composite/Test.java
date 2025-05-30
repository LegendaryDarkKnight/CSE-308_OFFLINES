package Composite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        LocalDateTime creationTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a", Locale.ENGLISH);
        String formattedDateTime = creationTime.format(formatter);
        System.out.println(formattedDateTime);
    }
}
