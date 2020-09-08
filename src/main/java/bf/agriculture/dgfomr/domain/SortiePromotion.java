package bf.agriculture.dgfomr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A SortiePromotion.
 */
@Entity
@Table(name = "sortie_promotion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SortiePromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_sortie", nullable = false)
    private LocalDate dateSortie;

    @NotNull
    @Column(name = "annees_sortie", nullable = false)
    private Integer anneesSortie;

    @Column(name = "motif")
    private String motif;

    @OneToOne
    @JoinColumn(unique = true)
    private Installation installation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public SortiePromotion dateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
        return this;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Integer getAnneesSortie() {
        return anneesSortie;
    }

    public SortiePromotion anneesSortie(Integer anneesSortie) {
        this.anneesSortie = anneesSortie;
        return this;
    }

    public void setAnneesSortie(Integer anneesSortie) {
        this.anneesSortie = anneesSortie;
    }

    public String getMotif() {
        return motif;
    }

    public SortiePromotion motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Installation getInstallation() {
        return installation;
    }

    public SortiePromotion installation(Installation installation) {
        this.installation = installation;
        return this;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SortiePromotion)) {
            return false;
        }
        return id != null && id.equals(((SortiePromotion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SortiePromotion{" +
            "id=" + getId() +
            ", dateSortie='" + getDateSortie() + "'" +
            ", anneesSortie=" + getAnneesSortie() +
            ", motif='" + getMotif() + "'" +
            "}";
    }
}
