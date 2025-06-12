package smi.parseXml.parser.Service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import smi.parseXml.parser.Entity.CoursJoursDevise;
import smi.parseXml.parser.Entity.CoursJoursDeviseId;
import smi.parseXml.parser.Entity.Devise;
import smi.parseXml.parser.Repository.CoursDeviseRepository;
import smi.parseXml.parser.Repository.DeviseRepository;
import smi.parseXml.parser.Response.ExchangeRateData;
import smi.parseXml.parser.Response.RetailRatesData;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PersistanceServie {

    private final CoursDeviseRepository coursDeviseRepository;

    private final DeviseRepository deviseRepository;

    public void persistCourJour(ExchangeRateData exchangeRate, long conversionRate) {


        List<RetailRatesData> retailRatesDataList = exchangeRate.RetailRatesData;
        Optional<RetailRatesData> retailRateOpt = retailRatesDataList.stream()
                .filter(data -> "CSH".equals(data.retailRateType))
                .findFirst();

        Optional<Devise> deviseOpt = deviseRepository.findById(exchangeRate.currencyNumber);

        if (retailRateOpt.isEmpty()) {
            System.out.println("RetailRatesData with type 'CSH' not found.");
            return;
        }

        if (deviseOpt.isEmpty()) {
            System.out.println("Devise not found for currency number: " + exchangeRate.currencyNumber);
            return;
        }

        RetailRatesData retailRate = retailRateOpt.get();
        Devise devise = deviseOpt.get();
        int decimalPlaces = devise.getDecimalDevise(); // assuming it's an int

        BigDecimal achat = BigDecimal.valueOf(retailRate.sellRate)
                .divide(BigDecimal.valueOf(conversionRate), decimalPlaces, RoundingMode.HALF_UP);
        BigDecimal vente = BigDecimal.valueOf(retailRate.buyRate)
                .divide(BigDecimal.valueOf(conversionRate), decimalPlaces, RoundingMode.HALF_UP);

        CoursJoursDeviseId id = new CoursJoursDeviseId();
        id.setDateCours(LocalDate.now());
        id.setCodeDevise(exchangeRate.currencyNumber);

        CoursJoursDevise coursJoursDevise = new CoursJoursDevise();
        coursJoursDevise.setId(id);
        coursJoursDevise.setCoursAchat(achat);
        coursJoursDevise.setCoursVente(vente);
        coursJoursDevise.setDateValeur(LocalDate.now());
        coursJoursDevise.setDateMaj(LocalDate.now());
        coursJoursDevise.setCoursValide("V");

        coursDeviseRepository.save(coursJoursDevise);
    }










}
