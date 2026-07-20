package Componentes;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Barra extends JPanel {

    public Barra() {
        // Opcional: tamaño por defecto
        setSize(400, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Colores: morado profundo a rojo brillante
        Color colorIzq = new Color(90, 0, 120);   // morado
        Color colorDer = new Color(220, 0, 0);    // rojo

        GradientPaint gradiente = new GradientPaint(
            0, 0, colorIzq,
            getWidth(), 0, colorDer
        );

        g2.setPaint(gradiente);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }
}
