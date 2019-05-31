/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.faculte.admission.bean.NoteConcours;

/**
 *
 * @author Anas
 */
public interface NoteConcoursDao extends JpaRepository<NoteConcours, Long>{
    public NoteConcours findByRetenueEcritRefCandidat(String refCandiadt);
}
