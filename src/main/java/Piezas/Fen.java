package main.java.Piezas;

import javax.swing.JOptionPane;
import main.java.vistas.VistaFen;

public class Fen {

//Parte donde se valida que el FEN este completo y legible para el programa de lo contrario salta error
    public static void manejarFen(String fenCompleto, VistaFen vista) {
        if (fenCompleto.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor ingresa una cadena FEN.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] partes = fenCompleto.split(" ");
        if (partes.length < 6) {
            JOptionPane.showMessageDialog(null,
                    "Error: la cadena FEN no tiene las 6 partes requeridas.",
                    "Error FEN",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
//Tomamos la primero parte del FEN "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR" que es el order de las piezas
        String posiciones = partes[0];

        // Genera el tablero en formato plano (64 caracteres) y Muestra el tablero en la interfaz
        String tableroPlano = generarTablero(posiciones);

            vista.mostrarTableroGraficamente(tableroPlano);
    }

// Convierte la notación FEN de posiciones donde las letras son piezas y los numero espacios

    public static String generarTablero(String posiciones) {
// Separa las lineas que tengan un "/" para sacar una linea de texto
        StringBuilder resultado = new StringBuilder();
            String[] filas = posiciones.split("/");

        for (int i = 0; i < 8; i++) {
            String fila = filas[i];
                StringBuilder filaExpandida = new StringBuilder();
// Mira si tiene una letra o un numero y lo convierte en espacio
            for (char c : fila.toCharArray()) {
                if (Character.isDigit(c)) {
                    int espacios = Character.getNumericValue(c);
                        for (int j = 0; j < espacios; j++) {
                            filaExpandida.append(".");
                    }
                } else {
//agrega letras y espacios al texto
                    filaExpandida.append(c);
                }
            }
// vuelve el texto 8x8                       
            resultado.append(filaExpandida);
        }
        return resultado.toString();
    }
}