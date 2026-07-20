package GUI;

import BaseDatos.DB_Rutinas;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class pnlGestionRutinas extends javax.swing.JPanel {

    private String imagenSeleccionada;

    public pnlGestionRutinas() {
        initComponents();
        cargarRutinas();

        tblRutinas.getTabla().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                mostrarImagenRutina();
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbNivelRutina = new javax.swing.JComboBox<>();
        spRutinas = new javax.swing.JScrollPane();
        tblRutinas = new Componentes.Tabla();
        lblVistaRutina = new javax.swing.JLabel();
        btnGuardarRutina = new Componentes.Boton2();
        btnSeleccionarImagen = new Componentes.Boton2();
        pOWER1 = new Componentes.POWER();

        setBackground(new java.awt.Color(19, 3, 27));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbNivelRutina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Principiante", "Intermedio", "Avanzado" }));
        cmbNivelRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNivelRutinaActionPerformed(evt);
            }
        });
        add(cmbNivelRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        tblRutinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRutinasMouseClicked(evt);
            }
        });
        spRutinas.setViewportView(tblRutinas);

        add(spRutinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 510, 390));
        add(lblVistaRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 790, 530));

        btnGuardarRutina.setText("Guardar");
        btnGuardarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRutinaActionPerformed(evt);
            }
        });
        add(btnGuardarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, -1, -1));

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

    private void cmbNivelRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNivelRutinaActionPerformed
        cargarRutinas();
    }//GEN-LAST:event_cmbNivelRutinaActionPerformed

    private void tblRutinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRutinasMouseClicked

    }//GEN-LAST:event_tblRutinasMouseClicked

    private void btnGuardarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRutinaActionPerformed
        int fila = tblRutinas.getSelectedRow();
        // Verificar que haya una rutina seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una rutina de la tabla");
            return;
        }
        // Verificar que haya una imagen nueva
        if (imagenSeleccionada == null || imagenSeleccionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una imagen nueva");
            return;
        }
        int idRutina = Integer.parseInt(
                tblRutinas.getValueAt(fila, 0).toString()
        );
        String nombreRutina = imagenSeleccionada
                .replace(".png", "")
                .replace(".jpg", "")
                .replace(".jpeg", "")
                .replace("_", " ");
        DB_Rutinas db = new DB_Rutinas();
        boolean actualizado = db.actualizarImagenRutina(
                idRutina,
                nombreRutina,
                imagenSeleccionada
        );
        if (actualizado) {
            JOptionPane.showMessageDialog(this,
                    "Rutina actualizada correctamente");
            cargarRutinas();
            imagenSeleccionada = null;
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar la rutina");
        }
    }//GEN-LAST:event_btnGuardarRutinaActionPerformed

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
                    lblVistaRutina.getWidth(),
                    lblVistaRutina.getHeight(),
                    Image.SCALE_SMOOTH
            );
            lblVistaRutina.setIcon(new ImageIcon(imagen));
            System.out.println("Imagen seleccionada: " + imagenSeleccionada);
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed

    private void cargarRutinas() {
        DefaultTableModel modelo = tblRutinas.getModelo();
        modelo.setRowCount(0);
        String nivel = cmbNivelRutina.getSelectedItem().toString();
        System.out.println("Nivel seleccionado: " + nivel);
        DB_Rutinas dbRutinas = new DB_Rutinas();
        ResultSet rs = dbRutinas.obtenerRutinasPorNivel(nivel);
        try {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_rutina"),
                    rs.getString("nombre")
                });
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla: " + e.getMessage());
        }
    }

    private void mostrarImagenRutina() {
        int fila = tblRutinas.getSelectedRow();
        if (fila == -1) {
            return;
        }
        String nombre = tblRutinas.getValueAt(fila, 1).toString();
        lblVistaRutina.setText(nombre);
        int idRutina = Integer.parseInt(tblRutinas.getValueAt(fila, 0).toString());
        DB_Rutinas db = new DB_Rutinas();
        String imagen = db.obtenerImagenRutina(idRutina);
        lblVistaRutina.setToolTipText(imagen);
        java.net.URL ruta = getClass().getResource("/ImagenesR/" + imagen);
        if (ruta != null) {
            ImageIcon icono = new ImageIcon(ruta);
            Image img = icono.getImage().getScaledInstance(
                    lblVistaRutina.getWidth(),
                    lblVistaRutina.getHeight(),
                    Image.SCALE_SMOOTH
            );
            lblVistaRutina.setIcon(new ImageIcon(img));
        } else {
            lblVistaRutina.setIcon(null);
            System.out.println("No se encontró la imagen: " + imagen);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Componentes.Boton2 btnGuardarRutina;
    private Componentes.Boton2 btnSeleccionarImagen;
    private javax.swing.JComboBox<String> cmbNivelRutina;
    private javax.swing.JLabel lblVistaRutina;
    private Componentes.POWER pOWER1;
    private javax.swing.JScrollPane spRutinas;
    private Componentes.Tabla tblRutinas;
    // End of variables declaration//GEN-END:variables
}
