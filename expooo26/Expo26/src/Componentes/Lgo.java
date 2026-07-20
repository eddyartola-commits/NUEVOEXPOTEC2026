package Componentes;

import java.awt.*;
import javax.swing.*;

public class Lgo extends JPanel {

    private String textoPower = "POWER";
    private String textoFit = "FIT";
    private String textoStrike = "STRIKE";

    public Lgo() {
        setOpaque(false);
        setPreferredSize(new Dimension(600, 200)); // más alto para el salto de línea
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font fuente = new Font("Horizon", Font.BOLD, 48); // tu fuente personalizada
        g2.setFont(fuente);
        FontMetrics fm = g2.getFontMetrics();

        int x = 20;
        int y = 60; // primera línea

        // POWER → degradado gris
        GradientPaint gradPower = new GradientPaint(0, 0, new Color(200,200,200),
                                                    200, 0, new Color(80,80,80));
        g2.setPaint(gradPower);
        g2.drawString(textoPower, x, y);

        x += fm.stringWidth(textoPower) + 20;

        // FIT → degradado rojo
        GradientPaint gradFit = new GradientPaint(0, 0, new Color(255,80,80),
                                                  200, 0, new Color(180,0,0));
        g2.setPaint(gradFit);
        g2.drawString(textoFit, x, y);

        // 🔹 Salto de línea para STRIKE
        x = 20; // reinicia X
        y += 70; // baja a la siguiente línea

        // STRIKE → degradado naranja más intenso
        GradientPaint gradStrike = new GradientPaint(0, 0, new Color(255,200,100),
                                                     200, 0, new Color(255,100,0));
        g2.setPaint(gradStrike);
        g2.drawString(textoStrike, x, y);

        g2.dispose();
    }
}
