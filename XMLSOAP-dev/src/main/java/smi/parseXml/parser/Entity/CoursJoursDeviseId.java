package smi.parseXml.parser.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CoursJoursDeviseId implements java.io.Serializable {
    private static final long serialVersionUID = -8263052744835022626L;
    @NotNull
    @Column(name = "DATE_COURS", nullable = false)
    private LocalDate dateCours;

    @NotNull
    @Column(name = "CODE_DEVISE", nullable = false)
    private Long codeDevise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CoursJoursDeviseId entity = (CoursJoursDeviseId) o;
        return Objects.equals(this.dateCours, entity.dateCours) &&
                Objects.equals(this.codeDevise, entity.codeDevise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCours, codeDevise);
    }

}