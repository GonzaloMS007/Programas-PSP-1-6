package PROGRAMA5;
/**
 * PROGRAMA5_PSP
 * @author GONZALO MARTINEZ SILVERIO
 */
import java.util.Scanner; // Importa la clase Scanner para leer la entrada del usuario
import org.apache.commons.math3.distribution.TDistribution; // Importa la clase TDistribution para realizar cálculos estadísticos

public class PROGRAMA5 { //clase principal PROGRAMA5
    static double f(double x, int dof) { // Definimos la función f que calcula la densidad de probabilidad
        TDistribution tDistribution = new TDistribution(dof); // Creamos una nueva distribución t con los grados de libertad especificados
        return tDistribution.density(x); // Devuelve la densidad de probabilidad en el punto x
    }

    static double calcular(double a, double b, int dof, double E) { // Definimos la función calcular que calcula la integral
        int num_seg = 10; // Número inicial de segmentos
        double h = (b - a) / num_seg; // Calculamos el tamaño de cada segmento
        double integral = 0.0; // Inicializamos la integral
        double x;

        for (int i = 0; i <= num_seg; i++) { // Iteracion sobre cada segmento
            x = a + i * h; // Calculamos el valor de x para este segmento
            if (i == 0 || i == num_seg) { // Si es el primer o último segmento
                integral += f(x, dof); // Añadir f(x) a la integral
            } else if (i % 2 == 0) { // Si es un segmento par
                integral += 2 * f(x, dof); // Añadir 2*f(x) a la integral
            } else { // Si es un segmento impar
                integral += 4 * f(x, dof); // Añadir 4*f(x) a la integral
            }
        }

        integral *= h / 3; // Multiplicar la integral por h/3
        double new_integral;

        while (true) { // Bucle infinito
            num_seg *= 2; // Duplica el número de segmentos
            h = (b - a) / num_seg; // Recalcula el tamaño de cada segmento
            new_integral = 0.0; // Inicializamos la nueva integral

            for (int i = 0; i <= num_seg; i++) { // Itera sobre cada segmento
                x = a + i * h; // Calculamos el valor de x para este segmento
                if (i == 0 || i == num_seg) { // Si es el primer o último segmento
                    new_integral += f(x, dof); // Añadir f(x) a la nueva integral
                } else if (i % 2 == 0) { // Si es un segmento par
                    new_integral += 2 * f(x, dof); // Añadir 2*f(x) a la nueva integral
                } else { // Si es un segmento impar
                    new_integral += 4 * f(x, dof); // Añadir 4*f(x) a la nueva integral
                }
            }

            new_integral *= h / 3; // Multiplica la nueva integral por h/3

            if (Math.abs(new_integral - integral) < E) { // Si la diferencia entre la nueva integral y la integral es menor que E
                break; // Rompe el bucle
            }

            integral = new_integral; // Actualiza la integral con el valor de la nueva integral
        }

        return new_integral; // Devuelve la nueva integral
    }

    public static void main(String[] args) { //metodo para iniciar el programa
        System.out.println("INSTITUTO TECNOLOGICO SUPERIOR DE ALAMO TEMAPACHE");      
        Scanner scanner = new Scanner(System.in); // Creamos un nuevo Scanner para leer la entrada del usuario
        double[] x = new double[3]; // Creamos un array para almacenar los valores de x
        int[] dof = new int[3]; // Creamos un array para almacenar los grados de libertad
        double E = 0.00001; // Definimos el error permitido
        double[] valoresEsperados = {0.35006, 0.36757, 0.49500}; // Define los valores esperados
        for (int i = 0; i < 3; i++) { // Itera sobre cada prueba
            System.out.print("Ingrese el valor de x[" + i + "]: "); // Pide al usuario que introduzca el valor de x
            x[i] = scanner.nextDouble(); // Lee el valor de x del usuario
            System.out.print("Ingrese valor de dof [" + i + "]: "); // Pide al usuario que introduzca los grados de libertad
            dof[i] = scanner.nextInt(); // Lee los grados de libertad del usuario
        }
        System.out.println("\nRESULTADOS:");

        for (int i = 0; i < 3; i++) { // Itera sobre cada prueba
            double actual = calcular(0, x[i], dof[i], E); // Calcula la integral
            System.out.printf("Prueba de 0 a x = %.4f, dof = %d\n", x[i], dof[i]); // Imprime los detalles de la prueba
            System.out.printf("Valor Esperado (p): %.5f\n", valoresEsperados[i]); // Imprime el valor esperado
            System.out.printf("Valor Actual: %.5f\n", actual); // Imprime el valor actual
        }
        scanner.close(); // Cierra el Scanner
    }
}