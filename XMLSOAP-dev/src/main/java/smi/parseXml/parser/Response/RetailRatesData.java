package smi.parseXml.parser.Response;

import lombok.Data;

@Data
public class RetailRatesData {
    public String retailRateType;
    public String retailRateReciprocal;
    public Long buyRate;
    public Long sellRate;
}
