package GUI_USUARIO;

import GUI.Menu;
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
import java.sql.ResultSet;
import BaseDatos.DB_Comidas;

public class Comidas extends javax.swing.JPanel {

    public Comidas() {
        initComponents();
        cargarImagenesComidas();
        panelPrincipiante.setVisible(false);
        panelIntermedio.setVisible(false);
        panelAvanzado.setVisible(false);

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

        // Solo define el tamaño completo real que deba medir tu diseño
        this.setPreferredSize(new java.awt.Dimension(1492, 1733));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEscalable2 = new Labels.LabelEscalable();
        labelEscalable3 = new Labels.LabelEscalable();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        boton21 = new Componentes.Boton2();
        fondo = new Labels.LabelEscalable();
        tituloImpacto1 = new Componentes.TituloImpacto();
        jLabel4 = new javax.swing.JLabel();
        panelPrincipiante = new javax.swing.JPanel();
        tituloImpacto3 = new Componentes.TituloImpacto();
        labelEscalable12 = new Labels.LabelEscalable();
        labelEscalable13 = new Labels.LabelEscalable();
        labelEscalable14 = new Labels.LabelEscalable();
        panelAvanzado = new javax.swing.JPanel();
        tituloImpacto6 = new Componentes.TituloImpacto();
        labelEscalable6 = new Labels.LabelEscalable();
        labelEscalable7 = new Labels.LabelEscalable();
        labelEscalable8 = new Labels.LabelEscalable();
        panelIntermedio = new javax.swing.JPanel();
        tituloImpacto4 = new Componentes.TituloImpacto();
        labelEscalable1 = new Labels.LabelEscalable();
        labelEscalable4 = new Labels.LabelEscalable();
        labelEscalable5 = new Labels.LabelEscalable();

        setBackground(new java.awt.Color(19, 3, 27));
        setForeground(new java.awt.Color(204, 0, 153));
        setLayout(null);

        labelEscalable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/flecha-correcta.png"))); // NOI18N
        add(labelEscalable2);
        labelEscalable2.setBounds(260, 340, 70, 60);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        add(labelEscalable3);
        labelEscalable3.setBounds(1200, 21, 55, 53);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Questrial", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario");
        add(jLabel7);
        jLabel7.setBounds(1266, 17, 100, 40);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Questrial", 0, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Bienvenido");
        add(jLabel9);
        jLabel9.setBounds(1268, 45, 100, 30);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Horizon", 1, 34)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("de los platillos");
        add(jLabel3);
        jLabel3.setBounds(20, 200, 490, 50);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("comidas deliciosas, balance perfecto de");
        add(jLabel5);
        jLabel5.setBounds(10, 260, 510, 40);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Horizon", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("macros y micras para alcanzar tus metas");
        add(jLabel8);
        jLabel8.setBounds(20, 310, 460, 20);

        boton21.setText("JUGAR DE Nuevo");
        boton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton21ActionPerformed(evt);
            }
        });
        add(boton21);
        boton21.setBounds(10, 340, 350, 70);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoComida.jpeg"))); // NOI18N
        add(fondo);
        fondo.setBounds(520, 0, 970, 420);

        tituloImpacto1.setText("rica tentacion?");
        tituloImpacto1.setFont(new java.awt.Font("Horizon", 1, 36)); // NOI18N
        add(tituloImpacto1);
        tituloImpacto1.setBounds(15, 140, 560, 44);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Horizon", 1, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("¿resistes nuestra");
        add(jLabel4);
        jLabel4.setBounds(10, 90, 530, 50);

        panelPrincipiante.setBackground(new java.awt.Color(19, 3, 27));
        panelPrincipiante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloImpacto3.setText("Principiante");
        panelPrincipiante.add(tituloImpacto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, -1));

        labelEscalable12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Tortilla_Espinacas.png"))); // NOI18N
        panelPrincipiante.add(labelEscalable12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 650, 500));

        labelEscalable13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Atun_Aguacate.png"))); // NOI18N
        panelPrincipiante.add(labelEscalable13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 630, -1, -1));

        labelEscalable14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Champiñones_Arroz.png"))); // NOI18N
        panelPrincipiante.add(labelEscalable14, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, -1, -1));

        add(panelPrincipiante);
        panelPrincipiante.setBounds(10, 450, 1480, 1280);

        panelAvanzado.setBackground(new java.awt.Color(19, 3, 27));
        panelAvanzado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloImpacto6.setText("Avanzado");
        panelAvanzado.add(tituloImpacto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        labelEscalable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Pancakes.png"))); // NOI18N
        panelAvanzado.add(labelEscalable6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        labelEscalable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Salsa_Champiñones.png"))); // NOI18N
        panelAvanzado.add(labelEscalable7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, 500));

        labelEscalable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Ensalada_Atun.png"))); // NOI18N
        panelAvanzado.add(labelEscalable8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, -1, -1));

        add(panelAvanzado);
        panelAvanzado.setBounds(10, 450, 1480, 1280);

        panelIntermedio.setBackground(new java.awt.Color(19, 3, 27));
        panelIntermedio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloImpacto4.setText("Intermedio");
        panelIntermedio.add(tituloImpacto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Pancakes.png"))); // NOI18N
        panelIntermedio.add(labelEscalable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Filete_Salmon.png"))); // NOI18N
        panelIntermedio.add(labelEscalable4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, -1, -1));

        labelEscalable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesRC/Carne_De_Res.png"))); // NOI18N
        panelIntermedio.add(labelEscalable5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 630, -1, -1));

        add(panelIntermedio);
        panelIntermedio.setBounds(10, 450, 1480, 1280);
    }// </editor-fold>//GEN-END:initComponents

    private void boton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton21ActionPerformed
        GUI_USUARIO.PruebaImpacto nuevaPantalla = new GUI_USUARIO.PruebaImpacto(1);

        // 2. Buscamos el contenedor principal de las pantallas
        java.awt.Container padre = this.getParent();

        if (padre != null) {
            padre.removeAll(); // Borramos la pantalla de inicio actual

            // Ajustamos la nueva pantalla al tamaño del contenedor derecho
            nuevaPantalla.setSize(padre.getWidth(), padre.getHeight());
            nuevaPantalla.setLocation(0, 0);

            padre.add(nuevaPantalla); // Agregamos la pantalla de juego
            padre.revalidate();       // Refrescamos el diseño
            padre.repaint();          // Volvemos a pintar la interfaz
        }
    }//GEN-LAST:event_boton21ActionPerformed

    public void actualizarInterfazComida(double pesoUsuario) {
        // 1. Primero nos aseguramos de ocultar todos los paneles para limpiar la pantalla
        panelPrincipiante.setVisible(false);
        panelIntermedio.setVisible(false);
        panelAvanzado.setVisible(false); // <--- CORREGIDO: Antes tenías repetido panelPrincipiante aquí

        System.out.println("DEBUG: El peso que llego a Comidas es: " + pesoUsuario);

        // 2. Evaluamos los rangos y encendemos SOLO el que corresponde
        if (pesoUsuario <= 0) {
            // Si no hay peso, se quedan todos ocultos
        } else if (pesoUsuario <= 130.00) {
            // Rango Ligero / Principiante
            panelPrincipiante.setVisible(true);
        } else if (pesoUsuario > 130.00 && pesoUsuario <= 175.00) {
            // Rango Estándar / Intermedio
            panelIntermedio.setVisible(true);
        } else {
            // Rango Avanzado / Hipercalórico
            panelAvanzado.setVisible(true); // <--- CORREGIDO: Ahora sí activa el panel avanzado
        }

        this.revalidate();
        this.repaint();
    }

    private void cargarImagenesComidas() {

        DB_Comidas db = new DB_Comidas();
        ResultSet rs = db.obtenerTodasLasComidas();
        try {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_comida") + " -> " + rs.getString("imagen")
                );
                int id = rs.getInt("id_comida");
                String imagen = rs.getString("imagen");
                java.net.URL ruta = getClass().getResource("/ImagenesRC/" + imagen);
                if (ruta == null) {
                    continue;
                }
                ImageIcon icono = new ImageIcon(ruta);
                switch (id) {
                    case 1:
                        labelEscalable12.setIcon(icono);
                        break;
                    case 2:
                        labelEscalable13.setIcon(icono);
                        break;
                    case 3:
                        labelEscalable14.setIcon(icono);
                        break;
                    case 4:
                        labelEscalable1.setIcon(icono);
                        break;
                    case 5:
                        labelEscalable4.setIcon(icono);
                        break;
                    case 6:
                        labelEscalable5.setIcon(icono);
                        break;
                    case 7:
                        labelEscalable6.setIcon(icono);
                        break;
                    case 8:
                        labelEscalable7.setIcon(icono);
                        break;
                    case 9:
                        labelEscalable8.setIcon(icono);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Boton2 boton21;
    private Labels.LabelEscalable fondo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable12;
    private Labels.LabelEscalable labelEscalable13;
    private Labels.LabelEscalable labelEscalable14;
    private Labels.LabelEscalable labelEscalable2;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Labels.LabelEscalable labelEscalable5;
    private Labels.LabelEscalable labelEscalable6;
    private Labels.LabelEscalable labelEscalable7;
    private Labels.LabelEscalable labelEscalable8;
    private javax.swing.JPanel panelAvanzado;
    private javax.swing.JPanel panelIntermedio;
    private javax.swing.JPanel panelPrincipiante;
    private Componentes.TituloImpacto tituloImpacto1;
    private Componentes.TituloImpacto tituloImpacto3;
    private Componentes.TituloImpacto tituloImpacto4;
    private Componentes.TituloImpacto tituloImpacto6;
    // End of variables declaration//GEN-END:variables
}
