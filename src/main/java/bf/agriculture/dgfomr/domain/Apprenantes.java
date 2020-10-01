package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import bf.agriculture.dgfomr.domain.enumeration.Sexe;

import bf.agriculture.dgfomr.domain.enumeration.Examen;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private Sexe sexe;

    @Column(name = "contact")
    private String contact;

    @Enumerated(EnumType.STRING)
    @Column(name = "typecandidat")
    private Examen typecandidat;

    @Column(name = "situation_matrimonial")
    private String situationMatrimonial;

    @Column(name = "charger")
    private String charger;

    @Column(name = "localite")
    private String localite;

    @ManyToOne
    @JsonIgnoreProperties(value = "apprenantes", allowSetters = true)
    private NiveauInstruction niveauapprenant;

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

    public Sexe getSexe() {
        return sexe;
    }

    public Apprenantes sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
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

    public Examen getTypecandidat() {
        return typecandidat;
    }

    public Apprenantes typecandidat(Examen typecandidat) {
        this.typecandidat = typecandidat;
        return this;
    }

    public void setTypecandidat(Examen typecandidat) {
        this.typecandidat = typecandidat;
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

    public NiveauInstruction getNiveauapprenant() {
        return niveauapprenant;
    }

    public Apprenantes niveauapprenant(NiveauInstruction niveauInstruction) {
        this.niveauapprenant = niveauInstruction;
        return this;
    }

    public void setNiveauapprenant(NiveauInstruction niveauInstruction) {
        this.niveauapprenant = niveauInstruction;
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
            ", typecandidat='" + getTypecandidat() + "'" +
            ", situationMatrimonial='" + getSituationMatrimonial() + "'" +
            ", charger='" + getCharger() + "'" +
            ", localite='" + getLocalite() + "'" +
            "}";
    }
}
