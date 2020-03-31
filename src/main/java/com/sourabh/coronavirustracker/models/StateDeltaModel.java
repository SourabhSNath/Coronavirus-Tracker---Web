package com.sourabh.coronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StateDeltaModel {
    @JsonProperty("active")
    private int active;
    @JsonProperty("confirmed")
    private int confirmed;
    @JsonProperty("deaths")
    private int deaths;
    @JsonProperty("recovered")
    private int recovered;

    public StateDeltaModel() {

    }

    /**
     * Required to convert number value to int
     * [It was "active" : 59 instead of "active" : "59"]
     */
    public StateDeltaModel(int active) {
        this.active = active;
    }

    public StateDeltaModel(int active, int confirmed, int deaths, int recovered) {
        this.active = active;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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
        return "StateDeltaModel{" +
                "active=" + active +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
