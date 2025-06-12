package smi.parseXml.parser.Response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Data {
    @JacksonXmlElementWrapper(useWrapping = false) // because XML is flat list
    @JacksonXmlProperty(localName = "ExchangeRateData")
    public List<ExchangeRateData> ExchangeRateData;
}
