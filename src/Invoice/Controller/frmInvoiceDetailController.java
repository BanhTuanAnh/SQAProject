/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Controller;
import Main.View.frmMain;
import ClockDetail.Controller.InsertUpdateClockController;
import ClockDetail.View.frmClockDetail;
import ClockDetail.View.frmInsertUpdateClockIndex;
import Invoice.View.frmInvoiceDetail;
import Model.DAO.ClockDetailUltil;
import Model.Entity.Customer;
import Model.Entity.ElectricIndex;
import Model.Entity.Invoice;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class frmInvoiceDetailController extends BaseClass.BaseController{

    private frmMain frmParent;
    private frmInvoiceDetail frmInvoiceDetail;
    private Invoice invoice = null;
    private int mode;
    public boolean success;
    private Customer customer;

    public frmInvoiceDetailController(frmMain frmParent, Invoice invoice, Customer customer) {
        this.frmInvoiceDetail = new frmInvoiceDetail(frmParent, true);
        this.frmParent  = frmParent;
        this.innitEvent();
        if (invoice != null) {
            this.invoice = invoice;
        }
        if (customer != null) {
            this.customer = customer;
        }
        this.innitView();
    }

    public frmInvoiceDetailController(frmMain frmParent, Customer customer) {
        this.frmParent  = frmParent;
        this.frmInvoiceDetail = new frmInvoiceDetail(frmParent, true);
        this.customer = customer;
        this.innitEvent();
        invoice = null;
        this.innitView();

    }

    public void showForm(boolean visible, frmClockDetail frmParent) {

        this.frmInvoiceDetail.setLocationRelativeTo(frmParent);
        frmInvoiceDetail.setVisible(visible);
    }
    public void showForm(boolean visible) {
        frmInvoiceDetail.setVisible(visible);
    }

    @Override
    public void innitEvent() {
        this.frmInvoiceDetail.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frmInvoiceDetail.setVisible(false);
            }
        });
        this.frmInvoiceDetail.getBtnSave().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnSaveMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(InsertUpdateClockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
         this.frmInvoiceDetail.getBtnCancel().addMouseListener(new java.awt.event.MouseAdapter() {
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
//        this.invoice = new Invoice();
//        invoice.setCustomerID((long) this.frmInvoiceDetail.getNumClockIndexID().getValue());
//        invoice.setClockIndex((long)this.frmInsertUpdateClockIndex.getNumClockIndex().getValue());
//        Calendar cal = Calendar.getInstance();
//        Date date = (Date) this.frmInsertUpdateClockIndex.getdteCreatedDate().getValue();
//        electricIndex.setCreatedDate(date);
//        cal.setTime(date);
////        int month = cal.get(Calendar.MONTH) + 1;
////        int year = cal.get(Calendar.YEAR);
//        electricIndex.setNumYear((long) this.frmInsertUpdateClockIndex.getNumYear().getValue());
//        int month = this.frmInsertUpdateClockIndex.getCboMonth().getSelectedIndex()+1;
//        electricIndex.setNumMonth(month);
//        electricIndex.setClockCode(this.frmInsertUpdateClockIndex.getTxtClockCode().getText());
//        if(ClockDetailUltil.InsertUpdateClockDetail(electricIndex) > 0){
//            JOptionPane.showMessageDialog(frmInsertUpdateClockIndex, "Cất thành công");
//            this.success = true;
//            this.setEditAbleLayout(false);
//        }else{
//            this.success =false;
//             JOptionPane.showMessageDialog(frmInsertUpdateClockIndex, "Thông tin số điện sai");
//             
//        
//        }
            
    };
    @Override
    public void innitView() {
//        
//        this.frmInvoiceDetail.getNumClockIndexID().setVisible(false);
//        if(this.invoice  != null){
//            this.frmInsertUpdateClockIndex.getNumYear().setValue(electricIndex.getNumMonth());
//            this.frmInsertUpdateClockIndex.getdteCreatedDate().setValue(electricIndex.getCreatedDate());
//            this.frmInsertUpdateClockIndex.getNumClockIndex().setValue(electricIndex.getClockIndex());
//            this.frmInsertUpdateClockIndex.getNumClockIndexID().setValue(electricIndex.getClockDetailID());
//            this.frmInsertUpdateClockIndex.getCboMonth().setSelectedIndex((int)electricIndex.getNumMonth() - 1);
//            this.frmInsertUpdateClockIndex.getTxtClockCode().setText(electricIndex.getClockCode());
//        }else{
//              this.frmInsertUpdateClockIndex.getNumClockIndexID().setValue(-1);
//        this.frmInsertUpdateClockIndex.getTxtClockCode().setText(this.customer.getClockCode());
//        long millis=System.currentTimeMillis();  
//        Date date=new java.sql.Date(millis);
//        Calendar calendar = Calendar.getInstance();
//        this.frmInsertUpdateClockIndex.getdteCreatedDate().setValue(date);
//        this.frmInsertUpdateClockIndex.getNumYear().setValue(calendar.get(Calendar.YEAR));
//         this.frmInsertUpdateClockIndex.getCboMonth().setSelectedIndex(calendar.get(Calendar.MONTH));
//        }
      
    }

    private void setEditAbleLayout(boolean b) {
//          this.frmInsertUpdateClockIndex.getNumClockIndexID().setEditable(b);
//       this.frmInsertUpdateClockIndex.getNumClockIndex().setEditable(b);
//       this.frmInsertUpdateClockIndex.getTxtClockCode().setEditable(b);
//     this.frmInsertUpdateClockIndex.getdteCreatedDate().setEditable(b);
//     this.frmInsertUpdateClockIndex.getCboMonth().setEditable(b);
    }
}
