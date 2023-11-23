/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mastermind;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */

//Clase main Mastermind, el juego en si mismo, que hará uso de las clases MastermindGráfico y MastemindLógico para crear un
//juego de mastermind, este se ejecutara en el método main de la clase, creamos la interfaz donde se verá el juego, y vamos jugando rondas
//donde el jugador ingresara combinaciones hasta que logre adivinar correctamente la que se genera al principio del juego
public class MasterMind {

    private static MasterMindGrafico mastermindGrafico;
    private static MasterMindLogico mastermindLogico;
    private static Scanner scan;

    public static void main(String args[]) {
        int rondasJugadas = 0;
        boolean jugadorGano = false;
        String combinacionDelJugador = "";
        
            mastermindGrafico = new MasterMindGrafico();
            mastermindLogico = new MasterMindLogico();
            mastermindGrafico.crearTableroDeJuego();
            
            String codigoSecreto = mastermindLogico.crearCodigoSecreto();
            System.out.println("CombinacionSecreta: "+codigoSecreto);
            while (rondasJugadas < 15 && !jugadorGano) {
            combinacionDelJugador = mastermindLogico.ingresarCombinacion();
            mastermindGrafico.mostrarCombinacionDelUsuario(combinacionDelJugador);
            mastermindGrafico.mostrarRetroalimentacion(mastermindLogico.
                    darRetroalimentacion(codigoSecreto, combinacionDelJugador));
            jugadorGano = mastermindLogico.detectarVictoria(mastermindLogico.
                    darRetroalimentacion(codigoSecreto, combinacionDelJugador));
            System.out.println("Combinaciones y retraolimentaciones\n");
            rondasJugadas++;
            for (int i = 0; i < rondasJugadas; i++) {
            mastermindLogico.mostrarJugadasPasadas(i);
            }
            }
            
            if (jugadorGano) {
                System.out.println("Has adivinado la combinacion, has ganado");
                mastermindGrafico.mostrarCodigoSecreto(codigoSecreto);
            } else {
                System.out.println("Se acabo el juego, mostrando combinacion");
                mastermindGrafico.mostrarCodigoSecreto(codigoSecreto);
                
            }
            
        }
    }


//Clase de la parte logica del mastermind, que se encargara de ordenar a los objetos de otras clases que realicen sus funciones,
//al CódigoSecreto que genere uno, al Decodificador que ingrese sus combinaciones, y a la Retroalimentación, para que haga
//sus retroalimentaciónes, también detectara cuando gane
class MasterMindLogico {

    private CodigoSecreto codigoSecreto;
    private Jugador jugador;
    private Retroalimentacion retroalimentador;
    

    //Constructor Mastermind
    public MasterMindLogico() {
        codigoSecreto = new CodigoSecreto();
        jugador = new Jugador();
        retroalimentador = new Retroalimentacion();
    }


    //En este método indicaremos al objeto codigoSecreto que cree un código secreto, luego 
    //regresaremos ese codigo
    public String crearCodigoSecreto() {
        String codigoSecretoString;
        codigoSecretoString = codigoSecreto.generarCodigoSecreto();
        return codigoSecretoString;
    }

    //En este método sera donde el jugador ingresara su combinacion de colores, y esta 
    //combinación la regresaremos
    public String ingresarCombinacion() {
        String combinacion;
        combinacion = jugador.ingresarCombinacion();
        return combinacion;
    }

    //En este método será donde indicaremos que retroalimentación tiene la combinacion que ingreso
    //el jugador
    public ArrayList<Pin> darRetroalimentacion(String codigoSecreto, String codigoDelJugador) {
        retroalimentador.recibirCodigos(codigoSecreto, codigoDelJugador);
        ArrayList<Pin> pinesRetroalimentadores = retroalimentador.hacerRetroalimentacion();
        return pinesRetroalimentadores;
    }

    //Este método se encargará de mostrar cuales fueron las combinaciones pasadas del jugador y cuales
    //fueron sus respectivas retroalimentaciones pasadas
    public void mostrarJugadasPasadas(int marcador) {
        jugador.mostrarCombinacionesPasadas(marcador);
        retroalimentador.mostrarRetroalimentacionesPasadas(marcador);
    }

    //Este método se encargara de detectar si el jugador gano, esto se sabra si la cantidad
    //de pines negros es igual a la cantidad de colores en la combinacion, si esto ocurre, regresamos
    //un booleano igual a true, y si no ocurre, se regresa un false
    public boolean detectarVictoria(ArrayList<Pin> retroalimentacionDada) {
        int cantidadDePinesNegros = 0;
        boolean jugadorGano = false;
        for (int i = 0; i < retroalimentacionDada.size(); i++) {
            if (retroalimentacionDada.get(i).getColor().equals("negro")) {
                cantidadDePinesNegros++;
            }
        }
        if (cantidadDePinesNegros == 6) {
            jugadorGano = true;
            System.out.println("Has adivinado la combinacion, has ganado \n");
        }
        return jugadorGano;
    }
}

//MastermindGráfico se encargara de todo lo que implique mostrar cosas en pantalla, como el tablero de juego, las 
//combinaciones del jugador, las retroalimenaciones dadas, y de mostrar el código secreto al final del juego
class MasterMindGrafico {

    private Canvas canvas;
    private CombinacionGrafica combinacionGraficada;
    private RetroalimentacionGrafica retroalimentacionGraficada;
    private Tablero tablero;


    //Constructor
    public MasterMindGrafico() {
        canvas = new Canvas("MasterMind");
        combinacionGraficada = new CombinacionGrafica(canvas);
        retroalimentacionGraficada = new RetroalimentacionGrafica(canvas);
        tablero = new Tablero(canvas);
    }

    //Este método se encargara de únicamente de dibujar el tablero que vemos al inicio del juego
    public void crearTableroDeJuego() {
        tablero.dibujarTablero();
        tablero.dibujarInstrucciones();
        retroalimentacionGraficada.pintarPinesIniciales();

    }

    //En este método lo único que haremos sera ordenar a la combinacionGraficada que muestre el código secreto usando también
    //la cantidad de intentos
    public void mostrarCodigoSecreto(String codigoSecreto) {
        
        combinacionGraficada.mostrarCodigoSecreto(codigoSecreto);
        
    }

    //Este método se hará cargo de mostrar la combinación que el usuario ingresa en el tablero
    public void mostrarCombinacionDelUsuario(String combinacion) {
        combinacionGraficada.mostrarCombinacion(combinacion);
    }

    //Aquí recibiremos los pines de retroalimentación del jugador para que la retroalimenación gráfica los muestre
    public void mostrarRetroalimentacion(ArrayList<Pin> pinesDeRetroalimentacion) {
        retroalimentacionGraficada.mostrarRetroalimentacion(pinesDeRetroalimentacion);
    }
}

