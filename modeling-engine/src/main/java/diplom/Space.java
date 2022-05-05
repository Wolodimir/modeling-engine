package diplom;

import java.util.WeakHashMap;

import static diplom.Data.*;

public class Space {

    static public void borderConditions(int i) {
        if (particles[i].x >= L) { //граничные условия по оси Х
            particles[i].Vx = -particles[i].Vx;
        } else if (particles[i].x <= 0) {
            particles[i].Vx = -particles[i].Vx;
        }

        if (particles[i].y >= L) { //граничные условия по оси Y
            particles[i].Vy = -particles[i].Vy;
        } else if (particles[i].y <= 0) {
            particles[i].Vy = -particles[i].Vy;
        }

        if (particles[i].z >= L) { //граничные условия по оси Z
            particles[i].Vz = -particles[i].Vz;
        } else if (particles[i].z <= 0) {
            particles[i].Vz = -particles[i].Vz;
        }
    }

    /**
     * АЛГОРИТМ АРЗУМАНОВА-КРАМАРУХИ
     */
    static public void initialCoords(int D) {
        if (D == 1) {
            int iter = 0;
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    particles[iter] = new Particle(
                            ((i + 1) * L / N) / 1.1,
                            0,
                            0,
                            10E4 / 2 - 10E3,
                            0,
                            0
                    );
                } else {
                    particles[iter] = new Particle(
                            ((i + 1) * L / N) / 1.1,
                            0,
                            0,
                            -10E4 / 2 - 10E3,
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
                    try {
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
                    } catch (Exception ignored) {}
                    iter++;
                }
            }
        }

        if (D == 3) {
            int iter = 0;
            int WHD = (int) Math.cbrt(N) + 1;
            for (int i = 0; i < WHD; i++) {
                for (int j = 0; j < WHD; j++) {
                    for (int k = 0; k < WHD; k++) {
                        try {
                            particles[iter] = new Particle(
                                    ((i + 1) * L / WHD) / 1.1,
                                    ((j + 1) * L / WHD) / 1.1,
                                    ((k + 1) * L / WHD) / 1.1,
                                    Math.sqrt(3 * KB * T / m),
                                    Math.sqrt(3 * KB * T / m),
                                    Math.sqrt(3 * KB * T / m)
                            );
                        } catch (Exception ignored) {
                        }
                        iter++;
                    }
                }
            }
        }
    }
}
