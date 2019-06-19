/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest;

import com.anas.Inscription.common.util.GeneratePdf;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import simple.faculte.admission.bean.NoteConcours;
import simple.faculte.admission.bean.NoteModuleConcours;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.dao.NoteConcoursDao;
import simple.faculte.admission.rest.converter.NoteConcoursConverter;
import simple.faculte.admission.rest.converter.NoteModuleConcoursConverter;
import simple.faculte.admission.rest.converter.RetenueEcritConverter;
import simple.faculte.admission.rest.proxy.ConcoursProxy;
import simple.faculte.admission.rest.vo.NoteConcoursVo;
import simple.faculte.admission.rest.vo.NoteModuleConcoursVo;
import simple.faculte.admission.rest.vo.RetenueEcritVo;
import simple.faculte.admission.rest.vo.exchange.ConcoursVo;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;
import simple.faculte.admission.rest.vo.exchange.NotesSemestreVo;
import simple.faculte.admission.service.NoteModuleConcoursService;
import simple.faculte.admission.service.RetenueEcritService;
import simple.faculte.admission.service.RetenueFinalService;
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
    @Autowired
    private RetenueFinalService retenueFinalService;
    @Autowired
    private NoteConcoursConverter noteConcoursConverter;
    @Autowired
    private ConcoursProxy concoursProxy;

    @GetMapping("/liste_admis/{refConcours}")
    public List<RetenueEcritVo> findListeRetenueFinal(@PathVariable String refConcours) {
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return retenueEcritConverter.toVo(retenueFinalService.findListeRetenueFinal(refConcours));
    }

    @PostMapping("/admis/save/")
    public int saveRetenueFinal(@RequestBody List<RetenueEcritVo> retenueEcritsVo) {
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return retenueFinalService.saveRetenueFinal(retenueEcritConverter.toItem(retenueEcritsVo));
    }

    @GetMapping("/{refConcours}")
    public List<RetenueEcritVo> findListeReteues(@PathVariable String refConcours) {
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return retenueEcritConverter.toVo(retenueEcritService.findListeReteues(refConcours));
    }

    @GetMapping("retenue_ecrit/pdf/{reference}")
    public ResponseEntity<Object> report(@PathVariable String reference) throws JRException, IOException {
        ConcoursVo concoursVo = concoursProxy.findByReference(reference);
        Map<String, Object> params = new HashMap<>();
        params.put("refConcours", reference);
        params.put("dateConcours", concoursVo.getAnneeConcours());
        params.put("filiereConcours", concoursVo.getRefFiliere());
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return GeneratePdf.generate("Liste_Candidats", params, retenueEcritConverter.toVo(retenueEcritService.findRetenusByRefConcours(reference)), "/Pdfs/ListeRetenueEcrit.jasper");
    }

    @GetMapping("retenue_Oral/pdf/{reference}")
    public ResponseEntity<Object> reportOral(@PathVariable String reference) throws JRException, IOException {
        ConcoursVo concoursVo = concoursProxy.findByReference(reference);
        Map<String, Object> params = new HashMap<>();
        params.put("refConcours", reference);
        params.put("dateConcours", concoursVo.getAnneeConcours());
        params.put("filiereConcours", concoursVo.getRefFiliere());
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return GeneratePdf.generate("Liste_Candidats", params, retenueEcritConverter.toVo(retenueFinalService.findListeRetenueFinalInBd(reference)), "/Pdfs/ListeRetenueOral.jasper");
    }

    @GetMapping("retenue_final/pdf/{reference}")
    public ResponseEntity<Object> reportFinal(@PathVariable String reference) throws JRException, IOException {
        ConcoursVo concoursVo = concoursProxy.findByReference(reference);
        Map<String, Object> params = new HashMap<>();
        params.put("refConcours", reference);
        params.put("dateConcours", concoursVo.getAnneeConcours());
        params.put("filiereConcours", concoursVo.getRefFiliere());
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return GeneratePdf.generate("Liste_Candidats", params, retenueEcritConverter.toVo(retenueFinalService.findListeRetenueFinalInBd(reference)), "/Pdfs/ListeRetenueOral.jasper");
    }

    @GetMapping("/liste_admis/BdListe/{refConcours}")
    public List<RetenueEcritVo> findListeRetenueFinalInBd(@PathVariable String refConcours) {
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return retenueEcritConverter.toVo(retenueFinalService.findListeRetenueFinalInBd(refConcours));
    }

    @PostMapping("/preselection")
    public int savePreselection(@RequestBody List<RetenueEcritVo> retenueEcrits) {
        return retenueEcritService.savePreselection(retenueEcritConverter.toItem(retenueEcrits));
    }

    @GetMapping("/listeNoteConcours/{refConcours}")
    public List<NoteConcoursVo> findNotesConcours(@PathVariable String refConcours) {
        noteConcoursConverter.init();
        noteConcoursConverter.getRetenueEcritConverter().setNoteConcours(false);
        return noteConcoursConverter.toVo(noteModuleConcoursService.findNotesConcours(refConcours));
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

    @GetMapping("retenue_oral/BdListe/{refConcours}")
    public List<RetenueEcritVo> listeRetenueInBd(@PathVariable String refConcours) {
        return retenueEcritConverter.toVo(retenueOralService.listeRetenueInBd(refConcours));
    }

    @GetMapping("/retenue_oral/{refConcours}")
    public List<RetenueEcritVo> findListeRetenueOral(@PathVariable String refConcours) {
        retenueEcritConverter.init();
        retenueEcritConverter.getNoteConcoursConverter().setRetenueEcrit(false);
        return retenueEcritConverter.toVo(retenueOralService.findListeRetenueOral(refConcours));
    }

    @PostMapping("/xls/note_oral/save")
    public int saveNoteConcoursOralFromExcel(@RequestBody List<NoteConcoursVo> noteConcourss) {
        noteConcoursConverter.init();
        noteConcoursConverter.getRetenueEcritConverter().setNoteConcours(false);
        System.out.println(noteConcoursConverter.toItem(noteConcourss).get(0).getRetenueEcrit());
        return noteModuleConcoursService.saveNoteConcoursOralFromExcel(noteConcoursConverter.toItem(noteConcourss));
    }

    @PostMapping("/xls/note_oral/")
    public List<NoteConcoursVo> uploadNotesOralFromExel(MultipartFile file) {
        return noteModuleConcoursService.uploadNotesOralFromExel(file);
    }

    @PostMapping("/xls/save")
    public int saveNoteModuleConcoursFromExcel(@RequestBody List<NoteModuleConcoursVo> noteModuleConcourss) {
        return noteModuleConcoursService.saveNoteModuleConcoursFromExcel(noteModuleConcoursConverter.toItem(noteModuleConcourss));
    }

    @PostMapping("retenue_oral/save")
    public int saveRetenueOral(@RequestBody List<RetenueEcritVo> retenueEcrits) {
        System.out.println(retenueEcrits);
        return retenueOralService.saveRetenueOral(retenueEcritConverter.toItem(retenueEcrits));
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
