package com.example.webservice.controller;

import com.example.webservice.model.ExchangeRateResponse;
import com.example.webservice.model.ExchangeRateData;
import com.example.webservice.model.RetailRateData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ExchangeRateController {

    @GetMapping(value = "/exchange-rates", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getExchangeRates() {
        // Generate UID with IBAN prefix and 6 random digits
        String uid = "IBAN" + String.format("%06d", new Random().nextInt(1000000));
        
        // Format date as required: 2022-12-28T11:20:01.000+03:00
        String date = OffsetDateTime.now()
                .withOffsetSameInstant(ZoneOffset.of("+03:00"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        
        // Create the SOAP envelope XML with the exact structure requested
        String soapXml = 
                        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                        "    <soapenv:Body>\n" +
                        "        <ns1:RERCEResponse xmlns:ns1=\"http://www.qnb.com.qa/mwSchema\">\n" +
                        "            <Header>\n" +
                        "                <uid>" + uid + "</uid>\n" +
                        "                <requestorID>ATUN</requestorID>\n" +
                        "                <locale>EN</locale>\n" +
                        "                <branchCode>0010</branchCode>\n" +
                        "                <host>EQUATION_TUNISIA.qnb.bnk</host>\n" +
                        "                <unit>TUD</unit>\n" +
                        "                <date>" + date + "</date>\n" +
                        "                <channelID>QBO</channelID>\n" +
                        "            </Header>\n" +
                        "            <Data>\n" + 
                        generateExchangeRateDataXml() +
                        "            </Data>\n" +
                        "            <Result>\n" +
                        "                <code>0000</code>\n" +
                        "            </Result>\n" +
                        "        </ns1:RERCEResponse>\n" +
                        "    </soapenv:Body>\n" +
                        "</soapenv:Envelope>";
        
        return ResponseEntity.ok(soapXml);
    }
    
    private String generateExchangeRateDataXml() {
        StringBuilder xml = new StringBuilder();
        
        // Add USD data
        xml.append(createExchangeRateDataXml("USD", "840", "Y", "2965850000", "2965850000", "4",
                createRetailRatesXml("CSH", "Y", "2921000000", "2999000000"),
                createRetailRatesXml("GOV", "Y", "1891100000", "1928900000"),
                createRetailRatesXml("REM", "Y", "2901500000", "2977300000"),
                createRetailRatesXml("STF", "Y", "2960000000", "2960000000")));

        // Add EUR data
        xml.append(createExchangeRateDataXml("EUR", "978", "Y", "3402400000", "3402400000", "4",
                createRetailRatesXml("CSH", "Y", "3337000000", "3427000000"),
                createRetailRatesXml("GOV", "Y", "2157900000", "2201100000"),
                createRetailRatesXml("REM", "Y", "3318500000", "3405700000"),
                createRetailRatesXml("STF", "Y", "3382000000", "3382000000")));
        
        // Add DKK data
        xml.append(createExchangeRateDataXml("DKK", "208", "Y", "456134000", "456134000", "4",
                createRetailRatesXml("CSH", "Y", "442870000", "453380000"),
                createRetailRatesXml("GOV", "Y", "286547000", "292335000"),
                createRetailRatesXml("REM", "Y", "444904000", "456422000"),
                createRetailRatesXml("STF", "Y", "448120000", "448120000")));
        
        // Add EGP data
        xml.append(createExchangeRateDataXml("EGP", "818", "Y", "55108000", "55108000", "3",
                createRetailRatesXml("GOV", "Y", "68632700", "68932700"),
                createRetailRatesXml("REM", "Y", "68632700", "68932700"),
                createRetailRatesXml("STF", "Y", "68632700", "68932700")));
        
        // Add GBP data
        xml.append(createExchangeRateDataXml("GBP", "826", "Y", "4031500000", "4031500000", "4",
                createRetailRatesXml("CSH", "Y", "3962000000", "4068000000"),
                createRetailRatesXml("GOV", "Y", "2959600000", "3019300000"),
                createRetailRatesXml("REM", "Y", "3940000000", "4040800000"),
                createRetailRatesXml("STF", "Y", "4015000000", "4015000000")));
        
        // Add GLD data
        xml.append(createExchangeRateDataXml("GLD", "848", "Y", "2419112250000", "2411831200000", "1",
                createRetailRatesXml("REM", "N", "661700000000", "662500000000")));
        
        // Add HKD data
        xml.append(createExchangeRateDataXml("HKD", "344", "Y", "49641000", "49641000", "3",
                createRetailRatesXml("GOV", "Y", "49434200", "49534200"),
                createRetailRatesXml("REM", "Y", "49434200", "49534200"),
                createRetailRatesXml("STF", "Y", "49434200", "49534200")));
        
        // Add INR data
        xml.append(createExchangeRateDataXml("INR", "356", "Y", "5827000", "5827000", "3",
                createRetailRatesXml("GOV", "Y", "9666500", "9696500"),
                createRetailRatesXml("REM", "Y", "9666500", "9696500"),
                createRetailRatesXml("STF", "Y", "6030000", "6030000")));
        
        // Add JOD data
        xml.append(createExchangeRateDataXml("JOD", "400", "Y", "543595000", "543595000", "3",
                createRetailRatesXml("GOV", "Y", "540845100", "543745100"),
                createRetailRatesXml("REM", "Y", "540845100", "543745100"),
                createRetailRatesXml("STF", "Y", "540845100", "543745100")));
        
        // Add JPY data
        xml.append(createExchangeRateDataXml("JPY", "392", "Y", "20712950", "20712950", "4",
                createRetailRatesXml("CSH", "Y", "20266000", "20812000"),
                createRetailRatesXml("GOV", "Y", "15761500", "16080000"),
                createRetailRatesXml("REM", "Y", "20148100", "20668400"),
                createRetailRatesXml("STF", "Y", "20539000", "20539000")));
        
        // Add KWD data
        xml.append(createExchangeRateDataXml("KWD", "414", "Y", "9669650000", "9669650000", "4",
                createRetailRatesXml("CSH", "Y", "9532000000", "9789000000"),
                createRetailRatesXml("GOV", "Y", "6262500000", "6389100000"),
                createRetailRatesXml("REM", "Y", "9459900000", "9703700000"),
                createRetailRatesXml("STF", "Y", "9660000000", "9660000000")));
        
        // Add LYD data
        xml.append(createExchangeRateDataXml("LYD", "434", "Y", "543550000", "543550000", "3",
                createRetailRatesXml("CSH", "Y", "534000000", "548000000"),
                createRetailRatesXml("REM", "Y", "527100000", "543500000"),
                createRetailRatesXml("STF", "Y", "541000000", "541000000")));
        
        // Add MAD data
        xml.append(createExchangeRateDataXml("MAD", "504", "Y", "323460000", "323460000", "3",
                createRetailRatesXml("GOV", "Y", "48113100", "48313100"),
                createRetailRatesXml("REM", "Y", "48113100", "48313100"),
                createRetailRatesXml("STF", "Y", "48113100", "48313100")));
        
        // Add NOK data
        xml.append(createExchangeRateDataXml("NOK", "578", "Y", "294930500", "294930500", "4",
                createRetailRatesXml("CSH", "Y", "282410000", "289120000"),
                createRetailRatesXml("GOV", "Y", "252699000", "257805000"),
                createRetailRatesXml("REM", "Y", "288715000", "296162000"),
                createRetailRatesXml("STF", "Y", "285770000", "285770000")));
        
        // Add NZD data
        xml.append(createExchangeRateDataXml("NZD", "554", "Y", "299047000", "299047000", "3",
                createRetailRatesXml("GOV", "Y", "285120000", "286120000"),
                createRetailRatesXml("REM", "Y", "285120000", "286120000"),
                createRetailRatesXml("STF", "Y", "315330000", "315330000")));
        
        // Add OMR data
        xml.append(createExchangeRateDataXml("OMR", "512", "N", "1000000000", "1000000000", "3",
                createRetailRatesXml("GOV", "N", "1000000000", "1000000000"),
                createRetailRatesXml("REM", "N", "1000000000", "1000000000"),
                createRetailRatesXml("STF", "N", "1000000000", "1000000000")));
        
        // Add PKR data
        xml.append(createExchangeRateDataXml("PKR", "586", "Y", "3685000", "3685000", "3",
                createRetailRatesXml("GOV", "Y", "6329300", "6349300"),
                createRetailRatesXml("REM", "Y", "6329300", "6349300"),
                createRetailRatesXml("STF", "Y", "6329300", "6349300")));
        
        // Add QAR data
        xml.append(createExchangeRateDataXml("QAR", "634", "Y", "813730000", "813730000", "4",
                createRetailRatesXml("CSH", "Y", "801500000", "823000000"),
                createRetailRatesXml("GOV", "Y", "515030000", "525440000"),
                createRetailRatesXml("REM", "Y", "797060000", "816080000"),
                createRetailRatesXml("STF", "Y", "812300000", "812300000")));
        
        // Add SAR data
        xml.append(createExchangeRateDataXml("SAR", "682", "Y", "790775000", "790775000", "4",
                createRetailRatesXml("CSH", "Y", "778700000", "799700000"),
                createRetailRatesXml("GOV", "Y", "499920000", "510010000"),
                createRetailRatesXml("REM", "Y", "773640000", "793800000"),
                createRetailRatesXml("STF", "Y", "789200000", "789200000")));
        
        // Add SEK data
        xml.append(createExchangeRateDataXml("SEK", "752", "Y", "310525000", "310525000", "4",
                createRetailRatesXml("CSH", "Y", "303300000", "311500000"),
                createRetailRatesXml("GOV", "Y", "228380000", "233000000"),
                createRetailRatesXml("REM", "Y", "302410000", "310230000"),
                createRetailRatesXml("STF", "Y", "307400000", "307400000")));
        
        // Add SGD data
        xml.append(createExchangeRateDataXml("SGD", "702", "Y", "301795000", "301795000", "3",
                createRetailRatesXml("GOV", "Y", "256222100", "257222100"),
                createRetailRatesXml("REM", "Y", "256222100", "257222100"),
                createRetailRatesXml("STF", "Y", "256222100", "257222100")));
        
        // Add SYP data
        xml.append(createExchangeRateDataXml("SYP", "760", "Y", "2208000", "2208000", "3",
                createRetailRatesXml("GOV", "Y", "7485400", "7515400"),
                createRetailRatesXml("REM", "Y", "7485400", "7515400"),
                createRetailRatesXml("STF", "Y", "7485400", "7515400")));
        
        // Add TND data
        xml.append(createExchangeRateDataXml("TND", "788", "N", "1000000000", "1000000000", "4",
                createRetailRatesXml("CSH", "N", "1000000000", "1000000000"),
                createRetailRatesXml("GOV", "Y", "1000000000", "1000000000"),
                createRetailRatesXml("REM", "Y", "1000000000", "1000000000"),
                createRetailRatesXml("STF", "Y", "1000000000", "1000000000")));
        
        // Add USD data
        xml.append(createExchangeRateDataXml("USD", "840", "Y", "2965850000", "2965850000", "4",
                createRetailRatesXml("CSH", "Y", "2921000000", "2999000000"),
                createRetailRatesXml("GOV", "Y", "1891100000", "1928900000"),
                createRetailRatesXml("REM", "Y", "2901500000", "2977300000"),
                createRetailRatesXml("STF", "Y", "2960000000", "2960000000")));
        
        // Add XAG data
        xml.append(createExchangeRateDataXml("XAG", "841", "Y", "47144500000", "47071600000", "1",
                createRetailRatesXml("REM", "Y", "1000000000", "0")));
        
        // Add ZAR data
        xml.append(createExchangeRateDataXml("ZAR", "710", "Y", "37294000", "37294000", "3",
                createRetailRatesXml("GOV", "Y", "54857100", "55857100"),
                createRetailRatesXml("REM", "Y", "54857100", "55857100"),
                createRetailRatesXml("STF", "Y", "54857100", "55857100")));
        
        return xml.toString();
    }

    private String createExchangeRateDataXml(String currency, String currencyNumber, String spotRateReciprocal,
                                           String spotRate, String userRate, String numberOfRetailRates,
                                           String... retailRates) {
        StringBuilder xml = new StringBuilder();
        xml.append("                <ExchangeRateData>\n");
        xml.append("                    <currency>").append(currency).append("</currency>\n");
        xml.append("                    <currencyNumber>").append(currencyNumber).append("</currencyNumber>\n");
        xml.append("                    <spotRateReciprocal>").append(spotRateReciprocal).append("</spotRateReciprocal>\n");
        xml.append("                    <spotRate>").append(spotRate).append("</spotRate>\n");
        xml.append("                    <userRate>").append(userRate).append("</userRate>\n");
        xml.append("                    <numberOfRetailRates>").append(numberOfRetailRates).append("</numberOfRetailRates>\n");
        
        for (String rate : retailRates) {
            xml.append(rate);
        }
        
        xml.append("                </ExchangeRateData>\n");
        return xml.toString();
    }

    private String createRetailRatesXml(String type, String reciprocal, String buyRate, String sellRate) {
        StringBuilder xml = new StringBuilder();
        xml.append("                    <RetailRatesData>\n");
        xml.append("                        <retailRateType>").append(type).append("</retailRateType>\n");
        xml.append("                        <retailRateReciprocal>").append(reciprocal).append("</retailRateReciprocal>\n");
        xml.append("                        <buyRate>").append(buyRate).append("</buyRate>\n");
        xml.append("                        <sellRate>").append(sellRate).append("</sellRate>\n");
        xml.append("                    </RetailRatesData>\n");
        return xml.toString();
    }
} 
