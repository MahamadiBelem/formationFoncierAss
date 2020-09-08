package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Infrastructure.
 */
@Entity
@Table(name = "infrastructure")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Infrastructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_elaboration", nullable = false)
    private LocalDate dateElaboration;

    @NotNull
    @Column(name = "fonctionnalite", nullable = false)
    private String fonctionnalite;

    @NotNull
    @Column(name = "etat", nullable = false)
    private String etat;

    @ManyToOne
    @JsonIgnoreProperties(value = "infrastructures", allowSetters = true)
    private TypeInfratructure typeinfrastructure;

    @ManyToOne
    @JsonIgnoreProperties(value = "infrastructures", allowSetters = true)
    private CentreFormation centreformation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateElaboration() {
        return dateElaboration;
    }

    public Infrastructure dateElaboration(LocalDate dateElaboration) {
        this.dateElaboration = dateElaboration;
        return this;
    }

    public void setDateElaboration(LocalDate dateElaboration) {
        this.dateElaboration = dateElaboration;
    }

    public String getFonctionnalite() {
        return fonctionnalite;
    }

    public Infrastructure fonctionnalite(String fonctionnalite) {
        this.fonctionnalite = fonctionnalite;
        return this;
    }

    public void setFonctionnalite(String fonctionnalite) {
        this.fonctionnalite = fonctionnalite;
    }

    public String getEtat() {
        return etat;
    }

    public Infrastructure etat(String etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public TypeInfratructure getTypeinfrastructure() {
        return typeinfrastructure;
    }

    public Infrastructure typeinfrastructure(TypeInfratructure typeInfratructure) {
        this.typeinfrastructure = typeInfratructure;
        return this;
    }

    public void setTypeinfrastructure(TypeInfratructure typeInfratructure) {
        this.typeinfrastructure = typeInfratructure;
    }

    public CentreFormation getCentreformation() {
        return centreformation;
    }

    public Infrastructure centreformation(CentreFormation centreFormation) {
        this.centreformation = centreFormation;
        return this;
    }

    public void setCentreformation(CentreFormation centreFormation) {
        this.centreformation = centreFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Infrastructure)) {
            return false;
        }
        return id != null && id.equals(((Infrastructure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Infrastructure{" +
            "id=" + getId() +
            ", dateElaboration='" + getDateElaboration() + "'" +
            ", fonctionnalite='" + getFonctionnalite() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
