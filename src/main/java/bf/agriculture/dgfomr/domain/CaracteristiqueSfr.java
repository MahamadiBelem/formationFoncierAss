package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CaracteristiqueSfr.
 */
@Entity
@Table(name = "caracteristique_sfr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CaracteristiqueSfr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "nbr_agent")
    private Integer nbrAgent;

    @Column(name = "equipement_informatique")
    private Boolean equipementInformatique;

    @Column(name = "equipement_cartographique")
    private Boolean equipementCartographique;

    @Column(name = "acces_internet")
    private Boolean accesInternet;

    @Column(name = "equipemement_vehicule")
    private Boolean equipemementVehicule;

    @ManyToOne
    @JsonIgnoreProperties(value = "caracteristiqueSfrs", allowSetters = true)
    private Sfr caracteristiqueSfr;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public CaracteristiqueSfr annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getNbrAgent() {
        return nbrAgent;
    }

    public CaracteristiqueSfr nbrAgent(Integer nbrAgent) {
        this.nbrAgent = nbrAgent;
        return this;
    }

    public void setNbrAgent(Integer nbrAgent) {
        this.nbrAgent = nbrAgent;
    }

    public Boolean isEquipementInformatique() {
        return equipementInformatique;
    }

    public CaracteristiqueSfr equipementInformatique(Boolean equipementInformatique) {
        this.equipementInformatique = equipementInformatique;
        return this;
    }

    public void setEquipementInformatique(Boolean equipementInformatique) {
        this.equipementInformatique = equipementInformatique;
    }

    public Boolean isEquipementCartographique() {
        return equipementCartographique;
    }

    public CaracteristiqueSfr equipementCartographique(Boolean equipementCartographique) {
        this.equipementCartographique = equipementCartographique;
        return this;
    }

    public void setEquipementCartographique(Boolean equipementCartographique) {
        this.equipementCartographique = equipementCartographique;
    }

    public Boolean isAccesInternet() {
        return accesInternet;
    }

    public CaracteristiqueSfr accesInternet(Boolean accesInternet) {
        this.accesInternet = accesInternet;
        return this;
    }

    public void setAccesInternet(Boolean accesInternet) {
        this.accesInternet = accesInternet;
    }

    public Boolean isEquipemementVehicule() {
        return equipemementVehicule;
    }

    public CaracteristiqueSfr equipemementVehicule(Boolean equipemementVehicule) {
        this.equipemementVehicule = equipemementVehicule;
        return this;
    }

    public void setEquipemementVehicule(Boolean equipemementVehicule) {
        this.equipemementVehicule = equipemementVehicule;
    }

    public Sfr getCaracteristiqueSfr() {
        return caracteristiqueSfr;
    }

    public CaracteristiqueSfr caracteristiqueSfr(Sfr sfr) {
        this.caracteristiqueSfr = sfr;
        return this;
    }

    public void setCaracteristiqueSfr(Sfr sfr) {
        this.caracteristiqueSfr = sfr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaracteristiqueSfr)) {
            return false;
        }
        return id != null && id.equals(((CaracteristiqueSfr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaracteristiqueSfr{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", nbrAgent=" + getNbrAgent() +
            ", equipementInformatique='" + isEquipementInformatique() + "'" +
            ", equipementCartographique='" + isEquipementCartographique() + "'" +
            ", accesInternet='" + isAccesInternet() + "'" +
            ", equipemementVehicule='" + isEquipemementVehicule() + "'" +
            "}";
    }
}
