/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Controller;


import BaseClass.BaseController;
import Customer.Controller.pnlCustomerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import Main.View.frmMain;


/**
 *
 * @author admin
 */
public class frmMainController extends BaseController{
    private frmMain frmMain ;
    pnlCustomerController customerController;
    public frmMainController(frmMain frame){
       frmMain = new frmMain();
             
        customerController = new pnlCustomerController(frmMain.getPnlCustomer());
    }

    public frmMainController() {
        frmMain= new frmMain();
          customerController = new pnlCustomerController(frmMain.getPnlCustomer());
        
        
    }
    public void showForm(boolean visible){
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.setVisible(visible);
    }

    @Override
    public void innitEvent() {
        
    }

    @Override
    public void innitView() {
        
    }
    
 
}
