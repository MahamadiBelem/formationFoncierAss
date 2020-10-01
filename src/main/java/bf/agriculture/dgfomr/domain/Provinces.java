package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "provinces")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Communes> communes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "provinces", allowSetters = true)
    private Region region;

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

    public Set<Communes> getCommunes() {
        return communes;
    }

    public Provinces communes(Set<Communes> communes) {
        this.communes = communes;
        return this;
    }

    public Provinces addCommunes(Communes communes) {
        this.communes.add(communes);
        communes.setProvinces(this);
        return this;
    }

    public Provinces removeCommunes(Communes communes) {
        this.communes.remove(communes);
        communes.setProvinces(null);
        return this;
    }

    public void setCommunes(Set<Communes> communes) {
        this.communes = communes;
    }

    public Region getRegion() {
        return region;
    }

    public Provinces region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
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
