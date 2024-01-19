package com.pweb.dulich.dto;

public class ImageWithId {
    private Long id;
    private String image;

    public ImageWithId() {
    }

    public ImageWithId(Long id, String image) {
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
