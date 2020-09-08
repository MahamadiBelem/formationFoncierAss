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
    private TypeFormation typeamenagement;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "formations_formation",
               joinColumns = @JoinColumn(name = "formations_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "formation_id", referencedColumnName = "id"))
    private Set<Apprenantes> formations = new HashSet<>();

    @ManyToMany(mappedBy = "formations")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<CentreFormation> centreformations = new HashSet<>();

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

    public TypeFormation getTypeamenagement() {
        return typeamenagement;
    }

    public Formations typeamenagement(TypeFormation typeFormation) {
        this.typeamenagement = typeFormation;
        return this;
    }

    public void setTypeamenagement(TypeFormation typeFormation) {
        this.typeamenagement = typeFormation;
    }

    public Set<Apprenantes> getFormations() {
        return formations;
    }

    public Formations formations(Set<Apprenantes> apprenantes) {
        this.formations = apprenantes;
        return this;
    }

    public Formations addFormation(Apprenantes apprenantes) {
        this.formations.add(apprenantes);
        apprenantes.getCentreformations().add(this);
        return this;
    }

    public Formations removeFormation(Apprenantes apprenantes) {
        this.formations.remove(apprenantes);
        apprenantes.getCentreformations().remove(this);
        return this;
    }

    public void setFormations(Set<Apprenantes> apprenantes) {
        this.formations = apprenantes;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public Formations centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public Formations addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getFormations().add(this);
        return this;
    }

    public Formations removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getFormations().remove(this);
        return this;
    }

    public void setCentreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
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
