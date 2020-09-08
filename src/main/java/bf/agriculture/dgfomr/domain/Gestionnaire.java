package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Gestionnaire.
 */
@Entity
@Table(name = "gestionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @Column(name = "contacts_gestionnaires")
    private String contactsGestionnaires;

    @NotNull
    @Column(name = "niveau_etude", nullable = false)
    private String niveauEtude;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public Gestionnaire nomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
        return this;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getContactsGestionnaires() {
        return contactsGestionnaires;
    }

    public Gestionnaire contactsGestionnaires(String contactsGestionnaires) {
        this.contactsGestionnaires = contactsGestionnaires;
        return this;
    }

    public void setContactsGestionnaires(String contactsGestionnaires) {
        this.contactsGestionnaires = contactsGestionnaires;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public Gestionnaire niveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
        return this;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gestionnaire)) {
            return false;
        }
        return id != null && id.equals(((Gestionnaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Gestionnaire{" +
            "id=" + getId() +
            ", nomComplet='" + getNomComplet() + "'" +
            ", contactsGestionnaires='" + getContactsGestionnaires() + "'" +
            ", niveauEtude='" + getNiveauEtude() + "'" +
            "}";
    }
}
