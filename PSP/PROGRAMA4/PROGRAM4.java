package PROGRAMA4;
/**
 * INSTITUTO TECNOLOGICO SUPERIOR DE ALAMO TEMAPACHE
 * @author Gonzalo Martinez Silverio
 * nm635542@gmail.com
 */
import java.util.HashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public class PROGRAM4 {
    public static void main(String[] args) {
// Crear un HashMap llamado datosLocMetodo para almacenar datos relacionados con LOC/Método
        Map<String, Double> datosLocMetodo = new HashMap<>();
        datosLocMetodo.put("cada_caracter", 18.0 / 3.0);
        datosLocMetodo.put("lectura_cadena", 18.0 / 3.0);
        datosLocMetodo.put("caracter_individual", 25.0 / 3.0);
        datosLocMetodo.put("cada_linea", 31.0 / 3.0);
        datosLocMetodo.put("caracter_solo", 37.0 / 3.0);
        datosLocMetodo.put("constructor_cadena", 82.0 / 5.0);
        datosLocMetodo.put("gestor_cadena", 82.0 / 4.0);
        datosLocMetodo.put("grupo_lista", 87.0 / 4.0);
        datosLocMetodo.put("recorte_lista", 89.0 / 4.0);
        datosLocMetodo.put("decrementador_cadena", 230.0 / 10.0);
        datosLocMetodo.put("Caracter", 85.0 / 3.0);
        datosLocMetodo.put("Carácter", 87.0 / 3.0);
        datosLocMetodo.put("Convertidor", 558.0 / 10.0);
// Crear un HashMap llamado datosPagsCapitulo  para almacenar datos relacionados con Páginas/Capítulo
        Map<String, Integer> datosPagsCapitulo = new HashMap<>();
        datosPagsCapitulo.put("Prefacio", 7);
        datosPagsCapitulo.put("Capítulo 1", 12);
        datosPagsCapitulo.put("Capítulo 2", 10);
        datosPagsCapitulo.put("Capítulo 3", 12);
        datosPagsCapitulo.put("Capítulo 4", 10);
        datosPagsCapitulo.put("Capítulo 5", 12);
        datosPagsCapitulo.put("Capítulo 6", 12);
        datosPagsCapitulo.put("Capítulo 7", 12);
        datosPagsCapitulo.put("Capítulo 8", 12);
        datosPagsCapitulo.put("Capítulo 9", 8);
        datosPagsCapitulo.put("Apéndice A", 8);
        datosPagsCapitulo.put("Apéndice B", 8);
        datosPagsCapitulo.put("Apéndice C", 20);
        datosPagsCapitulo.put("Apéndice D", 14);
        datosPagsCapitulo.put("Apéndice E", 18);
        datosPagsCapitulo.put("Apéndice F", 12);
// Calcular los tamaños relativos para datosLocMetodo y almacenar los resultados en un nuevo mapa
        Map<String, Double> resultadoDatosLocMetodo = calcularTamañoRelativo(datosLocMetodo);
// Calcular los tamaños relativos para datosPagsCapitulo y almacenar los resultados en un nuevo mapa
        Map<String, Double> resultadoDatosPagsCapitulo = calcularTamañoRelativo(datosPagsCapitulo);
// Imprimir los resultados para datosLocMetodo
        System.out.println("Datos LOC/Método:");
        for (String clave : new String[]{"VS", "S", "M", "L", "VL"}) {
            System.out.println("LOC/Método: " + clave + " = " + resultadoDatosLocMetodo.get(clave));
        }
// Imprimir los resultados para datosPagsCapitulo
        System.out.println("\nDatos Páginas/Capítulo:");
        for (String clave : new String[]{"VS", "S", "M", "L", "VL"}) {
            System.out.println("Páginas/Capítulo: " + clave + " = " + resultadoDatosPagsCapitulo.get(clave));
        }
    }
// Este método calcula los tamaños relativos en base a los datos proporcionados en el mapa 'datos'
    public static Map<String, Double> calcularTamañoRelativo(Map<String, ? extends Number> datos) {
// Transforma los valores en un arreglo de logaritmos naturales
        double[] tamañosLn = datos.values().stream().mapToDouble(value -> Math.log(value.doubleValue())).toArray();
// Calcula la media de los logaritmos naturales
        double media = DoubleStream.of(tamañosLn).average().getAsDouble();
// Calcula la varianza de los logaritmos naturales
        double varianza = DoubleStream.of(tamañosLn).map(x -> Math.pow(x - media, 2)).sum() / (tamañosLn.length - 1);
// Calcula la desviación estándar (sigma) a partir de la varianza
        double sigma = Math.sqrt(varianza);
// Crea un nuevo HashMap llamado rangosLn con categorías VS, S, M, L y VL basadas en la media y sigma
        Map<String, Double> rangosLn = new HashMap<>();
        rangosLn.put("VS", media - 2 * sigma);
        rangosLn.put("S", media - sigma);
        rangosLn.put("M", media);
        rangosLn.put("L", media + sigma);
        rangosLn.put("VL", media + 2 * sigma);
// Crea un nuevo HashMap llamado rangos que contiene los tamaños relativos reales
        Map<String, Double> rangos = new HashMap<>();
        for (Map.Entry<String, Double> entry : rangosLn.entrySet()) {
            rangos.put(entry.getKey(), Math.exp(entry.getValue()));
        }
// Retorna el mapa de tamaños relativos
        return rangos;
    }
}
