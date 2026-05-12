import java.util.Scanner;

public class LuckyArcade {

    private static Scanner sc = new Scanner(System.in);
    private static Jugador jugador;
    private static String[][] rodillos = new String[3][3];

    private static boolean easterEggActivo = false;
    private static int turnosEasterEgg = 0;
    private static boolean minijuegoRealizado = false;

    public static void main(String[] args) {
        Pantalla.limpiarConsola();
        Pantalla.imprimirLogo();
        jugador = iniciarSesion();

        boolean jugando = true;
        while (jugando) {
            mostrarMenu();
            int opcion = leerEnteroSeguro("  -> Elige una opcion: ", 1, 4);
            switch (opcion) {
                case 1: apostar();   break;
                case 2: verSaldo();  break;
                case 3: verRanking(); break;
                case 4: jugando = false; salir(); break;
            }
        }
    }

    // ─── INICIO DE SESION ───────────────────────────────
    private static Jugador iniciarSesion() {
        System.out.println(Colores.CYAN
            + "  +==============================+"
            + "\n  |   BIENVENIDO AL CASINO       |"
            + "\n  +==============================+" + Colores.RESET);
        System.out.print(Colores.BLANCO + "  Ingresa tu nombre: " + Colores.RESET);
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) nombre = "Anonimo";

        Jugador j = Persistencia.cargarSaldo(nombre);

        if (nombre.equalsIgnoreCase("LUCKY")) {
            activarEasterEgg(j, 0);
        }
        return j;
    }

    // ─── MENU ────────────────────────────────────────────
    private static void mostrarMenu() {
        System.out.println();
        System.out.println(Colores.AMARILLO + "  +--------------------------------+");
        System.out.println("  |        MENU PRINCIPAL          |");
        System.out.println("  +--------------------------------+");
        System.out.printf("  |  Jugador: %-21s|%n", jugador.getNombre());
        System.out.printf("  |  Saldo:   $%-20s|%n", String.format("%.2f", jugador.getSaldo()));
        if (easterEggActivo && turnosEasterEgg > 0)
            System.out.printf("  |  " + Colores.VERDE + "AMULETO ACTIVO (%d turnos)     " + Colores.AMARILLO + "|%n", turnosEasterEgg);
        System.out.println("  +--------------------------------+");
        System.out.println("  |  1. Apostar                    |");
        System.out.println("  |  2. Ver Saldo                  |");
        System.out.println("  |  3. Ranking                    |");
        System.out.println("  |  4. Salir                      |");
        System.out.println("  +--------------------------------+" + Colores.RESET);
    }

    // ─── APOSTAR ─────────────────────────────────────────
    private static void apostar() {
        if (jugador.getSaldo() <= 0) {
            System.out.println(Colores.ROJO + "  Sin saldo! No puedes apostar." + Colores.RESET);
            return;
        }

        Pantalla.imprimirTablaSimbolos();
        System.out.printf("%n  Tu saldo actual: $%.2f%n", jugador.getSaldo());
        System.out.print(Colores.BLANCO + "  Cuanto deseas apostar? $" + Colores.RESET);
        double apuesta = leerDoubleSeguro();

        if (apuesta <= 0 || apuesta > jugador.getSaldo()) {
            System.out.println(Colores.ROJO + "  Apuesta invalida." + Colores.RESET);
            return;
        }

        // Easter Egg apuesta 777
        if (apuesta == 777 && !minijuegoRealizado) {
            activarEasterEgg(jugador, apuesta);
            return;
        }

        jugador.restarSaldo(apuesta);
        Persistencia.guardarSaldo(jugador);

        Pantalla.animarGiro(rodillos, easterEggActivo);

        System.out.println(Colores.BLANCO + "  ── Resultado ──────────────────" + Colores.RESET);
        double premio = Premios.calcularPremio(rodillos, apuesta);

        if (premio > 0) {
            jugador.agregarSaldo(premio);
            System.out.println(Colores.VERDE + Colores.NEGRITA
                + "  Premio total ganado: $" + String.format("%.2f", premio) + Colores.RESET);
        } else {
            System.out.println(Colores.ROJO + "  Sin premio esta vez. Suerte la proxima!" + Colores.RESET);
        }

        System.out.printf(Colores.CYAN + "  Saldo actualizado: $%.2f%n" + Colores.RESET, jugador.getSaldo());
        Persistencia.guardarSaldo(jugador);

        if (easterEggActivo && turnosEasterEgg > 0) {
            turnosEasterEgg--;
            if (turnosEasterEgg == 0) {
                easterEggActivo = false;
                System.out.println(Colores.AMARILLO + "  El amuleto se ha agotado." + Colores.RESET);
            }
        }

        pausar();
    }

    // ─── VER SALDO ───────────────────────────────────────
    private static void verSaldo() {
        System.out.println();
        System.out.println(Colores.CYAN + "  +================================+");
        System.out.printf("  |  Jugador: %-21s|%n", jugador.getNombre());
        System.out.printf("  |  Saldo:   $%-20s|%n", String.format("%.2f", jugador.getSaldo()));
        System.out.println("  +================================+" + Colores.RESET);
        pausar();
    }

    // ─── RANKING ─────────────────────────────────────────
    private static void verRanking() {
        Jugador[] todos = Persistencia.obtenerTodos();
        System.out.println();
        System.out.println(Colores.AMARILLO + "  +====+====================+=============+");
        System.out.println("  | #  | Jugador            | Saldo       |");
        System.out.println("  +====+====================+=============+" + Colores.RESET);

        if (todos.length == 0) {
            System.out.println(Colores.BLANCO + "  |     Sin jugadores registrados         |" + Colores.RESET);
        } else {
            for (int i = 0; i < todos.length; i++) {
                String pos = " " + (i + 1) + ".";
                String nombre = todos[i].getNombre();
                if (nombre.length() > 18) nombre = nombre.substring(0, 18);
                String saldo = "$" + String.format("%.2f", todos[i].getSaldo());
                System.out.printf(Colores.BLANCO + "  |%-4s| %-18s | %-11s |%n" + Colores.RESET,
                    pos, nombre, saldo);
            }
        }

        System.out.println(Colores.AMARILLO + "  +====+====================+=============+" + Colores.RESET);
        pausar();
    }

    // ─── SALIR ───────────────────────────────────────────
    private static void salir() {
        Persistencia.guardarSaldo(jugador);
        Pantalla.limpiarConsola();
        System.out.println(Colores.AMARILLO + Colores.NEGRITA);
        System.out.println("  +====================================+");
        System.out.printf("  |  Hasta luego, %-20s|%n", jugador.getNombre() + "!");
        System.out.printf("  |  Saldo final: $%-19s|%n", String.format("%.2f", jugador.getSaldo()));
        System.out.println("  |  Vuelve pronto al Lucky Arcade!   |");
        System.out.println("  +====================================+");
        System.out.println(Colores.RESET);
        sc.close();
    }

    // ─── EASTER EGG ──────────────────────────────────────
    private static void activarEasterEgg(Jugador j, double apuesta) {
        System.out.println(Colores.AMARILLO + Colores.NEGRITA);
        System.out.println("  +====================================+");
        System.out.println("  |    *** EASTER EGG ACTIVADO! ***    |");
        System.out.println("  |       EL AMULETO DE LA SUERTE      |");
        System.out.println("  +====================================+" + Colores.RESET);

        if (apuesta == 777) {
            System.out.println(Colores.CYAN + "  Apuesta magica 777! Activando mini-juego de dados..." + Colores.RESET);
            pausar();
            double ganancia = Premios.minijuegoDados(apuesta);
            j.restarSaldo(apuesta);
            j.agregarSaldo(ganancia);
            Persistencia.guardarSaldo(j);
            minijuegoRealizado = true;
        } else {
            System.out.println(Colores.VERDE + "  Eres LUCKY! Las probabilidades se duplican por 3 turnos." + Colores.RESET);
            easterEggActivo = true;
            turnosEasterEgg = 3;
        }
        pausar();
    }

    // ─── UTILIDADES ──────────────────────────────────────
    private static int leerEnteroSeguro(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.println(Colores.ROJO + "  Opcion fuera de rango (" + min + "-" + max + ")." + Colores.RESET);
            } catch (NumberFormatException e) {
                System.out.println(Colores.ROJO + "  Ingresa un numero valido." + Colores.RESET);
            }
        }
    }

    private static double leerDoubleSeguro() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print(Colores.ROJO + "  Numero invalido. Intenta de nuevo: $" + Colores.RESET);
            }
        }
    }

    private static void pausar() {
        System.out.print(Colores.BLANCO + "\n  [Presiona ENTER para continuar...]" + Colores.RESET);
        sc.nextLine();
        Pantalla.limpiarConsola();
        Pantalla.imprimirLogo();
    }
}
