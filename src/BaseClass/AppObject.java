/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseClass;

import java.util.List;


/**
 *
 * @author admin
 */
public abstract class AppObject {


    public AppObject() {
    }
    public  Object[] ToSerilize(){
        return null;
    };
    public abstract String[] GetProps();
    public abstract List<Object> ToList();
}
