/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Entities.Gamematch;
import Entities.Player;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import managers.GameManager;
import managers.MatchManager;
import managers.PlayerManager;

/**
 *
 * @author Aron
 */
@Named
@RequestScoped
@Stateful
//@Transactional(Transactional.TxType.REQUIRES_NEW)
public class MatchComponent {
    @Inject 
    private GameManager gameManager;
    
    @Inject 
    private PlayerManager playerManager;
    
    @Inject 
    private MatchManager matchManager;
//
//    @Resource
//    private TransactionSynchronizationRegistry tx;
    
    public void playGame(){
        Player player = gameManager.getPlayer();
        
        //Implement detailed match making mechanics
        Gamematch match = matchManager.playGame(player);
        playerManager.updatePlayerScore(player, match.getPlayeronescore());
        
        
        gameManager.updatePlayer();// setPlayer(player);
    }

}
