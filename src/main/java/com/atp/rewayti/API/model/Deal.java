
package com.atp.rewayti.API.model;

import com.google.gson.annotations.SerializedName;

public class Deal {

    @SerializedName("author")
    private String mAuthor;
    @SerializedName("book_id")
    private String mBookId;
    @SerializedName("book_image")
    private String mBookImage;
    @SerializedName("book_name")
    private String mBookName;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("randid")
    private String mRandid;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getBookId() {
        return mBookId;
    }

    public void setBookId(String bookId) {
        mBookId = bookId;
    }

    public String getBookImage() {
        return mBookImage;
    }

    public void setBookImage(String bookImage) {
        mBookImage = bookImage;
    }

    public String getBookName() {
        return mBookName;
    }

    public void setBookName(String bookName) {
        mBookName = bookName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getRandid() {
        return mRandid;
    }

    public void setRandid(String randid) {
        mRandid = randid;
    }

}
