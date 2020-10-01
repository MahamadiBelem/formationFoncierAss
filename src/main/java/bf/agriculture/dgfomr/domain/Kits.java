package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "kits")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Installation> installationKits = new HashSet<>();

    @ManyToMany(mappedBy = "kits")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<CompositionKits> installations = new HashSet<>();

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

    public Set<Installation> getInstallationKits() {
        return installationKits;
    }

    public Kits installationKits(Set<Installation> installations) {
        this.installationKits = installations;
        return this;
    }

    public Kits addInstallationKits(Installation installation) {
        this.installationKits.add(installation);
        installation.getKits().add(this);
        return this;
    }

    public Kits removeInstallationKits(Installation installation) {
        this.installationKits.remove(installation);
        installation.getKits().remove(this);
        return this;
    }

    public void setInstallationKits(Set<Installation> installations) {
        this.installationKits = installations;
    }

    public Set<CompositionKits> getInstallations() {
        return installations;
    }

    public Kits installations(Set<CompositionKits> compositionKits) {
        this.installations = compositionKits;
        return this;
    }

    public Kits addInstallation(CompositionKits compositionKits) {
        this.installations.add(compositionKits);
        compositionKits.getKits().add(this);
        return this;
    }

    public Kits removeInstallation(CompositionKits compositionKits) {
        this.installations.remove(compositionKits);
        compositionKits.getKits().remove(this);
        return this;
    }

    public void setInstallations(Set<CompositionKits> compositionKits) {
        this.installations = compositionKits;
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
