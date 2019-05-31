/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.bean;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Anas
 */
@Entity
public class RetenueEcrit implements Serializable {

    @OneToMany( cascade = CascadeType.REMOVE, mappedBy = "retenueEcrit")
    private List<NoteModuleConcours> noteModuleConcourss;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String refCandidat;
    private String refConcours;
    private Double notePreselection;
    private boolean Preselectione;
    private boolean RetenueOral;
    private boolean Admis;
    @OneToOne(cascade = CascadeType.REMOVE)
    private NoteConcours noteConcours;

    public NoteConcours getNoteConcours() {
        return noteConcours;
    }

    public void setNoteConcours(NoteConcours noteConcours) {
        this.noteConcours = noteConcours;
    }
    
    @JsonIgnore
    public List<NoteModuleConcours> getNoteModuleConcourss() {
        return noteModuleConcourss;
    }

    @JsonSetter
    public void setNoteModuleConcourss(List<NoteModuleConcours> noteModuleConcourss) {
        this.noteModuleConcourss = noteModuleConcourss;
    }

    public String getRefConcours() {
        return refConcours;
    }

    public void setRefConcours(String refConcours) {
        this.refConcours = refConcours;
    }

    public boolean isRetenueOral() {
        return RetenueOral;
    }

    public void setRetenueOral(boolean RetenueOral) {
        this.RetenueOral = RetenueOral;
    }

    public boolean isAdmis() {
        return Admis;
    }

    public void setAdmis(boolean Admis) {
        this.Admis = Admis;
    }

    public boolean isPreselectione() {
        return Preselectione;
    }

    public void setPreselectione(boolean Preselectione) {
        this.Preselectione = Preselectione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefCandidat() {
        return refCandidat;
    }

    public void setRefCandidat(String refCandidat) {
        this.refCandidat = refCandidat;
    }

    public Double getNotePreselection() {
        return notePreselection;
    }

    public void setNotePreselection(Double notePreselection) {
        this.notePreselection = notePreselection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetenueEcrit)) {
            return false;
        }
        RetenueEcrit other = (RetenueEcrit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simple.faculte.admission.bean.RetenueEcrit[ id=" + id + " ]";
    }

}
