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
public class NoteConcoursVo {

    private String noteEcrit;
    private String noteOral;
    private RetenueEcritVo retenueEcritVo;
    private EtudiantConcoursVo etudiantConcoursVo;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public EtudiantConcoursVo getEtudiantConcoursVo() {
        return etudiantConcoursVo;
    }

    public void setEtudiantConcoursVo(EtudiantConcoursVo etudiantConcoursVo) {
        this.etudiantConcoursVo = etudiantConcoursVo;
    }

    public String getNoteEcrit() {
        return noteEcrit;
    }

    public void setNoteEcrit(String noteEcrit) {
        this.noteEcrit = noteEcrit;
    }

    public String getNoteOral() {
        return noteOral;
    }

    public void setNoteOral(String noteOral) {
        this.noteOral = noteOral;
    }

    public RetenueEcritVo getRetenueEcritVo() {
        return retenueEcritVo;
    }

    public void setRetenueEcritVo(RetenueEcritVo retenueEcritVo) {
        this.retenueEcritVo = retenueEcritVo;
    }

}
