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
 * A PublicCible.
 */
@Entity
@Table(name = "public_cible")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicCible implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_public", nullable = false)
    private String libellePublic;

    @ManyToMany(mappedBy = "publiccibles")
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

    public String getLibellePublic() {
        return libellePublic;
    }

    public PublicCible libellePublic(String libellePublic) {
        this.libellePublic = libellePublic;
        return this;
    }

    public void setLibellePublic(String libellePublic) {
        this.libellePublic = libellePublic;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public PublicCible centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public PublicCible addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getPubliccibles().add(this);
        return this;
    }

    public PublicCible removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getPubliccibles().remove(this);
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
        if (!(o instanceof PublicCible)) {
            return false;
        }
        return id != null && id.equals(((PublicCible) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PublicCible{" +
            "id=" + getId() +
            ", libellePublic='" + getLibellePublic() + "'" +
            "}";
    }
}
