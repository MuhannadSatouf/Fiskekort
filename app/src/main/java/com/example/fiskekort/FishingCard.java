package com.example.fiskekort;

public class FishingCard {
    private String cardNumber;
    private String startDate;
    private String finishDate;
  //  private User owner;
    private LocationType locationType;
    private Municipality municipality;
    private Lake lake;

    public FishingCard(User owner, String cardNumber, String startDate, String finishDate) {
        this.cardNumber = cardNumber;
        this.startDate = startDate;
        this.finishDate = finishDate;
       // this.owner = owner;   // is owner needed at all? it will be on his phone...
    }

    public FishingCard(String cardNumber, String startDate, String finishDate) {   //please, add areas where user is allowed to do fishing
                this.cardNumber=cardNumber;
                this.startDate=startDate;
                this.finishDate=finishDate;

    }

    public FishingCard(String startDate, String finishDate, LocationType locationType, Municipality municipality, Lake lake){
        setStartDate(startDate);
        setFinishDate(finishDate);
        setLocationType(locationType);
        setMunicipality(municipality);
        setLake(lake);
    }

    public FishingCard(String startDate, String finishDate, LocationType locationType, Municipality municipality){
        new FishingCard(startDate, finishDate, locationType, municipality, null);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Lake getLake() {
        return lake;
    }

    public void setLake(Lake lake) {
        this.lake = lake;
    }
}
