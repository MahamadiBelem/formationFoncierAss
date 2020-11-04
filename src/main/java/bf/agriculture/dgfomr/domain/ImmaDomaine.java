package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImmaDomaine.
 */
@Entity
@Table(name = "imma_domaine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImmaDomaine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "superfice_tot_inventorie")
    private Float superficeTotInventorie;

    @Column(name = "superficie_tot_immatricule")
    private Float superficieTotImmatricule;

    @Column(name = "superficie_tot_encadre")
    private Float superficieTotEncadre;

    @ManyToOne
    @JsonIgnoreProperties(value = "immaDomaines", allowSetters = true)
    private Sfr sfrImmaDomaine;

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

    public ImmaDomaine annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Float getSuperficeTotInventorie() {
        return superficeTotInventorie;
    }

    public ImmaDomaine superficeTotInventorie(Float superficeTotInventorie) {
        this.superficeTotInventorie = superficeTotInventorie;
        return this;
    }

    public void setSuperficeTotInventorie(Float superficeTotInventorie) {
        this.superficeTotInventorie = superficeTotInventorie;
    }

    public Float getSuperficieTotImmatricule() {
        return superficieTotImmatricule;
    }

    public ImmaDomaine superficieTotImmatricule(Float superficieTotImmatricule) {
        this.superficieTotImmatricule = superficieTotImmatricule;
        return this;
    }

    public void setSuperficieTotImmatricule(Float superficieTotImmatricule) {
        this.superficieTotImmatricule = superficieTotImmatricule;
    }

    public Float getSuperficieTotEncadre() {
        return superficieTotEncadre;
    }

    public ImmaDomaine superficieTotEncadre(Float superficieTotEncadre) {
        this.superficieTotEncadre = superficieTotEncadre;
        return this;
    }

    public void setSuperficieTotEncadre(Float superficieTotEncadre) {
        this.superficieTotEncadre = superficieTotEncadre;
    }

    public Sfr getSfrImmaDomaine() {
        return sfrImmaDomaine;
    }

    public ImmaDomaine sfrImmaDomaine(Sfr sfr) {
        this.sfrImmaDomaine = sfr;
        return this;
    }

    public void setSfrImmaDomaine(Sfr sfr) {
        this.sfrImmaDomaine = sfr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImmaDomaine)) {
            return false;
        }
        return id != null && id.equals(((ImmaDomaine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImmaDomaine{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", superficeTotInventorie=" + getSuperficeTotInventorie() +
            ", superficieTotImmatricule=" + getSuperficieTotImmatricule() +
            ", superficieTotEncadre=" + getSuperficieTotEncadre() +
            "}";
    }
}
