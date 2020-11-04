package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TypeActe.
 */
@Entity
@Table(name = "type_acte")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeActe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_type_acte")
    private String libelleTypeActe;

    @OneToMany(mappedBy = "typeDossierApfr")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DossierApfr> dossierApfrs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTypeActe() {
        return libelleTypeActe;
    }

    public TypeActe libelleTypeActe(String libelleTypeActe) {
        this.libelleTypeActe = libelleTypeActe;
        return this;
    }

    public void setLibelleTypeActe(String libelleTypeActe) {
        this.libelleTypeActe = libelleTypeActe;
    }

    public Set<DossierApfr> getDossierApfrs() {
        return dossierApfrs;
    }

    public TypeActe dossierApfrs(Set<DossierApfr> dossierApfrs) {
        this.dossierApfrs = dossierApfrs;
        return this;
    }

    public TypeActe addDossierApfr(DossierApfr dossierApfr) {
        this.dossierApfrs.add(dossierApfr);
        dossierApfr.setTypeDossierApfr(this);
        return this;
    }

    public TypeActe removeDossierApfr(DossierApfr dossierApfr) {
        this.dossierApfrs.remove(dossierApfr);
        dossierApfr.setTypeDossierApfr(null);
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
        if (!(o instanceof TypeActe)) {
            return false;
        }
        return id != null && id.equals(((TypeActe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeActe{" +
            "id=" + getId() +
            ", libelleTypeActe='" + getLibelleTypeActe() + "'" +
            "}";
    }
}
