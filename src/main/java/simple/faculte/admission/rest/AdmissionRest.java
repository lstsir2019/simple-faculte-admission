/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest;

import com.anas.Inscription.common.util.GeneratePdf;
import java.io.File;
import java.io.IOException;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import simple.faculte.admission.bean.NoteModuleConcours;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.rest.converter.NoteModuleConcoursConverter;
import simple.faculte.admission.rest.converter.RetenueEcritConverter;
import simple.faculte.admission.rest.vo.NoteModuleConcoursVo;
import simple.faculte.admission.rest.vo.RetenueEcritVo;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;
import simple.faculte.admission.rest.vo.exchange.NotesSemestreVo;
import simple.faculte.admission.service.NoteModuleConcoursService;
import simple.faculte.admission.service.RetenueEcritService;
import simple.faculte.admission.service.RetenueOralService;

/**
 *
 * @author Anas
 */
@RestController()
@RequestMapping("/admission/retenus")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdmissionRest {

    @Autowired
        RetenueEcritService retenueEcritService;
        @Autowired
        RetenueEcritConverter retenueEcritConverter;
        @Autowired
        NoteModuleConcoursService noteModuleConcoursService;
        @Autowired
        private NoteModuleConcoursConverter noteModuleConcoursConverter;
        @Autowired
        private RetenueOralService retenueOralService;

        @GetMapping("/{refConcours}")
        public List<RetenueEcritVo> findListeReteues(@PathVariable String refConcours) {
            return retenueEcritConverter.toVo(retenueEcritService.findListeReteues(refConcours));
        }

        @GetMapping("retenue_ecrit/pdf/{reference}")
        public ResponseEntity<Object> report(@PathVariable String reference) throws JRException, IOException {

            return GeneratePdf.generate("Liste_Retenues", null, retenueEcritConverter.toVo(retenueEcritService.findRetenusByRefConcours(reference)), "/Pdfs/liste_Retenue.jasper");
        }

        @PostMapping("/preselection")
        public int savePreselection(@RequestBody List<RetenueEcritVo> retenueEcrits) {
            return retenueEcritService.savePreselection(retenueEcritConverter.toItem(retenueEcrits));
        }

        @GetMapping("/")
        public List<EtudiantConcoursVo> findAllCandidats() {
            return retenueEcritService.findAllCandidats();
        }

        @GetMapping("/xls/notes/{refModuleConcours}")
        public List<NoteModuleConcoursVo> findByRefModuleConcours(@PathVariable String refModuleConcours) {
            return noteModuleConcoursConverter.toVo(noteModuleConcoursService.findByRefModuleConcours(refModuleConcours));
        }

        @PostMapping("/xls")
        public List<NoteModuleConcoursVo> uploadNotesFromExel(MultipartFile file) {
        System.out.println(noteModuleConcoursService.uploadNotesFromExel(file).get(0).getRetenueEcritVo());
        return noteModuleConcoursService.uploadNotesFromExel(file);
    }

    @GetMapping("/retenue_oral/{refConcours}")
    public List<RetenueEcritVo> findListeRetenueOral(@PathVariable String refConcours) {
        retenueEcritConverter.init();   
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return retenueEcritConverter.toVo(retenueOralService.findListeRetenueOral(refConcours));
    }

    @PostMapping("/xls/save")
    public int saveNoteModuleConcoursFromExcel(@RequestBody List<NoteModuleConcoursVo> noteModuleConcourss) {
        return noteModuleConcoursService.saveNoteModuleConcoursFromExcel(noteModuleConcoursConverter.toItem(noteModuleConcourss));
    }

    @GetMapping("/list_retenues/{refConcours}")
    public List<RetenueEcritVo> findRetenusByRefConcours(@PathVariable String refConcours) {
        return retenueEcritConverter.toVo(retenueEcritService.findRetenusByRefConcours(refConcours));
    }

    public RetenueEcritService getRetenueEcritService() {
        return retenueEcritService;
    }

    public void setRetenueEcritService(RetenueEcritService retenueEcritService) {
        this.retenueEcritService = retenueEcritService;
    }

    public RetenueEcritConverter getRetenueEcritConverter() {
        return retenueEcritConverter;
    }

    public void setRetenueEcritConverter(RetenueEcritConverter retenueEcritConverter) {
        this.retenueEcritConverter = retenueEcritConverter;
    }

    public NoteModuleConcoursService getNoteModuleConcoursService() {
        return noteModuleConcoursService;
    }

    public void setNoteModuleConcoursService(NoteModuleConcoursService noteModuleConcoursService) {
        this.noteModuleConcoursService = noteModuleConcoursService;
    }

}
