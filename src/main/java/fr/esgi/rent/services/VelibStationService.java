package fr.esgi.rent.services;

import fr.esgi.rent.dto.VelibStation;
import fr.esgi.rent.dto.VelibStationResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VelibStationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getVelibTowns(List<String> towns) {
        String url = "http://localhost:8081/stations/velibs";

        Map<String, Object> body = Map.of("towns", towns);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<VelibStationResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                VelibStationResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getStations()
                    .stream()
                    .map(VelibStation::getNomCommune)
                    .distinct()
                    .collect(Collectors.toList());
        }

        return List.of();
    }
}
