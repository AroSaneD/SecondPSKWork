/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

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
import javax.transaction.TransactionSynchronizationRegistry;

/**
 *
 * @author Aron
 */
@Named
@RequestScoped
@Stateful
public class PlayerComponent {
//
//    @PersistenceContext(unitName = "SecondJavaWorkPU")
//    private EntityManager em;
//
//    @Resource
//    private TransactionSynchronizationRegistry tx;
//
//    @Inject
//    private MatchComponent mc;
//
//    
}
