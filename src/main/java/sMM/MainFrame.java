/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.TimerTask;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import sun.java2d.SunGraphicsEnvironment;
/**
 *
 * @author Eloy
 */
public class MainFrame extends javax.swing.JFrame implements WindowListener {
    private static DatosUsuario du;
    private ImageIcon addDiscoImg;
    private ImageIcon addDiscoImg2;
    private ImageIcon defaultPortada;
    private HashMap<Integer,Integer> tuplaFilaDisco;
    addDiscoFrame frame;
    
    // Variables para mover ventana desde la barra de titulo (Dragger)
    Point start_drag;
    Point start_loc;
    
    // Variables de estado
    boolean menuOpen;
    boolean dragging;
    
    public static DatosUsuario getDatosUsuario() {
        return du;
    }
    
    public void consultarDiscos() {
        // Copia de datos en memoria
        ConexionBD bd = ConexionJDBC.getInstance();
        du = new DatosUsuario();
        du.setDiscos(bd.getDiscos());
        du.setAutores(bd.listaAutores());
        du.setAutoresDiscos(bd.getAutoresDiscos());
        du.setCategorias(bd.listaCategorias());
        du.setDiscograficas(bd.listaDiscograficas());
        du.setTiendas(bd.listaTiendas());
        du.setUbicaciones(bd.listaUbicaciones());

        tuplaFilaDisco = new HashMap<>();
        
        Lista.setVisible(false);
        // Poner en lista
        CustomTableModel listModel = new CustomTableModel(new String [] {
                "TÍTULO", "AUTORES", "EDICIÓN", "SALIDA", "VALORACIÓN"
            });

        int rowCount = listModel.getRowCount();
        //Remove rows one by one from the end of the table
        //for (int i = rowCount - 1; i >= 0; i--) {
         //   listModel.removeRow(i);
        //}
        int i = 0;
        for (Entry<Integer,Disco> ed : du.getDiscos().entrySet()) {
            Object[] listaAtributos = new Object[5];
            listaAtributos[0] = ed.getValue().getTitulo();
            listaAtributos[1] =  du.stringAutoresDisco(ed.getKey());
            String edicion, salida;
            if (ed.getValue().getAnoEdicion() == 0) {
                edicion = "-";
            } else {
                edicion = Integer.toString(ed.getValue().getAnoEdicion());
            }
            if (ed.getValue().getAnoSalida() == 0) {
                salida = "-";
            } else {
                salida = Integer.toString(ed.getValue().getAnoSalida());
            }
            listaAtributos[2] = edicion;
            listaAtributos[3] = salida;
            listaAtributos[4] = ed.getValue().getValoracion();
            tuplaFilaDisco.put(i, ed.getValue().getID());
            listModel.addRow(listaAtributos);
            i++;
        }
        Lista.setModel(listModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        Lista.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        Lista.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        Lista.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        Lista.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        Lista.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        Lista.getColumnModel().getColumn(4).setMinWidth(130);
        Lista.getColumnModel().getColumn(4).setMaxWidth(130);
        Lista.getColumnModel().getColumn(2).setMinWidth(100);
        Lista.getColumnModel().getColumn(2).setMaxWidth(100);
        Lista.getColumnModel().getColumn(3).setMinWidth(100);
        Lista.getColumnModel().getColumn(3).setMaxWidth(100);
        Lista.setVisible(true);
    }
    
    Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint();
        Point target_location = this.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),
            (int) (target_location.getY() + cursor.getY()));
    }
    
    private void resetBotones() {
        DiscosButton.setBackground(new Color(13,115,119));
        DiscosButton.setForeground(Color.white);
        DatosButton.setBackground(new Color(13,115,119));
        DatosButton.setForeground(Color.white);
    }
 
    
    public void animacionColapsarMenu() {
        TimerTask task = new ColapsarMenu(Menu,this);
        Timer timer = new Timer();
        timer.schedule(task, 0, 7);
    }
    
    public void animacionExpandirMenu() {
        TimerTask task = new ExpandirMenu(Menu,this);
        Timer timer = new Timer();
        timer.schedule(task, 0, 7);
    }

        


    /**
     * Creates new form MainFrame
     */
    
    private void cargarRecursos() {
        ClassLoader classLoader = getClass().getClassLoader();
        addDiscoImg = new ImageIcon( classLoader.getResource("discosIcon.png"));
        addDiscoImg2 = new ImageIcon( classLoader.getResource("discosIcon2.png"));
        addDiscoButton.setIcon(addDiscoImg);
        defaultPortada = new ImageIcon(classLoader.getResource("noPortada.png"));
         defaultPortada.setImage(defaultPortada.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));
        setIconImage(defaultPortada.getImage());
        
        addDiscoImg.setImage(addDiscoImg.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        addDiscoImg2.setImage(addDiscoImg2.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        
        portadaDisco.setIcon(defaultPortada);
        
          // Configuracion de los fonts
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font f1 = Font.createFont(Font.TRUETYPE_FONT,classLoader.getResourceAsStream("Avenir-Medium.ttf"));
            Font f2 = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream("Avenir-Book.ttf"));
            Font f3 = f1.deriveFont(Font.BOLD,14f);
            Font f4 = f1.deriveFont(Font.BOLD + Font.ITALIC,14f);
            f1 = f1.deriveFont(19f);
            f2 = f2.deriveFont(14f);
            ge.registerFont(f1);
            ge.registerFont(f2);
            ge.registerFont(f3);
            ge.registerFont(f4);
            labelTitulo.setFont(f4);
            DiscosButton.setFont(f1);
            DatosButton.setFont(f1);
            Lista.setFont(f2);
            Lista.getTableHeader().setFont(f3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public MainFrame() {
            
    
        //UIManager.put("ScrollBarUI", new MyScrollbar());
        
        initComponents();
       
        
        
      
        
        addDiscoButton.setOpaque(false);

        // Config lista de discos
        UIManager.getDefaults().put("TableHeader.cellBorder",createEmptyBorder());
        PanelLista.getViewport().setBackground(new Color(30,30,30));
        PanelLista.setBorder(createEmptyBorder());
        PanelLista.getHorizontalScrollBar().setUI(new MyScrollbar());
        PanelLista.getVerticalScrollBar().setUI(new MyScrollbar());
        Lista.getTableHeader().setBorder(null);
        Lista.getTableHeader().setBackground(new Color(205,205,205));
        Lista.getTableHeader().setForeground(Color.BLACK);
        Lista.getTableHeader().setReorderingAllowed(false);
        //Lista.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        
        //jTextField1.setEditable(false);
        //jTextField1.setColumns(20);
  
        //labelDatos.setRows(5);
        //labelDatos.setLineWrap(true);

       // labelDatos.setWrapStyleWord(true);

                
        
        consultarDiscos();
        
        //Lista.setAutoResizeMode(AUTO_RESIZE_OFF);
        //Lista.getColumnModel().getColumn(0).setPreferredWidth(132);
        //Lista.getColumnModel().getColumn(1).setPreferredWidth(132);


        
        // Configuracion dimension maxima
        GraphicsConfiguration config = this.getGraphicsConfiguration();
        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(config.getDevice());
        setMaximizedBounds(usableBounds);
        
        
        
        // Poner ventana en medio
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        // Listener para controlar mejor la ventana
        addWindowListener(this);
        
      
        Lista.getTableHeader().setPreferredSize(new Dimension(Lista.getTableHeader().getSize().width,30));
        //Lista.setPreferredScrollableViewportSize(Lista.getPreferredSize());
        // Poner en medio el texto de la lista de discos
        //DefaultListCellRenderer renderer = (DefaultListCellRenderer) Lista.getCellRenderer();
        //renderer.setHorizontalAlignment(SwingConstants.CENTER);
        //actualizarTarjetas();
        //Info.getLayout().preferredLayoutSize(Info);
        
        
        Lista.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (Lista.getSelectedRow() != -1) {
                    int row = Lista.convertRowIndexToModel(Lista.getSelectedRow());
                    String titulo = (String) Lista.getModel().getValueAt(row, 0);
                    String autores = (String) Lista.getModel().getValueAt(row, 1);
                    String edicion = (String) Lista.getModel().getValueAt(row, 2);
                    
                    String res;
                    if (autores == null || autores.compareTo("") == 0) {
                        res = titulo;
                    } else {
                        res = titulo + " - " + autores;
                    }
                    if (edicion.compareTo("-") != 0) {
                        res += " (" + edicion + ")";
                    }
                    labelTitulo.setText("<html>" + res +"</html>");
                    int idDisco = tuplaFilaDisco.get(row);
                    Disco d = du.getDiscos().get(idDisco);
                    StringJoiner joiner = new StringJoiner(",  ");
                    
                    if (d.getIdCategoria() != 0) {
                        joiner.add("Categoría: "  + du.getCategorias().get(d.getIdCategoria()).toString());
                    }
                    if (d.getAnoSalida() != 0) joiner.add("Año de salida:  " + d.getAnoSalida());
                    if (d.getNumeroCatalogo() != null) joiner.add("Número de catalogo:  " + d.getNumeroCatalogo());
                    if (d.getCodigoBarras() != null) joiner.add("Código de barras:  " + d.getCodigoBarras());
                    if (d.getCodigoColeccion() != null) joiner.add("Código de colección:  " + d.getCodigoColeccion());
                    if (d.getFechaCompra() != null)  joiner.add("Fecha de compra:  " + d.getFechaCompra());
                    if (d.getPrecioCompra() != -1)  joiner.add("Precio de compra:  " + d.getPrecioCompra());
                    if (d.getValoracion() != -1)  joiner.add("Valoración:  " + d.getValoracion());
                    if (d.getPaisEdicion() != null)  joiner.add("País de edición:  " + d.getPaisEdicion());
                    if (d.getIdDiscografica() != 0) {
                        joiner.add("Discografica:  "  + du.getDiscograficas().get(d.getIdDiscografica()).toString());
                    }
                    if (d.getIdUbicacion() != 0) {
                        joiner.add("Ubicación: "  + du.getUbicaciones().get(d.getIdUbicacion()).toString());
                    }
                   if (d.getPosicionEnUbicacion() != null)  joiner.add("Posición del disco:  " +d.getPosicionEnUbicacion());
                   if (d.getIdTienda() != 0) {
                        joiner.add("Tienda:  "  + du.getTiendas().get(d.getIdTienda()).toString());
                    }
                   
                    
                   
                    
                            
                     labelDatos.setText("<html>" + joiner.toString() + "</html>");
                    
                    
                }
            }
        });
        
        // Resaltar ventana activa por defecto
        DiscosButton.setBackground(new Color(20,255,236));
        DiscosButton.setForeground(new Color(50,50,50));
        
        // Inicializacion variables de estado
        EstadoMenu.moviendose = false;
        menuOpen = true;
        dragging = false;
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
        Menu = new javax.swing.JPanel();
        Activador = new javax.swing.JLabel();
        DiscosButton = new javax.swing.JLabel();
        DatosButton = new javax.swing.JLabel();
        Dragger = new javax.swing.JPanel();
        Cerrar = new javax.swing.JLabel();
        Maximizar = new javax.swing.JLabel();
        Minimizar = new javax.swing.JLabel();
        Info = new javax.swing.JPanel();
        Discos = new javax.swing.JPanel();
        removeDiscoButton = new javax.swing.JLabel();
        addDiscoButton = new javax.swing.JLabel();
        PanelLista = new javax.swing.JScrollPane();
        Lista = new javax.swing.JTable();
        PanelDatos = new javax.swing.JPanel();
        portadaDisco = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        labelDatos = new javax.swing.JLabel();
        Datos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1100, 720));

        Base.setBackground(new java.awt.Color(30, 30, 30));
        Base.setForeground(new java.awt.Color(255, 255, 255));
        Base.setToolTipText("");
        Base.setPreferredSize(new java.awt.Dimension(1100, 720));
        Base.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BaseMouseDragged(evt);
            }
        });
        Base.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BaseMousePressed(evt);
            }
        });

        Menu.setBackground(new java.awt.Color(0, 0, 0));
        Menu.setForeground(new java.awt.Color(50, 50, 50));
        Menu.setPreferredSize(new java.awt.Dimension(175, 720));

        Activador.setFont(new java.awt.Font("Audiowide", 1, 44)); // NOI18N
        Activador.setForeground(new java.awt.Color(255, 255, 255));
        Activador.setText("=");
        Activador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActivadorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ActivadorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ActivadorMouseExited(evt);
            }
        });

        DiscosButton.setBackground(new java.awt.Color(13, 115, 119));
        DiscosButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        DiscosButton.setForeground(new java.awt.Color(255, 255, 255));
        DiscosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiscosButton.setText("Discos");
        DiscosButton.setOpaque(true);
        DiscosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DiscosButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DiscosButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DiscosButtonMouseExited(evt);
            }
        });

        DatosButton.setBackground(new java.awt.Color(13, 115, 119));
        DatosButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        DatosButton.setForeground(new java.awt.Color(255, 255, 255));
        DatosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DatosButton.setText("Datos");
        DatosButton.setOpaque(true);
        DatosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatosButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Activador)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(DiscosButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DatosButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Activador, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(DiscosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DatosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dragger.setBackground(new java.awt.Color(30, 30, 30));
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

        Maximizar.setBackground(new java.awt.Color(50, 50, 50));
        Maximizar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        Maximizar.setForeground(new java.awt.Color(255, 255, 255));
        Maximizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Maximizar.setText("+");
        Maximizar.setMaximumSize(new java.awt.Dimension(50, 35));
        Maximizar.setMinimumSize(new java.awt.Dimension(50, 35));
        Maximizar.setName(""); // NOI18N
        Maximizar.setPreferredSize(new java.awt.Dimension(50, 35));
        Maximizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaximizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MaximizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MaximizarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MaximizarMousePressed(evt);
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

        javax.swing.GroupLayout DraggerLayout = new javax.swing.GroupLayout(Dragger);
        Dragger.setLayout(DraggerLayout);
        DraggerLayout.setHorizontalGroup(
            DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DraggerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DraggerLayout.setVerticalGroup(
            DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DraggerLayout.createSequentialGroup()
                .addGroup(DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, DraggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Maximizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Cerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Info.setPreferredSize(new java.awt.Dimension(925, 685));
        Info.setLayout(new java.awt.CardLayout());

        Discos.setBackground(new java.awt.Color(30, 30, 30));
        Discos.setPreferredSize(new java.awt.Dimension(925, 685));

        removeDiscoButton.setFont(new java.awt.Font("Audiowide", 0, 18)); // NOI18N
        removeDiscoButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        removeDiscoButton.setText("X");
        removeDiscoButton.setOpaque(true);
        removeDiscoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeDiscoButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeDiscoButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeDiscoButtonMouseExited(evt);
            }
        });

        addDiscoButton.setFont(new java.awt.Font("Audiowide", 0, 18)); // NOI18N
        addDiscoButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addDiscoButton.setOpaque(true);
        addDiscoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDiscoButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addDiscoButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addDiscoButtonMouseExited(evt);
            }
        });

        PanelLista.setBackground(new java.awt.Color(30, 30, 30));
        PanelLista.setBorder(null);
        PanelLista.setForeground(new java.awt.Color(30, 30, 30));
        PanelLista.setToolTipText("");

        Lista.setAutoCreateRowSorter(true);
        Lista.setBackground(new java.awt.Color(30, 30, 30));
        Lista.setForeground(new java.awt.Color(254, 254, 254));
        Lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Titulo", "Autores", "Año de edición", "Número de catalogo", "Valoración", "Precio de compra", "Código de barras"
            }
        ));
        Lista.setFocusable(false);
        Lista.setGridColor(new java.awt.Color(50, 50, 50));
        Lista.setIntercellSpacing(new java.awt.Dimension(0, 1));
        Lista.setRowHeight(30);
        Lista.setSelectionBackground(new java.awt.Color(50, 50, 50));
        Lista.setSelectionForeground(new java.awt.Color(254, 254, 254));
        Lista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Lista.setShowVerticalLines(false);
        PanelLista.setViewportView(Lista);

        PanelDatos.setBackground(new java.awt.Color(50, 50, 50));
        PanelDatos.setPreferredSize(new java.awt.Dimension(200, 200));

        portadaDisco.setBackground(new java.awt.Color(205, 205, 205));
        portadaDisco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        portadaDisco.setOpaque(true);

        labelTitulo.setForeground(new java.awt.Color(205, 205, 205));
        labelTitulo.setText("Prueba a seleccionar un disco.");

        labelDatos.setBackground(new java.awt.Color(205, 205, 205));
        labelDatos.setForeground(new java.awt.Color(240, 240, 240));
        labelDatos.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                    .addComponent(labelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(portadaDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(portadaDisco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout DiscosLayout = new javax.swing.GroupLayout(Discos);
        Discos.setLayout(DiscosLayout);
        DiscosLayout.setHorizontalGroup(
            DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DiscosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(PanelLista, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DiscosLayout.createSequentialGroup()
                .addContainerGap(775, Short.MAX_VALUE)
                .addComponent(removeDiscoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addDiscoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addComponent(PanelDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
        );
        DiscosLayout.setVerticalGroup(
            DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiscosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(PanelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addDiscoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeDiscoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Info.add(Discos, "discosCard");

        Datos.setBackground(new java.awt.Color(0, 0, 0));
        Datos.setForeground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout DatosLayout = new javax.swing.GroupLayout(Datos);
        Datos.setLayout(DatosLayout);
        DatosLayout.setHorizontalGroup(
            DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );
        DatosLayout.setVerticalGroup(
            DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        Info.add(Datos, "datosCard");

        javax.swing.GroupLayout BaseLayout = new javax.swing.GroupLayout(Base);
        Base.setLayout(BaseLayout);
        BaseLayout.setHorizontalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BaseLayout.createSequentialGroup()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Dragger, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)))
        );
        BaseLayout.setVerticalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BaseLayout.createSequentialGroup()
                .addComponent(Dragger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Base, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BaseMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BaseMouseDragged

    }//GEN-LAST:event_BaseMouseDragged

    private void BaseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BaseMousePressed

    }//GEN-LAST:event_BaseMousePressed

    private void ActivadorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActivadorMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ActivadorMouseExited

    private void ActivadorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActivadorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ActivadorMouseEntered

    private void ActivadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActivadorMouseClicked
        // Boton expandir/colapsar menu, 
        if (evt.getButton() == 1) {
            if (!EstadoMenu.moviendose) { // Desactiva boton si se esta moviendo
                EstadoMenu.moviendose = true;
                
                if (menuOpen) {
                    for (Component x : Menu.getComponents()) {
                        x.setVisible(false);
                    }
                    Activador.setVisible(true);
                    menuOpen = false;
                    animacionColapsarMenu();
                    //dMenu = new Dimension(50,this.getSize().height);
                    //dInfo = new Dimension(this.getSize().width-50,this.getSize().height-100);
                } else {
                    menuOpen = true;
                    animacionExpandirMenu();
                }
            }
            
            //Menu.setVisible(false);
            //Info.setVisible(false);
            //Menu.setPreferredSize(dMenu);
            //Info.setPreferredSize(dInfo);
            //Menu.setVisible(true);
            //Info.setVisible(true);
        }
    }//GEN-LAST:event_ActivadorMouseClicked

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

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        if (evt.getButton() == 1) System.exit(0);
    }//GEN-LAST:event_CerrarMouseClicked

    private void MaximizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaximizarMouseEntered
        Maximizar.setOpaque(true);
        Maximizar.setForeground(Color.ORANGE);
    }//GEN-LAST:event_MaximizarMouseEntered

    private void MaximizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaximizarMouseExited
        Maximizar.setOpaque(false);
        Maximizar.setForeground(Color.white);
    }//GEN-LAST:event_MaximizarMouseExited

    private void MaximizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaximizarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaximizarMousePressed

    private void MaximizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaximizarMouseClicked
        if (evt.getButton() == 1) {
            if (this.getExtendedState() == JFrame.NORMAL) {
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                Maximizar.setText("-");
            } else {
                this.setExtendedState(JFrame.NORMAL);
                Maximizar.setText("+");
            }

        }
    }//GEN-LAST:event_MaximizarMouseClicked

    private void MinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseClicked
        if (evt.getButton() == 1) this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinimizarMouseClicked

    private void MinimizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MinimizarMousePressed

    private void MinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_MinimizarMouseReleased

    private void MinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseEntered
        Minimizar.setOpaque(true);
        Minimizar.setForeground(Color.CYAN);
    }//GEN-LAST:event_MinimizarMouseEntered

    private void MinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseExited
        Minimizar.setOpaque(false);
        Minimizar.setForeground(Color.WHITE);
    }//GEN-LAST:event_MinimizarMouseExited

    private void DiscosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiscosButtonMouseClicked
        if (evt.getButton() == 1) {
            resetBotones();
            DiscosButton.setBackground(new Color(20,255,236));
            DiscosButton.setForeground(new Color(50,50,50));
            CardLayout cl = (CardLayout) Info.getLayout();
            cl.show(Info, "discosCard");
        }
    }//GEN-LAST:event_DiscosButtonMouseClicked

    private void DatosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosButtonMouseClicked
        if (evt.getButton() == 1) {
            resetBotones();
            DatosButton.setBackground(new Color(20,255,236));
            DatosButton.setForeground(new Color(50,50,50));
            CardLayout cl = (CardLayout) Info.getLayout();
            cl.show(Info, "datosCard");
        }
    }//GEN-LAST:event_DatosButtonMouseClicked

    private void DiscosButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiscosButtonMouseEntered

    }//GEN-LAST:event_DiscosButtonMouseEntered

    private void DiscosButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiscosButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DiscosButtonMouseExited

    private void addDiscoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDiscoButtonMouseClicked
        if (evt.getButton() == 1) {
            if (frame == null || !frame.isVisible()) {
                frame = new addDiscoFrame(this);
                frame.setVisible(true);
            } else {
                frame.setExtendedState(NORMAL);
                frame.toFront();
            }
        }
    }//GEN-LAST:event_addDiscoButtonMouseClicked

    private void addDiscoButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDiscoButtonMouseEntered
        addDiscoButton.setIcon(addDiscoImg2);
    }//GEN-LAST:event_addDiscoButtonMouseEntered

    private void addDiscoButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDiscoButtonMouseExited
        addDiscoButton.setIcon(addDiscoImg);
    }//GEN-LAST:event_addDiscoButtonMouseExited

    private void removeDiscoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeDiscoButtonMouseClicked
       if (evt.getButton() == 1) {

           if (Lista.getSelectedRow() != -1) {
                          ConexionBD bd = ConexionJDBC.getInstance();
            labelTitulo.setText("Disco borrado.");
            labelDatos.setText("");
           bd.eliminarDisco(tuplaFilaDisco.get( Lista.convertRowIndexToModel(Lista.getSelectedRow())));
           consultarDiscos();
           }

       }
    }//GEN-LAST:event_removeDiscoButtonMouseClicked

    private void removeDiscoButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeDiscoButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_removeDiscoButtonMouseEntered

    private void removeDiscoButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeDiscoButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_removeDiscoButtonMouseExited

    public void mensajeDiscoCreado() {
            labelTitulo.setText("Disco creado.");
            labelDatos.setText("");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               MainFrame f =  new MainFrame();
               f.setVisible(true);
               f.cargarRecursos();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Activador;
    private javax.swing.JPanel Base;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JPanel Datos;
    private javax.swing.JLabel DatosButton;
    private javax.swing.JPanel Discos;
    private javax.swing.JLabel DiscosButton;
    private javax.swing.JPanel Dragger;
    private javax.swing.JPanel Info;
    private javax.swing.JTable Lista;
    private javax.swing.JLabel Maximizar;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JScrollPane PanelLista;
    private javax.swing.JLabel addDiscoButton;
    private javax.swing.JLabel labelDatos;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel portadaDisco;
    private javax.swing.JLabel removeDiscoButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
    @Override
    public void windowDeiconified(WindowEvent event) {
        if (Maximizar.getText().compareTo("-") == 0) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Se abre con forma de ventana maxima si se ha minimizado asi
        }
    }
}
