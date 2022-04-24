package diplom;

import java.util.WeakHashMap;

import static diplom.Data.*;

public class Space {

    public static double L;
    public double V;

    static public void borderConditions(int i) {
        if (true) { //граничные условия по оси Х
        } else if (true) {
        }

        if (true) { //граничные условия по оси Y
        } else if (true) {
        }

        if (true) { //граничные условия по оси Z
        } else if (true) {
        }
    }

    static public void initialCoords(int D) {
        if (D == 1) {
            int iter = 0;
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    particles[iter] = new Particle(
                            ((i + 1) * L / N) / 1.1,
                            0,
                            0,
                            (10E4 / 2 - 10E3) * 5,
                            0,
                            0
                    );
                } else {
                    particles[iter] = new Particle(
                            ((i + 1) * L / N) / 1.1,
                            0,
                            0,
                            -(10E4 / 2 - 10E3) * 5,
                            0,
                            0
                    );
                }

                iter++;
            }
        }

        if (D == 2) {
            int iter = 0;
            int WH = (int) Math.sqrt(N) + 1;
            for (int i = 0; i < WH; i++) {
                for (int j = 0; j < WH; j++) {
                    if (iter % 2 == 0) {
                        particles[iter] = new Particle(
                                ((i + 1) * L / WH) / 1.1,
                                ((j + 1) * L / WH) / 1.1,
                                0,
                                10E4 / 2 - 10E3,
                                10E4 / 2 - 10E3,
                                0
                        );
                    } else {
                        particles[iter] = new Particle(
                                ((i + 1) * L / WH) / 1.1,
                                ((j + 1) * L / WH) / 1.1,
                                0,
                                -10E4 / 2 - 10E3,
                                -10E4 / 2 - 10E3,
                                0
                        );
                    }
                    iter++;
                }
            }
        }
        if (D == 3) {
            int i = 0, j = 0, k = 0, iter = 0;
            while (iter < N) {

            }
        }
    }
}
