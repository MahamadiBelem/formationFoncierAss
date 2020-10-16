package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import bf.agriculture.dgfomr.domain.enumeration.RegimeFormateur;

/**
 * A FormateurCentre.
 */
@Entity
@Table(name = "formateur_centre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormateurCentre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_etablissement")
    private LocalDate dateEtablissement;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "regime_formateur", nullable = false)
    private RegimeFormateur regimeFormateur;

    @ManyToOne
    @JsonIgnoreProperties(value = "formateurCentres", allowSetters = true)
    private CentreFormation formateurCentreFormation;

    @ManyToOne
    @JsonIgnoreProperties(value = "formateurCentres", allowSetters = true)
    private Formateurs formateurCentre;

    @ManyToOne
    @JsonIgnoreProperties(value = "formateurCentres", allowSetters = true)
    private Regime regimecentreformateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEtablissement() {
        return dateEtablissement;
    }

    public FormateurCentre dateEtablissement(LocalDate dateEtablissement) {
        this.dateEtablissement = dateEtablissement;
        return this;
    }

    public void setDateEtablissement(LocalDate dateEtablissement) {
        this.dateEtablissement = dateEtablissement;
    }

    public RegimeFormateur getRegimeFormateur() {
        return regimeFormateur;
    }

    public FormateurCentre regimeFormateur(RegimeFormateur regimeFormateur) {
        this.regimeFormateur = regimeFormateur;
        return this;
    }

    public void setRegimeFormateur(RegimeFormateur regimeFormateur) {
        this.regimeFormateur = regimeFormateur;
    }

    public CentreFormation getFormateurCentreFormation() {
        return formateurCentreFormation;
    }

    public FormateurCentre formateurCentreFormation(CentreFormation centreFormation) {
        this.formateurCentreFormation = centreFormation;
        return this;
    }

    public void setFormateurCentreFormation(CentreFormation centreFormation) {
        this.formateurCentreFormation = centreFormation;
    }

    public Formateurs getFormateurCentre() {
        return formateurCentre;
    }

    public FormateurCentre formateurCentre(Formateurs formateurs) {
        this.formateurCentre = formateurs;
        return this;
    }

    public void setFormateurCentre(Formateurs formateurs) {
        this.formateurCentre = formateurs;
    }

    public Regime getRegimecentreformateur() {
        return regimecentreformateur;
    }

    public FormateurCentre regimecentreformateur(Regime regime) {
        this.regimecentreformateur = regime;
        return this;
    }

    public void setRegimecentreformateur(Regime regime) {
        this.regimecentreformateur = regime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormateurCentre)) {
            return false;
        }
        return id != null && id.equals(((FormateurCentre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormateurCentre{" +
            "id=" + getId() +
            ", dateEtablissement='" + getDateEtablissement() + "'" +
            ", regimeFormateur='" + getRegimeFormateur() + "'" +
            "}";
    }
}
