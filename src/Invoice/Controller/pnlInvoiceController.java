/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Controller;

import ClockDetail.Controller.ClockDetailController;
import Customer.Controller.pnlCustomerController;
import Customer.View.pnlCustomer;
import Invoice.View.pnlInvoice;
import Model.DAO.AccountObjectUltil;
import Model.DAO.InvoiceUltil;
import Model.Entity.Customer;
import Model.Entity.Invoice;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class pnlInvoiceController extends BaseClass.BaseController{

    private pnlInvoice pnlInvoice;
    private List<Invoice> invoices;
    public pnlInvoiceController() {
        this.pnlInvoice = new pnlInvoice();
        invoices= new ArrayList<>();
        innitEvent();
        innitView();
    }
        public pnlInvoiceController(pnlInvoice pnlInvoice) {
        this.pnlInvoice = pnlInvoice;
        innitEvent();
        innitView();
    }
    public void showForm(boolean visible){
        pnlInvoice.setVisible(visible);
    }
    @Override
    public void innitEvent() {
        this.pnlInvoice.getBtnEditInvoice().addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                btnEditInvoiceClicked(evt);
//            }


        });
//        this.pnlInvoice.getTblCustomer().addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                try {
//                    tblCustomerMouseClicked(evt);
//                } catch (SQLException | ClassNotFoundException ex) {
//                    Logger.getLogger(pnlCustomerController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            
//        });
    
    
    }
    
    @Override
    public void innitView() {
        try {
            invoices = InvoiceUltil.GetInvoices();
            DefaultTableModel model = this.fillTable(invoices, new Invoice());
             this.pnlInvoice.getTblInvoice().setModel(model);  
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(pnlInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    
}
