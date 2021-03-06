package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Villages.
 */
@Entity
@Table(name = "villages")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Villages implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_village", nullable = false)
    private String libelleVillage;

    @ManyToOne
    @JsonIgnoreProperties(value = "villages", allowSetters = true)
    private Communes commune;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleVillage() {
        return libelleVillage;
    }

    public Villages libelleVillage(String libelleVillage) {
        this.libelleVillage = libelleVillage;
        return this;
    }

    public void setLibelleVillage(String libelleVillage) {
        this.libelleVillage = libelleVillage;
    }

    public Communes getCommune() {
        return commune;
    }

    public Villages commune(Communes communes) {
        this.commune = communes;
        return this;
    }

    public void setCommune(Communes communes) {
        this.commune = communes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Villages)) {
            return false;
        }
        return id != null && id.equals(((Villages) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Villages{" +
            "id=" + getId() +
            ", libelleVillage='" + getLibelleVillage() + "'" +
            "}";
    }
}
