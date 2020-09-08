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
 * A Contributions.
 */
@Entity
@Table(name = "contributions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contributions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_contribution", nullable = false)
    private String libelleContribution;

    @ManyToMany(mappedBy = "contributions")
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

    public String getLibelleContribution() {
        return libelleContribution;
    }

    public Contributions libelleContribution(String libelleContribution) {
        this.libelleContribution = libelleContribution;
        return this;
    }

    public void setLibelleContribution(String libelleContribution) {
        this.libelleContribution = libelleContribution;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public Contributions centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public Contributions addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getContributions().add(this);
        return this;
    }

    public Contributions removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getContributions().remove(this);
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
        if (!(o instanceof Contributions)) {
            return false;
        }
        return id != null && id.equals(((Contributions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contributions{" +
            "id=" + getId() +
            ", libelleContribution='" + getLibelleContribution() + "'" +
            "}";
    }
}
