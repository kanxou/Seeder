package com.growthcapital.seeder.enums;


public enum ContractType {
    MONTHLY("Monthly"),
    ANNUALLY("Annually");

    private final String value;

    ContractType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}