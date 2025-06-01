package fr.esgi.rent.dto;

import java.util.List;

public class VelibStationResponse {
    private List<VelibStation> stations;

    public List<VelibStation> getStations() {
        return stations;
    }

    public void setStations(List<VelibStation> stations) {
        this.stations = stations;
    }
}
