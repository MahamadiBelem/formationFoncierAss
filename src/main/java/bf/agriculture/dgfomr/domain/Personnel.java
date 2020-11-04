package bf.agriculture.dgfomr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Personnel.
 */
@Entity
@Table(name = "personnel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom_pers")
    private String nomPers;

    @Column(name = "prenom_pers")
    private String prenomPers;

    @Column(name = "tel_pers")
    private String telPers;

    @Column(name = "email_pers")
    private String emailPers;

    @ManyToOne
    @JsonIgnoreProperties(value = "personnel", allowSetters = true)
    private Sfr sfrPersonnel;

    @ManyToOne
    @JsonIgnoreProperties(value = "personnel", allowSetters = true)
    private ProfilPersonnel personnel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPers() {
        return nomPers;
    }

    public Personnel nomPers(String nomPers) {
        this.nomPers = nomPers;
        return this;
    }

    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

    public String getPrenomPers() {
        return prenomPers;
    }

    public Personnel prenomPers(String prenomPers) {
        this.prenomPers = prenomPers;
        return this;
    }

    public void setPrenomPers(String prenomPers) {
        this.prenomPers = prenomPers;
    }

    public String getTelPers() {
        return telPers;
    }

    public Personnel telPers(String telPers) {
        this.telPers = telPers;
        return this;
    }

    public void setTelPers(String telPers) {
        this.telPers = telPers;
    }

    public String getEmailPers() {
        return emailPers;
    }

    public Personnel emailPers(String emailPers) {
        this.emailPers = emailPers;
        return this;
    }

    public void setEmailPers(String emailPers) {
        this.emailPers = emailPers;
    }

    public Sfr getSfrPersonnel() {
        return sfrPersonnel;
    }

    public Personnel sfrPersonnel(Sfr sfr) {
        this.sfrPersonnel = sfr;
        return this;
    }

    public void setSfrPersonnel(Sfr sfr) {
        this.sfrPersonnel = sfr;
    }

    public ProfilPersonnel getPersonnel() {
        return personnel;
    }

    public Personnel personnel(ProfilPersonnel profilPersonnel) {
        this.personnel = profilPersonnel;
        return this;
    }

    public void setPersonnel(ProfilPersonnel profilPersonnel) {
        this.personnel = profilPersonnel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personnel)) {
            return false;
        }
        return id != null && id.equals(((Personnel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personnel{" +
            "id=" + getId() +
            ", nomPers='" + getNomPers() + "'" +
            ", prenomPers='" + getPrenomPers() + "'" +
            ", telPers='" + getTelPers() + "'" +
            ", emailPers='" + getEmailPers() + "'" +
            "}";
    }
}
