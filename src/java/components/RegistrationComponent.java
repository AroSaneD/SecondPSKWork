/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Entities.Player;
import java.io.Serializable;
import java.util.UUID;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import managers.GameManager;
import managers.PlayerManager;
import org.hsqldb.Server;

/**
 *
 * @author Aron
 */
@Named
//@SessionScoped
@ConversationScoped
@Stateful
public class RegistrationComponent implements Serializable {
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED,
            synchronization = SynchronizationType.UNSYNCHRONIZED,
            unitName = "SecondJavaWorkPU")
    private EntityManager em;
    
    @Inject
    private Conversation conversation;
    
    @Inject 
    private GameManager gameManager;
    
    @Inject
    private PlayerManager playerManager;
    
    private String email = null;
    private String password = null;
    private String playerName = null;
    private String errorMessage = null;

    private Player player = new Player();
    
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
            conversation.begin();
            player.setEmail(email);
            player.setPassword(password);
            player.setUid(UUID.randomUUID().toString());
            player.setPlayername(UUID.randomUUID().toString());
            em.persist(player);
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
            //Player player = playerManager.createPlayer(email, password, playerName);
            
            if(player != null){
                
                try{
                    player.setPlayername(playerName);
                    em.persist(player); //Probably not necessary, but not sure enough
                    //em.joinTransaction();
                    em.flush();
                    conversation.end();
                }
                catch(OptimisticLockException ole){
                    return "criticalFailure";
                }
                
                try{
                    this.gameManager.setPlayer(player); //em.flush doesnt appear to work. Because this does not find a player in the database
                }
                catch(Exception e){
                    
                }
                return "success";
            }
            else{
                this.errorMessage = "Was unable to create player";
                return "failure";
            }
        }
    }
}
