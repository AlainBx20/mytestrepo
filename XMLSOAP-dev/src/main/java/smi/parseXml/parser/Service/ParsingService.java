package smi.parseXml.parser.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import smi.parseXml.parser.Response.Envelope;

@Service
public class ParsingService {




    public Envelope parseXml(String xml) {

        // envelope is the root object of the xml file
        Envelope envelope;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            envelope = xmlMapper.readValue(xml, Envelope.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return envelope;
    }









}
