package com.example.fiskekort;


import java.util.Date;

public class FishingCard {
    private String cardNumber;
    private String startDate;
    private String finishDate;
    private User owner;

    public FishingCard(User owner, String cardNumber, String startDate, String finishDate) {
        this.cardNumber = cardNumber;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.owner = owner;
    }

    public FishingCard(String cardNumber, String startDate, String finishDate) {
                this.cardNumber=cardNumber;
                this.startDate=startDate;
                this.finishDate=finishDate;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
