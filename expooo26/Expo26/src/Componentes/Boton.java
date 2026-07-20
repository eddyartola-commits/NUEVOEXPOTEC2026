package Componentes;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Cursor;

public class Boton extends JButton {

    private Font fuentePersonalizada;
    
    // Variables de control para la animación de zoom
    private Timer timerAnimacion;
    private int anchoOriginal = -1;
    private int altoOriginal = -1;
    private int xOriginal = -1;
    private int yOriginal = -1;
    
    // 🔹 Parámetros ajustados para mayor impacto y velocidad
    private final int incrementoMaximo = 26; // ¡Crece el doble que antes!
    private final int paso = 4; // Transición más rápida y enérgica

    public Boton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setBorder(new EmptyBorder(10, 25, 10, 25));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        this.fuentePersonalizada = new Font("Horizon", Font.BOLD, 14);
        super.setFont(this.fuentePersonalizada);
        
        agregarEfectoZoom();
    }

    private void agregarEfectoZoom() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // Capturar dimensiones originales la primera vez
                if (anchoOriginal == -1) {
                    anchoOriginal = getWidth();
                    altoOriginal = getHeight();
                    xOriginal = getX();
                    yOriginal = getY();
                }

                int anchoObjetivo = anchoOriginal + incrementoMaximo;
                int altoObjetivo = altoOriginal + (incrementoMaximo * altoOriginal / anchoOriginal);

                if (timerAnimacion != null && timerAnimacion.isRunning()) {
                    timerAnimacion.stop();
                }

                timerAnimacion = new Timer(10, ev -> {
                    int w = getWidth();
                    int h = getHeight();
                    if (w < anchoObjetivo) {
                        int nuevoW = Math.min(w + paso, anchoObjetivo);
                        int nuevoH = Math.min(h + paso, altoObjetivo);
                        int nuevoX = getX() - (nuevoW - w) / 2;
                        int nuevoY = getY() - (nuevoH - h) / 2;
                        setBounds(nuevoX, nuevoY, nuevoW, nuevoH);
                    } else {
                        timerAnimacion.stop();
                    }
                });
                timerAnimacion.start();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (anchoOriginal == -1) return;

                if (timerAnimacion != null && timerAnimacion.isRunning()) {
                    timerAnimacion.stop();
                }

                timerAnimacion = new Timer(10, ev -> {
                    int w = getWidth();
                    int h = getHeight();
                    if (w > anchoOriginal) {
                        int nuevoW = Math.max(w - paso, anchoOriginal);
                        int nuevoH = Math.max(h - paso, altoOriginal);
                        int nuevoX = getX() + (w - nuevoW) / 2;
                        int nuevoY = getY() + (h - nuevoH) / 2;
                        setBounds(nuevoX, nuevoY, nuevoW, nuevoH);
                    } else {
                        // Asegurar el encuadre exacto original
                        setBounds(xOriginal, yOriginal, anchoOriginal, altoOriginal);
                        timerAnimacion.stop();
                    }
                });
                timerAnimacion.start();
            }
        });
    }

    @Override
    public void setFont(Font font) {
        this.fuentePersonalizada = font;
        super.setFont(font);
        repaint(); 
    }

    @Override
    public Font getFont() {
        return this.fuentePersonalizada != null ? this.fuentePersonalizada : super.getFont();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setFont(getFont());

        Graphics2D g2 = (Graphics2D) g.create();
       
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        Point2D inicio = new Point2D.Float(0, getHeight());
        Point2D fin = new Point2D.Float(getWidth() * 0.8f, 0);
        float[] distribucion = {0.0f, 1.0f};

        Color colorInicio = new Color(43, 11, 84);  
        Color colorFin = new Color(255, 39, 59);    

        LinearGradientPaint lgp = new LinearGradientPaint(inicio, fin, distribucion, new Color[]{colorInicio, colorFin});
        g2.setPaint(lgp);

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

        g2.dispose();
        super.paintComponent(g);
    }
}