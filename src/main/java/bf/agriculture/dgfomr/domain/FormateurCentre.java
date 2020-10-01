package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

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
    @Column(name = "specialite", nullable = false)
    private String specialite;

    @NotNull
    @Column(name = "regime_formateur", nullable = false)
    private String regimeFormateur;

    @ManyToOne
    @JsonIgnoreProperties(value = "formateurCentres", allowSetters = true)
    private CentreFormation formateurCentreFormation;

    @ManyToOne
    @JsonIgnoreProperties(value = "formateurCentres", allowSetters = true)
    private Formateurs formateurCentre;

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

    public String getSpecialite() {
        return specialite;
    }

    public FormateurCentre specialite(String specialite) {
        this.specialite = specialite;
        return this;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getRegimeFormateur() {
        return regimeFormateur;
    }

    public FormateurCentre regimeFormateur(String regimeFormateur) {
        this.regimeFormateur = regimeFormateur;
        return this;
    }

    public void setRegimeFormateur(String regimeFormateur) {
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
            ", specialite='" + getSpecialite() + "'" +
            ", regimeFormateur='" + getRegimeFormateur() + "'" +
            "}";
    }
}
