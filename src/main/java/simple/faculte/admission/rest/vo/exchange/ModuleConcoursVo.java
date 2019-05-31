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
public class ModuleConcoursVo {

    private CoefModuleConcoursVo coefModuleConcoursVo;
    private Long id;
    private String refModule;
    private ConcoursVo concoursVo;

    public CoefModuleConcoursVo getCoefModuleConcoursVo() {
        return coefModuleConcoursVo;
    }

    public void setCoefModuleConcoursVo(CoefModuleConcoursVo coefModuleConcoursVo) {
        this.coefModuleConcoursVo = coefModuleConcoursVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefModule() {
        return refModule;
    }

    public void setRefModule(String refModule) {
        this.refModule = refModule;
    }

    public ConcoursVo getConcoursVo() {
        return concoursVo;
    }

    public void setConcoursVo(ConcoursVo concoursVo) {
        this.concoursVo = concoursVo;
    }

}
