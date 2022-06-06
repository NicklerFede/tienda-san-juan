package com.tienda.sanjuan.entidades;

import com.tienda.sanjuan.Enums.Bebes;
import com.tienda.sanjuan.Enums.Ninios;
import com.tienda.sanjuan.Enums.Pantalones;
import com.tienda.sanjuan.Enums.Plazas;
import com.tienda.sanjuan.Enums.RopaNormal;
import com.tienda.sanjuan.Enums.Seccion;
import com.tienda.sanjuan.Enums.TipoMedida;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Articulo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Foto foto;
    private String title;
    private Double price;
    private String color;
    private Integer stock;
    //private Seccion seccion;
    private String material;
    //private String sizea;
    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private Seccion categoria;
    @Enumerated(EnumType.STRING)
    private TipoMedida tipoMedida;
    @Enumerated(EnumType.STRING)
    private RopaNormal ropaComun;
    @Enumerated(EnumType.STRING)
    private Pantalones pantalones;
    @Enumerated(EnumType.STRING)
    private Ninios ninios;
    @Enumerated(EnumType.STRING)
    private Bebes bebes;
    @Enumerated(EnumType.STRING)
    private Plazas plazas;
    private Boolean alta = true;
    private Boolean destacado = false;

    public Articulo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Seccion getCategoria() {
        return categoria;
    }

    public void setCategoria(Seccion categoria) {
        this.categoria = categoria;
    }

    public TipoMedida getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(TipoMedida tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public RopaNormal getRopaComun() {
        return ropaComun;
    }

    public void setRopaComun(RopaNormal ropaComun) {
        this.ropaComun = ropaComun;
    }

    public Pantalones getPantalones() {
        return pantalones;
    }

    public void setPantalones(Pantalones pantalones) {
        this.pantalones = pantalones;
    }

    public Ninios getNinios() {
        return ninios;
    }

    public void setNinios(Ninios ninios) {
        this.ninios = ninios;
    }

    public Bebes getBebes() {
        return bebes;
    }

    public void setBebes(Bebes bebes) {
        this.bebes = bebes;
    }

    public Plazas getPlazas() {
        return plazas;
    }

    public void setPlazas(Plazas plazas) {
        this.plazas = plazas;
    }

}
