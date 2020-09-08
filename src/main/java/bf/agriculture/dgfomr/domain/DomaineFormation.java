package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DomaineFormation.
 */
@Entity
@Table(name = "domaine_formation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DomaineFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_domaine", nullable = false)
    private String libelleDomaine;

    @ManyToMany(mappedBy = "domaineformations")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<CentreFormation> centreformations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleDomaine() {
        return libelleDomaine;
    }

    public DomaineFormation libelleDomaine(String libelleDomaine) {
        this.libelleDomaine = libelleDomaine;
        return this;
    }

    public void setLibelleDomaine(String libelleDomaine) {
        this.libelleDomaine = libelleDomaine;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public DomaineFormation centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public DomaineFormation addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getDomaineformations().add(this);
        return this;
    }

    public DomaineFormation removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getDomaineformations().remove(this);
        return this;
    }

    public void setCentreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomaineFormation)) {
            return false;
        }
        return id != null && id.equals(((DomaineFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DomaineFormation{" +
            "id=" + getId() +
            ", libelleDomaine='" + getLibelleDomaine() + "'" +
            "}";
    }
}
