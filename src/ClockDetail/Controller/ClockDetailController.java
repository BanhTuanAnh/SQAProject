/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClockDetail.Controller;
import Main.Controller.frmMainController;
import BaseClass.BaseController;
import ClockDetail.View.frmClockDetail;
import Customer.View.pnlCustomer;
import Model.DAO.ClockDetailUltil;
import Model.DAO.ClockUltil;
import Model.Entity.Customer;
import Model.Entity.ElectricIndex;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ClockDetailController extends BaseController {

    private Customer customer;
    private frmClockDetail frmClockDetail;
    private List<ElectricIndex> list;
    public void showForm(boolean visible) {
        frmClockDetail.setLocationRelativeTo(null);
        frmClockDetail.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmClockDetail.setVisible(visible);
    }

    public ClockDetailController(Customer customer) {
        frmClockDetail = new frmClockDetail();
        this.customer = customer;
        innitEvent();
        innitView();
    }

    @Override
    public void innitEvent() {
        this.frmClockDetail.getBtnAddEI().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddEIMouseClicked(evt);
            }

        });
        this.frmClockDetail.getBtnUpdateEI().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnEditEIMouseClicked(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
                this.frmClockDetail.getBtnBack().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }

        });
        this.frmClockDetail.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frmClockDetail.setVisible(false);
            }
        });
        this.frmClockDetail.getBtnDeleteEI().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnDeleteEIClicked(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }
    private void btnDeleteEIClicked(MouseEvent evt) throws SQLException, ClassNotFoundException{
        int index =this.frmClockDetail.getTblClockDetail().getSelectedRow();
        
        Object[] options = {"Có","Không"};
        int n = JOptionPane.showOptionDialog(frmClockDetail, 
                "Bạn có muốn xóa chỉ số " + this.list.get(index).getStrMonth()+ ": " + this.list.get(index).getClockIndex() ,
                "Xác nhận",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if(n == 0 ){
            long id =this.list.get(index).getClockDetailID();
            int rowAffected = ClockDetailUltil.DeleteElectricIndexByID(id);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(frmClockDetail,"Xóa thành công");
                this.innitView();
            }
            else{
                 JOptionPane.showMessageDialog(frmClockDetail,"Xóa thất bại");
            }
        }
    }
    private void btnBackMouseClicked(MouseEvent evt) {
       frmMainController frmMainController = new frmMainController();
                    frmMainController.showForm(true);
                    this.showForm(false);
    }

    private void btnAddEIMouseClicked(MouseEvent evt){
        InsertUpdateClockController dialog = new InsertUpdateClockController(frmClockDetail,customer);
        dialog.showForm(true, frmClockDetail);
        if (dialog.success) {
            this.innitView();
        }
    }

    private void btnEditEIMouseClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {
        int index=frmClockDetail.getTblClockDetail().getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(frmClockDetail,"Bạn phải chọn 1 chỉ số");
        }
        else {
        ElectricIndex eIndex = ClockDetailUltil.GetElectricIndexByID(list.get(index).getClockDetailID());
        InsertUpdateClockController dialog = new InsertUpdateClockController(frmClockDetail,eIndex,customer);
        dialog.showForm(true, frmClockDetail);
        if (dialog.success) {
            this.innitView();
        }
        }
        
    }

    @Override
    public void innitView() {
        if (this.customer != null) {
            this.frmClockDetail.getLblCustomerName().setText(customer.getName());
        } else {
            this.frmClockDetail.getLblCustomerName().setText("");
        }
        long customerID = customer.getId();
       

        try {
            list = ClockUltil.GetElectricIndexsByCustomer(customerID);
            this.frmClockDetail.getTblClockDetail().setModel(this.fillTable(list, new ElectricIndex()));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
