package smi.parseXml.parser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import smi.parseXml.parser.Entity.CoursJoursDeviseId;
import smi.parseXml.parser.Entity.Devise;

import java.util.List;


@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long>  {


    @Query("SELECT d FROM Devise d WHERE d.quote = 'O'")
    List<Devise> findAllWhereQuoteIsO();


    List<Devise> findByQuote(String quote);



}
