package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Sfr.
 */
@Entity
@Table(name = "sfr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sfr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nbr_personne")
    private Integer nbrPersonne;

    @OneToMany(mappedBy = "sfrPersonnel")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Personnel> personnel = new HashSet<>();

    @OneToMany(mappedBy = "caracteristiqueSfr")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CaracteristiqueSfr> caracteristiqueSfrs = new HashSet<>();

    @OneToMany(mappedBy = "sfrDossierApfr")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DossierApfr> dossierApfrs = new HashSet<>();

    @OneToMany(mappedBy = "sfrImmaDomaine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ImmaDomaine> immaDomaines = new HashSet<>();

    @OneToMany(mappedBy = "sfrFormation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FormationSFR> formationSFRS = new HashSet<>();

    @OneToOne(mappedBy = "sfr")
    @JsonIgnore
    private Communes communes;

    @ManyToOne
    @JsonIgnoreProperties(value = "sfrs", allowSetters = true)
    //private StructureLocale structureLocale;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbrPersonne() {
        return nbrPersonne;
    }

    public Sfr nbrPersonne(Integer nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
        return this;
    }

    public void setNbrPersonne(Integer nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

    public Set<Personnel> getPersonnel() {
        return personnel;
    }

    public Sfr personnel(Set<Personnel> personnel) {
        this.personnel = personnel;
        return this;
    }

    public Sfr addPersonnel(Personnel personnel) {
        this.personnel.add(personnel);
        personnel.setSfrPersonnel(this);
        return this;
    }

    public Sfr removePersonnel(Personnel personnel) {
        this.personnel.remove(personnel);
        personnel.setSfrPersonnel(null);
        return this;
    }

    public void setPersonnel(Set<Personnel> personnel) {
        this.personnel = personnel;
    }

    public Set<CaracteristiqueSfr> getCaracteristiqueSfrs() {
        return caracteristiqueSfrs;
    }

    public Sfr caracteristiqueSfrs(Set<CaracteristiqueSfr> caracteristiqueSfrs) {
        this.caracteristiqueSfrs = caracteristiqueSfrs;
        return this;
    }

    public Sfr addCaracteristiqueSfr(CaracteristiqueSfr caracteristiqueSfr) {
        this.caracteristiqueSfrs.add(caracteristiqueSfr);
        caracteristiqueSfr.setCaracteristiqueSfr(this);
        return this;
    }

    public Sfr removeCaracteristiqueSfr(CaracteristiqueSfr caracteristiqueSfr) {
        this.caracteristiqueSfrs.remove(caracteristiqueSfr);
        caracteristiqueSfr.setCaracteristiqueSfr(null);
        return this;
    }

    public void setCaracteristiqueSfrs(Set<CaracteristiqueSfr> caracteristiqueSfrs) {
        this.caracteristiqueSfrs = caracteristiqueSfrs;
    }

    public Set<DossierApfr> getDossierApfrs() {
        return dossierApfrs;
    }

    public Sfr dossierApfrs(Set<DossierApfr> dossierApfrs) {
        this.dossierApfrs = dossierApfrs;
        return this;
    }

    public Sfr addDossierApfr(DossierApfr dossierApfr) {
        this.dossierApfrs.add(dossierApfr);
        dossierApfr.setSfrDossierApfr(this);
        return this;
    }

    public Sfr removeDossierApfr(DossierApfr dossierApfr) {
        this.dossierApfrs.remove(dossierApfr);
        dossierApfr.setSfrDossierApfr(null);
        return this;
    }

    public void setDossierApfrs(Set<DossierApfr> dossierApfrs) {
        this.dossierApfrs = dossierApfrs;
    }

    public Set<ImmaDomaine> getImmaDomaines() {
        return immaDomaines;
    }

    public Sfr immaDomaines(Set<ImmaDomaine> immaDomaines) {
        this.immaDomaines = immaDomaines;
        return this;
    }

    public Sfr addImmaDomaine(ImmaDomaine immaDomaine) {
        this.immaDomaines.add(immaDomaine);
        immaDomaine.setSfrImmaDomaine(this);
        return this;
    }

    public Sfr removeImmaDomaine(ImmaDomaine immaDomaine) {
        this.immaDomaines.remove(immaDomaine);
        immaDomaine.setSfrImmaDomaine(null);
        return this;
    }

    public void setImmaDomaines(Set<ImmaDomaine> immaDomaines) {
        this.immaDomaines = immaDomaines;
    }

    public Set<FormationSFR> getFormationSFRS() {
        return formationSFRS;
    }

    public Sfr formationSFRS(Set<FormationSFR> formationSFRS) {
        this.formationSFRS = formationSFRS;
        return this;
    }

    public Sfr addFormationSFR(FormationSFR formationSFR) {
        this.formationSFRS.add(formationSFR);
        formationSFR.setSfrFormation(this);
        return this;
    }

    public Sfr removeFormationSFR(FormationSFR formationSFR) {
        this.formationSFRS.remove(formationSFR);
        formationSFR.setSfrFormation(null);
        return this;
    }

    public void setFormationSFRS(Set<FormationSFR> formationSFRS) {
        this.formationSFRS = formationSFRS;
    }

    public Communes getCommunes() {
        return communes;
    }

    public Sfr communes(Communes communes) {
        this.communes = communes;
        return this;
    }

    public void setCommunes(Communes communes) {
        this.communes = communes;
    }

 /*   public StructureLocale getStructureLocale() {
        return structureLocale;
    }

    public Sfr structureLocale(StructureLocale structureLocale) {
        this.structureLocale = structureLocale;
        return this;
    }

    public void setStructureLocale(StructureLocale structureLocale) {
        this.structureLocale = structureLocale;
    }*/
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sfr)) {
            return false;
        }
        return id != null && id.equals(((Sfr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sfr{" +
            "id=" + getId() +
            ", nbrPersonne=" + getNbrPersonne() +
            "}";
    }
}
