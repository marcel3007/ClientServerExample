package csv;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DateEntry {

    private LocalDate localDate;

    // hour, value
    private Map<Integer, Double> values;

    DateEntry(LocalDate localDate) {
        this.localDate = localDate;
        values = new HashMap<>();
    }

    void add(int hour, double value) {
        values.put(hour, value);
    }

    private double getMax() {
        return Collections.max(values.values());
    }

    private double getMin() {
        return Collections.min(values.values());
    }

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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
//        sb.append("Date: ");
//        sb.append(localDate.toString());
//        sb.append(System.lineSeparator());
        sb.append(String.format("Max: %.1f°C, Min: %.1f°C, Avg: %.1f°C", getMax(), getMin(), getAvg()));
        sb.append(System.lineSeparator());
        for (Map.Entry<Integer, Double> entry : values.entrySet()) {
            sb.append(localDate.toString());
            sb.append(" ");
            sb.append(entry.getKey());
            sb.append( ":00");
            sb.append(" ");
            sb.append(String.format("%.1f°C",entry.getValue()));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
