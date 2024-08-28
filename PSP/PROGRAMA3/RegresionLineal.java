package PROGRAMA3;
/**
 * INSTITUTO TECNOLOGICO SUPERIOR DE ALAMO TEMAPACHE
 * @AUTOR: Gonzalo Martinez Silverio
 * No. Control: 202Z0029
 * ASIGNATURA: Process Personal Software
 * DOCENTE: Dra Tania Turrubiates Lopez
 */
import java.util.*;

public class RegresionLineal {

    public static LinkedList<Double> obtenerDatos(Scanner scanner, String tipo) {
        System.out.println("Ingrese los datos de " + tipo + " (separados por espacios):");
        String[] input = scanner.nextLine().split(" ");
        LinkedList<Double> datos = new LinkedList<Double>();
        for (String dato : input) {
            datos.add(Double.parseDouble(dato));
        }
        return datos;
    }

    public static void calcularYk(LinkedList<Double> xEstimado,
            LinkedList<Double> yReal, LinkedList<Double> xk, int conjunto) {
        double beta0 = calcularBeta0(xEstimado, yReal);
        double beta1 = calcularBeta1(xEstimado, yReal);
        double rxy = calcularCoeficienteCorrelacion(xEstimado, yReal);
        double r2 = calcularR2(xEstimado, yReal);
        double yk = predecirY(beta0, beta1, xk.get(0));
        
        System.out.println("Resultados para el conjunto " + conjunto + ":");
        System.out.println("B_0 = " + beta0);
        System.out.println("B_1 = " + beta1);
        System.out.println("r_(x,y) = " + rxy);
        System.out.println("r^2 = " + r2);
        System.out.println("y_k = " + yk);
    }

    // Define aquí las funciones calcularBeta0, calcularBeta1, 
    //calcularCoeficienteCorrelacion, calcularR2 y predecirY
    public static double calcularMedia(LinkedList<Double> lista) {
        double suma = 0.0;
        for (double valor : lista) {
            suma += valor;
        }
        return suma / lista.size();
    }

    public static double calcularCoeficienteCorrelacion(LinkedList<Double> x, LinkedList<Double> y) {
        double mediaX = calcularMedia(x);
        double mediaY = calcularMedia(y);
        double numerador = 0.0;
        double denominadorX = 0.0;
        double denominadorY = 0.0;
        
        for (int i = 0; i < x.size(); i++) {
            numerador += (x.get(i) - mediaX) * (y.get(i) - mediaY);
            denominadorX += Math.pow(x.get(i) - mediaX, 2);
            denominadorY += Math.pow(y.get(i) - mediaY, 2);
        }
        return numerador / (Math.sqrt(denominadorX) * Math.sqrt(denominadorY));
    }

    public static double calcularBeta0(LinkedList<Double> x, LinkedList<Double> y) {
        double mediaX = calcularMedia(x);
        double mediaY = calcularMedia(y);
        double rxy = calcularCoeficienteCorrelacion(x, y);
        double beta1 = calcularBeta1(x, y);
        return mediaY - beta1 * mediaX;
    }

    public static double calcularBeta1(LinkedList<Double> x, LinkedList<Double> y) {
        double mediaX = calcularMedia(x);
        double mediaY = calcularMedia(y);
        double numerador = 0.0;
        double denominador = 0.0;

        for (int i = 0; i < x.size(); i++) {
            numerador += (x.get(i) - mediaX) * (y.get(i) - mediaY);
            denominador += Math.pow(x.get(i) - mediaX, 2);
        }
        return numerador / denominador;
    }

    public static double calcularR2(LinkedList<Double> x, LinkedList<Double> y) {
        double rxy = calcularCoeficienteCorrelacion(x, y);
        return rxy * rxy;
    }

    public static double predecirY(double beta0, double beta1, double xk) {
        return beta0 + beta1 * xk;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Datos de entrada
        //Prueba 1
        System.out.println(" * INSTITUTO TECNOLOGICO SUPERIOR DE ALAMO TEMAPACHE\n"+" * @AUTOR: Gonzalo Martinez Silverio\n" +" * No. Control: 202Z0029\n" +" * ASIGNATURA: Process Personal Software\n" +" * DOCENTE: Dra Tania Turrubiates Lopez\n");
        System.out.println("Conjunto 1:");
        LinkedList<Double> xEstimado = obtenerDatos(scanner, "Tamaño de proxy estimado");
        LinkedList<Double> yReal = obtenerDatos(scanner, "Tamaño real agregado y modificado");
        LinkedList<Double> xk = obtenerDatos(scanner, "Proxy (Para las cuatro pruebas)");
        calcularYk(xEstimado, yReal, xk, 1);

        // Prueba 2
        System.out.println("\nConjunto 2:");
        LinkedList<Double> xEstimado2 = obtenerDatos(scanner, "Tamaño de proxy estimado");
        LinkedList<Double> yReal2 = obtenerDatos(scanner, "Tamaño real agregado y modificado");
        calcularYk(xEstimado2, yReal2, xk, 2);

        // Prueba 3
        System.out.println("\nConjunto 3:\n");
        LinkedList<Double> xEstimado3 = obtenerDatos(scanner, "Tamaño de proxy estimado");
        LinkedList<Double> yReal3 = obtenerDatos(scanner, "Tamaño real agregado y modificado");
        calcularYk(xEstimado3, yReal3, xk, 3);

        // Prueba 4
        System.out.println("\nConjunto 4;\n");
        LinkedList<Double> xEstimado4 = obtenerDatos(scanner, "Tamaño de proxy estimado");
        LinkedList<Double> yReal4 = obtenerDatos(scanner, "Tamaño real agregado y modificado");
        calcularYk(xEstimado4, yReal4, xk, 4);
    }
}

/*
EstimatedProxySize 1
130 650 99 150 128 302 95 945 368 961
ActualAddedandModifiedSize 1
186 699 132 272 291 331 199 1890 788 1601
proxy
386

EstimatedProxySize 2
130 650 99 150 128 302 95 945 368 961
ActualAddedandModifiedSize 2
15.0 69.9 6.5 22.4 28.4 65.9 19.4 198.7 38.8 138.2
proxy
386

EstimatedProxySize 3
163 765 141 166 137 355 136 1206 433 1130
ActualAddedandModifiedSize 3
186 699 132 272 291 331 199 1890 788 1601
proxy
386

EstimatedProxySize 4
163 765 141 166 137 355 136 1206 433 1130
ActualAddedandModifiedSize 4
15.0 69.9 6.5 22.4 28.4 65.9 19.4 198.7 38.8 138.2
proxy
386
*/