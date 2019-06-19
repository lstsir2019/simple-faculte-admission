/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.proxy;

import com.anas.Inscription.common.util.NumberUtil;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;

/**
 *
 * @author Anas
 */
@FeignClient(name = "zuul-server")
public interface CandidatProxy {

    @GetMapping("/Inscription-api/inscription/etudiants/")
    public List<EtudiantConcoursVo> findAll();

    @GetMapping("/Inscription-api/inscription/etudiants/listePostule/refConcours/{reference}")
    public List<EtudiantConcoursVo> findByRefConcours(@PathVariable("reference") String reference);

    @GetMapping("/Inscription-api/inscription/etudiants/liste_etudiants/{cne}")
    public EtudiantConcoursVo findByCne(@PathVariable("cne") String cne);
 
}
