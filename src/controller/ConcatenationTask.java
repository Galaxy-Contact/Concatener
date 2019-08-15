package controller;

import model.ExcelModel;
import org.apache.poi.ss.usermodel.Row;
import view.GUI;

import javax.swing.*;
import java.util.ArrayList;

public class ConcatenationTask extends SwingWorker<Void, Integer> {

    private ExcelModel model;
    private GUI parent;
    private ArrayList<Row> rows = new ArrayList<>();

    public ConcatenationTask(GUI parent) {
        this.parent = parent;
        model = parent.getModel();
    }

    private void cleanup() {
        rows.clear();
    }

    @Override
    protected Void doInBackground() throws Exception {
        cleanup();
        try {
            model.loadExcel(rows);
            model.concatener(rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
