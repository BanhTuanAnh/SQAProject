/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Feature.Controller;

import Feature.View.frmAccountingMonney;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class AccountingController  extends BaseClass.BaseController{
    private frmAccountingMonney frmAccountingMoney;
    private BussinessAccountingController bAControler; 
     private CommonAccountingController cAController;

    public AccountingController() {
        this.frmAccountingMoney = new frmAccountingMonney();
        bAControler = new BussinessAccountingController(this.frmAccountingMoney.getBussinessAccounting1());
        cAController = new CommonAccountingController(this.frmAccountingMoney.getCommonAccounting1());
    
        
    }
     
    @Override
    public void innitEvent() {
    }

    @Override
    public void innitView() {
    }
       public void showForm(boolean visible){
        frmAccountingMoney.setLocationRelativeTo(null);
        frmAccountingMoney.setVisible(visible);
    }

}
