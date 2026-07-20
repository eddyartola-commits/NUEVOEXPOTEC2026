package Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class TexBox extends JPanel {
    private JTextField txtInput;
    private JLabel lblIcono;
    private int radioEsquinas = 20;
    private int anchoBloqueIcono = 65; 

    // 🔹 Texto placeholder
    private String placeholder = "PULSO CARDIACO";

    public TexBox() {
        setOpaque(false);
        setLayout(new BorderLayout());
        
        // Ajustamos márgenes exteriores
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 15));

        // Bloque del icono
        lblIcono = new JLabel();
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setPreferredSize(new Dimension(anchoBloqueIcono, 50));

        // Caja de texto corregida
        txtInput = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Dibujar placeholder si está vacío
                if (getText().isEmpty()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(150, 140, 170)); 
                    g2.setFont(getFont());
                    
                    FontMetrics fm = g2.getFontMetrics();
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(placeholder, 5, y);
                    g2.dispose();
                }
            }
        };
        
        txtInput.setFont(new Font("Questrial", Font.PLAIN, 16));
        txtInput.setForeground(Color.WHITE);
        
        // 🛠️ TRANSPARENCIA CORRECTORA ABSOLUTA
        txtInput.setOpaque(false); 
        txtInput.setBackground(new Color(0, 0, 0, 0)); 
        
        // Margen interior para el texto
        txtInput.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        txtInput.setCaretColor(Color.WHITE);
        txtInput.setEditable(true);

        // Agregar componentes
        add(lblIcono, BorderLayout.WEST);
        add(txtInput, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // NOTA: No llamamos a super.paintComponent(g) para evitar que Swing intente pintar fondos fantasma del sistema
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        // 1. Fondo principal redondo de la cápsula (Oscuro)
        RoundRectangle2D capsuleShape = new RoundRectangle2D.Double(0, 0, ancho, alto, radioEsquinas, radioEsquinas);
        g2.setColor(new Color(25, 10, 40));
        g2.fill(capsuleShape);

        // 2. 🛠️ CORRECCIÓN DE RECORTE MATEMÁTICO (Evita las esquinas transparentes flotantes)
        // Creamos un rectángulo plano para la zona del icono
        Rectangle2D iconoRect = new Rectangle2D.Double(0, 0, anchoBloqueIcono, alto);
        
        // Intersecamos el área de la cápsula redonda con el rectángulo del icono
        Area areaIconoRedondeada = new Area(capsuleShape);
        areaIconoRedondeada.intersect(new Area(iconoRect));

        // Pintamos el bloque izquierdo respetando perfectamente la curvatura interna
        g2.setColor(new Color(114, 38, 226)); // #7226e2
        g2.fill(areaIconoRedondeada);

        // 3. Borde exterior definido con tu nuevo color #7226e2
        g2.setColor(new Color(114, 38, 226)); // #7226e2
        g2.setStroke(new BasicStroke(2f)); 
        g2.draw(new RoundRectangle2D.Double(1, 1, ancho - 2, alto - 2, radioEsquinas, radioEsquinas));

        g2.dispose();
    }

    // Métodos públicos se mantienen igual...
    public String getText() { return txtInput.getText(); }
    public void setText(String texto) { txtInput.setText(texto); }
    public void setIcon(Icon icono) { lblIcono.setIcon(icono); }
    public void setPlaceholderText(String placeholder) { this.placeholder = placeholder; repaint(); }
    public String getPlaceholderText() { return placeholder; }
}