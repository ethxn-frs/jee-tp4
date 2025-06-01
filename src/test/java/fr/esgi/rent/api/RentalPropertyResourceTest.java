package fr.esgi.rent.api;

import fr.esgi.rent.domain.EnergyClassificationEntity;
import fr.esgi.rent.domain.PropertyTypeEntity;
import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.RentalPropertyCreateDto;
import fr.esgi.rent.dto.RentalPropertyDto;
import fr.esgi.rent.dto.RentalPropertySearchRequest;
import fr.esgi.rent.repository.EnergyClassificationRepository;
import fr.esgi.rent.repository.PropertyTypeRepository;
import fr.esgi.rent.repository.RentalPropertyRepository;
import fr.esgi.rent.services.VelibStationService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RentalPropertyResourceTest {

    @Mock
    private RentalPropertyRepository rentalPropertyRepository;

    @Mock
    private PropertyTypeRepository propertyTypeRepository;

    @Mock
    private EnergyClassificationRepository energyClassificationRepository;

    @InjectMocks
    private RentalPropertyResource rentalPropertyResource;

    private RentalPropertyEntity testProperty;

    @Mock
    private VelibStationService velibStationService;

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

    @Test
    @DisplayName("POST crée une location avec types valides")
    void testCreateRentalProperty_shouldReturnDto() {
        UUID propertyTypeId = UUID.randomUUID();
        UUID energyId = UUID.randomUUID();

        RentalPropertyCreateDto dto = new RentalPropertyCreateDto();
        dto.setDescription("Appartement neuf");
        dto.setTown("Toulouse");
        dto.setAddress("31 avenue de la République");
        dto.setRentAmount(900);
        dto.setSecurityDepositAmount(1800);
        dto.setArea(50);
        dto.setNumberOfBedrooms((byte) 1);
        dto.setFloorNumber((short) 2);
        dto.setNumberOfFloors((short) 4);
        dto.setConstructionYear((short) 2020);
        dto.setHasBalcony(true);
        dto.setHasElevator(true);
        dto.setHasIntercom(false);
        dto.setHasParkingSpace(true);
        dto.setPropertyTypeId(propertyTypeId);
        dto.setEnergyClassificationId(energyId);

        PropertyTypeEntity propertyType = new PropertyTypeEntity();
        propertyType.setId(propertyTypeId);
        propertyType.setDesignation("Appartement");

        EnergyClassificationEntity energy = new EnergyClassificationEntity();
        energy.setId(energyId);
        energy.setDesgination("A");

        RentalPropertyEntity savedEntity = new RentalPropertyEntity(dto);
        savedEntity.setId(UUID.randomUUID());
        savedEntity.setPropertyType(propertyType);
        savedEntity.setEnergyClassification(energy);

        when(propertyTypeRepository.findById(propertyTypeId)).thenReturn(Optional.of(propertyType));
        when(energyClassificationRepository.findById(energyId)).thenReturn(Optional.of(energy));
        when(rentalPropertyRepository.save(any())).thenReturn(savedEntity);

        RentalPropertyDto result = rentalPropertyResource.createRentalProperty(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Appartement neuf", result.getDescription());
        Assertions.assertEquals("Toulouse", result.getTown());
        Assertions.assertEquals("31 avenue de la République", result.getAddress());
        Assertions.assertTrue(result.isHasBalcony());
        Assertions.assertEquals(50, result.getArea());
    }

    @Test
    @DisplayName("POST retourne null si propertyTypeId introuvable")
    void testCreateRentalProperty_shouldReturnNull_ifInvalidPropertyTypeId() {
        RentalPropertyCreateDto dto = new RentalPropertyCreateDto();
        dto.setPropertyTypeId(UUID.randomUUID());
        dto.setEnergyClassificationId(UUID.randomUUID());

        when(propertyTypeRepository.findById(dto.getPropertyTypeId())).thenReturn(Optional.empty());

        RentalPropertyDto result = rentalPropertyResource.createRentalProperty(dto);

        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("POST retourne null si energyClassificationId introuvable")
    void testCreateRentalProperty_shouldReturnNull_ifInvalidEnergyClassificationId() {
        UUID validPropertyTypeId = UUID.randomUUID();
        UUID invalidEnergyId = UUID.randomUUID();

        PropertyTypeEntity propertyType = new PropertyTypeEntity();
        propertyType.setId(validPropertyTypeId);
        propertyType.setDesignation("Maison");

        RentalPropertyCreateDto dto = new RentalPropertyCreateDto();
        dto.setPropertyTypeId(validPropertyTypeId);
        dto.setEnergyClassificationId(invalidEnergyId);

        when(propertyTypeRepository.findById(validPropertyTypeId)).thenReturn(Optional.of(propertyType));
        when(energyClassificationRepository.findById(invalidEnergyId)).thenReturn(Optional.empty());

        RentalPropertyDto result = rentalPropertyResource.createRentalProperty(dto);

        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("POST /search avec nearVelibStations=false retourne tous les biens")
    void testSearchRentalProperties_withoutFilter_returnsAll() {
        when(rentalPropertyRepository.findAll()).thenReturn(List.of(testProperty));

        RentalPropertySearchRequest request = new RentalPropertySearchRequest();
        request.setNearVelibStations(false);

        List<RentalPropertyDto> result = rentalPropertyResource.searchRentalProperties(request);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Nice", result.get(0).getTown());
    }

    @Test
    @DisplayName("POST /search avec nearVelibStations=true retourne que les biens dans les villes Vélib")
    void testSearchRentalProperties_withFilter_returnsOnlyMatchingTowns() {
        RentalPropertyEntity otherProperty = new RentalPropertyEntity();
        otherProperty.setId(UUID.randomUUID());
        otherProperty.setTown("Paris");

        when(rentalPropertyRepository.findAll()).thenReturn(List.of(testProperty, otherProperty));
        when(velibStationService.getVelibTowns(List.of("Nice", "Paris"))).thenReturn(List.of("Nice"));

        RentalPropertySearchRequest request = new RentalPropertySearchRequest();
        request.setNearVelibStations(true);

        List<RentalPropertyDto> result = rentalPropertyResource.searchRentalProperties(request);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Nice", result.get(0).getTown());
    }

    @Test
    @DisplayName("POST /search avec nearVelibStations=true retourne liste vide si aucune ville ne correspond")
    void testSearchRentalProperties_withFilter_noMatch() {
        when(rentalPropertyRepository.findAll()).thenReturn(List.of(testProperty));
        when(velibStationService.getVelibTowns(List.of("Nice"))).thenReturn(List.of());

        RentalPropertySearchRequest request = new RentalPropertySearchRequest();
        request.setNearVelibStations(true);

        List<RentalPropertyDto> result = rentalPropertyResource.searchRentalProperties(request);

        Assertions.assertTrue(result.isEmpty());
    }

}
