package smi.parseXml.parser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smi.parseXml.parser.Entity.CoursJoursDevise;
import smi.parseXml.parser.Entity.CoursJoursDeviseId;



@Repository
public interface CoursDeviseRepository extends JpaRepository<CoursJoursDevise, CoursJoursDeviseId> {
}
