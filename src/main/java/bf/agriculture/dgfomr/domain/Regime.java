package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Regime.
 */
@Entity
@Table(name = "regime")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Regime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_regime", nullable = false)
    private String libelleRegime;

    @OneToMany(mappedBy = "regime")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CentreFormation> centreFormations = new HashSet<>();

    @OneToMany(mappedBy = "regimecentreformateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FormateurCentre> formateurCentres = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleRegime() {
        return libelleRegime;
    }

    public Regime libelleRegime(String libelleRegime) {
        this.libelleRegime = libelleRegime;
        return this;
    }

    public void setLibelleRegime(String libelleRegime) {
        this.libelleRegime = libelleRegime;
    }

    public Set<CentreFormation> getCentreFormations() {
        return centreFormations;
    }

    public Regime centreFormations(Set<CentreFormation> centreFormations) {
        this.centreFormations = centreFormations;
        return this;
    }

    public Regime addCentreFormation(CentreFormation centreFormation) {
        this.centreFormations.add(centreFormation);
        centreFormation.setRegime(this);
        return this;
    }

    public Regime removeCentreFormation(CentreFormation centreFormation) {
        this.centreFormations.remove(centreFormation);
        centreFormation.setRegime(null);
        return this;
    }

    public void setCentreFormations(Set<CentreFormation> centreFormations) {
        this.centreFormations = centreFormations;
    }

    public Set<FormateurCentre> getFormateurCentres() {
        return formateurCentres;
    }

    public Regime formateurCentres(Set<FormateurCentre> formateurCentres) {
        this.formateurCentres = formateurCentres;
        return this;
    }

    public Regime addFormateurCentre(FormateurCentre formateurCentre) {
        this.formateurCentres.add(formateurCentre);
        formateurCentre.setRegimecentreformateur(this);
        return this;
    }

    public Regime removeFormateurCentre(FormateurCentre formateurCentre) {
        this.formateurCentres.remove(formateurCentre);
        formateurCentre.setRegimecentreformateur(null);
        return this;
    }

    public void setFormateurCentres(Set<FormateurCentre> formateurCentres) {
        this.formateurCentres = formateurCentres;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Regime)) {
            return false;
        }
        return id != null && id.equals(((Regime) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Regime{" +
            "id=" + getId() +
            ", libelleRegime='" + getLibelleRegime() + "'" +
            "}";
    }
}
