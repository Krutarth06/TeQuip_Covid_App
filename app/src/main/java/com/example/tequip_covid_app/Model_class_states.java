package com.example.tequip_covid_app;

public class Model_class_states {
    public String state, totalCasesIndia, totalCasesForeign, recovered, deaths, totalCases;

//    public Model_class_states() {
//    }

    public Model_class_states(String state, String totalCasesIndia, String totalCasesForeign, String recovered, String deaths, String totalCases) {
        this.state = state;
        this.totalCasesIndia = totalCasesIndia;
        this.totalCasesForeign = totalCasesForeign;
        this.recovered = recovered;
        this.deaths = deaths;
        this.totalCases = totalCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotalCasesIndia() {
        return totalCasesIndia;
    }

    public void setTotalCasesIndia(String totalCasesIndia) {
        this.totalCasesIndia = totalCasesIndia;
    }

    public String getTotalCasesForeign() {
        return totalCasesForeign;
    }

    public void setTotalCasesForeign(String totalCasesForeign) {
        this.totalCasesForeign = totalCasesForeign;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }
}
