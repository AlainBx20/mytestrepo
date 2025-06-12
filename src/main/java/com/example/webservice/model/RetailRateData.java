package com.example.webservice.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "RetailRatesData")
@XmlAccessorType(XmlAccessType.FIELD)
public class RetailRateData {
    private String retailRateType;
    private String retailRateReciprocal;
    private String buyRate;
    private String sellRate;

    // Getters and Setters
    public String getRetailRateType() {
        return retailRateType;
    }

    public void setRetailRateType(String retailRateType) {
        this.retailRateType = retailRateType;
    }

    public String getRetailRateReciprocal() {
        return retailRateReciprocal;
    }

    public void setRetailRateReciprocal(String retailRateReciprocal) {
        this.retailRateReciprocal = retailRateReciprocal;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getSellRate() {
        return sellRate;
    }

    public void setSellRate(String sellRate) {
        this.sellRate = sellRate;
    }
} 