
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


public class menuAdmi extends javax.swing.JPanel {


    public menuAdmi() {
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
        
          this.setPreferredSize(new java.awt.Dimension(1491, 1038));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        boton21 = new Componentes.Boton2();
        boton22 = new Componentes.Boton2();
        barra1 = new Componentes.Barra();
        labelEscalable3 = new Labels.LabelEscalable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelEscalable1 = new Labels.LabelEscalable();
        fondo = new Labels.LabelEscalable();
        tituloImpacto7 = new Componentes.TituloImpacto();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(19, 3, 27));
        setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Questrial", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administra y controla todo panel");
        add(jLabel1);
        jLabel1.setBounds(40, 240, 300, 30);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Horizon", 1, 41)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("control");
        add(jLabel2);
        jLabel2.setBounds(30, 120, 320, 40);

        boton21.setText("GESTIONAR");
        boton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton21ActionPerformed(evt);
            }
        });
        add(boton21);
        boton21.setBounds(20, 350, 300, 60);

        boton22.setText("VER MI PROGRESO");
        add(boton22);
        boton22.setBounds(20, 350, 300, 60);
        add(barra1);
        barra1.setBounds(12, 320, 315, 5);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        add(labelEscalable3);
        labelEscalable3.setBounds(1200, 21, 55, 53);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Questrial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Administrador");
        add(jLabel7);
        jLabel7.setBounds(1266, 17, 160, 40);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Questrial", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bienvenido");
        add(jLabel8);
        jLabel8.setBounds(1268, 45, 100, 30);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/fondo2.png"))); // NOI18N
        add(labelEscalable1);
        labelEscalable1.setBounds(10, 520, 1470, 380);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FONDO2 (1).png"))); // NOI18N
        add(fondo);
        fondo.setBounds(360, 0, 1130, 440);

        tituloImpacto7.setText("total");
        tituloImpacto7.setFont(new java.awt.Font("Horizon", 1, 51)); // NOI18N
        add(tituloImpacto7);
        tituloImpacto7.setBounds(50, 170, 260, 59);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Questrial", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CONDENADO AL EXITO");
        add(jLabel3);
        jLabel3.setBounds(60, 78, 220, 26);
    }// </editor-fold>//GEN-END:initComponents

    private void boton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton21ActionPerformed
         GUI.pnlGestionarUsuarios nuevaPantalla = new GUI.pnlGestionarUsuarios(); 
    
    // 2. Buscamos el contenedor principal
    java.awt.Container padre = this.getParent();
    
    if (padre != null) {
        padre.removeAll(); // Borramos el contenido actual del contenedor
        
        // Ajustamos la nueva pantalla al tamaño del contenedor
        nuevaPantalla.setSize(padre.getWidth(), padre.getHeight());
        nuevaPantalla.setLocation(0, 0);
        
        padre.add(nuevaPantalla); // Agregamos el panel de gestionar usuarios
        padre.revalidate();       // Refrescamos el diseño
        padre.repaint();          // Volvemos a pintar la interfaz
    }    // TODO add your handling
    }//GEN-LAST:event_boton21ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Barra barra1;
    private Componentes.Boton2 boton21;
    private Componentes.Boton2 boton22;
    private Labels.LabelEscalable fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable3;
    private Componentes.TituloImpacto tituloImpacto7;
    // End of variables declaration//GEN-END:variables
}
