/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mastermind;

/**
 *
 * @author HP
 */
import java.util.*;

//Esta clase se encargara principalmente de crear la combinación de colores que el Decodificador debera adivinar, su proposito
//es prácticamente ese

public class CodigoSecreto {

    
    private String codigoSecreto;
    private ArrayList coloresUsables;

    //Constructor
    public CodigoSecreto() {
        codigoSecreto = "";
        coloresUsables = new ArrayList<>(Arrays.asList("Az", "Ve", "Am", "Mo", "Ro", "Ma", "Ci", "Pi"));
    }

    //Este metodo utilizara una variable tipo Random que se encargara de ir generando numeros del 1 al 6,
    //después comoprobaremos si estos numeros no se encuentran ya en el Array de usados, si no es asi, los agregamos al String
    //de colores a adivinar y sumamos 1 al contador, y agregamos ese numero al Array, al final regresamos el codigo secreto generado.
    public String generarCodigoSecreto() {
        Random r = new Random();
        int contador = 0;
        while (contador < 6) {
            int numeroAleatorio = r.nextInt(coloresUsables.size());
            codigoSecreto += coloresUsables.remove(numeroAleatorio);
            contador++;
        }
        return codigoSecreto;
    }

}
class CombinacionGrafica {

    private Canvas canvas;
    private ArrayList<Circulo> combinaciones;
    private int posicionX;
    private int posicionY;
    private int contador;

    //Constructor
    public CombinacionGrafica(Canvas canvasAUsar) {
        combinaciones = new ArrayList();
        canvas = canvasAUsar;
        posicionX = 230;
        posicionY = 655;
        contador = 0;
    }

    //Este metodo se encargara de pintar la combinacion que ingrese el usuario, para esto usa las variables locales
    //limite inferior y limite superior, iremos recorriendo el String combinacion con un while y usando los limites
    //para substring, metodo de clase String con el que sacaremos cada color de la cadena que ingrese el usuario,
    //despues agregamos un cuadro al ArrayList de cuadros y lo pintamos del color correspondiente, sumamos 2 al limite inferior
    //y uno al contador, después recorremos en el eje x, luego en el eje y
    public void mostrarCombinacion(String combinacion) {
        int limiteInferior = 0;
        int limiteSuperior = 0;
        while (limiteSuperior != combinacion.length()) {
            limiteSuperior = limiteInferior + 2;
            String color = combinacion.substring(limiteInferior, limiteSuperior);
            combinaciones.add(new Circulo(30, posicionX, posicionY, canvas, color));
            combinaciones.get(contador).dibujarCuadro();
            limiteInferior = limiteInferior + 2;
            posicionX += 35;
            contador++;
        }
        posicionX = 230;
        posicionY -= 35;
    }

    //Este método se encargará de mostrar el código secreto del juego, para esto usaremos ese código secreto, esto es para saber que tan arriba en el canvas ponerla, después solo obtenemos cada color de la
    //combinacion y lo dibujamos en el canvas, además de un mensaje para indicar que ese es el código secreto
    public void mostrarCodigoSecreto(String codigoSecreto) {
        
        ArrayList<Circulo> codigoSecretoGrafico = new ArrayList();
        int posicionEnX = 230;
        int posicionEnY = 655;
        int limiteInferior = 0;
        int limiteSuperior = 0;
        int contador = 0;
        for (int i = 0; i < 15 + 1; i++) {
            posicionEnY -= 33;
        }
        while (limiteSuperior != codigoSecreto.length()) {
            limiteSuperior = limiteInferior + 2;
            String color = codigoSecreto.substring(limiteInferior, limiteSuperior);
            codigoSecretoGrafico.add(new Circulo(30, posicionEnX, posicionEnY, canvas, color));
            codigoSecretoGrafico.get(contador).dibujarCuadro();
            limiteInferior += 2;
            posicionEnX += 35;
            contador++;
        }
        Interfaz mensaje = new Interfaz("Código secreto", 200, posicionEnY - 15, canvas, "Blanco");
        mensaje.escribirLetra();
    }
}
