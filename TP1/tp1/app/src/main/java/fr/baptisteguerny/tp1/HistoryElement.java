package fr.baptisteguerny.tp1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryElement {
    private double sourceTemperature;
    private double outputTemperature;
    private boolean sourceIsCelsius;
    private Date date;

    public HistoryElement(double sourceTemperature, double outputTemperature, boolean sourceIsCelsius) {
        this.sourceTemperature = sourceTemperature;
        this.outputTemperature = outputTemperature;
        this.sourceIsCelsius = sourceIsCelsius;
        this.date = new Date();
    }

    public String getDate() {
        SimpleDateFormat sdf;
        if (isSameDay(date)) {
            sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        }
        return sdf.format(date);
    }

    private boolean isSameDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return sdf.format(date).equals(sdf.format(new Date()));
    }

    public String getConversion() {
        String sourceFormat = sourceIsCelsius ? "°C" : "°F";
        String outputFormat = sourceIsCelsius ? "°F" : "°C";
        return sourceTemperature + sourceFormat + " -> " + outputTemperature + outputFormat;
    }
}
