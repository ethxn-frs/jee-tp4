package fr.esgi.rent.dto;

import java.util.UUID;

public class RentalPropertyCreateDto {

    private String description;
    private String town;
    private String address;
    private double rentAmount;
    private double securityDepositAmount;
    private double area;
    private byte numberOfBedrooms;
    private Short floorNumber;
    private Short numberOfFloors;
    private short constructionYear;
    private boolean hasElevator;
    private boolean hasIntercom;
    private boolean hasBalcony;
    private boolean hasParkingSpace;

    private UUID propertyTypeId;
    private UUID energyClassificationId;


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

    public UUID getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(UUID propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public UUID getEnergyClassificationId() {
        return energyClassificationId;
    }

    public void setEnergyClassificationId(UUID energyClassificationId) {
        this.energyClassificationId = energyClassificationId;
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
