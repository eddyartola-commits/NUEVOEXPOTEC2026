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
public class TituloImpacto extends JLabel {

    public TituloImpacto() {
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

        // 2. 🎨 COLORES CAMBIADOS: Extracción exacta de la foto "DATOS"
        Color moradoIzquierda = new Color(133, 49, 221); // #8531DD - Tono morado inicial de la "D"
        Color purpleCentro    = new Color(203, 56, 161); // #CB38A1 - Púrpura/Fucsia intermedio (letras A-T)
        Color violetaDerecha  = new Color(245, 59, 97);  // #F53B61 - Rosa encendido/Coral de la "O-S"

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