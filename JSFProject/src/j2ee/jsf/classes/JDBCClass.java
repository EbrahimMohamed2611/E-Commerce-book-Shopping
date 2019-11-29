package j2ee.jsf.classes;

import j2ee.jsf.beans.LoginBean;
import j2ee.jsf.beans.RegisterBean;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class JDBCClass {
    
    public static boolean registerCustomer(){
        

        Connection conn = null;
        PreparedStatement pstmt = null;
        
          try {

            //STEP 3: Open a connection   
            System.out.println("Connecting to database...");
            //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("MyLibraryAppDBDS");
            conn = ds.getConnection();


        // Reading parameter from Bean in Session Scope
   RegisterBean registerBean =
     (RegisterBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("RegisterBean");


            
            String userName = registerBean.getUserName();
            String password = registerBean.getPassword();
            String fullName = registerBean.getFullName();
            String Country = registerBean.getCountry();
            String city = registerBean.getCity();
            String address = registerBean.getAddress();
            String job = registerBean.getJob();
            double income = registerBean.getIncome();
            Date dob = registerBean.getDob();
            String email = registerBean.getEmail();
            String gender = registerBean.getGender();
            String egyptian = registerBean.getEgyptianValue();
            String mobile = registerBean.getMobile();


            String sql;
            sql = "INSERT INTO LIBRARY_USERS" + " (USER_NAME, PASSWORD)" + " VALUES(?,?)";

            //STEP 4: Execute a query
            System.out.println("Creating Statement...");
            pstmt = conn.prepareStatement(sql);
            System.out.println("set params");

            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            pstmt.executeUpdate();

            //// Insert into Library Customers
            sql =
                "INSERT INTO LIBRARY_CUSTOMERS" + " (USER_ID, FULL_NAME, ADDRESS, JOB, INCOME," +
                " GENDER, CITY, COUNTRY, EMAIL, MOBILE, DATE_BIRTH, EGYPTIAN)" +
                " VALUES(LIBRARY_USERS_SEQ.CURRVAL,?,?,?,?,?,?,?,?,?,?,?)";
            
            System.out.println("Creating statement...");
            pstmt = conn.prepareStatement(sql);
            System.out.println("set params");

            pstmt.setString(1, fullName);
            pstmt.setString(2, address);
            pstmt.setString(3, job);
            pstmt.setDouble(4, income);
            pstmt.setString(5, gender);
            pstmt.setString(6, city);
            pstmt.setString(7, Country);
            pstmt.setString(8, email);
            pstmt.setString(9, mobile);
            // Convert from java.util.Date to java.sql.date using Constructor
            pstmt.setDate(10, new java.sql.Date(dob.getTime()));
            pstmt.setString(11, egyptian);

            pstmt.executeUpdate();

        pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
        }


    public static boolean checkLogin(){
        
        Connection conn = null;
               PreparedStatement pstmt = null;
                   boolean userFound = false;
                try{
               //STEP 2: Register JDBC driver
               //      Class.forName(JDBC_DRIVER);

                 //STEP 3: Open a connection
                 System.out.println("Connecting to database...");
               //     conn = DriverManager.getConnection(DB_URL,USER,PASS);

               //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                           Context ctx = new InitialContext();             
                             DataSource ds = (DataSource)ctx.lookup("MyLibraryAppDBDS");
                             conn = ds.getConnection();

                   // Reading parameter from Bean in Session Scope       
                         LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().
                             getExternalContext().getSessionMap().get("LoginBean");
                            
                          String userName = loginBean.getUserName();
                          String password = loginBean.getPassword();
                          

                    String sql;
                    sql = "SELECT COUNT(*) CNT"
                           +" FROM LIBRARY_USERS"
                           +" WHERE USER_NAME=?"
                           +" AND PASSWORD=?";
                  
                         //STEP 4: Execute a query
                         System.out.println("Creating statement...");
                         pstmt = conn.prepareStatement(sql);
                           System.out.println("set params");

                         pstmt.setString(1, userName);
                         pstmt.setString(2, password);
                            
                          ResultSet rs =  pstmt.executeQuery();
                               rs.next();
                               int cnt = rs.getInt("CNT");
                    if(cnt > 0)
                    {
                        userFound = true;
                    System.out.println("User Found");
                    }
                    else {
                        System.out.println("User Not Found");
                    }
                    rs.close();
                    pstmt.close();
                    
               } catch (Exception e) {
                    e.printStackTrace();
                    return userFound;
               }
                           
              return userFound;
               
           }

    
    public static ArrayList<Products> getAllProducts(){
        

        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<Products> productsList = new ArrayList<Products>();
        
         try{
        //STEP 2: Register JDBC driver
        //      Class.forName(JDBC_DRIVER);

          //STEP 3: Open a connection
          System.out.println("Connecting to database...");
        //     conn = DriverManager.getConnection(DB_URL,USER,PASS);

        //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                    Context ctx = new InitialContext();             
                      DataSource ds = (DataSource)ctx.lookup("MyLibraryAppDBDS");
                      conn = ds.getConnection();

            // Reading parameter from Bean in Session Scope       
          //        LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().
           //    getExternalContext().getSessionMap().get("LoginBean");
                     
           //        String userName = loginBean.getUserName();
            //       String password = loginBean.getPassword();
             

             String sql;
             sql = "SELECT ID, NAME, PRICE, DESCRIPTION, IMAGE"
                        +" FROM PRODUCTS";
           
                  //STEP 4: Execute a query
                  System.out.println("Creating statement...");
                  pstmt = conn.prepareStatement(sql);
                    System.out.println("set params");

              //    pstmt.setString(1, userName);
              //    pstmt.setString(2, password);
                     
                   ResultSet rs =  pstmt.executeQuery();
             while( rs.next()){
                        int productId = rs.getInt("ID");
                        String productName = rs.getString("NAME");
                        double productPrice = rs.getDouble("PRICE");
                        String productDescription = rs.getString("DESCRIPTION");
                        String productImage = rs.getString("IMAGE");
                            
                        Products p1 = new Products(productId, productName, productPrice, 
                                                 productDescription, productImage);
                 
                            productsList.add(p1);
                     }
             rs.close();
             pstmt.close();
             conn.close();
        } catch (Exception e) {
             e.printStackTrace();
        }
                    
        return productsList;
        
        }

    
    
    
}
