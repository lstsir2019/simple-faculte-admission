/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.vo.exchange;

/**
 *
 * @author BOSS
 */
public class CoefModuleConcoursVo {

    private Long id;
    private String coef;
    private ModuleConcoursVo moduleConcoursVo;
    private String refFiliere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoef() {
        return coef;
    }

    public void setCoef(String coef) {
        this.coef = coef;
    }

    public ModuleConcoursVo getModuleConcoursVo() {
        return moduleConcoursVo;
    }

    public void setModuleConcoursVo(ModuleConcoursVo moduleConcoursVo) {
        this.moduleConcoursVo = moduleConcoursVo;
    }

    public String getRefFiliere() {
        return refFiliere;
    }

    public void setRefFiliere(String refFiliere) {
        this.refFiliere = refFiliere;
    }

}
