package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Installation.
 */
@Entity
@Table(name = "installation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Installation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "annees_installation", nullable = false)
    private String anneesInstallation;

    @NotNull
    @Column(name = "date_intallation", nullable = false)
    private LocalDate dateIntallation;

    @Column(name = "lieu_installation")
    private String lieuInstallation;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "installation_activiteinstallation",
               joinColumns = @JoinColumn(name = "installation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "activiteinstallation_id", referencedColumnName = "id"))
    private Set<ActiviteInstallation> activiteinstallations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "installation_kits",
               joinColumns = @JoinColumn(name = "installation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "kits_id", referencedColumnName = "id"))
    private Set<Kits> kits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnneesInstallation() {
        return anneesInstallation;
    }

    public Installation anneesInstallation(String anneesInstallation) {
        this.anneesInstallation = anneesInstallation;
        return this;
    }

    public void setAnneesInstallation(String anneesInstallation) {
        this.anneesInstallation = anneesInstallation;
    }

    public LocalDate getDateIntallation() {
        return dateIntallation;
    }

    public Installation dateIntallation(LocalDate dateIntallation) {
        this.dateIntallation = dateIntallation;
        return this;
    }

    public void setDateIntallation(LocalDate dateIntallation) {
        this.dateIntallation = dateIntallation;
    }

    public String getLieuInstallation() {
        return lieuInstallation;
    }

    public Installation lieuInstallation(String lieuInstallation) {
        this.lieuInstallation = lieuInstallation;
        return this;
    }

    public void setLieuInstallation(String lieuInstallation) {
        this.lieuInstallation = lieuInstallation;
    }

    public Set<ActiviteInstallation> getActiviteinstallations() {
        return activiteinstallations;
    }

    public Installation activiteinstallations(Set<ActiviteInstallation> activiteInstallations) {
        this.activiteinstallations = activiteInstallations;
        return this;
    }

    public Installation addActiviteinstallation(ActiviteInstallation activiteInstallation) {
        this.activiteinstallations.add(activiteInstallation);
        activiteInstallation.getInstallations().add(this);
        return this;
    }

    public Installation removeActiviteinstallation(ActiviteInstallation activiteInstallation) {
        this.activiteinstallations.remove(activiteInstallation);
        activiteInstallation.getInstallations().remove(this);
        return this;
    }

    public void setActiviteinstallations(Set<ActiviteInstallation> activiteInstallations) {
        this.activiteinstallations = activiteInstallations;
    }

    public Set<Kits> getKits() {
        return kits;
    }

    public Installation kits(Set<Kits> kits) {
        this.kits = kits;
        return this;
    }

    public Installation addKits(Kits kits) {
        this.kits.add(kits);
        kits.getInstallations().add(this);
        return this;
    }

    public Installation removeKits(Kits kits) {
        this.kits.remove(kits);
        kits.getInstallations().remove(this);
        return this;
    }

    public void setKits(Set<Kits> kits) {
        this.kits = kits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Installation)) {
            return false;
        }
        return id != null && id.equals(((Installation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Installation{" +
            "id=" + getId() +
            ", anneesInstallation='" + getAnneesInstallation() + "'" +
            ", dateIntallation='" + getDateIntallation() + "'" +
            ", lieuInstallation='" + getLieuInstallation() + "'" +
            "}";
    }
}
