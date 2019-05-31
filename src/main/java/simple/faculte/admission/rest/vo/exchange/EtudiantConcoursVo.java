/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.faculte.admission.rest.vo.exchange;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anas
 */
public class EtudiantConcoursVo {

    private Long id;
    private String cne;
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private String email;
    private String niveau;
    private String adressePersonnelle;
    private String statue;
    private String dateNaissance;
    private DiplomeEtudiantVo diplomeEtudiantVo;
    private List<ChoixVo> choixVos;
    private List<NotesSemestreVo> notesSemestreVos;


    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdressePersonnelle() {
        return adressePersonnelle;
    }

    public void setAdressePersonnelle(String adressePersonnelle) {
        this.adressePersonnelle = adressePersonnelle;
    }



    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    
    public DiplomeEtudiantVo getDiplomeEtudiantVo() {
        return diplomeEtudiantVo;
    }

    public void setDiplomeEtudiantVo(DiplomeEtudiantVo diplomeEtudiantVo) {
        this.diplomeEtudiantVo = diplomeEtudiantVo;
    }

    public List<ChoixVo> getChoixVos() {
        return choixVos;
    }
    public void setChoixVos(List<ChoixVo> choixVos) {
        this.choixVos = choixVos;
    }

    public List<NotesSemestreVo> getNotesSemestreVos() {
        return notesSemestreVos;
    }

    public void setNotesSemestreVos(List<NotesSemestreVo> notesSemestreVos) {
        this.notesSemestreVos = notesSemestreVos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

}
