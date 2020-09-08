package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Promoteurs.
 */
@Entity
@Table(name = "promoteurs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Promoteurs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_promoteur", nullable = false)
    private String libellePromoteur;

    @Column(name = "contact_promoteur")
    private String contactPromoteur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibellePromoteur() {
        return libellePromoteur;
    }

    public Promoteurs libellePromoteur(String libellePromoteur) {
        this.libellePromoteur = libellePromoteur;
        return this;
    }

    public void setLibellePromoteur(String libellePromoteur) {
        this.libellePromoteur = libellePromoteur;
    }

    public String getContactPromoteur() {
        return contactPromoteur;
    }

    public Promoteurs contactPromoteur(String contactPromoteur) {
        this.contactPromoteur = contactPromoteur;
        return this;
    }

    public void setContactPromoteur(String contactPromoteur) {
        this.contactPromoteur = contactPromoteur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Promoteurs)) {
            return false;
        }
        return id != null && id.equals(((Promoteurs) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Promoteurs{" +
            "id=" + getId() +
            ", libellePromoteur='" + getLibellePromoteur() + "'" +
            ", contactPromoteur='" + getContactPromoteur() + "'" +
            "}";
    }
}
