import java.io.*;
import java.security.*;
import java.util.*;
import org.apache.commons.io.*;


public class Equalize {

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) throws Exception {
        List<String> normal = new ArrayList<String>();
        List<String> attack = new ArrayList<String>();
        Set<String> massa = new LinkedHashSet<String>();

        for (String s : new String[] {"/kddcup99/kddcup_treino_ordenado_registros_unicos.csv.arff"}) {
            File file = new File(s);
            List<String> lines = FileUtils.readLines(file);
            for (String line : lines) {
                if (line.endsWith("normal.")) {
                    normal.add(line);
                } else {
                    attack.add(line);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            massa.clear();
            massa.addAll(attack);
            for (int j = 0; j < 262178; j++) {
                String value = normal.get(random.nextInt(normal.size()));
                if (massa.contains(value)) {
                    System.out.println("colisao");
                    j--;
                }
                massa.add(value);
            }
            FileUtils.writeLines(new File("/kddcup99/kdd_treino" + "_" + i + ".arff"), massa);
        }
    }
}
import java.io.*;
import java.security.*;
import java.util.*;
import org.apache.commons.io.*;


public class Equalize {

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) throws Exception {
        List<String> normal = new ArrayList<String>();
        List<String> attack = new ArrayList<String>();
        Set<String> massa = new LinkedHashSet<String>();

        for (String s : new String[] {"/kddcup99/kddcup_treino_ordenado_registros_unicos.csv"}) {
            File file = new File(s);
            List<String> lines = FileUtils.readLines(file);
            for (String line : lines) {
                if (line.endsWith("normal.")) {
                    normal.add(line);
                } else {
                    attack.add(line);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            massa.clear();
            massa.addAll(attack);
            for (int j = 0; j < 262178; j++) {
                String value = normal.get(random.nextInt(normal.size()));
                if (massa.contains(value)) {
                    j--;
                }
                massa.add(value);
            }
            FileUtils.writeLines(new File("/kddcup99/kdd_treino" + "_" + i + ".csv"), massa);
        }
    }
}
