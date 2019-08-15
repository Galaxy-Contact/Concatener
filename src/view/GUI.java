package view;

import controller.ConcatenationTask;
import model.ExcelModel;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI extends JFrame {

    private ExcelModel model;

    private JPanel pnlExcel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnlColumns = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnlActions = new JPanel(new FlowLayout());

    private JLabel lblExcel = new JLabel("Fichier excel: ");
    private JLabel lblColumns = new JLabel("Liste des columns: ");

    private JTextField tfExcel = new JTextField("");
    private JTextField tfColumns = new JTextField("");

    private JButton btnExcel = new JButton("Parcourir");
    private JButton btnConcat = new JButton("Concatener");

    private JFileChooser jfc = new JFileChooser(".");

    public GUI(ExcelModel model) {
        this.model = model;

        initComponents();

        setLayout(new GridLayout(3, 1));

        pnlExcel.add(lblExcel);
        pnlExcel.add(tfExcel);
        pnlExcel.add(btnExcel);

        pnlColumns.add(lblColumns);
        pnlColumns.add(tfColumns);

        pnlActions.add(btnConcat);

        add(pnlExcel);
        add(pnlColumns);
        add(pnlActions);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
    }

    private void initComponents() {
        tfExcel.setPreferredSize(new Dimension(200, 25));
        tfColumns.setPreferredSize(new Dimension(200, 25));

        lblExcel.setPreferredSize(lblColumns.getPreferredSize());

        tfColumns.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                changedUpdate(documentEvent);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                changedUpdate(documentEvent);
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });

        btnExcel.addActionListener(e -> {
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (jfc.showOpenDialog(this) == jfc.getApproveButtonMnemonic()) {
                tfExcel.setText(jfc.getSelectedFile().getPath());
            }
        });

        btnConcat.addActionListener(e -> {
            if (tfColumns.getText().equals("") || tfExcel.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer tous les infos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            model.setListColumns(new ArrayList<>(Arrays.asList(tfColumns.getText().split("((?![a-zA-Z]).)+"))));
            model.setExcelFile(new File(tfExcel.getText()));

            new ConcatenationTask(this).execute();
        });
    }

    public ExcelModel getModel() {
        return model;
    }

    public void setModel(ExcelModel model) {
        this.model = model;
    }
}
