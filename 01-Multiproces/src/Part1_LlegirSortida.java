import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Part1_LlegirSortida {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());
        System.out.println("=== Contingut del directori ===");

        ProcessBuilder pb = new ProcessBuilder(SO.llistarFitxers());
        pb.redirectErrorStream(true);

        Process p = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linia;

        while ((linia = br.readLine()) != null) {
            System.out.println(linia);
        }

        int codi = p.waitFor();
        System.out.println("\nEl procés ha acabat amb codi: " + codi);
    }
}