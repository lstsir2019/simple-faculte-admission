/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import simple.faculte.admission.rest.vo.exchange.ConcoursVo;
import simple.faculte.admission.rest.vo.exchange.ModuleConcoursVo;

/**
 *
 * @author Anas
 */
@FeignClient(name = "microservice-concours-api", url = "localhost:8090")
public interface ConcoursProxy {

    @GetMapping("concours-api/concours/reference/{reference}")
    public ConcoursVo findByReference(@PathVariable("reference") String reference);

    @GetMapping("concours-api/concours/total_coef/{refConcours}")
    public double totalCoef(@PathVariable("refConcours") String refConcours);

    @GetMapping("concours-api/concours/reference/{refConcours}/module-concours/reference/{refModule}")
    public ModuleConcoursVo findByReferenceAndConcoursReference(@PathVariable("refModule") String refModule, @PathVariable("refConcours") String refConcours);

 @GetMapping("concours-api/concours/module-concours/{id}")
    public ModuleConcoursVo findById(@PathVariable("id") Long id);

}
