/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service.impl;

import com.anas.Inscription.common.util.NumberUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;
import simple.faculte.admission.service.RetenueEcritService;
import simple.faculte.admission.service.RetenueOralService;

/**
 *
 * @author Anas
 */
@Service
public class RetenueOralServiceImpl implements RetenueOralService {

    @Autowired
    private RetenueEcritService retenueEcritService;

    @Override
    public List<RetenueEcrit> findListeRetenueOral(String refConcours) {

        List<RetenueEcrit> listE = retenueEcritService.findRetenusByRefConcours(refConcours);
        if (listE == null || listE.isEmpty()) {
            return null;
        }
        listE.sort(
                (retenueEcrit1, retenueEcrit2) -> {
                    return (int) (new Double(retenueEcrit2.getNoteConcours().getNoteEcrit()) - new Double(retenueEcrit1.getNoteConcours().getNoteEcrit())); //To change body of generated lambdas, choose Tools | Templates.
                });
        for (int i = 0; i < 3; i++) {
            listE.get(i).setPreselectione(true);
            listE.get(i).setRetenueOral(true);
        }
        return listE;
    }

    @Override
    public int saveRetenueOral(List<RetenueEcrit> retenueEcrits) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
