/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.faculte.admission.bean.NoteModuleConcours;

/**
 *
 * @author Anas
 */
@Repository
public interface NoteModuleConcoursDao extends JpaRepository<NoteModuleConcours, Object> {

    public List<NoteModuleConcours> findByRefModuleConcours(String refModuleConcours);
}
