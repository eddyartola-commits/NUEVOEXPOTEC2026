package Iconos;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class IconoEstadistica extends JPanel {

    private Color colorFondo = new Color(40, 20, 60);   // morado oscuro
    private Color colorBorde = new Color(180, 100, 255); // púrpura brillante
    private int radioEsquinas = 20; // redondeo de esquinas

    public IconoEstadistica() {
        setOpaque(false);
        setPreferredSize(new Dimension(200, 60)); // tamaño inicial
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Fondo redondeado
        g2.setColor(colorFondo);
        g2.fill(new RoundRectangle2D.Double(0, 0, w, h, radioEsquinas, radioEsquinas));

        // Borde
        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(2));
        g2.draw(new RoundRectangle2D.Double(1, 1, w - 2, h - 2, radioEsquinas, radioEsquinas));

        g2.dispose();
    }

    // Métodos para cambiar colores si quieres desde el diseñador
    public void setColorFondo(Color c) { this.colorFondo = c; repaint(); }
    public void setColorBorde(Color c) { this.colorBorde = c; repaint(); }
    public void setRadioEsquinas(int r) { this.radioEsquinas = r; repaint(); }
}
