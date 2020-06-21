/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.interfaz;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eloy
 */
public class CustomTableModel extends DefaultTableModel {
    int id = 0;
    public CustomTableModel(String[] x, int id) {
        super(null,x);
        this.id = id;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }
    @Override
    public Class getColumnClass(int column) {
        Class sol = String.class;
        if (id == 0) { 
            if (column == 2 || column == 3) {
                sol = Integer.class;
            } else if (column == 4) {
                sol = Float.class;
            }
        } else {
            if (column == 0) {
                sol = Integer.class;
            } else if (column == 4) {
                sol = Float.class;
            }
        }
        return sol;
    }
}
