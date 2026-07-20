package Componentes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class BarraCarga extends JPanel {

    private int progreso = 0;
    private Timer timer;
    private Point mousePress;

    public BarraCarga() {
        // Ajustamos el tamaño preferido al contenedor de tu interfaz
        setPreferredSize(new Dimension(500, 70));

        // Hace transparente el panel para que luzca el fondo de Mike Tyson
        setOpaque(false);

        // Timer para la animación del progreso (cada 50ms sube 1%)
        timer = new Timer(50, e -> {
            if (progreso < 100) {
                progreso++;
                repaint(); // Redibuja los gráficos en cada tick
            } else {
                timer.stop();
                
                // 1. Buscamos el JFrame actual (PantallaCarga) que contiene a esta barra
                Window ventanaActual = SwingUtilities.getWindowAncestor(this);
                
                if (ventanaActual != null) {
                    // 2. Creamos e inicializamos la ventana de destino (reemplaza 'Menu' por tu clase real)
                    RegistraseInicioSesion.Registrase ventanaMenu = new RegistraseInicioSesion.Registrase(); 
                    ventanaMenu.setVisible(true);
                    
                    // 3. Destruimos la pantalla de carga para liberar memoria
                    ventanaActual.dispose();
                }
                
            }
        });

        // Eventos para arrastrar el componente / ventana
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point location = getLocation();
                int dx = e.getX() - mousePress.x;
                int dy = e.getY() - mousePress.y;
                setLocation(location.x + dx, location.y + dy);
            }
        });
    }

    // Inicia la animación desde cero
    public void iniciarCarga() {
        progreso = 0;
        if (timer != null) {
            timer.start();
        }
    }

    // Establecer progreso de forma manual si es necesario
    public void setProgreso(int valor) {
        progreso = Math.max(0, Math.min(100, valor));
        repaint();
    }

    public int getProgreso() {
        return progreso;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // NO llamamos a super.paintComponent(g) para mantener la transparencia cristalina del fondo
        Graphics2D g2 = (Graphics2D) g.create();

        // Activamos suavizado de bordes de alta calidad para las curvas y fuentes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int ancho = getWidth();

        // -----------------------------------------------------------------
        // 1. DIBUJO DE TEXTOS (Alineación simétrica con fuente Horizon)
        // -----------------------------------------------------------------
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Horizon", Font.BOLD, 18));
        g2.drawString("CARGANDO...", 10, 22);
        g2.drawString(progreso + "%", ancho - 65, 22);

        // Configuración geométrica de la cápsula
        int xBarra = 10;
        int yBarra = 35;
        int anchoBarra = ancho - 20;
        int altoBarra = 22;
        int radioEsquinas = altoBarra; // Esquinas perfectamente circulares (Estilo cápsula)

        // -----------------------------------------------------------------
        // 2. FONDO OSCURO DE LA CÁPSULA (Morado/Negro translúcido)
        // -----------------------------------------------------------------
        g2.setColor(new Color(16, 6, 26, 220)); 
        g2.fill(new RoundRectangle2D.Double(xBarra, yBarra, anchoBarra, altoBarra, radioEsquinas, radioEsquinas));

        // -----------------------------------------------------------------
        // 3. BARRA DE PROGRESO DEGRADADA NEÓN (Rojo brillante -> Violeta Eléctrico)
        // -----------------------------------------------------------------
        if (progreso > 0) {
            // Descontamos 4 píxeles para dejar un margen estético con el borde neón
            int anchoProgreso = (anchoBarra - 4) * progreso / 100;
            
            if (anchoProgreso > 0) {
                // El gradiente abarca toda la longitud para que la transición cromática se mantenga fija
                GradientPaint gp = new GradientPaint(
                        xBarra + 2, yBarra + 2, new Color(255, 0, 55),          // Rojo/Rosa Neón
                        xBarra + anchoBarra - 2, yBarra + 2, new Color(130, 0, 255) // Violeta Eléctrico
                );
                g2.setPaint(gp);
                
                // Pintamos el relleno interior reduciendo levemente la escala para no pisar el draw del contorno
                g2.fill(new RoundRectangle2D.Double(xBarra + 2, yBarra + 2, anchoProgreso, altoBarra - 4, radioEsquinas - 4, radioEsquinas - 4));
            }
        }

        // -----------------------------------------------------------------
        // 4. BORDE EXTERIOR DE CONTORNO NEÓN (Brillante y definido)
        // -----------------------------------------------------------------
        g2.setColor(new Color(185, 20, 255, 180)); // Magenta Neón con canal alfa para efecto de brillo sutil
        g2.setStroke(new BasicStroke(1.5f)); // Trazo de línea fina idéntica al asset original
        g2.draw(new RoundRectangle2D.Double(xBarra, yBarra, anchoBarra, altoBarra, radioEsquinas, radioEsquinas));

        g2.dispose();
    }
}