/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClockDetail.View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author admin
 */
public class frmClockDetail extends javax.swing.JFrame {

    /**
     * Creates new form frmClockDetail
     */
    public frmClockDetail() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formDialog = new javax.swing.JDialog();
        jToolBar1 = new javax.swing.JToolBar();
        btnUpdateEI = new javax.swing.JButton();
        btnAddEI = new javax.swing.JButton();
        btnDeleteEI = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClockDetail = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        lblCustomerName = new javax.swing.JLabel();

        javax.swing.GroupLayout formDialogLayout = new javax.swing.GroupLayout(formDialog.getContentPane());
        formDialog.getContentPane().setLayout(formDialogLayout);
        formDialogLayout.setHorizontalGroup(
            formDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        formDialogLayout.setVerticalGroup(
            formDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnUpdateEI.setText("Cập nhật số điện");
        btnUpdateEI.setFocusable(false);
        btnUpdateEI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUpdateEI.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnUpdateEI);

        btnAddEI.setText("Thêm số điện");
        btnAddEI.setFocusable(false);
        btnAddEI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddEI.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddEI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddEIMouseClicked(evt);
            }
        });
        jToolBar1.add(btnAddEI);

        btnDeleteEI.setText("Xóa số điện");
        btnDeleteEI.setFocusable(false);
        btnDeleteEI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeleteEI.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnDeleteEI);

        btnBack.setText("Trở lại");
        btnBack.setFocusable(false);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnBack);

        tblClockDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "123", "9", "900000", "Đã thanh toán"},
                {"2", "133", "10", "120000", "Chưa thanh toán"},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tháng", "Chỉ số công tơ", "Số KWH", "Số tiền phải đóng", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tblClockDetail);

        jButton1.setText("Tìm kiếm");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Trạng thái"}));

        lblCustomerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCustomerName.setText("Bành Tuấn Anh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(lblCustomerName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddEIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddEIMouseClicked
     
    }//GEN-LAST:event_btnAddEIMouseClicked

    public JLabel getLblCustomerName() {
        return lblCustomerName;
    }

    public void setLblCustomerName(JLabel lblCustomerName) {
        this.lblCustomerName = lblCustomerName;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    public JTable getTblClockDetail() {
        return tblClockDetail;
    }

    public void setTblClockDetail(JTable tblClockDetail) {
        this.tblClockDetail = tblClockDetail;
    }

    public JButton getBtnAddEI() {
        return btnAddEI;
    }

    public void setBtnAddEI(JButton btnAddEI) {
        this.btnAddEI = btnAddEI;
    }

    public JButton getBtnDeleteEI() {
        return btnDeleteEI;
    }

    public void setBtnDeleteEI(JButton btnDeleteEI) {
        this.btnDeleteEI = btnDeleteEI;
    }

    public JButton getBtnUpdateEI() {
        return btnUpdateEI;
    }

    public void setBtnUpdateEI(JButton btnUpdateEI) {
        this.btnUpdateEI = btnUpdateEI;
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEI;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDeleteEI;
    private javax.swing.JButton btnUpdateEI;
    private javax.swing.JDialog formDialog;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JTable tblClockDetail;
    // End of variables declaration//GEN-END:variables
}
