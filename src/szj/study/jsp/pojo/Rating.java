package szj.study.jsp.pojo;

import java.util.Date;

public class Rating {
    private int ratingId;
    private String movieName;
    private String studioName;
    private double ratingScore;
    private Date ratingTime;

    public Rating() {}

    public Rating(int ratingId, String movieName, String studioName, double ratingScore, Date ratingTime) {
        this.ratingId = ratingId;
        this.movieName = movieName;
        this.studioName = studioName;
        this.ratingScore = ratingScore;
        this.ratingTime = ratingTime;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public Date getRatingTime() {
        return ratingTime;
    }

    public void setRatingTime(Date ratingTime) {
        this.ratingTime = ratingTime;
    }
}
