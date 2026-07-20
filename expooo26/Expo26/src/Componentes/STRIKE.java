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
 * Componente personalizado para títulos con el degradado exacto de "STRIKE".
 */
public class STRIKE extends JLabel {

    public STRIKE() {
        // Configuración inicial por defecto para el diseñador
        setText("STRIKE");
        // Una tipografía gruesa recomendada para este estilo deportivo
        setFont(new Font("Fc Faster", Font.BOLD, 36)); 
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 1. Activar filtros de antialiasing para un acabado curvo impecable
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 2. Colores exactos extraídos de la imagen "STRIKE" (Rojo Coral -> Naranja -> Naranja Claro)
        Color rojoCoralIzquierda = new Color(243, 60, 48);   // #F33C30 - Tono inicial de la "S"
        Color naranjaCentro      = new Color(254, 116, 62);  // #FE743E - Transición media (letras R-I)
        Color naranjaClaroDerecha = new Color(254, 155, 96); // #FE9B60 - Extremo iluminado de la "E"

        // 3. Configurar la dirección del degradado (Izquierda a Derecha)
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(getWidth(), 0);
        
        float[] fractions = {0.0f, 0.5f, 1.0f};
        Color[] colors = {rojoCoralIzquierda, naranjaCentro, naranjaClaroDerecha};

        LinearGradientPaint gradiente = new LinearGradientPaint(start, end, fractions, colors);

        // 4. Aplicar el degradado al lienzo de texto
        g2.setPaint(gradiente);

        // 5. Dibujar el texto respetando la alineación asignada en las propiedades
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