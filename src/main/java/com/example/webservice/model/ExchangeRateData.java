package com.example.webservice.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ExchangeRateData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRateData {
    private String currency;
    private String currencyNumber;
    private String spotRateReciprocal;
    private String spotRate;
    private String userRate;
    private String numberOfRetailRates;
    
    @XmlElementWrapper(name = "RetailRatesData")
    @XmlElement(name = "RetailRatesData")
    private List<RetailRateData> retailRatesData;

    // Getters and Setters
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyNumber() {
        return currencyNumber;
    }

    public void setCurrencyNumber(String currencyNumber) {
        this.currencyNumber = currencyNumber;
    }

    public String getSpotRateReciprocal() {
        return spotRateReciprocal;
    }

    public void setSpotRateReciprocal(String spotRateReciprocal) {
        this.spotRateReciprocal = spotRateReciprocal;
    }

    public String getSpotRate() {
        return spotRate;
    }

    public void setSpotRate(String spotRate) {
        this.spotRate = spotRate;
    }

    public String getUserRate() {
        return userRate;
    }

    public void setUserRate(String userRate) {
        this.userRate = userRate;
    }

    public String getNumberOfRetailRates() {
        return numberOfRetailRates;
    }

    public void setNumberOfRetailRates(String numberOfRetailRates) {
        this.numberOfRetailRates = numberOfRetailRates;
    }

    public List<RetailRateData> getRetailRatesData() {
        return retailRatesData;
    }

    public void setRetailRatesData(List<RetailRateData> retailRatesData) {
        this.retailRatesData = retailRatesData;
    }
} 