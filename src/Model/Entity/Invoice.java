/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity;

import BaseClass.AppObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Invoice extends AppObject{
    private int id;
    private float amount;
    private String sMonth;
    private String code;
    private String description;
    private String customerName;
    private Date createdDate;
    private String state;
    private int CustomerCode;
    private String[] props ={"Mã hóa đơn","Tháng","Tên khách hàng", "Số tiền","Diễn giải","Trạng thái","Ngày tạo" } ;

    public int getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(int CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getsMonth() {
        return sMonth;
    }

    public void setsMonth(String sMonth) {
        this.sMonth = sMonth;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
            
    @Override
    public String[] GetProps() {
                    return this.props;
    }

    @Override
    public List<Object> ToList() {
        List<Object> data = new ArrayList<>();
        data.add(code);
        data.add(sMonth);
        data.add(customerName);
        data.add(amount);
        data.add(description);
        data.add(state);
        data.add(createdDate);
        return data;
    }
    
}
