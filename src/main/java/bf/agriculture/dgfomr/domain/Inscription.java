package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Inscription.
 */
@Entity
@Table(name = "inscription")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "annees_academique")
    private String anneesAcademique;

    @Column(name = "date_inscription")
    private String dateInscription;

    @ManyToOne
    @JsonIgnoreProperties(value = "inscriptions", allowSetters = true)
    private Formations inscription;

    @ManyToOne
    @JsonIgnoreProperties(value = "inscriptions", allowSetters = true)
    private Apprenantes inscriptionApprenant;

    @ManyToOne
    @JsonIgnoreProperties(value = "inscriptions", allowSetters = true)
    private CentreFormation inscriptionCentreFormation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnneesAcademique() {
        return anneesAcademique;
    }

    public Inscription anneesAcademique(String anneesAcademique) {
        this.anneesAcademique = anneesAcademique;
        return this;
    }

    public void setAnneesAcademique(String anneesAcademique) {
        this.anneesAcademique = anneesAcademique;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public Inscription dateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
        return this;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Formations getInscription() {
        return inscription;
    }

    public Inscription inscription(Formations formations) {
        this.inscription = formations;
        return this;
    }

    public void setInscription(Formations formations) {
        this.inscription = formations;
    }

    public Apprenantes getInscriptionApprenant() {
        return inscriptionApprenant;
    }

    public Inscription inscriptionApprenant(Apprenantes apprenantes) {
        this.inscriptionApprenant = apprenantes;
        return this;
    }

    public void setInscriptionApprenant(Apprenantes apprenantes) {
        this.inscriptionApprenant = apprenantes;
    }

    public CentreFormation getInscriptionCentreFormation() {
        return inscriptionCentreFormation;
    }

    public Inscription inscriptionCentreFormation(CentreFormation centreFormation) {
        this.inscriptionCentreFormation = centreFormation;
        return this;
    }

    public void setInscriptionCentreFormation(CentreFormation centreFormation) {
        this.inscriptionCentreFormation = centreFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inscription)) {
            return false;
        }
        return id != null && id.equals(((Inscription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inscription{" +
            "id=" + getId() +
            ", anneesAcademique='" + getAnneesAcademique() + "'" +
            ", dateInscription='" + getDateInscription() + "'" +
            "}";
    }
}
