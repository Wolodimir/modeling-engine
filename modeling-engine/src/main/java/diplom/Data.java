package diplom;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Data {

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

    public static long hhhh = System.currentTimeMillis();

    public static Particle nullParticle = new Particle(0, 0, 0, 0, 0, 0);
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
        particles = new Particle[N];
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

}
