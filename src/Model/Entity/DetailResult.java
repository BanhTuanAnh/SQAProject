/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity;
import BaseClass.AppObject;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class DetailResult extends AppObject {
    private long amount;
    private String method;
    private String taget;
    private String[] props={"Chỉ tiêu","Công thức","Số tiền"};
    @Override
    public String[] GetProps() {
        return this.props;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTaget() {
        return taget;
    }

    public void setTaget(String taget) {
        this.taget = taget;
    }

    public String[] getProps() {
        return props;
    }

    public void setProps(String[] props) {
        this.props = props;
    }
    
     @Override
    public List<Object> ToList() {
        
        List <Object> data = new ArrayList<>();
        data.add(this.taget);
        data.add(this.method);
        data.add(this.amount);
        
        return data;
     }
    
}
