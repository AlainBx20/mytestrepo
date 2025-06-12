package smi.parseXml.parser.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DEVISE")
public class Devise {
    @Id
    @Column(name = "CODE_DEVISE", nullable = false)
    private Long id;

    @Size(max = 3)
    @NotNull
    @Column(name = "SIGLE_DEVISE", nullable = false, length = 3)
    private String sigleDevise;

    @Size(max = 35)
    @Column(name = "LIB_DEVISE", length = 35)
    private String libDevise;

    @Column(name = "UNITE_DEVISE")
    private Short uniteDevise;

    @Column(name = "DECIMAL_DEVISE")
    private int decimalDevise;

    @Size(max = 1)
    @Column(name = "QUOTE", length = 1)
    private String quote;

    @Size(max = 3)
    @Column(name = "CODE_ISO", length = 3)
    private String codeIso;

}