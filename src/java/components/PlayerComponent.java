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

    @PersistenceContext(unitName = "SecondJavaWorkPU")
    private EntityManager em;

    @Resource
    private TransactionSynchronizationRegistry tx;

    @Inject
    private MatchComponent mc;

    @PostConstruct
    private void gimiau() {
        System.out.println(this + " gimiau.");
    }

    @PreDestroy
    private void tuojMirsiu() {
        System.out.println(this + " tuoj mirsiu.");
    }

    public String getFullGreeting() {
        String firstGreeting = this.getHello();
        String secondGreeting = this.getSecondHello();

        return firstGreeting + " | " + secondGreeting;

    }

    public String getHello() {
        return "Hello. " + new Date() + " " + toString();
//        System.out.println(this + " Vykdau dalykinį funkcionalumą, rašau/skaitau DB...");
//        return "Kuku " + new Date() + " " + toString() + " | " + antrasBean.sayKuku();
    }

    public String getSecondHello() {
        return mc.getHello();
    }

    @AfterBegin
    private void afterBeginTransaction() {
        System.out.println(this + " Transakcija: " + tx.getTransactionKey());
    }

    @AfterCompletion
    private void afterTransactionCompletion(boolean commited) {
        System.out.println(this + " Transakcija pasibaigė; commited: " + commited);
    }
}
