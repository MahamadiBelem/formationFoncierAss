package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A FormationCentreFormation.
 */
@Entity
@Table(name = "formation_centre_formation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormationCentreFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datedebut")
    private LocalDate datedebut;

    @Column(name = "date_clore")
    private LocalDate dateClore;

    @ManyToOne
    @JsonIgnoreProperties(value = "formationCentreFormations", allowSetters = true)
    private CentreFormation formationcentre;

    @ManyToOne
    @JsonIgnoreProperties(value = "formationCentreFormations", allowSetters = true)
    private Formations centreformation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public FormationCentreFormation datedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
        return this;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDateClore() {
        return dateClore;
    }

    public FormationCentreFormation dateClore(LocalDate dateClore) {
        this.dateClore = dateClore;
        return this;
    }

    public void setDateClore(LocalDate dateClore) {
        this.dateClore = dateClore;
    }

    public CentreFormation getFormationcentre() {
        return formationcentre;
    }

    public FormationCentreFormation formationcentre(CentreFormation centreFormation) {
        this.formationcentre = centreFormation;
        return this;
    }

    public void setFormationcentre(CentreFormation centreFormation) {
        this.formationcentre = centreFormation;
    }

    public Formations getCentreformation() {
        return centreformation;
    }

    public FormationCentreFormation centreformation(Formations formations) {
        this.centreformation = formations;
        return this;
    }

    public void setCentreformation(Formations formations) {
        this.centreformation = formations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormationCentreFormation)) {
            return false;
        }
        return id != null && id.equals(((FormationCentreFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormationCentreFormation{" +
            "id=" + getId() +
            ", datedebut='" + getDatedebut() + "'" +
            ", dateClore='" + getDateClore() + "'" +
            "}";
    }
}
