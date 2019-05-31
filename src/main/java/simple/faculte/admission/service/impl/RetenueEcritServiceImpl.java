/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service.impl;

import com.anas.Inscription.common.util.NumberUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.faculte.admission.bean.NoteConcours;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.dao.NoteConcoursDao;
import simple.faculte.admission.dao.RetenueEcritDao;
import simple.faculte.admission.rest.proxy.CandidatProxy;
import simple.faculte.admission.rest.proxy.ConcoursProxy;
import simple.faculte.admission.rest.vo.exchange.ConcoursVo;
import simple.faculte.admission.rest.vo.exchange.DiplomeEtudiantVo;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;
import simple.faculte.admission.service.RetenueEcritService;

/**
 *
 * @author Anas
 */
@Service
public class RetenueEcritServiceImpl implements RetenueEcritService {

    @Autowired
    RetenueEcritDao retenueEcritDao;
    @Autowired
    CandidatProxy candidatProxy;
    @Autowired
    ConcoursProxy concoursProxy;
    @Autowired
    NoteConcoursDao noteConcoursDao;

    @Override
    public List<RetenueEcrit> findListeReteues(String refConcours) {
        List<RetenueEcrit> listEtudiantRetenue = new ArrayList();
        List<EtudiantConcoursVo> listE = findByRefConcours(refConcours);
        if (listE == null || listE.isEmpty()) {
            return null;
        }
        listE.sort(
                (etudiantConcoursVo1, etudiantConcoursVo2) -> {
                    return (int) (new Double(etudiantConcoursVo2.getDiplomeEtudiantVo().getNote()) - new Double(etudiantConcoursVo1.getDiplomeEtudiantVo().getNote())); //To change body of generated lambdas, choose Tools | Templates.
                });
        for (int i = 0; i < listE.size(); i++) {
            RetenueEcrit retenueEcrit = new RetenueEcrit();
            retenueEcrit.setRefCandidat(listE.get(i).getCne());
            retenueEcrit.setNotePreselection(NumberUtil.toDouble(listE.get(i).getDiplomeEtudiantVo().getNote()));
            listEtudiantRetenue.add(retenueEcrit);
        }
        for (int i = 0; i < 5; i++) {
            listEtudiantRetenue.get(i).setPreselectione(true);
        }
        return listEtudiantRetenue;
    }

    @Override
    public List<EtudiantConcoursVo> findAllCandidats() {
        return candidatProxy.findAll();
    }

    @Override
    public ConcoursVo findByReference(String reference) {
        return concoursProxy.findByReference(reference);
    }

    @Override
    public int savePreselection(List<RetenueEcrit> retenueEcrits) {
        if (retenueEcrits.isEmpty() || retenueEcrits == null) {
            return -1;
        }
        for (RetenueEcrit retenueEcrit : retenueEcrits) {
            NoteConcours noteConours = new NoteConcours();
            noteConours.setNoteEcrit(0);
            noteConours.setNoteOral(0);
            retenueEcritDao.save(retenueEcrit);
            noteConours.setRetenueEcrit(retenueEcrit);
            noteConcoursDao.save(noteConours);
            retenueEcrit.setNoteConcours(noteConours);
            retenueEcritDao.save(retenueEcrit);
            
        }
        return 1;
    }

    @Override
    public List<EtudiantConcoursVo> findByRefConcours(String refConcours) {
        return candidatProxy.findByRefConcours(refConcours);
    }

    @Override
    public EtudiantConcoursVo findByCne(String cne) {
        return candidatProxy.findByCne(cne);
    }

    @Override
    public RetenueEcrit findByRefCandidat(String refConcours) {
        return retenueEcritDao.findByRefCandidat(refConcours);
    }

    @Override
    public List<RetenueEcrit> findRetenusByRefConcours(String refConcours) {
        return this.retenueEcritDao.findByRefConcours(refConcours);
    }

}
