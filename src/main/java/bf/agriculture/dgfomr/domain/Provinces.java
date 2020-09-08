package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Provinces.
 */
@Entity
@Table(name = "provinces")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Provinces implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_province", nullable = false)
    private String libelleProvince;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleProvince() {
        return libelleProvince;
    }

    public Provinces libelleProvince(String libelleProvince) {
        this.libelleProvince = libelleProvince;
        return this;
    }

    public void setLibelleProvince(String libelleProvince) {
        this.libelleProvince = libelleProvince;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Provinces)) {
            return false;
        }
        return id != null && id.equals(((Provinces) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Provinces{" +
            "id=" + getId() +
            ", libelleProvince='" + getLibelleProvince() + "'" +
            "}";
    }
}
