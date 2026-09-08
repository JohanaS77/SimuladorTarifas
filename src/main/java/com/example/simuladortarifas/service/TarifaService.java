package com.example.simuladortarifas.service;

import org.springframework.stereotype.Service;
//lógica del negocio//
@Service  //dice a Spring uqe esta clase debe ser gestionada por su contenedor//
public class TarifaService {

    public double calcularTarifa(double base, double impuesto) {
        return base + (base * impuesto);
    }
}