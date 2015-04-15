/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Application.Procesador;
import Application.VentanaDelegador;
import static java.awt.image.ImageObserver.WIDTH;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Fabricio Simoncelli
 */
public final class VocabularioGUI extends javax.swing.JFrame {
    
    private VentanaDelegador delegador = new VentanaDelegador();
    private Procesador procesador = new Procesador();
    private File[] files;
    private Worker worker;
    private List<String> filesReady = new ArrayList<>();
    private List<String> filesDone = new ArrayList<>();
    private static int cantidadDePalabras;
    
    public VocabularioGUI() {
        try {
            files = null;
            initComponents();
            cargarDocumentosProcesados();
            cargarTabla();
            txtCantPalabras.setText(cantidadDePalabras + "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VocabularioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Palabras = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_buscador = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtp_docAProcesar = new javax.swing.JTextPane();
        txt_enProceso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtp_docProcesados = new javax.swing.JTextPane();
        barraProgreso = new javax.swing.JProgressBar();
        btnCargarArchivos = new javax.swing.JButton();
        btnLimpiarBD = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCantPalabras = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vocabulario - TP_TSB 2014 - Fabricio Simoncelli - 60026_3k4 ");
        setPreferredSize(new java.awt.Dimension(841, 500));
        setResizable(false);

        tabla_Palabras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Palabra", "Frecuencia", "Cantidad de Documentos"
            }
        ));
        tabla_Palabras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_PalabrasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_Palabras);
        if (tabla_Palabras.getColumnModel().getColumnCount() > 0) {
            tabla_Palabras.getColumnModel().getColumn(0).setResizable(false);
            tabla_Palabras.getColumnModel().getColumn(1).setResizable(false);
            tabla_Palabras.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setText("Palabra a buscar:");

        txt_buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscadorKeyReleased(evt);
            }
        });

        jScrollPane2.setViewportView(txtp_docAProcesar);

        jLabel2.setText("Procesando:");

        jLabel3.setText("Documentos a Procesar");

        jLabel4.setText("Documentos Procesados");

        jScrollPane3.setViewportView(txtp_docProcesados);

        btnCargarArchivos.setText("Cargar Archivos");
        btnCargarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivosActionPerformed(evt);
            }
        });

        btnLimpiarBD.setText("Limpiar BD");
        btnLimpiarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarBDActionPerformed(evt);
            }
        });

        jLabel5.setText("Cantidad total de palabras:");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_enProceso))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarArchivos)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarBD)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCantPalabras, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_buscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargarArchivos)
                            .addComponent(btnLimpiarBD))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCantPalabras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_enProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscadorKeyReleased
        String patron = txt_buscador.getText().toUpperCase();
        TableModel modelo = tabla_Palabras.getModel();
        TableRowSorter sorter = new TableRowSorter(modelo);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + patron + ".*"));
        tabla_Palabras.setRowSorter(sorter);
        tabla_Palabras.repaint();
    }//GEN-LAST:event_txt_buscadorKeyReleased

    private void btnCargarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivosActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return (f.isFile() && f.getName().endsWith(".txt") || f.isDirectory());
            }
            
            @Override
            public String getDescription() {
                return "Archivos de Texto";
            }
        });
        
        if (fc.showDialog(this, "Cargar") != JFileChooser.CANCEL_OPTION) {
            files = null;
            files = fc.getSelectedFiles();
            for (File file : files) {
                filesReady.add(file.getName());
            }
            cargarArchivosListos();
            btnCargarArchivos.setEnabled(false);
            btnLimpiarBD.setEnabled(false);
            
            this.worker = new Worker();
            worker.addPropertyChangeListener(
                    new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if ("progress".equals(evt.getPropertyName())) {
                                barraProgreso.setValue((Integer) evt.getNewValue());
                            }
                        }
                    });
            worker.execute(); // invoca al m�todo doInBackgrond()
            if (worker.isDone()) {
                btnCargarArchivos.setEnabled(true);
                btnLimpiarBD.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnCargarArchivosActionPerformed

    private void btnLimpiarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarBDActionPerformed
        try {
            delegador.limpiarBaseDeDatos();
            txt_buscador.setText("");
            filesDone.clear();
            txtp_docProcesados.setText("");
            cargarTabla();
            txtCantPalabras.setText("");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VocabularioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarBDActionPerformed

    private void tabla_PalabrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_PalabrasMouseClicked
        try {
            String palabra = (String) tabla_Palabras.getValueAt(tabla_Palabras.getSelectedRow(), 0);
            List<String> documentos = delegador.getDocumentosXPalabra(palabra);
            if (documentos.size() > 1) {
                JOptionPane.showMessageDialog(rootPane, "Pertenece a los siguientes documentos: " + "\n" + documentos, palabra, WIDTH);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Pertenece al siguiente documento: " + "\n" + documentos, palabra, WIDTH);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VocabularioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla_PalabrasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VocabularioGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VocabularioGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VocabularioGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VocabularioGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VocabularioGUI().setVisible(true);
            }
        });
    }
    
    public void cargarTabla() throws ClassNotFoundException, SQLException {
        tabla_Palabras.setAutoCreateRowSorter(true);
        tabla_Palabras.setModel(delegador.getDatosTablas());
        tabla_Palabras.repaint();
    }
    
    private void cargarArchivosListos() {
        txtp_docAProcesar.setText("");
        for (String filesReady1 : filesReady) {
            txtp_docAProcesar.setText(filesReady1 + "\n" + txtp_docAProcesar.getText());
        }
    }
    
    private void cargarProcesados() {
        txtp_docProcesados.setText("");
        for (String filesDone1 : filesDone) {
            txtp_docProcesados.setText(filesDone1 + "\n" + txtp_docProcesados.getText());
        }
    }
    
    private void cargarDocumentosProcesados() throws SQLException, ClassNotFoundException {
        if (filesDone.addAll(delegador.getDocumentos())) {
            cargarProcesados();
        }
    }
    
    private class Worker extends SwingWorker<String, Void> {
        
        @Override
        protected String doInBackground() throws Exception {
            int i = 0;
            for (File file : files) {
                txt_enProceso.setText(file.getName());
                Map m = procesador.procesar(file.getName(), file.getAbsolutePath());
                delegador.insertarBD(m, file.getName());
                cargarTabla();
                filesDone.add(filesReady.get(0));
                filesReady.remove(0);
                cargarArchivosListos();
                cargarProcesados();
                cantidadDePalabras += m.size();
                txtCantPalabras.setText(cantidadDePalabras + "");
                setProgress(100 * i / files.length);
            }
            Thread.sleep(100);
            return "";
        }
        
        @Override
        protected void done() {
            try {
                txt_enProceso.setText(get());
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(VocabularioGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            btnCargarArchivos.setEnabled(true);
            btnLimpiarBD.setEnabled(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton btnCargarArchivos;
    private javax.swing.JButton btnLimpiarBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_Palabras;
    private javax.swing.JTextField txtCantPalabras;
    private javax.swing.JTextField txt_buscador;
    private javax.swing.JTextField txt_enProceso;
    private javax.swing.JTextPane txtp_docAProcesar;
    private javax.swing.JTextPane txtp_docProcesados;
    // End of variables declaration//GEN-END:variables
}
