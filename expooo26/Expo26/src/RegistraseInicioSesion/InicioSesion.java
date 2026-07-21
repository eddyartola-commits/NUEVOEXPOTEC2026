package RegistraseInicioSesion;

import GUI.Menu;
import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JFrame {

    private int mouseX;
    private int mouseY;

// Las dimensiones exactas que tiene tu jPanel1 en el diseño de NetBeans
    private final int ANCHO_BASE = 1330;
    private final int ALTO_BASE = 590;
    private java.awt.Rectangle botonBase;
    private java.awt.Rectangle fondoBase;

    public InicioSesion() {
        // 1. PRIMERÍSIMA LÍNEA (Antes de cualquier otra cosa): Desactivar los bordes nativos
        this.setUndecorated(true);

        // 2. SEGUNDA LÍNEA: Inicializar los componentes del diseñador
        initComponents();
        txtUsuario.addActionListener(e -> btnIniciarSesion.doClick());
        txtContrasena.addActionListener(e -> btnIniciarSesion.doClick());
        this.setLocationRelativeTo(null);

        java.awt.Color colorGrisPlaceholder = new java.awt.Color(153, 153, 153);
        javax.swing.border.MatteBorder lineaInferior = new javax.swing.border.MatteBorder(0, 0, 2, 0, java.awt.Color.WHITE);

        txtUsuario.setOpaque(false);
        txtUsuario.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtUsuario.setBorder(lineaInferior);
        txtUsuario.setForeground(colorGrisPlaceholder);
        txtUsuario.setCaretColor(java.awt.Color.WHITE);

        txtContrasena.setOpaque(false);
        txtContrasena.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtContrasena.setBorder(lineaInferior);
        txtContrasena.setForeground(colorGrisPlaceholder);
        txtContrasena.setCaretColor(java.awt.Color.WHITE);
        txtContrasena.setEchoChar('*');

        jCheckBox1.setOpaque(false);
        jCheckBox1.setForeground(java.awt.Color.WHITE);

        // Guardar los límites iniciales
        botonBase = btnIniciarSesion.getBounds();
        fondoBase = labelEscalable1.getBounds();

        // Forzar el primer dibujado manual corregido sin el fondo gris
        escalarComponentesLogin();

        // =========================================================================
        // LISTENERS PARA ARRASTRAR LA VENTANA (jPanel2)
        // =========================================================================
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });

        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                if (getExtendedState() != javax.swing.JFrame.MAXIMIZED_BOTH) {
                    int x = evt.getXOnScreen();
                    int y = evt.getYOnScreen();
                    setLocation(x - mouseX, y - mouseY);
                }
            }
        });

    }

    // LÓGICA DE CLIC PARA EL USUARIO
    private void txtUsuarioFocusGained() {
        if (txtUsuario.getText().equals("usuario")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(java.awt.Color.WHITE);
        }
    }

    private void txtUsuarioFocusLost() {
        if (txtUsuario.getText().trim().isEmpty()) {
            txtUsuario.setText("usuario");
            txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        }
    }

    // LÓGICA DE CLIC PARA LA CONTRASEÑA
    private void txtContrasenaFocusGained() {
        String passStr = new String(txtContrasena.getPassword());
        if (passStr.equals("  Ingrese su Contraseña")) {
            txtContrasena.setText("");
            txtContrasena.setForeground(java.awt.Color.WHITE);
            txtContrasena.setEchoChar('*'); // Activa el ocultamiento por asteriscos automáticamente
        }
    }

    private void txtContrasenaFocusLost() {
        String passStr = new String(txtContrasena.getPassword());
        if (passStr.trim().isEmpty() || passStr.equals("Contraseña")) {
            txtContrasena.setEchoChar((char) 0);
            txtContrasena.setText("Contraseña");
            txtContrasena.setForeground(new java.awt.Color(153, 153, 153));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Candado = new Labels.LabelEscalable();
        Usuario = new Labels.LabelEscalable();
        textoDifuminadoRegistro1 = new Componentes.TextoDifuminadoRegistro();
        btnIniciarSesion = new Componentes.Boton();
        jCheckBox1 = new javax.swing.JCheckBox();
        txtContrasena = new javax.swing.JPasswordField();
        btnRegresar = new javax.swing.JButton();
        jLabel1Usuario1 = new javax.swing.JLabel();
        jLabel1Usuario3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        labelEscalable1 = new Labels.LabelEscalable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(45, 7, 107));
        jPanel2.setLayout(null);

        jButton1.setText("-");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(830, 10, 80, 24);

        jButton2.setText("x");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(920, 10, 80, 24);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 40));

        Candado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bloquear.png"))); // NOI18N
        jPanel1.add(Candado, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 290, 50, 40));

        Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario.png"))); // NOI18N
        jPanel1.add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, 50, 40));

        textoDifuminadoRegistro1.setText("Iniciar Sesión");
        textoDifuminadoRegistro1.setFont(new java.awt.Font("Horizon", 1, 36)); // NOI18N
        jPanel1.add(textoDifuminadoRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 230, 50));

        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 260, 50));

        jCheckBox1.setFont(new java.awt.Font("Questrial", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Mostrar Contraseña");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 340, 190, 30));

        txtContrasena.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContrasenaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContrasenaFocusLost(evt);
            }
        });
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 330, 30));

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Regresar.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 40));

        jLabel1Usuario1.setFont(new java.awt.Font("Questrial", 0, 36)); // NOI18N
        jLabel1Usuario1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1Usuario1.setText("Contraseña");
        jPanel1.add(jLabel1Usuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 230, 40));

        jLabel1Usuario3.setFont(new java.awt.Font("Questrial", 0, 36)); // NOI18N
        jLabel1Usuario3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1Usuario3.setText("Usuario");
        jPanel1.add(jLabel1Usuario3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 140, 40));

        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 330, 30));

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoLogin.png"))); // NOI18N
        labelEscalable1.setMaximumSize(new java.awt.Dimension(35, 35));
        labelEscalable1.setMinimumSize(new java.awt.Dimension(35, 35));
        labelEscalable1.setPreferredSize(new java.awt.Dimension(35, 35));
        jPanel1.add(labelEscalable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1030, 570));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        // Validar que no se envíen los placeholders vacíos
        if (usuario.isEmpty() || usuario.equalsIgnoreCase("Usuario")
                || contrasena.isEmpty() || contrasena.equalsIgnoreCase("Contraseña")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Por favor complete todos los campos.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        java.sql.Connection con = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;

        // CORRECCIÓN 1: Se agregó 'id_login' a la consulta SQL
        String sql = "SELECT id_login, Rol, Nombre FROM Login WHERE Nombre = ? AND Contrasena = ?";
        try {
            con = Conexion.Conexion.conectar();

            if (con != null) {
                pst = con.prepareStatement(sql);
                pst.setString(1, usuario);
                pst.setString(2, contrasena);

                rs = pst.executeQuery();

                if (rs.next()) {
                    // CORRECCIÓN 2: Extraer el id_login real de la base de datos
                    int idLogin = rs.getInt("id_login");
                    String rolUsuario = rs.getString("Rol");
                    String nombreUsuario = rs.getString("Nombre");

                    // CORRECCIÓN 3: Pasar el ID obtenido al constructor del Menú
                    // NOTA: Asegúrate de que tu clase Menu tenga el constructor configurado para recibirlo: public Menu(int idUsuarioLogueado)
                    Menu menu = new Menu(idLogin);

                    menu.aplicarPermisos(rolUsuario);
                    menu.setVisible(true);

                    if (rolUsuario.equalsIgnoreCase("Administrador")) {
                        javax.swing.JOptionPane.showMessageDialog(this, "¡Bienvenido Administrador: " + nombreUsuario + "!");
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "¡Bienvenido Usuario: " + nombreUsuario + "!");
                    }

                    menu.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                    this.dispose();

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "El usuario o la contraseña son incorrectos.", "Error de Autenticación", javax.swing.JOptionPane.ERROR_MESSAGE);
                    txtContrasena.setText("Contraseña");
                    txtContrasena.setEchoChar((char) 0);
                    txtContrasena.setForeground(new java.awt.Color(153, 153, 153));
                    txtUsuario.requestFocus();
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo establecer conexión con la base de datos.", "Error de Conexión", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (java.sql.SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        Registrase nuevo = new Registrase();
        nuevo.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setState(javax.swing.JFrame.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0); // Cierra todo el programa
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        String passStr = new String(txtContrasena.getPassword());
        boolean esPlaceholder = passStr.equalsIgnoreCase("Contraseña") || passStr.toLowerCase().contains("ingrese su contrase");

        if (jCheckBox1.isSelected()) {
            if (!esPlaceholder) {
                txtContrasena.setEchoChar((char) 0);
            }

            // Cambiar al candado ABIERTO
            try {
                java.net.URL urlAbierto = getClass().getResource("/Iconos/Abierto.png");
                if (urlAbierto == null) {
                    urlAbierto = getClass().getResource("/Iconos/abierto.png"); // Variante minúscula
                }
                if (urlAbierto != null && Candado != null) {
                    javax.swing.ImageIcon iconoOriginal = new javax.swing.ImageIcon(urlAbierto);
                    java.awt.Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                            Candado.getWidth(),
                            Candado.getHeight(),
                            java.awt.Image.SCALE_SMOOTH
                    );
                    Candado.setIcon(new javax.swing.ImageIcon(imagenEscalada));
                }
            } catch (Exception e) {
                System.out.println("Error al cargar candado abierto: " + e.getMessage());
            }

        } else {
            if (!esPlaceholder) {
                txtContrasena.setEchoChar('*');
            }

            // Cambiar al candado CERRADO (Probando múltiples nombres para asegurar el tiro)
            try {
                java.net.URL urlBloqueo = getClass().getResource("/Iconos/bloquear.png");
                if (urlBloqueo == null) {
                    urlBloqueo = getClass().getResource("/Iconos/bloquear.png"); // Con mayúscula
                }
                if (urlBloqueo == null) {
                    urlBloqueo = getClass().getResource("/Iconos/bloquear.png"); // Nombre alternativo
                }
                if (urlBloqueo == null) {
                    urlBloqueo = getClass().getResource("/Iconos/bloquear.png"); // Nombre alternativo 2
                }
                if (urlBloqueo != null && Candado != null) {
                    javax.swing.ImageIcon iconoOriginal = new javax.swing.ImageIcon(urlBloqueo);
                    java.awt.Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                            Candado.getWidth(),
                            Candado.getHeight(),
                            java.awt.Image.SCALE_SMOOTH
                    );
                    Candado.setIcon(new javax.swing.ImageIcon(imagenEscalada));
                } else {
                    System.out.println("No se encontró ninguna imagen para el candado cerrado en /Iconos/");
                }
            } catch (Exception e) {
                System.out.println("Error al cargar candado cerrado: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        String textoActual = txtUsuario.getText().trim();
        // Validamos sin importar si tiene espacios o mayúsculas
        if (textoActual.equalsIgnoreCase("Usuario") || textoActual.equalsIgnoreCase("Ingrese su nombre de usuario")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(java.awt.Color.WHITE); // O el color de tu letra al escribir
        }
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        // Al salir del campo: Si lo dejaste vacío, vuelve a poner el texto fantasma
        if (txtUsuario.getText().trim().isEmpty()) {
            txtUsuario.setText("Usuario");
            txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtContrasenaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaFocusGained
        String passStr = new String(txtContrasena.getPassword()).trim();

        // Si el campo tiene el texto guía, lo limpiamos y activamos los asteriscos
        if (passStr.equalsIgnoreCase("Contraseña") || passStr.equalsIgnoreCase("Contrasena")) {
            txtContrasena.setText("");
            txtContrasena.setEchoChar('*'); // 👁️ Aquí se activan los asteriscos al escribir
            txtContrasena.setForeground(java.awt.Color.WHITE);
        }
    }//GEN-LAST:event_txtContrasenaFocusGained

    private void txtContrasenaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaFocusLost

        String passStr = new String(txtContrasena.getPassword()).trim();

        // Si el usuario no escribió nada y se salió, vuelve a poner el texto guía en gris
        if (passStr.isEmpty() || passStr.equalsIgnoreCase("Contraseña") || passStr.equalsIgnoreCase("Contrasena")) {
            txtContrasena.setText("Contraseña");
            txtContrasena.setEchoChar((char) 0); // 👁️ Quitamos los asteriscos para que se lea la palabra
            txtContrasena.setForeground(new java.awt.Color(153, 153, 153)); // Color gris
        }
        // Quitamos la solicitud automática de foco para que no se cicle el cursor
    }//GEN-LAST:event_txtContrasenaFocusLost

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    private void escalarComponentesLogin() {
        int anchoVentana = this.getWidth();
        int altoVentana = this.getHeight();
        int altoBarra = 40; // Alto de la barra morada superior

        // 1. Forzar al contenedor principal (jPanel1) a ocupar todo el JFrame
        if (jPanel1 != null) {
            jPanel1.setBounds(0, 0, anchoVentana, altoVentana);
        }

        // 2. Ajustar la barra superior morada (jPanel2) y sus botones de cierre
        if (jPanel2 != null) {
            jPanel2.setBounds(0, 0, anchoVentana, altoBarra);
        }
        if (jButton1 != null) { // Botón '-'
            jButton1.setBounds(anchoVentana - 105, 5, 45, 30);
        }
        if (jButton2 != null) { // Botón 'x'
            jButton2.setBounds(anchoVentana - 55, 5, 45, 30);
        }
        if (btnRegresar != null) {
            btnRegresar.setBounds(15, altoBarra + 15, 50, 50);
        }

        // 3. CALCULAR DESPLAZAMIENTO (Ajusta el bloque de login hacia la derecha en pantallas grandes)
        // Usamos el ancho base con el que lo diseñaste en NetBeans (ej: 1330)
        int desplazamientoX = anchoVentana - 1330;
        int centradoY = (altoVentana - 590) / 2;

        if (desplazamientoX < 0) {
            desplazamientoX = 0;
        }
        if (centradoY < 0) {
            centradoY = 0;
        }

        // Mover bloque de login en conjunto (Sumándole el desplazamientoX para empujarlo a la derecha)
        // NOTA: Verifica que estos nombres coincidan EXACTAMENTE con las variables de tus JTextFields/JLabels
        if (txtUsuario != null) {
            txtUsuario.setLocation(700 + desplazamientoX, 200 + centradoY);
        }
        if (txtContrasena != null) {
            txtContrasena.setLocation(700 + desplazamientoX, 270 + centradoY);
        }
        if (jCheckBox1 != null) {
            jCheckBox1.setLocation(700 + desplazamientoX, 330 + centradoY);
        }
        if (btnIniciarSesion != null) {
            btnIniciarSesion.setLocation(700 + desplazamientoX, 380 + centradoY);
        }

        // Iconos de usuario y candado (si los tienes)
        if (jLabel1Usuario1 != null) {
            jLabel1Usuario1.setLocation(640 + desplazamientoX, 200 + centradoY);
        }
        if (jLabel1Usuario3 != null) {
            jLabel1Usuario3.setLocation(640 + desplazamientoX, 270 + centradoY);
        }

        // 4. SOLUCIÓN AL FONDO GRIS: Estirar el JLabel y forzar escala de la imagen
        if (labelEscalable1 != null) {
            // Obligamos al JLabel a ocupar todo el espacio restante abajo de la barra morada
            labelEscalable1.setBounds(0, altoBarra, anchoVentana, altoVentana - altoBarra);

            // Guardar respaldo de la imagen original la primera vez
            if (labelEscalable1.getClientProperty("fotoOriginal") == null && labelEscalable1.getIcon() instanceof javax.swing.ImageIcon) {
                java.awt.Image img = ((javax.swing.ImageIcon) labelEscalable1.getIcon()).getImage();
                labelEscalable1.putClientProperty("fotoOriginal", img);
            }

            java.awt.Image fotoBase = (java.awt.Image) labelEscalable1.getClientProperty("fotoOriginal");

            if (fotoBase != null) {
                // Forzar el redimensionado liso para rellenar el espacio vacío
                java.awt.Image imagenEscalada = fotoBase.getScaledInstance(
                        anchoVentana,
                        altoVentana - altoBarra,
                        java.awt.Image.SCALE_SMOOTH
                );
                labelEscalable1.setIcon(new javax.swing.ImageIcon(imagenEscalada));
            }
        }

        // 5. Garantizar orden de capas
        if (jPanel1 != null) {
            if (jPanel2 != null) {
                jPanel1.setComponentZOrder(jPanel2, 0);
            }
            if (labelEscalable1 != null) {
                jPanel1.setComponentZOrder(labelEscalable1, jPanel1.getComponentCount() - 1);
            }
        }

        // 6. Refrescar el sistema de dibujo de Java
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Candado;
    private javax.swing.JLabel Usuario;
    private Componentes.Boton btnIniciarSesion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1Usuario1;
    private javax.swing.JLabel jLabel1Usuario3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private Labels.LabelEscalable labelEscalable1;
    private Componentes.TextoDifuminadoRegistro textoDifuminadoRegistro1;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
