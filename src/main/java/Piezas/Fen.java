package main.java.Piezas;

import java.util.Scanner;

public class Fen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fen = sc.nextLine();
        sc.close();

        String[] LineaFen = fen.split(" ");

        if (LineaFen.length < 6) {
            System.out.println("Error: la cadena FEN no tiene las 6 partes requeridas.");
            return;
        }

        String posiciones = LineaFen[0];

        String tablero = generarTablero(posiciones);
        System.out.println(tablero);
    }

    public static String generarTablero(String posiciones) {
        StringBuilder resultado = new StringBuilder();
        String[] filas = posiciones.split("/");

        for (int i = 0; i < 8; i++) {
            String fila = filas[i];
            StringBuilder filaExpandida = new StringBuilder();

            for (char c : fila.toCharArray()) {
                if (Character.isDigit(c)) {
                    int espacios = Character.getNumericValue(c);
                    for (int j = 0; j < espacios; j++) {
                        filaExpandida.append(".");
                    }
                } else {
                    filaExpandida.append(c);
                }
            }

            resultado.append(filaExpandida);
        }

        return resultado.toString();
    }
}