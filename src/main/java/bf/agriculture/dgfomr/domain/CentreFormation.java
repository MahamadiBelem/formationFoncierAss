package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A CentreFormation.
 */
@Entity
@Table(name = "centre_formation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CentreFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "denomination", nullable = false)
    private String denomination;

    @NotNull
    @Column(name = "localisation", nullable = false)
    private String localisation;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "statuts")
    private String statuts;

    @Column(name = "capaciteacceuil")
    private String capaciteacceuil;

    @Column(name = "ref_ouverture")
    private String refOuverture;

    @Column(name = "date_ouverture")
    private LocalDate dateOuverture;

    @OneToMany(mappedBy = "centreformation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Infrastructure> infrastructures = new HashSet<>();

    @OneToMany(mappedBy = "centreformation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Amenagement> amenagements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "centreFormations", allowSetters = true)
    private Communes communes;

    @ManyToOne
    @JsonIgnoreProperties(value = "centreFormations", allowSetters = true)
    private Promoteurs promoteurs;

    @ManyToOne
    @JsonIgnoreProperties(value = "centreFormations", allowSetters = true)
    private Gestionnaire gestionnaires;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_approchepedagogique",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "approchepedagogique_id", referencedColumnName = "id"))
    private Set<ApprochePedagogique> approchepedagogiques = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_publiccible",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "publiccible_id", referencedColumnName = "id"))
    private Set<PublicCible> publiccibles = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_specialites",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "specialites_id", referencedColumnName = "id"))
    private Set<Specialites> specialites = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_domaineformation",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "domaineformation_id", referencedColumnName = "id"))
    private Set<DomaineFormation> domaineformations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_contributions",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "contributions_id", referencedColumnName = "id"))
    private Set<Contributions> contributions = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_niveaurecrutement",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "niveaurecrutement_id", referencedColumnName = "id"))
    private Set<NiveauRecrutement> niveaurecrutements = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "centre_formation_conditionaccess",
               joinColumns = @JoinColumn(name = "centre_formation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "conditionaccess_id", referencedColumnName = "id"))
    private Set<ConditionAccess> conditionaccesses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "centreFormations", allowSetters = true)
    private Regime regime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public CentreFormation denomination(String denomination) {
        this.denomination = denomination;
        return this;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getLocalisation() {
        return localisation;
    }

    public CentreFormation localisation(String localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getAdresse() {
        return adresse;
    }

    public CentreFormation adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getStatuts() {
        return statuts;
    }

    public CentreFormation statuts(String statuts) {
        this.statuts = statuts;
        return this;
    }

    public void setStatuts(String statuts) {
        this.statuts = statuts;
    }

    public String getCapaciteacceuil() {
        return capaciteacceuil;
    }

    public CentreFormation capaciteacceuil(String capaciteacceuil) {
        this.capaciteacceuil = capaciteacceuil;
        return this;
    }

    public void setCapaciteacceuil(String capaciteacceuil) {
        this.capaciteacceuil = capaciteacceuil;
    }

    public String getRefOuverture() {
        return refOuverture;
    }

    public CentreFormation refOuverture(String refOuverture) {
        this.refOuverture = refOuverture;
        return this;
    }

    public void setRefOuverture(String refOuverture) {
        this.refOuverture = refOuverture;
    }

    public LocalDate getDateOuverture() {
        return dateOuverture;
    }

    public CentreFormation dateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
        return this;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Set<Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public CentreFormation infrastructures(Set<Infrastructure> infrastructures) {
        this.infrastructures = infrastructures;
        return this;
    }

    public CentreFormation addInfrastructure(Infrastructure infrastructure) {
        this.infrastructures.add(infrastructure);
        infrastructure.setCentreformation(this);
        return this;
    }

    public CentreFormation removeInfrastructure(Infrastructure infrastructure) {
        this.infrastructures.remove(infrastructure);
        infrastructure.setCentreformation(null);
        return this;
    }

    public void setInfrastructures(Set<Infrastructure> infrastructures) {
        this.infrastructures = infrastructures;
    }

    public Set<Amenagement> getAmenagements() {
        return amenagements;
    }

    public CentreFormation amenagements(Set<Amenagement> amenagements) {
        this.amenagements = amenagements;
        return this;
    }

    public CentreFormation addAmenagement(Amenagement amenagement) {
        this.amenagements.add(amenagement);
        amenagement.setCentreformation(this);
        return this;
    }

    public CentreFormation removeAmenagement(Amenagement amenagement) {
        this.amenagements.remove(amenagement);
        amenagement.setCentreformation(null);
        return this;
    }

    public void setAmenagements(Set<Amenagement> amenagements) {
        this.amenagements = amenagements;
    }

    public Communes getCommunes() {
        return communes;
    }

    public CentreFormation communes(Communes communes) {
        this.communes = communes;
        return this;
    }

    public void setCommunes(Communes communes) {
        this.communes = communes;
    }

    public Promoteurs getPromoteurs() {
        return promoteurs;
    }

    public CentreFormation promoteurs(Promoteurs promoteurs) {
        this.promoteurs = promoteurs;
        return this;
    }

    public void setPromoteurs(Promoteurs promoteurs) {
        this.promoteurs = promoteurs;
    }

    public Gestionnaire getGestionnaires() {
        return gestionnaires;
    }

    public CentreFormation gestionnaires(Gestionnaire gestionnaire) {
        this.gestionnaires = gestionnaire;
        return this;
    }

    public void setGestionnaires(Gestionnaire gestionnaire) {
        this.gestionnaires = gestionnaire;
    }

    public Set<ApprochePedagogique> getApprochepedagogiques() {
        return approchepedagogiques;
    }

    public CentreFormation approchepedagogiques(Set<ApprochePedagogique> approchePedagogiques) {
        this.approchepedagogiques = approchePedagogiques;
        return this;
    }

    public CentreFormation addApprochepedagogique(ApprochePedagogique approchePedagogique) {
        this.approchepedagogiques.add(approchePedagogique);
        approchePedagogique.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removeApprochepedagogique(ApprochePedagogique approchePedagogique) {
        this.approchepedagogiques.remove(approchePedagogique);
        approchePedagogique.getCentreformations().remove(this);
        return this;
    }

    public void setApprochepedagogiques(Set<ApprochePedagogique> approchePedagogiques) {
        this.approchepedagogiques = approchePedagogiques;
    }

    public Set<PublicCible> getPubliccibles() {
        return publiccibles;
    }

    public CentreFormation publiccibles(Set<PublicCible> publicCibles) {
        this.publiccibles = publicCibles;
        return this;
    }

    public CentreFormation addPubliccible(PublicCible publicCible) {
        this.publiccibles.add(publicCible);
        publicCible.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removePubliccible(PublicCible publicCible) {
        this.publiccibles.remove(publicCible);
        publicCible.getCentreformations().remove(this);
        return this;
    }

    public void setPubliccibles(Set<PublicCible> publicCibles) {
        this.publiccibles = publicCibles;
    }

    public Set<Specialites> getSpecialites() {
        return specialites;
    }

    public CentreFormation specialites(Set<Specialites> specialites) {
        this.specialites = specialites;
        return this;
    }

    public CentreFormation addSpecialites(Specialites specialites) {
        this.specialites.add(specialites);
        specialites.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removeSpecialites(Specialites specialites) {
        this.specialites.remove(specialites);
        specialites.getCentreformations().remove(this);
        return this;
    }

    public void setSpecialites(Set<Specialites> specialites) {
        this.specialites = specialites;
    }

    public Set<DomaineFormation> getDomaineformations() {
        return domaineformations;
    }

    public CentreFormation domaineformations(Set<DomaineFormation> domaineFormations) {
        this.domaineformations = domaineFormations;
        return this;
    }

    public CentreFormation addDomaineformation(DomaineFormation domaineFormation) {
        this.domaineformations.add(domaineFormation);
        domaineFormation.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removeDomaineformation(DomaineFormation domaineFormation) {
        this.domaineformations.remove(domaineFormation);
        domaineFormation.getCentreformations().remove(this);
        return this;
    }

    public void setDomaineformations(Set<DomaineFormation> domaineFormations) {
        this.domaineformations = domaineFormations;
    }

    public Set<Contributions> getContributions() {
        return contributions;
    }

    public CentreFormation contributions(Set<Contributions> contributions) {
        this.contributions = contributions;
        return this;
    }

    public CentreFormation addContributions(Contributions contributions) {
        this.contributions.add(contributions);
        contributions.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removeContributions(Contributions contributions) {
        this.contributions.remove(contributions);
        contributions.getCentreformations().remove(this);
        return this;
    }

    public void setContributions(Set<Contributions> contributions) {
        this.contributions = contributions;
    }

    public Set<NiveauRecrutement> getNiveaurecrutements() {
        return niveaurecrutements;
    }

    public CentreFormation niveaurecrutements(Set<NiveauRecrutement> niveauRecrutements) {
        this.niveaurecrutements = niveauRecrutements;
        return this;
    }

    public CentreFormation addNiveaurecrutement(NiveauRecrutement niveauRecrutement) {
        this.niveaurecrutements.add(niveauRecrutement);
        niveauRecrutement.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removeNiveaurecrutement(NiveauRecrutement niveauRecrutement) {
        this.niveaurecrutements.remove(niveauRecrutement);
        niveauRecrutement.getCentreformations().remove(this);
        return this;
    }

    public void setNiveaurecrutements(Set<NiveauRecrutement> niveauRecrutements) {
        this.niveaurecrutements = niveauRecrutements;
    }

    public Set<ConditionAccess> getConditionaccesses() {
        return conditionaccesses;
    }

    public CentreFormation conditionaccesses(Set<ConditionAccess> conditionAccesses) {
        this.conditionaccesses = conditionAccesses;
        return this;
    }

    public CentreFormation addConditionaccess(ConditionAccess conditionAccess) {
        this.conditionaccesses.add(conditionAccess);
        conditionAccess.getCentreformations().add(this);
        return this;
    }

    public CentreFormation removeConditionaccess(ConditionAccess conditionAccess) {
        this.conditionaccesses.remove(conditionAccess);
        conditionAccess.getCentreformations().remove(this);
        return this;
    }

    public void setConditionaccesses(Set<ConditionAccess> conditionAccesses) {
        this.conditionaccesses = conditionAccesses;
    }

    public Regime getRegime() {
        return regime;
    }

    public CentreFormation regime(Regime regime) {
        this.regime = regime;
        return this;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CentreFormation)) {
            return false;
        }
        return id != null && id.equals(((CentreFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentreFormation{" +
            "id=" + getId() +
            ", denomination='" + getDenomination() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", statuts='" + getStatuts() + "'" +
            ", capaciteacceuil='" + getCapaciteacceuil() + "'" +
            ", refOuverture='" + getRefOuverture() + "'" +
            ", dateOuverture='" + getDateOuverture() + "'" +
            "}";
    }
}
