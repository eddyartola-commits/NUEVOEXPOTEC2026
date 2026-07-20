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
 * Componente personalizado para títulos con degradado de color de alta calidad.
 */
public class TituloMenu extends JLabel {

    public TituloMenu() {
        // Configuración inicial por defecto (se puede cambiar desde el diseñador)
        setText("VERSION");
        // Usamos una fuente gruesa e imponente como la de tu diseño
        setFont(new Font("Horizon", Font.BOLD, 43)); 
        setForeground(Color.WHITE); // Color de respaldo
    }

@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 1. Activar Antialiasing avanzado para curvas perfectas y máxima nitidez
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 2. Colores exactos extraídos de la imagen "VERSION" (Morado -> Púrpura -> Violeta Claro)
        Color moradoIzquierda = new Color(103, 43, 226); // #672BE2 - Morado base de la "V"
        Color purpleCentro    = new Color(146, 45, 237); // #922DED - Púrpura brillante (letras R-S)
        Color violetaDerecha  = new Color(168, 62, 252); // #A83EFC - Violeta iluminado de la "N"

        // 3. Configurar puntos de anclaje del degradado (Izquierda a Derecha)
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(getWidth(), 0);
        
        float[] fractions = {0.0f, 0.5f, 1.0f};
        Color[] colors = {moradoIzquierda, purpleCentro, violetaDerecha};

        LinearGradientPaint gradiente = new LinearGradientPaint(start, end, fractions, colors);

        // 4. Aplicar el degradado como pintura del texto
        g2.setPaint(gradiente);

        // 5. Dibujar el texto respetando la alineación del componente
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
