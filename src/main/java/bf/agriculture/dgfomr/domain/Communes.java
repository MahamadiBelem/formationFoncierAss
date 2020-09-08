package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Communes.
 */
@Entity
@Table(name = "communes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Communes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_commune", nullable = false)
    private String libelleCommune;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleCommune() {
        return libelleCommune;
    }

    public Communes libelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
        return this;
    }

    public void setLibelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Communes)) {
            return false;
        }
        return id != null && id.equals(((Communes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Communes{" +
            "id=" + getId() +
            ", libelleCommune='" + getLibelleCommune() + "'" +
            "}";
    }
}
