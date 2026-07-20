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
 * Componente personalizado para el título "FIT" con su degradado exacto.
 */
public class FIT extends JLabel {

    public FIT() {
        // Texto predeterminado para el componente
        setText("FIT");
        // Fuente gruesa e imponente para diseño deportivo
        setFont(new Font("Fc Faster", Font.BOLD, 36)); 
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 1. Activar los filtros de antialiasing avanzados para curvas perfectas
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 2. Colores exactos extraídos de tu logotipo (Rojo Coral -> Naranja -> Naranja Claro)
        Color rojoCoralIzquierda = new Color(243, 60, 48);   // #F33C30 - Tono de inicio intenso
        Color naranjaCentro      = new Color(254, 116, 62);  // #FE743E - Transición media
        Color naranjaClaroDerecha = new Color(254, 155, 96); // #FE9B60 - Extremo iluminado final

        // 3. Configurar la dirección del degradado de Izquierda a Derecha
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(getWidth(), 0);
        
        float[] fractions = {0.0f, 0.5f, 1.0f};
        Color[] colors = {rojoCoralIzquierda, naranjaCentro, naranjaClaroDerecha};

        LinearGradientPaint gradiente = new LinearGradientPaint(start, end, fractions, colors);

        // 4. Aplicar el degradado al lienzo de texto
        g2.setPaint(gradiente);

        // 5. Dibujar el texto centrado o alineado según las propiedades del componente
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