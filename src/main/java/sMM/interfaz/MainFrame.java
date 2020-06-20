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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.TimerTask;
import javax.swing.JFrame;
import java.util.Timer;
import javax.imageio.ImageIO;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import sMM.basededatos.*;
import sMM.modelo.*;
import sun.java2d.SunGraphicsEnvironment;

/**
 *
 * @author Eloy
 */
public class MainFrame extends javax.swing.JFrame implements WindowListener {

    private static DatosUsuario du;
    private ImageIcon addDiscoImg;
    private ImageIcon addDiscoImg2;
    private ImageIcon rmvDiscoImg;
    private ImageIcon rmvDiscoImg2;
    private ImageIcon autores;
    private ImageIcon amigos;
    private ImageIcon amigosSel;
    private ImageIcon autoresSel;
    private ImageIcon categorias;
    private ImageIcon categoriasSel;
    private ImageIcon tiendas;
    private ImageIcon tiendasSel;
    private ImageIcon ubicaciones;
    private ImageIcon generos;
    private ImageIcon generosSel;
    private ImageIcon ubicacionesSel;
    private ImageIcon discograficas;
    private ImageIcon discograficasSel;
    private ImageIcon formatos;
    private ImageIcon formatosSel;
    private ImageIcon defaultPortada;
    private HashMap<Integer, Integer> tuplaFilaDisco;
    private Cloudinary cloudinary = new Cloudinary("cloudinary://846511673329722:8t_2g__--MhkPhYpG3xEyncFf5Y@storemymusic");
    AddDiscoFrame frame;
    RowFilter<CustomTableModel, Integer> FiltroListaDeseos = new RowFilter<CustomTableModel, Integer>() {
            public boolean include(RowFilter.Entry<? extends CustomTableModel, ? extends Integer> entry) {
                
                int idEnTabla = entry.getIdentifier();
                int idDiscoMarcado = tuplaFilaDisco.get(Lista.convertRowIndexToModel(idEnTabla));
                if (du.getDiscos().get(idDiscoMarcado).getEnListaDeseos()==true) {
                    // Returning true indicates this row should be shown.
                    return true;
                }
                return false;
            }
        };
    RowFilter<CustomTableModel, Integer> FiltroColeccion = new RowFilter<CustomTableModel, Integer>() {
            public boolean include(RowFilter.Entry<? extends CustomTableModel, ? extends Integer> entry) {
                
                int idEnTabla = entry.getIdentifier();
                int idDiscoMarcado = tuplaFilaDisco.get(Lista.convertRowIndexToModel(idEnTabla));
                if (du.getDiscos().get(idDiscoMarcado).getEnListaDeseos()==false) {
                    // Returning true indicates this row should be shown.
                    return true;
                }
                return false;
            }
        };
    RowFilter<CustomTableModel, Integer> FiltroFavoritos = new RowFilter<CustomTableModel, Integer>() {
            public boolean include(RowFilter.Entry<? extends CustomTableModel, ? extends Integer> entry) {
                
                int idEnTabla = entry.getIdentifier();
                int idDiscoMarcado = tuplaFilaDisco.get(Lista.convertRowIndexToModel(idEnTabla));
                if (du.getDiscos().get(idDiscoMarcado).getFavorito()==true) {
                    // Returning true indicates this row should be shown.
                    return true;
                }
                return false;
            }
        };
    

    // Variables para mover ventana desde la barra de titulo (Dragger)
    Point start_drag;
    Point start_loc;

    // Variables de estado
    boolean menuOpen;
    boolean dragging;
    boolean listaDeseosBool;
    boolean coleccionBool;
    boolean favoritosBool;

    public static DatosUsuario getDatosUsuario() {
        return du;
    }

    public void consultarDiscos() {
        // Copia de datos en memoria
        ConexionBD bd = ConexionJDBC.getInstance();
        du = new DatosUsuario();
        du.setDiscos(bd.getDiscos());
        du.setFormatos(bd.listaFormatos());
        du.setSoportes(bd.listaSoportes());
        du.setGeneros(bd.listaGeneros());
        du.setAutores(bd.listaAutores());
        du.setAmigos(bd.listaAmigos());
        du.setAutoresDiscos(bd.getAutoresDiscos());
        du.setGenerosDiscos(bd.getGenerosDiscos());
        du.setCategorias(bd.listaCategorias());
        du.setDiscograficas(bd.listaDiscograficas());
        du.setTiendas(bd.listaTiendas());
        du.setUbicaciones(bd.listaUbicaciones());

        tuplaFilaDisco = new HashMap<>();

        Lista.setVisible(false);
        // Poner en lista
        CustomTableModel listModel = new CustomTableModel(new String[]{
            "TÍTULO", "AUTORES", "EDICIÓN", "SALIDA", "VALORACIÓN"
        });

        //int rowCount = listModel.getRowCount();
        //Remove rows one by one from the end of the table
        //for (int i = rowCount - 1; i >= 0; i--) {
        //   listModel.removeRow(i);
        //}
        int i = 0;
        for (Entry<Integer, Disco> ed : du.getDiscos().entrySet()) {
            Object[] listaAtributos = new Object[5];
            listaAtributos[0] = ed.getValue().getTitulo();
            listaAtributos[1] = du.stringAutoresDisco(ed.getKey());
            if (ed.getValue().getAnoEdicion() != 0) {
                listaAtributos[2] = ed.getValue().getAnoEdicion();
            }
            if (ed.getValue().getAnoSalida() != 0) {
                listaAtributos[3] = ed.getValue().getAnoSalida();
            }
            if (ed.getValue().getValoracion() != -1) {
                listaAtributos[4] = ed.getValue().getValoracion();
            }

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
        DiscosButton.setBackground(new Color(13, 115, 119));
        DiscosButton.setForeground(Color.white);
        DatosButton.setBackground(new Color(13, 115, 119));
        DatosButton.setForeground(Color.white);
    }

    public void animacionColapsarMenu() {
        TimerTask task = new ColapsarMenu(Menu, this);
        Timer timer = new Timer();
        timer.schedule(task, 0, 7);
    }

    public void animacionExpandirMenu() {
        TimerTask task = new ExpandirMenu(Menu, this);
        Timer timer = new Timer();
        timer.schedule(task, 0, 7);
    }

    private void cargarRecursos() {
        ClassLoader classLoader = getClass().getClassLoader();
        addDiscoImg = new ImageIcon(classLoader.getResource("addDiscosIcon.png"));
        addDiscoImg2 = new ImageIcon(classLoader.getResource("addDiscosIcon2.png"));
        rmvDiscoImg = new ImageIcon(classLoader.getResource("rmvDiscoImg.png"));
        rmvDiscoImg2 = new ImageIcon(classLoader.getResource("rmvDiscoImg2.png"));
        addDiscoButton.setIcon(addDiscoImg);
        defaultPortada = new ImageIcon(classLoader.getResource("noPortada.png"));
        autores = new ImageIcon(classLoader.getResource("autores.png"));
        autoresSel = new ImageIcon(classLoader.getResource("autoresSel.png"));
        categorias = new ImageIcon(classLoader.getResource("categorias.png"));
        categoriasSel = new ImageIcon(classLoader.getResource("categoriasSel.png"));
        discograficas = new ImageIcon(classLoader.getResource("discograficas.png"));
        discograficasSel = new ImageIcon(classLoader.getResource("discograficasSel.png"));
        discoButton.setIcon(discograficas);
        formatos = new ImageIcon(classLoader.getResource("formatos.png"));
        formatosSel = new ImageIcon(classLoader.getResource("formatosSel.png"));
        tiendas = new ImageIcon(classLoader.getResource("tiendas.png"));
        tiendasSel = new ImageIcon(classLoader.getResource("tiendasSel.png"));
        ubicaciones = new ImageIcon(classLoader.getResource("ubicaciones.png"));
        ubicacionesSel = new ImageIcon(classLoader.getResource("ubicacionesSel.png"));
        generos = new ImageIcon(classLoader.getResource("generos.png"));
        generosSel = new ImageIcon(classLoader.getResource("generosSel.png"));
        amigos = new ImageIcon(classLoader.getResource("amigos.png"));
        amigosSel = new ImageIcon(classLoader.getResource("amigosSel.png"));
        removeDiscoButton.setIcon(rmvDiscoImg);
        amigosButton.setIcon(amigos);
        generosButton.setIcon(generos);
        ubicacionesButton.setIcon(ubicaciones);
        tiendasButton.setIcon(tiendas);
        formatosButton.setIcon(formatos);
        categoriasButton.setIcon(categorias);
        defaultPortada.setImage(defaultPortada.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));
        setIconImage(defaultPortada.getImage());
        autoresButton.setIcon(autores);
        addDiscoImg.setImage(addDiscoImg.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        addDiscoImg2.setImage(addDiscoImg2.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        rmvDiscoImg.setImage(rmvDiscoImg.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        rmvDiscoImg2.setImage(rmvDiscoImg2.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        portadaDisco.setIcon(defaultPortada);

        // Configuracion de los fonts
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font f1 = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream("Avenir-Medium.ttf"));
            Font f2 = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream("Avenir-Book.ttf"));
            Font f3 = f1.deriveFont(Font.BOLD, 14f);
            Font f4 = f1.deriveFont(Font.BOLD + Font.ITALIC, 14f);
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

    public void configComponentes() {
        addDiscoButton.setOpaque(false);

        // Config lista de discos
        UIManager.getDefaults().put("TableHeader.cellBorder", createEmptyBorder());
        PanelLista.getViewport().setBackground(new Color(30, 30, 30));
        PanelLista.setBorder(createEmptyBorder());
        PanelLista.getHorizontalScrollBar().setUI(new MyScrollbar());
        PanelLista.getVerticalScrollBar().setUI(new MyScrollbar());
        Lista.getTableHeader().setBorder(null);
        Lista.getTableHeader().setBackground(new Color(205, 205, 205));
        Lista.getTableHeader().setForeground(Color.BLACK);
        Lista.getTableHeader().setReorderingAllowed(false);
        Lista.getTableHeader().setPreferredSize(new Dimension(Lista.getTableHeader().getSize().width, 30));

        // Comportamiento al seleccionar un disco de la lista
        Lista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (Lista.getSelectedRow() != -1) {
                    int row = Lista.convertRowIndexToModel(Lista.getSelectedRow());
                    String titulo = (String) Lista.getModel().getValueAt(row, 0);
                    String autores = (String) Lista.getModel().getValueAt(row, 1);

                    String res;
                    int idDisco = tuplaFilaDisco.get(row);
                    Disco d = du.getDiscos().get(idDisco);
                    int edicion = d.getAnoEdicion();

                    if (autores == null || autores.compareTo("") == 0) {
                        res = titulo;
                    } else {
                        res = titulo + " - " + autores;
                    }
                    if (edicion != 0) {
                        res += " (" + edicion + ")";
                    }
                    labelTitulo.setText("<html>" + res + "</html>");

                    StringJoiner joiner = new StringJoiner(",  ");

                    if (d.getIdCategoria() != 0) {
                        joiner.add("Categoría: " + du.getCategorias().get(d.getIdCategoria()).toString());
                    }
                    if (d.getAnoSalida() != 0) {
                        joiner.add("Año de salida:  " + d.getAnoSalida());
                    }
                    if (d.getNumeroCatalogo() != null && d.getNumeroCatalogo().compareTo("") != 0) {
                        joiner.add("Número de catalogo:  " + d.getNumeroCatalogo());
                    }
                    if (d.getCodigoBarras() != null && d.getCodigoBarras().compareTo("") != 0) {
                        joiner.add("Código de barras:  " + d.getCodigoBarras());
                    }
                    if (d.getCodigoColeccion() != null && d.getCodigoColeccion().compareTo("") != 0) {
                        joiner.add("Código de colección:  " + d.getCodigoColeccion());
                    }
                    if (d.getFechaCompra() != null) {
                        joiner.add("Fecha de compra:  " + d.getFechaCompra());
                    }
                    if (d.getPrecioCompra() != -1) {
                        joiner.add("Precio de compra:  " + d.getPrecioCompra());
                    }
                    if (d.getValoracion() != -1) {
                        joiner.add("Valoración:  " + d.getValoracion());
                    }
                    if (d.getPaisEdicion() != null && d.getPaisEdicion().compareTo("") != 0) {
                        joiner.add("País de edición:  " + d.getPaisEdicion());
                    }
                    if (d.getIdDiscografica() != 0) {
                        joiner.add("Discografica:  " + du.getDiscograficas().get(d.getIdDiscografica()).toString());
                    }
                    if (d.getIdUbicacion() != 0) {
                        joiner.add("Ubicación: " + du.getUbicaciones().get(d.getIdUbicacion()).toString());
                    }
                    if (d.getPosicionEnUbicacion() != null && d.getPosicionEnUbicacion().compareTo("") != 0) {
                        joiner.add("Posición del disco:  " + d.getPosicionEnUbicacion());
                    }
                    if (d.getIdTienda() != 0) {
                        joiner.add("Tienda:  " + du.getTiendas().get(d.getIdTienda()).toString());
                    }
                    String generos = du.stringGenerosDisco(idDisco);
                    if (generos.compareTo("") != 0) {
                        joiner.add("Géneros: " + generos);
                    }

                    if (d.getPortada() == null || d.getPortada().compareTo("") == 0) {
                        portadaDisco.setIcon(defaultPortada);
                    } else {
                        try {

                            String path = cloudinary.url().resourceType("image").generate(d.getPortada());
                            URL url = new URL(path);
                            Image image = ImageIO.read(url);
                            portadaDisco.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

                        } catch (Exception e) {
                            portadaDisco.setIcon(defaultPortada);
                        }
                    }

                    if (d.getNotas().compareTo("") != 0) {
                        labelNota.setText("<html> NOTA<br>" + d.getNotas() + "</html>");
                    } else {
                        labelNota.setText("");
                    }

                    if (joiner.toString().compareTo("") == 0) {
                        joiner.add("Vaya, que vacío. Prueba a añadir datos.");
                    }

                    labelDatos.setText("<html>" + joiner.toString() + "</html>");

                }
            }
        });
        // Resaltar ventana activa por defecto
        DiscosButton.setBackground(new Color(20, 255, 236));
        DiscosButton.setForeground(new Color(50, 50, 50));

        busquedaField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent arg0) {

            }
            public void insertUpdate(DocumentEvent arg0) {
                String text = busquedaField.getText();
                TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
                if (text.trim().length() == 0) {
                    DescripcionLabel.setText("Mostrando todos los discos");
                    rs.setRowFilter(null);
                } else {
                    DescripcionLabel.setText("Filtrando por Busqueda");
                    listaDeseosBool = false;
                    coleccionBool = false;
                    favoritosBool = false;
                    rs.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            public void removeUpdate(DocumentEvent arg0) {
                String text = busquedaField.getText();
                TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
                
                if (text.trim().length() == 0) {
                    DescripcionLabel.setText("Mostrando todos los discos");
                    rs.setRowFilter(null);
                } else {
                    DescripcionLabel.setText("Filtrando por Busqueda");
                    listaDeseosBool = false;
                    coleccionBool = false;
                    favoritosBool = false;
                    rs.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
    }

    public void configVentana() {
        // Configuracion dimension maxima
        GraphicsConfiguration config = this.getGraphicsConfiguration();
        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(config.getDevice());
        setMaximizedBounds(usableBounds);

        // Poner ventana en medio
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // Listener para controlar mejor la ventana
        addWindowListener(this);
    }

    public MainFrame() {
        // Inicializacion variables de estado
        EstadoMenu.moviendose = false;
        menuOpen = true;
        dragging = false;

        initComponents();
        configComponentes();
        configVentana();
        cargarRecursos();
        consultarDiscos();
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
        labelNota = new javax.swing.JLabel();
        busquedaField = new javax.swing.JTextField();
        busquedaLabel = new javax.swing.JLabel();
        editarButton = new javax.swing.JLabel();
        ListaDeseosButton = new javax.swing.JLabel();
        DescripcionLabel = new javax.swing.JLabel();
        ColeccionButton = new javax.swing.JLabel();
        FavoritosButton = new javax.swing.JLabel();
        Datos = new javax.swing.JPanel();
        autoresButton = new javax.swing.JLabel();
        ubicacionesButton = new javax.swing.JLabel();
        generosButton = new javax.swing.JLabel();
        categoriasButton = new javax.swing.JLabel();
        formatosButton = new javax.swing.JLabel();
        discoButton = new javax.swing.JLabel();
        tiendasButton = new javax.swing.JLabel();
        amigosButton = new javax.swing.JLabel();

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

        labelNota.setForeground(new java.awt.Color(254, 254, 254));
        labelNota.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelNota.setName(""); // NOI18N

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(labelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(labelNota, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(labelNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        busquedaField.setBackground(new java.awt.Color(50, 50, 50));
        busquedaField.setForeground(new java.awt.Color(254, 254, 254));
        busquedaField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        busquedaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaFieldActionPerformed(evt);
            }
        });
        busquedaField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                busquedaFieldPropertyChange(evt);
            }
        });

        busquedaLabel.setForeground(new java.awt.Color(254, 254, 254));
        busquedaLabel.setText("Búsqueda");

        editarButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editarButton.setText("Editar");
        editarButton.setOpaque(true);
        editarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarButtonMouseClicked(evt);
            }
        });

        ListaDeseosButton.setBackground(new java.awt.Color(230, 230, 230));
        ListaDeseosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ListaDeseosButton.setText("Lista de Deseos");
        ListaDeseosButton.setOpaque(true);
        ListaDeseosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaDeseosButtonMouseClicked(evt);
            }
        });

        DescripcionLabel.setForeground(new java.awt.Color(254, 254, 254));
        DescripcionLabel.setText("Mostrando todos los discos");

        ColeccionButton.setBackground(new java.awt.Color(230, 230, 230));
        ColeccionButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ColeccionButton.setText("Colección");
        ColeccionButton.setOpaque(true);
        ColeccionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ColeccionButtonMouseClicked(evt);
            }
        });

        FavoritosButton.setBackground(new java.awt.Color(230, 230, 230));
        FavoritosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FavoritosButton.setText("Favoritos");
        FavoritosButton.setOpaque(true);
        FavoritosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FavoritosButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DiscosLayout = new javax.swing.GroupLayout(Discos);
        Discos.setLayout(DiscosLayout);
        DiscosLayout.setHorizontalGroup(
            DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
            .addGroup(DiscosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DiscosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ListaDeseosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ColeccionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FavoritosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DescripcionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(busquedaField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(busquedaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeDiscoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addDiscoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(DiscosLayout.createSequentialGroup()
                        .addComponent(PanelLista)
                        .addGap(50, 50, 50))))
        );
        DiscosLayout.setVerticalGroup(
            DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiscosLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(PanelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ListaDeseosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FavoritosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addDiscoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeDiscoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DiscosLayout.createSequentialGroup()
                        .addComponent(busquedaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DiscosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(busquedaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DescripcionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(editarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ColeccionButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ListaDeseosButton.getAccessibleContext().setAccessibleName("MostrarListaDeseos");

        Info.add(Discos, "discosCard");

        Datos.setBackground(new java.awt.Color(30, 30, 30));
        Datos.setForeground(new java.awt.Color(153, 255, 153));
        Datos.setLayout(new java.awt.GridLayout(2, 4));

        autoresButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autoresButton.setPreferredSize(new java.awt.Dimension(150, 200));
        autoresButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autoresButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                autoresButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                autoresButtonMouseExited(evt);
            }
        });
        Datos.add(autoresButton);

        ubicacionesButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ubicacionesButton.setPreferredSize(new java.awt.Dimension(150, 200));
        ubicacionesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ubicacionesButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ubicacionesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ubicacionesButtonMouseExited(evt);
            }
        });
        Datos.add(ubicacionesButton);

        generosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        generosButton.setPreferredSize(new java.awt.Dimension(150, 200));
        generosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generosButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generosButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generosButtonMouseExited(evt);
            }
        });
        Datos.add(generosButton);

        categoriasButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoriasButton.setPreferredSize(new java.awt.Dimension(150, 200));
        categoriasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoriasButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categoriasButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categoriasButtonMouseExited(evt);
            }
        });
        Datos.add(categoriasButton);

        formatosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        formatosButton.setPreferredSize(new java.awt.Dimension(150, 200));
        formatosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formatosButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formatosButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formatosButtonMouseExited(evt);
            }
        });
        Datos.add(formatosButton);

        discoButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discoButton.setPreferredSize(new java.awt.Dimension(150, 200));
        discoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                discoButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                discoButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                discoButtonMouseExited(evt);
            }
        });
        Datos.add(discoButton);

        tiendasButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tiendasButton.setPreferredSize(new java.awt.Dimension(150, 200));
        tiendasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tiendasButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tiendasButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tiendasButtonMouseExited(evt);
            }
        });
        Datos.add(tiendasButton);

        amigosButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amigosButton.setPreferredSize(new java.awt.Dimension(150, 200));
        amigosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amigosButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                amigosButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                amigosButtonMouseExited(evt);
            }
        });
        Datos.add(amigosButton);

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
                    .addComponent(Dragger, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)))
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
            .addComponent(Base, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE)
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
        if (evt.getButton() == 1)
            System.exit(0);
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
        if (evt.getButton() == 1)
            this.setExtendedState(JFrame.ICONIFIED);
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
            DiscosButton.setBackground(new Color(20, 255, 236));
            DiscosButton.setForeground(new Color(50, 50, 50));
            CardLayout cl = (CardLayout) Info.getLayout();
            cl.show(Info, "discosCard");
        }
    }//GEN-LAST:event_DiscosButtonMouseClicked

    private void DatosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosButtonMouseClicked
        if (evt.getButton() == 1) {
            resetBotones();
            DatosButton.setBackground(new Color(20, 255, 236));
            DatosButton.setForeground(new Color(50, 50, 50));
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
                frame = new AddDiscoFrame(this);
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
                labelNota.setText("");
                bd.eliminarDisco(tuplaFilaDisco.get(Lista.convertRowIndexToModel(Lista.getSelectedRow())));
                consultarDiscos();
            }

        }
    }//GEN-LAST:event_removeDiscoButtonMouseClicked

    private void removeDiscoButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeDiscoButtonMouseEntered
        removeDiscoButton.setIcon(rmvDiscoImg2);
    }//GEN-LAST:event_removeDiscoButtonMouseEntered

    private void removeDiscoButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeDiscoButtonMouseExited
        removeDiscoButton.setIcon(rmvDiscoImg);
    }//GEN-LAST:event_removeDiscoButtonMouseExited

    private void busquedaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busquedaFieldActionPerformed

    private void busquedaFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_busquedaFieldPropertyChange


    }//GEN-LAST:event_busquedaFieldPropertyChange

    private void autoresButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autoresButtonMouseClicked
        if (evt.getButton() == 1) {
            new AutoresFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_autoresButtonMouseClicked

    private void autoresButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autoresButtonMouseEntered
        autoresButton.setIcon(autoresSel);
    }//GEN-LAST:event_autoresButtonMouseEntered

    private void autoresButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autoresButtonMouseExited
        autoresButton.setIcon(autores);
    }//GEN-LAST:event_autoresButtonMouseExited

    private void categoriasButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriasButtonMouseEntered
        categoriasButton.setIcon(categoriasSel);
    }//GEN-LAST:event_categoriasButtonMouseEntered

    private void categoriasButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriasButtonMouseExited
        categoriasButton.setIcon(categorias);
    }//GEN-LAST:event_categoriasButtonMouseExited

    private void categoriasButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriasButtonMouseClicked
        if (evt.getButton() == 1) {
            new CategoriasFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_categoriasButtonMouseClicked

    private void ubicacionesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubicacionesButtonMouseEntered
        ubicacionesButton.setIcon(ubicacionesSel);
    }//GEN-LAST:event_ubicacionesButtonMouseEntered

    private void ubicacionesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubicacionesButtonMouseExited
        ubicacionesButton.setIcon(ubicaciones);
    }//GEN-LAST:event_ubicacionesButtonMouseExited

    private void ubicacionesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubicacionesButtonMouseClicked
        if (evt.getButton() == 1) {
            new UbicacionesFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_ubicacionesButtonMouseClicked

    private void discoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discoButtonMouseClicked
        if (evt.getButton() == 1) {
            new DiscograficasFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_discoButtonMouseClicked

    private void discoButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discoButtonMouseEntered
        discoButton.setIcon(discograficasSel);
    }//GEN-LAST:event_discoButtonMouseEntered

    private void discoButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discoButtonMouseExited
        discoButton.setIcon(discograficas);
    }//GEN-LAST:event_discoButtonMouseExited

    private void tiendasButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiendasButtonMouseClicked
        if (evt.getButton() == 1) {
            new TiendasFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_tiendasButtonMouseClicked

    private void tiendasButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiendasButtonMouseExited
        tiendasButton.setIcon(tiendas);
    }//GEN-LAST:event_tiendasButtonMouseExited

    private void tiendasButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiendasButtonMouseEntered
        tiendasButton.setIcon(tiendasSel);
    }//GEN-LAST:event_tiendasButtonMouseEntered

    private void formatosButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formatosButtonMouseEntered
        formatosButton.setIcon(formatosSel);
    }//GEN-LAST:event_formatosButtonMouseEntered

    private void formatosButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formatosButtonMouseExited
        formatosButton.setIcon(formatos);
    }//GEN-LAST:event_formatosButtonMouseExited

    private void generosButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generosButtonMouseEntered
        generosButton.setIcon(generosSel);
    }//GEN-LAST:event_generosButtonMouseEntered

    private void generosButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generosButtonMouseExited
        generosButton.setIcon(generos);
    }//GEN-LAST:event_generosButtonMouseExited

    private void amigosButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amigosButtonMouseEntered
        amigosButton.setIcon(amigosSel);
    }//GEN-LAST:event_amigosButtonMouseEntered

    private void amigosButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amigosButtonMouseExited
        amigosButton.setIcon(amigos);
    }//GEN-LAST:event_amigosButtonMouseExited

    private void generosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generosButtonMouseClicked
        if (evt.getButton() == 1) {
            new GeneroFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_generosButtonMouseClicked

    private void amigosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amigosButtonMouseClicked
        if (evt.getButton() == 1) {
            new AmigosFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_amigosButtonMouseClicked

    private void formatosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formatosButtonMouseClicked

        if (evt.getButton() == 1) {
            new SoporteFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_formatosButtonMouseClicked

    private void editarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarButtonMouseClicked
        if (evt.getButton() == 1 && Lista.getSelectedRow() != -1) {
            new ModDiscoFrame(this, tuplaFilaDisco.get(Lista.convertRowIndexToModel(Lista.getSelectedRow()))).setVisible(true);
        }
    }//GEN-LAST:event_editarButtonMouseClicked

    private void ListaDeseosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaDeseosButtonMouseClicked
        
        TableRowSorter d = (TableRowSorter) Lista.getRowSorter();
        d.setRowFilter(null);
        if (listaDeseosBool == false)
        {
        DescripcionLabel.setText("Mostrando la lista de Deseos");
        TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
        rs.setRowFilter(FiltroListaDeseos);
        listaDeseosBool = true;
        coleccionBool = false;
        favoritosBool = false;
        } else
        {
            DescripcionLabel.setText("Mostrando todos los discos");
            TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
            rs.setRowFilter(null);
            listaDeseosBool = false;
        }
        
        
        


    }//GEN-LAST:event_ListaDeseosButtonMouseClicked

    private void ColeccionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ColeccionButtonMouseClicked
        // TODO add your handling code here:
        TableRowSorter d = (TableRowSorter) Lista.getRowSorter();
        d.setRowFilter(null);
        
        if (coleccionBool == false)
        {
        DescripcionLabel.setText("Mostrando la Colección");
        TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
        rs.setRowFilter(FiltroColeccion);
        coleccionBool = true;
        listaDeseosBool = false;
        favoritosBool = false;
        } else
        {
            DescripcionLabel.setText("Mostrando todos los discos");
            TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
            rs.setRowFilter(null);
            coleccionBool = false;
        }
    }//GEN-LAST:event_ColeccionButtonMouseClicked

    private void FavoritosButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FavoritosButtonMouseClicked
        // TODO add your handling code here:
        TableRowSorter d = (TableRowSorter) Lista.getRowSorter();
        d.setRowFilter(null);
        
        if (favoritosBool == false)
        {
        DescripcionLabel.setText("Mostrando favoritos");
        TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
        rs.setRowFilter(FiltroFavoritos);
        coleccionBool = false;
        listaDeseosBool = false;
        favoritosBool = true;
        } else
        {
            DescripcionLabel.setText("Mostrando todos los discos");
            TableRowSorter rs = (TableRowSorter) Lista.getRowSorter();
            rs.setRowFilter(null);
            favoritosBool = false;
        }
    }//GEN-LAST:event_FavoritosButtonMouseClicked

    public void mensajeDiscoCreado() {
        labelTitulo.setText("Disco creado.");
        labelDatos.setText("");
        labelNota.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Activador;
    private javax.swing.JPanel Base;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JLabel ColeccionButton;
    private javax.swing.JPanel Datos;
    private javax.swing.JLabel DatosButton;
    private javax.swing.JLabel DescripcionLabel;
    private javax.swing.JPanel Discos;
    private javax.swing.JLabel DiscosButton;
    private javax.swing.JPanel Dragger;
    private javax.swing.JLabel FavoritosButton;
    private javax.swing.JPanel Info;
    private javax.swing.JTable Lista;
    private javax.swing.JLabel ListaDeseosButton;
    private javax.swing.JLabel Maximizar;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JScrollPane PanelLista;
    private javax.swing.JLabel addDiscoButton;
    private javax.swing.JLabel amigosButton;
    private javax.swing.JLabel autoresButton;
    private javax.swing.JTextField busquedaField;
    private javax.swing.JLabel busquedaLabel;
    private javax.swing.JLabel categoriasButton;
    private javax.swing.JLabel discoButton;
    private javax.swing.JLabel editarButton;
    private javax.swing.JLabel formatosButton;
    private javax.swing.JLabel generosButton;
    private javax.swing.JLabel labelDatos;
    private javax.swing.JLabel labelNota;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel portadaDisco;
    private javax.swing.JLabel removeDiscoButton;
    private javax.swing.JLabel tiendasButton;
    private javax.swing.JLabel ubicacionesButton;
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
