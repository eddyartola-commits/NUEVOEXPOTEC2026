package GUI;

import CrudGestionUsuario.Usuario;
import CrudGestionUsuario.UsuarioDAO;
import RegistraseInicioSesion.Registrase;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class pnlGestionarUsuarios extends javax.swing.JPanel {
    
    UsuarioDAO dao = new UsuarioDAO();
    Usuario u = new Usuario();
    javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel();

    public void listarUsuarios() {
        java.util.List<Usuario> lista = dao.listar();
        
        // 🔹 CORRECCIÓN 1: Acceso correcto a la JTable interna
        modeloTabla = (javax.swing.table.DefaultTableModel) tblUsuarios.getTabla().getModel();
        modeloTabla.setRowCount(0); 
        
        Object[] fila = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getId_login();
            fila[1] = lista.get(i).getRol();
            fila[2] = lista.get(i).getNombre();
            fila[3] = lista.get(i).getCorreo();
            fila[4] = lista.get(i).getContrasena();
            modeloTabla.addRow(fila);
        }
        // 🔹 CORRECCIÓN 2: Asignación del modelo a la JTable interna
        tblUsuarios.getTabla().setModel(modeloTabla);
    }

 
    public pnlGestionarUsuarios() {
        initComponents();
        
          setLayout(null);

// Panel oculto (Configurado con layout nulo para permitir la animación interna del botón)
JPanel panelOpciones = new JPanel();
panelOpciones.setLayout(null); // 🔹 IMPORTANTE: Permite animar componentes por coordenadas
panelOpciones.setOpaque(false); 
panelOpciones.setVisible(false);
panelOpciones.setBounds(1215, 100, 230, 90); // 🔹 Ampliado un poco para dar margen al crecimiento

// Botón "Cerrar sesión"
JButton btnCerrarSesion = new JButton("CERRAR SESIÓN") {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, new Color(200, 0, 0),
                                             getWidth(), getHeight(), new Color(120, 0, 200));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
        g2.dispose();
        super.paintComponent(g);
    }
};

// 🔹 Dimensiones Base y de Zoom para Cerrar Sesión
final int btnAnchoBase = 210;
final int btnAltoBase = 55;
final int btnAnchoZoom = 225; // Crece 15px a lo ancho
final int btnAltoZoom = 65;  // Crece 10px a lo alto
final int btnPaso = 2;

// Posición inicial centrada dentro de panelOpciones
btnCerrarSesion.setBounds(10, 10, btnAnchoBase, btnAltoBase);
btnCerrarSesion.setFont(new Font("Horizon", Font.BOLD, 14));
btnCerrarSesion.setForeground(Color.WHITE);
btnCerrarSesion.setFocusPainted(false);
btnCerrarSesion.setContentAreaFilled(false);
btnCerrarSesion.setBorderPainted(false);
btnCerrarSesion.setOpaque(false);
btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));

// 🔹 Animación de Zoom para Cerrar Sesión (Cambia tamaño y color)
btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
    private Timer timerBtn;

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        btnCerrarSesion.setForeground(Color.ORANGE);
        
        if (timerBtn != null && timerBtn.isRunning()) {
            timerBtn.stop();
        }

        timerBtn = new Timer(10, ev -> {
            int w = btnCerrarSesion.getWidth();
            int h = btnCerrarSesion.getHeight();
            if (w < btnAnchoZoom || h < btnAltoZoom) {
                int nuevoW = Math.min(w + btnPaso, btnAnchoZoom);
                int nuevoH = Math.min(h + btnPaso, btnAltoZoom);
                int nuevoX = btnCerrarSesion.getX() - (nuevoW - w) / 2;
                int nuevoY = btnCerrarSesion.getY() - (nuevoH - h) / 2;
                btnCerrarSesion.setBounds(nuevoX, nuevoY, nuevoW, nuevoH);
            } else {
                timerBtn.stop();
            }
        });
        timerBtn.start();
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        btnCerrarSesion.setForeground(Color.WHITE);
        
        if (timerBtn != null && timerBtn.isRunning()) {
            timerBtn.stop();
        }

        timerBtn = new Timer(10, ev -> {
            int w = btnCerrarSesion.getWidth();
            int h = btnCerrarSesion.getHeight();
            if (w > btnAnchoBase || h > btnAltoBase) {
                int nuevoW = Math.max(w - btnPaso, btnAnchoBase);
                int nuevoH = Math.max(h - btnPaso, btnAltoBase);
                int nuevoX = btnCerrarSesion.getX() + (w - nuevoW) / 2;
                int nuevoY = btnCerrarSesion.getY() + (h - nuevoH) / 2;
                btnCerrarSesion.setBounds(nuevoX, nuevoY, nuevoW, nuevoH);
            } else {
                timerBtn.stop();
            }
        });
        timerBtn.start();
    }
});

 btnCerrarSesion.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "Redirigiendo a Registrarse");
         // Abrir nuevo frame
         Registrase login = new Registrase();
         login.setVisible(true);
        // Cerrar ventana actual
         Window window = SwingUtilities.getWindowAncestor(this);
         if (window != null) {
         window.dispose();
         };
});
panelOpciones.add(btnCerrarSesion);


// 🔹 Método local de alta calidad para redimensionar los íconos a 45x45 de forma nítida
java.util.function.BiFunction<String, Integer, ImageIcon> crearIconoCalidad = (ruta, tam) -> {
    java.net.URL url = getClass().getResource(ruta);
    if (url == null) return new ImageIcon();
    Image imgOrig = new ImageIcon(url).getImage();
    java.awt.image.BufferedImage imgRedim = new java.awt.image.BufferedImage(tam, tam, java.awt.image.BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = imgRedim.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.drawImage(imgOrig, 0, 0, tam, tam, null);
    g2.dispose();
    return new ImageIcon(imgRedim);
};

// Configurar los tamaños base y hover utilizando el renderizado limpio de la flecha
final int tamañoBase = 45;   
final int tamañoZoom = 55;   
final int paso = 2;

ImageIcon iconoAjustadoNormal = crearIconoCalidad.apply("/Iconos/flecha.png", tamañoBase);
ImageIcon iconoAjustadoHover  = crearIconoCalidad.apply("/Iconos/flecha.png", tamañoZoom);

// Botón con ícono de flecha
JButton jButton1 = new JButton(iconoAjustadoNormal);
jButton1.setBounds(1400, 35, tamañoBase, tamañoBase); 
jButton1.setBorderPainted(false);
jButton1.setContentAreaFilled(false);
jButton1.setFocusPainted(false);
jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));

// Acción del botón: mostrar/ocultar panel
jButton1.addActionListener(e -> {
    panelOpciones.setVisible(!panelOpciones.isVisible());
    revalidate();
    repaint();
});


// 🔹 Animación de Zoom de la flecha sin solapamiento de hilos
jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
    private Timer timerAnimacion;

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        jButton1.setIcon(iconoAjustadoHover);
        
        if (timerAnimacion != null && timerAnimacion.isRunning()) {
            timerAnimacion.stop();
        }

        timerAnimacion = new Timer(10, ev -> {
            int w = jButton1.getWidth();
            int h = jButton1.getHeight();
            if (w < tamañoZoom) {
                int nuevoW = Math.min(w + paso, tamañoZoom);
                int nuevoH = Math.min(h + paso, tamañoZoom);
                int nuevoX = jButton1.getX() - (nuevoW - w) / 2;
                int nuevoY = jButton1.getY() - (nuevoH - h) / 2;
                jButton1.setBounds(nuevoX, nuevoY, nuevoW, nuevoH);
            } else {
                timerAnimacion.stop();
            }
        });
        timerAnimacion.start();
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        jButton1.setIcon(iconoAjustadoNormal);
        
        if (timerAnimacion != null && timerAnimacion.isRunning()) {
            timerAnimacion.stop();
        }

        timerAnimacion = new Timer(10, ev -> {
            int w = jButton1.getWidth();
            int h = jButton1.getHeight();
            if (w > tamañoBase) {
                int nuevoW = Math.max(w - paso, tamañoBase);
                int nuevoH = Math.max(h - paso, tamañoBase);
                int nuevoX = jButton1.getX() + (w - nuevoW) / 2;
                int nuevoY = jButton1.getY() + (h - nuevoH) / 2;
                jButton1.setBounds(nuevoX, nuevoY, nuevoW, nuevoH);
            } else {
                timerAnimacion.stop();
            }
        });
        timerAnimacion.start();
    }
});

// Agregar al panel principal
add(jButton1);
    add(panelOpciones);
    String[] colsEstadisticas = {"ID USUARIO", "ROL", "NOMBRE", "CORREO", "CONTRASEÑA",};
    tblUsuarios.inicializarTabla(colsEstadisticas);
    
    // Solo define el tamaño completo real que deba medir tu diseño
    this.setPreferredSize(new java.awt.Dimension(1491, 1038));
    
    listarUsuarios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelEscalable1 = new Labels.LabelEscalable();
        iconoEstadistica2 = new Iconos.IconoEstadistica();
        labelEscalable10 = new Labels.LabelEscalable();
        labelEscalable9 = new Labels.LabelEscalable();
        labelEscalable14 = new Labels.LabelEscalable();
        labelEscalable13 = new Labels.LabelEscalable();
        labelEscalable11 = new Labels.LabelEscalable();
        labelEscalable8 = new Labels.LabelEscalable();
        jLabel2 = new javax.swing.JLabel();
        labelEscalable12 = new Labels.LabelEscalable();
        tblUsuarios = new Componentes.Tabla();
        btnCrear = new Componentes.Boton();
        btnEliminar = new Componentes.Boton();
        btnVer = new Componentes.Boton();
        btnModificar = new Componentes.Boton();
        correo = new Componentes.TexBox();
        rol = new Componentes.TexBox();
        nombreUsuario = new Componentes.TexBox();
        id_usuarios = new Componentes.TexBox();
        contraseña = new Componentes.TexBox();
        barra1 = new Componentes.Barra();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnLimpiar = new Componentes.Boton();

        setBackground(new java.awt.Color(16, 3, 27));
        setForeground(new java.awt.Color(19, 3, 27));
        setPreferredSize(new java.awt.Dimension(1155, 583));
        setLayout(null);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Questrial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Administrador");
        add(jLabel6);
        jLabel6.setBounds(1270, 18, 120, 40);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Questrial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bienvenido");
        add(jLabel5);
        jLabel5.setBounds(1310, 45, 80, 30);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        add(labelEscalable1);
        labelEscalable1.setBounds(1200, 21, 55, 53);

        iconoEstadistica2.setLayout(null);

        labelEscalable10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        iconoEstadistica2.add(labelEscalable10);
        labelEscalable10.setBounds(390, 610, 60, 50);

        labelEscalable9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Usuariooo.png"))); // NOI18N
        iconoEstadistica2.add(labelEscalable9);
        labelEscalable9.setBounds(10, 10, 70, 70);

        add(iconoEstadistica2);
        iconoEstadistica2.setBounds(50, 44, 90, 90);

        labelEscalable14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        add(labelEscalable14);
        labelEscalable14.setBounds(20, 610, 60, 50);

        labelEscalable13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/candado.png"))); // NOI18N
        add(labelEscalable13);
        labelEscalable13.setBounds(580, 770, 50, 50);

        labelEscalable11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/correo-electronico.png"))); // NOI18N
        add(labelEscalable11);
        labelEscalable11.setBounds(1140, 610, 50, 50);

        labelEscalable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        add(labelEscalable8);
        labelEscalable8.setBounds(390, 610, 60, 50);

        jLabel2.setFont(new java.awt.Font("Horizon", 3, 38)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("gestionar usuarios");
        add(jLabel2);
        jLabel2.setBounds(155, 75, 650, 44);

        labelEscalable12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        add(labelEscalable12);
        labelEscalable12.setBounds(760, 610, 60, 50);
        add(tblUsuarios);
        tblUsuarios.setBounds(20, 180, 1450, 340);

        btnCrear.setText("GUARDAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        add(btnCrear);
        btnCrear.setBounds(20, 910, 260, 60);

        btnEliminar.setText("eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar);
        btnEliminar.setBounds(310, 910, 260, 60);

        btnVer.setText("VER");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        add(btnVer);
        btnVer.setBounds(880, 910, 260, 60);

        btnModificar.setText("MODIFICAR");
        btnModificar.setToolTipText("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar);
        btnModificar.setBounds(600, 910, 260, 60);

        correo.setPlaceholderText("Correo");
        add(correo);
        correo.setBounds(1130, 600, 340, 70);

        rol.setBackground(new java.awt.Color(255, 255, 255));
        rol.setForeground(new java.awt.Color(255, 255, 255));
        rol.setPlaceholderText("ROL ");
        add(rol);
        rol.setBounds(390, 600, 340, 70);

        nombreUsuario.setPlaceholderText("Nombre Usuario");
        add(nombreUsuario);
        nombreUsuario.setBounds(760, 600, 340, 70);

        id_usuarios.setPlaceholderText("ID USUARIO");
        add(id_usuarios);
        id_usuarios.setBounds(20, 600, 340, 70);

        contraseña.setPlaceholderText("Contraseña");
        add(contraseña);
        contraseña.setBounds(570, 760, 340, 70);

        javax.swing.GroupLayout barra1Layout = new javax.swing.GroupLayout(barra1);
        barra1.setLayout(barra1Layout);
        barra1Layout.setHorizontalGroup(
            barra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        barra1Layout.setVerticalGroup(
            barra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        add(barra1);
        barra1.setBounds(40, 155, 780, 4);

        jLabel11.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ingrese contraseña");
        add(jLabel11);
        jLabel11.setBounds(620, 710, 260, 50);

        jLabel13.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ingrese correo");
        add(jLabel13);
        jLabel13.setBounds(1210, 550, 190, 50);

        jLabel14.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ingrese ID");
        add(jLabel14);
        jLabel14.setBounds(130, 550, 130, 50);

        jLabel15.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ingrese su usuario");
        add(jLabel15);
        jLabel15.setBounds(830, 550, 240, 50);

        jLabel16.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ingrese su rol");
        add(jLabel16);
        jLabel16.setBounds(480, 550, 180, 50);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar);
        btnLimpiar.setBounds(1180, 910, 260, 60);
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
// 1. Validar que el campo del ID no esté vacío
    if (id_usuarios.getText().trim().equals("")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione o busque primero un usuario para modificar.");
    } else {
        try {
            // 2. Validar que NINGUNO de los otros campos se quede en blanco
            if (rol.getText().trim().isEmpty() || 
                nombreUsuario.getText().trim().isEmpty() || 
                correo.getText().trim().isEmpty() || 
                contraseña.getText().trim().isEmpty()) {
                
                javax.swing.JOptionPane.showMessageDialog(
                    this, 
                    "No se puede modificar el registro dejando campos en blanco. Por favor, rellene todos los campos.", 
                    "Campos Vacíos", 
                    javax.swing.JOptionPane.WARNING_MESSAGE
                );
                return; // Detiene el proceso antes de cambiar nada en la BD
            }

            // 3. Obtener y limpiar el texto ingresado en el campo de rol
            String rolIngresado = rol.getText().trim();
            
            // 4. Validar que sea exactamente "Administrador" o "Usuario"
            if (!rolIngresado.equals("Administrador") && !rolIngresado.equals("Usuario")) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, 
                    "El rol ingresado no es válido.\nSolo se permite: 'Administrador' o 'Usuario' (respete mayúsculas y minúsculas).", 
                    "Error de Validación", 
                    javax.swing.JOptionPane.WARNING_MESSAGE
                );
                return; // Detiene la ejecución para que no intente guardar en la BD
            }

            // 5. Si pasa todas las validaciones, asignamos los datos actualizados al objeto 'u'
            u.setId_login(Integer.parseInt(id_usuarios.getText().trim()));
            u.setRol(rolIngresado);
            u.setNombre(nombreUsuario.getText().trim());
            u.setCorreo(correo.getText().trim());
            u.setContrasena(contraseña.getText().trim());

            // 6. Ejecutar actualización en el DAO y refrescar la tabla
            dao.actualizar(u);
            listarUsuarios();
            javax.swing.JOptionPane.showMessageDialog(this, "¡Usuario actualizado correctamente!");
            
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
// 1. Capturar los valores de todos los cuadros de texto y quitar espacios
    String txtId = id_usuarios.getText().trim();
    String txtRol = rol.getText().trim();
    String txtNombre = nombreUsuario.getText().trim();
    String txtCorreo = correo.getText().trim();
    String txtContrasena = contraseña.getText().trim();

    // Base de la consulta SQL dinámica
    StringBuilder sql = new StringBuilder("SELECT * FROM Login WHERE 1=1");
    java.util.List<Object> parametros = new java.util.ArrayList<>();

    // 2. Construir el filtro dinámico según lo que el usuario haya escrito
    if (!txtId.isEmpty()) {
        sql.append(" AND id_login = ?");
        try {
            parametros.add(Integer.parseInt(txtId));
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    if (!txtRol.isEmpty()) {
        sql.append(" AND Rol LIKE ?");
        parametros.add("%" + txtRol + "%");
    }
    if (!txtNombre.isEmpty()) {
        sql.append(" AND Nombre LIKE ?");
        parametros.add("%" + txtNombre + "%");
    }
    if (!txtCorreo.isEmpty()) {
        sql.append(" AND Correo LIKE ?");
        parametros.add("%" + txtCorreo + "%");
    }
    if (!txtContrasena.isEmpty()) {
        sql.append(" AND Contrasena LIKE ?");
        parametros.add("%" + txtContrasena + "%");
    }

    // Variables de control para pasar la info a los campos de texto
    int contadorRegistros = 0;
    String ultimoId = "", ultimoRol = "", ultimoNombre = "", ultimoCorreo = "", ultimaContrasena = "";

    // 3. Conectarse a la BD y ejecutar la búsqueda
    try (java.sql.Connection con = Conexion.Conexion.conectar(); 
         java.sql.PreparedStatement ps = con.prepareStatement(sql.toString())) {
        
        for (int i = 0; i < parametros.size(); i++) {
            ps.setObject(i + 1, parametros.get(i));
        }

        try (java.sql.ResultSet rs = ps.executeQuery()) {
            // 4. Configurar el modelo para pintar los resultados
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // No editable al hacer doble clic
                }
            };
            
            modelo.addColumn("ID");
            modelo.addColumn("Rol");
            modelo.addColumn("Nombre");
            modelo.addColumn("Correo");
            modelo.addColumn("Contrasena");

            while (rs.next()) {
                contadorRegistros++;
                
                // Extraemos los datos de la fila actual de la BD
                ultimoId = rs.getString("id_login");
                ultimoRol = rs.getString("Rol");
                ultimoNombre = rs.getString("Nombre");
                ultimoCorreo = rs.getString("Correo");
                ultimaContrasena = rs.getString("Contrasena");

                // Añadimos la fila a la tabla visual
                Object[] fila = new Object[5];
                fila[0] = ultimoId;
                fila[1] = ultimoRol;
                fila[2] = ultimoNombre;
                fila[3] = ultimoCorreo;
                fila[4] = ultimaContrasena;
                modelo.addRow(fila);
            }

            // 5. Asignar el modelo a la tabla real
            javax.swing.JTable tablaReal = tblUsuarios.getTabla();
            tablaReal.setModel(modelo); 

            // 6. RESTAURAR ESTÉTICA: Forzar el dibujado de líneas de cuadrícula
            tablaReal.setShowGrid(true);
            tablaReal.setShowHorizontalLines(true);
            tablaReal.setShowVerticalLines(true);
            tablaReal.setGridColor(new java.awt.Color(128, 0, 128)); // Color morado de tu diseño

            // 7. RESTAURAR ESTÉTICA: Centrar los textos en todas las columnas
            javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            for (int i = 0; i < tablaReal.getColumnCount(); i++) {
                tablaReal.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            // 8. ASIGNAR A TEXTBOXES Y MENSAJES INTELIGENTES
            if (contadorRegistros > 0) {
                // Solo llenamos los cuadros de texto si el usuario usó algún filtro de búsqueda
                if (!parametros.isEmpty()) {
                    id_usuarios.setText(ultimoId);
                    rol.setText(ultimoRol);
                    nombreUsuario.setText(ultimoNombre);
                    correo.setText(ultimoCorreo);
                    contraseña.setText(ultimaContrasena);
                }
                // Eliminamos por completo el JOptionPane de éxito que estaba aquí.
                
            } else {
                // Si de verdad se buscó algo y no se encontró nada, avisamos una sola vez
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron usuarios con los criterios ingresados.", "Sin Resultados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al realizar la búsqueda: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // 1. Verificar que el campo del ID (texBox2) no esté vacío
    if (id_usuarios.getText().trim().equals("")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del usuario en el campo de texto para poder eliminarlo.");
    } else {
        try {
            // 2. Obtener el ID directamente desde el texBox2
            int id = Integer.parseInt(id_usuarios.getText().trim());
            
            // 3. Ejecutar la eliminación en el DAO
            dao.eliminar(id);
            
            // 4. Actualizar la interfaz (recargar tabla y limpiar campos)
            listarUsuarios();
            id_usuarios.setText(""); // Limpia el campo del ID tras eliminar
            
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario con ID " + id + " eliminado correctamente.");
            
        } catch (NumberFormatException e) {
            // Se ejecuta si el usuario escribe letras o símbolos en lugar de números
            javax.swing.JOptionPane.showMessageDialog(this, "El ID ingresado debe ser un número entero válido.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Captura cualquier otro error (como fallos de conexión con la Base de Datos)
            javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error del Sistema", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed

       try {
    // 1. Validar campos requeridos
    if (rol.getText().trim().isEmpty() || 
        nombreUsuario.getText().trim().isEmpty() || 
        correo.getText().trim().isEmpty() || 
        contraseña.getText().trim().isEmpty()) {
        
        javax.swing.JOptionPane.showMessageDialog(
            this, 
            "Todos los campos son obligatorios. Por favor, rellene toda la información.", 
            "Campos Vacíos", 
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
        return; 
    }

    String rolIngresado = rol.getText().trim();
    
    // 2. Validar roles permitidos
    if (!rolIngresado.equals("Administrador") && !rolIngresado.equals("Usuario")) {
        javax.swing.JOptionPane.showMessageDialog(
            this, 
            "El rol ingresado no es válido.\nSolo se permite: 'Administrador' o 'Usuario'.", 
            "Error de Validación", 
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
        return; 
    }

    // 3. Mapear datos al objeto
    u.setRol(rolIngresado);
    u.setNombre(nombreUsuario.getText().trim());
    u.setCorreo(correo.getText().trim());
    u.setContrasena(contraseña.getText().trim());

    // 4. Guardar (Inyecta automáticamente el ID real generado en la base de datos a 'u')
    dao.agregar(u);
    listarUsuarios(); 
    
    javax.swing.JOptionPane.showMessageDialog(this, "¡Usuario registrado correctamente!");
    
    // =========================================================================
    // 🚀 NOTIFICACIÓN AUTOMÁTICA EN SEGUNDO PLANO
    // =========================================================================
    new Thread(() -> {
        try {
            // Instanciamos tu clase de MantenimientoDAO para usar el método que ya creaste
            Conexion.MantenimientoDAO mantenimientoDao = new Conexion.MantenimientoDAO();
            String correosAdmins = mantenimientoDao.obtenerCorreosAdministradores();

            // Si existen administradores en la lista, disparamos el correo
            if (correosAdmins != null && !correosAdmins.trim().isEmpty()) {
                
                String idRealGenerado = String.valueOf(u.getId_login()); // 🌟 Aquí va el ID numérico real de la BD

                Alerta.ServicioNotificaciones.enviarNotificacionNuevoUsuario(
                    idRealGenerado, 
                    u.getNombre(), 
                    u.getRol(), 
                    correosAdmins
                );
            } else {
                System.out.println("No se envió la alerta: No se encontraron correos de administradores.");
            }
        } catch (Exception ex) {
            System.err.println("Error en el hilo de envío de alertas: " + ex.getMessage());
        }
    }).start();
    // =========================================================================
    
} catch (Exception e) {
    javax.swing.JOptionPane.showMessageDialog(this, "Error al registrar el usuario: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
      // 1. Llama a tu método existente para borrar los cuadros de texto
    limpiarCampos();
    
    // 2. Recarga la tabla con el diseño original y todos los datos completos
    listarUsuarios();
    }//GEN-LAST:event_btnLimpiarActionPerformed

     private void limpiarCampos() {
        id_usuarios.setText("");
        rol.setText("");
        nombreUsuario.setText("");
        correo.setText("");
        contraseña.setText("");
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Barra barra1;
    private Componentes.Boton btnCrear;
    private Componentes.Boton btnEliminar;
    private Componentes.Boton btnLimpiar;
    private Componentes.Boton btnModificar;
    private Componentes.Boton btnVer;
    private Componentes.TexBox contraseña;
    private Componentes.TexBox correo;
    private Iconos.IconoEstadistica iconoEstadistica2;
    private Componentes.TexBox id_usuarios;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable10;
    private Labels.LabelEscalable labelEscalable11;
    private Labels.LabelEscalable labelEscalable12;
    private Labels.LabelEscalable labelEscalable13;
    private Labels.LabelEscalable labelEscalable14;
    private Labels.LabelEscalable labelEscalable8;
    private Labels.LabelEscalable labelEscalable9;
    private Componentes.TexBox nombreUsuario;
    private Componentes.TexBox rol;
    private Componentes.Tabla tblUsuarios;
    // End of variables declaration//GEN-END:variables
}
