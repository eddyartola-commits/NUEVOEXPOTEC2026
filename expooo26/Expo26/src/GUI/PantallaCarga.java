package GUI;




public class PantallaCarga extends javax.swing.JFrame {

   private int mouseX;
   private int mouseY;
   
    public PantallaCarga() {
        
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null); // Centrar en pantalla

        // 🔹 1. Capturar la posición inicial del mouse al presionar la pantalla
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });

        // 🔹 2. Calcular el desplazamiento y mover la ventana mientras se arrastra
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                
                // Mueve la ventana dinámicamente a las nuevas coordenadas
                setLocation(x - mouseX, y - mouseY);
            }
        });
        
        barraCarga3.iniciarCarga();
        
        
    }

     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraCarga1 = new Componentes.BarraCarga();
        barraCarga2 = new Componentes.BarraCarga();
        panelPrincipal = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        barraCarga3 = new Componentes.BarraCarga();
        sTRIKE1 = new Componentes.STRIKE();
        fIT1 = new Componentes.FIT();
        pOWER1 = new Componentes.POWER();
        labelEscalable1 = new Labels.LabelEscalable();
        labelEscalable2 = new Labels.LabelEscalable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(43, 4, 64));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(500, 400));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setBackground(new java.awt.Color(12, 0, 33));
        panelPrincipal.setLayout(new java.awt.CardLayout());

        btnCerrar.setBackground(new java.awt.Color(19, 3, 27));
        btnCerrar.setMaximumSize(new java.awt.Dimension(1160, 630));
        btnCerrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(45, 7, 107));
        jPanel1.setLayout(null);
        btnCerrar.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 40));
        btnCerrar.add(barraCarga3, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 390, 350, 70));

        sTRIKE1.setFont(new java.awt.Font("FC Faster", 2, 44)); // NOI18N
        btnCerrar.add(sTRIKE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 260, 50));

        fIT1.setFont(new java.awt.Font("FC Faster", 2, 40)); // NOI18N
        btnCerrar.add(fIT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 100, 40));

        pOWER1.setFont(new java.awt.Font("FC Faster", 2, 40)); // NOI18N
        btnCerrar.add(pOWER1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 200, -1));

        labelEscalable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Logo.png"))); // NOI18N
        btnCerrar.add(labelEscalable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 108, 300, 140));

        labelEscalable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoPantallaCarga.png"))); // NOI18N
        btnCerrar.add(labelEscalable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1100, 590));

        panelPrincipal.add(btnCerrar, "card3");

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents
                             
    
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.BarraCarga barraCarga1;
    private Componentes.BarraCarga barraCarga2;
    private Componentes.BarraCarga barraCarga3;
    private javax.swing.JPanel btnCerrar;
    private Componentes.FIT fIT1;
    private javax.swing.JPanel jPanel1;
    private Labels.LabelEscalable labelEscalable1;
    private Labels.LabelEscalable labelEscalable2;
    private Componentes.POWER pOWER1;
    private javax.swing.JPanel panelPrincipal;
    private Componentes.STRIKE sTRIKE1;
    // End of variables declaration//GEN-END:variables
}
