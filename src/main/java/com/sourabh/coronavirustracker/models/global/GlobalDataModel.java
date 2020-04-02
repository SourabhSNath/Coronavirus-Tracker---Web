package com.sourabh.coronavirustracker.models.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalDataModel {

    private int id;
    private String country;
    private String province;
    private GlobalTotalDataModel globalTotalDataModel;

    public GlobalDataModel() {

    }

    public GlobalDataModel(int id, String country, String province,
                           GlobalTotalDataModel globalTotalDataModel) {
        this.id = id;
        this.country = country;
        this.province = province;
        this.globalTotalDataModel = globalTotalDataModel;

    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id + 1;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("province")
    public void setProvince(String province) {
        this.province = province.equals("") ? "-" : province;
    }

    @JsonIgnore
    public void setGlobalTotalDataModel(GlobalTotalDataModel globalTotalDataModel) {
        this.globalTotalDataModel = globalTotalDataModel;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }


    public String getProvince() {
        return province;
    }

    public GlobalTotalDataModel getGlobalTotalDataModel() {
        return globalTotalDataModel;
    }

    @Override
    public String toString() {
        return "GlobalDataModel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", globalTotalDataModel=" + globalTotalDataModel +
                '}';
    }
}
