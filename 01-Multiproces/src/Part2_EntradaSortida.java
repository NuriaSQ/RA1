import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Part2_EntradaSortida {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());

        ProcessBuilder pb = new ProcessBuilder(SO.ordenar());
        pb.redirectErrorStream(true);

        Process p = pb.start();

        PrintWriter pw = new PrintWriter(p.getOutputStream(), true);

        String[] fruites = {"plàtan", "poma", "albergínia", "cireres", "maduixa"};

        System.out.println("Enviem al procés 'sort':");

        for (String f : fruites) {
            System.out.println("    -> " + f);
            pw.println(f);
        }

        pw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        System.out.println("\nResposta del procés 'sort' (ordenat):");

        String linia;
        while ((linia = br.readLine()) != null) {
            System.out.println("    <- " + linia);
        }

        int codi = p.waitFor();
        System.out.println("\nCodi de retorn: " + codi);
    }
}