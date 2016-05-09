/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import Entities.Player;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Aron
 */
@ApplicationScoped
@Stateful
public class GameManager {
    Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
