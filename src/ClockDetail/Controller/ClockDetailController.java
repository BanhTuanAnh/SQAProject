/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClockDetail.Controller;

import BaseClass.BaseController;
import ClockDetail.View.frmClockDetail;
import Customer.View.pnlCustomer;
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

    public ClockDetailController() {
        frmClockDetail = new frmClockDetail();
        this.customer = new Customer();
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
                btnEditEIMouseClicked(evt);
            }

        });
        this.frmClockDetail.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frmClockDetail.setVisible(false);
            }
        });

    }

    private void btnAddEIMouseClicked(MouseEvent evt) {
        InsertUpdateClockController dialog = new InsertUpdateClockController(frmClockDetail);
        dialog.showForm(true, frmClockDetail);
        if (dialog.success) {
            JOptionPane.showMessageDialog(frmClockDetail, "Cất thành công");
        }
    }

    private void btnEditEIMouseClicked(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void innitView() {
        if (this.customer != null) {
            this.frmClockDetail.getLblCustomerName().setText(customer.getName());
        } else {
            this.frmClockDetail.getLblCustomerName().setText("");
        }
        int customerID = customer.getId();
        List<ElectricIndex> list;

        try {
            list = ClockUltil.GetElectricIndexsByCustomer(customerID);
            this.frmClockDetail.getTblClockDetail().setModel(this.fillTable(list, new ElectricIndex()));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
