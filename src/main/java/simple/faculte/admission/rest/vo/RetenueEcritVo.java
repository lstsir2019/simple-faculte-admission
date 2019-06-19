/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.vo;

import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;

/**
 *
 * @author Anas
 */
public class RetenueEcritVo {

    private String refCandidat;
    private String refConcours;
    private String notePreselection;
    private boolean preselectione;
    private boolean retenueOral;
    private boolean admis;
    private EtudiantConcoursVo etudiantConcoursVo;
    private NoteConcoursVo noteConcoursVo;

    public NoteConcoursVo getNoteConcoursVo() {
        return noteConcoursVo;
    }

    public void setNoteConcoursVo(NoteConcoursVo noteConcoursVo) {
        this.noteConcoursVo = noteConcoursVo;
    }
    

    public String getRefConcours() {
        return refConcours;
    }

    public void setRefConcours(String refConcours) {
        this.refConcours = refConcours;
    }

    public String getRefCandidat() {
        return refCandidat;
    }

    public void setRefCandidat(String refCandidat) {
        this.refCandidat = refCandidat;
    }

    public String getNotePreselection() {
        return notePreselection;
    }

    public void setNotePreselection(String notePreselection) {
        this.notePreselection = notePreselection;
    }

    public boolean isPreselectione() {
        return preselectione;
    }

    public void setPreselectione(boolean preselectione) {
        this.preselectione = preselectione;
    }

    public boolean isRetenueOral() {
        return retenueOral;
    }

    public void setRetenueOral(boolean retenueOral) {
        this.retenueOral = retenueOral;
    }

    public boolean isAdmis() {
        return admis;
    }

    public void setAdmis(boolean admis) {
        this.admis = admis;
    }



    public EtudiantConcoursVo getEtudiantConcoursVo() {
        return etudiantConcoursVo;
    }

    public void setEtudiantConcoursVo(EtudiantConcoursVo etudiantConcoursVo) {
        this.etudiantConcoursVo = etudiantConcoursVo;
    }

}
