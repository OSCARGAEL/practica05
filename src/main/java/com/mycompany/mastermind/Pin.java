/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mastermind;

/**
 *
 * @author HP
 */
//La clase solo se encargará de modular su color en base a lo que la clase Retroalimentación le indique

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Pin {

    private String color;

    //Método constructor que recibira el color que debera tomar el pin, y que usara el pin gráfico más tarde
    public Pin(String colorDePin) {
        color = colorDePin;
    }

    //Método que regresara el color que tiene el pin
    public String getColor() {
        return color;
    }
}
class PinGrafico {

    private ArrayList<Circulo> pines;
    private Canvas canvas;
    private Pin pinAMostrar;

    //El constructor, que recibira el canvas a usar, la cantidad de colores y la cantidad de intentos, y tambien tendrá
    //un objeto tipo Pin que se encargara de simular visualmente según sus características
    public PinGrafico(Canvas canvasAUsar, Pin pinAMostrar) {
        canvas = canvasAUsar;
        this.pinAMostrar = pinAMostrar;
    }

    //Método constructor, a diferencia el otro este solo recibe el canvas a utilizar, esto es porque su trabajo será únicamente el de 
    //llamar al método pintarPinEstandar
    public PinGrafico(Canvas canvasAUsar) {
        canvas = canvasAUsar;
        //pines = new ArrayList();
    }
    // se pintan los circulos de la derecha
    //Este método se encargará de pintar el pinAMostrar que recibe en el constructor, también recibira
    //las posiciones en eje x y eje y, , y hara uso del método getIndicacion() de la clase Pin, para 
    //saber de que color pintarlo específicamente
    public void pintarPin(int posicionEnX, int posicionEnY) {
        Circulo pinGraficado = new Circulo(15, posicionEnX, posicionEnY,
                canvas, pinAMostrar.getColor());
        pinGraficado.dibujarCuadro();
    }
    // se muestra los circulos de la derecha
    //Aquí solo nos encargaremos de pintar un pin de color gris, que se usara para pintar los espacios para los pines que se 
    //colocaran
    public void pintarPinEstandar(int posicionEnX, int posicionEnY) {
        Circulo pinGraficado = new Circulo(15, posicionEnX, posicionEnY, canvas);
        pinGraficado.dibujarCuadro();
    }
}


class Tablero {
    private ArrayList<Circulo> tablero;
    private ArrayList<Circulo> cuadrosInstrucciones;
    private ArrayList<Interfaz> instrucciones;
    private Canvas canvas;
    private int cantidadCuadros;
    private int cantidadIntentos;
    //Metodo constructor, que recibira el canvas a usar, la cantidad de colores en la combinacion a adivinar y la cantidad de 
    //intentos que tendra el usuario, tambien tendremos 3 arrayList, tablero sera un ArrayList de cuadros que guardara los 
    //y cuadrosInstrucciones guardara los cuadros correpondientes a las instrucciones
    public Tablero(Canvas canvasAUsar) {
        tablero = new ArrayList();
        instrucciones = new ArrayList();
        cuadrosInstrucciones = new ArrayList();
        canvas = canvasAUsar;
    }
    //Este metodo dibujara el tablero del juego 
    public void dibujarTablero() {
        int x = 230;
        int y = 655;
        int k = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                tablero.add(new Circulo(30, x, y, canvas));
                tablero.get(k).dibujarCuadro();
                x = x + 35;
                k++;
            }
            x = 230;
            y -= 35;
        }
    }

    //Este metodo pintara en el tablero las instrucciones, es decir, cuales son los colores que puede meter y a que colores
    //corresponden visualmente
    public void dibujarInstrucciones() {
        int posicionEnX = 50;
        int posicionEnY = 650;
        ArrayList<String> indicaciones = new ArrayList<>(Arrays.asList("Azul", "Verde", "Amarillo", "Morado", "Rojo", "Rosa", "Cyan", "Magenta"));
        ArrayList<String> coloresUsables = new ArrayList<>(Arrays.asList("Az", "Ve", "Am", "Mo", "Ro", "Pi", "Ci", "Ma"));
        for (int j = 0; j < indicaciones.size(); j++) {
            instrucciones.add(new Interfaz(coloresUsables.get(j), posicionEnX, posicionEnY, canvas, indicaciones.get(j)));
            instrucciones.get(j).escribirLetra();
            posicionEnY -= 45;
        }
        posicionEnX = 150;
        posicionEnY = 615;
        for (int i = 0; i < indicaciones.size(); i++) {
            cuadrosInstrucciones.add(new Circulo(40, posicionEnX -40, posicionEnY, canvas, coloresUsables.get(i)));
            cuadrosInstrucciones.get(i).dibujarCuadro();
            posicionEnY -= 45;
        }
    }
}

