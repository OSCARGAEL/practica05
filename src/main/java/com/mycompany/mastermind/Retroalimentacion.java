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

//Esta clase se encargara de dar la retroalimentación para las combinaciones que el jugador (Decodificador) ingrese,
//para esto tendrá ArrayList de de objetos tipo Pin, a los que irá dotando de color y que irá regresando a lo largo
//del juego, y que la clase RetroalimentaciónGráfica usaran

public class Retroalimentacion {

    private String codigoSecreto;
    private String codigoDelJugador;
    private ArrayList<String> retroAnteriores;
    private ArrayList<Pin> pinesDeRetroalimentacion;
    private ArrayList<Pin> pinesAnteriores;
    private ArrayList<ArrayList<Pin>> retroalimentacionesPasadas;

    //Método constructor que recibira la cantidad de colores con los que se trabajara, y que
    //creara los pines de retroalimentacion que indicaran los errores y aciertos, además de un
    //ArrayList que guardara las retroalimentaciones pasadas del juego
    public Retroalimentacion() {
        pinesDeRetroalimentacion = new ArrayList();
        retroalimentacionesPasadas = new ArrayList();
        retroAnteriores= new ArrayList<String>();
       
    }

    //En este método recibiremos el codigo secreto y el código ingresado por el jugador
    public void recibirCodigos(String codigoSecreto, String codigoDelJugador) {
        this.codigoSecreto = codigoSecreto;
        this.codigoDelJugador = codigoDelJugador;
    }

    //Es este método realizaremos la retroalimentación entre el código secreto que se genero y
    //el código que el jugador ingreso
    public ArrayList<Pin> hacerRetroalimentacion() {
        vaciarPinesDeRetroalimentacion();
        int limiteInferior = 0;
        int limiteSuperior = 0;
        String colorDelJugador = "";
        String colorDelCodigo = "";
        //Primero comprobaremos si ambos códigos son exactamenet iguales, de ser así, todos
        //los pines que regresaremos serán de color negro
        int comparacion = codigoSecreto.compareTo(codigoDelJugador);
        if (comparacion == 0) {
            for (int i = 0; i < 6; i++) {
                pinesDeRetroalimentacion.add(new Pin("negro"));
            }
        } //Pero si no son iguales, entonces iremos comprobando cada color uno por uno, si los
        //colores coinciden en posicion el pin sera negro, si el color del jugador si esta 
        //ahí pero no en esa posición, entonces el pin será , y si el color que ingresa no esta
        //en el codigo directamente, entonces no pintamos ninguno, por lo que indicamos gris para
        //que no se pinte realemente
        else if (!codigoDelJugador.equalsIgnoreCase(codigoSecreto)) {
            while (limiteSuperior != codigoSecreto.length()) {
                limiteSuperior = limiteInferior + 2;
                colorDelCodigo = codigoSecreto.substring(limiteInferior, limiteSuperior);
                colorDelJugador = codigoDelJugador.substring(limiteInferior, limiteSuperior);

                if (colorDelCodigo.equals(colorDelJugador)) {
                    pinesDeRetroalimentacion.add(new Pin("negro"));
                } else if (codigoSecreto.contains(colorDelJugador)) {
                    pinesDeRetroalimentacion.add(new Pin("blanco"));
                } else if (!codigoSecreto.contains(colorDelJugador)) {
                    pinesDeRetroalimentacion.add(new Pin("gris"));
                }
                limiteInferior += 2;
            }
        }
        //Ya que el juego consiste en deducir, dejarle los pines en el orden que puso los
        //colores seria muy fácil, así los mezclamos antes de regresarlos
        //Collections.shuffle(pinesDeRetroalimentacion);
        retroalimentacionesPasadas.add(pinesDeRetroalimentacion);
        return pinesDeRetroalimentacion;
    }

    //En este método mostraremos las retroalimentaciones pasadas que ocurrieron en el juego, 
    //para esto recibiremos que retroalimentacion se quiere mostrar específícamente, ya que
    //este método se usara mas adelante junto al del decodificador en bucle
    public void mostrarRetroalimentacionesPasadas(int marcador) {
        String retro="";
        System.out.println("Retroalimentacion número " + (marcador + 1) + ": \n");
        pinesAnteriores = retroalimentacionesPasadas.get(marcador);
        if(marcador==0){
        for (int i = 0; i < pinesAnteriores.size(); i++) {
            retro+=pinesAnteriores.get(i).getColor()+" ";
        }
        }
        if(marcador==0){
        retroAnteriores.add(retro);
        }
        System.out.println(retroAnteriores.get(marcador));
        System.out.println("\n");
        
        pinesAnteriores.clear();
    }

    //Con este método mandamos que los pinesDeRetroalimentación se vacíen para no estar mandando
    //las mismas retroalimentaciones constantemente
    public void vaciarPinesDeRetroalimentacion() {
        pinesDeRetroalimentacion.clear();
    }
    
}
class RetroalimentacionGrafica {

    private ArrayList<PinGrafico> pinesDeRetroalimentacion;
    private int posicionEnX;
    private int posicionEnY;
    private Canvas canvas;

    //Metodo constructor, que contendran las posiciones en el eje x, y las posiciones en el eje y inicializadas en ciertos valores, y
    //recibiremos también un Canvas que sera donde trabajaremos
    public RetroalimentacionGrafica(Canvas canvasAUsar) {
        pinesDeRetroalimentacion = new ArrayList();
        posicionEnX = 600;
        posicionEnY = 665;
        canvas = canvasAUsar;
    }

    //Este metodo se encargara de pintar la retroalimentacion de las combinaciones que meta el usuario, para esto recibiremos un 
    //ArrayList de pines graficos, quienes contendran las diferentes indicaciones de negro o blanco
    public void mostrarRetroalimentacion(ArrayList<Pin> pines) {
        for (int i = 0; i < pines.size(); i++) {
            pinesDeRetroalimentacion.add(new PinGrafico(canvas, pines.get(i)));
            pinesDeRetroalimentacion.get(i).pintarPin(posicionEnX, posicionEnY);
            posicionEnX -= 25;
        }
        posicionEnX = 600;
        posicionEnY -= 35;
        pinesDeRetroalimentacion.clear();
    }

    //Este método se encargara de pintar los espacios para los pines, los espacios que están a un
    //lado de la parte donde se colocaran nuestras combinaciones
    public void pintarPinesIniciales() {
        int contador = 0;
        int x = 600;
        int y = 665;
        ArrayList<PinGrafico> pinesIniciales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                pinesIniciales.add(new PinGrafico(canvas));
                pinesIniciales.get(contador).pintarPinEstandar(x, y);
                contador++;
                x -= 25;
            }
            x = 600;
            y -= 35;
        }
    }
}

//La clase a la que pertenecera el jugador, su proposito principal es la de hacer que el jugador ingrese
//las combinaciones de colores, además de comprobar si lo que ingresa es correcto
class Jugador {

    private HashSet combinacionesUsadas;
    private ArrayList<String> coloresUsables;
    private ArrayList<String> combinacionesPasadas;

    //Constructor
    public Jugador() {
        combinacionesUsadas = new HashSet();
        combinacionesPasadas = new ArrayList();
        coloresUsables = new ArrayList<>(Arrays.asList("Az", "Ve", "Am", "Mo", "Ro", "Ma", "Ci", "Pi"));
    }
    //En este método el decodificador se encargara de ingresar una combinacion de colores, seguira haciendo hasta que la combinacion
    //que metio cumpla con las condiciones requeridas, al finalizar, añadimos esa combinaciones a las combinaciones que ya uso, no al
    //HashSet, sino a un arraylist que saremos para ver las combinaciones pasadas en orden
    public String ingresarCombinacion() {
        Scanner scan = new Scanner(System.in);
        boolean combinacionEsValida;
        String combinacionCorregida;
        do {
            System.out.println("Ingrese una combinación de colores: ");
            String combinacion = scan.next();
            System.out.println("La combinacion es " + combinacion);
            combinacionCorregida = corregirCodigo(combinacion);
            combinacionEsValida = comprobarCombinacion(combinacionCorregida);
        } while (!combinacionEsValida);
        combinacionesPasadas.add(combinacionCorregida);
        return combinacionCorregida;
    }

    //Este método se encargara de corregir la combinación que el usuario ingrese, ya que la primera letra del color debe ser
    //mayúscula y la segunda minúscula
    public String corregirCodigo(String combinacion) {
        String codigoCorregido = "";
        String colorActual = "";
        int limiteInferior = 0;
        while (limiteInferior != combinacion.length()) {
            colorActual += combinacion.substring(limiteInferior, limiteInferior + 1).toUpperCase();
            colorActual += combinacion.substring(limiteInferior + 1, limiteInferior + 2).toLowerCase();
            codigoCorregido += colorActual;
            colorActual = "";
            limiteInferior += 2;
        }
        return codigoCorregido;
    }

    //Este método se encargara de comprobar si el usuario esta metiendo una combinación donde se repitan los colores,
    //para esto primero tomamos un color que servira como punto de comparacion, y dentro de otro while, iremos recorriendo
    //la combinacion y comprobaremos cuantas veces se encuentra ese color en la combinacion, si ese conteo es mayor a 1
    //indicamos que la combinacion es invalida, reiniciamos el contador de apariciones y se recorre el primer while para
    //obtener un nuevo color a comparar
    public boolean comprobarColoresRepetidos(String combinacion) {
        String color = "";
        int limiteSuperior = 0;
        int limiteInferior = 0;
        int cantidadDeVecesEnElString = 0;
        boolean combinacionEsValida = true;
        while (limiteSuperior != combinacion.length()) {
            limiteSuperior = limiteInferior + 2;
            color = combinacion.substring(limiteInferior, limiteSuperior);
            int superior = 0;
            int inferior = 0;
            while (superior != combinacion.length()) {
                superior = inferior + 2;
                String colorAComparar = combinacion.substring(inferior, superior);
                if (color.equals(colorAComparar)) {
                    cantidadDeVecesEnElString++;
                }
                inferior += 2;
            }
            if (cantidadDeVecesEnElString > 1) {
                combinacionEsValida = false;
            }
            cantidadDeVecesEnElString = 0;
            limiteInferior += 2;
        }
        return combinacionEsValida;
    }

    //En este método comprobaremos si los colores que el jugador mete si existen, para esto vamos recorriendo cada color de
    //su combinación, y si el color no existe se indica un false, anulando la combinacion
    public boolean coloresExisten(String combinacion) {
        int limiteSuperior = 0;
        int limiteInferior = 0;
        String colorActual = "";
        boolean losColoresExisten = true;
        while (limiteSuperior != combinacion.length()) {
            limiteSuperior = limiteInferior + 2;
            colorActual = combinacion.substring(limiteInferior, limiteSuperior);
            if (!coloresUsables.contains(colorActual)) {
                losColoresExisten = false;
            }
            limiteInferior += 2;
        }
        return losColoresExisten;
    }

    //Se encargará de comprobar si la combinacion que el jugador mete es valida, para esto comprobamos  que no este vacío,
    //que no este ya en el HashSet, que no repita colores, que sus colores existan, si cumple todo eso regresamos un true, pero
    //si alguno no cumple regresamos false
    public boolean comprobarCombinacion(String combinacion) {
        String codigoAComprobar = "";
        codigoAComprobar = combinacion;
        boolean esValido = true;
        if (codigoAComprobar.isBlank() || codigoAComprobar.isEmpty() || codigoAComprobar.equals("\n")) {
            System.out.println("El codigo esta vacio \n");
            esValido = false;
        } else {
            boolean comprobarColoresRepetidos = comprobarColoresRepetidos(codigoAComprobar);
            boolean comprobarColoresExistentes = coloresExisten(combinacion);
            if (codigoAComprobar.length() != 6 * 2) {
                System.out.println("ERROR: El codigo no puede tener colores vacios\n");
                esValido = false;
            } else if (combinacionesUsadas.contains(codigoAComprobar)) {
                System.out.println("ERROR: Codigo repetido. No puede poner el mismo codigo 2 veces\n");
                esValido = false;
            } else if (!comprobarColoresRepetidos) {
                System.out.println("ERROR: No puedes poner el mismo color 2 veces \n");
                esValido = false;
            } else if (!comprobarColoresExistentes) {
                System.out.println("ERROR: Debes poner colores que si existan \n");
                esValido = false;
            }
        }
        if (esValido) {
            combinacionesUsadas.add(codigoAComprobar);
        }
        return esValido;
    }

    //Este método se encargara de mostrar una combinación especifica según lo indique el marcadorDeCombinacion
    public void mostrarCombinacionesPasadas(int marcadorDeCombinacion) {
        System.out.println("Combinacion numero " + (marcadorDeCombinacion + 1) + ": " + combinacionesPasadas.get(marcadorDeCombinacion) + "\n");
    }
}