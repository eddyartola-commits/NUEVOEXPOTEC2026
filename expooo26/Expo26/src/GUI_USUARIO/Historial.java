package GUI_USUARIO;

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

public class Historial extends javax.swing.JPanel {
    // 🔹 Guardamos el ID del usuario actual para filtrar sus registros

    private int idUsuarioLogueado;
// Dentro de tu clase Historial.java agrega esto:

    // Modificamos el constructor para recibir el ID del usuario que inició sesión
    public Historial(int idUsuarioLogueado) {
         this.idUsuarioLogueado = idUsuarioLogueado;

    System.out.println("ID recibido en Historial: " + this.idUsuarioLogueado);

    initComponents();
    configurarTabla();
    cargarDatos();
    System.out.println("Buscando datos para ID: " + idUsuarioLogueado);

        

        // 1. Establecemos el título correcto en el Label
        jLabel1.setText("Historial Personal");

        try {
            // Carga la imagen original desde el paquete Iconos
            java.net.URL imgURL = getClass().getResource("/Iconos/Usuariooo.png");

            if (imgURL != null) {
                // 🔹 REDIMENSIONADO DE ALTA CALIDAD PARA EL ICONO DEL USUARIO (50x50 píxeles)
                int tamIcono = 50;
                Image imgOrig = new ImageIcon(imgURL).getImage();
                java.awt.image.BufferedImage imgRedim = new java.awt.image.BufferedImage(tamIcono, tamIcono, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = imgRedim.createGraphics();

                // Filtros para evitar que se pixelee o se vea borroso
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.drawImage(imgOrig, 0, 0, tamIcono, tamIcono, null);
                g2.dispose();

                // Asignamos el ícono ya suavizado y escalado al label
                jLabel1.setIcon(new ImageIcon(imgRedim));

                // Espaciado y alineación entre el ícono y el texto
                jLabel1.setIconTextGap(20); // 🔹 Añade 20 píxeles de espacio de separación
                jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT); // Texto a la derecha
                jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);   // Centrado verticalmente
            } else {
                System.err.println("No se pudo encontrar el icono: Usuariooo");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el icono: " + e.getMessage());
        }

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
                GradientPaint gp = new GradientPaint(0, 0, new Color(200, 0, 0), getWidth(), getHeight(), new Color(120, 0, 200));
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
            }
        });
        panelOpciones.add(btnCerrarSesion);

        // 🔹 Método local de alta calidad para redimensionar los íconos a 45x45 de forma nítida
        java.util.function.BiFunction<String, Integer, ImageIcon> crearIconoCalidad = (ruta, tam) -> {
            java.net.URL url = getClass().getResource(ruta);
            if (url == null) {
                return new ImageIcon();
            }
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
        ImageIcon iconoAjustadoHover = crearIconoCalidad.apply("/Iconos/flecha.png", tamañoZoom);

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

        // 🛠️ SOLUCIÓN: Agregarlos a jPanel1 en lugar de usar add(...) básico
        this.add(jButton1);
        this.add(panelOpciones);

        // 🔹 2. 🛠️ SOLUCIÓN AL ORDEN DE CAPAS (Z-ORDER) SIN USAR PANEL INTERMEDIO:
        // Forzamos a que la flecha esté al frente de todo (Capa 0)
        this.setComponentZOrder(jButton1, 0);

        // El panel de opciones flotante justo debajo de la flecha (Capa 1)
        this.setComponentZOrder(panelOpciones, 1);
        // 🔹 3. Forzar el refresco de renderizado en 'this'
        this.revalidate();
        this.repaint();
    }

    public void configurarTabla() {
        String[] columnas = {
            "ID Datos",
            "ID Login (Admin)",
            "Edad",
            "Peso (lb)",
            "Ritmo Cardíaco",
            "Punteo Usuario",
            "Fecha Registro"
        };
        
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.getTabla().setModel(modelo);
        

    }

    public void cargarDatos() {
        // Obtenemos el modelo actual de la tabla
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tabla.getTabla().getModel();

        // Limpiamos la tabla antes de cargar datos nuevos (por si acaso se vuelve a llamar)
        modelo.setRowCount(0);

        // Consulta SQL para obtener los datos que coincidan con el usuario
        String sql = "SELECT id_datos, "
        + "id_login, "
        + "edad, "
        + "peso_lb, "
        + "ritmo_cardiaco, "
        + "punteo_usuario, "
        + "fecha_registro "
        + "FROM Datos "
        + "WHERE id_login = ? "
        + "ORDER BY fecha_registro DESC";
        

        try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, this.idUsuarioLogueado);

            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[7];
    fila[0] = rs.getInt("id_datos");
    fila[1] = rs.getInt("id_login");
    fila[2] = rs.getInt("edad");
    fila[3] = rs.getDouble("peso_lb");
    fila[4] = rs.getInt("ritmo_cardiaco");
    fila[5] = rs.getInt("punteo_usuario");
   fila[6] = rs.getTimestamp("fecha_registro");

                    modelo.addRow(fila); // Agrega la fila a la interfaz
                }
            }
        } catch (java.sql.SQLException e) {
    e.printStackTrace();

    JOptionPane.showMessageDialog(this,
        e.getMessage(),
        "Error SQL",
        JOptionPane.ERROR_MESSAGE);
}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabla = new Componentes.Tabla();
        btneliminar = new Componentes.Boton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        barra1 = new Componentes.Barra();
        barra2 = new Componentes.Barra();

        setBackground(new java.awt.Color(19, 3, 27));
        setForeground(new java.awt.Color(153, 255, 204));
        setLayout(null);
        add(tabla);
        tabla.setBounds(50, 230, 1370, 540);

        btneliminar.setText("Eliminar Dato");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        add(btneliminar);
        btneliminar.setBounds(590, 810, 310, 70);

        jLabel1.setFont(new java.awt.Font("Horizon", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Historial Personal");
        add(jLabel1);
        jLabel1.setBounds(270, 40, 880, 56);

        jLabel2.setFont(new java.awt.Font("Horizon", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Datos Personales ");
        add(jLabel2);
        jLabel2.setBounds(60, 170, 382, 29);

        javax.swing.GroupLayout barra1Layout = new javax.swing.GroupLayout(barra1);
        barra1.setLayout(barra1Layout);
        barra1Layout.setHorizontalGroup(
            barra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        barra1Layout.setVerticalGroup(
            barra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        add(barra1);
        barra1.setBounds(250, 100, 920, 6);

        javax.swing.GroupLayout barra2Layout = new javax.swing.GroupLayout(barra2);
        barra2.setLayout(barra2Layout);
        barra2Layout.setHorizontalGroup(
            barra2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        barra2Layout.setVerticalGroup(
            barra2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        add(barra2);
        barra2.setBounds(60, 200, 380, 6);
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if (tabla.getTabla().getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No hay registros para eliminar.");
            return;
        }
        int filaSeleccionada = tabla.getTabla().getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila de la tabla para eliminar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar este registro de forma permanente?",
                "Confirmar Eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (confirmar != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

       int idEliminar = Integer.parseInt(
        tabla.getTabla().getValueAt(filaSeleccionada, 0).toString());
        String sql = "DELETE FROM Datos\n"
                + "WHERE id_datos = ?\n"
                + "AND id_login = ?";

        try (java.sql.Connection con = Conexion.conectar(); java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setInt(1,idEliminar);
            pst.setInt(2, this.idUsuarioLogueado);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                cargarDatos(); // 🔹 REFRESCAR: Vuelve a cargar los datos en tiempo real después de borrar uno
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo encontrar el registro para eliminar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, "Error de base de datos al intentar eliminar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btneliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Barra barra1;
    private Componentes.Barra barra2;
    private Componentes.Boton btneliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Componentes.Tabla tabla;
    // End of variables declaration//GEN-END:variables
}
