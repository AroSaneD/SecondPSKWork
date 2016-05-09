/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Entities.Player;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;
import managers.GameManager;
import managers.PlayerManager;
import org.hsqldb.rights.UserManager;

/**
 *
 * @author Aron
 */
@Named
@SessionScoped
@Stateful
public class LoginComponent {

    @PersistenceContext(unitName = "SecondJavaWorkPU")
    private EntityManager em;

    private String email;
    private String password;
    private String errorMessage;

    @Inject 
    private PlayerManager playerManager;
    
    @Inject 
    private GameManager gameManager;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public String tryLogin(){
        Player player =  playerManager.getPlayerByEmail(email);
        
        if(player != null){
            this.gameManager.setPlayer(player);
            this.setErrorMessage(null);
            return "success";
        }
        else{
            this.setErrorMessage("No such user exists");
            return "failure";
        }
    }
    
    public String register(){
        return "register";
    }
//    public String tryRegister(){
//        Player player =  playerManager.createPlayer(email);
//        
//        if(player != null){
//            this.gameManager.setPlayer(player);
//            this.setErrorMessage(null);
//            return "success";
//        }
//        else{
//            //Check for more conditions
//            this.setErrorMessage("Email is already taken");
//            return "failure";
//        }
//    }
}
