import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Data {

    public static long N;
    public static double time;
    public static double dt;
    public static double m;
    public static double KB;
    public static double EPS;
    public static double SIG;
    public static long T;
    public static double P;
    public static double V;
    public static double L;
    public static long D;

    public static void initStaticVariables() throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("/home/vladimir/hobby-dev/modeling-engine/modeling-engine/src/main/resources/config.json");
        System.out.println(jsonObject);
        N = (long) jsonObject.get("N");
        time = (double) jsonObject.get("t");
        dt = (double) jsonObject.get("dt");
        m = (double) jsonObject.get("m");
        KB = (double) jsonObject.get("KB");
        EPS = (double) jsonObject.get("EPS");
        SIG = (double) jsonObject.get("SIG");
        T = (long) jsonObject.get("T");
        P = (double) jsonObject.get("P");
        D = (long) jsonObject.get("D");
        L = (double) jsonObject.get("L");
        V = Math.pow(L, 3);
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

}
