package com.example.springwebapp.model;


public class Product {

    private Integer id;

    private String linc;
    private String description;
    private Integer price;
    private String prevImage;
    private Autor autor;
    private String type;

    public Product(final String description, final Integer price, final String linc, final String prevLinc,final Autor autor,final Integer type) {
        this.autor=autor;
        this.description = description;
        this.price = price;
        this.linc=linc;
        this.prevImage=prevLinc;
        this.autor.addPortfolio(this);
        switch (type){
            case 1:
                this.type="Animation";
                break;
            case 2:
                this.type="Emotion";
                break;
            case 3:
                this.type="Skin";
                break;

        }

    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    public String getLinc() {
        return this.linc;
    }

    public void setLinc(final String Linc) {
        this.linc = linc;
    }

    public String getPrevImage() {
        return this.prevImage;
    }

    public void setPrevImage(final String linc) { this.prevImage=linc; }

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(final Autor autor) { this.autor=autor; }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) { this.type=type; }

    public Integer getInternalId() {
        return this.id;
    }

    public void setInternalId(final Integer id) { this.id=id; }

}
