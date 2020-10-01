package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Typecandidat.
 */
@Entity
@Table(name = "typecandidat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Typecandidat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_type_candidat", nullable = false)
    private String libelleTypeCandidat;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTypeCandidat() {
        return libelleTypeCandidat;
    }

    public Typecandidat libelleTypeCandidat(String libelleTypeCandidat) {
        this.libelleTypeCandidat = libelleTypeCandidat;
        return this;
    }

    public void setLibelleTypeCandidat(String libelleTypeCandidat) {
        this.libelleTypeCandidat = libelleTypeCandidat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Typecandidat)) {
            return false;
        }
        return id != null && id.equals(((Typecandidat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Typecandidat{" +
            "id=" + getId() +
            ", libelleTypeCandidat='" + getLibelleTypeCandidat() + "'" +
            "}";
    }
}
