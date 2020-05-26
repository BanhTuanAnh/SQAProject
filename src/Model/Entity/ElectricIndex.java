/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity;

import BaseClass.AppObject;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ElectricIndex extends AppObject {
    private String month;
      private int numMonth;
        private int numYear;
    private long clockIndex;
    private int clockDetailID;
    private float amount;
    private String state;
    private long preClockIndex;
    private long KWHNumber;
    private Date createdDate;
    private String clockCode;
    private String props[]={"Tháng","Số điện","Số điện kỳ trước","Số KWH","Số tiền","Ngày tạo","Trạng thái"};

    @Override
    public String[] GetProps() {
        return this.props;
    }

    @Override
    public List<Object> ToList() {
        List<Object> data = new ArrayList<>();
        data.add(this.month);
        data.add(this.clockIndex);
        data.add(this.preClockIndex);
        data.add(this.KWHNumber);
        data.add(amount);
        data.add(createdDate);
        data.add(this.state);
        return data;
    }

    public String getClockCode() {
        return clockCode;
    }

    public void setClockCode(String clockCode) {
        this.clockCode = clockCode;
    }

  
    public void setNumYear(int numYear) {
        this.numYear = numYear;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStrMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

   
    public void setClockIndex(long clockIndex) {
        this.clockIndex = clockIndex;
    }

  
    public void setClockDetailID(int clockDetailID) {
        this.clockDetailID = clockDetailID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

   

  

    public void setKWHNumber(int KWHNumber) {
        this.KWHNumber = KWHNumber;
    }

    public int getNumMonth() {
        return numMonth;
    }

    public void setNumMonth(int numMonth) {
        this.numMonth = numMonth;
    }

    public int getNumYear() {
        return numYear;
    }


    public long getClockIndex() {
        return clockIndex;
    }

    public int getClockDetailID() {
        return clockDetailID;
    }

    public long getPreClockIndex() {
        return preClockIndex;
    }

    public void setPreClockIndex(long preClockIndex) {
        this.preClockIndex = preClockIndex;
    }

    public long getKWHNumber() {
        return KWHNumber;
    }

    public void setKWHNumber(long KWHNumber) {
        this.KWHNumber = KWHNumber;
    }

    public String[] getProps() {
        return props;
    }

    public void setProps(String[] props) {
        this.props = props;
    }
    
}
