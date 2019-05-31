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
 * @author Anas
 */
public interface RetenueOralService  {
    public List<RetenueEcrit> findListeRetenueOral(String refConcours);
    public int saveRetenueOral(List<RetenueEcrit> retenueEcrits);
}
