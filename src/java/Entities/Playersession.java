/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aron
 */
@Entity
@Table(name = "PLAYERSESSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playersession.findAll", query = "SELECT p FROM Playersession p"),
    @NamedQuery(name = "Playersession.findById", query = "SELECT p FROM Playersession p WHERE p.id = :id"),
    @NamedQuery(name = "Playersession.findByLogindate", query = "SELECT p FROM Playersession p WHERE p.logindate = :logindate")})
public class Playersession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOGINDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logindate;
    @JoinColumn(name = "PLAYER", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Player player;

    public Playersession() {
    }

    public Playersession(Integer id) {
        this.id = id;
    }

    public Playersession(Integer id, Date logindate) {
        this.id = id;
        this.logindate = logindate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playersession)) {
            return false;
        }
        Playersession other = (Playersession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Playersession[ id=" + id + " ]";
    }
    
}
