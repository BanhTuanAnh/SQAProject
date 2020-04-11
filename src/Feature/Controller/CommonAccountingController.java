/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Feature.Controller;

import Feature.View.Bussiness.pnlCommonAccounting;
import Feature.View.Common.pnlBussinessAccounting;
import Model.DAO.CaculateElectricAmountUltil;
import Model.Entity.CaculateResult;
import Model.Entity.DetailResult;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class CommonAccountingController extends BaseClass.BaseController {

    private pnlCommonAccounting pnlAccounting;
    private CaculateResult cResult = null;

    CommonAccountingController(pnlCommonAccounting commonAccounting1) {
        this.pnlAccounting = commonAccounting1;
                this.innitEvent();
        this.innitView();
     }
     @Override
    public void innitView() {
    }
    
     @Override
    public void innitEvent() {
        this.pnlAccounting.getBtnCaculate().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    btnCaculateClicked(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(BussinessAccountingController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BussinessAccountingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }
    
    private void btnCaculateClicked(MouseEvent evt) throws SQLException, ClassNotFoundException {
        long firstIndex = (long) this.pnlAccounting.getNumFirstIndex().getValue();
        long secondIndex = (long) this.pnlAccounting.getNumSecondIndex().getValue();
        if (firstIndex > secondIndex || firstIndex < 0) {
            JOptionPane.showMessageDialog(pnlAccounting, "Chỉ số trước phải nhỏ hơn chỉ số sau và là số dương");
        } else {

            cResult = CaculateElectricAmountUltil.getCaulateCommontResult(secondIndex - firstIndex);
            if (cResult != null) {
                DefaultTableModel model = this.fillTable(cResult.getListDetailResult(), new DetailResult());
                this.pnlAccounting.getTblResult().setModel(model);
                this.pnlAccounting.getNumAmount().setValue(cResult.getAmount());
                this.pnlAccounting.getNumTax().setValue(cResult.getTax());
                this.pnlAccounting.getNumTotalAmount().setValue(cResult.getTotalAmount());

            }

        }

    }
}
