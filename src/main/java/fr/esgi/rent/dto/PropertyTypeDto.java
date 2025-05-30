package fr.esgi.rent.dto;

import fr.esgi.rent.domain.PropertyTypeEntity;

import java.util.UUID;

public class PropertyTypeDto {

    private UUID id;
    private String designation;

    public PropertyTypeDto(PropertyTypeEntity entity) {
        this.id = entity.getId();
        this.designation = entity.getDesignation();
    }

    public UUID getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }
}
