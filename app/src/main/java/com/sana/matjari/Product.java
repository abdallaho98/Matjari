package com.sana.matjari;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Abdallah on 4/26/2018.
 */

public class Product {
    private String image;



    public String getImage(String productImg) {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String name;
    private Vendeur vendeur;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private float coast;

    public Product(String name, float coast) {
        this.name = name;
        this.coast = coast;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public float getCoast() {
        return coast;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }
    public Product() {
        this.image = "";this.coast = 0;this.name = "";this.description = "";this.vendeur = new Vendeur();
    }
}
