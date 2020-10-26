package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import bf.agriculture.dgfomr.domain.enumeration.Partenaire;

/**
 * A SourceFiancement.
 */
@Entity
@Table(name = "source_fiancement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SourceFiancement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_source", nullable = false)
    private String libelleSource;

    @Enumerated(EnumType.STRING)
    @Column(name = "partenaire")
    private Partenaire partenaire;

    @ManyToMany(mappedBy = "sourceIntallations")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Installation> installationSources = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleSource() {
        return libelleSource;
    }

    public SourceFiancement libelleSource(String libelleSource) {
        this.libelleSource = libelleSource;
        return this;
    }

    public void setLibelleSource(String libelleSource) {
        this.libelleSource = libelleSource;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public SourceFiancement partenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
        return this;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Set<Installation> getInstallationSources() {
        return installationSources;
    }

    public SourceFiancement installationSources(Set<Installation> installations) {
        this.installationSources = installations;
        return this;
    }

    public SourceFiancement addInstallationSource(Installation installation) {
        this.installationSources.add(installation);
        installation.getSourceIntallations().add(this);
        return this;
    }

    public SourceFiancement removeInstallationSource(Installation installation) {
        this.installationSources.remove(installation);
        installation.getSourceIntallations().remove(this);
        return this;
    }

    public void setInstallationSources(Set<Installation> installations) {
        this.installationSources = installations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SourceFiancement)) {
            return false;
        }
        return id != null && id.equals(((SourceFiancement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SourceFiancement{" +
            "id=" + getId() +
            ", libelleSource='" + getLibelleSource() + "'" +
            ", partenaire='" + getPartenaire() + "'" +
            "}";
    }
}
