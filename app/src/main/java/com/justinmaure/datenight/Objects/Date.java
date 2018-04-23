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
    private Float rating;
//    private String location;
    private String creatorName;
    private Integer isFavourited;

    public Date(String dateName, String description, String picture, Integer isPublic,
                Float rating, String creatorName, Integer isFavourited){
        this.dateName = dateName;
        this.description = description;
        this.picture = picture;
        this.isPublic = isPublic;
        this.rating = rating;
        this.creatorName = creatorName;
        this.isFavourited = isFavourited;
    }

    public Date(Integer id, String dateName, String description, String picture, Integer isPublic,
                Float rating, String creatorName, Integer isFavourited) {
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
        this.isPublic = aPublic;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
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
        this.isFavourited = favourited;
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
        parcel.writeFloat(this.rating);
    }

    protected Date(Parcel in) {
        this.id = in.readInt();
        this.dateName = in.readString();
        this.description = in.readString();
        this.picture = in.readString();
        this.isPublic = in.readInt();
        this.rating = in.readFloat();
        this.creatorName = in.readString();
        this.isFavourited = in.readInt();
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel source) {
            return new Date(source);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

}
