package Componentes;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class TextoDifuminado extends JLabel {

    public TextoDifuminado() {
        // Valores iniciales por defecto
        setText("Iniciar Sesión");
        setFont(new java.awt.Font("Segoe UI Black", java.awt.Font.BOLD, 82));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Activar suavizado para que las letras no se vean pixeladas
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Definir las posiciones del difuminado (0.0 es el inicio, 1.0 es el final)
        float[] posiciones = {0.0f, 0.5f, 1.0f};
        
        // Definir los colores exactos: Rojo -> Naranja -> Amarillo
       Color[] colores = {
    new Color(255, 30, 30),   // 1. Rojo intenso (Inicio)
    new Color(255, 110, 0),   // 2. Naranja intermedio (Centro)
    new Color(255, 160, 20)    // 3. ¡CAMBIADO! Ahora es un naranja claro en lugar de amarillo (Final)
};

        // Crear el difuminado lineal que va de izquierda a derecha
        LinearGradientPaint degradado = new LinearGradientPaint(
            0, 0, getWidth(), 0, 
            posiciones, colores
        );

        // Aplicar el difuminado al texto
        g2d.setPaint(degradado);
        
        // Dibujar el texto en la pantalla
        g2d.setFont(getFont());
        int x = 0;
        int y = g2d.getFontMetrics().getAscent();
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}