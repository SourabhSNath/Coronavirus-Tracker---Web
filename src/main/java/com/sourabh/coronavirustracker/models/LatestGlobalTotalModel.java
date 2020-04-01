package com.sourabh.coronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LatestGlobalTotalModel {
    @JsonProperty("confirmed")
    private int confirmed;
    @JsonIgnore
    private int previousConfirmed;
    @JsonProperty("deaths")
    private int deaths;
    @JsonProperty("recovered")
    private int recovered;

    public LatestGlobalTotalModel() {

    }

    public LatestGlobalTotalModel(int confirmed, int previousConfirmed, int deaths, int recovered) {
        this.confirmed = confirmed;
        this.previousConfirmed = previousConfirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getPreviousConfirmed() {
        return previousConfirmed;
    }

    public void setPreviousConfirmed(int previousConfirmed) {
        this.previousConfirmed = previousConfirmed;
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
                ", previousConfirmed=" + previousConfirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
