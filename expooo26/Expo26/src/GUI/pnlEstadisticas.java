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
import javax.swing.*;

public class pnlEstadisticas extends javax.swing.JPanel {

 
    public pnlEstadisticas() {
    
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

   
   
   String[] colsEstadisticas = {"ID DATOS", "ID LOGIN", "EDAD", "RITMO CARDÍACO", "PESO (LB)", "PUNTAJE"};
    tblEstadistica.inicializarTabla(colsEstadisticas);
   
    mostrarDatosTabla();
    
   // Solo define el tamaño completo real que deba medir tu diseño
    this.setPreferredSize(new java.awt.Dimension(1491, 1038));
    
}


  
 

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        iconoEstadistica2 = new Iconos.IconoEstadistica();
        labelEscalable4 = new Labels.LabelEscalable();
        labelEscalable1 = new Labels.LabelEscalable();
        labelEscalable5 = new Labels.LabelEscalable();
        barra1 = new Componentes.Barra();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelEscalable2 = new Labels.LabelEscalable();
        labelEscalable6 = new Labels.LabelEscalable();
        labelEscalable7 = new Labels.LabelEscalable();
        labelEscalable8 = new Labels.LabelEscalable();
        labelEscalable9 = new Labels.LabelEscalable();
        tblEstadistica = new Componentes.Tabla();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        iconoEstadistica3 = new Iconos.IconoEstadistica();
        barra2 = new Componentes.Barra();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelEscalable3 = new Labels.LabelEscalable();
        btnCrear1 = new Componentes.Boton();
        btnEliminar1 = new Componentes.Boton();
        btnVer1 = new Componentes.Boton();
        btnModificar1 = new Componentes.Boton();
        id_datos1 = new Componentes.TexBox();
        punteo_usuario1 = new Componentes.TexBox();
        id_login1 = new Componentes.TexBox();
        edad1 = new Componentes.TexBox();
        ritmo_cardiaco1 = new Componentes.TexBox();
        peso_lb1 = new Componentes.TexBox();
        tabla3 = new Componentes.Tabla();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new Componentes.Boton();
        btnEliminar = new Componentes.Boton();
        btnVer = new Componentes.Boton();
        btnModificar = new Componentes.Boton();
        id_datos = new Componentes.TexBox();
        id_login = new Componentes.TexBox();
        edad = new Componentes.TexBox();
        ritmo_cardiaco = new Componentes.TexBox();
        peso_lb = new Componentes.TexBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        punteo_usuario = new Componentes.TexBox();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiar = new Componentes.Boton();

        setBackground(new java.awt.Color(19, 3, 27));
        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Horizon", 3, 38)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estadisticas");
        add(jLabel2);
        jLabel2.setBounds(155, 75, 430, 44);

        iconoEstadistica2.setLayout(null);

        labelEscalable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/grafico-de-barras.png"))); // NOI18N
        iconoEstadistica2.add(labelEscalable4);
        labelEscalable4.setBounds(10, 8, 70, 70);

        add(iconoEstadistica2);
        iconoEstadistica2.setBounds(50, 44, 90, 90);

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ritmoCardiaco.png"))); // NOI18N
        add(labelEscalable1);
        labelEscalable1.setBounds(760, 760, 60, 50);

        labelEscalable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8-edad-48.png"))); // NOI18N
        add(labelEscalable5);
        labelEscalable5.setBounds(400, 760, 48, 60);

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

        labelEscalable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/puntaje.png"))); // NOI18N
        add(labelEscalable6);
        labelEscalable6.setBounds(1140, 610, 40, 50);

        labelEscalable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/escala-de-peso.png"))); // NOI18N
        add(labelEscalable7);
        labelEscalable7.setBounds(770, 620, 50, 40);

        labelEscalable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        add(labelEscalable8);
        labelEscalable8.setBounds(390, 610, 60, 50);

        labelEscalable9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/idUsuario.png"))); // NOI18N
        add(labelEscalable9);
        labelEscalable9.setBounds(20, 610, 70, 50);
        add(tblEstadistica);
        tblEstadistica.setBounds(20, 180, 1450, 340);

        jPanel1.setBackground(new java.awt.Color(19, 3, 27));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Horizon", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estadisticas");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(160, 80, 430, 41);

        javax.swing.GroupLayout iconoEstadistica3Layout = new javax.swing.GroupLayout(iconoEstadistica3);
        iconoEstadistica3.setLayout(iconoEstadistica3Layout);
        iconoEstadistica3Layout.setHorizontalGroup(
            iconoEstadistica3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        iconoEstadistica3Layout.setVerticalGroup(
            iconoEstadistica3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel1.add(iconoEstadistica3);
        iconoEstadistica3.setBounds(40, 50, 100, 90);

        javax.swing.GroupLayout barra2Layout = new javax.swing.GroupLayout(barra2);
        barra2.setLayout(barra2Layout);
        barra2Layout.setHorizontalGroup(
            barra2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        barra2Layout.setVerticalGroup(
            barra2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel1.add(barra2);
        barra2.setBounds(40, 150, 470, 3);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Questrial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bienvenido");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(1310, 45, 80, 30);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Questrial", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Administrador");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(1270, 18, 120, 40);

        labelEscalable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario (5).png"))); // NOI18N
        labelEscalable3.setText("labelEscalable2");
        jPanel1.add(labelEscalable3);
        labelEscalable3.setBounds(1200, 21, 55, 53);

        btnCrear1.setText("GUARDAR");
        btnCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrear1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear1);
        btnCrear1.setBounds(20, 760, 190, 30);

        btnEliminar1.setText("eliminar");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar1);
        btnEliminar1.setBounds(230, 760, 180, 30);

        btnVer1.setText("VER");
        btnVer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVer1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer1);
        btnVer1.setBounds(430, 770, 140, 20);

        btnModificar1.setText("MODIFICAR");
        btnModificar1.setToolTipText("");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar1);
        btnModificar1.setBounds(630, 770, 180, 30);

        id_datos1.setPlaceholderText("ID DATOS");
        jPanel1.add(id_datos1);
        id_datos1.setBounds(20, 560, 440, 40);

        punteo_usuario1.setPlaceholderText("PUNTAJE");
        jPanel1.add(punteo_usuario1);
        punteo_usuario1.setBounds(490, 710, 380, 40);

        id_login1.setPlaceholderText("ID LOGIN");
        jPanel1.add(id_login1);
        id_login1.setBounds(20, 630, 430, 40);

        edad1.setPlaceholderText("EDAD");
        jPanel1.add(edad1);
        edad1.setBounds(490, 560, 400, 50);

        ritmo_cardiaco1.setPlaceholderText("RITMO CARDIACO");
        jPanel1.add(ritmo_cardiaco1);
        ritmo_cardiaco1.setBounds(500, 630, 370, 50);

        peso_lb1.setPlaceholderText("PESO");
        jPanel1.add(peso_lb1);
        peso_lb1.setBounds(70, 680, 340, 50);
        jPanel1.add(tabla3);
        tabla3.setBounds(20, 180, 1420, 360);
        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(1010, 0, 20, 170);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1490, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1490, 1040);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 0, 0);

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar);
        btnGuardar.setBounds(60, 880, 260, 60);

        btnEliminar.setText("eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar);
        btnEliminar.setBounds(900, 880, 260, 60);

        btnVer.setText("VER");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        add(btnVer);
        btnVer.setBounds(340, 880, 260, 60);

        btnModificar.setText("MODIFICAR");
        btnModificar.setToolTipText("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar);
        btnModificar.setBounds(620, 880, 260, 60);

        id_datos.setPlaceholderText("ID DATOS");
        add(id_datos);
        id_datos.setBounds(20, 600, 340, 70);

        id_login.setBackground(new java.awt.Color(255, 255, 255));
        id_login.setForeground(new java.awt.Color(255, 255, 255));
        id_login.setPlaceholderText("ID LOGIN");
        add(id_login);
        id_login.setBounds(390, 600, 340, 70);

        edad.setPlaceholderText("EDAD");
        add(edad);
        edad.setBounds(390, 750, 340, 70);

        ritmo_cardiaco.setPlaceholderText("RITMO CARDIACO");
        add(ritmo_cardiaco);
        ritmo_cardiaco.setBounds(760, 750, 340, 70);

        peso_lb.setPlaceholderText("PESO");
        add(peso_lb);
        peso_lb.setBounds(760, 600, 340, 70);

        jLabel4.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingrese RITMO CARDIACO");
        add(jLabel4);
        jLabel4.setBounds(790, 700, 300, 50);

        jLabel9.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ingrese ID usuario");
        add(jLabel9);
        jLabel9.setBounds(460, 550, 230, 50);

        jLabel10.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ingrese PESO PERSONA");
        add(jLabel10);
        jLabel10.setBounds(800, 550, 270, 50);

        jLabel11.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ingrese ID");
        add(jLabel11);
        jLabel11.setBounds(130, 550, 130, 50);

        jLabel12.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ingrese EDAD PERSONA");
        add(jLabel12);
        jLabel12.setBounds(430, 700, 280, 50);

        punteo_usuario.setPlaceholderText("PUNTAJE");
        add(punteo_usuario);
        punteo_usuario.setBounds(1130, 600, 340, 70);

        jLabel1.setFont(new java.awt.Font("Horizon", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingrese PUNTAJE PERSONA");
        add(jLabel1);
        jLabel1.setBounds(1140, 550, 330, 50);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar);
        btnLimpiar.setBounds(1180, 880, 260, 60);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrear1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnVer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVer1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!validarCampos()) {
            JOptionPane.showMessageDialog(this,
                "Debe completar todos los campos antes de modificar el registro.");
            return;
        }

        String sql = "INSERT INTO Datos (id_login, edad, peso_lb, ritmo_cardiaco, punteo_usuario) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection con = Conexion.Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            if (id_login.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "El campo ID LOGIN es completamente obligatorio.");
                return;
            }

            ps.setInt(1, Integer.parseInt(id_login.getText().trim()));
            ps.setInt(2, Integer.parseInt(edad.getText().trim()));
            ps.setInt(3, Integer.parseInt(peso_lb.getText().trim()));
            ps.setInt(4, Integer.parseInt(ritmo_cardiaco.getText().trim()));
            ps.setInt(5, Integer.parseInt(punteo_usuario.getText().trim()));

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "¡Nuevo registro insertado correctamente!");
                mostrarDatosTabla(); // Refresca los cambios visuales en la tabla
                limpiarCampos();     // Limpia las cajas de texto de la interfaz
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Verifique que no existan letras en los campos numéricos.");
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error de Base de Datos: " + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String idEliminar = id_datos.getText().trim();

        if (idEliminar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID DATOS de la fila que desea eliminar.");
            return;
        }

        int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar permanentemente el registro #" + idEliminar + "?",
            "Confirmar acción", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirmar != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM Datos WHERE id_datos = ?";

        try (java.sql.Connection con = Conexion.Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(idEliminar));
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "El registro fue removido del sistema de forma exitosa.");
                mostrarDatosTabla(); // Remueve la fila de la tabla visualmente
                limpiarCampos();     // Limpia la pantalla
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún registro coincidente con ese ID.");
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al intentar eliminar el registro: " + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed

        String idBuscar = id_datos.getText().trim();

        if (idBuscar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar obligatoriamente un ID DATOS para realizar la consulta.", "ID Requerido", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Accedemos al modelo del componente y lo limpiamos para mostrar solo la búsqueda
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblEstadistica.getModelo();
        modelo.setRowCount(0);

        String sql = "SELECT id_datos, id_login, edad, ritmo_cardiaco, peso_lb, punteo_usuario FROM Datos WHERE id_datos = ?";

        try (java.sql.Connection con = Conexion.Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(idBuscar));

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id_datos");
                    String login = rs.getString("id_login");
                    String ed = rs.getString("edad");
                    String ritmo = rs.getString("ritmo_cardiaco");
                    String peso = rs.getString("peso_lb");
                    String puntaje = rs.getString("punteo_usuario");

                    // Rellenamos tus TextBox de la izquierda
                    id_login.setText(login);
                    edad.setText(ed);
                    ritmo_cardiaco.setText(ritmo);
                    peso_lb.setText(peso);
                    punteo_usuario.setText(puntaje);

                    // Insertamos la fila única localizada en la tabla
                    String[] fila = {id, login, ed, ritmo, peso, puntaje};
                    modelo.addRow(fila);
                    tblEstadistica.setRowSelectionInterval(0, 0);
                    tblEstadistica.requestFocusTabla();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "El ID de datos " + idBuscar + " no existe en el sistema.", "Sin registros", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    mostrarDatosTabla(); // Si no lo encuentra, vuelve a mostrar todo
                }
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID DATOS debe ser exclusivamente un número entero válido.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al buscar en la base de datos: " + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String idModificar = id_datos.getText().trim();

        // Validar que se haya ingresado el ID
        if (idModificar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID DATOS que desea modificar.");
            return;
        }

        try (java.sql.Connection con = Conexion.Conexion.conectar()) {

            // ==============================
            // OBTENER LOS DATOS ACTUALES
            // ==============================
            String consulta = "SELECT * FROM Datos WHERE id_datos = ?";

            int idLoginActual;
            int edadActual;
            double pesoActual;
            int ritmoActual;
            int puntajeActual;

            try (java.sql.PreparedStatement psConsulta = con.prepareStatement(consulta)) {

                psConsulta.setInt(1, Integer.parseInt(idModificar));

                java.sql.ResultSet rs = psConsulta.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "No existe un registro con ese ID.");
                    return;
                }

                idLoginActual = rs.getInt("id_login");
                edadActual = rs.getInt("edad");
                pesoActual = rs.getDouble("peso_lb");
                ritmoActual = rs.getInt("ritmo_cardiaco");
                puntajeActual = rs.getInt("punteo_usuario");
            }

            // ==============================
            // ACTUALIZAR
            // ==============================
            String sql = "UPDATE Datos SET id_login=?, edad=?, peso_lb=?, ritmo_cardiaco=?, punteo_usuario=? WHERE id_datos=?";

            try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1,
                    id_login.getText().trim().isEmpty()
                    ? idLoginActual
                    : Integer.parseInt(id_login.getText().trim()));

                ps.setInt(2,
                    edad.getText().trim().isEmpty()
                    ? edadActual
                    : Integer.parseInt(edad.getText().trim()));

                ps.setDouble(3,
                    peso_lb.getText().trim().isEmpty()
                    ? pesoActual
                    : Double.parseDouble(peso_lb.getText().trim()));

                ps.setInt(4,
                    ritmo_cardiaco.getText().trim().isEmpty()
                    ? ritmoActual
                    : Integer.parseInt(ritmo_cardiaco.getText().trim()));

                ps.setInt(5,
                    punteo_usuario.getText().trim().isEmpty()
                    ? puntajeActual
                    : Integer.parseInt(punteo_usuario.getText().trim()));

                ps.setInt(6, Integer.parseInt(idModificar));

                int resultado = ps.executeUpdate();

                if (resultado > 0) {

                    JOptionPane.showMessageDialog(this, "Registro modificado correctamente.");

                    mostrarDatosTabla();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(this, "No se pudo modificar el registro.");

                }

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                "Los campos numéricos deben contener únicamente números.");

        } catch (java.sql.SQLException e) {

            JOptionPane.showMessageDialog(this,
                "Error de Base de Datos: " + e.getMessage());

        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
        mostrarDatosTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

     private void ejecutarBusqueda() {
        String idDatos = id_datos.getText().trim();
        if (idDatos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID DATOS para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT id_login, edad, ritmo_cardiaco, peso_lb, punteo_usuario FROM Datos WHERE id_datos = ?";

        // 💡 NOTA: Se cambió 'Conexion.getConexion()' por 'Conexion.Conexion.getConexion()' para evitar conflictos de nombres con el paquete.
        // Busca donde dice Conexion.getConexion() y cámbialo por:
        try (java.sql.Connection con = Conexion.Conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(idDatos));
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id_login.setText(String.valueOf(rs.getInt("id_login")));
                    edad.setText(String.valueOf(rs.getInt("edad")));
                    ritmo_cardiaco.setText(String.valueOf(rs.getInt("ritmo_cardiaco")));
                    peso_lb.setText(String.valueOf(rs.getDouble("peso_lb")));
                    punteo_usuario.setText(String.valueOf(rs.getInt("punteo_usuario")));
                } else {
                    JOptionPane.showMessageDialog(this, "El ID DATOS no existe en la base de datos.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID DATOS debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     private boolean validarCampos() {
        return !id_login.getText().trim().isEmpty()
                && !edad.getText().trim().isEmpty()
                && !ritmo_cardiaco.getText().trim().isEmpty()
                && !peso_lb.getText().trim().isEmpty()
                && !punteo_usuario.getText().trim().isEmpty();
    }

    private void limpiarCampos() {
        id_datos.setText("");
        id_login.setText("");
        edad.setText("");
        ritmo_cardiaco.setText("");
        peso_lb.setText("");
        punteo_usuario.setText("");
    }
    
    //muestra los datos de la tabla de estadisticas.
    public void mostrarDatosTabla() {
        
        // 1. Obtenemos el modelo real de tu componente personalizado
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblEstadistica.getModelo();
        
        // 2. Limpiamos cualquier residuo o fila previa
        modelo.setRowCount(0);

        // 3. Consulta SQL ordenada
        String sql = "SELECT id_datos, id_login, edad, ritmo_cardiaco, peso_lb, punteo_usuario FROM Datos ORDER BY id_datos ASC";

        try (java.sql.Connection con = Conexion.Conexion.conectar(); 
             java.sql.PreparedStatement ps = con.prepareStatement(sql); 
             java.sql.ResultSet rs = ps.executeQuery()) {

            Object[] fila = new Object[6];

            while (rs.next()) {
                fila[0] = rs.getInt("id_datos");
                fila[1] = rs.getInt("id_login");
                fila[2] = rs.getInt("edad");
                fila[3] = rs.getInt("ritmo_cardiaco");
                fila[4] = rs.getDouble("peso_lb");
                fila[5] = rs.getInt("punteo_usuario");

                // Agregamos la fila directamente al componente visual
                modelo.addRow(fila);
            }

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la tabla: " + e.getMessage());
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Barra barra1;
    private Componentes.Barra barra2;
    private Componentes.Boton btnCrear1;
    private Componentes.Boton btnEliminar;
    private Componentes.Boton btnEliminar1;
    private Componentes.Boton btnGuardar;
    private Componentes.Boton btnLimpiar;
    private Componentes.Boton btnModificar;
    private Componentes.Boton btnModificar1;
    private Componentes.Boton btnVer;
    private Componentes.Boton btnVer1;
    private Componentes.TexBox edad;
    private Componentes.TexBox edad1;
    private Iconos.IconoEstadistica iconoEstadistica2;
    private Iconos.IconoEstadistica iconoEstadistica3;
    private Componentes.TexBox id_datos;
    private Componentes.TexBox id_datos1;
    private Componentes.TexBox id_login;
    private Componentes.TexBox id_login1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable2;
    private Labels.LabelEscalable labelEscalable3;
    private Labels.LabelEscalable labelEscalable4;
    private Labels.LabelEscalable labelEscalable5;
    private Labels.LabelEscalable labelEscalable6;
    private Labels.LabelEscalable labelEscalable7;
    private Labels.LabelEscalable labelEscalable8;
    private Labels.LabelEscalable labelEscalable9;
    private Componentes.TexBox peso_lb;
    private Componentes.TexBox peso_lb1;
    private Componentes.TexBox punteo_usuario;
    private Componentes.TexBox punteo_usuario1;
    private Componentes.TexBox ritmo_cardiaco;
    private Componentes.TexBox ritmo_cardiaco1;
    private Componentes.Tabla tabla3;
    private Componentes.Tabla tblEstadistica;
    // End of variables declaration//GEN-END:variables

    
}
