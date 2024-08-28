package PROGRAMA1;
/**
 *
 * @author Gonzalo
 */
import java.util.LinkedList;
import java.util.Scanner;

public class ProgramaPSP0 {
    static double media, varianza, desviacion;
    static int nVeces;

    public void MediaYDesviacionEstandar() {
        // Imprimir información del programa
        System.out.println("INSTITUTO TECNOLOGICO SUPERIOR DE ALAMO TEMAPACHE");
        System.out.println("PSP PROCESO PERSONAL DEL SOFTWARE || PROGRAMA: 01");
        System.out.println("DOCENTE: DR. TANIA TURRUBIATES LOPEZ");
        System.out.println("PERIODO ESCOLAR: AGOSTO 2023 - ENERO 2024");
        System.out.println("ALUMNO: GONZALO MARTINEZ SILVERIO || 202Z0029");
        System.out.println("||  GRUPO: 7S1A  ||");

        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner sc = new Scanner(System.in);
        System.out.println("\n¿Cuantos numeros desea calcular?");
        nVeces = sc.nextInt();

        // Lista enlazada para almacenar los valores
        LinkedList<Double> numeros = new LinkedList<>();
        for (int i = 0; i < nVeces; i++) {
            System.out.println("Ingrese el numero: " + (i+1) + ": ");
            numeros.add(sc.nextDouble());
        }

        // Cálculo de la media
        double suma = 0;
        for (double num : numeros) {
            suma += num;
        }
        media = suma / nVeces;
        System.out.println("La media es: " + media);

        // Cálculo de la varianza
        varianza = 0;
        for (double num : numeros) {
            varianza += Math.pow(num - media, 2);
        }
        varianza = varianza / (nVeces - 1);

        // Cálculo de la desviación estándar
        desviacion = Math.sqrt(varianza);
        
        // Redondear la desviación estándar a dos decimales
        double redondeo = Math.rint(desviacion * 100) / 100;
        System.out.println("LA DESVIACION ESTANDAR ES: " + redondeo);
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase ProgramaPSP0 y llamar al método MediaYDesviacionEstandar()
        ProgramaPSP0 PSP0 = new ProgramaPSP0();
        PSP0.MediaYDesviacionEstandar();
    }
}

/*
Este código Java es un programa que calcula la media y la desviación estándar de un conjunto
de números ingresados por el usuario. A continuación, se explica cómo funciona paso a paso: 
Importa las clases LinkedList y Scanner para trabajar con listas enlazadas y obtener entrada 
del usuario. Declara variables estáticas para la media (media), la varianza (varianza), la 
desviación estándar (desviacion), y la cantidad de números a ingresar (nVeces). Define el método
MediaYDesviacionEstandar() que realiza todo el cálculo. Este método se encarga de:
a. Imprimir información sobre el programa, como el nombre del instituto, el número de programa,
el nombre del docente, el período escolar, el nombre del alumno y el grupo.
b. Crea un objeto Scanner para obtener la entrada del usuario.
c. Pregunta al usuario cuántos números desea calcular (nVeces).
d. Crea una lista enlazada (numeros) para almacenar los valores ingresados por el usuario.
e. Utiliza un bucle for para solicitar al usuario que ingrese nVeces números, y los agrega a
la lista enlazada.
f. Calcula la media sumando todos los valores de la lista y dividiéndolos por nVeces.
g. Calcula la varianza utilizando la fórmula de la varianza, que implica sumar los cuadrados 
de las diferencias entre cada número y la media.
h. Calcula la desviación estándar tomando la raíz cuadrada de la varianza.
i. Redondea la desviación estándar a dos decimales y la muestra en la consola.

En el método main, crea una instancia de la clase ProgramaPSP0 llamada PSP0 y luego llama 
al método MediaYDesviacionEstandar() para iniciar el programa.

En resumen, el programa solicita al usuario una cantidad de números, recopila esos números
en una lista enlazada, calcula la media, la varianza y la des
*/