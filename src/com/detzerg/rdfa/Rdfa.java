package com.detzerg.rdfa;
/**
 *
 * @author Detzer
 */
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class Rdfa extends javax.swing.JFrame {

    private final String MODEL = "web.rdf";
   
    public Rdfa() {
        System.out.println("Iniciando Procesamiento");
        RFAController r = new RFAController();               
        // File process
        //initComponents();
        
        if(r.processFile()) {
            System.out.println("Conversión completa!");
            initComponents();
        } else {
            System.out.println("Ocurrió un error en el proceso de conversión, revise el archivo HTML");
        }
        FileManager.get().addLocatorClassLoader(Rdfa.class.getClassLoader());
    }

    
    public void show(String cadena){
        Model model = FileManager.get().loadModel(this.MODEL);
        
        String header = "prefix dc: <http://purl.org/dc/elements/1.1/> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";

        System.out.println("CONSULTA >> \n"+cadena);
        
        cadena = header + " " + cadena;
                      
        Query query = QueryFactory.create(cadena);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
	try {
            ResultSet results = qe.execSelect();
            String values = ResultSetFormatter.asText(results);           
            txt.setText(values.replace("@", "").replace("\"", ""));
        } finally { qe.close(); }
    }
    
    public void onChange() {
        if(rdMobile.isSelected()) {
            filterByPlatform("mobile");
        }
        
        if(rdps4.isSelected()) {
            filterByPlatform("ps4");
        }
        
        if(rdpc.isSelected()) {
            filterByPlatform("pc");
        }
        
        if(rdxbox.isSelected()) {
            filterByPlatform("xbox");
        }
    }
    
    public void filterByPlatform(String type){
        String cadena = 
            "select ?title ?price ?desc where {"
            + "?a dc:title ?title; "
            + " dc:price ?price; "
            + " dc:desc ?desc; "
            + " dc:"+type+" ?platform "        
            + " filter( str(?platform) = \"yes\" )"                           
            + "}";

        this.show(cadena);
    }
    
    public void filterByYear() {
        String year = txtYear.getText();

        if(year.trim().equals("")){
            return;
        }

        String cadena =
            "select ?year ?title where {"
            + "?a dc:year ?year. "
            + "?a dc:title ?title"
            + " filter( str(?year) = '"+year+"' )"
            + "}";

        this.show(cadena);
    }
    
    public void filterByEngine(String engine) {


        if(engine.trim().equals("")){
            return;
        }

        String cadena = 
            "select ?title ?price ?desc where {"
            + "?a dc:title ?title. "
            + "?a dc:price ?price. "
            + "?a dc:desc ?desc. "
            + "?a dc:engine ?engine. "
            + " filter( regex(?engine, '"+engine+"', 'i') )"                           
            + "}";

        this.show(cadena);
    }
    
    public void filterByPrice(String StringPrice, String operator) {
        
       if(StringPrice.trim().equals("")) return;
        
        Double price = Double.parseDouble(StringPrice);
        
         String cadena = 
            "select ?title ?price ?desc where {"
            + "?a dc:title ?title; "
            + " dc:price ?price; "
            + " dc:desc ?desc; "
            + " filter( ?price "+operator+" \""+price+"\" )"                           
            + "}";         
        
        
        this.show(cadena);
    }
    
    public void filterByDesc(String StringDesc, String operator) {
        
       if(StringDesc.trim().equals("")) return;
        
        int price = Integer.parseInt(StringDesc);
        
         String cadena = 
            "select ?title ?price ?desc where {"
            + "?a dc:title ?title; "
            + " dc:price ?price; "
            + " dc:desc ?desc; "
            + " filter( ?desc "+operator+" \""+price+"\" )"                           
            + "}";         
        
        
        this.show(cadena);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSearchName = new javax.swing.JButton();
        txtYear = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEngine = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDescMayor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPriceMenor = new javax.swing.JTextField();
        txtDescMenor = new javax.swing.JTextField();
        txtPriceMayor = new javax.swing.JFormattedTextField();
        rdps4 = new javax.swing.JRadioButton();
        rdpc = new javax.swing.JRadioButton();
        rdMobile = new javax.swing.JRadioButton();
        rdxbox = new javax.swing.JRadioButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HTML+RDFA - Web Semantica ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VideoGames - Ofertas");

        jLabel2.setText("Por nombre: ");

        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Busquedas");

        btnSearchName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/detzerg/rdfa/send.png"))); // NOI18N
        btnSearchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNameActionPerformed(evt);
            }
        });

        txtYear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy"))));
        txtYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtYearKeyPressed(evt);
            }
        });

        jLabel4.setText("Lanzamiento: ");

        jLabel5.setText("Motor: ");

        txtEngine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unreal Engine", "SandBox", "Treyarch NGL", "Garena Games", "Gamestriks", "GameCube", "Overtras Games", "Game Horizon", "Fandango Games", "Pitfalliz Games", "Fleischer Studios", "GameNintendo" }));
        txtEngine.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtEngineItemStateChanged(evt);
            }
        });

        jLabel6.setText("Precio >:  ");

        jLabel7.setText("% Desc. >: ");

        txtDescMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescMayorKeyPressed(evt);
            }
        });

        jLabel8.setText("Precio <:  ");

        jLabel9.setText("% Desc. <: ");

        txtPriceMenor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceMenorKeyPressed(evt);
            }
        });

        txtDescMenor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescMenorKeyPressed(evt);
            }
        });

        txtPriceMayor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPriceMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceMayorKeyPressed(evt);
            }
        });

        buttonGroup1.add(rdps4);
        rdps4.setText("PS4");
        rdps4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdps4StateChanged(evt);
            }
        });

        buttonGroup1.add(rdpc);
        rdpc.setText("PC");
        rdpc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdpcStateChanged(evt);
            }
        });

        buttonGroup1.add(rdMobile);
        rdMobile.setText("Movil");
        rdMobile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdMobileStateChanged(evt);
            }
        });

        buttonGroup1.add(rdxbox);
        rdxbox.setText("XBox");
        rdxbox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdxboxStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchName))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtYear, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDescMayor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(txtPriceMayor))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEngine, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtDescMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdpc, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdxbox))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtPriceMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdps4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 8, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtEngine, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPriceMayor, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDescMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(rdpc)
                            .addComponent(rdxbox))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPriceMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(rdps4)
                            .addComponent(rdMobile))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchNameActionPerformed
        String name = txtName.getText().trim();
        String cadena = 
            "select ?title ?price ?desc where {"
            + "?a dc:title ?title; "
            + " dc:price ?price; "
            + " dc:desc ?desc "
            + " filter( regex(?title, '"+name+"', 'i') )"                           
            + "}";
        
        
        this.show(cadena);
        
    }//GEN-LAST:event_btnSearchNameActionPerformed

    private void txtYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYearKeyPressed
         if (evt.getKeyCode() ==  10) {
             filterByYear();
         }
    }//GEN-LAST:event_txtYearKeyPressed

    private void txtEngineItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtEngineItemStateChanged
       String engine = evt.getItem().toString();
       filterByEngine(engine);              
    }//GEN-LAST:event_txtEngineItemStateChanged

    private void txtPriceMayorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceMayorKeyPressed
        if(evt.getKeyCode() == 10) {            
            this.filterByPrice(txtPriceMayor.getText(), ">=");                
        }           
    }//GEN-LAST:event_txtPriceMayorKeyPressed

    private void txtDescMayorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescMayorKeyPressed
         if(evt.getKeyCode() == 10) {            
            this.filterByDesc(txtDescMayor.getText(), ">=");                
        } 
    }//GEN-LAST:event_txtDescMayorKeyPressed

    private void txtPriceMenorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceMenorKeyPressed
        if(evt.getKeyCode() == 10) {            
            this.filterByPrice(txtPriceMenor.getText(), "<=");                
        } 
    }//GEN-LAST:event_txtPriceMenorKeyPressed

    private void txtDescMenorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescMenorKeyPressed
        if(evt.getKeyCode() == 10) {            
            this.filterByDesc(txtDescMenor.getText(), "<=");                
        } 
    }//GEN-LAST:event_txtDescMenorKeyPressed

    private void rdMobileStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdMobileStateChanged
        this.onChange();
    }//GEN-LAST:event_rdMobileStateChanged

    private void rdps4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdps4StateChanged
       onChange();
    }//GEN-LAST:event_rdps4StateChanged

    private void rdpcStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdpcStateChanged
      onChange();
    }//GEN-LAST:event_rdpcStateChanged

    private void rdxboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdxboxStateChanged
    onChange();
    }//GEN-LAST:event_rdxboxStateChanged

    

    public static void main(String args[]) {        
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
            java.util.logging.Logger.getLogger(Rdfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rdfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rdfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rdfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rdfa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdMobile;
    private javax.swing.JRadioButton rdpc;
    private javax.swing.JRadioButton rdps4;
    private javax.swing.JRadioButton rdxbox;
    private javax.swing.JTextArea txt;
    private javax.swing.JTextField txtDescMayor;
    private javax.swing.JTextField txtDescMenor;
    private javax.swing.JComboBox<String> txtEngine;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtPriceMayor;
    private javax.swing.JTextField txtPriceMenor;
    private javax.swing.JFormattedTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
