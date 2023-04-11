package com.platzi.market.persistence.entity;


import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class Cliente {

    @javax.persistence.Id
    private String Id;
    private String nombre;
    private  String apellidos;
    private Long celular;
    private String direccion;

    @Column(name="correo_electronico")
    private String correoElectronico;
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

}
