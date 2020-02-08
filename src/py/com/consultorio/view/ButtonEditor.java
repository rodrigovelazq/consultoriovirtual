/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.consultorio.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import py.com.consultorio.controlador.Conector;

/**
 *
 * @author usuario
 */
public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;

    private String label;
    
    private static Conector conector;

    private String nombrePaciente;
    
    private String idPaciente;
    
    private boolean isPushed;

    JTabbedPane jTabbedPane1;

    public ButtonEditor(JCheckBox checkBox, JTabbedPane jTabbedPane1, Conector conector) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        this.conector = conector;
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
        this.jTabbedPane1 = jTabbedPane1;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        this.nombrePaciente = table.getValueAt(row, 1).toString() + " " + table.getValueAt(row, 2).toString();
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            this.agregarTab();
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }

    private void agregarTab() {
        JPanel jPanel1 = new JPanel();
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);

        this.jTabbedPane1.addTab(this.nombrePaciente, jPanel1);
        int index = this.jTabbedPane1.indexOfTab(this.nombrePaciente);
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(this.nombrePaciente);
        JButton btnClose = new JButton("x");
        btnClose.addActionListener(new MyCloseActionHandler(jTabbedPane1,index));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);

        this.jTabbedPane1.setTabComponentAt(index, pnlTab);

        //btnClose.addActionListener(myCloseActionHandler);
    }

    private class MyCloseActionHandler implements ActionListener {

        private JTabbedPane jTabbedPane1;
        private int index;
        
        MyCloseActionHandler(JTabbedPane jTabbedPane1,int index) {
            this.jTabbedPane1 = jTabbedPane1;
            this.index = index;
        }

        public void actionPerformed(ActionEvent evt) {
                jTabbedPane1.remove(this.index);
        }

    }
}
