package com.example.simuladortarifas.runner;

import com.example.simuladortarifas.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  //componente ejecutor - muestra el resultado//
public class TarifaRunner implements CommandLineRunner {

    @Autowired
    private TarifaService tarifaService;
    @Value("${tarifa.base}")
    private double base;

    @Value("${tarifa.impuesto}")
    private double impuesto;

    @Override
    public void run(String... args) throws Exception {
        double resultado = tarifaService.calcularTarifa(base, impuesto);

        System.out.println("=== Simulador de Tarifas ===");
        System.out.println("Base: " + base);
        System.out.println("Impuesto: " + impuesto);
        System.out.println("Tarifa final: " + resultado);
    }
}
