/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.converter;

import com.anas.Inscription.common.util.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.rest.vo.RetenueEcritVo;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;
import simple.faculte.admission.service.RetenueEcritService;

/**
 *
 * @author Anas
 */
@Component
public class RetenueEcritConverter extends AbstractConverter<RetenueEcrit, RetenueEcritVo> {

    @Autowired
    private RetenueEcritService retenueEcritService;
    @Autowired
    private NoteConcoursConverter noteConcoursConverter;

    private boolean noteConcours;

    @Override
    public RetenueEcrit toItem(RetenueEcritVo vo) {
        if (vo == null) {
            return null;
        } else {
            RetenueEcrit retenueEcrit = new RetenueEcrit();
            retenueEcrit.setRefCandidat(vo.getRefCandidat());
            retenueEcrit.setNotePreselection(NumberUtil.toDouble(vo.getNotePreselection()));
            retenueEcrit.setPreselectione(vo.isPreselectione());
            retenueEcrit.setRetenueOral(vo.isRetenueOral());
            retenueEcrit.setRefConcours(vo.getRefConcours());
            retenueEcrit.setAdmis(vo.isAdmis());
            if (noteConcours) {
                retenueEcrit.setNoteConcours(noteConcoursConverter.toItem(vo.getNoteConcoursVo()));
            }
            return retenueEcrit;
        }
    }

    @Override
    public RetenueEcritVo toVo(RetenueEcrit item) {
        RetenueEcritVo vo = new RetenueEcritVo();
        vo.setRefCandidat(item.getRefCandidat());
        vo.setNotePreselection(NumberUtil.toString(item.getNotePreselection()));
        vo.setPreselectione(item.isPreselectione());
        vo.setRetenueOral(item.isRetenueOral());
        vo.setAdmis(item.isAdmis());
        vo.setRefConcours(item.getRefConcours());
        EtudiantConcoursVo e = retenueEcritService.findByCne(vo.getRefCandidat());
        if (e == null) {
            e = new EtudiantConcoursVo();
        }
         if (noteConcours) {
                vo.setNoteConcoursVo(noteConcoursConverter.toVo(item.getNoteConcours()));
            }
        vo.setEtudiantConcoursVo(e);
        return vo;
    }

    @Override
    public void init() {
       noteConcours = true;
    }

    public RetenueEcritService getRetenueEcritService() {
        return retenueEcritService;
    }

    public void setRetenueEcritService(RetenueEcritService retenueEcritService) {
        this.retenueEcritService = retenueEcritService;
    }

    public boolean isNoteConcours() {
        return noteConcours;
    }

    public void setNoteConcours(boolean noteConcours) {
        this.noteConcours = noteConcours;
    }

    public NoteConcoursConverter getNoteConcoursConverter() {
        return noteConcoursConverter;
    }

    public void setNoteConcoursConverter(NoteConcoursConverter noteConcoursConverter) {
        this.noteConcoursConverter = noteConcoursConverter;
    }

}
