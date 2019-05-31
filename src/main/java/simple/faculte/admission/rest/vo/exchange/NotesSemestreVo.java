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
public class NotesSemestreVo {

    private Long id;
    private EtudiantConcoursVo etudiantConcoursVo;
    private String refSemestre;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtudiantConcoursVo getEtudiantConcoursVo() {
        return etudiantConcoursVo;
    }

    public void setEtudiantConcoursVo(EtudiantConcoursVo etudiantConcoursVo) {
        this.etudiantConcoursVo = etudiantConcoursVo;
    }

    public String getRefSemestre() {
        return refSemestre;
    }

    public void setRefSemestre(String refSemestre) {
        this.refSemestre = refSemestre;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
