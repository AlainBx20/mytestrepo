package smi.parseXml.parser.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@AllArgsConstructor
public class ReadDataService {


    public String readFromUrl(String url) {

        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000); // 6-digit number
        String uid = "IBANSYS" + randomNumber;
        String date  = LocalDateTime.now().toString();


        RestTemplate restTemplate = new RestTemplate();

        // SOAP XML as a String
        String soapXml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                        "xmlns:mws=\"http://www.qnb.com.qa/mwSchema\">\n" +
                        "    <soapenv:Header/>\n" +
                        "    <soapenv:Body>\n" +
                        "        <mws:RERCERequest>\n" +
                        "            <Header>\n" +
                        "                <uid>"+uid+"</uid>\n" +
                        "                <requestorID>ATUN</requestorID>\n" +
                        "                <locale>EN</locale>\n" +
                        "                <branchCode>0010</branchCode>\n" +
                        "                <host>EQUATION_TUNISIA.qnb.bnk</host>\n" +
                        "                <unit>TUD</unit>\n" +
                        "                <date>"+date+"</date>\n" +
                        "                <channelID>QBO</channelID>\n" +
                        "            </Header>\n" +
                        "            <Data/>\n" +
                        "        </mws:RERCERequest>\n" +
                        "    </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML); // Very important for SOAP
        headers.add("SOAPAction", ""); // Optional, depends on your service

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        System.out.println("Response:");
        System.out.println(response.getBody());

        return response.getBody();
    }


}

