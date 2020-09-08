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
 * A ApprochePedagogique.
 */
@Entity
@Table(name = "approche_pedagogique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApprochePedagogique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_approche", nullable = false)
    private String libelleApproche;

    @ManyToMany(mappedBy = "approchepedagogiques")
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

    public String getLibelleApproche() {
        return libelleApproche;
    }

    public ApprochePedagogique libelleApproche(String libelleApproche) {
        this.libelleApproche = libelleApproche;
        return this;
    }

    public void setLibelleApproche(String libelleApproche) {
        this.libelleApproche = libelleApproche;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public ApprochePedagogique centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public ApprochePedagogique addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getApprochepedagogiques().add(this);
        return this;
    }

    public ApprochePedagogique removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getApprochepedagogiques().remove(this);
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
        if (!(o instanceof ApprochePedagogique)) {
            return false;
        }
        return id != null && id.equals(((ApprochePedagogique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApprochePedagogique{" +
            "id=" + getId() +
            ", libelleApproche='" + getLibelleApproche() + "'" +
            "}";
    }
}
