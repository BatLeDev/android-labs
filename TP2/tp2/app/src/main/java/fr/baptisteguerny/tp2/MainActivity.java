package fr.baptisteguerny.tp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView filmListView = findViewById(R.id.film_list);
        FilmListAdapter filmListAdapter = new FilmListAdapter(getLayoutInflater());
        filmListView.setAdapter(filmListAdapter);

        filmListAdapter.addFilm(new Film("The Shawshank Redemption", "Frank Darabont", 120, "@drawable/the_shawshank_redemption"));
        filmListAdapter.addFilm(new Film("The Godfather", "Francis Ford Coppola", 150, "@drawable/the_godfather"));
        filmListAdapter.addFilm(new Film("The Dark Knight", "Christopher Nolan", 120, "@drawable/the_dark_knight"));
        filmListAdapter.addFilm(new Film("The Lord of the Rings: The Return of the King", "Peter Jackson", 58, "@drawable/the_lord_of_the_rings_the_return_of_the_king"));
    }

    public void openGitHubLink(View view) {
        String url = "https://github.com/BatLeDev/android-labs/blob/master/TP2/TP2.md";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
