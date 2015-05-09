/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author idelcano
 */
public class Ficheros {

    private static Ficheros ficheros;

    private Ficheros() {
    }

    ;
    public static Ficheros getInstance() {
        if (ficheros == null) {
            ficheros = new Ficheros();
        }
        return ficheros;
    }

    public BufferedReader cargarFicheroLectura(File fichero) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(fichero)));
        } catch (FileNotFoundException ex) {
            System.out.println("El fichero no existe");
        }
        return null;
    }
    
    public String leerFichero(File fichero) {
        String cadena = "";
        BufferedReader buffer = cargarFicheroLectura(fichero);
        try {
            String linea = "";
            while ((linea = buffer.readLine()) != null) {
                cadena = cadena + linea + "\n";
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if(buffer!=null)
                buffer.close();
            } catch (IOException ex) {
                System.out.println("Error de lectura/escritura cerrando en bufferedreader");
            }
        }
        return cadena;
    }
    
    
    public BufferedWriter cargarFicheroEscritura(File fichero) {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero)));
        } catch (FileNotFoundException ex) {
            System.out.println("El fichero no existe");
        }
        return null;
    }
public String escribirFicheroMensaje(File fichero,String mensaje) {
        String cadenaError="ok";
        BufferedWriter buffer = cargarFicheroEscritura(fichero);
        try {
            buffer.write(mensaje); 
        } catch (IOException ex) {
            cadenaError="Error de escritura en  "+fichero.getAbsolutePath();
            System.out.println("Error de lectura");
        } finally {
            try {
                buffer.close();
            } catch (IOException ex) {
                cadenaError="Error de lectura/escritura cerrando en bufferedwritter  "+fichero.getAbsolutePath();
                System.out.println("Error de lectura/escritura cerrando en bufferedwritter");
            }
        }
        return cadenaError;
    }
public byte[] leerFicheroBinario(File archivoDescifrado) { 
        FileInputStream fos =null;
        byte[] fileContent= null;
        try{
        fos= new FileInputStream(archivoDescifrado);
        byte[] archivo=null;
        fileContent = new byte[(int)archivoDescifrado.length()];
        fos.read(fileContent);
        String s = new String(fileContent); 
        
        } catch (IOException ex) { 
            System.out.println("Error de lectura");
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                 System.out.println("Error de lectura/escritura cerrando en bufferedwritter");
            }
        }
        return fileContent;
    }
    public String escribirFicheroBinario(File archivoDescifrado, byte[] mensaje) {
        String cadenaError="ok"; 
        FileOutputStream fos =null;
        try{
        fos= new FileOutputStream(archivoDescifrado);
        fos.write(mensaje);
        } catch (IOException ex) {
            cadenaError="Error de escritura en  "+archivoDescifrado.getAbsolutePath();
            System.out.println("Error de lectura");
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                cadenaError="Error de lectura/escritura cerrando en bufferedwritter  "+archivoDescifrado.getAbsolutePath();
                System.out.println("Error de lectura/escritura cerrando en bufferedwritter");
            }
        }
        return cadenaError;
    }
}
