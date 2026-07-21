package GUI;

import GUI_USUARIO.Comidas;
import GUI_USUARIO.Historial;
import GUI_USUARIO.PruebaImpacto;
import GUI_USUARIO.Ranking;
import GUI_USUARIO.RutinasUsuario;
import javax.swing.*;

public class Menu extends javax.swing.JFrame {

    private int idUsuarioLogueado;

    public static boolean datosIngresados = false;
    public static double pesoUsuarioActual = 0.0;
    public static double punteoUsuarioActual = 0.0;

    public Menu(int idUsuarioLogueado) {
        this.idUsuarioLogueado = idUsuarioLogueado;
        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMaximizedBounds(
                java.awt.GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds()
        );

        // Listener para reescalar la barra superior
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                escalarComponentes();
            }
        });
    }

// 2. EL MÉTODO (Abajo del constructor)
    private void escalarComponentes() {
        int anchoVentana = this.getWidth();
        int altoBarra = 40;
        if (jPanel2 != null) {
            jPanel2.setBounds(0, 0, anchoVentana, altoBarra);
        }
        if (jButton2 != null) {
            jButton2.setBounds(anchoVentana - 55, 5, 45, 30);
        }

        if (jButton1 != null) {
            jButton1.setBounds(anchoVentana - 105, 5, 45, 30);
        }
        if (jPanel2 != null) {
            this.getContentPane().setComponentZOrder(jPanel2, 0);
        }
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void aplicarPermisos(String rol) {

        if (rol.equalsIgnoreCase("Usuario")) {

            btnPantallaAdmi.setVisible(false);
            btnGestionarUsuarios.setVisible(false);
            btnEstadisticas.setVisible(false);
            btnMantenimiento.setVisible(false);
            btnComidas.setVisible(false);
            btnRutinas.setVisible(false);

            btnGestionarUsuarios.setPreferredSize(new java.awt.Dimension(0, 0));
            btnEstadisticas.setPreferredSize(new java.awt.Dimension(0, 0));
            btnMantenimiento.setPreferredSize(new java.awt.Dimension(0, 0));
            btnComidas.setPreferredSize(new java.awt.Dimension(0, 0));
            btnRutinas.setPreferredSize(new java.awt.Dimension(0, 0));

            // 🌟 CORRECCIÓN AQUÍ: Instanciar y meter el panel dentro de vistaContenido7
            pnlMenu pnlMenu = new pnlMenu();
            cambiarPantalla(pnlMenu);

        } else {
            // ... Tu bloque else para Administrador se queda exactamente igual ...
            btnPantallaP.setVisible(false);
            btnPruebaImpacto.setVisible(false);
            btnHistorial.setVisible(false);
            btnRutinasUsuario.setVisible(false);
            btnComidasUsuario.setVisible(false);
            Ranking.setVisible(false);

            btnPantallaP.setPreferredSize(new java.awt.Dimension(0, 0));
            btnPruebaImpacto.setPreferredSize(new java.awt.Dimension(0, 0));
            btnHistorial.setPreferredSize(new java.awt.Dimension(0, 0));
            btnRutinasUsuario.setPreferredSize(new java.awt.Dimension(0, 0));
            btnComidasUsuario.setPreferredSize(new java.awt.Dimension(0, 0));
            Ranking.setPreferredSize(new java.awt.Dimension(0, 0));

            menuAdmi pnlMenu = new menuAdmi();
            cambiarPantalla(pnlMenu);
        }

        jPanel2.revalidate();
        jPanel2.repaint();
    }

    public Menu(String rol) {

        this(-1);

        aplicarPermisos(rol);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fIT1 = new Componentes.FIT();
        fIT2 = new Componentes.FIT();
        fIT3 = new Componentes.FIT();
        fIT4 = new Componentes.FIT();
        fIT5 = new Componentes.FIT();
        fIT6 = new Componentes.FIT();
        jPanel2 = new javax.swing.JPanel();
        btnPantallaP = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelEscalable1 = new Labels.LabelEscalable();
        btnGestionarUsuarios = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelEscalable2 = new Labels.LabelEscalable();
        btnMantenimiento = new javax.swing.JPanel();
        labelEscalable4 = new Labels.LabelEscalable();
        jLabel9 = new javax.swing.JLabel();
        btnComidas = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        labelEscalable7 = new Labels.LabelEscalable();
        btnRutinas = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        labelEscalable6 = new Labels.LabelEscalable();
        btnEstadisticas = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        labelEscalable3 = new Labels.LabelEscalable();
        sTRIKE1 = new Componentes.STRIKE();
        pOWER1 = new Componentes.POWER();
        fIT7 = new Componentes.FIT();
        btnPruebaImpacto = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        labelEscalable8 = new Labels.LabelEscalable();
        btnHistorial = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        labelEscalable9 = new Labels.LabelEscalable();
        jLabel20 = new javax.swing.JLabel();
        btnComidasUsuario = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        labelEscalable11 = new Labels.LabelEscalable();
        btnRutinasUsuario = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        labelEscalable10 = new Labels.LabelEscalable();
        btnPantallaAdmi = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        labelEscalable5 = new Labels.LabelEscalable();
        Ranking = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        labelEscalable12 = new Labels.LabelEscalable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        vistaContenido7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 0, 51));
        setMinimumSize(new java.awt.Dimension(250, 600));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(19, 3, 27));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(41, 0, 83), null, null));

        btnPantallaP.setOpaque(false);
        btnPantallaP.setPreferredSize(new java.awt.Dimension(325, 85));
        btnPantallaP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPantallaPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPantallaPMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPantallaPMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(97, 255, 202));
        jLabel6.setText("PRINCIPAL");

        jLabel7.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(97, 255, 202));
        jLabel7.setText("PANTALLA");

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/hogar.png"))); // NOI18N

        javax.swing.GroupLayout btnPantallaPLayout = new javax.swing.GroupLayout(btnPantallaP);
        btnPantallaP.setLayout(btnPantallaPLayout);
        btnPantallaPLayout.setHorizontalGroup(
            btnPantallaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPantallaPLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(labelEscalable1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(btnPantallaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(68, 68, 68))
        );
        btnPantallaPLayout.setVerticalGroup(
            btnPantallaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPantallaPLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(btnPantallaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEscalable1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnPantallaPLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnGestionarUsuarios.setOpaque(false);
        btnGestionarUsuarios.setPreferredSize(new java.awt.Dimension(325, 85));
        btnGestionarUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGestionarUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGestionarUsuariosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGestionarUsuariosMousePressed(evt);
            }
        });
        btnGestionarUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(97, 255, 202));
        jLabel8.setText("GESTIONAR");
        btnGestionarUsuarios.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 170, -1));

        jLabel12.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(97, 255, 202));
        jLabel12.setText("USUARIOS");
        btnGestionarUsuarios.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 140, -1));

        labelEscalable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar-usuario (2).png"))); // NOI18N
        btnGestionarUsuarios.add(labelEscalable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 52, 46));

        btnMantenimiento.setOpaque(false);
        btnMantenimiento.setPreferredSize(new java.awt.Dimension(325, 85));
        btnMantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMantenimientoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMantenimientoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMantenimientoMousePressed(evt);
            }
        });

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/atencion-al-cliente.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Horizon", 3, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(97, 255, 202));
        jLabel9.setText("MANTENIMIENto");

        javax.swing.GroupLayout btnMantenimientoLayout = new javax.swing.GroupLayout(btnMantenimiento);
        btnMantenimiento.setLayout(btnMantenimientoLayout);
        btnMantenimientoLayout.setHorizontalGroup(
            btnMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMantenimientoLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(labelEscalable4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        btnMantenimientoLayout.setVerticalGroup(
            btnMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMantenimientoLayout.createSequentialGroup()
                .addGroup(btnMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnMantenimientoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelEscalable4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnMantenimientoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel9)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnComidas.setOpaque(false);
        btnComidas.setPreferredSize(new java.awt.Dimension(325, 85));
        btnComidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComidasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComidasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnComidasMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Horizon", 3, 19)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(97, 255, 202));
        jLabel5.setText("Comidas");

        labelEscalable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/comida.png"))); // NOI18N

        javax.swing.GroupLayout btnComidasLayout = new javax.swing.GroupLayout(btnComidas);
        btnComidas.setLayout(btnComidasLayout);
        btnComidasLayout.setHorizontalGroup(
            btnComidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnComidasLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(labelEscalable7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnComidasLayout.setVerticalGroup(
            btnComidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnComidasLayout.createSequentialGroup()
                .addGroup(btnComidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnComidasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelEscalable7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnComidasLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnRutinas.setOpaque(false);
        btnRutinas.setPreferredSize(new java.awt.Dimension(325, 85));
        btnRutinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRutinasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRutinasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRutinasMousePressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Horizon", 3, 19)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(97, 255, 202));
        jLabel11.setText("Rutinas");

        labelEscalable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rutina.png"))); // NOI18N

        javax.swing.GroupLayout btnRutinasLayout = new javax.swing.GroupLayout(btnRutinas);
        btnRutinas.setLayout(btnRutinasLayout);
        btnRutinasLayout.setHorizontalGroup(
            btnRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRutinasLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(labelEscalable6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        btnRutinasLayout.setVerticalGroup(
            btnRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRutinasLayout.createSequentialGroup()
                .addGroup(btnRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnRutinasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelEscalable6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnRutinasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnEstadisticas.setOpaque(false);
        btnEstadisticas.setPreferredSize(new java.awt.Dimension(325, 85));
        btnEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEstadisticasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEstadisticasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEstadisticasMousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Horizon", 3, 19)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(97, 255, 202));
        jLabel10.setText("ESTADISTICAS");

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/grafico-de-barras.png"))); // NOI18N

        javax.swing.GroupLayout btnEstadisticasLayout = new javax.swing.GroupLayout(btnEstadisticas);
        btnEstadisticas.setLayout(btnEstadisticasLayout);
        btnEstadisticasLayout.setHorizontalGroup(
            btnEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEstadisticasLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(labelEscalable3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        btnEstadisticasLayout.setVerticalGroup(
            btnEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEstadisticasLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(btnEstadisticasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelEscalable3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sTRIKE1.setFont(new java.awt.Font("Fc Faster", 2, 45)); // NOI18N

        pOWER1.setFont(new java.awt.Font("Fc Faster", 2, 40)); // NOI18N

        fIT7.setFont(new java.awt.Font("Fc Faster", 2, 40)); // NOI18N

        btnPruebaImpacto.setOpaque(false);
        btnPruebaImpacto.setPreferredSize(new java.awt.Dimension(325, 85));
        btnPruebaImpacto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPruebaImpactoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPruebaImpactoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPruebaImpactoMousePressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Horizon", 3, 19)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(97, 255, 202));
        jLabel13.setText("jugar");

        labelEscalable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/prueba.png"))); // NOI18N

        javax.swing.GroupLayout btnPruebaImpactoLayout = new javax.swing.GroupLayout(btnPruebaImpacto);
        btnPruebaImpacto.setLayout(btnPruebaImpactoLayout);
        btnPruebaImpactoLayout.setHorizontalGroup(
            btnPruebaImpactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPruebaImpactoLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(labelEscalable8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnPruebaImpactoLayout.setVerticalGroup(
            btnPruebaImpactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPruebaImpactoLayout.createSequentialGroup()
                .addGroup(btnPruebaImpactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnPruebaImpactoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelEscalable8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnPruebaImpactoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel13)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnHistorial.setOpaque(false);
        btnHistorial.setPreferredSize(new java.awt.Dimension(325, 85));
        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistorialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistorialMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHistorialMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(97, 255, 202));
        jLabel15.setText("personal");

        labelEscalable9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/historial.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(97, 255, 202));
        jLabel20.setText("historial");

        javax.swing.GroupLayout btnHistorialLayout = new javax.swing.GroupLayout(btnHistorial);
        btnHistorial.setLayout(btnHistorialLayout);
        btnHistorialLayout.setHorizontalGroup(
            btnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHistorialLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(labelEscalable9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(btnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnHistorialLayout.setVerticalGroup(
            btnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHistorialLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(btnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHistorialLayout.createSequentialGroup()
                        .addComponent(labelEscalable9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHistorialLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(17, 17, 17))))
        );

        btnComidasUsuario.setOpaque(false);
        btnComidasUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComidasUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComidasUsuarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnComidasUsuarioMousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(97, 255, 202));
        jLabel17.setText("COMIDAS");

        labelEscalable11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/comida.png"))); // NOI18N

        javax.swing.GroupLayout btnComidasUsuarioLayout = new javax.swing.GroupLayout(btnComidasUsuario);
        btnComidasUsuario.setLayout(btnComidasUsuarioLayout);
        btnComidasUsuarioLayout.setHorizontalGroup(
            btnComidasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnComidasUsuarioLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(labelEscalable11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnComidasUsuarioLayout.setVerticalGroup(
            btnComidasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnComidasUsuarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelEscalable11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnComidasUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(37, 37, 37))
        );

        btnRutinasUsuario.setOpaque(false);
        btnRutinasUsuario.setPreferredSize(new java.awt.Dimension(325, 85));
        btnRutinasUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRutinasUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRutinasUsuarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRutinasUsuarioMousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(97, 255, 202));
        jLabel16.setText("Rutinas");

        labelEscalable10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rutina.png"))); // NOI18N

        javax.swing.GroupLayout btnRutinasUsuarioLayout = new javax.swing.GroupLayout(btnRutinasUsuario);
        btnRutinasUsuario.setLayout(btnRutinasUsuarioLayout);
        btnRutinasUsuarioLayout.setHorizontalGroup(
            btnRutinasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRutinasUsuarioLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(labelEscalable10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnRutinasUsuarioLayout.setVerticalGroup(
            btnRutinasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRutinasUsuarioLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(labelEscalable10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(btnRutinasUsuarioLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPantallaAdmi.setOpaque(false);
        btnPantallaAdmi.setPreferredSize(new java.awt.Dimension(325, 85));
        btnPantallaAdmi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPantallaAdmiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPantallaAdmiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPantallaAdmiMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(97, 255, 202));
        jLabel14.setText("PRINCIPAL");

        jLabel18.setFont(new java.awt.Font("Horizon", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(97, 255, 202));
        jLabel18.setText("PANTALLA");

        labelEscalable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/hogar.png"))); // NOI18N

        javax.swing.GroupLayout btnPantallaAdmiLayout = new javax.swing.GroupLayout(btnPantallaAdmi);
        btnPantallaAdmi.setLayout(btnPantallaAdmiLayout);
        btnPantallaAdmiLayout.setHorizontalGroup(
            btnPantallaAdmiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPantallaAdmiLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(labelEscalable5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(btnPantallaAdmiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
        );
        btnPantallaAdmiLayout.setVerticalGroup(
            btnPantallaAdmiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPantallaAdmiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(btnPantallaAdmiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEscalable5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnPantallaAdmiLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        Ranking.setOpaque(false);
        Ranking.setPreferredSize(new java.awt.Dimension(357, 97));
        Ranking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RankingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RankingMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RankingMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Horizon", 3, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(97, 255, 202));
        jLabel19.setText("RANKING GLOBAL");

        labelEscalable12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clasificacion.png"))); // NOI18N

        javax.swing.GroupLayout RankingLayout = new javax.swing.GroupLayout(Ranking);
        Ranking.setLayout(RankingLayout);
        RankingLayout.setHorizontalGroup(
            RankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RankingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelEscalable12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel19)
                .addGap(35, 35, 35))
        );
        RankingLayout.setVerticalGroup(
            RankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RankingLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(RankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RankingLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RankingLayout.createSequentialGroup()
                        .addComponent(labelEscalable12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPantallaP, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(btnMantenimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(pOWER1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fIT7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addComponent(btnEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(btnRutinas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(btnComidas, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(btnPruebaImpacto, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(sTRIKE1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(btnGestionarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRutinasUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(btnComidasUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPantallaAdmi, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addComponent(Ranking, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pOWER1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fIT7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sTRIKE1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnPantallaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPantallaAdmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGestionarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRutinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPruebaImpacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRutinasUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComidasUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ranking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(45, 7, 107));
        jPanel1.setForeground(new java.awt.Color(114, 38, 226));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("-");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1750, 10, 70, -1));

        jButton2.setText("x");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1830, 10, 70, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        vistaContenido7.setBackground(new java.awt.Color(19, 3, 27));
        vistaContenido7.setForeground(new java.awt.Color(255, 255, 255));
        vistaContenido7.setLayout(null);
        jScrollPane1.setViewportView(vistaContenido7);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPantallaPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPantallaPMouseEntered

    }//GEN-LAST:event_btnPantallaPMouseEntered

    private void btnPantallaPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPantallaPMouseExited
    }//GEN-LAST:event_btnPantallaPMouseExited

    private void btnPantallaPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPantallaPMousePressed
        resetearColoresBotones();
        btnPantallaP.setBackground(new java.awt.Color(91, 71, 153));
        btnPantallaP.setOpaque(true);
        cambiarPantalla(new pnlMenu());
    }//GEN-LAST:event_btnPantallaPMousePressed

    private void btnEstadisticasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstadisticasMouseEntered

    }//GEN-LAST:event_btnEstadisticasMouseEntered

    private void btnEstadisticasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstadisticasMouseExited

    }//GEN-LAST:event_btnEstadisticasMouseExited

    private void btnEstadisticasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstadisticasMousePressed
        resetearColoresBotones();
        btnEstadisticas.setBackground(new java.awt.Color(91, 71, 153));
        btnEstadisticas.setOpaque(true);
        cambiarPantalla(new pnlEstadisticas());

        GUI.pnlEstadisticas panelEst = new GUI.pnlEstadisticas();

        // 2. Creamos el ScrollPane inyectando tu panel adentro
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(panelEst);

        // Configuración de barras dinámicas
        scroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Estética Neón (Sin bordes grises)
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        // 3. Limpiamos tu contenedor del menú (reemplaza 'panelPrincipal' por el tuyo)
        vistaContenido7.removeAll();

        // 4. Agregamos el SCROLL (que ya lleva tu diseño dentro) al contenedor en pantalla completa
        vistaContenido7.add(scroll, java.awt.BorderLayout.CENTER);

        // 5. Refrescamos la interfaz gráfica
        vistaContenido7.revalidate();
        vistaContenido7.repaint();
    }//GEN-LAST:event_btnEstadisticasMousePressed

    private void btnGestionarUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionarUsuariosMouseEntered


    }//GEN-LAST:event_btnGestionarUsuariosMouseEntered

    private void btnGestionarUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionarUsuariosMouseExited

    }//GEN-LAST:event_btnGestionarUsuariosMouseExited

    private void btnGestionarUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionarUsuariosMousePressed

        resetearColoresBotones();
        btnGestionarUsuarios.setBackground(new java.awt.Color(91, 71, 153));
        btnGestionarUsuarios.setOpaque(true);
        cambiarPantalla(new pnlGestionarUsuarios());
    }//GEN-LAST:event_btnGestionarUsuariosMousePressed

    private void btnRutinasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRutinasMouseEntered


    }//GEN-LAST:event_btnRutinasMouseEntered

    private void btnRutinasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRutinasMouseExited

    }//GEN-LAST:event_btnRutinasMouseExited

    private void btnRutinasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRutinasMousePressed
        resetearColoresBotones();
        btnRutinas.setBackground(new java.awt.Color(91, 71, 153));
        btnRutinas.setOpaque(true);
        cambiarPantalla(new pnlGestionRutinas());
    }//GEN-LAST:event_btnRutinasMousePressed

    private void btnMantenimientoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoMouseEntered


    }//GEN-LAST:event_btnMantenimientoMouseEntered

    private void btnMantenimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoMouseExited

    }//GEN-LAST:event_btnMantenimientoMouseExited

    private void btnMantenimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoMousePressed

        resetearColoresBotones();
        btnMantenimiento.setBackground(new java.awt.Color(91, 71, 153));
        btnMantenimiento.setOpaque(true);
        cambiarPantalla(new Mante());
    }//GEN-LAST:event_btnMantenimientoMousePressed

    private void btnComidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComidasMouseEntered

    }//GEN-LAST:event_btnComidasMouseEntered

    private void btnComidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComidasMouseExited

    }//GEN-LAST:event_btnComidasMouseExited

    private void btnComidasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComidasMousePressed

        resetearColoresBotones();
        btnComidas.setBackground(new java.awt.Color(91, 71, 153));
        btnComidas.setOpaque(true);
        cambiarPantalla(new pnlComidas());
    }//GEN-LAST:event_btnComidasMousePressed

    private void btnPruebaImpactoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPruebaImpactoMouseEntered


    }//GEN-LAST:event_btnPruebaImpactoMouseEntered

    private void btnPruebaImpactoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPruebaImpactoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPruebaImpactoMouseExited

    private void btnPruebaImpactoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPruebaImpactoMousePressed
        resetearColoresBotones();
        btnPruebaImpacto.setBackground(new java.awt.Color(91, 71, 153));
        btnPruebaImpacto.setOpaque(true);
        cambiarPantalla(new PruebaImpacto(idUsuarioLogueado));


    }//GEN-LAST:event_btnPruebaImpactoMousePressed

    private void btnHistorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialMouseEntered

    private void btnHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialMouseExited

    private void btnHistorialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMousePressed

        // TODO add your handling code he
        // Evaluamos si el usuario ya guardó datos de forma exitosa en el panel central
        if (datosIngresados) {
            resetearColoresBotones();
            btnHistorial.setBackground(new java.awt.Color(91, 71, 153));
            btnHistorial.setOpaque(true);
            // SI YA INGRESÓ DATOS: Concedemos el acceso al Historial
            cambiarPantalla(new Historial(idUsuarioLogueado));
        } else {
            // SI NO HA INGRESADO DATOS: Bloqueamos la acción y avisamos en pantalla
            JOptionPane.showMessageDialog(
                    this,
                    "¡Acceso Denegado!\nPara ver esta sección, primero debes rellenar tus datos en el apartado 'PRUEBA' y presionar 'GUARDAR DATOS'.",
                    "Requisito Obligatorio",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnHistorialMousePressed

    private void btnRutinasUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRutinasUsuarioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRutinasUsuarioMouseEntered

    private void btnRutinasUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRutinasUsuarioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRutinasUsuarioMouseExited

    private void btnRutinasUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRutinasUsuarioMousePressed
        if (GUI.Menu.datosIngresados) {
            resetearColoresBotones();
            btnRutinasUsuario.setBackground(new java.awt.Color(91, 71, 153));
            btnRutinasUsuario.setOpaque(true);
            resetearColoresBotones();
            cambiarPantalla(new RutinasUsuario());
            // 1. LLAMADA AL CONSTRUCTOR: Crea la pantalla de rutinas (ocultando los paneles por defecto)
            RutinasUsuario pantallaRutinas = new RutinasUsuario();
            btnRutinasUsuario.setBackground(new java.awt.Color(91, 71, 153));
            btnRutinasUsuario.setOpaque(true);
            // 2. ACTUALIZACIÓN: Inyectamos el puntaje del golpe para encender la rutina correcta
            pantallaRutinas.actualizarInterfazRutina(GUI.Menu.punteoUsuarioActual);

            // 3. CAMBIO DE PANTALLA: Mostramos las rutinas al usuario
            cambiarPantalla(pantallaRutinas);
        } else {
            // SI NO HA INGRESADO DATOS: Bloqueamos la acción y avisamos en pantalla
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "¡Acceso Denegado!\nPara ver tus rutinas, primero debes rellenar tus datos en el apartado 'PRUEBA' y presionar 'GUARDAR DATOS'.",
                    "Requisito Obligatorio",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnRutinasUsuarioMousePressed

    private void btnComidasUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComidasUsuarioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComidasUsuarioMouseEntered

    private void btnComidasUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComidasUsuarioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComidasUsuarioMouseExited

    private void btnComidasUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComidasUsuarioMousePressed
        // TODO add your handling code here:
        resetearColoresBotones();
        btnComidasUsuario.setBackground(new java.awt.Color(91, 71, 153));
        btnComidasUsuario.setOpaque(true);
        cambiarPantalla(new Comidas());

        // Evaluamos si el usuario ya guardó datos de forma exitosa en el panel central
        if (GUI.Menu.datosIngresados) {
            resetearColoresBotones();
            btnComidasUsuario.setBackground(new java.awt.Color(91, 71, 153));
            btnComidasUsuario.setOpaque(true);

            // 1. LLAMADA AL CONSTRUCTOR: Crea la pantalla (ocultando los paneles por defecto)
            Comidas pantallaComidas = new Comidas();

            // 2. ACTUALIZACIÓN: Inyectamos el peso para encender el panel correcto
            pantallaComidas.actualizarInterfazComida(GUI.Menu.pesoUsuarioActual);

            // 3. CAMBIO DE PANTALLA: Concedemos el acceso visual
            cambiarPantalla(pantallaComidas);
        } else {
            // SI NO HA INGRESADO DATOS: Bloqueamos la acción y avisamos en pantalla
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "¡Acceso Denegado!\nPara ver tus comidas, primero debes rellenar tus datos en el apartado 'PRUEBA' y presionar 'GUARDAR DATOS'.",
                    "Requisito Obligatorio",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnComidasUsuarioMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setState(javax.swing.JFrame.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0); // Cierra todo el programa
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPantallaAdmiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPantallaAdmiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPantallaAdmiMouseEntered

    private void btnPantallaAdmiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPantallaAdmiMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPantallaAdmiMouseExited

    private void btnPantallaAdmiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPantallaAdmiMousePressed
        // TODO add your handling code here:
        resetearColoresBotones();
        btnPantallaAdmi.setBackground(new java.awt.Color(91, 71, 153));
        btnPantallaAdmi.setOpaque(true);
        cambiarPantalla(new menuAdmi());
    }//GEN-LAST:event_btnPantallaAdmiMousePressed

    private void RankingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RankingMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_RankingMouseEntered

    private void RankingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RankingMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_RankingMouseExited

    private void RankingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RankingMousePressed
        // TODO add your handling code here:
        resetearColoresBotones();
        Ranking.setBackground(new java.awt.Color(91, 71, 153));
        Ranking.setOpaque(true);
        cambiarPantalla(new Ranking());
    }//GEN-LAST:event_RankingMousePressed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(0).setVisible(true);
            }
        });

    }

    private void cambiarPantalla(javax.swing.JPanel nuevaPantalla) {
        jScrollPane1.setViewportView(nuevaPantalla);
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }

    private void resetearColoresBotones() {
        // El color oscuro original de tu menú (ajusta el código RGB si es necesario)
        java.awt.Color colorOscuro = new java.awt.Color(19, 3, 27);

        // Ponemos TODOS los botones en el color oscuro original
        btnPantallaP.setBackground(colorOscuro);
        btnEstadisticas.setBackground(colorOscuro);
        btnGestionarUsuarios.setBackground(colorOscuro);
        btnMantenimiento.setBackground(colorOscuro);
        btnRutinas.setBackground(colorOscuro);
        btnComidas.setBackground(colorOscuro);
        btnPruebaImpacto.setBackground(colorOscuro);
        btnHistorial.setBackground(colorOscuro);
        btnComidasUsuario.setBackground(colorOscuro);
        btnRutinasUsuario.setBackground(colorOscuro);
        btnPantallaAdmi.setBackground(colorOscuro);
        Ranking.setBackground(colorOscuro);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Ranking;
    private javax.swing.JPanel btnComidas;
    private javax.swing.JPanel btnComidasUsuario;
    private javax.swing.JPanel btnEstadisticas;
    private javax.swing.JPanel btnGestionarUsuarios;
    private javax.swing.JPanel btnHistorial;
    private javax.swing.JPanel btnMantenimiento;
    private javax.swing.JPanel btnPantallaAdmi;
    private javax.swing.JPanel btnPantallaP;
    private javax.swing.JPanel btnPruebaImpacto;
    private javax.swing.JPanel btnRutinas;
    private javax.swing.JPanel btnRutinasUsuario;
    private Componentes.FIT fIT1;
    private Componentes.FIT fIT2;
    private Componentes.FIT fIT3;
    private Componentes.FIT fIT4;
    private Componentes.FIT fIT5;
    private Componentes.FIT fIT6;
    private Componentes.FIT fIT7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable10;
    private Labels.LabelEscalable labelEscalable11;
    private Labels.LabelEscalable labelEscalable12;
    private Labels.LabelEscalable labelEscalable2;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Labels.LabelEscalable labelEscalable5;
    private Labels.LabelEscalable labelEscalable6;
    private Labels.LabelEscalable labelEscalable7;
    private Labels.LabelEscalable labelEscalable8;
    private Labels.LabelEscalable labelEscalable9;
    private Componentes.POWER pOWER1;
    private Componentes.STRIKE sTRIKE1;
    private javax.swing.JPanel vistaContenido7;
    // End of variables declaration//GEN-END:variables
}
