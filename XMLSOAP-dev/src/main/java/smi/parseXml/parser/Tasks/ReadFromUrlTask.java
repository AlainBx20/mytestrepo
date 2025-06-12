package smi.parseXml.parser.Tasks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smi.parseXml.parser.Service.ProcessingDataService;

@Component
public class ReadFromUrlTask {

    @Autowired
    ProcessingDataService processingDataService;

    @Value("${data.provider.url}")
    private String url;

    @Value("${data.conversion.rate}")
    private String conversionRate;


    @Scheduled(fixedRate=60000) // every day at 07:30 AMcron = "0 30 7 * * *"
    public void readFromUrl() {

        processingDataService.process(url, Long.parseLong(conversionRate));



    }


}
