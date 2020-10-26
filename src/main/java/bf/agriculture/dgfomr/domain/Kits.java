package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Kits.
 */
@Entity
@Table(name = "kits")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Kits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "isobtenu")
    private Boolean isobtenu;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsobtenu() {
        return isobtenu;
    }

    public Kits isobtenu(Boolean isobtenu) {
        this.isobtenu = isobtenu;
        return this;
    }

    public void setIsobtenu(Boolean isobtenu) {
        this.isobtenu = isobtenu;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Kits)) {
            return false;
        }
        return id != null && id.equals(((Kits) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Kits{" +
            "id=" + getId() +
            ", isobtenu='" + isIsobtenu() + "'" +
            "}";
    }
}
