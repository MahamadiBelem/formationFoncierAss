package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TypeDemandeur.
 */
@Entity
@Table(name = "type_demandeur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeDemandeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_type_demandeur")
    private String libelleTypeDemandeur;

    @OneToMany(mappedBy = "dossierApfrTypeDemandeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DossierApfr> dossierApfrs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTypeDemandeur() {
        return libelleTypeDemandeur;
    }

    public TypeDemandeur libelleTypeDemandeur(String libelleTypeDemandeur) {
        this.libelleTypeDemandeur = libelleTypeDemandeur;
        return this;
    }

    public void setLibelleTypeDemandeur(String libelleTypeDemandeur) {
        this.libelleTypeDemandeur = libelleTypeDemandeur;
    }

    public Set<DossierApfr> getDossierApfrs() {
        return dossierApfrs;
    }

    public TypeDemandeur dossierApfrs(Set<DossierApfr> dossierApfrs) {
        this.dossierApfrs = dossierApfrs;
        return this;
    }

    public TypeDemandeur addDossierApfr(DossierApfr dossierApfr) {
        this.dossierApfrs.add(dossierApfr);
        dossierApfr.setDossierApfrTypeDemandeur(this);
        return this;
    }

    public TypeDemandeur removeDossierApfr(DossierApfr dossierApfr) {
        this.dossierApfrs.remove(dossierApfr);
        dossierApfr.setDossierApfrTypeDemandeur(null);
        return this;
    }

    public void setDossierApfrs(Set<DossierApfr> dossierApfrs) {
        this.dossierApfrs = dossierApfrs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeDemandeur)) {
            return false;
        }
        return id != null && id.equals(((TypeDemandeur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeDemandeur{" +
            "id=" + getId() +
            ", libelleTypeDemandeur='" + getLibelleTypeDemandeur() + "'" +
            "}";
    }
}
