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
public class Employee extends AppObject {
    private int id;
    private String name;

    private String code;
    private String phoneNumber;
    private String address;
    private String[] props ={"Mã","Tên","Số điện thoại", "Địa chỉ" } ;
    public Employee() {
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
        data.add(phoneNumber);
        data.add(address);
        return data;
    }
    public Employee(String name,  String code, String phoneNumber, String address) {
        this.name = name;
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
