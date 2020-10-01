package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Region.
 */
@Entity
@Table(name = "region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_region", nullable = false)
    private String libelleRegion;

    @OneToMany(mappedBy = "region")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Provinces> provinces = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleRegion() {
        return libelleRegion;
    }

    public Region libelleRegion(String libelleRegion) {
        this.libelleRegion = libelleRegion;
        return this;
    }

    public void setLibelleRegion(String libelleRegion) {
        this.libelleRegion = libelleRegion;
    }

    public Set<Provinces> getProvinces() {
        return provinces;
    }

    public Region provinces(Set<Provinces> provinces) {
        this.provinces = provinces;
        return this;
    }

    public Region addProvinces(Provinces provinces) {
        this.provinces.add(provinces);
        provinces.setRegion(this);
        return this;
    }

    public Region removeProvinces(Provinces provinces) {
        this.provinces.remove(provinces);
        provinces.setRegion(null);
        return this;
    }

    public void setProvinces(Set<Provinces> provinces) {
        this.provinces = provinces;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Region)) {
            return false;
        }
        return id != null && id.equals(((Region) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Region{" +
            "id=" + getId() +
            ", libelleRegion='" + getLibelleRegion() + "'" +
            "}";
    }
}
