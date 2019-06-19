/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service;

import java.util.List;
import simple.faculte.admission.bean.RetenueEcrit;

/**
 *
 * @author Ragnarok
 */
public interface RetenueFinalService {

    public List<RetenueEcrit> findListeRetenueFinal(String refConcours);
    public List<RetenueEcrit> findListeRetenueFinalInBd(String refConcours);

    public int saveRetenueFinal(List<RetenueEcrit> retenueEcrits);
}
