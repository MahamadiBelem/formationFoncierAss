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
 * A NiveauRecrutement.
 */
@Entity
@Table(name = "niveau_recrutement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NiveauRecrutement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_niveau", nullable = false)
    private String libelleNiveau;

    @ManyToMany(mappedBy = "niveaurecrutements")
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

    public String getLibelleNiveau() {
        return libelleNiveau;
    }

    public NiveauRecrutement libelleNiveau(String libelleNiveau) {
        this.libelleNiveau = libelleNiveau;
        return this;
    }

    public void setLibelleNiveau(String libelleNiveau) {
        this.libelleNiveau = libelleNiveau;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public NiveauRecrutement centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public NiveauRecrutement addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getNiveaurecrutements().add(this);
        return this;
    }

    public NiveauRecrutement removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getNiveaurecrutements().remove(this);
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
        if (!(o instanceof NiveauRecrutement)) {
            return false;
        }
        return id != null && id.equals(((NiveauRecrutement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NiveauRecrutement{" +
            "id=" + getId() +
            ", libelleNiveau='" + getLibelleNiveau() + "'" +
            "}";
    }
}
