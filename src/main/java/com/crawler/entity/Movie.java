package com.crawler.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Movie)实体类
 *
 * @author makejava
 * @since 2020-04-07 14:51:18
 */
public class Movie implements Serializable {
    private static final long serialVersionUID = 759528057011765205L;
    
    private Integer id;
    
    private String name;
    
    private String starring;
    
    private String director;
    
    private Date year;
    
    private String area;
    
    private String synopsis;
    
    private String imgpath;
    
    private String moviepath;
    
    private Integer playernum;
    
    private Boolean carousel;
    
    private String carouselimg;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getMoviepath() {
        return moviepath;
    }

    public void setMoviepath(String moviepath) {
        this.moviepath = moviepath;
    }

    public Integer getPlayernum() {
        return playernum;
    }

    public void setPlayernum(Integer playernum) {
        this.playernum = playernum;
    }

    public Boolean getCarousel() {
        return carousel;
    }

    public void setCarousel(Boolean carousel) {
        this.carousel = carousel;
    }

    public String getCarouselimg() {
        return carouselimg;
    }

    public void setCarouselimg(String carouselimg) {
        this.carouselimg = carouselimg;
    }

}