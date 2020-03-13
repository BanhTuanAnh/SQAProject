/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.Controller;

import BaseClass.BaseController;
import Login.View.frmLogin;
import Model.DAO.AccountObjectUltil;
import Model.DAO.DBUltil;
import Model.Entity.Employee;
import Model.Enum.StringEnum;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Main.Controller.frmMainController;

/**
 *
 * @author admin
 */
public class LoginController extends BaseController {
    
    private frmLogin frmLogin;
    private AccountObjectUltil accountObjectUltil;
    public LoginController() {
        super();
        this.frmLogin = new frmLogin();
        this.innitEvent();
    }
    public void showForm(boolean visible){
        frmLogin.pack();
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(visible);
    }
    @Override
    public void innitEvent() {
        this.frmLogin.getBtnLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    btnLoginMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void btnLoginMouseClicked(MouseEvent evt) throws SQLException, ClassNotFoundException{
        String userName = this.frmLogin.getTxtAccount().getText();
        
        char[] temp = this.frmLogin.getTxtPassword().getPassword();
        String passWord = "";
        for(char c : temp){
            passWord= passWord + c;
        }
        if(this.Validate()){
            try {
                Employee employee = AccountObjectUltil.checkLoginInfo(userName,passWord);
                if(employee == null){
                   JOptionPane.showMessageDialog(frmLogin,"Sai mật khẩu, đăng nhập lại");
                }else {
                    frmMainController frmMainController = new frmMainController();
                    frmMainController.showForm(true);
                    this.showForm(false);
                }
                   
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    private boolean Validate(){
        
        return true;
    }
    @Override
    public void innitView() {
        
     }
    

    private void LoadData(){
        
    }


   
}
