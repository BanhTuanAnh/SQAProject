
import BaseClass.ColumnName;
import BaseClass.TableName;
import Model.DAO.AccountObjectUltil;
import Model.DAO.DBConnection;
import Model.DAO.DBUltil;
import Model.Entity.Customer;
import Model.Entity.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class TestAccountObject {
         Connection conn = DBConnection.getSQLServerConnection();
    @Test
    public void testCheckLoginInfo() throws SQLException, ClassNotFoundException {
        try {
                       conn.setAutoCommit(false);
              Employee employee1 = AccountObjectUltil.checkLoginInfo("admin", "21345");

        Employee employee2 = AccountObjectUltil.checkLoginInfo("btanh", "21345");

        Assert.assertNotNull(employee1);
        Assert.assertNull(employee2);
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
        return;
    }

    @Test
    public void testGetCustomers() {
         Connection conn = DBConnection.getSQLServerConnection();
         try {
             conn.setAutoCommit(false);
             String stoName = "select count(*)as number from AccountObject where AccountObjectType =0";
        ResultSet rs = DBUltil.ExcuteQuery(stoName);
        List<Customer> customers = new ArrayList<>();
        customers = AccountObjectUltil.GetCustomers();
        Assert.assertNotNull(customers);
        if (rs.next()) {
            int quantity = rs.getInt("number");
            Assert.assertEquals(quantity, customers.size());
        } else {
            Assert.assertEquals(0, customers.size());
        }
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
       
        return;
    }

    @Test
    public void testGetCustomerByID(){
        Connection conn = DBConnection.getSQLServerConnection();
        try {
                    conn.setAutoCommit(false);
             String stoName = "select CD.*,C.ClockCode from AccountObject CD inner join Clock C on C.ClockID = CD.ClockID where CD.AccountObjectID = 4";
        ResultSet rs = DBUltil.ExcuteQuery(stoName);
        Customer customer4 = AccountObjectUltil.GetCustomerByID(4);
 

        if (rs.next()) {
            Assert.assertNotNull(customer4);
            Customer customer = new Customer();
            customer.setId(rs.getInt(ColumnName.AOAccountObjectID));
            customer.setCode(rs.getNString(ColumnName.AOAccountObjectCode));
            customer.setIntcontractType(rs.getInt(ColumnName.ContractType));
            customer.setIntVoltType(rs.getInt(ColumnName.VoltageType));
            customer.setAddress(rs.getNString(ColumnName.AOAccountAddress));
            customer.setClockCode(rs.getNString(ColumnName.ClockCode));
            customer.setPhoneNumber(rs.getNString(ColumnName.AOPhoneNumber));
            customer.setName(rs.getNString(ColumnName.AOAccountObjectName));
            Assert.assertEquals(customer4.getId(), customer.getId());

        } else {
            Assert.assertNotNull(customer4);

        }
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
       
        return;
    }

    public void testDeleteCustomerByID(long customerID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getSQLServerConnection();
        try {
              conn.setAutoCommit(false);
            Customer customer = AccountObjectUltil.GetCustomerByID(99);

        if (customer == null) {
            customer = new Customer();
            customer.setId(99);
            customer.setCode("KH99");
            customer.setIntcontractType(0);
            customer.setIntVoltType(0);
            customer.setAddress("Hà Nội");
            customer.setClockCode("");
            customer.setPhoneNumber("0123");
            customer.setName("test customer");
            AccountObjectUltil.InsertUpdateCustomer(customer);

        }
        AccountObjectUltil.DeleteCustomerByID(99);
        customer = AccountObjectUltil.GetCustomerByID(99);
        Assert.assertNull(customer);
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return;

    }
@Test
    public void testInsertCustomer0() throws SQLException, ClassNotFoundException {
          Connection conn = DBConnection.getSQLServerConnection();
        try {
                        conn.setAutoCommit(false);
            Customer customer;
        AccountObjectUltil.DeleteCustomerByID(99);
        customer = new Customer();
        customer.setId(1);
        customer.setCode("KH99");
        customer.setIntcontractType(0);
        customer.setIntVoltType(0);
        customer.setAddress("Hà Nội");
        customer.setClockCode("");
        customer.setPhoneNumber("0123");
        customer.setName("test customer");
        AccountObjectUltil.InsertUpdateCustomer(customer);
        
        customer = AccountObjectUltil.GetCustomerByCode("KH99");

        Assert.assertNotNull(customer);
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return;
    }
    
    @Test
    public void testInsertCustomer() throws SQLException, ClassNotFoundException {
          Connection conn = DBConnection.getSQLServerConnection();
        try {
                        conn.setAutoCommit(false);
            Customer customer;
        AccountObjectUltil.DeleteCustomerByID(99);
        customer = new Customer();
        customer.setId(-1);
        customer.setCode("KH99");
        customer.setIntcontractType(0);
        customer.setIntVoltType(0);
        customer.setAddress("Hà Nội");
        customer.setClockCode("");
        customer.setPhoneNumber("0123");
        customer.setName("test customer");
        AccountObjectUltil.InsertUpdateCustomer(customer);
        
        customer = AccountObjectUltil.GetCustomerByCode("KH99");

        Assert.assertNotNull(customer);
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return;
    }

    @Test
    public void testUpdateCustomer() throws SQLException, ClassNotFoundException {
         Connection conn = DBConnection.getSQLServerConnection();
         try {
                         conn.setAutoCommit(false);
            Customer customer;
        AccountObjectUltil.DeleteCustomerByID(99);
        customer = new Customer();
        customer.setId(-1);
        customer.setCode("KH99");
        customer.setIntcontractType(0);
        customer.setIntVoltType(0);
        customer.setAddress("Hà Nội");
        customer.setClockCode("0001");
        customer.setPhoneNumber("0123");
        customer.setName("test customer");
        AccountObjectUltil.InsertUpdateCustomer(customer);
        customer=AccountObjectUltil.GetCustomerByCode("KH99");
        customer.setAddress("Hải phòng");
        customer.setName("update Customer");
        
        AccountObjectUltil.InsertUpdateCustomer(customer);
        customer = AccountObjectUltil.GetCustomerByCode("KH99");

        Assert.assertNotNull(customer);
        Assert.assertEquals("update Customer",customer.getName() );
        Assert.assertEquals( "Hải phòng",customer.getAddress());
        Assert.assertEquals( "KH99",customer.getCode());
        } catch (Exception e) {
        }finally{
             try{
                conn.rollback();
                conn.setAutoCommit(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return;
    }
}
