/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.service;

import java.io.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import simple.faculte.admission.bean.NoteModuleConcours;
import simple.faculte.admission.rest.vo.NoteModuleConcoursVo;
import simple.faculte.admission.rest.vo.exchange.NotesSemestreVo;

/**
 *
 * @author Anas
 */
public interface NoteModuleConcoursService {

    public int saveNoteModuleConcours(NoteModuleConcours noteModuleConcours);

    public List<NoteModuleConcoursVo> uploadNotesFromExel(MultipartFile file);

    public File GenerateFileFromMultipath(MultipartFile file);

    public int saveNoteModuleConcoursFromExcel(List<NoteModuleConcours> noteModuleConcourss);

    public boolean validateNote(NoteModuleConcours noteModuleConcours);

    public List<NoteModuleConcours> findByRefModuleConcours(String refModuleConcours);

}
