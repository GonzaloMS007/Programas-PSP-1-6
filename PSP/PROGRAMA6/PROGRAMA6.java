package PROGRAMA6;
/**
 * @author Gonzalo Martinez Silverio
 */
import java.util.Scanner;
import org.apache.commons.math3.distribution.TDistribution;

public class PROGRAMA6 {

// Reutilizando los métodos existentes de la distribución t
    public static double distribucionT(double x, int dof) {
        // Crear un objeto de la clase TDistribution con el grado de libertad dado
        TDistribution tDistribution = new TDistribution(dof);
        // Devolver la densidad de la distribución t en el punto x
        return tDistribution.density(x);
    }
// Reutilizando los métodos existentes de la integrarDistribucionT
    public static double integrarDistribucionT(double a, double b, int dof, double E) {
        // Inicializar el número de segmentos con 10
        int num_seg = 10;
        // Calcular el ancho de cada segmento
        double h = (b - a) / num_seg;
        // Calcular el valor inicial de la integral usando la regla de Simpson
        double valorIntegral = calcularValorIntegral(h, a, dof, num_seg);

        // Repetir el proceso hasta que el error sea menor que el epsilon dado
        while (true) {
            // Duplicar el número de segmentos
            num_seg *= 2;
            // Recalcular el ancho de cada segmento
            h = (b - a) / num_seg;
            // Calcular el nuevo valor de la integral usando la regla de Simpson
            double nuevoValorIntegral = calcularValorIntegral(h, a, dof, num_seg);

            // Verificar si la diferencia entre el nuevo y el viejo valor es menor que el epsilon
            if (Math.abs(nuevoValorIntegral - valorIntegral) < E) {
                // Salir del bucle
                break;
            }

            // Actualizar el valor de la integral con el nuevo valor
            valorIntegral = nuevoValorIntegral;
        }

        // Devolver el valor final de la integral
        return valorIntegral;
    }
// Reutilizando los métodos existentes de la calcularValorIntegral
    private static double calcularValorIntegral(double h, double a, int dof, int num_seg) {
        // Inicializar el valor de la integral con 0
        double valorIntegral = 0.0;
        // Declarar una variable para almacenar el valor de x en cada segmento
        double x;

        // Recorrer todos los segmentos
        for (int i = 0; i <= num_seg; i++) {
            // Calcular el valor de x en el segmento i
            x = a + i * h;
            // Aplicar la fórmula de Simpson según el caso
            if (i == 0 || i == num_seg) {
                // Si es el primer o el último segmento, multiplicar por 1
                valorIntegral += distribucionT(x, dof);
            } else if (i % 2 == 0) {
                // Si es un segmento par, multiplicar por 2
                valorIntegral += 2 * distribucionT(x, dof);
            } else {
                // Si es un segmento impar, multiplicar por 4
                valorIntegral += 4 * distribucionT(x, dof);
            }
        }

        // Multiplicar el valor de la integral por el ancho de cada segmento dividido por 3
        valorIntegral *= h / 3;

        // Devolver el valor de la integral
        return valorIntegral;
    }
// Nuevo método para encontrar x para una probabilidad dada por el usuario
    public static double encontrarXParaProbabilidad(double p, int dof, double E) {
        // Inicializar x con una suposición de 1
        double xAdivinanza = 1.0; 
        // Inicializar d con un valor de ajuste de 0.51
        double d = 0.51; 

        // Repetir el proceso hasta encontrar el valor de x que cumpla con la probabilidad deseada
        while (true) {
            // Calcular la probabilidad actual integrando la distribución t desde 0 hasta x
            double pActual = integrarDistribucionT(0, xAdivinanza, dof, E);

            // Verificar si el resultado actual está dentro del rango de error aceptable
            if (Math.abs(pActual - p) < E) {
                // Salir del bucle
                break;
            }

            // Ajustar x en función de la comparación con la probabilidad objetivo
            if (pActual < p) {
                // Si la probabilidad actual es menor que la deseada, aumentar x
                xAdivinanza += d;
            } else {
                // Si la probabilidad actual es mayor que la deseada, disminuir x
                xAdivinanza -= d;
            }

            // Realizar otra integración y verificar el resultado
            double nuevaP = integrarDistribucionT(0, xAdivinanza, dof, E);

            // Ajustar d en función del cambio en el signo del error
            if (Math.signum(nuevaP - p) != Math.signum(pActual - p)) {
                // Si el signo del error cambia, reducir d a la mitad
                d /= 2.0;
            }
        }

        // Devolver el valor de x encontrado
        return xAdivinanza;
    }
    // Reutilizando y modificado el método main existente
   public static void main(String[] args) {
        // Crear un objeto de la clase Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Crear una matriz de 3x3 para almacenar los resultados
        double[][] resultados = new double[3][3]; 
  
        // Pedir al usuario que ingrese la probabilidad y el grado de libertad para cada caso
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese la probabilidad deseada (p): ");
            double probabilidadObjetivo = scanner.nextDouble();
            System.out.print("Ingrese el valor (dof) para x[" + i + "]: ");
            int dof = scanner.nextInt();
            // Llamar al método encontrarXParaProbabilidad para obtener el valor de x correspondiente
            double xParaProbabilidad = encontrarXParaProbabilidad(probabilidadObjetivo, dof, 0.00001);
             // Almacenar el resultado en la matriz
            resultados[i][0] = probabilidadObjetivo;
            resultados[i][1] = dof;
            resultados[i][2] = xParaProbabilidad;
        }
            // Imprimir los resultados en forma de tabla
            System.out.println("\nRESULTADOS");
            System.out.printf("|  p    | dof  | Resultado\n");  
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %.3f | %-4d | %-17.5f\n", resultados[i][0], (int) resultados[i][1], resultados[i][2]);   
        }
        // Cerrar el objeto Scanner
        scanner.close();
    }
}

/*
+--------------------------------------------+
|               PROGRAMA6                    |
+--------------------------------------------+
| - distribucionT(x, dof)                    |
| - integrarDistribucionT(a, b, dof, E)      |
| - calcularValorIntegral(h, a, dof, num_seg)|
| - encontrarXParaProbabilidad(p, dof, E)    |
| + main(String[] args)                      |
+--------------------------------------------+

*/