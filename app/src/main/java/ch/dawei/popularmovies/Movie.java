package ch.dawei.popularmovies;

/**
 * Created by david on 10/18/2017.
 */

public class Movie {

    String moviePoster;
    String title;
    String releasedDate;
    double voteAverage;
    String plotSynopsis;

    public Movie(String moviePoster, String title, String releasedDate, double voteAverage, String plotSynopsis) {
        this.moviePoster = moviePoster;
        this.title = title;
        this.releasedDate = releasedDate;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }
}
