/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import sMM.basededatos.ConexionBD;
import sMM.basededatos.ConexionJDBC;
import sMM.modelo.Autor;
import sMM.modelo.DatosUsuario;
import sMM.modelo.Disco;

/**
 *
 * @author Eloy
 */
public class AutoresFrame extends javax.swing.JFrame {
 // Variables para mover ventana desde la barra de titulo (Dragger)
    Point start_drag;
    Point start_loc;
    boolean dragging;
    MainFrame mFrame;
    private HashMap<Integer,Integer> tuplaFilaAutor;
    private DatosUsuario du;
    
    Point getScreenLocation(MouseEvent e) {
    Point cursor = e.getPoint();
    Point target_location = this.getLocationOnScreen();
    return new Point((int) (target_location.getX() + cursor.getX()),
        (int) (target_location.getY() + cursor.getY()));
    }
    
    public void cargarDatos() {
        Lista.setVisible(false);
        du = MainFrame.getDatosUsuario();
        tuplaFilaAutor = new HashMap<>();
        DefaultTableModel listModel = (DefaultTableModel) Lista.getModel();
        int rowCount = listModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            listModel.removeRow(i);
        }
        
        if (du != null && du.getAutores() != null) {
            int i = 0;
            for (Map.Entry<Integer,Autor> ed : du.getAutores().entrySet()) {
                String[] listaAtributos = new String[2];
                listaAtributos[0] = ed.getValue().getNombre();
                listaAtributos[1] = ed.getValue().getNacionalidad();
                tuplaFilaAutor.put(i, ed.getValue().getIdentificador());
                listModel.addRow(listaAtributos);
                i++;
            }
        }
        Lista.setVisible(true);
    }

    public void configVentana() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void configComponentes() {
         // Config lista de autores
        UIManager.getDefaults().put("TableHeader.cellBorder",createEmptyBorder());
        PanelLista.getViewport().setBackground(new Color(30,30,30));
        PanelLista.setBorder(createEmptyBorder());
        PanelLista.getHorizontalScrollBar().setUI(new MyScrollbar());
        PanelLista.getVerticalScrollBar().setUI(new MyScrollbar());
        Lista.getTableHeader().setBorder(null);
        Lista.getTableHeader().setBackground(new Color(205,205,205));
        Lista.getTableHeader().setForeground(Color.BLACK);
        Lista.getTableHeader().setReorderingAllowed(false);
        Lista.getTableHeader().setPreferredSize(new Dimension(Lista.getTableHeader().getSize().width,30));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        Lista.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        Lista.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        Lista.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (Lista.getSelectedRow() != -1) {
                    nombreField.setEditable(false);
                    nacionalidadField.setEditable(false);
                    int row = Lista.convertRowIndexToModel(Lista.getSelectedRow());
                    String nombre = (String) Lista.getModel().getValueAt(row, 0);
                    String nacionalidad = (String) Lista.getModel().getValueAt(row, 1);
                    nombreField.setText(nombre);
                    nacionalidadField.setText(nacionalidad);
                    
                    
                }
            }
        });
        busquedaField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent arg0) {

            }
            public void insertUpdate(DocumentEvent arg0) {
                String text = busquedaField.getText();
                TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
                if (text.trim().length() == 0) {
                    rs.setRowFilter(null);
                } else {
                    rs.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            public void removeUpdate(DocumentEvent arg0) {
                String text = busquedaField.getText();
                TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
                if (text.trim().length() == 0) {
                    rs.setRowFilter(null);
                } else {
                    rs.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
    }
    
    public AutoresFrame(MainFrame f) {
        mFrame = f;
        initComponents();
        configComponentes();
        configVentana();
        cargarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Base = new javax.swing.JPanel();
        Separador = new javax.swing.JSeparator();
        PanelLista = new javax.swing.JScrollPane();
        Lista = new javax.swing.JTable();
        nombreLabel = new javax.swing.JLabel();
        nacionalidadLabel = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        nacionalidadField = new javax.swing.JTextField();
        nuevoButton = new javax.swing.JButton();
        modificarButton = new javax.swing.JButton();
        aplicarButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        busquedaField = new javax.swing.JTextField();
        busquedaLabel = new javax.swing.JLabel();
        Dragger = new javax.swing.JPanel();
        Minimizar = new javax.swing.JLabel();
        Cerrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(10, 10, 10));
        setUndecorated(true);

        Base.setBackground(new java.awt.Color(10, 10, 10));

        Separador.setBackground(new java.awt.Color(254, 254, 254));
        Separador.setOrientation(javax.swing.SwingConstants.VERTICAL);

        PanelLista.setBackground(new java.awt.Color(30, 30, 30));
        PanelLista.setBorder(null);
        PanelLista.setForeground(new java.awt.Color(30, 30, 30));
        PanelLista.setToolTipText("");

        Lista.setAutoCreateRowSorter(true);
        Lista.setBackground(new java.awt.Color(30, 30, 30));
        Lista.setForeground(new java.awt.Color(254, 254, 254));
        Lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "NACIONALIDAD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Lista.setFocusable(false);
        Lista.setGridColor(new java.awt.Color(50, 50, 50));
        Lista.setIntercellSpacing(new java.awt.Dimension(0, 1));
        Lista.setRowHeight(30);
        Lista.setSelectionBackground(new java.awt.Color(50, 50, 50));
        Lista.setSelectionForeground(new java.awt.Color(254, 254, 254));
        Lista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Lista.setShowVerticalLines(false);
        PanelLista.setViewportView(Lista);

        nombreLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nombreLabel.setForeground(new java.awt.Color(254, 254, 254));
        nombreLabel.setText("NOMBRE");

        nacionalidadLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nacionalidadLabel.setForeground(new java.awt.Color(254, 254, 254));
        nacionalidadLabel.setText("NACIONALIDAD");

        nombreField.setEditable(false);

        nacionalidadField.setEditable(false);
        nacionalidadField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nacionalidadFieldActionPerformed(evt);
            }
        });

        nuevoButton.setText("Nuevo");
        nuevoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoButtonMouseClicked(evt);
            }
        });
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        modificarButton.setText("Modificar");
        modificarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarButtonMouseClicked(evt);
            }
        });
        modificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarButtonActionPerformed(evt);
            }
        });

        aplicarButton.setText("Aplicar");
        aplicarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarButtonMouseClicked(evt);
            }
        });
        aplicarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarButtonActionPerformed(evt);
            }
        });

        eliminarButton.setText("Eliminar");
        eliminarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarButtonMouseClicked(evt);
            }
        });

        busquedaLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        busquedaLabel.setForeground(new java.awt.Color(254, 254, 254));
        busquedaLabel.setText("BÚSQUEDA");

        javax.swing.GroupLayout BaseLayout = new javax.swing.GroupLayout(Base);
        Base.setLayout(BaseLayout);
        BaseLayout.setHorizontalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BaseLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(PanelLista, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreLabel)
                    .addComponent(nacionalidadLabel)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nacionalidadField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BaseLayout.createSequentialGroup()
                        .addComponent(nuevoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aplicarButton))
                    .addComponent(eliminarButton)
                    .addComponent(busquedaField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busquedaLabel))
                .addGap(12, 12, 12))
        );
        BaseLayout.setVerticalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Separador, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BaseLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(busquedaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(nombreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nacionalidadLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nacionalidadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modificarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aplicarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        Dragger.setBackground(new java.awt.Color(50, 50, 50));
        Dragger.setForeground(new java.awt.Color(0, 255, 0));
        Dragger.setPreferredSize(new java.awt.Dimension(925, 35));
        Dragger.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                DraggerMouseDragged(evt);
            }
        });
        Dragger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DraggerMousePressed(evt);
            }
        });

        Minimizar.setBackground(new java.awt.Color(50, 50, 50));
        Minimizar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        Minimizar.setForeground(new java.awt.Color(255, 255, 255));
        Minimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minimizar.setText("_");
        Minimizar.setMaximumSize(new java.awt.Dimension(50, 35));
        Minimizar.setMinimumSize(new java.awt.Dimension(50, 35));
        Minimizar.setName(""); // NOI18N
        Minimizar.setPreferredSize(new java.awt.Dimension(50, 35));
        Minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MinimizarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MinimizarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                MinimizarMouseReleased(evt);
            }
        });

        Cerrar.setBackground(new java.awt.Color(50, 50, 50));
        Cerrar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        Cerrar.setForeground(new java.awt.Color(255, 255, 255));
        Cerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cerrar.setText("X");
        Cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CerrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CerrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CerrarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout DraggerLayout = new javax.swing.GroupLayout(Dragger);
        Dragger.setLayout(DraggerLayout);
        DraggerLayout.setHorizontalGroup(
            DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DraggerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DraggerLayout.setVerticalGroup(
            DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DraggerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(DraggerLayout.createSequentialGroup()
                .addComponent(Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dragger, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
            .addComponent(Base, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Dragger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseClicked
        if (evt.getButton() == 1) this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinimizarMouseClicked

    private void MinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseEntered
        Minimizar.setOpaque(true);
        Minimizar.setForeground(Color.CYAN);
    }//GEN-LAST:event_MinimizarMouseEntered

    private void MinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseExited
        Minimizar.setOpaque(false);
        Minimizar.setForeground(Color.WHITE);
    }//GEN-LAST:event_MinimizarMouseExited

    private void MinimizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MinimizarMousePressed

    private void MinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_MinimizarMouseReleased

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        if (evt.getButton() == 1) dispose();
    }//GEN-LAST:event_CerrarMouseClicked

    private void CerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseEntered
        Cerrar.setOpaque(true);
        Cerrar.setForeground(Color.red);
    }//GEN-LAST:event_CerrarMouseEntered

    private void CerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseExited
        Cerrar.setOpaque(false);
        Cerrar.setForeground(Color.white);
    }//GEN-LAST:event_CerrarMouseExited

    private void CerrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CerrarMousePressed

    private void CerrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CerrarMouseReleased

    private void DraggerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DraggerMouseDragged
        // Genera movimiento de la ventana
        if (this.getExtendedState() == JFrame.NORMAL && dragging) {
            Point current = this.getScreenLocation(evt);
            Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                (int) current.getY() - (int) start_drag.getY());
            Point new_location = new Point(
                (int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc
                    .getY() + offset.getY()));
            this.setLocation(new_location);
        }
    }//GEN-LAST:event_DraggerMouseDragged

    private void DraggerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DraggerMousePressed
        // Se empieza a mover ventana
        if (evt.getButton() == 1 && this.getExtendedState() == JFrame.NORMAL) {
            dragging = true;
            this.start_drag = this.getScreenLocation(evt);
            this.start_loc = this.getLocation();
        }
    }//GEN-LAST:event_DraggerMousePressed

    private void nacionalidadFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nacionalidadFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nacionalidadFieldActionPerformed

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void modificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarButtonActionPerformed

    private void aplicarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aplicarButtonActionPerformed

    private void nuevoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoButtonMouseClicked
        if (evt.getButton() == 1) {
            nombreField.setText("");
            nacionalidadField.setText("");
            nombreField.setEditable(true);
            nacionalidadField.setEditable(true);
            Lista.clearSelection();
        }
    }//GEN-LAST:event_nuevoButtonMouseClicked

    private void modificarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarButtonMouseClicked
        if (evt.getButton() == 1) {
            nombreField.setEditable(true);
            nacionalidadField.setEditable(true);
        }
    }//GEN-LAST:event_modificarButtonMouseClicked
    
    private void subirAutor(String n, String nac) {
        ConexionBD bd = ConexionJDBC.getInstance();
        try {
            int id = bd.añadirAutor(new Autor(n,nac));
            mFrame.consultarDiscos();
            //du.addAutor(new Autor(id,n,nac));
            cargarDatos();
            
        } catch (Exception e) {
            
        }
    }
    
    private void modificarAutor(int id, String n, String nac) {
        ConexionBD bd = ConexionJDBC.getInstance();
        try {
            bd.modificarAutor(id, n, nac);
            mFrame.consultarDiscos();
            //du.modificarAutor(id, n, nac);
            cargarDatos();
            
            
            
        } catch (Exception e) {
            
        }
    }
            
    private void aplicarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aplicarButtonMouseClicked
        if (evt.getButton() == 1) {
            if (nombreField.isEditable() && du != null && !nombreField.getText().isEmpty()) { // Nuevo/modificacion disponible
                if (Lista.getSelectedRow() == -1 && !du.existeAutor(nombreField.getText())) { // Nuevo autor
                    subirAutor(nombreField.getText(),nacionalidadField.getText());
                } else if (Lista.getSelectedRow() != -1 && !du.existeAutorIgnorando(nombreField.getText(), tuplaFilaAutor.get(Lista.convertRowIndexToModel(Lista.getSelectedRow())))) { // Modificar autor
                    modificarAutor(tuplaFilaAutor.get(Lista.convertRowIndexToModel(Lista.getSelectedRow())),nombreField.getText(),nacionalidadField.getText()); 
                }
                nombreField.setEditable(false);
                nacionalidadField.setEditable(false);
                nombreField.setText("");
                nacionalidadField.setText("");
                Lista.clearSelection();
            }
        }
    }//GEN-LAST:event_aplicarButtonMouseClicked

    private void eliminarAutor(int id) {
        ConexionBD bd = ConexionJDBC.getInstance();
        try {
            bd.eliminarAutor(id);
            //du.eliminarAutor(id);
            mFrame.consultarDiscos();
            cargarDatos();
            
            
            
        } catch (Exception e) {
            
        }
    }
    
    private void eliminarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarButtonMouseClicked
        if (evt.getButton() == 1) {
            if (Lista.getSelectedRow() != -1) {
                eliminarAutor(tuplaFilaAutor.get(Lista.convertRowIndexToModel(Lista.getSelectedRow())));
            }
        }
    }//GEN-LAST:event_eliminarButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AutoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutoresFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Base;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JPanel Dragger;
    private javax.swing.JTable Lista;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JScrollPane PanelLista;
    private javax.swing.JSeparator Separador;
    private javax.swing.JButton aplicarButton;
    private javax.swing.JTextField busquedaField;
    private javax.swing.JLabel busquedaLabel;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton modificarButton;
    private javax.swing.JTextField nacionalidadField;
    private javax.swing.JLabel nacionalidadLabel;
    private javax.swing.JTextField nombreField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JButton nuevoButton;
    // End of variables declaration//GEN-END:variables
}
