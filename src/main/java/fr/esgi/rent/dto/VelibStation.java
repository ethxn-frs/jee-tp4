package fr.esgi.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VelibStation {
    @JsonProperty("stationcode")
    private String stationcode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nom_commune")
    private String nomCommune;

    public String getStationcode() {
        return stationcode;
    }

    public void setStationcode(String stationcode) {
        this.stationcode = stationcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }
}
