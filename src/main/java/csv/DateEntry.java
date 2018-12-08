package csv;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an entry for one day
 * The hours and temperature values are stored in a map
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class DateEntry {

    /**
     * Date of the entry
     */
    private LocalDate localDate;

    /**
     * values of the date entry
     * key: hour
     * value: temperature
     */
    private Map<Integer, Double> values;

    /**
     * Creates a new DateEntry
     *
     * @param localDate the date of the DateEntry
     */
    DateEntry(LocalDate localDate) {
        this.localDate = localDate;
        values = new HashMap<>();
    }

    /**
     * Adds an entry
     *
     * @param hour  Hour of the day
     * @param value Temperature value of the hour
     */
    void add(int hour, double value) {
        values.put(hour, value);
    }

    /**
     * Calculate the max temperature value of the day
     *
     * @return max temperature value
     */
    private double getMax() {
        return Collections.max(values.values());
    }

    /**
     * Calculate the min temperature value of the day
     *
     * @return min temperature value
     */
    private double getMin() {
        return Collections.min(values.values());
    }

    /**
     * Calculate the average temperature value of the day
     *
     * @return average temperature value
     */
    private double getAvg() {

        double sum = 0;

        for (Map.Entry<Integer, Double> entry : values.entrySet()) {
            sum += entry.getValue();
        }

        if (values.size() > 0) {
            return sum / values.size();
        } else {
            throw new IllegalStateException("no values");
        }
    }


    /**
     * Return a string of all values (max, min, avg) and the hourly day entries
     *
     * @return a string of all values (max, min, avg) and the hourly day entries
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Max: %.1f°C, Min: %.1f°C, Avg: %.1f°C", getMax(), getMin(), getAvg()));
        sb.append(System.lineSeparator());
        for (Map.Entry<Integer, Double> entry : values.entrySet()) {
            sb.append(localDate.toString());
            sb.append(" ");
            sb.append(entry.getKey());
            sb.append(":00");
            sb.append(" ");
            sb.append(String.format("%.1f°C", entry.getValue()));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
