/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anas.Inscription.common.util;

/**
 *
 * @author user
 */
public class JexcelColumn {

    private String objectName;
    private String objectAttributeName;
    private boolean primitif;

    public JexcelColumn(String objectAttributeName) {
        this.objectAttributeName = objectAttributeName;
        this.primitif = true;
    }

    public JexcelColumn(String objectName, String objectAttributeName, String objectAttributeName1) {
        this.objectName = objectName;
        this.objectAttributeName = objectAttributeName;

    }

    public JexcelColumn(String objectName, String objectAttributeName) {
        this.objectName = objectName;
        this.objectAttributeName = objectAttributeName;

    }

    public String getObjectAttributeName() {
        return objectAttributeName;
    }

    public void setObjectAttributeName(String objectAttributeName) {
        this.objectAttributeName = objectAttributeName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public boolean isPrimitif() {
        return primitif;
    }

    public void setPrimitif(boolean primitif) {
        this.primitif = primitif;
    }


}
