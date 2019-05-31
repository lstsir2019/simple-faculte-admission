/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anas.Inscription.common.util;

/**
 *
 * @author Anas
 */
public class NumberUtil {

    private static final String CHAINE_VIDE = "";

    public static Long toLong(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {

            return new Long(value);
        }

    }

    public static String toString(Long value) {
        if (value == null) {
            return null;
        } else {
            return String.valueOf(value);
        }

    }

    public static int toInt(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        } else {

            return new Integer(value);
        }

    }

    public static String toString(int value) {

        return String.valueOf(value);
    }

    public static String toString(double value) {

        return String.valueOf(value);
    }

    public static double toDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        } else {

            return new Double(value);
        }
    }
}
