package com.sana.matjari;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdallah on 4/26/2018.
 */

public class Vendeur implements Serializable {

    private String marketName;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String region;
    private String wilaya;
    private String marketType;
    private String tel;
    private String address;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private float evaluation;
    private List<MarketTag> marketTags = new ArrayList<>();

    public Vendeur(String s) {
        this.marketName = s;
    }

    public Vendeur() {

    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    public void addTag(MarketTag tag) {
        this.marketTags.add(tag);
    }

    public void removeTag(MarketTag tag) {
        this.marketTags.remove(tag);
    }

    public List<MarketTag> getMarketTags() {
        return this.marketTags;
    }

    public void setMarketTags(List<MarketTag> marketTags) {
        this.marketTags = marketTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vendeur vendeur = (Vendeur) o;

        if (marketName != null ? !marketName.equals(vendeur.marketName) : vendeur.marketName != null)
            return false;
        return tel != null ? tel.equals(vendeur.tel) : vendeur.tel == null;
    }

    @Override
    public int hashCode() {
        int result = marketName != null ? marketName.hashCode() : 0;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        return result;
    }

}