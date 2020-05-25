/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Controller;


import BaseClass.BaseController;
import ClockDetail.Controller.ClockDetailController;
import Customer.Controller.pnlCustomerController;
import Feature.Controller.AccountingController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import Main.View.frmMain;
import java.awt.event.MouseEvent;


/**
 *
 * @author admin
 */
public class frmMainController extends BaseController{
    private frmMain frmMain ;
    pnlCustomerController customerController;
    public frmMainController(frmMain frame){
       frmMain = new frmMain();  
        customerController = new pnlCustomerController(frmMain.getPnlCustomer(),this.frmMain);
        this.innitEvent();
        this.innitView();
    }

    public frmMainController() {
        frmMain= new frmMain();
            this.innitEvent();
        this.innitView();
        customerController = new pnlCustomerController(frmMain.getPnlCustomer(),this.frmMain);
        
    }
    public void showForm(boolean visible){
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.setVisible(visible);
    }

    @Override
    public void innitEvent() {
        this.frmMain.getMnuCaculate().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuCaulateMouseClicked(evt);
            }
        });
        
    }
    private void mnuCaulateMouseClicked(MouseEvent evt) {
        AccountingController AController = new AccountingController();
                AController.showForm(true);
       }
    @Override
    public void innitView() {
        
    }
    
 
}
