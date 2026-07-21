package RegistraseInicioSesion;
import java.sql.ResultSet;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import GUI.Menu;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class Registrase extends javax.swing.JFrame {

    public Registrase() {
        setUndecorated(true);

        initComponents();
        txtUsuario.addActionListener(e -> btnRegistrar.doClick());
        txtEmail.addActionListener(e -> btnRegistrar.doClick());
        txtContrasena.addActionListener(e -> btnRegistrar.doClick());
        this.setLocationRelativeTo(null);

        // Modificamos propiedades directamente, SIN volver a instanciar (sin 'new')
        lblPregunta.setText("Ya tienes una cuenta? ");
        lblPregunta.setFont(new java.awt.Font("Questrial", java.awt.Font.PLAIN, 21));
        lblPregunta.setForeground(java.awt.Color.WHITE);

        lblIrALogin.setText("Login");
        lblIrALogin.setFont(new java.awt.Font("Questrial", java.awt.Font.BOLD, 22));
        lblIrALogin.setForeground(new java.awt.Color(166, 75, 255)); // Morado base inicial

        // Eventos interactivos del Label Login (Efecto Hover)
        lblIrALogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InicioSesion ventana = new InicioSesion();
                ventana.setVisible(true);
                Registrase.this.dispose();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                // Morado más oscuro al hacer clic presionado
                lblIrALogin.setForeground(new java.awt.Color(119, 39, 228));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                // Restaura al morado del hover al soltar el clic
                lblIrALogin.setForeground(new java.awt.Color(200, 150, 255));
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // EFECTO HOVER MORADO: Agranda la letra y cambia el color
                lblIrALogin.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 17));
                lblIrALogin.setForeground(new java.awt.Color(200, 150, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaura tamaño y color original al sacar el mouse
                lblIrALogin.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 20));
                lblIrALogin.setForeground(new java.awt.Color(166, 75, 255));
            }
        });

        // 5. CONFIGURACIÓN DE LOS CAMPOS TRANSPARENTES
        java.awt.Color colorGrisPlaceholder = new java.awt.Color(153, 153, 153);
        javax.swing.border.MatteBorder lineaInferior = new javax.swing.border.MatteBorder(0, 0, 2, 0, java.awt.Color.WHITE);

        txtUsuario.setOpaque(false);
        txtUsuario.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtUsuario.setBorder(lineaInferior);
        txtUsuario.setForeground(colorGrisPlaceholder);
        txtUsuario.setCaretColor(java.awt.Color.WHITE);

        txtEmail.setOpaque(false);
        txtEmail.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtEmail.setBorder(lineaInferior);
        txtEmail.setForeground(colorGrisPlaceholder);
        txtEmail.setCaretColor(java.awt.Color.WHITE);

        txtContrasena.setOpaque(false);
        txtContrasena.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtContrasena.setBorder(lineaInferior);
        txtContrasena.setForeground(colorGrisPlaceholder);
        txtContrasena.setCaretColor(java.awt.Color.WHITE);

        chkMostrar.setOpaque(false);
        chkMostrar.setForeground(java.awt.Color.WHITE);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chkMostrar = new javax.swing.JCheckBox();
        textoDifuminadoRegistro1 = new Componentes.TextoDifuminadoRegistro();
        txtContrasena = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblPregunta = new javax.swing.JLabel();
        lblIrALogin = new javax.swing.JLabel();
        btnRegistrar = new Componentes.Boton();
        labelEscalable1 = new Labels.LabelEscalable();
        labelEscalable3 = new Labels.LabelEscalable();
        labelEscalable5 = new Labels.LabelEscalable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        labelEscalable4 = new Labels.LabelEscalable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel1.setLayout(null);

        chkMostrar.setFont(new java.awt.Font("Questrial", 0, 14)); // NOI18N
        chkMostrar.setForeground(new java.awt.Color(255, 255, 255));
        chkMostrar.setText("Mostrar Contraseña");
        chkMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMostrarActionPerformed(evt);
            }
        });
        jPanel1.add(chkMostrar);
        chkMostrar.setBounds(570, 380, 170, 20);

        textoDifuminadoRegistro1.setText("Registrarse");
        textoDifuminadoRegistro1.setFont(new java.awt.Font("Horizon", 1, 36)); // NOI18N
        jPanel1.add(textoDifuminadoRegistro1);
        textoDifuminadoRegistro1.setBounds(600, 90, 310, 29);

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
        jPanel1.add(txtContrasena);
        txtContrasena.setBounds(570, 340, 330, 30);

        txtEmail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail);
        txtEmail.setBounds(570, 260, 330, 30);

        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtUsuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsuario.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsuario);
        txtUsuario.setBounds(570, 180, 330, 30);

        jLabel2.setFont(new java.awt.Font("Questrial", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(570, 140, 160, 38);

        jLabel3.setFont(new java.awt.Font("Questrial", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(570, 220, 110, 38);

        jLabel4.setFont(new java.awt.Font("Questrial", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(570, 300, 190, 38);

        lblPregunta.setFont(new java.awt.Font("Questrial", 0, 21)); // NOI18N
        lblPregunta.setForeground(new java.awt.Color(255, 255, 255));
        lblPregunta.setText("Ya tienes una cuenta? ");
        lblPregunta.setToolTipText("");
        jPanel1.add(lblPregunta);
        lblPregunta.setBounds(610, 470, 220, 20);

        lblIrALogin.setFont(new java.awt.Font("Questrial", 1, 21)); // NOI18N
        lblIrALogin.setForeground(new java.awt.Color(255, 255, 255));
        lblIrALogin.setText("Login");
        jPanel1.add(lblIrALogin);
        lblIrALogin.setBounds(830, 465, 60, 30);

        btnRegistrar.setText("Registrarse");
        btnRegistrar.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar);
        btnRegistrar.setBounds(640, 410, 240, 40);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario.png"))); // NOI18N
        jPanel1.add(labelEscalable1);
        labelEscalable1.setBounds(910, 170, 50, 40);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/EmailRegistrarse.png"))); // NOI18N
        jPanel1.add(labelEscalable3);
        labelEscalable3.setBounds(910, 250, 50, 40);

        labelEscalable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bloquear.png"))); // NOI18N
        jPanel1.add(labelEscalable5);
        labelEscalable5.setBounds(910, 330, 50, 40);

        jPanel2.setBackground(new java.awt.Color(45, 7, 107));
        jPanel2.setLayout(null);

        jButton1.setText("-");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(840, 10, 70, 27);

        jButton2.setText("x");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(920, 10, 80, 27);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1030, 40);

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoLogin.png"))); // NOI18N
        jPanel1.add(labelEscalable4);
        labelEscalable4.setBounds(0, 40, 1030, 570);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        String usuario = txtUsuario.getText().trim();
        String correo = txtEmail.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        // Validar campos vacíos
        if (usuario.isEmpty() || usuario.equalsIgnoreCase("Usuario")
                || correo.isEmpty() || correo.equalsIgnoreCase("Email")
                || contrasena.isEmpty() || contrasena.equalsIgnoreCase("Contraseña")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos correctamente.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Connection con = null;
        PreparedStatement psVerificar = null;
        PreparedStatement psInsertar = null;
        ResultSet rs = null;

        try {
            con = Conexion.conectar();
            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo conectar con la base de datos."
                );
                return;
            }
            // 1. Verificar si el usuario ya existe
            String verificar = "SELECT COUNT(*) FROM Login WHERE Nombre = ?";

            psVerificar = con.prepareStatement(verificar);
            psVerificar.setString(1, usuario);

            rs = psVerificar.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ese usuario ya existe.",
                        "Usuario duplicado",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            // 2. Insertar nuevo usuario
            String sql = "INSERT INTO Login(Rol, Nombre, Correo, Contrasena) VALUES (?, ?, ?, ?)";

            psInsertar = con.prepareStatement(
                    sql,
                    java.sql.Statement.RETURN_GENERATED_KEYS
            );
            psInsertar.setString(1, "Usuario");
            psInsertar.setString(2, usuario);
            psInsertar.setString(3, correo);
            psInsertar.setString(4, contrasena);

            // ESTE ERA EL PASO QUE FALTABA
            int filas = psInsertar.executeUpdate();
            if (filas > 0) {
                int idGenerado = 0;
                ResultSet claves = psInsertar.getGeneratedKeys();
                if (claves.next()) {
                    idGenerado = claves.getInt(1);
                }
                JOptionPane.showMessageDialog(
                        this,
                        "Usuario registrado correctamente."
                );
                // Abrir menú
                Menu menu = new Menu(idGenerado);
                menu.aplicarPermisos("Usuario");
                menu.setExtendedState(
                        javax.swing.JFrame.MAXIMIZED_BOTH
                );
                menu.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el usuario."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar: " + e.getMessage()
            );

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (psVerificar != null) {
                    psVerificar.close();
                }

                if (psInsertar != null) {
                    psInsertar.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {

                System.out.println(
                        "Error cerrando conexión: " + e.getMessage()
                );
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void chkMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMostrarActionPerformed
        String passStr = new String(txtContrasena.getPassword());
        boolean esPlaceholder = passStr.equalsIgnoreCase("Contrasena");

        if (chkMostrar.isSelected()) {
            if (!esPlaceholder) {
                txtContrasena.setEchoChar((char) 0);
            }
            cambiarIconoCandado("/Iconos/Abierto.png", "/Iconos/abierto.png");
        } else {
            if (!esPlaceholder) {
                txtContrasena.setEchoChar('*');
            }
            cambiarIconoCandado("/Iconos/bloquear.png", "/Iconos/Bloquear.png");
        }
    }//GEN-LAST:event_chkMostrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0); // Cierra todo el programa
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setState(javax.swing.JFrame.ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        if (txtUsuario.getText().equalsIgnoreCase("Usuario")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(java.awt.Color.WHITE);
        }
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if (txtUsuario.getText().trim().isEmpty()) {
            txtUsuario.setText("Usuario");
            txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtContrasenaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaFocusGained
        String passStr = new String(txtContrasena.getPassword()).trim();
        if (passStr.equalsIgnoreCase("Contrasena")) {
            txtContrasena.setText("");
            txtContrasena.setEchoChar('*');
            txtContrasena.setForeground(java.awt.Color.WHITE);
        }
    }//GEN-LAST:event_txtContrasenaFocusGained

    private void txtContrasenaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaFocusLost
        String passStr = new String(txtContrasena.getPassword()).trim();
        if (passStr.isEmpty() || passStr.equals("contrasena")) {
            txtContrasena.setEchoChar((char) 0);
            txtContrasena.setText("Contrasena");
            txtContrasena.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtContrasenaFocusLost

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        if (txtEmail.getText().equalsIgnoreCase("Email")) {
            txtEmail.setText("");
            txtEmail.setForeground(java.awt.Color.WHITE);
        }
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        if (txtEmail.getText().trim().isEmpty()) {
            txtEmail.setText("Email");
            txtEmail.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrase().setVisible(true);
            }
        });
    }

    private void cambiarIconoCandado(String ruta1, String ruta2) {
        try {
            java.net.URL url = getClass().getResource(ruta1);
            if (url == null) {
                url = getClass().getResource(ruta2);
            }
            if (url != null && labelEscalable5 != null) {
                javax.swing.ImageIcon original = new javax.swing.ImageIcon(url);
                java.awt.Image escalada = original.getImage().getScaledInstance(labelEscalable5.getWidth(), labelEscalable5.getHeight(), java.awt.Image.SCALE_SMOOTH);
                labelEscalable5.setIcon(new javax.swing.ImageIcon(escalada));
            }
        } catch (Exception e) {
            System.out.println("Error candado: " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Boton btnRegistrar;
    private javax.swing.JCheckBox chkMostrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Labels.LabelEscalable labelEscalable5;
    private javax.swing.JLabel lblIrALogin;
    private javax.swing.JLabel lblPregunta;
    private Componentes.TextoDifuminadoRegistro textoDifuminadoRegistro1;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
