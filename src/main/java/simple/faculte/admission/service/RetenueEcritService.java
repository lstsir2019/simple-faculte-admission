/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service;

import java.io.File;
import java.util.List;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.rest.vo.exchange.ConcoursVo;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;

/**
 *
 * @author Anas
 */
public interface RetenueEcritService {

    public List<RetenueEcrit> findListeReteues(String refConcours);

    public List<EtudiantConcoursVo> findAllCandidats();

    public RetenueEcrit findByRefCandidat(String refConcours);

    public List<EtudiantConcoursVo> findByRefConcours(String refConcours);

    public ConcoursVo findByReference(String reference);

    public int savePreselection(List<RetenueEcrit> retenueEcrits);

    public EtudiantConcoursVo findByCne(String cne);
    public List<RetenueEcrit> findRetenusByRefConcours(String refConcours);

}
