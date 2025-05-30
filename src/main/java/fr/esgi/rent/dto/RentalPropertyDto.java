package fr.esgi.rent.dto;

import fr.esgi.rent.domain.RentalPropertyEntity;

import java.util.UUID;

public class RentalPropertyDto {

    private UUID id;
    private String description;
    private String town;
    private String address;
    private PropertyTypeDto propertyType;
    private double rentAmount;
    private double securityDepositAmount;
    private double area;
    private byte numberOfBedrooms;
    private Short floorNumber;
    private Short numberOfFloors;
    private short constructionYear;
    private EnergyClassificationDto energyClassification;
    private boolean hasElevator;
    private boolean hasIntercom;
    private boolean hasBalcony;
    private boolean hasParkingSpace;

    public RentalPropertyDto(RentalPropertyEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.town = entity.getTown();
        this.address = entity.getAddress();
        this.propertyType = entity.getPropertyType() != null ? new PropertyTypeDto(entity.getPropertyType()) : null;
        this.rentAmount = entity.getRentAmount();
        this.securityDepositAmount = entity.getSecurityDepositAmount();
        this.area = entity.getArea();
        this.numberOfBedrooms = entity.getNumberOfBedrooms();
        this.floorNumber = entity.getFloorNumber();
        this.numberOfFloors = entity.getNumberOfFloors();
        this.constructionYear = entity.getConstructionYear();
        this.energyClassification = entity.getEnergyClassification() != null ? new EnergyClassificationDto(entity.getEnergyClassification()) : null;
        this.hasElevator = entity.isHasElevator();
        this.hasIntercom = entity.isHasIntercom();
        this.hasBalcony = entity.isHasBalcony();
        this.hasParkingSpace = entity.isHasParkingSpace();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PropertyTypeDto getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyTypeDto propertyType) {
        this.propertyType = propertyType;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public double getSecurityDepositAmount() {
        return securityDepositAmount;
    }

    public void setSecurityDepositAmount(double securityDepositAmount) {
        this.securityDepositAmount = securityDepositAmount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public byte getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(byte numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public Short getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Short floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Short getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Short numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public short getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(short constructionYear) {
        this.constructionYear = constructionYear;
    }

    public EnergyClassificationDto getEnergyClassification() {
        return energyClassification;
    }

    public void setEnergyClassification(EnergyClassificationDto energyClassification) {
        this.energyClassification = energyClassification;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public boolean isHasIntercom() {
        return hasIntercom;
    }

    public void setHasIntercom(boolean hasIntercom) {
        this.hasIntercom = hasIntercom;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public boolean isHasParkingSpace() {
        return hasParkingSpace;
    }

    public void setHasParkingSpace(boolean hasParkingSpace) {
        this.hasParkingSpace = hasParkingSpace;
    }
}
