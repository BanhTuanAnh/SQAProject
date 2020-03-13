/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer.Controller;

import BaseClass.BaseController;
import BaseClass.ColumnName;
import ClockDetail.Controller.ClockDetailController;
import Customer.View.pnlCustomer;
import Model.DAO.AccountObjectUltil;
import Model.DAO.ClockUltil;
import Model.Entity.ClockDetail;
import Model.Entity.Customer;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class pnlCustomerController extends BaseController{
    private pnlCustomer pnlCustomer;
    private List<Customer> customers;
    public pnlCustomerController() {
        this.pnlCustomer = new pnlCustomer();
        customers= new ArrayList<>();
        innitEvent();
        innitView();
    }
        public pnlCustomerController(pnlCustomer pnlCustomer) {
        this.pnlCustomer = pnlCustomer;
        innitEvent();
        innitView();
    }
    public void showForm(boolean visible){
        pnlCustomer.setVisible(visible);
    }
    @Override
    public void innitEvent() {
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
            private void btnViewElectricIndexMouseClicked(MouseEvent evt) {
                 int selectedIndex = this.pnlCustomer.getTblCustomer().getSelectedRow();
            if(selectedIndex>0) {
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
    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) throws SQLException, ClassNotFoundException{
        int selectedIndex = this.pnlCustomer.getTblCustomer().getSelectedRow();
        int customerID = customers.get(selectedIndex).getId();
        List<ClockDetail> clockDetails = new ArrayList<>();
        clockDetails = ClockUltil.GetCloclDetails(customerID);
        this.pnlCustomer.getTblClockDetail().setModel(this.fillTable(clockDetails, new ClockDetail()));
    }
    
}
