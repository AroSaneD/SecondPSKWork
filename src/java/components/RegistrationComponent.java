/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Entities.Player;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managers.GameManager;
import managers.PlayerManager;

/**
 *
 * @author Aron
 */
@Named
@SessionScoped
public class RegistrationComponent implements Serializable {
    
    @Inject 
    private GameManager gameManager;
    
    @Inject
    private PlayerManager playerManager;
    
    private String email = null;
    private String password = null;
    private String playerName = null;
    private String errorMessage = null;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getPlayerName() {return playerName;}

    public void setPlayerName(String playerName) {this.playerName = playerName;}
    
    public String beginRegister(){
        //begin registration here
        if(playerManager.isEmailTaken(this.email)){
            this.errorMessage = "Email is already in use";
            return "failure";
        }
        else{
            return "success";
        }
    }
    
    public String endRegister(){
        if(playerManager.isPlayerNameTaken(this.playerName)){
            this.errorMessage = "Player name is already taken";
            return "failure";
        }
        else{
            //end scope somehow
            Player player = playerManager.createPlayer(email, password, playerName);
            
            if(player != null){
                this.gameManager.setPlayer(player);
                return "success";
            }
            else{
                this.errorMessage = "Was unable to create player";
                return "failure";
            }
        }
    }
}
