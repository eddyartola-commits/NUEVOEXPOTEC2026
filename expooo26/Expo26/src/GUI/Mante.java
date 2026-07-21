



package GUI;

import Conexion.Conexion;
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
import javax.swing.table.DefaultTableModel;
import Conexion.MantenimientoDAO;
import Alerta.ServicioNotificaciones;

public class Mante extends javax.swing.JPanel {


    public Mante() {
        
        // 1. Inicializar componentes generados por NetBeans
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

        // 2. Inicializar y posicionar el Separador Difuminado personalizado
        Componentes.SeparadorDifuminado miLineaDifuminada = new Componentes.SeparadorDifuminado();
        miLineaDifuminada.setBounds(40, 125, 555, 16);
        this.add(miLineaDifuminada);

        // 3. Agregar los iconos pequeños a las cajas de texto de manera segura
        try {
            
        } catch (Exception e) {
            System.out.println("Error al cargar los iconos de las cajas: " + e.getMessage());
        }

       

        // 5. Inicializar los títulos de la Tabla de Mantenimiento corregidos
        String[] colsMantenimiento = {"ID MANTENIMIENTO", "ID LOGIN", "ESTADO MÁQUINA", "COMPONENTES DAÑADOS", "ÚLTIMA MODIFICACIÓN"};
        tabla.inicializarTabla(colsMantenimiento);

        

       

        btnver.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnverActionPerformed(null);
            }
        });

        btnactualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modificarDatosBD();
            }

        });

        bntlimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accionarBotonLimpiar();
            }
        });

        mostrarDatosEnTabla();

        this.revalidate();
        this.repaint();
        
        
          this.setPreferredSize(new java.awt.Dimension(1491, 1038));
    }
    
    private void eliminarDatosBD() {
        String idEliminar = texBox1.getText().trim();

        if (idEliminar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID Mantenimiento de la fila que desea eliminar.", "ID Requerido", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar permanentemente el reporte #" + idEliminar + "?",
                "Confirmar acción", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirmar != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM Reparacion_Mantenimiento WHERE id_mantenimiento = ?";

        try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(idEliminar));
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "El registro fue removido del sistema de forma exitosa.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                mostrarDatosEnTabla();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún registro coincidente con ese ID.", "Sin Coincidencias", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al intentar eliminar el registro: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarDatosEnBD() {
    if (!validarCampos()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos requeridos antes de guardar.", "Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    String idLoginStr = texBox2.getText().trim();
    String ultimaModificacion = texBox4.getText().trim();
    String estadoMaquina = texBox3.getText().trim();
    String componentesDanados = texBox5.getText().trim();

    String sql = "INSERT INTO Reparacion_Mantenimiento (id_login, estado_maquina, Componentes_danados, utima_modificacion) VALUES (?, ?, ?, ?)";

    try (java.sql.Connection con = Conexion.conectar(); 
         java.sql.PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

        try {
            ps.setInt(1, Integer.parseInt(idLoginStr));
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID de Login debe ser un número entero válido.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        ps.setString(2, estadoMaquina);
        ps.setString(3, componentesDanados);
        ps.setString(4, ultimaModificacion);
        
        int resultado = ps.executeUpdate();
        
        if (resultado > 0) {
            // Obtenemos el ID que MySQL le asignó automáticamente a este reporte
            String idGenerado = "N/A";
            try (java.sql.ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idGenerado = String.valueOf(generatedKeys.getInt(1));
                }
            }

            // 🚨 DISPARO AUTOMÁTICO DE NOTIFICACIÓN MASIVA DESDE EL BACKEND
           MantenimientoDAO mDAO = new MantenimientoDAO();
            String correosAdmins = mDAO.obtenerCorreosAdministradores();
            
            // Enviamos el correo masivo en segundo plano de forma silenciosa
            Alerta.ServicioNotificaciones.enviarAlertaMasivaAsync(correosAdmins, idGenerado);

            javax.swing.JOptionPane.showMessageDialog(this, "¡Reporte #" + idGenerado + " guardado y notificado a los Administradores!", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            mostrarDatosEnTabla();
        }
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error con la base de datos: " + e.getMessage(), "Error Crítico", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    
    public void modificarDatosBD() {
        String idModificar = texBox1.getText().trim();

        if (idModificar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, especifique el ID Mantenimiento que desea modificar.", "ID Requerido", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos para realizar la modificación.", "Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Tu consulta SQL tiene este orden:
        // 1. id_login = ?
        // 2. estado_maquina = ?
        // 3. Componentes_danados = ?
        // 4. utima_modificacion = ?
        // 5. WHERE id_mantenimiento = ?
        String sql = "UPDATE Reparacion_Mantenimiento SET id_login = ?, estado_maquina = ?, Componentes_danados = ?, utima_modificacion = ? WHERE id_mantenimiento = ?";

        try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            // ASIGNACIÓN CORREGIDA SEGÚN TUS TEXTBOX:
            ps.setInt(1, Integer.parseInt(texBox2.getText().trim()));    // 1. id_login (texBox2)
            ps.setString(2, texBox3.getText().trim());                  // 2. estado_maquina (texBox3)
            ps.setString(3, texBox5.getText().trim());                  // 3. Componentes_danados (texBox5)
            ps.setString(4, texBox4.getText().trim());                  // 4. utima_modificacion (texBox4)
            ps.setInt(5, Integer.parseInt(idModificar));                // 5. El ID del WHERE (texBox1)

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Registro ID " + idModificar + " actualizado con éxito.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                mostrarDatosEnTabla();
                limpiarCampos();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo modificar. El ID Mantenimiento no existe.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Los campos ID deben ser números enteros válidos.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error en la Base de Datos al modificar: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
     public void mostrarDatosEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModelo();
        modelo.setRowCount(0);

        String sql = "SELECT id_mantenimiento, id_login, estado_maquina, Componentes_danados, utima_modificacion FROM Reparacion_Mantenimiento";

        try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql); java.sql.ResultSet rs = ps.executeQuery()) {

            String[] fila = new String[5];
            while (rs.next()) {
                fila[0] = rs.getString("id_mantenimiento");
                fila[1] = rs.getString("id_login");
                fila[2] = rs.getString("estado_maquina");
                fila[3] = rs.getString("Componentes_danados");
                fila[4] = rs.getString("utima_modificacion");
                modelo.addRow(fila);
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la tabla: " + e.getMessage(), "Error Crítico", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        texBox1.setText("");
        texBox2.setText("");
        texBox3.setText("");
        texBox4.setText("");
        texBox5.setText("");
    }
    private boolean validarCampos() {
        return !texBox2.getText().trim().isEmpty()
                && !texBox3.getText().trim().isEmpty()
                && !texBox4.getText().trim().isEmpty()
                && !texBox5.getText().trim().isEmpty();
    }
    
    public void ejecutarBusqueda() {
    String idBuscar = texBox1.getText().trim();
    if (idBuscar.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese un ID Mantenimiento para buscar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    String sql = "SELECT id_login, estado_maquina, Componentes_danados, utima_modificacion FROM Reparacion_Mantenimiento WHERE id_mantenimiento = ?";

    try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, Integer.parseInt(idBuscar));
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Asignación correcta y emparejada con tus Placeholders:
                texBox2.setText(String.valueOf(rs.getInt("id_login")));             // ID Usuario -> texBox2
                texBox3.setText(rs.getString("estado_maquina"));                  // Estado Maquina -> texBox3
                texBox4.setText(rs.getString("utima_modificacion"));              // Ultima Modificacion -> texBox4
                texBox5.setText(rs.getString("Componentes_danados"));             // Componentes Dañados -> texBox5
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "El ID de mantenimiento no existe en la base de datos.", "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabla = new Componentes.Tabla();
        btneliminar = new Componentes.Boton();
        labelEscalable7 = new Labels.LabelEscalable();
        labelEscalable6 = new Labels.LabelEscalable();
        labelEscalable8 = new Labels.LabelEscalable();
        labelEscalable4 = new Labels.LabelEscalable();
        labelEscalable3 = new Labels.LabelEscalable();
        btnagregar = new Componentes.Boton();
        texBox4 = new Componentes.TexBox();
        texBox5 = new Componentes.TexBox();
        texBox3 = new Componentes.TexBox();
        texBox2 = new Componentes.TexBox();
        texBox1 = new Componentes.TexBox();
        btnver = new Componentes.Boton();
        btnactualizar = new Componentes.Boton();
        bntlimpiar = new Componentes.Boton();
        jLabel2 = new javax.swing.JLabel();
        iconoEstadistica2 = new Iconos.IconoEstadistica();
        labelEscalable1 = new Labels.LabelEscalable();
        barra1 = new Componentes.Barra();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelEscalable2 = new Labels.LabelEscalable();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(19, 3, 27));
        setLayout(null);
        add(tabla);
        tabla.setBounds(20, 180, 1450, 340);

        btneliminar.setText("Eliminar REPORTE");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        add(btneliminar);
        btneliminar.setBounds(320, 860, 280, 60);

        labelEscalable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/atencion-al-cliente.png"))); // NOI18N
        add(labelEscalable7);
        labelEscalable7.setBounds(1140, 620, 50, 40);

        labelEscalable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/identificaciones.png"))); // NOI18N
        add(labelEscalable6);
        labelEscalable6.setBounds(580, 770, 50, 40);

        labelEscalable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        add(labelEscalable8);
        labelEscalable8.setBounds(390, 610, 60, 50);

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/identificaciones.png"))); // NOI18N
        add(labelEscalable4);
        labelEscalable4.setBounds(40, 610, 40, 40);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/historial.png"))); // NOI18N
        add(labelEscalable3);
        labelEscalable3.setBounds(770, 610, 50, 50);

        btnagregar.setText("Agregar REPORTE");
        btnagregar.setActionCommand("Guardar Reporte");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        add(btnagregar);
        btnagregar.setBounds(30, 860, 280, 60);

        texBox4.setPlaceholderText("Ultima Modificaciòn");
        add(texBox4);
        texBox4.setBounds(760, 600, 340, 70);

        texBox5.setPlaceholderText("Componentes Dañados");
        add(texBox5);
        texBox5.setBounds(1130, 600, 340, 70);

        texBox3.setPlaceholderText("Estado Maquina");
        add(texBox3);
        texBox3.setBounds(570, 760, 340, 70);

        texBox2.setPlaceholderText("ID Usuario");
        add(texBox2);
        texBox2.setBounds(390, 600, 340, 70);

        texBox1.setPlaceholderText("ID Mantenimiento");
        add(texBox1);
        texBox1.setBounds(20, 600, 340, 70);

        btnver.setText("FILTRAR REPORTE");
        btnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverActionPerformed(evt);
            }
        });
        add(btnver);
        btnver.setBounds(910, 860, 280, 60);

        btnactualizar.setText("Actualizar Reporte");
        btnactualizar.setFont(new java.awt.Font("Horizon", 1, 13)); // NOI18N
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        add(btnactualizar);
        btnactualizar.setBounds(610, 860, 280, 60);

        bntlimpiar.setText("LIMPIAR CAMPOS");
        bntlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntlimpiarActionPerformed(evt);
            }
        });
        add(bntlimpiar);
        bntlimpiar.setBounds(1200, 860, 280, 60);

        jLabel2.setFont(new java.awt.Font("Horizon", 3, 38)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Reporte MANTENIMIENTO");
        add(jLabel2);
        jLabel2.setBounds(155, 75, 820, 44);

        iconoEstadistica2.setLayout(null);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/atencion-al-cliente.png"))); // NOI18N
        iconoEstadistica2.add(labelEscalable1);
        labelEscalable1.setBounds(10, 10, 70, 70);

        add(iconoEstadistica2);
        iconoEstadistica2.setBounds(50, 50, 90, 90);

        javax.swing.GroupLayout barra1Layout = new javax.swing.GroupLayout(barra1);
        barra1.setLayout(barra1Layout);
        barra1Layout.setHorizontalGroup(
            barra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        barra1Layout.setVerticalGroup(
            barra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        add(barra1);
        barra1.setBounds(40, 155, 590, 4);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Questrial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bienvenido");
        add(jLabel5);
        jLabel5.setBounds(1310, 45, 80, 30);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Questrial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Administrador");
        add(jLabel6);
        jLabel6.setBounds(1270, 18, 120, 40);

        labelEscalable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        labelEscalable2.setText("labelEscalable2");
        add(labelEscalable2);
        labelEscalable2.setBounds(1200, 21, 55, 53);

        jLabel9.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ingrese ID mantenimiento");
        add(jLabel9);
        jLabel9.setBounds(30, 550, 320, 50);

        jLabel12.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ingrese estado maquina");
        add(jLabel12);
        jLabel12.setBounds(590, 710, 310, 50);

        jLabel13.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ingrese ID usuario");
        add(jLabel13);
        jLabel13.setBounds(460, 550, 230, 50);

        jLabel14.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ingrese modificacion");
        add(jLabel14);
        jLabel14.setBounds(810, 550, 270, 50);

        jLabel15.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ingrese componentes");
        add(jLabel15);
        jLabel15.setBounds(1170, 550, 270, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void btnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverActionPerformed
        String idBuscar = texBox1.getText().trim();
        if (idBuscar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese un ID Mantenimiento para consultar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Consulta SQL para buscar el registro específico
        String sql = "SELECT id_mantenimiento, id_login, estado_maquina, Componentes_danados, utima_modificacion FROM Reparacion_Mantenimiento WHERE id_mantenimiento = ?";

        try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idBuscar));

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                // 1. Obtenemos el modelo de la tabla y lo limpiamos por completo
                DefaultTableModel modelo = (DefaultTableModel) tabla.getModelo();
                modelo.setRowCount(0);

                if (rs.next()) {
                    // 2. Llenamos las cajas de texto (como ya lo hacías)
                    texBox2.setText(String.valueOf(rs.getInt("id_login")));
                    texBox3.setText(rs.getString("estado_maquina"));
                    texBox4.setText(rs.getString("utima_modificacion"));
                    texBox5.setText(rs.getString("Componentes_danados"));

                    // 3. ¡NUEVO! Creamos la fila y la agregamos a la tabla para que SOLO aparezca este registro
                    String[] fila = new String[5];
                    fila[0] = rs.getString("id_mantenimiento");
                    fila[1] = rs.getString("id_login");
                    fila[2] = rs.getString("estado_maquina");
                    fila[3] = rs.getString("Componentes_danados");
                    fila[4] = rs.getString("utima_modificacion");

                    modelo.addRow(fila); // Agrega la fila única a la tabla

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "El ID de mantenimiento no existe.", "Aviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    // Si no existe, podemos volver a mostrar todos los datos para no dejar la tabla vacía
                    mostrarDatosEnTabla();
                }
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al consultar: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnverActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void bntlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntlimpiarActionPerformed

        String idBuscar = texBox1.getText().trim();
        String ultima = texBox2.getText().trim();
        String login = texBox3.getText().trim();
        String id = texBox4.getText().trim();
        String estado = texBox5.getText().trim();
        
        if (idBuscar.isEmpty() || ultima.isEmpty() || login.isEmpty() || id.isEmpty() || estado.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Sin argumentos para limpiar", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }else{
          javax.swing.JOptionPane.showMessageDialog(this, "Limpiado con exito", "Aviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
    }//GEN-LAST:event_bntlimpiarActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        guardarDatosEnBD();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminarActionPerformed

    public void accionarBotonLimpiar() {
        // 1. Borra el texto de todas las cajas
        limpiarCampos();

        // 2. Vuelve a cargar la lista completa de la base de datos en la tabla
        mostrarDatosEnTabla();

        // 3. Coloca el cursor automáticamente en la primera caja
        texBox1.requestFocus();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Barra barra1;
    private Componentes.Boton bntlimpiar;
    private Componentes.Boton btnactualizar;
    private Componentes.Boton btnagregar;
    private Componentes.Boton btneliminar;
    private Componentes.Boton btnver;
    private Iconos.IconoEstadistica iconoEstadistica2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable2;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Labels.LabelEscalable labelEscalable6;
    private Labels.LabelEscalable labelEscalable7;
    private Labels.LabelEscalable labelEscalable8;
    private Componentes.Tabla tabla;
    private Componentes.TexBox texBox1;
    private Componentes.TexBox texBox2;
    private Componentes.TexBox texBox3;
    private Componentes.TexBox texBox4;
    private Componentes.TexBox texBox5;
    // End of variables declaration//GEN-END:variables
}
