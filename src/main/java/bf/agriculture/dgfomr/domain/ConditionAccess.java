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
 * A ConditionAccess.
 */
@Entity
@Table(name = "condition_access")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConditionAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_conditon", nullable = false)
    private String libelleConditon;

    @ManyToMany(mappedBy = "conditionaccesses")
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

    public String getLibelleConditon() {
        return libelleConditon;
    }

    public ConditionAccess libelleConditon(String libelleConditon) {
        this.libelleConditon = libelleConditon;
        return this;
    }

    public void setLibelleConditon(String libelleConditon) {
        this.libelleConditon = libelleConditon;
    }

    public Set<CentreFormation> getCentreformations() {
        return centreformations;
    }

    public ConditionAccess centreformations(Set<CentreFormation> centreFormations) {
        this.centreformations = centreFormations;
        return this;
    }

    public ConditionAccess addCentreformation(CentreFormation centreFormation) {
        this.centreformations.add(centreFormation);
        centreFormation.getConditionaccesses().add(this);
        return this;
    }

    public ConditionAccess removeCentreformation(CentreFormation centreFormation) {
        this.centreformations.remove(centreFormation);
        centreFormation.getConditionaccesses().remove(this);
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
        if (!(o instanceof ConditionAccess)) {
            return false;
        }
        return id != null && id.equals(((ConditionAccess) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConditionAccess{" +
            "id=" + getId() +
            ", libelleConditon='" + getLibelleConditon() + "'" +
            "}";
    }
}
