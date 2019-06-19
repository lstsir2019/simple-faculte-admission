/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service.impl;

import com.anas.Inscription.common.util.NumberUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.dao.RetenueEcritDao;
import simple.faculte.admission.rest.proxy.ConcoursProxy;
import simple.faculte.admission.service.RetenueEcritService;
import simple.faculte.admission.service.RetenueFinalService;

/**
 *
 * @author Ragnarok
 */
@Service
public class RetenueFinalServiceImpl implements RetenueFinalService {

    @Autowired
    private RetenueEcritDao retenueEcritDao;
    @Autowired
    private RetenueEcritService retenueEcritService;
    @Autowired
    private ConcoursProxy concoursProxy;

    @Override
    public List<RetenueEcrit> findListeRetenueFinal(String refConcours) {
        List<RetenueEcrit> listE = retenueEcritDao.findByRefConcoursAndRetenueOralOrderByNoteConcoursNoteOralDesc(refConcours, true);
        if (listE == null || listE.isEmpty()) {
            return null;
        }
        int x = NumberUtil.toInt(concoursProxy.findByReference(refConcours).getNbreplace());

        for (int i = 0; i < x; i++) {
            if (i < listE.size()) {
                listE.get(i).setPreselectione(true);
                listE.get(i).setRetenueOral(true);
                listE.get(i).setAdmis(true);
            }

        }
        return listE;
    }

    @Override
    public int saveRetenueFinal(List<RetenueEcrit> retenueEcrits) {
        if (retenueEcrits.isEmpty() || retenueEcrits == null) {
            return -1;
        } else {
            for (RetenueEcrit retenueEcrit : retenueEcrits) {
                RetenueEcrit r = retenueEcritService.findByRefCandidat(retenueEcrit.getRefCandidat());
                r.setAdmis(true);
                retenueEcritDao.save(r);
            }
            return 1;
        }
    }

    @Override
    public List<RetenueEcrit> findListeRetenueFinalInBd(String refConcours) {
        return retenueEcritDao.findByRefConcoursAndAdmis(refConcours, true);
    }

}
