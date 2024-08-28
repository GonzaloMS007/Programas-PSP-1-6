package PROGRAMA2;
/*
Programa para contar lineas de codigo(en LOC) lo siguiente:
Tamaño total del programa
Tamaño total de cada una de las partes del programa (clases, 
funciones y procedimientos)
El número de elementos y métodos en cada parte

Produce e imprime:
Un único recuento para todo el programa
Tamaño y recuentos de elementos para cada parte, junto con el 
nombre de la parte (clases, funciones y procedimientos)
*/
/**
 * @author Gonzalo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Programa2 {
    public static void main(String[] args) {
        String filename = "C:/Users/Gonzalo/Desktop/Nueva Carpeta/RelojDigital.java"; 

        int NumeroDeFunciones = 0;
        int NumeroDeProcedimientos = 0;
        int NumeroDeClases = 0;
        int NumeroDeMetodos = 0;
        int NumeroDeLineasLOC = 0;
        boolean Comentarios = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String Lineas;
            while ((Lineas = br.readLine()) != null) {
                Lineas = Lineas.trim();
                // Comentarios líneas de importación y package
                if (Lineas.startsWith("import") || Lineas.startsWith("package")) {
                    continue;
                } 
                if (Comentarios) {
                    if (Lineas.contains("*/")) {
                        Comentarios = false;
                        Lineas = Lineas.substring(Lineas.indexOf("*/") + 2);
                    } else {
                        continue; // Ignorar líneas dentro del comentario
                    }
                }

                if (Lineas.startsWith("//")) {
                    continue; // Ignorar líneas de comentarios de una sola línea
                }

                if (Lineas.contains("/*")) {
                    if (Lineas.contains("*/")) {
                        Lineas = Lineas.substring(0, Lineas.indexOf("/*")) 
                                + Lineas.substring(Lineas.indexOf("*/") + 2);
                    } else {
                        Comentarios = true;
                        Lineas = Lineas.substring(0, Lineas.indexOf("/*"));
                    }
                }

                // No contar líneas que solo contienen "}"
                if (!Lineas.isEmpty() && !Lineas.equals("{") && !Lineas.equals("}")) {
                    NumeroDeLineasLOC++;
                    //System.out.println("Línea de código: " + Lineas); 
                }
                if (Lineas.startsWith("public class")|| Lineas.startsWith("class")) {
                    NumeroDeClases++;

                    System.out.println("Clase: \n" + Lineas);    
                    System.out.println("Numero de clase: " + NumeroDeClases);                    
                }
                
                else if (Lineas.startsWith("public ") 
                        || Lineas.startsWith("private") 
                        || Lineas.startsWith("protected")
                        || Lineas.startsWith("void")) {
                    NumeroDeMetodos++;
                    System.out.println("Método: \n" + Lineas);
                    System.out.println("Numero de método: " + NumeroDeMetodos);
                }
                
                  if (Lineas.contains("public void")
                          || Lineas.contains("public int")
                          || Lineas.contains("public double") 
                          || Lineas.contains("public String")) {
                    NumeroDeFunciones++;
     
                    System.out.println("Función: " + Lineas);
                    System.out.println("Numero de funcion: " + NumeroDeFunciones );
                }
                  else if ( Lineas.contains("public static int") 
                          || Lineas.contains("public static double") 
                          || Lineas.contains("public static String")) {
                    NumeroDeProcedimientos++;
                    System.out.println("Procedimiento: \n" + Lineas);
                    System.out.println("Numero de procedimiento: " + NumeroDeProcedimientos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nCantidad total de clases: " + NumeroDeClases); 
        System.out.println("Cantidad total de métodos: " + NumeroDeMetodos);
        System.out.println("Cantidad total de líneas de código (LOC): " + NumeroDeLineasLOC); 
    }
}

