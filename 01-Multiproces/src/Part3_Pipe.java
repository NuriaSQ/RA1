import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part3_Pipe {
    public static void main(String[] args) throws Exception {

        System.out.println("Sistema operatiu detectat: " + SO.nomSO());

        ProcessBuilder pb1 = new ProcessBuilder(SO.llistarFitxers());
        pb1.directory(new java.io.File(System.getProperty("user.dir")));

        ProcessBuilder pb2 = new ProcessBuilder(SO.filtrar(".java"));

        List<ProcessBuilder> builders = new ArrayList<>();
        builders.add(pb1);
        builders.add(pb2);

        List<Process> pipeline = new ArrayList<>();

        Process p1 = builders.get(0).start();
        Process p2 = builders.get(1).start();

        pipeline.add(p1);
        pipeline.add(p2);

        p1.getInputStream().transferTo(p2.getOutputStream());
        p2.getOutputStream().close();

        BufferedReader br = new BufferedReader(new InputStreamReader(p2.getInputStream()));

        System.out.println("=== Fitxers .java trobats ===");

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        for (Process p : pipeline) {
            p.waitFor();
        }

        System.out.println("\nPipeline completat.");
    }
}