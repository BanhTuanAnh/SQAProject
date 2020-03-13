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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class ClockDetailController extends BaseController{
    private Customer customer;
    private frmClockDetail frmClockDetail;
    
    public void showForm(boolean visible){
                frmClockDetail.setLocationRelativeTo(null);
         frmClockDetail.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmClockDetail.setVisible(visible);
    }
    public ClockDetailController(Customer customer) {
        frmClockDetail= new frmClockDetail();
        this.customer = customer;
         innitEvent() ;
         innitView();
    }
        public ClockDetailController() {
        frmClockDetail= new frmClockDetail();
        this.customer = new Customer();
    }
    @Override
    public void innitEvent() {
        
        
    }

    @Override
    public void innitView() {
        if(this.customer != null){
            this.frmClockDetail.getLblCustomerName().setText(customer.getName());
        }else
            this.frmClockDetail.getLblCustomerName().setText("");
        int customerID = customer.getId();
        List<ElectricIndex> list;
         
        try {
            list = ClockUltil.GetElectricIndexs(customerID);
            this.frmClockDetail.getTblClockDetail().setModel(this.fillTable(list, new ElectricIndex()));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClockDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
}
