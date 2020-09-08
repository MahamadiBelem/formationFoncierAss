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
 * A Specialites.
 */
@Entity
@Table(name = "specialites")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Specialites implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_specialite", nullable = false)
    private String libelleSpecialite;

    @ManyToMany(mappedBy = "specialites")
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

    public String getLibelleSpecialite() {
        return libelleSpecialite;
    }

    public Specialites libelleSpecialite(String libelleSpecialite) {
        this.libelleSpecialite = libelleSpecialite;
        return this;
    }

    public void setLibelleSpecialite(String libelleSpecialite) {
        this.libelleSpecialite = libelleSpecialite;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public Specialites centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public Specialites addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getSpecialites().add(this);
        return this;
    }

    public Specialites removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getSpecialites().remove(this);
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
        if (!(o instanceof Specialites)) {
            return false;
        }
        return id != null && id.equals(((Specialites) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Specialites{" +
            "id=" + getId() +
            ", libelleSpecialite='" + getLibelleSpecialite() + "'" +
            "}";
    }
}
