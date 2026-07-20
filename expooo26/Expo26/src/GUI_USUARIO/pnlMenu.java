package GUI;
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


public class pnlMenu extends javax.swing.JPanel {

    public pnlMenu() {
        
        
        initComponents();
        this.setLayout(null);
        // 1. Configuramos el panelOpciones oculto
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(null); 
        panelOpciones.setOpaque(false); 
        panelOpciones.setVisible(false);
        // Se ajustan las coordenadas dentro de jPanel1 (cerca del extremo derecho superior)
        panelOpciones.setBounds(1215, 100, 230, 90); 

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

        final int btnAnchoBase = 210;
        final int btnAltoBase = 55;
        final int btnAnchoZoom = 225; 
        final int btnAltoZoom = 65;  
        final int btnPaso = 2;

        btnCerrarSesion.setBounds(10, 10, btnAnchoBase, btnAltoBase);
        btnCerrarSesion.setFont(new Font("Horizon", Font.BOLD, 14));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setOpaque(false);
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        // 2. Redimensionamiento del ícono
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

        final int tamañoBase = 45;   
        final int tamañoZoom = 55;   
        final int paso = 2;

        ImageIcon iconoAjustadoNormal = crearIconoCalidad.apply("/Iconos/flecha.png", tamañoBase);
        ImageIcon iconoAjustadoHover  = crearIconoCalidad.apply("/Iconos/flecha.png", tamañoZoom);

        // Botón con ícono de flecha
        JButton jButton1 = new JButton(iconoAjustadoNormal);
        // Coordenadas reposicionadas en la esquina superior derecha dentro de jPanel1
        jButton1.setBounds(1400, 30, tamañoBase, tamañoBase); 
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jButton1.addActionListener(e -> {
            panelOpciones.setVisible(!panelOpciones.isVisible());
            jPanel1.revalidate();
            jPanel1.repaint();
        });

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
        jPanel1.add(jButton1);
        jPanel1.add(panelOpciones);

        // Forzar orden visual para asegurar que queden por encima de la imagen de fondo
        jPanel1.setComponentZOrder(jButton1, 0);
        jPanel1.setComponentZOrder(panelOpciones, 0);

        jPanel1.revalidate();
        jPanel1.repaint();
        
        this.setPreferredSize(new java.awt.Dimension(1491, 1038));
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelEscalable3 = new Labels.LabelEscalable();
        labelEscalable2 = new Labels.LabelEscalable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tituloMenu2 = new Componentes.TituloMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        barra1 = new Componentes.Barra();
        labelEscalable1 = new Labels.LabelEscalable();
        boton21 = new Componentes.Boton2();
        labelEscalable4 = new Labels.LabelEscalable();

        setBackground(new java.awt.Color(19, 3, 27));
        setMaximumSize(new java.awt.Dimension(1155, 583));
        setMinimumSize(new java.awt.Dimension(1155, 583));
        setPreferredSize(new java.awt.Dimension(1155, 583));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(19, 3, 27));
        jPanel1.setForeground(new java.awt.Color(19, 3, 27));
        jPanel1.setLayout(null);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        jPanel1.add(labelEscalable3);
        labelEscalable3.setBounds(1200, 21, 55, 53);

        labelEscalable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/flecha-correcta.png"))); // NOI18N
        jPanel1.add(labelEscalable2);
        labelEscalable2.setBounds(264, 383, 50, 40);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Questrial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bienvenido");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1273, 45, 80, 30);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Questrial", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Usuario");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(1266, 17, 100, 40);

        tituloMenu2.setFont(new java.awt.Font("Horizon", 1, 47)); // NOI18N
        jPanel1.add(tituloMenu2);
        tituloMenu2.setBounds(10, 170, 340, 50);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Questrial", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONDENADO AL EXITO");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(60, 70, 220, 30);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Horizon", 1, 41)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TU MEJOR");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 120, 320, 40);
        jPanel1.add(barra1);
        barra1.setBounds(12, 320, 315, 5);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FONDO.png"))); // NOI18N
        jPanel1.add(labelEscalable1);
        labelEscalable1.setBounds(360, 0, 1130, 440);

        boton21.setText("EMPIEZA A JUGAR");
        boton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton21ActionPerformed(evt);
            }
        });
        jPanel1.add(boton21);
        boton21.setBounds(20, 370, 310, 70);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1490, 480);

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoMenu.png"))); // NOI18N
        add(labelEscalable4);
        labelEscalable4.setBounds(10, 520, 1470, 380);
    }// </editor-fold>//GEN-END:initComponents

    private void boton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton21ActionPerformed
     GUI_USUARIO.PruebaImpacto nuevaPantalla = new GUI_USUARIO.PruebaImpacto(1); 
    
    // 2. Buscamos el contenedor principal
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Barra barra1;
    private Componentes.Boton2 boton21;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable2;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Componentes.TituloMenu tituloMenu2;
    // End of variables declaration//GEN-END:variables
}
