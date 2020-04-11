package com.sourabh.coronavirustracker.models.india;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndianDataModel implements Serializable {

    @JsonIgnore
    private int slNo;

    @JsonProperty("active")
    private int currentlyActive;

    @JsonProperty("confirmed")
    private int totalConfirmed;

    @JsonProperty("deaths")
    private int deaths;

    @JsonProperty("recovered")
    private int curedOrMigrated;

    @JsonProperty("state")
    private String stateOrUT;

    @JsonProperty("deltaconfirmed")
    private int deltaConfirmed;

    @JsonProperty("deltadeaths")
    private int deltaDeaths;

    @JsonProperty("deltarecovered")
    private int deltaRecovered;

    public IndianDataModel() {
    }

    public IndianDataModel(int slNo, int currentlyActive,
                           int totalConfirmed, int deaths,
                           int curedOrMigrated, String stateOrUT,
                           int deltaConfirmed, int deltaDeaths, int deltaRecovered) {
        this.slNo = slNo;
        this.currentlyActive = currentlyActive;
        this.totalConfirmed = totalConfirmed;
        this.deaths = deaths;
        this.curedOrMigrated = curedOrMigrated;
        this.stateOrUT = stateOrUT;
        this.deltaConfirmed = deltaConfirmed;
        this.deltaDeaths = deltaDeaths;
        this.deltaRecovered = deltaRecovered;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public int getCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(int currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getCuredOrMigrated() {
        return curedOrMigrated;
    }

    public void setCuredOrMigrated(int curedOrMigrated) {
        this.curedOrMigrated = curedOrMigrated;
    }

    public String getStateOrUT() {
        return stateOrUT;
    }

    public void setStateOrUT(String stateOrUT) {
        this.stateOrUT = stateOrUT;
    }

    public int getDeltaConfirmed() {
        return deltaConfirmed;
    }

    public void setDeltaConfirmed(int deltaConfirmed) {
        this.deltaConfirmed = deltaConfirmed;
    }

    public int getDeltaDeaths() {
        return deltaDeaths;
    }

    public void setDeltaDeaths(int deltaDeaths) {
        this.deltaDeaths = deltaDeaths;
    }

    public int getDeltaRecovered() {
        return deltaRecovered;
    }

    public void setDeltaRecovered(int deltaRecovered) {
        this.deltaRecovered = deltaRecovered;
    }

    @Override
    public String toString() {
        return "IndianDataModel{" +
                "slNo=" + slNo +
                ", currentlyActive=" + currentlyActive +
                ", totalConfirmed=" + totalConfirmed +
                ", deaths=" + deaths +
                ", curedOrMigrated=" + curedOrMigrated +
                ", stateOrUT='" + stateOrUT + '\'' +
                ", deltaConfirmed=" + deltaConfirmed +
                ", deltaDeaths=" + deltaDeaths +
                ", deltaRecovered=" + deltaRecovered +
                '}';
    }
}
