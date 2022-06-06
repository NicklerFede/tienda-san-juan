package com.tienda.sanjuan.entidades;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Orden {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    
    @OneToOne
    private Usuario user;
    
    private Double total;
    //private static final Logger LOG = Logger.getLogger(Orden.class.getName());


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Orden(String id, Date purchaseDate, Usuario user, Double total) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.user = user;
        this.total = total;
    }

    public Orden() {
    }

}
