/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.interfaz;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import javax.sound.sampled.Mixer.Info;
import javax.swing.JFrame;
import sMM.basededatos.ConexionBD;
import sMM.basededatos.ConexionJDBC;
import sMM.modelo.DatosUsuario;
import sMM.modelo.Disco;
import sMM.modelo.Pair;

/**
 *
 * @author Eloy
 */
public class ModDiscoFrame extends javax.swing.JFrame {
    // Variables para mover ventana desde la barra de titulo (Dragger)
    Point start_drag;
    Point start_loc;
    boolean dragging;
    
    private Cloudinary cloudinary = new Cloudinary("cloudinary://846511673329722:8t_2g__--MhkPhYpG3xEyncFf5Y@storemymusic");
    MainFrame mframe;
    private Set<Integer> autoresSel = new HashSet<>();
    private Set<Integer> generosSel = new HashSet<>();
    private int ub = 0;
    private int disco = 0;
    private int id = 0;
    private int categoria = 0;
    private int tienda = 0;
    private File portada = null;
    /**
     * Creates new form addDiscoFrame
     */
    
    private void mostrarDatos() {
        Disco dis = MainFrame.getDatosUsuario().getDiscos().get(id);
        
        tituloField.setText(dis.getTitulo());
        coleccionField.setText(dis.getCodigoColeccion());
        catalogoField.setText(dis.getNumeroCatalogo());
        barrasField.setText(dis.getCodigoBarras());
        posField.setText(dis.getPosicionEnUbicacion());
        notaArea.setText(dis.getNotas());
        paisField.setText(dis.getPaisEdicion());
        fechaField.setText(dis.getFechaCompra());
        CheckBoxDeseados.setSelected(dis.getEnListaDeseos());
        CheckBoxFavorito.setSelected(dis.getFavorito());
        CheckBoxPrestado.setSelected(dis.getPrestado());
        
        if (dis.getIdTienda() != 0) {
            seleccionarTienda(dis.getIdTienda());
        }
        if (dis.getIdCategoria() != 0) {
            seleccionarCategoria(dis.getIdCategoria());
        }
        if (dis.getIdUbicacion() != 0) {
            seleccionarUbicacion(dis.getIdUbicacion());
        }
        if (dis.getIdDiscografica() != 0) {
            seleccionarDiscografica(dis.getIdDiscografica());
        }
        
        DatosUsuario du = MainFrame.getDatosUsuario();
        ArrayList<Pair<Integer,Integer>>  autoresDiscos = du.getAutoresDiscos();
        ArrayList<Pair<Integer,Integer>>  generosDiscos = du.getGenerosDiscos();
        
        for (Pair<Integer,Integer> p : autoresDiscos) {
            if (p.getValue() == id) seleccionarAutor(p.getKey());
        }
        for (Pair<Integer,Integer> p : generosDiscos) {
            if (p.getValue() == id) seleccionarGenero(p.getKey());
        }
        
       
        
        if (dis.getValoracion() >= 0) {
            try {
                valoracionField.setText(Float.toString(dis.getValoracion()));
            } catch (Exception e) {
                
            }
            
        }
        if (dis.getAnoEdicion() > 0) {
            try {
                edicionField.setText(Integer.toString(dis.getAnoEdicion()));
            } catch (Exception e) {
                
            }
        }
        if (dis.getAnoSalida() > 0) {
            try {
                salidaField.setText(Integer.toString(dis.getAnoSalida()));
            } catch (Exception e) {
                
            }
        }
        if (dis.getPrecioCompra() >= 0) {
            try {
                precioField.setText(Float.toString(dis.getPrecioCompra()));
            } catch (Exception e) {
                
            }
        }
        
        
    }
    public ModDiscoFrame(MainFrame f, int id) {
        this.id = id;
        mframe = f;
        
        initComponents();
        mostrarDatos();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    private void modificarDisco() {
        Disco d = new Disco(tituloField.getText());
        d.setID(id);
        int salida = 0;
        int edicion = 0;
        float valoracion = -1f;
        try {
            salida = Integer.parseInt(salidaField.getText());
        } catch (Exception e) {
            
        }
        try {
            edicion = Integer.parseInt(edicionField.getText());
        } catch (Exception e) {
            
        }
        try {
            valoracion = Float.parseFloat(valoracionField.getText());
        } catch (Exception e) {
            
        }
            
        d.setAnoSalida(salida);
        d.setAnoEdicion(edicion);
        d.setNumeroCatalogo(catalogoField.getText());
        d.setCodigoBarras(barrasField.getText());
        d.setNotas(notaArea.getText());
        if (valoracion >= 0) {
            d.setValoracion(valoracion);
        }
        d.setPaisEdicion(paisField.getText());
        d.setPosicionEnUbicacion(posField.getText());
        d.setIdCategoria(categoria);
        d.setIdDiscografica(disco);
        d.setIdUbicacion(ub);
        
        if(CheckBoxDeseados.isSelected()){
           d.setEnListaDeseos(true);
           d.setPrestado(false);
        } else if(CheckBoxPrestado.isSelected()){
           d.setEnListaDeseos(false);
           d.setPrestado(true);
        }
        
        if(CheckBoxFavorito.isSelected()){
           d.setFavorito(true);
        }
        
        ConexionBD bd = ConexionJDBC.getInstance();
        bd.modificarDisco(d);
        if (id != 0) {
        if (portada != null) {
            try {
                String idPortada = null;
                Map uploadResult = cloudinary.uploader().upload(portada, ObjectUtils.emptyMap());
                for (Object e : uploadResult.keySet()) {
                    if ("public_id".compareTo(e.toString()) == 0) idPortada = uploadResult.get(e).toString();
                }
                if (idPortada != null) {
                    bd.insertarPortada(id, idPortada);
                }

            } catch (Exception e) {
                
            }
        }
        
        bd.eliminarGenerosAutoresDisco(id);
        bd.añadirAutoresDisco(id, autoresSel);
        bd.añadirGenerosDisco(id, generosSel);
        
        }

        
        
        
    }
    
    private void addAdquisicion() {
        if (id != 0) {
            ConexionBD bd = ConexionJDBC.getInstance();
            float precio = -1f;
            try {
                precio = Float.parseFloat(precioField.getText());
            } catch (Exception e) {
                
            }
            String fecha = fechaField.getText();
            String[] tokens = fecha.split("/");
            StringJoiner joiner = new StringJoiner("-");
            try {
                joiner.add(tokens[2]);
                joiner.add(tokens[1]);
                joiner.add(tokens[0]);
                fecha = joiner.toString();
            } catch (Exception e) {
                fecha = "";
            }
            
            bd.insertarDatosAdquisicion(id, coleccionField.getText(), fecha, precio, tienda);
        }
    }
    
    Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint();
        Point target_location = this.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),
            (int) (target_location.getY() + cursor.getY()));
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
        Dragger = new javax.swing.JPanel();
        Minimizar = new javax.swing.JLabel();
        Cerrar = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        Basicos = new javax.swing.JPanel();
        tituloLabel = new javax.swing.JLabel();
        tituloField = new javax.swing.JTextField();
        barrasLabel = new javax.swing.JLabel();
        categoriaField = new javax.swing.JTextField();
        AceptarButton = new javax.swing.JLabel();
        autoresField = new javax.swing.JTextField();
        catalogoLabel = new javax.swing.JLabel();
        catalogoField = new javax.swing.JTextField();
        salidaLabel = new javax.swing.JLabel();
        salidaField = new javax.swing.JTextField();
        edicionField = new javax.swing.JTextField();
        edicionLabel = new javax.swing.JLabel();
        autoresLabel = new javax.swing.JLabel();
        generosLabel = new javax.swing.JLabel();
        generosField = new javax.swing.JTextField();
        ubicacionField = new javax.swing.JTextField();
        ubicacionLabel = new javax.swing.JLabel();
        posField = new javax.swing.JTextField();
        posLabel = new javax.swing.JLabel();
        paisField = new javax.swing.JTextField();
        edicionLabel1 = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        categoriaLabel = new javax.swing.JLabel();
        barrasField = new javax.swing.JTextField();
        discograficaField = new javax.swing.JTextField();
        discograficaLabel = new javax.swing.JLabel();
        valoracionField = new javax.swing.JTextField();
        valoracionLabel = new javax.swing.JLabel();
        notaLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        notaArea = new javax.swing.JTextArea();
        portadaField = new javax.swing.JTextField();
        portadaLabel = new javax.swing.JLabel();
        siguienteButton = new javax.swing.JLabel();
        CheckBoxDeseados = new javax.swing.JCheckBox();
        CheckBoxPrestado = new javax.swing.JCheckBox();
        CheckBoxFavorito = new javax.swing.JCheckBox();
        Adquisicion = new javax.swing.JPanel();
        AceptarAdqButton = new javax.swing.JLabel();
        infoAdqLabel = new javax.swing.JLabel();
        precioLabel = new javax.swing.JLabel();
        precioField = new javax.swing.JTextField();
        fechaLabel = new javax.swing.JLabel();
        coleccionField = new javax.swing.JTextField();
        coleccionLabel = new javax.swing.JLabel();
        fechaField = new javax.swing.JFormattedTextField();
        tiendaField = new javax.swing.JTextField();
        tiendaLabel = new javax.swing.JLabel();
        atrasButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(30, 30, 30));
        setUndecorated(true);

        Base.setBackground(new java.awt.Color(10, 10, 10));
        Base.setToolTipText("");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DraggerLayout.setVerticalGroup(
            DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DraggerLayout.createSequentialGroup()
                .addGroup(DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        Panel.setBackground(new java.awt.Color(10, 10, 10));
        Panel.setLayout(new java.awt.CardLayout());

        Basicos.setBackground(new java.awt.Color(10, 10, 10));

        tituloLabel.setForeground(new java.awt.Color(255, 255, 255));
        tituloLabel.setText("TÍTULO *");

        tituloField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloFieldActionPerformed(evt);
            }
        });

        barrasLabel.setForeground(new java.awt.Color(255, 255, 255));
        barrasLabel.setText("CÓDIGO DE BARRAS");

        categoriaField.setEditable(false);
        categoriaField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoriaFieldMouseClicked(evt);
            }
        });
        categoriaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaFieldActionPerformed(evt);
            }
        });

        AceptarButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AceptarButton.setText("Confirmar");
        AceptarButton.setOpaque(true);
        AceptarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AceptarButtonMouseClicked(evt);
            }
        });

        autoresField.setEditable(false);
        autoresField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autoresFieldMouseClicked(evt);
            }
        });

        catalogoLabel.setForeground(new java.awt.Color(255, 255, 255));
        catalogoLabel.setText("NÚMERO DE CATALOGO");

        catalogoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalogoFieldActionPerformed(evt);
            }
        });

        salidaLabel.setForeground(new java.awt.Color(255, 255, 255));
        salidaLabel.setText("AÑO DE SALIDA");

        salidaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaFieldActionPerformed(evt);
            }
        });

        edicionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edicionFieldActionPerformed(evt);
            }
        });

        edicionLabel.setForeground(new java.awt.Color(255, 255, 255));
        edicionLabel.setText("AÑO DE EDICIÓN");

        autoresLabel.setForeground(new java.awt.Color(255, 255, 255));
        autoresLabel.setText("AUTORES");

        generosLabel.setForeground(new java.awt.Color(255, 255, 255));
        generosLabel.setText("GÉNEROS");

        generosField.setEditable(false);
        generosField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generosFieldMouseClicked(evt);
            }
        });

        ubicacionField.setEditable(false);
        ubicacionField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ubicacionFieldMouseClicked(evt);
            }
        });

        ubicacionLabel.setForeground(new java.awt.Color(255, 255, 255));
        ubicacionLabel.setText("UBICACIÓN");

        posField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posFieldActionPerformed(evt);
            }
        });

        posLabel.setForeground(new java.awt.Color(255, 255, 255));
        posLabel.setText("POSICIÓN");

        paisField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paisFieldActionPerformed(evt);
            }
        });

        edicionLabel1.setForeground(new java.awt.Color(255, 255, 255));
        edicionLabel1.setText("PAÍS DE EDICIÓN");

        infoLabel.setForeground(new java.awt.Color(254, 254, 254));

        categoriaLabel.setForeground(new java.awt.Color(255, 255, 255));
        categoriaLabel.setText("CATEGORÍA");

        barrasField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barrasFieldActionPerformed(evt);
            }
        });

        discograficaField.setEditable(false);
        discograficaField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                discograficaFieldMouseClicked(evt);
            }
        });
        discograficaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discograficaFieldActionPerformed(evt);
            }
        });

        discograficaLabel.setForeground(new java.awt.Color(255, 255, 255));
        discograficaLabel.setText("DISCOGRAFICA");

        valoracionField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valoracionFieldMouseClicked(evt);
            }
        });

        valoracionLabel.setForeground(new java.awt.Color(255, 255, 255));
        valoracionLabel.setText("VALORACIÓN");

        notaLabel.setForeground(new java.awt.Color(255, 255, 255));
        notaLabel.setText("NOTA");

        notaArea.setColumns(20);
        notaArea.setRows(5);
        jScrollPane1.setViewportView(notaArea);

        portadaField.setEditable(false);
        portadaField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                portadaFieldMouseClicked(evt);
            }
        });

        portadaLabel.setForeground(new java.awt.Color(255, 255, 255));
        portadaLabel.setText("IMAGEN DE PORTADA");

        siguienteButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        siguienteButton.setText("Adquisición");
        siguienteButton.setOpaque(true);
        siguienteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                siguienteButtonMouseClicked(evt);
            }
        });

        CheckBoxDeseados.setForeground(new java.awt.Color(254, 254, 254));
        CheckBoxDeseados.setText("Añadir a lista de deseos");
        CheckBoxDeseados.setOpaque(false);

        CheckBoxPrestado.setForeground(new java.awt.Color(254, 254, 254));
        CheckBoxPrestado.setText("¿Prestado?");
        CheckBoxPrestado.setOpaque(false);
        CheckBoxPrestado.setPreferredSize(new java.awt.Dimension(149, 23));

        CheckBoxFavorito.setForeground(new java.awt.Color(254, 254, 254));
        CheckBoxFavorito.setText("Añadir a favoritos");
        CheckBoxFavorito.setOpaque(false);

        javax.swing.GroupLayout BasicosLayout = new javax.swing.GroupLayout(Basicos);
        Basicos.setLayout(BasicosLayout);
        BasicosLayout.setHorizontalGroup(
            BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BasicosLayout.createSequentialGroup()
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(autoresLabel)
                        .addGap(109, 109, 109)
                        .addComponent(edicionLabel)
                        .addGap(66, 66, 66)
                        .addComponent(barrasLabel))
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(tituloLabel)
                        .addGap(106, 106, 106)
                        .addComponent(salidaLabel)
                        .addGap(66, 66, 66)
                        .addComponent(catalogoLabel))
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(generosLabel)
                        .addGap(108, 108, 108)
                        .addComponent(edicionLabel1)
                        .addGap(90, 90, 90)
                        .addComponent(categoriaLabel))
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(notaLabel)
                        .addGap(130, 130, 130)
                        .addComponent(valoracionLabel)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(BasicosLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generosField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubicacionField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(posField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BasicosLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(portadaLabel))
                            .addComponent(valoracionField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(discograficaField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BasicosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CheckBoxDeseados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BasicosLayout.createSequentialGroup()
                                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(CheckBoxFavorito, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(BasicosLayout.createSequentialGroup()
                                                    .addComponent(siguienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(AceptarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(BasicosLayout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addComponent(CheckBoxPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(36, 36, 36))))))
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BasicosLayout.createSequentialGroup()
                                .addComponent(salidaField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(catalogoField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BasicosLayout.createSequentialGroup()
                                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(edicionField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(paisField))
                                .addGap(18, 18, 18)
                                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoriaField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(barrasField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(portadaField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
            .addGroup(BasicosLayout.createSequentialGroup()
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(ubicacionLabel)
                        .addGap(121, 121, 121)
                        .addComponent(posLabel)
                        .addGap(105, 105, 105)
                        .addComponent(discograficaLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BasicosLayout.setVerticalGroup(
            BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BasicosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(catalogoLabel)
                    .addComponent(salidaLabel)
                    .addComponent(tituloLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(catalogoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salidaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barrasLabel)
                    .addComponent(edicionLabel)
                    .addComponent(autoresLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edicionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barrasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generosLabel)
                    .addComponent(edicionLabel1)
                    .addComponent(categoriaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generosField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoriaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubicacionLabel)
                    .addComponent(posLabel)
                    .addComponent(discograficaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubicacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discograficaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckBoxPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valoracionLabel)
                            .addComponent(notaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BasicosLayout.createSequentialGroup()
                                .addComponent(valoracionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(portadaLabel)
                                .addGap(5, 5, 5)
                                .addComponent(portadaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(BasicosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(CheckBoxDeseados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CheckBoxFavorito)
                        .addGap(16, 16, 16)
                        .addGroup(BasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(siguienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AceptarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        Panel.add(Basicos, "basicos");

        Adquisicion.setBackground(new java.awt.Color(10, 10, 10));

        AceptarAdqButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AceptarAdqButton.setText("Confirmar");
        AceptarAdqButton.setOpaque(true);
        AceptarAdqButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AceptarAdqButtonMouseClicked(evt);
            }
        });

        infoAdqLabel.setForeground(new java.awt.Color(254, 254, 254));

        precioLabel.setForeground(new java.awt.Color(255, 255, 255));
        precioLabel.setText("PRECIO");

        precioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioFieldActionPerformed(evt);
            }
        });

        fechaLabel.setForeground(new java.awt.Color(255, 255, 255));
        fechaLabel.setText("FECHA DE COMPRA");

        coleccionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coleccionFieldActionPerformed(evt);
            }
        });

        coleccionLabel.setForeground(new java.awt.Color(255, 255, 255));
        coleccionLabel.setText("CÓDIGO DE COLECCIÓN");

        fechaField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        fechaField.setPreferredSize(new java.awt.Dimension(14, 24));

        tiendaField.setEditable(false);
        tiendaField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tiendaFieldMouseClicked(evt);
            }
        });
        tiendaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiendaFieldActionPerformed(evt);
            }
        });

        tiendaLabel.setForeground(new java.awt.Color(255, 255, 255));
        tiendaLabel.setText("TIENDA");

        atrasButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        atrasButton.setText("Básicos");
        atrasButton.setOpaque(true);
        atrasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atrasButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout AdquisicionLayout = new javax.swing.GroupLayout(Adquisicion);
        Adquisicion.setLayout(AdquisicionLayout);
        AdquisicionLayout.setHorizontalGroup(
            AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdquisicionLayout.createSequentialGroup()
                .addGroup(AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdquisicionLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(infoAdqLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AdquisicionLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(precioLabel))
                    .addGroup(AdquisicionLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(precioField)
                            .addComponent(coleccionField, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addGroup(AdquisicionLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(fechaLabel))
                            .addComponent(fechaField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(AdquisicionLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(coleccionLabel)))
                        .addGroup(AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AdquisicionLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(tiendaField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AdquisicionLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(tiendaLabel)))))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdquisicionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(atrasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AceptarAdqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        AdquisicionLayout.setVerticalGroup(
            AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdquisicionLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(infoAdqLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(precioLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaLabel)
                    .addComponent(tiendaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiendaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(coleccionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coleccionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(AdquisicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AceptarAdqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atrasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        Panel.add(Adquisicion, "adquisicion");

        javax.swing.GroupLayout BaseLayout = new javax.swing.GroupLayout(Base);
        Base.setLayout(BaseLayout);
        BaseLayout.setHorizontalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dragger, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BaseLayout.setVerticalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BaseLayout.createSequentialGroup()
                .addComponent(Dragger, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AceptarButtonMouseClicked
        if (evt.getButton() == 1) {
            if (tituloField.getText().compareTo("") == 0) {
                infoLabel.setText("Es necesario poner un título");
            } else {

                modificarDisco();
                mframe.consultarDiscos();
                dispose();
            }
            //MainFrame.getDatosUsuario();
        }
    }//GEN-LAST:event_AceptarButtonMouseClicked

    private void tituloFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tituloFieldActionPerformed

    private void categoriaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaFieldActionPerformed

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        if (evt.getButton() == 1) {
            dispose();
        }
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
    
    public void seleccionarAutor(int id) {
        if (autoresSel.contains(id)) {
            autoresSel.remove(id);
        } else {
            autoresSel.add(id);
        }
        DatosUsuario du = MainFrame.getDatosUsuario();
        StringJoiner joiner = new StringJoiner(", ");
        for (int x : autoresSel) {
            joiner.add(du.getAutores().get(x).getNombre());
        }
        autoresField.setText(joiner.toString());
    }
    
    public void seleccionarGenero(int id) {
        if (generosSel.contains(id)) {
            generosSel.remove(id);
        } else {
           generosSel.add(id);
        }
        DatosUsuario du = MainFrame.getDatosUsuario();
        StringJoiner joiner = new StringJoiner(", ");
        for (int x : generosSel) {
            joiner.add(du.getGeneros().get(x).getNombre());
        }
        generosField.setText(joiner.toString());
    }
    
    public void seleccionarUbicacion(int id) {
        if (ub == id) {
            ub = 0;
            ubicacionField.setText("");
        } else {
            ub = id;
            ubicacionField.setText(MainFrame.getDatosUsuario().getUbicacion(id).getNombre());
        }
    }
    
    public void seleccionarCategoria(int id) {
        if (categoria == id) {
            categoria = 0;
            categoriaField.setText("");
        } else {
            categoria = id;
            categoriaField.setText(MainFrame.getDatosUsuario().getCategorias().get(id).getNombre());
        }
    }
    
    public void quitarCategoria(int id) {
        if (categoria == id) {
            categoria = 0;
            categoriaField.setText("");
        }
    }
 
    public void seleccionarDiscografica(int id) {
        if (disco == id) {
            disco = 0;
            discograficaField.setText("");
        } else {
            disco = id;
            discograficaField.setText(MainFrame.getDatosUsuario().getDiscograficas().get(id).getNombre());
        }
    }
    
    public void seleccionarTienda(int id) {
        if (tienda == id) {
            tienda = 0;
            tiendaField.setText("");
        } else {
            tienda = id;
            tiendaField.setText(MainFrame.getDatosUsuario().getTiendas().get(id).getNombre());
        }
    }
    public void quitarTienda(int id) {
        if (tienda == id) {
            tienda = 0;
            tiendaField.setText("");
        }
    }
    
    public void quitarDiscografica(int id) {
        if (disco == id) {
            disco = 0;
            discograficaField.setText("");
        }
    }
    
    public void quitarUbicacion(int id) {
        if (ub == id) {
            ub = 0;
            ubicacionField.setText("");
        }
    }
    
    public void quitarAutor(int id) {
        if (autoresSel.contains(id)) {
            autoresSel.remove(id);
        }
        DatosUsuario du = MainFrame.getDatosUsuario();
        StringJoiner joiner = new StringJoiner(", ");
        for (int x : autoresSel) {
            joiner.add(du.getAutores().get(x).getNombre());
        }
        autoresField.setText(joiner.toString());
    }
    
    public void quitarGenero(int id) {
        if (generosSel.contains(id)) {
            generosSel.remove(id);
        }
        DatosUsuario du = MainFrame.getDatosUsuario();
        StringJoiner joiner = new StringJoiner(", ");
        for (int x : generosSel) {
            joiner.add(du.getGeneros().get(x).getNombre());
        }
        generosField.setText(joiner.toString());
    }
    
    private void catalogoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalogoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catalogoFieldActionPerformed

    private void salidaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salidaFieldActionPerformed

    private void edicionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edicionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edicionFieldActionPerformed

    private void autoresFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autoresFieldMouseClicked
        if (evt.getButton() == 1) {
            new AutoresFrameSel(mframe,this).setVisible(true);
        }
    }//GEN-LAST:event_autoresFieldMouseClicked

    private void generosFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generosFieldMouseClicked
        if (evt.getButton() == 1) {
            new GeneroFrameSel(mframe,this).setVisible(true);
        }
    }//GEN-LAST:event_generosFieldMouseClicked

    private void ubicacionFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubicacionFieldMouseClicked
        if (evt.getButton() == 1) {
            new UbicacionesFrameSel(mframe,this).setVisible(true);
        }
    }//GEN-LAST:event_ubicacionFieldMouseClicked

    private void posFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_posFieldActionPerformed

    private void paisFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paisFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paisFieldActionPerformed

    private void barrasFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barrasFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasFieldActionPerformed

    private void discograficaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discograficaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discograficaFieldActionPerformed

    private void valoracionFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valoracionFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_valoracionFieldMouseClicked

    private void portadaFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portadaFieldMouseClicked
        if (evt.getButton() == 1) {
            FileDialog dialog = new FileDialog((Frame)null, "Selecciona una imagen de portada");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String fileName = dialog.getFile();
            if (fileName != null) {
                portada = new File(dialog.getDirectory()+ "/"  + fileName);
                portadaField.setText(fileName);
            } else {
                portada = null;
                portadaField.setText("");
            }
            

            
        }
    }//GEN-LAST:event_portadaFieldMouseClicked

    private void categoriaFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriaFieldMouseClicked
        if (evt.getButton() == 1) {
            new CategoriasFrameSel(mframe,this).setVisible(true);
        }
    }//GEN-LAST:event_categoriaFieldMouseClicked

    private void discograficaFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discograficaFieldMouseClicked
        if (evt.getButton() == 1) {
            new DiscograficasFrameSel(mframe,this).setVisible(true);
        }
    }//GEN-LAST:event_discograficaFieldMouseClicked

    private void AceptarAdqButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AceptarAdqButtonMouseClicked
        if (evt.getButton() == 1) {
            addAdquisicion();
            mframe.consultarDiscos();
            dispose();
        }
    }//GEN-LAST:event_AceptarAdqButtonMouseClicked

    private void precioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioFieldActionPerformed

    private void coleccionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coleccionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coleccionFieldActionPerformed

    private void tiendaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiendaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiendaFieldActionPerformed

    private void atrasButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasButtonMouseClicked
         if (evt.getButton() == 1) {
            CardLayout cl = (CardLayout) Panel.getLayout();
            cl.show(Panel, "basicos");
         }
    }//GEN-LAST:event_atrasButtonMouseClicked

    private void tiendaFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiendaFieldMouseClicked
        if (evt.getButton() == 1) {
            new TiendasFrameSel(mframe,this).setVisible(true);
        }
    }//GEN-LAST:event_tiendaFieldMouseClicked

    private void siguienteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_siguienteButtonMouseClicked
        if (evt.getButton() == 1) {
            CardLayout cl = (CardLayout) Panel.getLayout();
            cl.show(Panel, "adquisicion");
        }
    }//GEN-LAST:event_siguienteButtonMouseClicked

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
            java.util.logging.Logger.getLogger(ModDiscoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModDiscoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModDiscoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModDiscoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AddDiscoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AceptarAdqButton;
    private javax.swing.JLabel AceptarButton;
    private javax.swing.JPanel Adquisicion;
    private javax.swing.JPanel Base;
    private javax.swing.JPanel Basicos;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JCheckBox CheckBoxDeseados;
    private javax.swing.JCheckBox CheckBoxFavorito;
    private javax.swing.JCheckBox CheckBoxPrestado;
    private javax.swing.JPanel Dragger;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel atrasButton;
    private javax.swing.JTextField autoresField;
    private javax.swing.JLabel autoresLabel;
    private javax.swing.JTextField barrasField;
    private javax.swing.JLabel barrasLabel;
    private javax.swing.JTextField catalogoField;
    private javax.swing.JLabel catalogoLabel;
    private javax.swing.JTextField categoriaField;
    private javax.swing.JLabel categoriaLabel;
    private javax.swing.JTextField coleccionField;
    private javax.swing.JLabel coleccionLabel;
    private javax.swing.JTextField discograficaField;
    private javax.swing.JLabel discograficaLabel;
    private javax.swing.JTextField edicionField;
    private javax.swing.JLabel edicionLabel;
    private javax.swing.JLabel edicionLabel1;
    private javax.swing.JFormattedTextField fechaField;
    private javax.swing.JLabel fechaLabel;
    private javax.swing.JTextField generosField;
    private javax.swing.JLabel generosLabel;
    private javax.swing.JLabel infoAdqLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea notaArea;
    private javax.swing.JLabel notaLabel;
    private javax.swing.JTextField paisField;
    private javax.swing.JTextField portadaField;
    private javax.swing.JLabel portadaLabel;
    private javax.swing.JTextField posField;
    private javax.swing.JLabel posLabel;
    private javax.swing.JTextField precioField;
    private javax.swing.JLabel precioLabel;
    private javax.swing.JTextField salidaField;
    private javax.swing.JLabel salidaLabel;
    private javax.swing.JLabel siguienteButton;
    private javax.swing.JTextField tiendaField;
    private javax.swing.JLabel tiendaLabel;
    private javax.swing.JTextField tituloField;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JTextField ubicacionField;
    private javax.swing.JLabel ubicacionLabel;
    private javax.swing.JTextField valoracionField;
    private javax.swing.JLabel valoracionLabel;
    // End of variables declaration//GEN-END:variables
}
