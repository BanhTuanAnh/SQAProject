/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer.Controller;

import BaseClass.BaseController;
import ClockDetail.Controller.ClockDetailController;
import ClockDetail.Controller.InsertUpdateClockController;
import Customer.View.pnlCustomer;
import Main.View.frmMain;
import Model.DAO.AccountObjectUltil;
import Model.DAO.ClockDetailUltil;
import Model.DAO.ClockUltil;
import Model.Entity.ClockDetail;
import Model.Entity.Customer;
import Model.Entity.ElectricIndex;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class pnlCustomerController extends BaseController {
    private frmMain frmParent;
    private pnlCustomer pnlCustomer;
    private List<Customer> customers;

    public pnlCustomerController(frmMain frmParent) {
         this.frmParent=frmParent;
        this.pnlCustomer = new pnlCustomer();
        this.frmParent= null;
        customers = new ArrayList<>();
        innitEvent();
        innitView();
    }

    public pnlCustomerController(pnlCustomer pnlCustomer,frmMain frmParent) {
        this.frmParent=frmParent;
        this.pnlCustomer = pnlCustomer;
        innitEvent();
        innitView();
    }

    public void showForm(boolean visible) {
        pnlCustomer.setVisible(visible);
    }

    @Override
    public void innitEvent() {
        this.pnlCustomer.getBtnDeleteContract().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnDeleteCustomerClicked(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        this.pnlCustomer.getBtnAddContract().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddContractClicked(evt);
            }

        });
        this.pnlCustomer.getBtnEditContract().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnEditContractMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(pnlCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        this.pnlCustomer.getBtnViewElectricIndex().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewElectricIndexMouseClicked(evt);
            }
        });
        this.pnlCustomer.getTblCustomer().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    tblCustomerMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(pnlCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
    }
    private void btnEditContractMouseClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {
        int index=this.pnlCustomer.getTblCustomer().getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(pnlCustomer,"Bạn phải chọn 1 khách hàng");
        }
        else {
        Customer customer = AccountObjectUltil.GetCustomerByID(customers.get(index).getId());
         frmCustomerDetailController detailController = new frmCustomerDetailController(customer,this.frmParent);
        detailController.showForm(true,this.frmParent);
        if (detailController.success) {
            this.innitView();
        }
        }
        
    }
    private void btnViewElectricIndexMouseClicked(MouseEvent evt) {
        int selectedIndex = this.pnlCustomer.getTblCustomer().getSelectedRow();
        if (selectedIndex >= 0) {
            Customer customer = customers.get(selectedIndex);
            ClockDetailController clockDetailController = new ClockDetailController(customer);
            clockDetailController.showForm(true);
        }
    }

    @Override
    public void innitView() {
        try {
            customers = AccountObjectUltil.GetCustomers();
            DefaultTableModel model = this.fillTable(customers, new Customer());
            this.pnlCustomer.getTblCustomer().setModel(model);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(pnlCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) throws SQLException, ClassNotFoundException {
        int selectedIndex = this.pnlCustomer.getTblCustomer().getSelectedRow();
        long customerID = customers.get(selectedIndex).getId();
        List<ClockDetail> clockDetails = new ArrayList<>();
        clockDetails = ClockUltil.GetCloclDetails(customerID);
        this.pnlCustomer.getTblClockDetail().setModel(this.fillTable(clockDetails, new ClockDetail()));
    }
     private void btnDeleteCustomerClicked(MouseEvent evt) throws SQLException, ClassNotFoundException{
        int index =this.pnlCustomer.getTblCustomer().getSelectedRow();
        
        Object[] options = {"Có","Không"};
        int n = JOptionPane.showOptionDialog(pnlCustomer, 
                "Bạn có muốn xóa hợp đồng với khách hàng " + this.customers.get(index).getCode()+ ": " + this.customers.get(index).getName(),
                "Xác nhận",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if(n == 0 ){
            long id =this.customers.get(index).getId();
            int rowAffected = AccountObjectUltil.DeleteCustomerByID(id);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(pnlCustomer,"Xóa thành công");
                this.innitView();
            }
            else{
                 JOptionPane.showMessageDialog(pnlCustomer,"Xóa thất bại");
            }
        }
    }
    private void btnAddContractClicked(MouseEvent evt) {
        frmCustomerDetailController detailController = new frmCustomerDetailController(frmParent);
        detailController.showForm(true,this.frmParent);
        if (detailController.success) {
            this.innitView();
        }
    }
}
