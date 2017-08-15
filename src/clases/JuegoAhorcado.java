/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Nany
 */
public class JuegoAhorcado {
    private int oportunidades;  // Número de oportunidades
    private String palabra;     // Palabra a adivinar
    private char letra;      // Entrada del usuario a verificar 
    private String catego;      //Categoria de palabras
    private String display;     // Palabra oculta que se visualizara
    private String[] imagenes = {"a0.jpg","a1.jpg","a2.jpg","a3.jpg","a4.jpg",
                                  "a5.jpg","a6.jpg","a7.jpg","a8.jpg"};
//    private String[] palabras={"perro","vaca","mariposa","delfin","jaguar",
//                               "leon","leopardo","ganso","cotorro","pollo",
//                               "venado","gallina","gata"};
    private String[] palabras;  //<-----
    private boolean[] acertadas;
    private int errores;
    private boolean banInicio;      //Indica si el juego ha iniciado 
    private boolean banNany;      //Indica si seleccionaron categoria 
    
    
    public void inicializar() {
        errores = 0;
        oportunidades = 8;
        banInicio = false;
        
    }
    
    public void cargarPalabras(){
        //Depende de la categoria seleccionada 
        BufferedReader entrada;     //canal de comunicación de lectura
        File archivo=null;          //Nombre del archivo físico
        int num = 0;                //Número de palabras
        String linea=null;          //Usada para leer una línea a la vez
        
        //Se selecciona el archivo
        switch(catego){
            case "Animales":
                archivo = new File(
                        "C:\\datos\\animales.txt");
            break;
            case "TICs":
                archivo = new File(
                        "C:\\datos\\tics.txt");
            break;
            case "Peliculas":
                archivo = new File(
                        "C:\\datos\\peliculas.txt");
            break;
            case "Equipos":
                archivo = new File(
                        "C:\\datos\\equipos.txt");
            break;
            case "Frutas":
                archivo = new File(
                        "C:\\datos\\frutas.txt");
            break;
        }
        try{
            //Realiza la conexión al archivo 
            //Ciclo para contar las palabras
            entrada = new BufferedReader(new FileReader(archivo));
            linea = entrada.readLine();
            while(linea!=null){     //Mientras tenga texto
                num++;
                linea = entrada.readLine();
            }
            entrada.close();        //Cerramos la conexión
            palabras = new String[num];
            //Ciclo de lectura para agregar las palabras
            entrada = new BufferedReader(new FileReader(archivo));
            for ( int i=0; i<num; i++) {
                palabras[i] = entrada.readLine();
            }
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(
                    null, "Error al intentar subir el archivo");
        }
    }
    
    public void seleccionarPalabra(){
        
        Random r1 = new Random();
        int pos = 0;
        pos = r1.nextInt(palabras.length);      // devuelve el número de letras de la palabra o frase
        palabra = palabras[pos];
        acertadas = new boolean[palabra.length()];
        
        
    }
    
    public void generaDisplay(){
        display = "";
        for(int i = 0; i < palabra.length(); i++ ) {
            if ( acertadas[i] ) {
                display += palabra.charAt(i)+" ";
            } else {
                display += "_ ";
            }
        }
    }
    
    public boolean esLetraCorrecta(char letra){
        this.letra = letra;
        boolean banOK=false;
        for ( int i=0; i<palabra.length(); i++) {
            if(letra==palabra.charAt(i)){
                acertadas[i] = true;
                banOK=true;
            }
        }
        if(!banOK && banInicio){
            errores++;
            oportunidades--;
        }
        generaDisplay();
        return banOK;
    }
    
    public boolean yaGano(){
       // boolean banOk = true;       // se asume que ya ganó
        for ( int i = 0; i < acertadas.length; i++) {
            if ( !acertadas[i] ) {
              //  banOk = false;      // No ha ganado 
                return false;
            }
        }
        return true;
    }
    
    public boolean yaPerdio() {
        //return (errores == imagenes.length);      lo mismo pero en una línea.
        if ( errores == imagenes.length - 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getDisplay() {
        return display;
    }
    
    public String getPalabra(){
        return palabra;
    }
    
    public String getImagen(){
        return imagenes[errores];
    }

    public void setCatego(String catego) {
        this.catego = catego;
    }
    
    public void setBanInicio(boolean banInicio) {
        this.banInicio = banInicio;
    }
//    public void setLetra(char letra) {
//        this.letra = letra;
//    }

    public boolean isBanInicio() {
        return banInicio;
    }

    public void setBanNany(boolean banNany) {
        this.banNany = banNany;
    }

    public boolean isBanNany() {
        return banNany;
    }

   
    
    public int getOportunidades() {
        return oportunidades;
    }
}
