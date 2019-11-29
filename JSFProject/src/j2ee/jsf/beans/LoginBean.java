package j2ee.jsf.beans;

import j2ee.jsf.classes.JDBCClass;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;

public class LoginBean {
    public LoginBean() {
    }
    
    private String userName;
    private String password;
    private boolean showMenu;

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Object checkLogin() {
        // Add event code here...
        //if(userName.equals("ahmed") && (password.equals("ahmed123"))){
          
           boolean loginOk =  JDBCClass.checkLogin(); 
           if (loginOk){
                    setShowMenu(true);
                return "Home";
        }
           else{
            
                return null;
               
           }
    }
    
    
    public Object logout() {
        // Add event code here...
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        setShowMenu(false);
        return "Index";
    }
    
    
    
    
    

}