package com.ionicframework.itech719214;



public class Data {

    int id;
    int SB_ID;
    float rating;
    String name ;
    String SB_1st_Path;
    String SB_2st_Path;
    String SB_3st_Path;
    String SB_1st_Link;
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



    public Data(int id, int SB_ID, String SB_1st_Path, String SB_2st_Path, String SB_3st_Path, String SB_1st_Link, float rating, String name, String file, String image, String link, String free_link, String type_img, String type_pdf, String title, String product_id, String size, String bool) {
        this.id = id;
        this.SB_ID = SB_ID;
        this.SB_1st_Path = SB_1st_Path;
        this.SB_2st_Path = SB_2st_Path;
        this.SB_3st_Path = SB_3st_Path;
        this.SB_1st_Link = SB_1st_Link;
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



    public int getSB_ID() {
        return SB_ID;
    }

    public void setSB_ID(int SB_ID) {
        this.SB_ID = SB_ID;
    }

    public String getSB_1st_Path() {
        return SB_1st_Path;
    }

    public void setSB_1st_Path(String SB_1st_Path) {
        this.SB_1st_Path = SB_1st_Path;
    }

    public String getSB_2st_Path() {
        return SB_2st_Path;
    }

    public void setSB_2st_Path(String SB_2st_Path) {
        this.SB_2st_Path = SB_2st_Path;
    }

    public String getSB_3st_Path() {
        return SB_3st_Path;
    }

    public void setSB_3st_Path(String SB_3st_Path) {
        this.SB_3st_Path = SB_3st_Path;
    }

    public String getSB_1st_Link() {
        return SB_1st_Link;
    }

    public void setSB_1st_Link(String SB_1st_Link) {
        this.SB_1st_Link = SB_1st_Link;
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

