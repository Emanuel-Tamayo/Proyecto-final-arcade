import java.io.*;

public class Persistencia {

    private static final double SALDO_INICIAL = 1000.0;

    private static String getCSV() {
        return System.getProperty("user.dir") + File.separator + "jugadores.csv";
    }

    public static Jugador cargarSaldo(String nombre) {
        File archivo = new File(getCSV());

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] partes = linea.split(",");
                    if (partes.length >= 2 && partes[0].trim().equalsIgnoreCase(nombre.trim())) {
                        double saldo = Double.parseDouble(partes[1].trim());
                        System.out.println(Colores.AMARILLO + "  Bienvenido de vuelta, " + nombre + "! Saldo: $" + String.format("%.2f", saldo) + Colores.RESET);
                        return new Jugador(partes[0].trim(), saldo);
                    }
                }
            } catch (IOException e) {
                System.out.println(Colores.ROJO + "  Error al leer jugadores.csv" + Colores.RESET);
            } catch (NumberFormatException e) {
                System.out.println(Colores.ROJO + "  Dato corrupto en jugadores.csv" + Colores.RESET);
            }
        }

        Jugador nuevo = new Jugador(nombre, SALDO_INICIAL);
        System.out.println(Colores.VERDE + "  Nuevo jugador registrado! Saldo inicial: $" + String.format("%.2f", SALDO_INICIAL) + Colores.RESET);
        guardarSaldo(nuevo);
        return nuevo;
    }

    public static void guardarSaldo(Jugador jugador) {
        File archivo = new File(getCSV());

        // Leer lineas existentes en un arreglo estatico (max 100 jugadores)
        String[] lineas = new String[100];
        int total = 0;
        boolean encontrado = false;

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] partes = linea.split(",");
                    if (partes.length >= 2 && partes[0].trim().equalsIgnoreCase(jugador.getNombre())) {
                        lineas[total++] = jugador.getNombre() + "," + String.format("%.2f", jugador.getSaldo());
                        encontrado = true;
                    } else {
                        lineas[total++] = linea.trim();
                    }
                }
            } catch (IOException e) {
                System.out.println(Colores.ROJO + "  Error al leer jugadores.csv" + Colores.RESET);
            }
        }

        if (!encontrado) {
            lineas[total++] = jugador.getNombre() + "," + String.format("%.2f", jugador.getSaldo());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < total; i++) {
                bw.write(lineas[i]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(Colores.ROJO + "  Error al guardar jugadores.csv" + Colores.RESET);
        }
    }

    public static Jugador[] obtenerTodos() {
        File archivo = new File(getCSV());
        Jugador[] jugadores = new Jugador[100];
        int total = 0;

        if (!archivo.exists()) return new Jugador[0];

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    try {
                        jugadores[total++] = new Jugador(partes[0].trim(), Double.parseDouble(partes[1].trim()));
                    } catch (NumberFormatException e) {
                        // linea invalida, ignorar
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(Colores.ROJO + "  Error al leer el ranking." + Colores.RESET);
        }

        // Ordenar por saldo descendente (burbuja)
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - i - 1; j++) {
                if (jugadores[j].getSaldo() < jugadores[j + 1].getSaldo()) {
                    Jugador temp = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = temp;
                }
            }
        }

        // Retornar solo los usados
        Jugador[] resultado = new Jugador[total];
        for (int i = 0; i < total; i++) resultado[i] = jugadores[i];
        return resultado;
    }
}
