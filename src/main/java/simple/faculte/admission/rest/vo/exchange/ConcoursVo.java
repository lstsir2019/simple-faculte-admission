/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.vo.exchange;

import java.util.Date;
import java.util.List;

/**
 *
 * @author BOSS
 */
public class ConcoursVo {

    private Long id;
    private String reference;
    private String dateConcours;
    private String nbreetudiant;
    private List<ModuleConcoursVo> moduleConcoursVo;
    private String refFiliere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDateConcours() {
        return dateConcours;
    }

    public void setDateConcours(String dateConcours) {
        this.dateConcours = dateConcours;
    }

    public String getNbreetudiant() {
        return nbreetudiant;
    }

    public void setNbreetudiant(String nbreetudiant) {
        this.nbreetudiant = nbreetudiant;
    }

    public List<ModuleConcoursVo> getModuleConcoursVo() {
        return moduleConcoursVo;
    }

    public void setModuleConcoursVo(List<ModuleConcoursVo> moduleConcoursVo) {
        this.moduleConcoursVo = moduleConcoursVo;
    }

    public String getRefFiliere() {
        return refFiliere;
    }

    public void setRefFiliere(String refFiliere) {
        this.refFiliere = refFiliere;
    }

}
