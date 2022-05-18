package diplom;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Data {

    /**
     * Размер куба для 100 частиц = 10E-9 (10 нанометров при нашем алгоритме начальных координат)
     * Размер куба для 1000 частиц = 17E-9 (17 нанометров при нашем алгоритме начальных координат)
     * */

    public static double t = 0;
    public static int k = 0;

    public static double kin;
    public static double pot;

    public static int N;
    public static double time;
    public static double dt;
    public static double m;
    public static double KB;
    public static double EPS;
    public static double SIG;
    public static int T;
    public static double P;
    public static double V;
    public static double L;
    public static int D;
    public static int steps;
    public static int recordLast;
    public static int threadArea;

    public static long hhhh = System.currentTimeMillis();

    public static Particle[] particles;

    public static void initStaticVariables() throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("/home/vladimir/hobby-dev/modeling-engine/modeling-engine/src/main/resources/config.json");
        System.out.println(jsonObject);
        N = (int) (long) jsonObject.get("N");
        time = (double) jsonObject.get("t");
        dt = (double) jsonObject.get("dt");
        m = (double) jsonObject.get("m");
        KB = (double) jsonObject.get("KB");
        EPS = (double) jsonObject.get("EPS");
        SIG = (double) jsonObject.get("SIG");
        T = (int) (long) jsonObject.get("T");
        P = (double) jsonObject.get("P");
        D = (int) (long) jsonObject.get("D");
        L = (double) jsonObject.get("L");
        V = Math.pow(L, 3);
        steps = (int) (long) jsonObject.get("steps");
        recordLast = (int) (long) jsonObject.get("recordLast");

        if (recordLast != 0) {
            recordLast = steps - recordLast;
        }
        particles = new Particle[N];
        threadArea = N / 10;
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

}
