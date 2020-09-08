package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TypeAmenagement.
 */
@Entity
@Table(name = "type_amenagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeAmenagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_type_amenagement")
    private String libelleTypeAmenagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTypeAmenagement() {
        return libelleTypeAmenagement;
    }

    public TypeAmenagement libelleTypeAmenagement(String libelleTypeAmenagement) {
        this.libelleTypeAmenagement = libelleTypeAmenagement;
        return this;
    }

    public void setLibelleTypeAmenagement(String libelleTypeAmenagement) {
        this.libelleTypeAmenagement = libelleTypeAmenagement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeAmenagement)) {
            return false;
        }
        return id != null && id.equals(((TypeAmenagement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeAmenagement{" +
            "id=" + getId() +
            ", libelleTypeAmenagement='" + getLibelleTypeAmenagement() + "'" +
            "}";
    }
}
