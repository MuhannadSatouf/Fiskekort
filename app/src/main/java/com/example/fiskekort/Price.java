package com.example.fiskekort;

public class Price {
    double price;

    public double getPrice(Duration period, LocationType level){
        switch (period) {
            case ONE_DAY:
                price = 80;
                break;
            case THREE_MONTHS:
                if (level == LocationType.MUNICIPALITY) {
                    price = 250;
                } else {
                    price = 150;
                }
                break;
            case SIX_MONTHS:
                if (level == LocationType.MUNICIPALITY) {
                    price = 400;
                } else {
                    price = 250;
                }
                break;
            default:
                if (level == LocationType.MUNICIPALITY) {
                    price = 600;
                } else {
                    price = 400;
                }
                break;

        }
        return price;
    }
}
