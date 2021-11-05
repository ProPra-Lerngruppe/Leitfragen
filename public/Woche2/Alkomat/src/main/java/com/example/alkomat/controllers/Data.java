package com.example.alkomat.controllers;

public record Data(int gewicht, String geschlecht, int wodka, int whiskey, int wein, int bier, int korn) {


    public double berechneBlutalkoholWert() {
        double reductionsfactor = 0;
        String firstLetter = "" + geschlecht.charAt(0);

        if(firstLetter.equals("m") || firstLetter.equals("M")){
            reductionsfactor = 0.68;
        }
        else{
            reductionsfactor = 0.55;
        }

        int alkMl = wodka + whiskey + wein + bier + korn;

        return alkMl/(gewicht*reductionsfactor);
    }
}
