/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.converter;

import com.anas.Inscription.common.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simple.faculte.admission.bean.NoteConcours;
import simple.faculte.admission.rest.vo.NoteConcoursVo;

/**
 *
 * @author Anas
 */
@Component
public class NoteConcoursConverter extends AbstractConverter<NoteConcours, NoteConcoursVo> {

    @Autowired
    private RetenueEcritConverter retenueEcritConverter;
    private boolean retenueEcrit;

    @Override
    public NoteConcours toItem(NoteConcoursVo vo) {
        if (vo == null) {
            return null;
        } else {
            NoteConcours item = new NoteConcours();
            item.setNoteEcrit(NumberUtil.toDouble(vo.getNoteEcrit()));
            item.setNoteOral(NumberUtil.toDouble(vo.getNoteOral()));
            if (retenueEcrit) {
                item.setRetenueEcrit(retenueEcritConverter.toItem(vo.getRetenueEcritVo()));

            }

            return item;
        }
    }

    @Override
    public NoteConcoursVo toVo(NoteConcours item) {
        if (item == null) {
            return null;
        } else {
            NoteConcoursVo vo = new NoteConcoursVo();
            vo.setNoteEcrit(NumberUtil.toString(item.getNoteEcrit()));
            vo.setNoteOral(NumberUtil.toString(item.getNoteOral()));
            if (retenueEcrit) {
                vo.setRetenueEcritVo(retenueEcritConverter.toVo(item.getRetenueEcrit()));
            }
            return vo;
        }
    }

    public RetenueEcritConverter getRetenueEcritConverter() {
        return retenueEcritConverter;
    }

    public void setRetenueEcritConverter(RetenueEcritConverter retenueEcritConverter) {
        this.retenueEcritConverter = retenueEcritConverter;
    }

    public boolean isRetenueEcrit() {
        return retenueEcrit;
    }

    public void setRetenueEcrit(boolean retenueEcrit) {
        this.retenueEcrit = retenueEcrit;
    }

    @Override
    public void init() {
        retenueEcrit = true;
    }

}
