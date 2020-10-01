package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Formateurs.
 */
@Entity
@Table(name = "formateurs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Formateurs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @NotNull
    @Column(name = "emplois", nullable = false)
    private String emplois;

    @Column(name = "contactformateur")
    private String contactformateur;

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

    public Formateurs nomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
        return this;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmplois() {
        return emplois;
    }

    public Formateurs emplois(String emplois) {
        this.emplois = emplois;
        return this;
    }

    public void setEmplois(String emplois) {
        this.emplois = emplois;
    }

    public String getContactformateur() {
        return contactformateur;
    }

    public Formateurs contactformateur(String contactformateur) {
        this.contactformateur = contactformateur;
        return this;
    }

    public void setContactformateur(String contactformateur) {
        this.contactformateur = contactformateur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formateurs)) {
            return false;
        }
        return id != null && id.equals(((Formateurs) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Formateurs{" +
            "id=" + getId() +
            ", nomComplet='" + getNomComplet() + "'" +
            ", emplois='" + getEmplois() + "'" +
            ", contactformateur='" + getContactformateur() + "'" +
            "}";
    }
}
