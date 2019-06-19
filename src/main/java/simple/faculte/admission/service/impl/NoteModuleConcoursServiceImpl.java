/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service.impl;

import com.anas.Inscription.common.util.JexcelColumn;
import com.anas.Inscription.common.util.JexcelUtil;
import com.anas.Inscription.common.util.NumberUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import simple.faculte.admission.bean.NoteConcours;
import simple.faculte.admission.bean.NoteModuleConcours;
import simple.faculte.admission.bean.RetenueEcrit;
import simple.faculte.admission.dao.NoteConcoursDao;
import simple.faculte.admission.dao.NoteModuleConcoursDao;
import simple.faculte.admission.dao.RetenueEcritDao;
import simple.faculte.admission.rest.converter.NoteModuleConcoursConverter;
import simple.faculte.admission.rest.proxy.CandidatProxy;
import simple.faculte.admission.rest.proxy.ConcoursProxy;
import simple.faculte.admission.rest.vo.NoteConcoursVo;
import simple.faculte.admission.rest.vo.NoteModuleConcoursVo;
import simple.faculte.admission.rest.vo.exchange.EtudiantConcoursVo;
import simple.faculte.admission.rest.vo.exchange.ModuleConcoursVo;
import simple.faculte.admission.service.NoteModuleConcoursService;
import simple.faculte.admission.service.RetenueEcritService;

/**
 *
 * @author Anas
 */
@Service
public class NoteModuleConcoursServiceImpl implements NoteModuleConcoursService {

    @Autowired
    private NoteModuleConcoursDao noteModuleConcoursDao;
    @Autowired
    private NoteModuleConcoursConverter noteModuleConcoursConverter;
    @Autowired
    private RetenueEcritService retenueEcritService;
    @Autowired
    private CandidatProxy candidatProxy;
    @Autowired
    private ConcoursProxy concoursProxy;
    @Autowired
    private RetenueEcritDao retenueEcritDao;
    @Autowired
    private NoteConcoursDao noteConcoursDao;

    @Override
    public int saveNoteModuleConcours(NoteModuleConcours noteModuleConcours) {
        noteModuleConcoursDao.save(noteModuleConcours);
        return 1;
    }

    @Override
    public List<NoteModuleConcoursVo> uploadNotesFromExel(MultipartFile file) {
        try {
            JexcelColumn col1 = new JexcelColumn("retenueEcritVo", "refCandidat");
            JexcelColumn col2 = new JexcelColumn("nom");
            JexcelColumn col3 = new JexcelColumn("etudiantConcoursVo", "prenom");
            JexcelColumn col4 = new JexcelColumn("note");
            List<JexcelColumn> cols = new ArrayList<>();
            cols.add(col1);
            cols.add(col2);
            cols.add(col3);

            cols.add(col4);
            JexcelUtil<NoteModuleConcoursVo> jexcelUtil = new JexcelUtil<>(NoteModuleConcoursVo.class, cols);

            List<String> jsons = jexcelUtil.readAllExcelAsJson(GenerateFileFromMultipath(file).getAbsolutePath(), 0, 3, 11);

            List<NoteModuleConcoursVo> res = new ArrayList<>();

            for (int i = 0; i < jsons.size(); i++) {
                String json = jsons.get(i);
                System.out.println(json);
                System.out.println(jexcelUtil.fromJsonToObject(json).getEtudiantConcoursVo().getNom());
          
                res.add(jexcelUtil.fromJsonToObject(json));
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(NoteModuleConcours.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(NoteModuleConcours.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public File GenerateFileFromMultipath(MultipartFile file) {
        File file1 = new File("D:\\100APPLE", file.getOriginalFilename());

        try {
            FileUtils.writeByteArrayToFile(file1, file.getBytes());
            return file1;
        } catch (IOException ex) {
            Logger.getLogger(NoteModuleConcoursServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public int saveNoteModuleConcoursFromExcel(List<NoteModuleConcours> noteModuleConcourss) {
        if (noteModuleConcourss == null || noteModuleConcourss.isEmpty()) {
            return -1;
        } else {
            List<NoteModuleConcours> invalideNotes = new ArrayList();

            for (NoteModuleConcours noteModuleConcours : noteModuleConcourss) {
                if (validateNote(noteModuleConcours)) {
                    RetenueEcrit e = retenueEcritService.findByRefCandidat(noteModuleConcours.getRetenueEcrit().getRefCandidat());
                    noteModuleConcours.setRetenueEcrit(e);
                    ModuleConcoursVo m = concoursProxy.findById(NumberUtil.toLong(noteModuleConcours.getRefModuleConcours()));
                    double coef = NumberUtil.toDouble(m.getCoefModuleConcoursVo().getCoef());
                    e.getNoteConcours().setNoteEcrit((e.getNoteConcours().getNoteEcrit() + coef * noteModuleConcours.getNote()) / (concoursProxy.totalCoef(e.getRefConcours())));
                    e.getNoteConcours().setNoteEcrit(NumberUtil.toDouble(new DecimalFormat("00.000").format(e.getNoteConcours().getNoteEcrit())));

                    saveNoteModuleConcours(noteModuleConcours);
                    retenueEcritDao.save(e);
                } else {
                    invalideNotes.add(noteModuleConcours);
                }
            }
            return 1;
        }
    }

    @Override
    public boolean validateNote(NoteModuleConcours noteModuleConcours) {

        if (retenueEcritService.findByRefCandidat(noteModuleConcours.getRetenueEcrit().getRefCandidat()) == null) {
            return false;
        } else if (noteModuleConcours.getNote() > 20 || noteModuleConcours.getNote() < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<NoteModuleConcours> findByRefModuleConcours(String refModuleConcours) {
        return noteModuleConcoursDao.findByRefModuleConcours(refModuleConcours);
    }

    @Override
    public int saveNoteConcoursOralFromExcel(List<NoteConcours> noteConcourss) {
        if (noteConcourss == null || noteConcourss.isEmpty()) {
            return -1;
        } else {
            for (NoteConcours noteConcours : noteConcourss) {
                System.out.println(noteConcours.getRetenueEcrit().getRefCandidat());
                RetenueEcrit e = retenueEcritService.findByRefCandidat(noteConcours.getRetenueEcrit().getRefCandidat());

                if (e != null && e.isAdmis()==true) {
                    NoteConcours n = e.getNoteConcours();
                    n.setNoteOral(noteConcours.getNoteOral());
                    noteConcoursDao.save(n);
                    System.out.println("hani");
                }   
            }
            return 1;
        }

    }

    @Override
    public List<NoteConcoursVo> uploadNotesOralFromExel(MultipartFile file) {
        try {
            JexcelColumn col1 = new JexcelColumn("retenueEcritVo", "refCandidat");
            JexcelColumn col2 = new JexcelColumn("nom");
            JexcelColumn col3 = new JexcelColumn("etudiantConcoursVo", "prenom");
            JexcelColumn col4 = new JexcelColumn("noteOral");
            List<JexcelColumn> cols = new ArrayList<>();
            cols.add(col1);
            cols.add(col2);
            cols.add(col3);

            cols.add(col4);
            JexcelUtil<NoteConcoursVo> jexcelUtil = new JexcelUtil<>(NoteConcoursVo.class, cols);

            List<String> jsons = jexcelUtil.readAllExcelAsJson(GenerateFileFromMultipath(file).getAbsolutePath(), 0, 3, 11);

            List<NoteConcoursVo> res = new ArrayList<>();

            for (int i = 0; i < jsons.size(); i++) {
                String json = jsons.get(i);
                System.out.println(json);
            
                res.add(jexcelUtil.fromJsonToObject(json));
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(NoteModuleConcours.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(NoteModuleConcours.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<NoteConcours> findNotesConcours(String refConcours) {
        List<NoteConcours> listE = noteConcoursDao.findByRetenueEcritRefConcours(refConcours);
        List<NoteConcours> listA = new ArrayList<>();
        for (NoteConcours noteConcours : listE) {
            if (noteConcours.getRetenueEcrit().isRetenueOral() == true) {
                listA.add(noteConcours);
            }
        }
        return listA;
    }

}
