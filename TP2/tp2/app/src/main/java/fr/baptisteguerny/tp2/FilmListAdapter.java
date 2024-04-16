package fr.baptisteguerny.tp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FilmListAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<Film> films = new ArrayList<>();

    public FilmListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int position) {
        return films.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.film_list_item, parent, false);
            holder = new ViewHolder();
            holder.poster = view.findViewById(R.id.film_poster);
            holder.title = view.findViewById(R.id.film_title);
            holder.director = view.findViewById(R.id.film_director);
            holder.duration = view.findViewById(R.id.film_duration);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Film film = films.get(position);

        String posterResourceName = film.getPoster().substring(10); // Remove "@drawable/" from the start of the string
        int posterResourceId = view.getResources().getIdentifier(posterResourceName, "drawable", view.getContext().getPackageName());
        holder.poster.setImageResource(posterResourceId);

        holder.title.setText(film.getTitle());
        holder.director.setText(film.getDirector());
        holder.duration.setText(String.format("%d min", film.getDuration()));

        view.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), film.getTitle(), Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    public void addFilm(Film film) {
        films.add(film);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        ImageView poster;
        TextView title;
        TextView director;
        TextView duration;
    }
}
