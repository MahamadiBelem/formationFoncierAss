package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CompositionKits.
 */
@Entity
@Table(name = "composition_kits")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CompositionKits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_kits")
    private String libelleKits;

    @Column(name = "quantite_kits")
    private Long quantiteKits;

    @ManyToMany(mappedBy = "kits")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<ActiviteInstallation> installationKits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleKits() {
        return libelleKits;
    }

    public CompositionKits libelleKits(String libelleKits) {
        this.libelleKits = libelleKits;
        return this;
    }

    public void setLibelleKits(String libelleKits) {
        this.libelleKits = libelleKits;
    }

    public Long getQuantiteKits() {
        return quantiteKits;
    }

    public CompositionKits quantiteKits(Long quantiteKits) {
        this.quantiteKits = quantiteKits;
        return this;
    }

    public void setQuantiteKits(Long quantiteKits) {
        this.quantiteKits = quantiteKits;
    }

    public Set<ActiviteInstallation> getInstallationKits() {
        return installationKits;
    }

    public CompositionKits installationKits(Set<ActiviteInstallation> activiteInstallations) {
        this.installationKits = activiteInstallations;
        return this;
    }

    public CompositionKits addInstallationKits(ActiviteInstallation activiteInstallation) {
        this.installationKits.add(activiteInstallation);
        activiteInstallation.getKits().add(this);
        return this;
    }

    public CompositionKits removeInstallationKits(ActiviteInstallation activiteInstallation) {
        this.installationKits.remove(activiteInstallation);
        activiteInstallation.getKits().remove(this);
        return this;
    }

    public void setInstallationKits(Set<ActiviteInstallation> activiteInstallations) {
        this.installationKits = activiteInstallations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompositionKits)) {
            return false;
        }
        return id != null && id.equals(((CompositionKits) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompositionKits{" +
            "id=" + getId() +
            ", libelleKits='" + getLibelleKits() + "'" +
            ", quantiteKits=" + getQuantiteKits() +
            "}";
    }
}
