package smi.parseXml.parser.Response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;
@Data
public class ExchangeRateData {
    public String currency;
    public Long currencyNumber;
    public String spotRateReciprocal;
    public Long spotRate;
    public Long userRate;
    public Long numberOfRetailRates;
    @JacksonXmlElementWrapper(useWrapping = false) // because XML is flat list
    @JacksonXmlProperty(localName = "RetailRatesData")
    public List<RetailRatesData> RetailRatesData;
}
