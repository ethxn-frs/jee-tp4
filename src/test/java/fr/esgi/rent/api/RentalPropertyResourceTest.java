package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyEntity;
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
    @DisplayName("Retourne une liste contenant un bien")
    void testGetRentalProperties_shouldReturnList() {
        when(rentalPropertyRepository.findAll()).thenReturn(List.of(testProperty));

        List<RentalPropertyEntity> result = rentalPropertyResource.getRentalProperties();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Loft spacieux avec vue mer", result.getFirst().getDescription());
        Assertions.assertEquals("Nice", result.getFirst().getTown());
        Assertions.assertEquals("8 avenue du Port", result.getFirst().getAddress());
    }

    @Test
    @DisplayName("Retourne une liste vide quand aucun bien n'est en base")
    void testGetRentalProperties_shouldReturnEmptyList() {
        when(rentalPropertyRepository.findAll()).thenReturn(Collections.emptyList());

        List<RentalPropertyEntity> result = rentalPropertyResource.getRentalProperties();

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
}
