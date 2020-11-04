package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ProfilPersonnel.
 */
@Entity
@Table(name = "profil_personnel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfilPersonnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_profil")
    private String libelleProfil;

    @OneToMany(mappedBy = "personnel")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Personnel> personnel = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public ProfilPersonnel libelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
        return this;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public Set<Personnel> getPersonnel() {
        return personnel;
    }

    public ProfilPersonnel personnel(Set<Personnel> personnel) {
        this.personnel = personnel;
        return this;
    }

    public ProfilPersonnel addPersonnel(Personnel personnel) {
        this.personnel.add(personnel);
        personnel.setPersonnel(this);
        return this;
    }

    public ProfilPersonnel removePersonnel(Personnel personnel) {
        this.personnel.remove(personnel);
        personnel.setPersonnel(null);
        return this;
    }

    public void setPersonnel(Set<Personnel> personnel) {
        this.personnel = personnel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfilPersonnel)) {
            return false;
        }
        return id != null && id.equals(((ProfilPersonnel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfilPersonnel{" +
            "id=" + getId() +
            ", libelleProfil='" + getLibelleProfil() + "'" +
            "}";
    }
}
