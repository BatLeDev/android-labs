package fr.baptisteguerny.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UnlockTelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_tel);

        TextView outputText = findViewById(R.id.outputText);
        TextView telInput = findViewById(R.id.telInput);
        Button validateBtn = findViewById(R.id.validateBtn);
        validateBtn.setEnabled(false);

        Bundle buldle = getIntent().getExtras();
        String identifier = buldle.getString("identifier");
        String password = buldle.getString("password");
        if (identifier.equals("baptiste") && password.equals("admin")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Bienvenue Baptiste !", Toast.LENGTH_SHORT);
            toast.show();
            outputText.setText("Bienvenue Baptiste !");
            validateBtn.setEnabled(true);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Identifiants incorrects ! Snif... Vous devez revenir en arrière", Toast.LENGTH_SHORT);
            toast.show();
            outputText.setText("Identifiants incorrects ! Snif... Vous devez revenir en arrière");
        }

        validateBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            String phoneNumber = telInput.getText().toString();
            intent.putExtra("phoneNumber", phoneNumber);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    // Method to handle result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Handle the result
            }
        }
    }
}
