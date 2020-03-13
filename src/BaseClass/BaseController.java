/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseClass;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public abstract class BaseController  {
    
   public < T extends AppObject>  DefaultTableModel  fillTable(List<T> listData,T dataModel) {
        List<Object> data;
        String[] cols = dataModel.GetProps();
        DefaultTableModel model;
        model = new DefaultTableModel(cols , 0);
        model.setRowCount(0);
        for (T item : listData) {
            data = item.ToList();
            Object[] objects = new Object[cols.length] ;
            for(int i = 0;i<cols.length;i++){
               Object value = data.get(i);
               if(value !=null){
                   objects[i]=value;
               }               
            }
            model.addRow(objects);
        }
        return model;
    }
    public abstract void innitEvent();    
    public abstract void innitView();    
}
