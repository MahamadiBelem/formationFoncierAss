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

    @OneToMany(mappedBy = "commune")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Villages> villages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "communes", allowSetters = true)
    private Provinces provinces;

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

    public Set<Villages> getVillages() {
        return villages;
    }

    public Communes villages(Set<Villages> villages) {
        this.villages = villages;
        return this;
    }

    public Communes addVillages(Villages villages) {
        this.villages.add(villages);
        villages.setCommune(this);
        return this;
    }

    public Communes removeVillages(Villages villages) {
        this.villages.remove(villages);
        villages.setCommune(null);
        return this;
    }

    public void setVillages(Set<Villages> villages) {
        this.villages = villages;
    }

    public Provinces getProvinces() {
        return provinces;
    }

    public Communes provinces(Provinces provinces) {
        this.provinces = provinces;
        return this;
    }

    public void setProvinces(Provinces provinces) {
        this.provinces = provinces;
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
