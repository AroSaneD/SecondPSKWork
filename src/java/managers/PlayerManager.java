/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import Entities.Player;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Aron
 */
@Named
@RequestScoped
@Stateful
public class PlayerManager {

    @PersistenceContext(unitName = "SecondJavaWorkPU")
    private EntityManager em;
    
    public Player getPlayerByEmail(String email){
        try{
            TypedQuery<Player> query = em.createNamedQuery("Player.findByEmail", Player.class);
            query.setParameter("email", email);
            List<Player> results = query.getResultList();
            return results.get(0);
        }
        catch(Exception e){
            return null;
        }
    }

    
    public Player getPlayerByName(String name){
        try{
            TypedQuery<Player> query = em.createNamedQuery("Player.findByPlayername", Player.class);
            query.setParameter("playername", name);
            List<Player> results = query.getResultList();
            return results.get(0);
        }
        catch(Exception e){
            return null;
        }
    }
    
    public Player createPlayer(String email, String password, String playerName){
        //Player player = new Player(Integer.SIZE, email, username, 0, password, email)
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);
        player.setPlayername(playerName);
        player.setUid(UUID.randomUUID().toString());

        em.persist(player);
        return player;
    }

    public boolean isEmailTaken(String email) {
        return this.getPlayerByEmail(email) != null;
    }

    public boolean isPlayerNameTaken(String playerName) {
        return this.getPlayerByName(playerName) != null;
    }

    public void updatePlayerScore(Player player, Integer playeronescore) {
        player = this.getPlayerByEmail(player.getEmail());
        player.setPlayerpoints(player.getPlayerpoints() + playeronescore);
        //More score related actions here
        
        em.persist(player);
    }
}
