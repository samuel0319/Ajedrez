package main.java.vistas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import main.java.Piezas.*;

public class VistaFen extends JFrame {

    private JTextField inputFen;
    private JPanel tableroPanel;
    private static final int TAM_CASILLA = 70;

    public VistaFen() {
        setTitle("Lector FEN - Tablero de Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(8 * TAM_CASILLA + 40, 8 * TAM_CASILLA + 140);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        JLabel lbl = new JLabel("Introduce la notación FEN:");
        inputFen = new JTextField();
        JButton btnMostrar = new JButton("Mostrar tablero");
        panelSuperior.add(lbl, BorderLayout.WEST);
        panelSuperior.add(inputFen, BorderLayout.CENTER);
        panelSuperior.add(btnMostrar, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel del tablero
        tableroPanel = new JPanel(new GridLayout(8, 8));
        add(tableroPanel, BorderLayout.CENTER);

        // Acción del botón llamando a Fen para la logica
        btnMostrar.addActionListener(e -> {
            Fen.manejarFen(inputFen.getText().trim(), VistaFen.this);
        });
    }

// Da forma al tablero, color y se encarga de leer la informacion de Fen para poder poner imagenes de las piezas
    public void mostrarTableroGraficamente(String tableroPlano) {
        tableroPanel.removeAll(); // quita el tablero ya creado por uno nuevo
        Map<Character, String> piezas = obtenerPiezasUnicode();
// recorre todo el texto para poder colocar las piezas
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char simbolo = tableroPlano.charAt(i * 8 + j);
                JPanel casilla = new JPanel(new BorderLayout());
                JLabel etiqueta = new JLabel("", SwingConstants.CENTER);
                etiqueta.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 40));

                // Colores del tablero
                if ((i + j) % 2 == 0)
                    casilla.setBackground(new Color(240, 217, 181)); // claro
                else
                    casilla.setBackground(new Color(181, 136, 99)); // oscuro

                // Colocar pieza si existe
                if (simbolo != '.') {
                    etiqueta.setText(piezas.getOrDefault(simbolo, String.valueOf(simbolo)));
                }

                casilla.add(etiqueta);
                tableroPanel.add(casilla);
            }
        }

        tableroPanel.revalidate();
        tableroPanel.repaint();
    }
// informacion para poder colocar las figuras en las casillas correspondientes
    private Map<Character, String> obtenerPiezasUnicode() {
        Map<Character, String> mapa = new HashMap<>();
        mapa.put('K', "♔");
        mapa.put('Q', "♕");
        mapa.put('R', "♖");
        mapa.put('B', "♗");
        mapa.put('N', "♘");
        mapa.put('P', "♙");
        mapa.put('k', "♚");
        mapa.put('q', "♛");
        mapa.put('r', "♜");
        mapa.put('b', "♝");
        mapa.put('n', "♞");
        mapa.put('p', "♟");
        return mapa;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaFen ventana = new VistaFen();
            ventana.setVisible(true);
        });
    }
}