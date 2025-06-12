package smi.parseXml.parser.Service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smi.parseXml.parser.Entity.Devise;
import smi.parseXml.parser.Repository.DeviseRepository;
import smi.parseXml.parser.Response.Envelope;
import smi.parseXml.parser.Response.ExchangeRateData;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessingDataService {


    private final DeviseRepository deviseRepository;
    private final ParsingService parsingService;
    private final ReadDataService readDataService;
    private final PersistanceServie persistanceService;


    @Transactional
    public void process(String url, long conversionRate){
        //get the xml response from the url
        String xml = readDataService.readFromUrl(url);
        System.out.println("xml response from the url");
        System.out.println(xml);

     

        //map the xml to a java object using xml mapper
        Envelope response = parsingService.parseXml(xml);

        //get all the devises with a quote that equals to 'O'
        List<Devise> devisesWithOQuote = deviseRepository.findByQuote("O");

        System.out.println("les cours devise de la table devise");
        devisesWithOQuote.forEach(devise -> {
            System.out.println(devise.getId());
        });

        //get all the ids of these devises
        Set<Long> deviseIds = devisesWithOQuote.stream()
                .map(Devise::getId)
                .collect(Collectors.toSet());

        //get all the exchange rate data from the response

        List<ExchangeRateData> exchangeRateData = response.Body.RERCEResponse.Data.ExchangeRateData;
        System.out.println("list des echange rate data");
        exchangeRateData.forEach(exchangeRateData1 -> {
            System.out.println(exchangeRateData1.currencyNumber);
        });
        //filter the exchange rate with the devises that have a quota equals to 'O'
        List<ExchangeRateData> filteredExchangeRateData = exchangeRateData.stream()
                .filter(exchangeRate -> deviseIds.contains(exchangeRate.getCurrencyNumber()))
                .toList();
        System.out.println("list des echange rate data filtre");
        filteredExchangeRateData.forEach(exchangeRateData1 -> {
            System.out.println(exchangeRateData1.currencyNumber);
        });
        //persist each cour in a separate service
        filteredExchangeRateData.forEach(exchangeRateData1 -> {
            persistanceService.persistCourJour(exchangeRateData1, conversionRate);
        });



    }

//    private int getDecimalsForDevise(Long currencyNumber) {
//
//    }








}
