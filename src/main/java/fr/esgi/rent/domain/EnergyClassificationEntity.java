package fr.esgi.rent.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "energy_classification")
public class EnergyClassificationEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "designation")
    private String desgination;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDesgination() {
        return desgination;
    }

    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }

}