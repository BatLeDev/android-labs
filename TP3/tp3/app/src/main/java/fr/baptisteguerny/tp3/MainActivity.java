package fr.baptisteguerny.tp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;
    private String phoneNumber;
    private Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signinBtn = findViewById(R.id.signinBtn);
        this.call = findViewById(R.id.callBtn);
        this.call.setEnabled(false);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText identifier = findViewById(R.id.identifierInput);
                EditText password = findViewById(R.id.pwdInput);

                Intent intent = new Intent(MainActivity.this, UnlockTelActivity.class);
                intent.putExtra("identifier", identifier.getText().toString());
                intent.putExtra("password", password.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        this.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            phoneNumber = data.getStringExtra("phoneNumber");
            this.call.setEnabled(true);
        }
    }

    public void openGitHubLink(View view) {
        String url = "https://github.com/BatLeDev/android-labs/blob/master/TP3/TP3.md";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
