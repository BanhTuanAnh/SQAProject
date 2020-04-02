/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClockDetail.Controller;

import ClockDetail.View.frmClockDetail;
import ClockDetail.View.frmInsertUpdateClockIndex;
import Model.DAO.ClockDetailUltil;
import Model.Entity.Customer;

import Model.Entity.ElectricIndex;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class InsertUpdateClockController extends BaseClass.BaseController {

    private frmInsertUpdateClockIndex frmInsertUpdateClockIndex;
    private ElectricIndex electricIndex;
    private int mode;
    public boolean success;
    private Customer customer;

    public InsertUpdateClockController(frmClockDetail frmParent, ElectricIndex electricIndex, Customer customer) {
        this.frmInsertUpdateClockIndex = new frmInsertUpdateClockIndex(frmParent, true);
        this.innitEvent();
        if (electricIndex != null) {
            electricIndex = electricIndex;
        }
        if (customer != null) {
            customer = customer;
        }
        this.innitView();
    }

    public InsertUpdateClockController(frmClockDetail frmParent) {
        this.frmInsertUpdateClockIndex = new frmInsertUpdateClockIndex(frmParent, true);
        this.innitEvent();

        electricIndex = null;
        this.innitView();

    }

    public void showForm(boolean visible, frmClockDetail frmParent) {

        this.frmInsertUpdateClockIndex.setLocationRelativeTo(frmParent);
        frmInsertUpdateClockIndex.setVisible(visible);
    }

    @Override
    public void innitEvent() {
        this.frmInsertUpdateClockIndex.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frmInsertUpdateClockIndex.setVisible(false);
            }
        });
        this.frmInsertUpdateClockIndex.getBtnSave().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnSaveMouseClicked(evt);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(InsertUpdateClockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    private void btnSaveMouseClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {

        this.electricIndex = new ElectricIndex();
        electricIndex.setClockDetailID((int) this.frmInsertUpdateClockIndex.getNumClockIndexID().getValue());
        electricIndex.setKWHNumber((int) this.frmInsertUpdateClockIndex.getNumClockIndex().getValue());
        Calendar cal = Calendar.getInstance();
        Date date = (Date) this.frmInsertUpdateClockIndex.getCboCreatedDate().getValue();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        electricIndex.setNumMonth(month);
        electricIndex.setNumMonth(year);
        electricIndex.setClockCode(this.frmInsertUpdateClockIndex.getTxtClockCode().getText());
        if(ClockDetailUltil.InsertCloclDetail(electricIndex) > 0){
            JOptionPane.showMessageDialog(frmInsertUpdateClockIndex, "Thêm thành công");
            this.setViewLayout(true);
        }else
             JOptionPane.showMessageDialog(frmInsertUpdateClockIndex, "Thông tin số điện sai");
    };


    @Override

    public void innitView() {
        this.frmInsertUpdateClockIndex.getNumClockIndexID().setVisible(false);
        this.frmInsertUpdateClockIndex.getNumClockIndexID().setValue(-1);

    }

    private void setViewLayout(boolean b) {
          this.frmInsertUpdateClockIndex.getNumClockIndexID().setEditable(b);
       this.frmInsertUpdateClockIndex.getNumClockIndex().setEditable(b);
       this.frmInsertUpdateClockIndex.getTxtClockCode().setEditable(b);
     this.frmInsertUpdateClockIndex.getCboCreatedDate().setEditable(b);
    1}

}
