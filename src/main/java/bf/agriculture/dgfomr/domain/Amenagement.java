package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Amenagement.
 */
@Entity
@Table(name = "amenagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Amenagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "superficie_total", nullable = false)
    private Long superficieTotal;

    @NotNull
    @Column(name = "superficie_embave", nullable = false)
    private Long superficieEmbave;

    @NotNull
    @Column(name = "etats", nullable = false)
    private String etats;

    @ManyToOne
    @JsonIgnoreProperties(value = "amenagements", allowSetters = true)
    private TypeAmenagement typeamenagement;

    @ManyToOne
    @JsonIgnoreProperties(value = "amenagements", allowSetters = true)
    private CentreFormation centreformation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuperficieTotal() {
        return superficieTotal;
    }

    public Amenagement superficieTotal(Long superficieTotal) {
        this.superficieTotal = superficieTotal;
        return this;
    }

    public void setSuperficieTotal(Long superficieTotal) {
        this.superficieTotal = superficieTotal;
    }

    public Long getSuperficieEmbave() {
        return superficieEmbave;
    }

    public Amenagement superficieEmbave(Long superficieEmbave) {
        this.superficieEmbave = superficieEmbave;
        return this;
    }

    public void setSuperficieEmbave(Long superficieEmbave) {
        this.superficieEmbave = superficieEmbave;
    }

    public String getEtats() {
        return etats;
    }

    public Amenagement etats(String etats) {
        this.etats = etats;
        return this;
    }

    public void setEtats(String etats) {
        this.etats = etats;
    }

    public TypeAmenagement getTypeamenagement() {
        return typeamenagement;
    }

    public Amenagement typeamenagement(TypeAmenagement typeAmenagement) {
        this.typeamenagement = typeAmenagement;
        return this;
    }

    public void setTypeamenagement(TypeAmenagement typeAmenagement) {
        this.typeamenagement = typeAmenagement;
    }

    public CentreFormation getCentreformation() {
        return centreformation;
    }

    public Amenagement centreformation(CentreFormation centreFormation) {
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
        if (!(o instanceof Amenagement)) {
            return false;
        }
        return id != null && id.equals(((Amenagement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Amenagement{" +
            "id=" + getId() +
            ", superficieTotal=" + getSuperficieTotal() +
            ", superficieEmbave=" + getSuperficieEmbave() +
            ", etats='" + getEtats() + "'" +
            "}";
    }
}
