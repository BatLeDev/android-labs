package fr.baptisteguerny.tp1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText temperatureInput;
    private TextView resultView;
    private ListView historyListView;
    private RadioButton celsiusRadioButton;
    private Button convertButton;
    private ArrayList<HistoryElement> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureInput = findViewById(R.id.temperature_input);
        resultView = findViewById(R.id.output);
        historyListView = findViewById(R.id.history_list);
        celsiusRadioButton = findViewById(R.id.celsius_switch);
        convertButton = findViewById(R.id.convert_button);

        HistoryAdapter adapter = new HistoryAdapter();
        historyListView.setAdapter(adapter);

        restoreHistory();

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperatureInput.getText().toString().isEmpty()) {
                    resultView.setText(getString(R.string.empty_output));
                    return;
                }

                boolean isCelsius = celsiusRadioButton.isChecked();
                double sourceTemperature = Double.parseDouble(temperatureInput.getText().toString());
                double outputTemperature;

                if (isCelsius) {
                    outputTemperature = (sourceTemperature * 9.0 / 5.0) + 32.0;
                } else {
                    outputTemperature = (sourceTemperature - 32.0) * 5.0 / 9.0;
                }

                String unit = isCelsius ? "°F" : "°C";
                double roundedOutputTemperature = Math.round(outputTemperature * 100.0) / 100.0;

                resultView.setText(String.format("%s%s", roundedOutputTemperature, unit));
                temperatureInput.setText("");

                HistoryElement historyElement = new HistoryElement(sourceTemperature, roundedOutputTemperature, isCelsius);
                history.add(historyElement);

                if (history.size() > 10) { // Limit history to 10 elements
                    history.remove(0);
                }
                saveHistory();
                adapter.notifyDataSetChanged(); // Notify the adapter of the change
            }

            private void saveHistory() {
                SharedPreferences sharedPreferences = getSharedPreferences("HistoryPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(history);
                editor.putString("historyList", json);
                editor.apply();
            }
        });
    }

    private void restoreHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("HistoryPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("historyList", null);
        Type type = new TypeToken<ArrayList<HistoryElement>>(){}.getType();
        history = gson.fromJson(json, type);

        if (history == null) {
            history = new ArrayList<>();
        }
    }

    private class HistoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return history.size();
        }

        @Override
        public Object getItem(int position) {
            return history.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.history_item_layout, parent, false);
            }

            TextView textLeft = view.findViewById(R.id.history_conversion_item);
            TextView textRight = view.findViewById(R.id.history_date_item);

            textLeft.setText(history.get(position).getConversion());
            textRight.setText(history.get(position).getDate());

            return view;
        }
    }

}
