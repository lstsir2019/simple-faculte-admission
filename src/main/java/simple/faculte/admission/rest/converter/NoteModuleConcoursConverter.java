/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.converter;

import com.anas.Inscription.common.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simple.faculte.admission.bean.NoteModuleConcours;
import simple.faculte.admission.rest.vo.NoteModuleConcoursVo;
import simple.faculte.admission.service.RetenueEcritService;

/**
 *
 * @author Anas
 */
@Component
public class NoteModuleConcoursConverter extends AbstractConverter<NoteModuleConcours, NoteModuleConcoursVo> {

    @Autowired
    private RetenueEcritConverter retenueEcritConverter;
    @Autowired
    private RetenueEcritService retenueEcritService;

    @Override
    public NoteModuleConcours toItem(NoteModuleConcoursVo vo) {
        if (vo == null) {
            return null;
        } else {
            NoteModuleConcours item = new NoteModuleConcours();
            item.setNote(NumberUtil.toDouble(vo.getNote()));
            item.setRefModuleConcours(vo.getRefModuleConcours());
            item.setRetenueEcrit(retenueEcritConverter.toItem(vo.getRetenueEcritVo()));
            return item;
        }
    }

    @Override
    public NoteModuleConcoursVo toVo(NoteModuleConcours item) {
        if (item == null) {
            return null;
        } else {
            NoteModuleConcoursVo vo = new NoteModuleConcoursVo();
            vo.setNote(NumberUtil.toString(item.getNote()));
            vo.setRefModuleConcours(item.getRefModuleConcours());
            vo.setRetenueEcritVo(retenueEcritConverter.toVo(item.getRetenueEcrit()));
            vo.setEtudiantConcoursVo(retenueEcritService.findByCne(vo.getRetenueEcritVo().getRefCandidat()));
            return vo;
        }
    }

    @Override
    public void init() {
        
    }

    public RetenueEcritConverter getRetenueEcritConverter() {
        return retenueEcritConverter;
    }

    public void setRetenueEcritConverter(RetenueEcritConverter retenueEcritConverter) {
        this.retenueEcritConverter = retenueEcritConverter;
    }

    public RetenueEcritService getRetenueEcritService() {
        return retenueEcritService;
    }

    public void setRetenueEcritService(RetenueEcritService retenueEcritService) {
        this.retenueEcritService = retenueEcritService;
    }

}
