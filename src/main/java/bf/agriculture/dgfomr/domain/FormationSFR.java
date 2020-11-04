package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A FormationSFR.
 */
@Entity
@Table(name = "formation_sfr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormationSFR implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "nbr_agent_forme")
    private Integer nbrAgentForme;

    @Column(name = "theme_formation")
    private String themeFormation;

    @Column(name = "duree_tot_formation")
    private Integer dureeTotFormation;

    @ManyToOne
    @JsonIgnoreProperties(value = "formationSFRS", allowSetters = true)
    private Sfr sfrFormation;

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

    public FormationSFR annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getNbrAgentForme() {
        return nbrAgentForme;
    }

    public FormationSFR nbrAgentForme(Integer nbrAgentForme) {
        this.nbrAgentForme = nbrAgentForme;
        return this;
    }

    public void setNbrAgentForme(Integer nbrAgentForme) {
        this.nbrAgentForme = nbrAgentForme;
    }

    public String getThemeFormation() {
        return themeFormation;
    }

    public FormationSFR themeFormation(String themeFormation) {
        this.themeFormation = themeFormation;
        return this;
    }

    public void setThemeFormation(String themeFormation) {
        this.themeFormation = themeFormation;
    }

    public Integer getDureeTotFormation() {
        return dureeTotFormation;
    }

    public FormationSFR dureeTotFormation(Integer dureeTotFormation) {
        this.dureeTotFormation = dureeTotFormation;
        return this;
    }

    public void setDureeTotFormation(Integer dureeTotFormation) {
        this.dureeTotFormation = dureeTotFormation;
    }

    public Sfr getSfrFormation() {
        return sfrFormation;
    }

    public FormationSFR sfrFormation(Sfr sfr) {
        this.sfrFormation = sfr;
        return this;
    }

    public void setSfrFormation(Sfr sfr) {
        this.sfrFormation = sfr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormationSFR)) {
            return false;
        }
        return id != null && id.equals(((FormationSFR) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormationSFR{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", nbrAgentForme=" + getNbrAgentForme() +
            ", themeFormation='" + getThemeFormation() + "'" +
            ", dureeTotFormation=" + getDureeTotFormation() +
            "}";
    }
}
