package com.justinmaure.datenight.Objects;

/**
 * Created by Max on 3/28/18.
 */

public class Date {
    private Integer id;
    private String dateName;
    private String description;
    private String picture;
    private Boolean isPublic = false;
    private Integer rating;
//    private String location;
    private String creatorName;
    private Boolean isFavourited;

    public Date(String dateName, String description, String picture, boolean isPublic,
                Integer rating, String creatorName, Boolean isFavourited){
        this.dateName = dateName;
        this.description = description;
        this.picture = picture;
        this.isPublic = isPublic;
        this.rating = rating;
        this.creatorName = creatorName;
        this.isFavourited = isFavourited;
    }

    public Date(Integer id, String dateName, String description, String picture, boolean isPublic,
                Integer rating, String creatorName, Boolean isFavourited) {
        this.id = id;
        this.dateName = dateName;
        this.description = description;
        this.picture = picture;
        this.isPublic = isPublic;
        this.rating = rating;
        this.creatorName = creatorName;
        this.isFavourited = isFavourited;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Boolean getFavourited() {
        return isFavourited;
    }

    public void setFavourited(Boolean favourited) {
        isFavourited = favourited;
    }
}
