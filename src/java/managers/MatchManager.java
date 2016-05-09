/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import Entities.Gamematch;
import Entities.Player;
import java.util.Date;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aron
 */
@Named
@RequestScoped
@Stateful
public class MatchManager {
    
    @PersistenceContext(unitName = "SecondJavaWorkPU")
    private EntityManager em;
    
    public Gamematch playGame(Player player){
        Gamematch match = new Gamematch();
        match.setPlayerone(player);
        match.setPlayeronescore(20);
        match.setDateplayed(new Date());
        
        em.persist(match);
        
        return match;
    }
    
}
