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
public class NoteModuleConcoursVo {

    private RetenueEcritVo retenueEcritVo;
    private EtudiantConcoursVo etudiantConcoursVo;
    private String refModuleConcours;
    private String note;

    public EtudiantConcoursVo getEtudiantConcoursVo() {
        return etudiantConcoursVo;
    }

    public void setEtudiantConcoursVo(EtudiantConcoursVo etudiantConcoursVo) {
        this.etudiantConcoursVo = etudiantConcoursVo;
    }

    public RetenueEcritVo getRetenueEcritVo() {
        return retenueEcritVo;
    }

    public void setRetenueEcritVo(RetenueEcritVo retenueEcritVo) {
        this.retenueEcritVo = retenueEcritVo;
    }

    public String getRefModuleConcours() {
        return refModuleConcours;
    }

    public void setRefModuleConcours(String refModuleConcours) {
        this.refModuleConcours = refModuleConcours;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
