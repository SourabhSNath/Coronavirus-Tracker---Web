package com.sourabh.coronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LatestGlobalTotalModel {
    @JsonProperty("confirmed")
    private int confirmed;
    private int deaths;
    private int recovered;

    public LatestGlobalTotalModel() {

    }

    public LatestGlobalTotalModel(int confirmed, int deaths, int recovered) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "LatestGlobalTotalModel{" +
                "confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
