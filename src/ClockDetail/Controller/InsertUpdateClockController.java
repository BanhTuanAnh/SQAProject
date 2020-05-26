/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClockDetail.Controller;

import ClockDetail.View.frmClockDetail;
import ClockDetail.View.frmInsertUpdateClockIndex;
import Model.DAO.ClockDetailUltil;
import Model.Entity.Customer;

import Model.Entity.ElectricIndex;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class InsertUpdateClockController extends BaseClass.BaseController {
    private frmClockDetail frmClockDetail;
    private frmInsertUpdateClockIndex frmInsertUpdateClockIndex;
    private ElectricIndex electricIndex = null;
    private int mode;
    public boolean success;
    private Customer customer;

    public InsertUpdateClockController(frmClockDetail frmParent, ElectricIndex electricIndex, Customer customer) {
        this.frmInsertUpdateClockIndex = new frmInsertUpdateClockIndex(frmParent, true);
        this.frmClockDetail  = frmParent;
        this.innitEvent();
        if (electricIndex != null) {
            this.electricIndex = electricIndex;
        }
        if (customer != null) {
            this.customer = customer;
        }
        this.innitView();
    }

    public InsertUpdateClockController(frmClockDetail frmParent,Customer customer) {
        this.frmClockDetail  = frmParent;
        this.frmInsertUpdateClockIndex = new frmInsertUpdateClockIndex(frmParent, true);
        this.customer = customer;
        this.innitEvent();
        electricIndex = null;
        this.innitView();

    }

    public void showForm(boolean visible, frmClockDetail frmParent) {

        this.frmInsertUpdateClockIndex.setLocationRelativeTo(frmParent);
        frmInsertUpdateClockIndex.setVisible(visible);
    }
    public void showForm(boolean visible) {
        frmInsertUpdateClockIndex.setVisible(visible);
    }

    @Override
    public void innitEvent() {
        this.frmInsertUpdateClockIndex.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frmInsertUpdateClockIndex.setVisible(false);
            }
        });
        this.frmInsertUpdateClockIndex.getBtnSave().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnSaveMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(InsertUpdateClockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
         this.frmInsertUpdateClockIndex.getBtnCancel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnCancelClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(InsertUpdateClockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        

    }
     private void btnCancelClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {
         showForm(false);
     }

    private void btnSaveMouseClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {

        this.electricIndex = new ElectricIndex();
        electricIndex.setClockDetailID((int) this.frmInsertUpdateClockIndex.getNumClockIndexID().getValue());
        electricIndex.setClockIndex((long)this.frmInsertUpdateClockIndex.getNumClockIndex().getValue());
        Calendar cal = Calendar.getInstance();
        Date date = (Date) this.frmInsertUpdateClockIndex.getdteCreatedDate().getValue();
        electricIndex.setCreatedDate(date);
        cal.setTime(date);
//        int month = cal.get(Calendar.MONTH) + 1;
//        int year = cal.get(Calendar.YEAR);
        electricIndex.setNumYear((int) this.frmInsertUpdateClockIndex.getNumYear().getValue());
        int month = this.frmInsertUpdateClockIndex.getCboMonth().getSelectedIndex()+1;
        electricIndex.setNumMonth(month);
        electricIndex.setClockCode(this.frmInsertUpdateClockIndex.getTxtClockCode().getText());
        if(ClockDetailUltil.InsertUpdateClockDetail(electricIndex) > 0){
            JOptionPane.showMessageDialog(frmInsertUpdateClockIndex, "Cất thành công");
            this.success = true;
            this.setEditAbleLayout(false);
        }else{
            this.success =false;
             JOptionPane.showMessageDialog(frmInsertUpdateClockIndex, "Thông tin số điện sai");
             
        
        }
            
    };
    @Override

    public void innitView() {
        
        this.frmInsertUpdateClockIndex.getNumClockIndexID().setVisible(false);
        if(this.electricIndex != null){
            this.frmInsertUpdateClockIndex.getNumYear().setValue(electricIndex.getNumYear());
            this.frmInsertUpdateClockIndex.getdteCreatedDate().setValue(electricIndex.getCreatedDate());
            this.frmInsertUpdateClockIndex.getNumClockIndex().setValue(electricIndex.getClockIndex());
            this.frmInsertUpdateClockIndex.getNumClockIndexID().setValue(electricIndex.getClockDetailID());
            this.frmInsertUpdateClockIndex.getCboMonth().setSelectedIndex((int)electricIndex.getNumMonth() - 1);
            this.frmInsertUpdateClockIndex.getTxtClockCode().setText(electricIndex.getClockCode());
        }else{
              this.frmInsertUpdateClockIndex.getNumClockIndexID().setValue((int)-1);
        this.frmInsertUpdateClockIndex.getTxtClockCode().setText(this.customer.getClockCode());
        long millis=System.currentTimeMillis();  
        Date date=new java.sql.Date(millis);
        Calendar calendar = Calendar.getInstance();
        this.frmInsertUpdateClockIndex.getdteCreatedDate().setValue(date);
        this.frmInsertUpdateClockIndex.getNumYear().setValue(calendar.get(Calendar.YEAR));
         this.frmInsertUpdateClockIndex.getCboMonth().setSelectedIndex(calendar.get(Calendar.MONTH));
        }
      
    }

    private void setEditAbleLayout(boolean b) {
          this.frmInsertUpdateClockIndex.getNumClockIndexID().setEditable(b);
       this.frmInsertUpdateClockIndex.getNumClockIndex().setEditable(b);
       this.frmInsertUpdateClockIndex.getTxtClockCode().setEditable(b);
        this.frmInsertUpdateClockIndex.getNumYear().setEditable(b);
     this.frmInsertUpdateClockIndex.getdteCreatedDate().setEditable(b);
     this.frmInsertUpdateClockIndex.getCboMonth().setEditable(b);
    }

}
