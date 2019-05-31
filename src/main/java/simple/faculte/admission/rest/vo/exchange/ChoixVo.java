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
public class ChoixVo {

    private Long id;
    private EtudiantConcoursVo etudiantConcoursVo;
    private String numChoix;
    private String refConcours;

    public EtudiantConcoursVo getEtudiantConcoursVo() {
        return etudiantConcoursVo;
    }

    public void setEtudiantConcoursVo(EtudiantConcoursVo etudiantConcoursVo) {
        this.etudiantConcoursVo = etudiantConcoursVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumChoix() {
        return numChoix;
    }

    public void setNumChoix(String numChoix) {
        this.numChoix = numChoix;
    }

    public String getRefConcours() {
        return refConcours;
    }

    public void setRefConcours(String refConcours) {
        this.refConcours = refConcours;
    }

}
