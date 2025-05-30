package fr.esgi.rent.api;

import fr.esgi.rent.dto.RentalPropertyDto;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RentalPropertyResource {

    @Autowired
    private RentalPropertyRepository rentalPropertyRepository;

    @GetMapping("/rental-properties")
    public List<RentalPropertyDto> getRentalProperties() {
        return rentalPropertyRepository.findAll().stream().map(RentalPropertyDto::new).collect(Collectors.toList());
    }
}