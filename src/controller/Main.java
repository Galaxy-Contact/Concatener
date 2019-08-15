package controller;

import model.ExcelModel;
import view.GUI;

import javax.swing.*;

public class Main {
    private static ExcelModel model = new ExcelModel();
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(() -> new GUI(model));
    }
}
