package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Apprenantes.
 */
@Entity
@Table(name = "apprenantes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Apprenantes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "matricule", nullable = false, unique = true)
    private String matricule;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @Column(name = "sexe", nullable = false)
    private String sexe;

    @Column(name = "contact")
    private String contact;

    @Column(name = "iscandidat")
    private Boolean iscandidat;

    @Column(name = "situation_matrimonial")
    private String situationMatrimonial;

    @Column(name = "charger")
    private String charger;

    @Column(name = "localite")
    private String localite;

    @OneToOne
    @JoinColumn(unique = true)
    private SortiePromotion sortiepromotion;

    @ManyToMany(mappedBy = "formations")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Formations> centreformations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public Apprenantes matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public Apprenantes nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Apprenantes prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Apprenantes dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public Apprenantes sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getContact() {
        return contact;
    }

    public Apprenantes contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean isIscandidat() {
        return iscandidat;
    }

    public Apprenantes iscandidat(Boolean iscandidat) {
        this.iscandidat = iscandidat;
        return this;
    }

    public void setIscandidat(Boolean iscandidat) {
        this.iscandidat = iscandidat;
    }

    public String getSituationMatrimonial() {
        return situationMatrimonial;
    }

    public Apprenantes situationMatrimonial(String situationMatrimonial) {
        this.situationMatrimonial = situationMatrimonial;
        return this;
    }

    public void setSituationMatrimonial(String situationMatrimonial) {
        this.situationMatrimonial = situationMatrimonial;
    }

    public String getCharger() {
        return charger;
    }

    public Apprenantes charger(String charger) {
        this.charger = charger;
        return this;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getLocalite() {
        return localite;
    }

    public Apprenantes localite(String localite) {
        this.localite = localite;
        return this;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public SortiePromotion getSortiepromotion() {
        return sortiepromotion;
    }

    public Apprenantes sortiepromotion(SortiePromotion sortiePromotion) {
        this.sortiepromotion = sortiePromotion;
        return this;
    }

    public void setSortiepromotion(SortiePromotion sortiePromotion) {
        this.sortiepromotion = sortiePromotion;
    }

    public Set<Formations> getCentreformations() {
        return centreformations;
    }

    public Apprenantes centreformations(Set<Formations> formations) {
        this.centreformations = formations;
        return this;
    }

    public Apprenantes addCentreformation(Formations formations) {
        this.centreformations.add(formations);
        formations.getFormations().add(this);
        return this;
    }

    public Apprenantes removeCentreformation(Formations formations) {
        this.centreformations.remove(formations);
        formations.getFormations().remove(this);
        return this;
    }

    public void setCentreformations(Set<Formations> formations) {
        this.centreformations = formations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Apprenantes)) {
            return false;
        }
        return id != null && id.equals(((Apprenantes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Apprenantes{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", contact='" + getContact() + "'" +
            ", iscandidat='" + isIscandidat() + "'" +
            ", situationMatrimonial='" + getSituationMatrimonial() + "'" +
            ", charger='" + getCharger() + "'" +
            ", localite='" + getLocalite() + "'" +
            "}";
    }
}
