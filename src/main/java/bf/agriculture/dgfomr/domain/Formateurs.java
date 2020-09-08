package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Formateurs.
 */
@Entity
@Table(name = "formateurs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Formateurs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @NotNull
    @Column(name = "specialite", nullable = false)
    private String specialite;

    @NotNull
    @Column(name = "regime", nullable = false)
    private String regime;

    @ManyToMany(mappedBy = "formateurs")
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

    public String getNomComplet() {
        return nomComplet;
    }

    public Formateurs nomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
        return this;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Formateurs specialite(String specialite) {
        this.specialite = specialite;
        return this;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getRegime() {
        return regime;
    }

    public Formateurs regime(String regime) {
        this.regime = regime;
        return this;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public Formateurs centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public Formateurs addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getFormateurs().add(this);
        return this;
    }

    public Formateurs removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getFormateurs().remove(this);
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
        if (!(o instanceof Formateurs)) {
            return false;
        }
        return id != null && id.equals(((Formateurs) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Formateurs{" +
            "id=" + getId() +
            ", nomComplet='" + getNomComplet() + "'" +
            ", specialite='" + getSpecialite() + "'" +
            ", regime='" + getRegime() + "'" +
            "}";
    }
}
