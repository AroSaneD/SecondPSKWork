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
@Table(name = "GAMEMATCH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gamematch.findAll", query = "SELECT g FROM Gamematch g"),
    @NamedQuery(name = "Gamematch.findById", query = "SELECT g FROM Gamematch g WHERE g.id = :id"),
    @NamedQuery(name = "Gamematch.findByVictoriousplayer", query = "SELECT g FROM Gamematch g WHERE g.victoriousplayer = :victoriousplayer"),
    @NamedQuery(name = "Gamematch.findByPlayeronescore", query = "SELECT g FROM Gamematch g WHERE g.playeronescore = :playeronescore"),
    @NamedQuery(name = "Gamematch.findByPlayertwoscore", query = "SELECT g FROM Gamematch g WHERE g.playertwoscore = :playertwoscore"),
    @NamedQuery(name = "Gamematch.findByDateplayed", query = "SELECT g FROM Gamematch g WHERE g.dateplayed = :dateplayed")})
public class Gamematch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VICTORIOUSPLAYER")
    private Integer victoriousplayer;
    @Column(name = "PLAYERONESCORE")
    private Integer playeronescore;
    @Column(name = "PLAYERTWOSCORE")
    private Integer playertwoscore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEPLAYED")
    @Temporal(TemporalType.DATE)
    private Date dateplayed;
    @JoinColumn(name = "PLAYERTWO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Player playertwo;
    @JoinColumn(name = "PLAYERONE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Player playerone;

    public Gamematch() {
    }

    public Gamematch(Integer id) {
        this.id = id;
    }

    public Gamematch(Integer id, Date dateplayed) {
        this.id = id;
        this.dateplayed = dateplayed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVictoriousplayer() {
        return victoriousplayer;
    }

    public void setVictoriousplayer(Integer victoriousplayer) {
        this.victoriousplayer = victoriousplayer;
    }

    public Integer getPlayeronescore() {
        return playeronescore;
    }

    public void setPlayeronescore(Integer playeronescore) {
        this.playeronescore = playeronescore;
    }

    public Integer getPlayertwoscore() {
        return playertwoscore;
    }

    public void setPlayertwoscore(Integer playertwoscore) {
        this.playertwoscore = playertwoscore;
    }

    public Date getDateplayed() {
        return dateplayed;
    }

    public void setDateplayed(Date dateplayed) {
        this.dateplayed = dateplayed;
    }

    public Player getPlayertwo() {
        return playertwo;
    }

    public void setPlayertwo(Player playertwo) {
        this.playertwo = playertwo;
    }

    public Player getPlayerone() {
        return playerone;
    }

    public void setPlayerone(Player playerone) {
        this.playerone = playerone;
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
        if (!(object instanceof Gamematch)) {
            return false;
        }
        Gamematch other = (Gamematch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Gamematch[ id=" + id + " ]";
    }
    
}
