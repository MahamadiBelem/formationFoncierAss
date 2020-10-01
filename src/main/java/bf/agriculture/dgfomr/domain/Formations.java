package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Formations.
 */
@Entity
@Table(name = "formations")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Formations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "lebelle_formation")
    private String lebelleFormation;

    @Column(name = "cout_formation")
    private String coutFormation;

    @Column(name = "source_financement")
    private String sourceFinancement;

    @ManyToOne
    @JsonIgnoreProperties(value = "formations", allowSetters = true)
    private TypeFormation formationTypeFormation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public Formations theme(String theme) {
        this.theme = theme;
        return this;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLebelleFormation() {
        return lebelleFormation;
    }

    public Formations lebelleFormation(String lebelleFormation) {
        this.lebelleFormation = lebelleFormation;
        return this;
    }

    public void setLebelleFormation(String lebelleFormation) {
        this.lebelleFormation = lebelleFormation;
    }

    public String getCoutFormation() {
        return coutFormation;
    }

    public Formations coutFormation(String coutFormation) {
        this.coutFormation = coutFormation;
        return this;
    }

    public void setCoutFormation(String coutFormation) {
        this.coutFormation = coutFormation;
    }

    public String getSourceFinancement() {
        return sourceFinancement;
    }

    public Formations sourceFinancement(String sourceFinancement) {
        this.sourceFinancement = sourceFinancement;
        return this;
    }

    public void setSourceFinancement(String sourceFinancement) {
        this.sourceFinancement = sourceFinancement;
    }

    public TypeFormation getFormationTypeFormation() {
        return formationTypeFormation;
    }

    public Formations formationTypeFormation(TypeFormation typeFormation) {
        this.formationTypeFormation = typeFormation;
        return this;
    }

    public void setFormationTypeFormation(TypeFormation typeFormation) {
        this.formationTypeFormation = typeFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formations)) {
            return false;
        }
        return id != null && id.equals(((Formations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Formations{" +
            "id=" + getId() +
            ", theme='" + getTheme() + "'" +
            ", lebelleFormation='" + getLebelleFormation() + "'" +
            ", coutFormation='" + getCoutFormation() + "'" +
            ", sourceFinancement='" + getSourceFinancement() + "'" +
            "}";
    }
}
