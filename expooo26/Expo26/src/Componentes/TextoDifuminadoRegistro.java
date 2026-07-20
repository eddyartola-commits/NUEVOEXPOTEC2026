/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import javax.swing.JLabel;
public class TextoDifuminadoRegistro extends javax.swing.JLabel {
    
    public TextoDifuminadoRegistro() {
        setText("REGISTRARSE");
        setFont(new java.awt.Font("Horizon", java.awt.Font.BOLD, 24)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getText() == null || getText().isEmpty()) return;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float[] posiciones = {0.0f, 0.5f, 1.0f};
        Color[] colores = {
            new Color(255, 30, 30),   
            new Color(255, 110, 0),   
            new Color(255, 160, 20)   
        };

        LinearGradientPaint degradado = new LinearGradientPaint(0, 0, getWidth(), 0, posiciones, colores);
        g2d.setPaint(degradado);
        g2d.setFont(getFont());
        
        int x = 0;
        int y = g2d.getFontMetrics().getAscent();
        g2d.drawString(getText(), x, y);
        g2d.dispose();
    }    
}
