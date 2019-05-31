/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.vo.exchange;

/**
 *
 * @author Anas
 */
public class DiplomeEtudiantVo {

    private Long id;
    private String refDiplome;
    private String anneeObtention;
    private String mention;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefDiplome() {
        return refDiplome;
    }

    public void setRefDiplome(String refDiplome) {
        this.refDiplome = refDiplome;
    }

    public String getAnneeObtention() {
        return anneeObtention;
    }

    public void setAnneeObtention(String anneeObtention) {
        this.anneeObtention = anneeObtention;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

}
