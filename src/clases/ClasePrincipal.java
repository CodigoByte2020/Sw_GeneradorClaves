package clases;

public class ClasePrincipal {

    public static void main(String[] args) {

        Lógica objeto = new Lógica();
        String decisión;

        objeto.IngresoDatos();
        objeto.ValidarContenidoCadena();

        if (!Lógica.bandera) {
            objeto.GenerarClave();
        }

        if (!Lógica.bandera) {

            objeto.ImprimirClave();

            do {                
                System.out.println("¿Desea que la clave generada vuelva a su cadena original? (S/N)");
                decisión = Lógica.entrada.nextLine();

                if (decisión.equalsIgnoreCase("S")) {
                    objeto.RevertirClaveGenerada();
                    objeto.ImprimirClaveRevertida();
                } else if (decisión.equalsIgnoreCase("N")) {
                    System.out.println("Ok, el programa finalizará");
                }
                System.out.println("");
            } while (!decisión.equalsIgnoreCase("S") && !decisión.equalsIgnoreCase("N"));
        }
    }
}
