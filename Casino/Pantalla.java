public class Pantalla {

    public static final String[] SIMBOLOS = {"7", "X", "$", "*", "@", "#"};

    public static int obtenerMultiplicador(String simbolo) {
        switch (simbolo) {
            case "7": return 10;
            case "$": return 5;
            case "X": return 4;
            case "*": return 3;
            case "@": return 2;
            default:  return 1;
        }
    }

    public static String colorSimbolo(String simbolo) {
        switch (simbolo) {
            case "7": return Colores.AMARILLO + Colores.NEGRITA;
            case "$": return Colores.VERDE    + Colores.NEGRITA;
            case "X": return Colores.ROJO     + Colores.NEGRITA;
            case "*": return Colores.CYAN     + Colores.NEGRITA;
            case "@": return Colores.MAGENTA  + Colores.NEGRITA;
            default:  return Colores.BLANCO;
        }
    }

    public static void girar(String[][] rodillos, boolean easterEggActivo) {
        String[] pool = easterEggActivo
            ? new String[]{"7", "7", "$", "$", "X", "*", "@", "#"}
            : SIMBOLOS;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int idx = (int)(Math.random() * pool.length);
                rodillos[i][j] = pool[idx];
            }
        }
    }

    public static void dibujarPantalla(String[][] rodillos) {
        System.out.println(Colores.AMARILLO + "  +=========+=========+=========+" + Colores.RESET);
        for (int i = 0; i < 3; i++) {
            System.out.print(Colores.AMARILLO + "  |" + Colores.RESET);
            for (int j = 0; j < 3; j++) {
                String s = rodillos[i][j];
                System.out.print("    " + colorSimbolo(s) + s + Colores.RESET + "    ");
                System.out.print(Colores.AMARILLO + "|" + Colores.RESET);
            }
            System.out.println();
            if (i < 2)
                System.out.println(Colores.AMARILLO + "  +=========+=========+=========+" + Colores.RESET);
        }
        System.out.println(Colores.AMARILLO + "  +=========+=========+=========+" + Colores.RESET);
    }

    public static void animarGiro(String[][] rodillos, boolean easterEggActivo) {
        int frames = 8;
        for (int f = 0; f < frames; f++) {
            limpiarConsola();
            imprimirLogo();
            girar(rodillos, false);
            dibujarPantalla(rodillos);
            System.out.println();
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        limpiarConsola();
        imprimirLogo();
        girar(rodillos, easterEggActivo);
        dibujarPantalla(rodillos);
        System.out.println();
    }

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void imprimirLogo() {
    System.out.println(Colores.ROJO + Colores.NEGRITA);
    System.out.println("  .-=========-. ");
    System.out.println("  \\'-=======-'/");
    System.out.println("  _|   .=.   |_");
    System.out.println(" ((|  {{1}}  |))");
    System.out.println("  \\|   /|\\   |/");
    System.out.println("   \\__ '`' __/");
    System.out.println("     _`) (`_");
    System.out.println("   _/_______\\_");
    System.out.println("  /___________\\\\");
    System.out.println();
    System.out.println("   /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$ /$$   /$$  /$$$$$$ ");
    System.out.println("  /$$__  $$ /$$__  $$ /$$__  $$|_  $$_/| $$$ | $$ /$$__  $$");
    System.out.println(" | $$  \\__/| $$  \\ $$| $$  \\__/  | $$  | $$$$| $$| $$  \\ $$");
    System.out.println(" | $$      | $$$$$$$$|  $$$$$$   | $$  | $$ $$ $$| $$  | $$");
    System.out.println(" | $$      | $$__  $$ \\____  $$  | $$  | $$  $$$$| $$  | $$");
    System.out.println(" | $$    $$| $$  | $$ /$$  \\ $$  | $$  | $$\\  $$$| $$  | $$");
    System.out.println(" |  $$$$$$/| $$  | $$|  $$$$$$/ /$$$$$$| $$ \\  $$|  $$$$$$/");
    System.out.println("  \\______/ |__/  |__/ \\______/ |______/|__/  \\__/ \\______/ ");
    System.out.println(Colores.AMARILLO + "        ♠ ♥  L U C K Y   J A C K P O T  ♦ ♣" + Colores.RESET);
    System.out.println();
}

    public static void imprimirTablaSimbolos() {
        System.out.println(Colores.CYAN + "  +----------+--------------------+");
        System.out.println("  | Simbolo  | Premio (x apuesta) |");
        System.out.println("  +----------+--------------------+");
        for (String s : SIMBOLOS) {
            System.out.printf("  | %s%-7s%s | x%-18d|%n",
                colorSimbolo(s), s, Colores.CYAN, obtenerMultiplicador(s));
        }
        System.out.println("  +----------+--------------------+" + Colores.RESET);
    }
}
