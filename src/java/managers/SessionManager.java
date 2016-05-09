/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import Entities.Player;
import Entities.Playersession;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Aron
 */
@RequestScoped
@Stateful
//@Transactional(Transactional.TxType.REQUIRES_NEW)
public class SessionManager {
    
    @PersistenceContext(unitName = "SecondJavaWorkPU")
    private EntityManager em;
    
    public void UpdateSession(Player player){
        //Fancy session loging logic
        Playersession session = new Playersession();
        session.setPlayer(player);
        session.setLogindate(new Date());
        
        em.persist(session);
    }
    
}
