package com.example.cinehub.Movie;

import java.util.List;

public class MovieModel {

    @com.squareup.moshi.Json(name = "Title")
    private String title;
    @com.squareup.moshi.Json(name = "Year")
    private String year;
    @com.squareup.moshi.Json(name = "Rated")
    private String rated;
    @com.squareup.moshi.Json(name = "Released")
    private String released;
    @com.squareup.moshi.Json(name = "Runtime")
    private String runtime;
    @com.squareup.moshi.Json(name = "Genre")
    private String genre;
    @com.squareup.moshi.Json(name = "Director")
    private String director;
    @com.squareup.moshi.Json(name = "Writer")
    private String writer;
    @com.squareup.moshi.Json(name = "Actors")
    private String actors;
    @com.squareup.moshi.Json(name = "Plot")
    private String plot;
    @com.squareup.moshi.Json(name = "Language")
    private String language;
    @com.squareup.moshi.Json(name = "Country")
    private String country;
    @com.squareup.moshi.Json(name = "Awards")
    private String awards;
    @com.squareup.moshi.Json(name = "Poster")
    private String poster;
    @com.squareup.moshi.Json(name = "Ratings")
    private List<Ratings> ratings = null;
    @com.squareup.moshi.Json(name = "Metascore")
    private String metascore;
    @com.squareup.moshi.Json(name = "imdbRating")
    private String imdbRating;
    @com.squareup.moshi.Json(name = "imdbVotes")
    private String imdbVotes;
    @com.squareup.moshi.Json(name = "imdbID")
    private String imdbID;
    @com.squareup.moshi.Json(name = "Type")
    private String type;
    @com.squareup.moshi.Json(name = "DVD")
    private String dvd;
    @com.squareup.moshi.Json(name = "BoxOffice")
    private String boxOffice;
    @com.squareup.moshi.Json(name = "Production")
    private String production;
    @com.squareup.moshi.Json(name = "Website")
    private String website;
    @com.squareup.moshi.Json(name = "Response")
    private String response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.country == null) ? 0 : this.country.hashCode()));
        result = ((result * 31) + ((this.year == null) ? 0 : this.year.hashCode()));
        result = ((result * 31) + ((this.production == null) ? 0 : this.production.hashCode()));
        result = ((result * 31) + ((this.language == null) ? 0 : this.language.hashCode()));
        result = ((result * 31) + ((this.imdbRating == null) ? 0 : this.imdbRating.hashCode()));
        result = ((result * 31) + ((this.title == null) ? 0 : this.title.hashCode()));
        result = ((result * 31) + ((this.imdbVotes == null) ? 0 : this.imdbVotes.hashCode()));
        result = ((result * 31) + ((this.type == null) ? 0 : this.type.hashCode()));
        result = ((result * 31) + ((this.dvd == null) ? 0 : this.dvd.hashCode()));
        result = ((result * 31) + ((this.plot == null) ? 0 : this.plot.hashCode()));
        result = ((result * 31) + ((this.ratings == null) ? 0 : this.ratings.hashCode()));
        result = ((result * 31) + ((this.genre == null) ? 0 : this.genre.hashCode()));
        result = ((result * 31) + ((this.released == null) ? 0 : this.released.hashCode()));
        result = ((result * 31) + ((this.website == null) ? 0 : this.website.hashCode()));
        result = ((result * 31) + ((this.director == null) ? 0 : this.director.hashCode()));
        result = ((result * 31) + ((this.imdbID == null) ? 0 : this.imdbID.hashCode()));
        result = ((result * 31) + ((this.runtime == null) ? 0 : this.runtime.hashCode()));
        result = ((result * 31) + ((this.rated == null) ? 0 : this.rated.hashCode()));
        result = ((result * 31) + ((this.actors == null) ? 0 : this.actors.hashCode()));
        result = ((result * 31) + ((this.awards == null) ? 0 : this.awards.hashCode()));
        result = ((result * 31) + ((this.response == null) ? 0 : this.response.hashCode()));
        result = ((result * 31) + ((this.writer == null) ? 0 : this.writer.hashCode()));
        result = ((result * 31) + ((this.metascore == null) ? 0 : this.metascore.hashCode()));
        result = ((result * 31) + ((this.boxOffice == null) ? 0 : this.boxOffice.hashCode()));
        result = ((result * 31) + ((this.poster == null) ? 0 : this.poster.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieModel) == false) {
            return false;
        }
        MovieModel rhs = ((MovieModel) other);
        return ((((((((((((((((((((((((((this.country == rhs.country) || ((this.country != null) && this.country.equals(rhs.country))) && ((this.year == rhs.year) || ((this.year != null) && this.year.equals(rhs.year)))) && ((this.production == rhs.production) || ((this.production != null) && this.production.equals(rhs.production)))) && ((this.language == rhs.language) || ((this.language != null) && this.language.equals(rhs.language)))) && ((this.imdbRating == rhs.imdbRating) || ((this.imdbRating != null) && this.imdbRating.equals(rhs.imdbRating)))) && ((this.title == rhs.title) || ((this.title != null) && this.title.equals(rhs.title)))) && ((this.imdbVotes == rhs.imdbVotes) || ((this.imdbVotes != null) && this.imdbVotes.equals(rhs.imdbVotes)))) && ((this.type == rhs.type) || ((this.type != null) && this.type.equals(rhs.type)))) && ((this.dvd == rhs.dvd) || ((this.dvd != null) && this.dvd.equals(rhs.dvd)))) && ((this.plot == rhs.plot) || ((this.plot != null) && this.plot.equals(rhs.plot)))) && ((this.ratings == rhs.ratings) || ((this.ratings != null) && this.ratings.equals(rhs.ratings)))) && ((this.genre == rhs.genre) || ((this.genre != null) && this.genre.equals(rhs.genre)))) && ((this.released == rhs.released) || ((this.released != null) && this.released.equals(rhs.released)))) && ((this.website == rhs.website) || ((this.website != null) && this.website.equals(rhs.website)))) && ((this.director == rhs.director) || ((this.director != null) && this.director.equals(rhs.director)))) && ((this.imdbID == rhs.imdbID) || ((this.imdbID != null) && this.imdbID.equals(rhs.imdbID)))) && ((this.runtime == rhs.runtime) || ((this.runtime != null) && this.runtime.equals(rhs.runtime)))) && ((this.rated == rhs.rated) || ((this.rated != null) && this.rated.equals(rhs.rated)))) && ((this.actors == rhs.actors) || ((this.actors != null) && this.actors.equals(rhs.actors)))) && ((this.awards == rhs.awards) || ((this.awards != null) && this.awards.equals(rhs.awards)))) && ((this.response == rhs.response) || ((this.response != null) && this.response.equals(rhs.response)))) && ((this.writer == rhs.writer) || ((this.writer != null) && this.writer.equals(rhs.writer)))) && ((this.metascore == rhs.metascore) || ((this.metascore != null) && this.metascore.equals(rhs.metascore)))) && ((this.boxOffice == rhs.boxOffice) || ((this.boxOffice != null) && this.boxOffice.equals(rhs.boxOffice)))) && ((this.poster == rhs.poster) || ((this.poster != null) && this.poster.equals(rhs.poster))));
    }
}