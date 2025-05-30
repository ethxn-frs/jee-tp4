package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.RentalPropertyDto;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class RentalPropertyResourceTest {

    @Mock
    private RentalPropertyRepository rentalPropertyRepository;

    @InjectMocks
    private RentalPropertyResource rentalPropertyResource;

    private RentalPropertyEntity testProperty;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testProperty = new RentalPropertyEntity();
        testProperty.setId(UUID.randomUUID());
        testProperty.setDescription("Loft spacieux avec vue mer");
        testProperty.setTown("Nice");
        testProperty.setAddress("8 avenue du Port");
        testProperty.setRentAmount(1250.50);
        testProperty.setSecurityDepositAmount(2500.00);
        testProperty.setArea(72.5);
        testProperty.setNumberOfBedrooms((byte) 2);
    }

    @Test
    @DisplayName("Retourne une liste contenant un bien (DTO)")
    void testGetRentalProperties_shouldReturnListOfDto() {
        when(rentalPropertyRepository.findAll()).thenReturn(List.of(testProperty));

        List<RentalPropertyDto> result = rentalPropertyResource.getRentalProperties();

        Assertions.assertEquals(1, result.size());
        RentalPropertyDto dto = result.get(0);
        Assertions.assertEquals("Loft spacieux avec vue mer", dto.getDescription());
        Assertions.assertEquals("Nice", dto.getTown());
        Assertions.assertEquals("8 avenue du Port", dto.getAddress());
        Assertions.assertEquals(1250.50, dto.getRentAmount());
        Assertions.assertEquals(2500.00, dto.getSecurityDepositAmount());
        Assertions.assertEquals(72.5, dto.getArea());
        Assertions.assertEquals(2, dto.getNumberOfBedrooms());
    }

    @Test
    @DisplayName("Retourne une liste vide quand aucun bien n'est en base")
    void testGetRentalProperties_shouldReturnEmptyList() {
        when(rentalPropertyRepository.findAll()).thenReturn(Collections.emptyList());

        List<RentalPropertyDto> result = rentalPropertyResource.getRentalProperties();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Lève une exception quand le repository échoue")
    void testGetRentalProperties_shouldThrowException() {
        when(rentalPropertyRepository.findAll()).thenThrow(new RuntimeException("Échec de la base de données"));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            rentalPropertyResource.getRentalProperties();
        });

        Assertions.assertEquals("Échec de la base de données", exception.getMessage());
    }

    @Test
    @DisplayName("GET by ID retourne un DTO si trouvé")
    void testGetRentalPropertyById_shouldReturnDto() {
        UUID id = testProperty.getId();
        when(rentalPropertyRepository.findById(id)).thenReturn(Optional.of(testProperty));

        RentalPropertyDto result = rentalPropertyResource.getRentalPropertyById(id);

        Assertions.assertEquals("Loft spacieux avec vue mer", result.getDescription());
        Assertions.assertEquals("Nice", result.getTown());
    }

    @Test
    @DisplayName("GET by ID retourne null si non trouvé")
    void testGetRentalPropertyById_shouldReturn404() {
        UUID id = UUID.randomUUID();
        when(rentalPropertyRepository.findById(id)).thenReturn(Optional.empty());

        RentalPropertyDto result = rentalPropertyResource.getRentalPropertyById(id);

        Assertions.assertNull(result);
    }
}
