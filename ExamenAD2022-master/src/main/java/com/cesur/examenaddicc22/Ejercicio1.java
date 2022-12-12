package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {
        
        /* añadir código */
        
        try {




String linea;

FileReader fr = new FileReader ("pom.xml");

BufferedReader br = new BufferedReader(fr);

int i,j,a=0;

while((linea=br.readLine())!=null) {

for(i=0;i<linea.length();i++)
{if(i==0)
   {if(linea.charAt(i)!=' ')
    a++;
   }
   else
   {if(linea.charAt(i-1)==' ')
     if(linea.charAt(i)!=' ')	
       a++;
    
   }	
}
}

System.out.println("Son "+a+" palabras");

fr.close();
}
catch(IOException a){
System.out.println(a);
}

       
//        System.out.println("Ejercicio no resuelto");
    }
    
}
