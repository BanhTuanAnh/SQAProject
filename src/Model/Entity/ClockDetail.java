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
 * @author admin
 */
public class ClockDetail extends AppObject{
    private int clockID;
    private int clockIndexID;
    private int KWHnumber;
    private String month;
    private int index;
    private Date createdDate;
    private String state;
    private String[] props={"Tháng","Chỉ số","Số KWH","Ngày ghi","Trạng thái"};

    public int getClockID() {
        return clockID;
    }

    public void setClockID(int clockID) {
        this.clockID = clockID;
    }

    public int getClockIndexID() {
        return clockIndexID;
    }

    public void setClockIndexID(int clockIndexTD) {
        this.clockIndexID = clockIndexTD;
    }


    public int getKWHnumber() {
        return KWHnumber;
    }

    public void setKWHnumber(int KWHnumber) {
        this.KWHnumber = KWHnumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getProps() {
        return props;
    }

    public void setProps(String[] props) {
        this.props = props;
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
        
        List <Object> data = new ArrayList<>();
        data.add(this.month);
        data.add(this.index);
        data.add(this.KWHnumber);
        data.add(this.createdDate);
        data.add(this.state);
        
        return data;
     }
    
}
