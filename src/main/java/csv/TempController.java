package csv;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * A Controller for the temperature values
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class TempController {

    /**
     * A Map of all date entries
     * key: a date
     * value: DateEntry to the specific date
     */
    private Map<LocalDate, DateEntry> values;

    /**
     * The constructor creates a new map of DateEntry to a Date and parse a CSV File for entries. If entries are avaiable, the entries will be added to the values map
     *
     * @param file csv file location
     */
    public TempController(String file) {
        values = new HashMap<>();

        CSVReader reader;

        try {
            reader = new CSVReader(new FileReader(file), ';');
            String[] record;
            while ((record = reader.readNext()) != null) {

                String date = record[0];
                String time = record[1];
                String value = record[2];

                add(date, time, value);
            }
        } catch (IOException ignored) {
        }


    }

    /**
     * Creates a new DateEntry from a CSV Row and adds this entry to the map
     * If a DateEntry allready exists, the new Entry will be added in the existing entry
     *
     * @param date  date of the entry
     * @param time  time of the entry
     * @param value value / temperature of the entry
     */
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

    /**
     * Get an DateEntry for a specific date and returns all the data as String
     *
     * @param dateString a specific date
     * @return the data to this date
     */
    public String get(String dateString) {


        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return "Na valid date. A valid date would be 2018-12-06";
        }

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
