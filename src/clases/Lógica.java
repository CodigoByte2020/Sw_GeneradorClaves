package clases;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Lógica {

    private List<Integer> ListaCadenaOriginal = new ArrayList<>();
    private List<Integer> ClaveGenerada = new ArrayList<>();
    private List<Integer> ClaveRevertida = new ArrayList<>();
    private String CadenaOriginal;
    public static boolean bandera = false;
    public static Scanner entrada = new Scanner(System.in);
    private int Contador0;

    //Validamos la longitud de la cadena de texto ingresada
    public String IngresoDatos() {

        do {
            System.out.println("************************");
            System.out.println("* GENERADOR DE CLAVES: *");
            System.out.println("************************");
            System.out.println("");
            System.out.println(" La cadena de texto:");
            System.out.println(" - Debe contener entre 30 y 100 caracteres.");
            System.out.println(" - Debe estar compuesta de 0 y 1.");
            System.out.println(" - Debe tener como máximo 9 ceros seguidos");
            System.out.print(" Ingrese la cadena de texto a cifrar: ");
            CadenaOriginal = entrada.nextLine();

            if (CadenaOriginal.length() < 30 || CadenaOriginal.length() > 100) {
                System.out.println("Longitud de cadena fuera del rango, por favor ingrese de nuevo la cadena de texto a cifrar.");
                System.out.println("");
            }
        } while (CadenaOriginal.length() < 30 || CadenaOriginal.length() > 100);

        return CadenaOriginal;
    }

    /*Validamos que cada elemento ingresado sea 0 o 1, si es así los guardamos en la ListaCadenaOriginal, 
    sino el programa finalizará*/
    public List<Integer> ValidarContenidoCadena() {

        for (int i = 0, j = 1; j <= CadenaOriginal.length() && bandera == false; i++, j++) {
            switch (CadenaOriginal.substring(i, j)) {
                case "0":
                    ListaCadenaOriginal.add(0);
                    break;
                case "1":
                    ListaCadenaOriginal.add(1);
                    break;
                default:
                    bandera = true;
                    System.out.println("Caracter no permitido en la cadena, el programa finalizará.");
                    break;
            }
        }

        return ListaCadenaOriginal;
    }

    /*Contamos todos los 0, y esta suma será un digito de la clave secreta, si hay dos unos seguidos el dígito 
      clave será 0.
      Si hay más de 9 ceros seguidos el programa finalizará y cada vez que hay un 1 el Contador0 se agrega a la 
      ClaveGenerada luego el Contador0 se reinicia*/
    public List<Integer> GenerarClave() {

        for (int i = 0; i < ListaCadenaOriginal.size() && bandera == false; i++) {
            if (i == 0) {
                if (ListaCadenaOriginal.get(i) == 0) {
                    Contador0 += 1;
                }
            } else if (i >= 1 && i <= ListaCadenaOriginal.size() - 2) {
                if (ListaCadenaOriginal.get(i) == 0) {
                    if (Contador0 < 9) {
                        Contador0 += 1;
                    } else {
                        bandera = true;
                        System.out.println("Se encontraron mas de 9 ceros seguidos, el programa finalizará");
                    }
                } else if (ListaCadenaOriginal.get(i) == 1) {
                    ClaveGenerada.add(Contador0);
                    Contador0 = 0;
                }
            } else if (i == ListaCadenaOriginal.size() - 1) {
                if (ListaCadenaOriginal.get(i) == 0) {
                    if (Contador0 < 9) {
                        Contador0 += 1;
                    } else {
                        bandera = true;
                        System.out.println("Se encontraron mas de 9 ceros seguidos, el programa finalizará");
                    }
                }
                ClaveGenerada.add(Contador0);
            }
        }

        return ClaveGenerada;
    }

    //Imprimimos la clave de forma invertida
    public void ImprimirClave() {

        System.out.print(" Clave generada: ");
        for (int i = ClaveGenerada.size() - 1; i >= 0; i--) {
            System.out.print(ClaveGenerada.get(i));
        }
        System.out.println("");
    }

    public List<Integer> RevertirClaveGenerada() {

        //Guardamos el primer elemento de la ListaCadenaOriginal en la primera posición de la ClaveRevertida
        if (ListaCadenaOriginal.get(0) == 1) {
            ClaveRevertida.add(1);
        }

        /*Guardamos ceros y unos en la ClaveRevertida, cantidad determinada según el valor de cada elemento de la 
        ClaveGenerada*/
        for (int i = 0; i < ClaveGenerada.size(); i++) {
            for (int j = 0; j < ClaveGenerada.get(i); j++) {
                ClaveRevertida.add(0);
            }
            if (i < ClaveGenerada.size() - 1) {
                ClaveRevertida.add(1);
            }
        }

        //Guardamos el último elemento de la ListaCadenaOriginal en la última posición de la ClaveRevertida
        if (ListaCadenaOriginal.get(ListaCadenaOriginal.size() - 1) == 1) {
            ClaveRevertida.add(1);
        }

        return ClaveRevertida;
    }

    public void ImprimirClaveRevertida() {

        System.out.println("Clave revertida a su cadena original: ");
        for (Integer i : ClaveRevertida) {
            System.out.print(i);
        }
    }
}
