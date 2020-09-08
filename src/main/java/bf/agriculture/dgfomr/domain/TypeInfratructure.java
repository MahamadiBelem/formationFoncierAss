package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TypeInfratructure.
 */
@Entity
@Table(name = "type_infratructure")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeInfratructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_type_infracture")
    private String libelleTypeInfracture;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTypeInfracture() {
        return libelleTypeInfracture;
    }

    public TypeInfratructure libelleTypeInfracture(String libelleTypeInfracture) {
        this.libelleTypeInfracture = libelleTypeInfracture;
        return this;
    }

    public void setLibelleTypeInfracture(String libelleTypeInfracture) {
        this.libelleTypeInfracture = libelleTypeInfracture;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeInfratructure)) {
            return false;
        }
        return id != null && id.equals(((TypeInfratructure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeInfratructure{" +
            "id=" + getId() +
            ", libelleTypeInfracture='" + getLibelleTypeInfracture() + "'" +
            "}";
    }
}
