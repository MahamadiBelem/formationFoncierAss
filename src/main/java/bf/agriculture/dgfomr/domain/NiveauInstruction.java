package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NiveauInstruction.
 */
@Entity
@Table(name = "niveau_instruction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NiveauInstruction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "niveau_instruction", nullable = false)
    private String niveauInstruction;

    @ManyToOne
    @JsonIgnoreProperties(value = "niveauInstructions", allowSetters = true)
    private Apprenantes apprenants;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveauInstruction() {
        return niveauInstruction;
    }

    public NiveauInstruction niveauInstruction(String niveauInstruction) {
        this.niveauInstruction = niveauInstruction;
        return this;
    }

    public void setNiveauInstruction(String niveauInstruction) {
        this.niveauInstruction = niveauInstruction;
    }

    public Apprenantes getApprenants() {
        return apprenants;
    }

    public NiveauInstruction apprenants(Apprenantes apprenantes) {
        this.apprenants = apprenantes;
        return this;
    }

    public void setApprenants(Apprenantes apprenantes) {
        this.apprenants = apprenantes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NiveauInstruction)) {
            return false;
        }
        return id != null && id.equals(((NiveauInstruction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NiveauInstruction{" +
            "id=" + getId() +
            ", niveauInstruction='" + getNiveauInstruction() + "'" +
            "}";
    }
}
