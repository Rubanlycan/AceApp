package com.ionicframework.itech719214;



public class Data {

    int id;
    float rating;
    String name ;
    String file ;
    String image ;
    String link ;
    String free_link ;
    String type_img ;
    String type_pdf ;
    String title ;
    String Product_id ;
    String size ;
    String bool ;

    public Data(int id, float rating,String name, String file, String image, String link, String free_link, String type_img, String type_pdf, String title, String product_id, String size, String bool) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.file = file;
        this.image = image;
        this.link = link;
        this.free_link = free_link;
        this.type_img = type_img;
        this.type_pdf = type_pdf;
        this.title = title;
        this.Product_id = product_id;
        this.size = size;
        this.bool = bool;

    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFree_link() {
        return free_link;
    }

    public void setFree_link(String free_link) {
        this.free_link = free_link;
    }

    public String getType_img() {
        return type_img;
    }

    public void setType_img(String type_img) {
        this.type_img = type_img;
    }

    public String getType_pdf() {
        return type_pdf;
    }

    public void setType_pdf(String type_pdf) {
        this.type_pdf = type_pdf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(String product_id) {
        Product_id = product_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }


}

