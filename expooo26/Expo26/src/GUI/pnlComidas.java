package GUI;

import BaseDatos.DB_Comidas;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class pnlComidas extends javax.swing.JPanel {

    private String imagenSeleccionada = "";

    public pnlComidas() {
        initComponents();
        cargarComidas();

        tblComidas.getTabla().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                mostrarImagenComida();
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbNivelComida = new javax.swing.JComboBox<>();
        spComidas = new javax.swing.JScrollPane();
        tblComidas = new Componentes.Tabla();
        lblVistaComida = new javax.swing.JLabel();
        btnGuardarComida = new Componentes.Boton2();
        btnSeleccionarImagen = new Componentes.Boton2();
        pOWER1 = new Componentes.POWER();

        setBackground(new java.awt.Color(19, 3, 27));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbNivelComida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Principiante", "Intermedio", "Avanzado" }));
        cmbNivelComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNivelComidaActionPerformed(evt);
            }
        });
        add(cmbNivelComida, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        tblComidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComidasMouseClicked(evt);
            }
        });
        spComidas.setViewportView(tblComidas);

        add(spComidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 510, 390));
        add(lblVistaComida, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 750, 570));

        btnGuardarComida.setText("Guardar");
        btnGuardarComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComidaActionPerformed(evt);
            }
        });
        add(btnGuardarComida, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, -1, -1));

        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });
        add(btnSeleccionarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, -1, -1));

        pOWER1.setText("Vista Previa");
        pOWER1.setFont(new java.awt.Font("Fc Faster", 1, 30)); // NOI18N
        add(pOWER1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNivelComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNivelComidaActionPerformed
        cargarComidas();
    }//GEN-LAST:event_cmbNivelComidaActionPerformed

    private void tblComidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComidasMouseClicked

    }//GEN-LAST:event_tblComidasMouseClicked


    private void btnGuardarComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComidaActionPerformed
        int fila = tblComidas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una comida");
            return;
        }
        if (imagenSeleccionada == null || imagenSeleccionada.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una imagen");
            return;
        }
        int idComida = Integer.parseInt(
                tblComidas.getValueAt(fila, 0).toString()
        );
        // Obtener el nombre a partir del archivo
        String nombreComida = imagenSeleccionada
                .replace(".png", "")
                .replace(".jpg", "")
                .replace(".jpeg", "")
                .replace("_", "");
        DB_Comidas db = new DB_Comidas();
        boolean actualizado = db.actualizarImagenComida(
                idComida,
                nombreComida,
                imagenSeleccionada
        );
        if (actualizado) {
            JOptionPane.showMessageDialog(this,
                    "Imagen actualizada correctamente");
            cargarComidas();
            mostrarImagenComida();
            imagenSeleccionada = "";
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo actualizar la imagen");
        }
    }//GEN-LAST:event_btnGuardarComidaActionPerformed

    private void cargarComidas() {
        DefaultTableModel modelo = tblComidas.getModelo();
        modelo.setRowCount(0);
        String nivel = cmbNivelComida.getSelectedItem().toString();
        System.out.println("Nivel seleccionado: " + nivel);
        DB_Comidas dbComidas = new DB_Comidas();
        ResultSet rs = dbComidas.obtenerComidasPorNivel(nivel);
        try {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_comida"),
                    rs.getString("nombre")
                });
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla: " + e.getMessage());
        }
    }

    private void mostrarImagenComida() {
        int fila = tblComidas.getSelectedRow();
        if (fila == -1) {
            return;
        }
        int idComida = Integer.parseInt(
                tblComidas.getValueAt(fila, 0).toString());
        DB_Comidas db = new DB_Comidas();
        String imagen = db.obtenerImagenComida(idComida);
        lblVistaComida.setToolTipText(imagen);
        java.net.URL ruta = getClass().getResource("/ImagenesRC/" + imagen);
        if (ruta != null) {
            ImageIcon icono = new ImageIcon(ruta);
            Image img = icono.getImage().getScaledInstance(
                    lblVistaComida.getWidth(),
                    lblVistaComida.getHeight(),
                    Image.SCALE_SMOOTH);
            lblVistaComida.setIcon(new ImageIcon(img));
        } else {
            lblVistaComida.setIcon(null);
            System.out.println("No se encontró la imagen: " + imagen);
        }
    }

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        JFileChooser selector = new JFileChooser();
        // Solo permitir imágenes
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes", "jpg", "png", "jpeg"
        ));
        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = selector.getSelectedFile();
            // Guardamos el nombre de la imagen seleccionada
            imagenSeleccionada = archivo.getName();
            // Mostramos la imagen en el JLabel
            ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
            Image imagen = icono.getImage().getScaledInstance(
                    lblVistaComida.getWidth(),
                    lblVistaComida.getHeight(),
                    Image.SCALE_SMOOTH
            );
            lblVistaComida.setIcon(new ImageIcon(imagen));
            System.out.println("Imagen seleccionada: " + imagenSeleccionada);
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Boton2 btnGuardarComida;
    private Componentes.Boton2 btnSeleccionarImagen;
    private javax.swing.JComboBox<String> cmbNivelComida;
    private javax.swing.JLabel lblVistaComida;
    private Componentes.POWER pOWER1;
    private javax.swing.JScrollPane spComidas;
    private Componentes.Tabla tblComidas;
    // End of variables declaration//GEN-END:variables
}
