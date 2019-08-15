package model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelModel {
    private File excelFile;
    private HSSFWorkbook excelWorkbook;
    private ArrayList<Integer> listColumns;

    public void loadExcel(ArrayList<Row> result) throws IOException {
        InputStream fis = new FileInputStream(excelFile);
        excelWorkbook = new HSSFWorkbook(fis);
        Sheet excelSheet = excelWorkbook.getSheetAt(0);
        Iterator<Row> it = excelSheet.rowIterator();
        while (it.hasNext()) {
            result.add(it.next());
            System.out.println(result.size());
        }
        fis.close();
    }

    public void concatener(ArrayList<Row> rows) throws IOException {
        OutputStream fos = new FileOutputStream(excelFile);

        for (Row row : rows) {
            String resConcat = "";
            for (int i = 0; i < listColumns.size() - 1; i ++) {
                int column = listColumns.get(i);
                if (row.getCell(column) == null)
                    row.createCell(column);
                System.out.println(row.getCell(column).getStringCellValue());
                resConcat = strConcat(resConcat, row.getCell(column).getStringCellValue());
            }
            System.out.println("\t=> " + resConcat);
            row.createCell(listColumns.get(listColumns.size() - 1)).setCellValue(resConcat);
        }

        excelWorkbook.write(fos);

        fos.flush();
        fos.close();
        excelWorkbook.close();
    }

    private String strConcat(String a, String b) {
        b = b.toLowerCase().trim();
        if (b.equals("") || b.equals("(no description)"))
            return a;
        // Make first letter uppercase
        b = (b.charAt(0) + "").toUpperCase() + b.substring(1);
        // Remove last dot in a
        while (a.endsWith("."))
            a = a.substring(0, a.length() - 1);
        if (a.equals(""))
            return b;
        else
            return a + ". " + b;
    }

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

    public ArrayList<Integer> getListColumns() {
        return listColumns;
    }

    public void setListColumns(ArrayList<String> listColumns) {
        this.listColumns = new ArrayList<>();
        for (String s : listColumns) {
            this.listColumns.add(getIntCol(s));
            System.out.println(getIntCol(s));
        }
    }

    private int getIntCol(String s) {
        int pow = 1, res = 0;
        s = s.toUpperCase();
        for (int i = s.length() - 1; i >= 0; i --) {
            res = res * pow + (s.charAt(i) - 'A' + 1);
            pow *= 26;
        }
        return res - 1;
    }
}
