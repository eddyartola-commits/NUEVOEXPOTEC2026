package GUI_USUARIO;

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

public class PruebaImpacto extends javax.swing.JPanel {

    private int idUsuarioLogueado;

        
    
    public PruebaImpacto(int idUsuarioLogueado) {
        this.idUsuarioLogueado = idUsuarioLogueado;
        initComponents();
        setLayout(null);
        System.out.println("Usuario conectado ID: " + idUsuarioLogueado);
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

          //Paneles Cambiados
         double pesoInicial = 0.0;
try {
    String texto = txtPeso.getText(); // O la variable de texto que uses en la línea 55
    if (texto != null && !texto.trim().isEmpty()) {
        pesoInicial = Double.parseDouble(texto.trim());
    }
} catch (NumberFormatException e) {
    pesoInicial = 0.0; // Evita que el programa se rompa si está vacío al iniciar
}
        
        
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

        this.add(jButton1);
        this.add(panelOpciones);

        // 🔹 2. 🛠️ SOLUCIÓN AL ORDEN DE CAPAS (Z-ORDER) SIN USAR PANEL INTERMEDIO:
        // Forzamos a que la flecha esté al frente de todo (Capa 0)
        this.setComponentZOrder(jButton1, 0);

        // El panel de opciones flotante justo debajo de la flecha (Capa 1)
        this.setComponentZOrder(panelOpciones, 1);

        // Enviamos tu componente de fondo ("fondo") al último lugar absoluto de la lista
        if (fondo != null) {
            this.setComponentZOrder(fondo, this.getComponentCount() - 1);
        }

        // 🔹 3. Forzar el refresco de renderizado en 'this'
        this.revalidate();
        this.repaint();

        this.setPreferredSize(new java.awt.Dimension(1491, 1338));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloImpacto1 = new Componentes.TituloImpacto();
        tituloImpacto2 = new Componentes.TituloImpacto();
        tituloImpacto3 = new Componentes.TituloImpacto();
        tituloImpacto4 = new Componentes.TituloImpacto();
        tituloImpacto5 = new Componentes.TituloImpacto();
        labelEscalable3 = new Labels.LabelEscalable();
        labelEscalable5 = new Labels.LabelEscalable();
        labelEscalable6 = new Labels.LabelEscalable();
        boton1 = new Componentes.Boton();
        boton2 = new Componentes.Boton();
        texBox1 = new Componentes.TexBox();
        texBox5 = new Componentes.TexBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tituloImpacto6 = new Componentes.TituloImpacto();
        tituloImpacto7 = new Componentes.TituloImpacto();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fondo = new Labels.LabelEscalable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        labelEscalable2 = new Labels.LabelEscalable();
        texBox3 = new Componentes.TexBox();
        labelEscalable1 = new Labels.LabelEscalable();
        txtPeso = new Componentes.TexBox();
        labelEscalable4 = new Labels.LabelEscalable();
        txtPunteo = new Componentes.TexBox();

        setBackground(new java.awt.Color(19, 3, 27));
        setForeground(new java.awt.Color(19, 3, 27));
        setLayout(null);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        add(labelEscalable3);
        labelEscalable3.setBounds(1200, 21, 55, 53);

        labelEscalable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-edad-48.png"))); // NOI18N
        add(labelEscalable5);
        labelEscalable5.setBounds(130, 720, 60, 70);

        labelEscalable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-edad-48.png"))); // NOI18N
        add(labelEscalable6);
        labelEscalable6.setBounds(780, 890, 60, 70);

        boton1.setText("JUGAR");
        boton1.setFont(new java.awt.Font("Horizon", 1, 24)); // NOI18N
        add(boton1);
        boton1.setBounds(850, 460, 430, 70);

        boton2.setText("GUARDAR DATOS");
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });
        add(boton2);
        boton2.setBounds(570, 1030, 300, 60);

        texBox1.setPlaceholderText("Edad");
        add(texBox1);
        texBox1.setBounds(130, 710, 350, 85);

        texBox5.setPlaceholderText("Fecha_Usuario");
        add(texBox5);
        texBox5.setBounds(780, 880, 350, 85);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Questrial", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Y vive la experiencia Power Fit Strike");
        add(jLabel11);
        jLabel11.setBounds(30, 610, 534, 30);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Questrial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("de potencia y domina la precisión ");
        add(jLabel4);
        jLabel4.setBounds(60, 210, 395, 25);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Questrial", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario");
        add(jLabel7);
        jLabel7.setBounds(1266, 17, 100, 40);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Questrial", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bienvenido");
        add(jLabel8);
        jLabel8.setBounds(1268, 45, 100, 30);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Questrial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("en cada impacto.");
        add(jLabel3);
        jLabel3.setBounds(60, 250, 220, 25);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Horizon", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Y RECUERDA!");
        add(jLabel2);
        jLabel2.setBounds(40, 1150, 650, 70);

        tituloImpacto6.setText("RETO?");
        tituloImpacto6.setFont(new java.awt.Font("Horizon", 1, 48)); // NOI18N
        add(tituloImpacto6);
        tituloImpacto6.setBounds(50, 100, 260, 70);

        tituloImpacto7.setText("DATOS");
        tituloImpacto7.setFont(new java.awt.Font("Horizon", 1, 48)); // NOI18N
        add(tituloImpacto7);
        tituloImpacto7.setBounds(520, 496, 350, 56);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Questrial", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Pon a prueba tus reflejos, destruye tus límites ");
        add(jLabel12);
        jLabel12.setBounds(60, 170, 534, 25);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Questrial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Registra tus datos despues de cada juego!");
        add(jLabel13);
        jLabel13.setBounds(30, 570, 534, 25);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Horizon", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("¿LISTO PARA EL ");
        add(jLabel5);
        jLabel5.setBounds(40, 40, 650, 70);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("INGRESA RITMO CARDIACO");
        add(jLabel6);
        jLabel6.setBounds(1010, 660, 380, 60);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Estadisticas.png"))); // NOI18N
        add(fondo);
        fondo.setBounds(670, 0, 820, 400);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Horizon", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("INGRESA TUS");
        add(jLabel16);
        jLabel16.setBounds(30, 490, 650, 70);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("INGRESA TU FECHA");
        add(jLabel17);
        jLabel17.setBounds(830, 830, 260, 60);

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("INGRESA TU PUNTAJE");
        add(jLabel18);
        jLabel18.setBounds(350, 830, 310, 60);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("INGRESA TU PESO");
        add(jLabel19);
        jLabel19.setBounds(610, 660, 250, 60);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Horizon", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("INGRESA TU EDAD");
        add(jLabel20);
        jLabel20.setBounds(190, 660, 250, 60);

        labelEscalable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ritmoCardiaco.png"))); // NOI18N
        add(labelEscalable2);
        labelEscalable2.setBounds(1014, 720, 60, 64);

        texBox3.setPlaceholderText("Ritmo_Cardiaco");
        add(texBox3);
        texBox3.setBounds(1010, 710, 350, 85);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/escala-de-peso.png"))); // NOI18N
        add(labelEscalable1);
        labelEscalable1.setBounds(580, 720, 50, 60);

        txtPeso.setPlaceholderText("Peso_Libras");
        add(txtPeso);
        txtPeso.setBounds(570, 710, 350, 85);

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/puntaje.png"))); // NOI18N
        add(labelEscalable4);
        labelEscalable4.setBounds(320, 880, 64, 70);

        txtPunteo.setPlaceholderText("Punteo_Usuario");
        add(txtPunteo);
        txtPunteo.setBounds(320, 880, 350, 85);
    }// </editor-fold>//GEN-END:initComponents

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        
        String edad = texBox1.getText().trim();
        String ritmo = texBox3.getText().trim();
        String fecha = texBox5.getText().trim(); 
        String peso = txtPeso.getText().trim();
        String puntaje = txtPunteo.getText().trim();

        // 1. Validar que no estén vacíos
        if (edad.isEmpty() || ritmo.isEmpty() || fecha.isEmpty() || peso.isEmpty() || puntaje.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Acceso Denegado: Todos los campos son obligatorios y no pueden estar vacíos.",
                    "Error al Guardar",
                    JOptionPane.ERROR_MESSAGE);
            return; // Detiene la ejecución si hay campos vacíos
        }

        // 2. Insertar en la Base de Datos
        String sql = "INSERT INTO Datos (id_login, edad, peso_lb, ritmo_cardiaco, punteo_usuario) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection con = Conexion.Conexion.conectar(); java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            // Convertimos los valores a variables numéricas para usarlas con seguridad
            double pesoDouble = Double.parseDouble(peso);
            int punteoInt = Integer.parseInt(puntaje);

            pst.setInt(1, this.idUsuarioLogueado);
            pst.setInt(2, Integer.parseInt(edad));
            pst.setDouble(3, pesoDouble); 
            pst.setInt(4, Integer.parseInt(ritmo));
            pst.setInt(5, punteoInt);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                // =============================================================
                // 1. GUARDAMOS EN EL MENÚ PRIMERO (Mientras las variables tienen valor)
                // =============================================================
                GUI.Menu.datosIngresados = true;
                GUI.Menu.pesoUsuarioActual = pesoDouble; 
                GUI.Menu.punteoUsuarioActual = punteoInt;

                JOptionPane.showMessageDialog(this,
                        "¡Los datos se han registrado y guardado en la base de datos con éxito!",
                        "Operación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                // =============================================================
                // 2. AHORA SÍ LIMPIAMOS LAS CAJAS TRANQUILAMENTE
                // =============================================================
                texBox1.setText("");
                texBox3.setText("");
                texBox5.setText("");
                txtPeso.setText("");
                txtPunteo.setText("");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Por favor ingresa solo números válidos en los campos numéricos (puedes usar decimales en el peso).",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error de base de datos al intentar guardar los datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        // NOTA: Borra por completo el bloque "Codigo para actualizar los paneles" 
        // que tenías aquí abajo del catch, ya no es necesario porque lo guardamos arriba con seguridad.
       

 
        
    }//GEN-LAST:event_boton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Boton boton1;
    private Componentes.Boton boton2;
    private Labels.LabelEscalable fondo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable2;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Labels.LabelEscalable labelEscalable5;
    private Labels.LabelEscalable labelEscalable6;
    private Componentes.TexBox texBox1;
    private Componentes.TexBox texBox3;
    private Componentes.TexBox texBox5;
    private Componentes.TituloImpacto tituloImpacto1;
    private Componentes.TituloImpacto tituloImpacto2;
    private Componentes.TituloImpacto tituloImpacto3;
    private Componentes.TituloImpacto tituloImpacto4;
    private Componentes.TituloImpacto tituloImpacto5;
    private Componentes.TituloImpacto tituloImpacto6;
    private Componentes.TituloImpacto tituloImpacto7;
    private Componentes.TexBox txtPeso;
    private Componentes.TexBox txtPunteo;
    // End of variables declaration//GEN-END:variables
}
