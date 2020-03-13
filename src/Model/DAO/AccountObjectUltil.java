/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import Model.Entity.Customer;
import Model.Entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author admin
 */
public class AccountObjectUltil {
    public static Employee checkLoginInfo(String username,String password) throws SQLException, ClassNotFoundException{
        String stoName = "ProCheckLoginAccount";
        ResultSet rs =DBUltil.ExcuteQuery(stoName,username,password);
          Employee employee =null;
        if(rs.next()){
            employee = new Employee();
                employee.setId(rs.getInt(ColumnName.AOAccountObjectID));
                employee.setCode(rs.getNString(ColumnName.AOAccountObjectCode));
                employee.setAddress(rs.getNString(ColumnName.AOAccountAddress));
                employee.setName(rs.getNString(ColumnName.AOAccountObjectName));
                employee.setPhoneNumber(rs.getNString(ColumnName.AOPhoneNumber));
        }
        return employee;
    }
    public static List<Customer> GetCustomers() throws SQLException, ClassNotFoundException{
        String stoName = "ProGetAllCustomers";
        ResultSet rs =DBUltil.ExcuteQuery(stoName);
         List<Customer> customers = new ArrayList<>();
        while(rs.next()){
            Customer customer = new Customer();
                customer.setId(rs.getInt(ColumnName.AOAccountObjectID));
                customer.setCode(rs.getNString(ColumnName.AOAccountObjectCode));
                customer.setAddress(rs.getNString(ColumnName.AOAccountAddress));
                customer.setClockCode(rs.getNString(ColumnName.ClockCode));
                customer.setPhoneNumber(rs.getNString(ColumnName.AOPhoneNumber));
                customer.setName(rs.getNString(ColumnName.AOAccountObjectName));
                customers.add(customer);
        }
        return customers;
    }

}
