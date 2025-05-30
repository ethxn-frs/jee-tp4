package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.RentalPropertyCreateDto;
import fr.esgi.rent.dto.RentalPropertyDto;
import fr.esgi.rent.repository.EnergyClassificationRepository;
import fr.esgi.rent.repository.PropertyTypeRepository;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RentalPropertyResource {

    @Autowired
    private RentalPropertyRepository rentalPropertyRepository;

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    @Autowired
    private EnergyClassificationRepository energyClassificationRepository;

    @GetMapping("/rental-properties")
    public List<RentalPropertyDto> getRentalProperties() {
        return rentalPropertyRepository.findAll().stream().map(RentalPropertyDto::new).collect(Collectors.toList());
    }

    @GetMapping("/rental-properties/{id}")
    public RentalPropertyDto getRentalPropertyById(@PathVariable UUID id) {
        return rentalPropertyRepository.findById(id)
                .map(RentalPropertyDto::new)
                .orElse(null);
    }

    @PostMapping("/rental-properties")
    public RentalPropertyDto createRentalProperty(@RequestBody RentalPropertyCreateDto dto) {
        RentalPropertyEntity entity = new RentalPropertyEntity(dto);

        if (propertyTypeRepository.findById(dto.getPropertyTypeId()).isPresent()) {
            entity.setPropertyType(propertyTypeRepository.findById(dto.getPropertyTypeId()).get());
        } else {
            return null;
        }

        if (energyClassificationRepository.findById(dto.getEnergyClassificationId()).isPresent()) {
            entity.setEnergyClassification(energyClassificationRepository.findById(dto.getEnergyClassificationId()).get());
        } else {
            return null;
        }

        return new RentalPropertyDto(rentalPropertyRepository.save(entity));
    }
}