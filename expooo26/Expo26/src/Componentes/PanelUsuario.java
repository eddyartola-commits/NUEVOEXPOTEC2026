package Componentes;

import javax.swing.*;
import java.awt.*;

public class PanelUsuario extends JPanel {

    private JLabel lblUsuario;
    private JPanel panelOpciones;
    private JButton btnCerrarSesion;

    public PanelUsuario(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(19,3,27));

        // Label superior
        lblUsuario = new JLabel("Administrador ▼");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Horizon", Font.BOLD, 18));

        // Panel oculto
        panelOpciones = new JPanel();
        panelOpciones.setBackground(new Color(30,30,30));
        panelOpciones.setVisible(false);

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.addActionListener(e -> {
            JOptionPane.showMessageDialog(parentFrame, "Sesión cerrada");
            parentFrame.dispose(); // cerrar ventana principal
        });

        panelOpciones.add(btnCerrarSesion);

        // Evento para mostrar/ocultar
        lblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                panelOpciones.setVisible(!panelOpciones.isVisible());
                revalidate();
                repaint();
            }
        });

        add(lblUsuario, BorderLayout.NORTH);
        add(panelOpciones, BorderLayout.CENTER);
    }
}