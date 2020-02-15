package com.sana.matjari;

import java.util.Date;

/**
 * Created by asus on 27/04/2018.
 */

public class Commande {

    private Client client;
    private Vendeur vendeur;
    private Date date;
    private Product product;
    private int amount;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
