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

    @Column(name = "iskit")
    private Boolean iskit;

    @OneToOne
    @JoinColumn(unique = true)
    private SortiePromotion installation;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "installation_activiteinstallation",
               joinColumns = @JoinColumn(name = "installation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "activiteinstallation_id", referencedColumnName = "id"))
    private Set<ActiviteInstallation> activiteinstallations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "installation_source_intallation",
               joinColumns = @JoinColumn(name = "installation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "source_intallation_id", referencedColumnName = "id"))
    private Set<SourceFiancement> sourceIntallations = new HashSet<>();

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

    public Boolean isIskit() {
        return iskit;
    }

    public Installation iskit(Boolean iskit) {
        this.iskit = iskit;
        return this;
    }

    public void setIskit(Boolean iskit) {
        this.iskit = iskit;
    }

    public SortiePromotion getInstallation() {
        return installation;
    }

    public Installation installation(SortiePromotion sortiePromotion) {
        this.installation = sortiePromotion;
        return this;
    }

    public void setInstallation(SortiePromotion sortiePromotion) {
        this.installation = sortiePromotion;
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

    public Set<SourceFiancement> getSourceIntallations() {
        return sourceIntallations;
    }

    public Installation sourceIntallations(Set<SourceFiancement> sourceFiancements) {
        this.sourceIntallations = sourceFiancements;
        return this;
    }

    public Installation addSourceIntallation(SourceFiancement sourceFiancement) {
        this.sourceIntallations.add(sourceFiancement);
        sourceFiancement.getInstallationSources().add(this);
        return this;
    }

    public Installation removeSourceIntallation(SourceFiancement sourceFiancement) {
        this.sourceIntallations.remove(sourceFiancement);
        sourceFiancement.getInstallationSources().remove(this);
        return this;
    }

    public void setSourceIntallations(Set<SourceFiancement> sourceFiancements) {
        this.sourceIntallations = sourceFiancements;
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
            ", iskit='" + isIskit() + "'" +
            "}";
    }
}
