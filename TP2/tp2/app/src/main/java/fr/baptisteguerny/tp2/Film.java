package fr.baptisteguerny.tp2;

public class Film {
        private String title;
        private String director;
        private int duration; // Duration in minutes
        private String poster; // Path to the poster image

    /**
     * Constructor
     * @param title Title of the film
     * @param director Director of the film
     * @param duration Duration of the film in minutes
     * @param poster Path to the poster image
     */
    public Film(String title, String director, int duration, String poster) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationString() {
        int hours = duration / 60;
        int minutes = duration % 60;
        return hours + "h" + minutes;
    }

    public String getPoster() { return poster; }
}
