package com.anas.Inscription.common.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import simple.faculte.admission.service.impl.NoteModuleConcoursServiceImpl;

public class JexcelUtil<T> {

    private Class myClass;
    private List<JexcelColumn> columns;

    public JexcelUtil(Class myClass, List<JexcelColumn> columns) {
        this.myClass = myClass;
        this.columns = columns;
    }

    public JexcelUtil() {
    }

    public List<JexcelColumn> constuctColumns(List<String> columns) {
        List<JexcelColumn> list = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            String get = columns.get(i);
            list.add(new JexcelColumn(get));
        }
        return list;
    }
    //    public static void main(String[] args) throws BiffException, IOException {
    //        List<String> columns = Arrays.asList("referenceMoleculeVo", "referenceFamilleVo");
    //        JexcelUtil<MoleculeFamilleVo> jexcelUtil = new JexcelUtil<>(MoleculeFamilleVo.class, columns);
    //
    //        List<String> jsons = jexcelUtil.readAllExcelAsJson("C:\\Users\\user\\Documents\\1lst-SIR\\S6\\Projets\\stage-pharmacie\\Docs\\molec-familles.xls", 0, 1, 1);
    //
    //        for (int i = 0; i < jsons.size(); i++) {
    //            System.out.println("jsons.get(i) == " + jsons.get(i));
    //
    //        }
    //    }

    public String readExcelAsJson(String pathname, int startCol, int endCol, int line) throws BiffException, IOException {
        Sheet sheet = startXl(pathname);
        String result = "";
        for (int i = startCol; i <= endCol; i++) {
            jxl.Cell c = sheet.getCell(i, line);
            result = readJxclElement(i, startCol, result, c, endCol);
        }
        System.out.println("m reste---" + result);
        return result;
    }

    private String readJxclElement(int i, int startCol, String result, Cell c, int endCol) {
        String cellContent = "";
        if (c.getContents().contains(",")) {
            cellContent = c.getContents().replace(",", ".");
        } else {
            cellContent = c.getContents();
        }
        JexcelColumn col = columns.get(i);
        String content = null;
        if (!col.isPrimitif()) {
            content = "\"" + col.getObjectName() + "\": {\"" + col.getObjectAttributeName() + "\": \"" + cellContent + "\"}";
        } else {
            content = "\"" + col.getObjectAttributeName() + "\": \"" + cellContent + "\"";
        }
        if (i == startCol) {
            result += "{" + content + ", ";
        } else if (i == endCol) {
            result += content + "}";
        } else {
            result += content + ", ";
        }
        return result;
    }

    public String readExcelAsJsonOneCol(String pathname, int myColumn, int line) throws BiffException, IOException {
        Sheet sheet = startXl(pathname);
        String result = "";
        jxl.Cell c = sheet.getCell(myColumn, line);
        JexcelColumn col = columns.get(myColumn);
        System.out.println("la colonne-------:" + col);
        result = "{\"" + col + "\": " + "\"" + c.getContents() + "\"} ";
        return result;
    }

    public T readExcel(String pathname, int startCol, int endCol, int startLine) throws IOException, BiffException {
        String json = readExcelAsJson(pathname, startCol, endCol, startLine);
        T myObject = fromJsonToObject(json);
        return myObject;
    }

    public T fromJsonToObject(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T myObject = (T) mapper.readValue(json, myClass);
        return myObject;
    }

    public List<T> readAllExcel(String pathname, int startCol, int endCol, int startLine) throws IOException, BiffException {
        List<T> objects = new ArrayList();
        Sheet sheet = startXl(pathname);
        int rows = sheet.getRows();
        for (int i = startLine; i < rows - 1; i++) {
            T object = readExcel(pathname, startCol, endCol, i);
            objects.add(object);
        }
        return objects;
    }

    public List<String> readAllExcelAsJson(String pathname, int startCol, int endCol, int startLine) throws IOException, BiffException {
        List<String> jsons = new ArrayList<>();
        Sheet sheet = startXl(pathname);
        int rows = sheet.getRows();
        for (int i = startLine; i < rows - 1; i++) {
            String json = readExcelAsJson(pathname, startCol, endCol, i);
            jsons.add(json);
        }
        return jsons;
    }

    public List<String> readAllExcelAsJsonOneCol(String pathname, int myColumn, int startLine) throws IOException, BiffException {
        List<String> jsons = new ArrayList<>();
        Sheet sheet = startXl(pathname);
        int rows = sheet.getRows();
        System.out.println(sheet.getRows());
        for (int i = startLine; i < rows - 1; i++) {
            String json = readExcelAsJsonOneCol(pathname, myColumn, i);
            jsons.add(json);
        }
        return jsons;
    }

    public Sheet startXl(String pathname) throws IOException, BiffException {
        File file = new File(pathname);
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet s = workbook.getSheet(0);
        return s;
    }


}
