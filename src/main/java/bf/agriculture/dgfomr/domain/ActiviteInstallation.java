package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ActiviteInstallation.
 */
@Entity
@Table(name = "activite_installation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActiviteInstallation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_activite", nullable = false)
    private String libelleActivite;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "activite_installation_kits",
               joinColumns = @JoinColumn(name = "activite_installation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "kits_id", referencedColumnName = "id"))
    private Set<CompositionKits> kits = new HashSet<>();

    @ManyToMany(mappedBy = "activiteinstallations")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Installation> installations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleActivite() {
        return libelleActivite;
    }

    public ActiviteInstallation libelleActivite(String libelleActivite) {
        this.libelleActivite = libelleActivite;
        return this;
    }

    public void setLibelleActivite(String libelleActivite) {
        this.libelleActivite = libelleActivite;
    }

    public Set<CompositionKits> getKits() {
        return kits;
    }

    public ActiviteInstallation kits(Set<CompositionKits> compositionKits) {
        this.kits = compositionKits;
        return this;
    }

    public ActiviteInstallation addKits(CompositionKits compositionKits) {
        this.kits.add(compositionKits);
        compositionKits.getInstallationKits().add(this);
        return this;
    }

    public ActiviteInstallation removeKits(CompositionKits compositionKits) {
        this.kits.remove(compositionKits);
        compositionKits.getInstallationKits().remove(this);
        return this;
    }

    public void setKits(Set<CompositionKits> compositionKits) {
        this.kits = compositionKits;
    }

    public Set<Installation> getInstallations() {
        return installations;
    }

    public ActiviteInstallation installations(Set<Installation> installations) {
        this.installations = installations;
        return this;
    }

    public ActiviteInstallation addInstallation(Installation installation) {
        this.installations.add(installation);
        installation.getActiviteinstallations().add(this);
        return this;
    }

    public ActiviteInstallation removeInstallation(Installation installation) {
        this.installations.remove(installation);
        installation.getActiviteinstallations().remove(this);
        return this;
    }

    public void setInstallations(Set<Installation> installations) {
        this.installations = installations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActiviteInstallation)) {
            return false;
        }
        return id != null && id.equals(((ActiviteInstallation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActiviteInstallation{" +
            "id=" + getId() +
            ", libelleActivite='" + getLibelleActivite() + "'" +
            "}";
    }
}
