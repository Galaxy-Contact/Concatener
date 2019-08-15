package model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.util.ArrayList;

public class ExcelModel {
    private File excelFile;
    private HSSFWorkbook excelWorkbook;
    private ArrayList<String> listColumns;

    public File getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }

    public HSSFWorkbook getExcelWorkbook() {
        return excelWorkbook;
    }

    public void setExcelWorkbook(HSSFWorkbook excelWorkbook) {
        this.excelWorkbook = excelWorkbook;
    }

    public ArrayList<String> getListColumns() {
        return listColumns;
    }

    public void setListColumns(ArrayList<String> listColumns) {
        this.listColumns = listColumns;
    }
}
