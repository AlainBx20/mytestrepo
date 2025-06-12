package smi.parseXml.parser.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "COURS_JOURS_DEVISE")
public class CoursJoursDevise {
    @EmbeddedId
    private CoursJoursDeviseId id;

    @Column(name = "COURS_ACHAT", precision = 38, scale = 6)
    private BigDecimal coursAchat;

    @Column(name = "COURS_VENTE", precision = 38, scale = 6)
    private BigDecimal coursVente;

    @Column(name = "DATE_VALEUR")
    private LocalDate dateValeur;

    @Column(name = "DATE_MAJ")
    private LocalDate dateMaj;

    @Size(max = 1)
    @Column(name = "COURS_VALIDE", length = 1)
    private String coursValide;

}