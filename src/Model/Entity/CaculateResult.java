/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity;

import java.util.List;

/**
 *
 * @author admin
 */
public class CaculateResult {
    private List<DetailResult> listDetailResult ;
    private long totalAmount;
    private long tax;
    private long Amount;

    public List<DetailResult> getListDetailResult() {
        return listDetailResult;
    }

    public void setListDetailResult(List<DetailResult> listDetailResult) {
        this.listDetailResult = listDetailResult;
    }

    
    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTax() {
        return tax;
    }

    public void setTax(long tax) {
        this.tax = tax;
    }

    public long getAmount() {
        return Amount;
    }

    public void setAmount(long Amount) {
        this.Amount = Amount;
    }
    
}
