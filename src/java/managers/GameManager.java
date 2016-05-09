/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import Entities.Player;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Aron
 */
@ApplicationScoped
@Stateful
public class GameManager {
    
    @Inject
    private SessionManager sessionManager;
    
    Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        sessionManager.UpdateSession(player);
        this.player = player;
    }
}
