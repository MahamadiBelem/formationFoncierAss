package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "niveauapprenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Apprenantes> apprenantes = new HashSet<>();

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

    public Set<Apprenantes> getApprenantes() {
        return apprenantes;
    }

    public NiveauInstruction apprenantes(Set<Apprenantes> apprenantes) {
        this.apprenantes = apprenantes;
        return this;
    }

    public NiveauInstruction addApprenantes(Apprenantes apprenantes) {
        this.apprenantes.add(apprenantes);
        apprenantes.setNiveauapprenant(this);
        return this;
    }

    public NiveauInstruction removeApprenantes(Apprenantes apprenantes) {
        this.apprenantes.remove(apprenantes);
        apprenantes.setNiveauapprenant(null);
        return this;
    }

    public void setApprenantes(Set<Apprenantes> apprenantes) {
        this.apprenantes = apprenantes;
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
