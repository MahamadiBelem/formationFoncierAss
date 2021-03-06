package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TypeFormation.
 */
@Entity
@Table(name = "type_formation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_type_formation")
    private String libelleTypeFormation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTypeFormation() {
        return libelleTypeFormation;
    }

    public TypeFormation libelleTypeFormation(String libelleTypeFormation) {
        this.libelleTypeFormation = libelleTypeFormation;
        return this;
    }

    public void setLibelleTypeFormation(String libelleTypeFormation) {
        this.libelleTypeFormation = libelleTypeFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeFormation)) {
            return false;
        }
        return id != null && id.equals(((TypeFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeFormation{" +
            "id=" + getId() +
            ", libelleTypeFormation='" + getLibelleTypeFormation() + "'" +
            "}";
    }
}
