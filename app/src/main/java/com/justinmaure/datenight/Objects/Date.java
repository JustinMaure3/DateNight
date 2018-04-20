package com.justinmaure.datenight.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Max on 3/28/18.
 */

public class Date implements Parcelable {
    private Integer id;
    private String dateName;
    private String description;
    private String picture;
    private Integer isPublic = 0;
    private Integer rating;
//    private String location;
    private String creatorName;
    private Integer isFavourited;

    public Date(String dateName, String description, String picture, Integer isPublic,
                Integer rating, String creatorName, Integer isFavourited){
        this.dateName = dateName;
        this.description = description;
        this.picture = picture;
        this.isPublic = isPublic;
        this.rating = rating;
        this.creatorName = creatorName;
        this.isFavourited = isFavourited;
    }

    public Date(Integer id, String dateName, String description, String picture, Integer isPublic,
                Integer rating, String creatorName, Integer isFavourited) {
        this.id = id;
        this.dateName = dateName;
        this.description = description;
        this.picture = picture;
        this.isPublic = isPublic;
        this.rating = rating;
        this.creatorName = creatorName;
        this.isFavourited = isFavourited;
    }

    public Integer getId() {
        return id;
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

    public Integer getPublic() {
        return isPublic;
    }

    public void setPublic(Integer aPublic) {
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

    public Integer getFavourited() {
        return isFavourited;
    }

    public void setFavourited(Integer favourited) {
        isFavourited = favourited;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.dateName);
        parcel.writeString(this.description);
        parcel.writeString(this.picture);
        parcel.writeInt(this.isPublic);
        parcel.writeInt(this.isFavourited);
        parcel.writeString(this.creatorName);
        parcel.writeInt(this.rating);
    }
}
