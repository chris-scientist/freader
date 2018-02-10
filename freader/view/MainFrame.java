
package freader.view;

import freader.model.Field;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * Fenêtre principale.
 * @author chris-scientist
 */
public class MainFrame extends javax.swing.JFrame {
    
    private FileTableModel data;
    
    private JFileChooser fileChooser;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        this.data = new FileTableModel();
        this.table.setModel(this.data);
        
        this.fileChooser = new JFileChooser();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addRowButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        removeRowButton = new javax.swing.JButton();
        readFileButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        addRowButton.setText("Ajouter une ligne");
        addRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowButtonActionPerformed(evt);
            }
        });

        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPane.setViewportView(table);

        removeRowButton.setText("Supprimer les lignes sélectionnées");
        removeRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRowButtonActionPerformed(evt);
            }
        });

        readFileButton.setText("Lire fichier");
        readFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readFileButtonActionPerformed(evt);
            }
        });

        openButton.setText("Ouvrir un fichier de description");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Sauvegarder");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addRowButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeRowButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(openButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(readFileButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRowButton)
                    .addComponent(removeRowButton)
                    .addComponent(readFileButton)
                    .addComponent(openButton)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowButtonActionPerformed
        this.data.addRow(new Field("", 0));
    }//GEN-LAST:event_addRowButtonActionPerformed

    private void removeRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowButtonActionPerformed
        int[] selectedRows = this.table.getSelectedRows();
        
        for(int i = selectedRows.length - 1 ;  i >= 0 ; i--) {
            this.data.removeRow(selectedRows[i]);
        }
    }//GEN-LAST:event_removeRowButtonActionPerformed

    private void readFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readFileButtonActionPerformed
        if(this.data.haveNoData()) {
            JOptionPane.showMessageDialog(this,
                "Vous devez préciser le format du fichier avant de le lire.",
                "Pas de donnée",
                JOptionPane.ERROR_MESSAGE);
        } else {
            int returnValue = this.fileChooser.showOpenDialog(this);

            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();

                DataFrame dataFrame = new DataFrame(this, this.data, file);
                this.setVisible(false);
                dataFrame.setVisible(true);
            }
        }
    }//GEN-LAST:event_readFileButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if(this.data.haveNoData()) {
            JOptionPane.showMessageDialog(this,
                "Vous devez décrire le fichier avant de pouvoir sauvegader sa description.",
                "Pas de donnée à sauvegarder",
                JOptionPane.ERROR_MESSAGE);
        } else {
            int returnValue = this.fileChooser.showSaveDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                List<Field> fields = this.data.getFieldSet();
                int nbField = fields.size();

                BufferedWriter bw;
                try {
                    bw = new BufferedWriter(new FileWriter(file));
                    bw.write(String.valueOf(nbField));
                    bw.newLine();
                    for(Field field : fields) {
                        bw.write(field.getName());
                        bw.newLine();
                        bw.write(String.valueOf(field.getSize()));
                        bw.newLine();
                    }
                    bw.close();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this,
                        e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        if(this.data.haveNoData()) {
            int returnValue = this.fileChooser.showOpenDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                
                
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    int nbField = Integer.parseInt(line);
                    for(int i = 0 ; i<nbField ; i++) {
                        String name = br.readLine();
                        String size = br.readLine();
                        this.data.addRow(new Field(name, Integer.valueOf(size)));
                    }
                    br.close();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this,
                        e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Supprimez les données avant d'en importer.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRowButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton readFileButton;
    private javax.swing.JButton removeRowButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}