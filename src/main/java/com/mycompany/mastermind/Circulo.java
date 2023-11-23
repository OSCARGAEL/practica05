/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mastermind;

/**
 *
 * @author HP
 */
import java.awt.*;

public class Circulo {
    
    private int radio;
    private int altura;
    private int ancho;
    private int pX;
    private int pY;
    private Color color;
    private Canvas canvas;

    //Constructor de Cuadro, que recibira el alto, el ancho, la posicion x, posicion y, y el canvas donde trabajar
    public Circulo(int radio, int x, int y, Canvas canvasAUsar) {
        this.radio=radio;
        
        pX = x;
        pY = y;
        canvas = canvasAUsar;
        color = new Color(225, 225, 225);
        canvas.setForegroundColor(color);
    }
    //Constructor similar al anterior, pero que aparte recibira el color del Cuadro y se encargara de hacer el cuadro de ese color

    public Circulo(int radio,int x, int y, Canvas canvasAUsar, String color) {
       
        this.radio=radio;
        
        pX = x;
        pY = y;
        canvas = canvasAUsar;
        cambiarColor(color);
    }

//Se encargara de dibujar un cuadro usando como parametros la posicion en x, la posicion en y, la altura y el ancho,
//y ordenando a canvas que dibuje el cuadro con esas caracteristicas
    public void dibujarCuadro() {
        canvas.fillCircle(pX, pY, radio);
    }

//Recibira un String con el nombre del color que queremos poner, dependiendo del color que se reciba, sera el color
//por el que se cambiara el objeto color, después mandamos al canvas a cambiarlo por ese color
    public void cambiarColor(String nuevoColor) {
        if ("Ve".equals(nuevoColor)) {
            color = Color.GREEN;
            //color = new Color(0,255,0);
            canvas.setForegroundColor(color);
        } else if ("Az".equals(nuevoColor)) {
            color = Color.BLUE;
            //color = new Color(0,0,255);
            canvas.setForegroundColor(color);
        } else if ("Am".equals(nuevoColor)) {
            color = Color.YELLOW;
            //color = new Color(255,255,0);
            canvas.setForegroundColor(color);
        } else if ("Ro".equals(nuevoColor)) {
            color = Color.RED;
            //color = new Color(255,0,0);
            canvas.setForegroundColor(color);
        } else if ("Mo".equals(nuevoColor)) {
            color = new Color(138, 43, 226);
            canvas.setForegroundColor(color);
        } else if ("negro".equals(nuevoColor)) {
            color = Color.BLACK;
            //color = new Color(0,0,0);
            canvas.setForegroundColor(color);
        } else if ("Pi".equals(nuevoColor)) {
            color = Color.PINK;
            canvas.setForegroundColor(color);
        } else if ("Ma".equals(nuevoColor)) {
            color = Color.MAGENTA;
            canvas.setForegroundColor(color);
        } else if ("Ci".equals(nuevoColor)) {
            color = Color.CYAN;
            canvas.setForegroundColor(color);
        } else if ("blanco".equals(nuevoColor)) {
            color = Color.white;
            canvas.setForegroundColor(color);
        }else if ("beach".equals(nuevoColor)) {
            color = new Color(160,0,0);
            canvas.setForegroundColor(color);
        }  if ("gris".equals(nuevoColor)) {
            color = new Color(225, 225, 225);
            canvas.setForegroundColor(color);

        }
    }
}
