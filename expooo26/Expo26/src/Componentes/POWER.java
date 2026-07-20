package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import javax.swing.JLabel;

/**
 * Componente personalizado para el título "POWER" con su degradado metálico exacto.
 */
public class POWER extends JLabel {

    public POWER() {
        // Texto inicial para el componente
        setText("POWER");
        // Fuente imponente para mantener la estética robusta y deportiva
        setFont(new Font("Fc Faster", Font.BOLD, 36)); 
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 1. Activar filtros de antialiasing avanzados para curvas perfectas sin píxeles duros
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 2. Colores exactos extraídos de la imagen "POWER" (Gris Medio -> Gris Plata -> Blanco Grisáceo)
        Color grisIzquierda = new Color(157, 158, 160); // #9D9EA0 - Tono base de la "P"
        Color plataCentro    = new Color(199, 200, 202); // #C7C8CA - Brillo medio de la "W"
        Color blancoDerecha  = new Color(228, 230, 233); // #E4E6E9 - Extremo iluminado de la "R"

        // 3. Configurar la dirección del degradado lineal (Izquierda a Derecha)
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(getWidth(), 0);
        
        float[] fractions = {0.0f, 0.5f, 1.0f};
        Color[] colors = {grisIzquierda, plataCentro, blancoDerecha};

        LinearGradientPaint gradiente = new LinearGradientPaint(start, end, fractions, colors);

        // 4. Aplicar el degradado al lienzo del texto
        g2.setPaint(gradiente);

        // 5. Dibujar el texto respetando la alineación configurada en el diseño
        java.awt.FontMetrics fm = g2.getFontMetrics();
        int x = 0;
        
        if (getHorizontalAlignment() == CENTER) {
            x = (getWidth() - fm.stringWidth(getText())) / 2;
        } else if (getHorizontalAlignment() == RIGHT) {
            x = getWidth() - fm.stringWidth(getText());
        }
        
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

        g2.drawString(getText(), x, y);
        g2.dispose();
    }
}