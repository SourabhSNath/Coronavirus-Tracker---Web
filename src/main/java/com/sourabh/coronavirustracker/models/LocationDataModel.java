package com.sourabh.coronavirustracker.models;

public class LocationDataModel {

    private String state;
    private String country;
    private int latestCaseData;
    private int previousDayDifference;

    public LocationDataModel() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestCaseData() {
        return latestCaseData;
    }

    public void setLatestCaseData(int latestCaseData) {
        this.latestCaseData = latestCaseData;
    }

    public int getPreviousDayDifference() {
        return previousDayDifference;
    }

    public void setPreviousDayDifference(int previousDayDifference) {
        this.previousDayDifference = previousDayDifference;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestCaseData=" + latestCaseData +
                '}';
    }
}
