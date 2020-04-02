package com.sourabh.coronavirustracker.models.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalTotalDataModel {
    @JsonProperty("confirmed")
    private int confirmed;
    @JsonIgnore
    private int newConfirmed;
    @JsonProperty("deaths")
    private int deaths;
    @JsonProperty("recovered")
    private int recovered;

    public GlobalTotalDataModel() {

    }

    public GlobalTotalDataModel(int confirmed, int newConfirmed, int deaths, int recovered) {
        this.confirmed = confirmed;
        this.newConfirmed = newConfirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
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
                ", newConfirmed=" + newConfirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
