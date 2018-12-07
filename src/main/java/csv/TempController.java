package csv;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class TempController {

    private HashMap<LocalDate, DateEntry> values;

    public TempController() throws IOException {
        values = new HashMap<>();

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/values.csv"), ';');

        String[] record;
        while ((record = reader.readNext()) != null) {

            String date = record[0];
            String time = record[1];
            String value = record[2];
            add(date, time, value);
        }

    }

    private void add(String date, String time, String value) {

        LocalDate localDate = LocalDate.parse(date);

        int hour = Integer.valueOf(time);
        double temperature = Double.valueOf(value);

        if (values.containsKey(localDate)) {
            values.get(localDate).add(hour, temperature);
        } else {
            DateEntry entry = new DateEntry(localDate);
            entry.add(hour, temperature);
            values.put(localDate, entry);
        }

    }

    public String get(String dateString) {

        LocalDate date = LocalDate.parse(dateString);

        StringBuilder sb = new StringBuilder();
        sb.append("Date: ");
        sb.append(date.toString());
        sb.append(System.lineSeparator());

        if (!values.containsKey(date)) {
            sb.append("No values found!");
            sb.append(System.lineSeparator());
            return sb.toString();
        }

        sb.append(values.get(date).toString());

        return sb.toString();
    }


}
