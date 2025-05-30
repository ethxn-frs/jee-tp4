package fr.esgi.rent.dto;

import fr.esgi.rent.domain.EnergyClassificationEntity;

import java.util.UUID;

public class EnergyClassificationDto {

    private UUID id;
    private String designation;

    public EnergyClassificationDto(EnergyClassificationEntity entity) {
        this.id = entity.getId();
        this.designation = entity.getDesgination();
    }

    public UUID getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }
}
