/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer.Controller;

import ClockDetail.Controller.InsertUpdateClockController;
import ClockDetail.View.frmClockDetail;
import ClockDetail.View.frmInsertUpdateClockIndex;
import Customer.View.frmCustomerDetail;
import Customer.View.frmDetailCustomer;
import Model.Entity.Customer;
import Model.Entity.ElectricIndex;
import Main.View.frmMain;
import Model.DAO.AccountObjectUltil;
import Model.DAO.ClockDetailUltil;
import Model.DAO.ClockUltil;
import java.awt.event.ItemEvent;
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
public class frmCustomerDetailController extends BaseClass.BaseController {
    
    private frmMain frmMain;
    private frmDetailCustomer frmDetail;
    private int mode;
    public boolean success;
    private Customer customer;

    public frmCustomerDetailController(Customer customer,frmMain frmMain) {
        this.frmMain = frmMain;
        this.frmDetail = new frmDetailCustomer(this.frmMain);
        this.innitEvent();
        if (customer != null) {
            this.customer = customer;
        }
        if (customer != null) {
            this.customer = customer;
        }
        this.innitView();
    }

    public frmCustomerDetailController(frmMain frmMain) {
        this.frmMain = frmMain;
        this.frmDetail = new frmDetailCustomer(this.frmMain);
        this.customer = null;
        this.innitEvent();
        this.innitView();

    }

    public void showForm(boolean visible, frmMain frmParent) {

        this.frmDetail.setLocationRelativeTo(frmParent);
        frmDetail.setVisible(visible);
    }


    @Override

    public void innitView() {
        this.frmDetail.getNumCustomerID().setVisible(false);
        if (this.customer != null) {
            this.frmDetail.getCboContract().setEditable(false);
            this.frmDetail.getNumCustomerID().setValue(customer.getId());
            this.frmDetail.getTxtCustomerAddress().setText(customer.getAddress());
            this.frmDetail.getTxtCustomerName().setText(customer.getName());
            this.frmDetail.getTxtClockCode().setText(customer.getClockCode());
            this.frmDetail.getTxtCustomerPhoneNumber().setText(customer.getPhoneNumber());
            this.frmDetail.getCboVolt().setSelectedIndex((int) customer.getIntVoltType());
            this.frmDetail.getCboContract().setSelectedIndex((int) customer.getIntcontractType());
            this.frmDetail.getTxtCustomerCode().setText(customer.getCode());
        } else {
            this.frmDetail.getNumCustomerID().setValue((int)-1);
            this.frmDetail.getCboContract().setSelectedIndex(0);
            this.frmDetail.getCboVolt().setSelectedIndex(0);
            try {
                this.frmDetail.getTxtCustomerCode().setText(AccountObjectUltil.GetNewCustomerCode());
                this.frmDetail.getTxtClockCode().setText(ClockUltil.GetNewClockCode());
            } catch (SQLException ex) {
                Logger.getLogger(frmCustomerDetailController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(frmCustomerDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void btnCancelClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {
        showForm(false,this.frmMain);
    }
    private void cboContractItemStateChanged(java.awt.event.ItemEvent evt) {   
         frmDetail.getCboVolt().setVisible(true);
       if (frmDetail.getCboContract().getSelectedIndex() == 0) {
          frmDetail.getCboVolt().setVisible(false);
       }
    }       
    @Override
    public void innitEvent() {
        this.frmDetail.getCboContract().addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboContractItemStateChanged(evt);
            }
        });                       


        this.frmDetail.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frmDetail.setVisible(false);
            }
        });
        this.frmDetail.getBtnSave().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnSaveMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(InsertUpdateClockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        this.frmDetail.getBtnCancel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnCancelClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(InsertUpdateClockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    private void btnSaveMouseClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {

        this.customer = new Customer();
        Object k = (this.frmDetail.getNumCustomerID().getValue());
        customer.setId((int) this.frmDetail.getNumCustomerID().getValue());
        customer.setClockCode(this.frmDetail.getTxtClockCode().getText());
        customer.setAddress(this.frmDetail.getTxtCustomerAddress().getText());
        customer.setName(this.frmDetail.getTxtCustomerName().getText());
        customer.setPhoneNumber(this.frmDetail.getTxtCustomerPhoneNumber().getText());
        customer.setIntVoltType(this.frmDetail.getCboVolt().getSelectedIndex());
        customer.setIntcontractType(this.frmDetail.getCboContract().getSelectedIndex());
        customer.setCode(this.frmDetail.getTxtCustomerCode().getText());
        if (AccountObjectUltil.InsertUpdateCustomer(customer) > 0) {
            JOptionPane.showMessageDialog(frmDetail, "Cất thành công");
            this.success = true;
            this.setEditAbleLayout(false);
        } else {
            this.success = false;
            JOptionPane.showMessageDialog(frmDetail, "Thông tin số điện sai");
        }
        

    }

    ;

    private void setEditAbleLayout(boolean b) {
        this.frmDetail.getTxtCustomerAddress().setEditable(b);
        this.frmDetail.getTxtCustomerName().setEditable(b);
        this.frmDetail.getTxtClockCode().setEditable(b);
        this.frmDetail.getTxtCustomerPhoneNumber().setEditable(b);
        this.frmDetail.getCboVolt().setEditable(b);
        this.frmDetail.getCboContract().setEditable(b);
    }
}
