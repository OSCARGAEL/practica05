/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mastermind;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author HP
 */

import java.util.*;

public class Interfaz {

    private String texto;
    private Color color;
    private int posicionX;
    private int posicionY;
    private int tamaño;
    private Canvas canvas;
    private Font font;

    //Metodo constructor que recibira el texto a escribir, la posicion en x, la posicion en y, el canvas a usar y el color
    //del que queremos pintar el texto
    public Interfaz(String text, int x, int y, Canvas canvasAUsar, String color) {
        texto = text;
        posicionX = x;
        posicionY = y;
        tamaño = 35;
        canvas = canvasAUsar;
        font = new Font("Monospaced", Font.BOLD, tamaño);
        canvas.setFont(font);
        cambiarColor(color);
    }

    //Usamos el método drawString de canvas, enviandole los parametros que nos 
    //llegan al constructor de la clase, el texto a escribir, las posiciones
    //en x y y el color con el que escribiremos.
    public void escribirLetra() {
        canvas.drawString(texto, posicionX, posicionY, color);
    }

    //Con este metodo, cambiaremos el color de las letra a escribir dependiendo de el color que llegue como parametro
    public void cambiarColor(String c) {
        if (c.equals("Azul")) {
            color = new Color(0, 0, 255);
            canvas.setForegroundColor(color);
        } else if (c.equals("Verde")) {
            color = new Color(0, 255, 0);
            canvas.setForegroundColor(color);
        } else if (c.equals("Amarillo")) {
            color = new Color(255, 255, 0);
            canvas.setForegroundColor(color);
        } else if (c.equals("Rojo")) {
            color = new Color(255, 0, 0);
            canvas.setForegroundColor(color);
        } else if (c.equals("Morado")) {
            color = new Color(138, 43, 226);
            canvas.setForegroundColor(color);
        } else if (c.equals("Negro")) {
            color = new Color(0, 0, 0);
            canvas.setForegroundColor(color);
        } else if (c.equals("Rosa")) {
            color = Color.PINK;
            canvas.setForegroundColor(color);
        } else if (c.equals("Cyan")) {
            color = Color.CYAN;
            canvas.setForegroundColor(color);
        } else if (c.equals("Magenta")) {
            color = Color.MAGENTA;
            canvas.setForegroundColor(color);
        } else if (c.equals("Blanco")) {
            color = Color.WHITE;
            canvas.setForegroundColor(color);
        }
    }

    //Este método nos permite cambiar el tamaño con el que se escribiran las letras
    //para hacer esto sobreescribimos el font actual (la fuente que usamos para
    //escribir) con las mismas especificaciones pero cambiando el tamaño, luego
    //la colocamos con setFont de canvas.
    public void cambiarTamaño(int t) {
        tamaño = t;
        font = new Font("Monospaced", Font.BOLD, tamaño);
        canvas.setFont(font);
    }

}
