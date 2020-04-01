package com.sourabh.coronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalDataModel {

    private int id;
    private String country;

    private int population;
    private String province;
    private LatestGlobalTotalModel latestGlobalTotalModel;

    public GlobalDataModel() {

    }

    public GlobalDataModel(int id, String country, int population, String province,
                           LatestGlobalTotalModel latestGlobalTotalModel) {
        this.id = id;
        this.country = country;
        this.population = population;
        this.province = province;
        this.latestGlobalTotalModel = latestGlobalTotalModel;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id + 1;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("country_population")
    public void setPopulation(int population) {
        this.population = population;
    }

    @JsonProperty("province")
    public void setProvince(String province) {
        this.province = province;
    }

    @JsonIgnore
    public void setLatestGlobalTotalModel(LatestGlobalTotalModel latestGlobalTotalModel) {
        this.latestGlobalTotalModel = latestGlobalTotalModel;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public String getProvince() {
        return province;
    }

    public LatestGlobalTotalModel getLatestGlobalTotalModel() {
        return latestGlobalTotalModel;
    }

    @Override
    public String toString() {
        return "GlobalDataModel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", population=" + population +
                ", province='" + province + '\'' +
                ", latestGlobalTotalModel=" + latestGlobalTotalModel +
                '}';
    }
}
