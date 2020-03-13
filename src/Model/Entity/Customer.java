/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity;

import BaseClass.AppObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Customer extends AppObject{
   private int id;
   private String name;
    private String code;
    private String address;
    private String clockCode;
    private String phoneNumber;
    private String[] props ={"Mã","Tên khách hàng","Mã đồng hồ","Số điện thoại", "Địa chỉ"} ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClockCode() {
        return clockCode;
    }

    public void setClockCode(String clockCode) {
        this.clockCode = clockCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getProps() {
        return props;
    }

    public void setProps(String[] props) {
        this.props = props;
    }
        
    
    
    @Override
    public Object[] ToSerilize() {
        return null;
    }


    @Override
    public String[] GetProps() {
        return this.props;
    }

    @Override
    public List<Object> ToList() {
   
     List<Object> data = new ArrayList<>();
        data.add(code);
        data.add(name);
       data.add(clockCode);
        data.add(phoneNumber);
        data.add(address);

        return data;
    }
    
}
