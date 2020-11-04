package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import bf.agriculture.dgfomr.domain.enumeration.TrancheAge;

/**
 * A DossierApfr.
 */
@Entity
@Table(name = "dossier_apfr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DossierApfr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "num_apfr")
    private String numApfr;

    @Enumerated(EnumType.STRING)
    @Column(name = "tranche_age")
    private TrancheAge trancheAge;

    @Column(name = "date_reception_cfv")
    private LocalDate dateReceptionCfv;

    @Column(name = "date_retour_ct")
    private LocalDate dateRetourCt;

    @Column(name = "date_enregistrement")
    private LocalDate dateEnregistrement;

    @Column(name = "date_depot_st")
    private LocalDate dateDepotSt;

    @Column(name = "date_retour_st")
    private LocalDate dateRetourSt;

    @Column(name = "date_notification")
    private LocalDate dateNotification;

    @Column(name = "date_signature")
    private LocalDate dateSignature;

    @Column(name = "taxes_totale")
    private LocalDate taxesTotale;

    @Column(name = "don_commune")
    private Float donCommune;

    @Column(name = "superficie_securise")
    private Float superficieSecurise;

    @Column(name = "date_constatation")
    private LocalDate dateConstatation;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossierApfrs", allowSetters = true)
    private Sfr sfrDossierApfr;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossierApfrs", allowSetters = true)
    private TypeDemandeur dossierApfrTypeDemandeur;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossierApfrs", allowSetters = true)
    private TypeActe typeDossierApfr;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumApfr() {
        return numApfr;
    }

    public DossierApfr numApfr(String numApfr) {
        this.numApfr = numApfr;
        return this;
    }

    public void setNumApfr(String numApfr) {
        this.numApfr = numApfr;
    }

    public TrancheAge getTrancheAge() {
        return trancheAge;
    }

    public DossierApfr trancheAge(TrancheAge trancheAge) {
        this.trancheAge = trancheAge;
        return this;
    }

    public void setTrancheAge(TrancheAge trancheAge) {
        this.trancheAge = trancheAge;
    }

    public LocalDate getDateReceptionCfv() {
        return dateReceptionCfv;
    }

    public DossierApfr dateReceptionCfv(LocalDate dateReceptionCfv) {
        this.dateReceptionCfv = dateReceptionCfv;
        return this;
    }

    public void setDateReceptionCfv(LocalDate dateReceptionCfv) {
        this.dateReceptionCfv = dateReceptionCfv;
    }

    public LocalDate getDateRetourCt() {
        return dateRetourCt;
    }

    public DossierApfr dateRetourCt(LocalDate dateRetourCt) {
        this.dateRetourCt = dateRetourCt;
        return this;
    }

    public void setDateRetourCt(LocalDate dateRetourCt) {
        this.dateRetourCt = dateRetourCt;
    }

    public LocalDate getDateEnregistrement() {
        return dateEnregistrement;
    }

    public DossierApfr dateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
        return this;
    }

    public void setDateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public LocalDate getDateDepotSt() {
        return dateDepotSt;
    }

    public DossierApfr dateDepotSt(LocalDate dateDepotSt) {
        this.dateDepotSt = dateDepotSt;
        return this;
    }

    public void setDateDepotSt(LocalDate dateDepotSt) {
        this.dateDepotSt = dateDepotSt;
    }

    public LocalDate getDateRetourSt() {
        return dateRetourSt;
    }

    public DossierApfr dateRetourSt(LocalDate dateRetourSt) {
        this.dateRetourSt = dateRetourSt;
        return this;
    }

    public void setDateRetourSt(LocalDate dateRetourSt) {
        this.dateRetourSt = dateRetourSt;
    }

    public LocalDate getDateNotification() {
        return dateNotification;
    }

    public DossierApfr dateNotification(LocalDate dateNotification) {
        this.dateNotification = dateNotification;
        return this;
    }

    public void setDateNotification(LocalDate dateNotification) {
        this.dateNotification = dateNotification;
    }

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public DossierApfr dateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
        return this;
    }

    public void setDateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
    }

    public LocalDate getTaxesTotale() {
        return taxesTotale;
    }

    public DossierApfr taxesTotale(LocalDate taxesTotale) {
        this.taxesTotale = taxesTotale;
        return this;
    }

    public void setTaxesTotale(LocalDate taxesTotale) {
        this.taxesTotale = taxesTotale;
    }

    public Float getDonCommune() {
        return donCommune;
    }

    public DossierApfr donCommune(Float donCommune) {
        this.donCommune = donCommune;
        return this;
    }

    public void setDonCommune(Float donCommune) {
        this.donCommune = donCommune;
    }

    public Float getSuperficieSecurise() {
        return superficieSecurise;
    }

    public DossierApfr superficieSecurise(Float superficieSecurise) {
        this.superficieSecurise = superficieSecurise;
        return this;
    }

    public void setSuperficieSecurise(Float superficieSecurise) {
        this.superficieSecurise = superficieSecurise;
    }

    public LocalDate getDateConstatation() {
        return dateConstatation;
    }

    public DossierApfr dateConstatation(LocalDate dateConstatation) {
        this.dateConstatation = dateConstatation;
        return this;
    }

    public void setDateConstatation(LocalDate dateConstatation) {
        this.dateConstatation = dateConstatation;
    }

    public Sfr getSfrDossierApfr() {
        return sfrDossierApfr;
    }

    public DossierApfr sfrDossierApfr(Sfr sfr) {
        this.sfrDossierApfr = sfr;
        return this;
    }

    public void setSfrDossierApfr(Sfr sfr) {
        this.sfrDossierApfr = sfr;
    }

    public TypeDemandeur getDossierApfrTypeDemandeur() {
        return dossierApfrTypeDemandeur;
    }

    public DossierApfr dossierApfrTypeDemandeur(TypeDemandeur typeDemandeur) {
        this.dossierApfrTypeDemandeur = typeDemandeur;
        return this;
    }

    public void setDossierApfrTypeDemandeur(TypeDemandeur typeDemandeur) {
        this.dossierApfrTypeDemandeur = typeDemandeur;
    }

    public TypeActe getTypeDossierApfr() {
        return typeDossierApfr;
    }

    public DossierApfr typeDossierApfr(TypeActe typeActe) {
        this.typeDossierApfr = typeActe;
        return this;
    }

    public void setTypeDossierApfr(TypeActe typeActe) {
        this.typeDossierApfr = typeActe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DossierApfr)) {
            return false;
        }
        return id != null && id.equals(((DossierApfr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DossierApfr{" +
            "id=" + getId() +
            ", numApfr='" + getNumApfr() + "'" +
            ", trancheAge='" + getTrancheAge() + "'" +
            ", dateReceptionCfv='" + getDateReceptionCfv() + "'" +
            ", dateRetourCt='" + getDateRetourCt() + "'" +
            ", dateEnregistrement='" + getDateEnregistrement() + "'" +
            ", dateDepotSt='" + getDateDepotSt() + "'" +
            ", dateRetourSt='" + getDateRetourSt() + "'" +
            ", dateNotification='" + getDateNotification() + "'" +
            ", dateSignature='" + getDateSignature() + "'" +
            ", taxesTotale='" + getTaxesTotale() + "'" +
            ", donCommune=" + getDonCommune() +
            ", superficieSecurise=" + getSuperficieSecurise() +
            ", dateConstatation='" + getDateConstatation() + "'" +
            "}";
    }
}
