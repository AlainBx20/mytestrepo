package com.example.webservice.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "RERCEResponse", namespace = "http://www.qnb.com.qa/mwSchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRateResponse {
    
    @XmlElement(name = "Header")
    private Header header;
    
    @XmlElement(name = "Data")
    private Data data;
    
    @XmlElement(name = "Result")
    private Result result;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        private String uid;
        private String requestorID;
        private String locale;
        private String branchCode;
        private String host;
        private String unit;
        private String date;
        private String channelID;

        // Getters and Setters
        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getRequestorID() {
            return requestorID;
        }

        public void setRequestorID(String requestorID) {
            this.requestorID = requestorID;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getChannelID() {
            return channelID;
        }

        public void setChannelID(String channelID) {
            this.channelID = channelID;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Data {
        @XmlElement(name = "ExchangeRateData")
        private List<ExchangeRateData> exchangeRateData;

        public List<ExchangeRateData> getExchangeRateData() {
            return exchangeRateData;
        }

        public void setExchangeRateData(List<ExchangeRateData> exchangeRateData) {
            this.exchangeRateData = exchangeRateData;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Result {
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
} 