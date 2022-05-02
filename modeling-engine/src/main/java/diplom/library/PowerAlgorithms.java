package diplom.library;

import static diplom.Data.*;
import static diplom.Data.particles;
import static diplom.library.ExternalFunctions.FPF;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PowerAlgorithms {

    public static void calcPowers() {
        double r;
        double f0;

        for (int i = 0; i < N; ++i) {
            particles[i].Fx = 0;
            particles[i].Fy = 0;
            particles[i].Fz = 0;
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    r = sqrt(pow((particles[i].x - particles[j].x), 2)
                            + pow((particles[i].y - particles[j].y), 2)
                            + pow((particles[i].z - particles[j].z), 2));
                    f0 = (double) 48 * (EPS / SIG) * (FPF((SIG / r), 13) - 0.5 * FPF((SIG / r), 7));
                    particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                    particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                    particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                }
            }
        }
    }

    public static void staticGrid() {
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                for (int k = 0; k < gridLength; k++) {
                    grid[i][j][k] = nullParticle;
                }
            }
        }
        int q = 0;
        for (int i = 0; i < N; i++) {
            int x = (int) (particles[i].x / gridDist);
            int y = (int) (particles[i].y / gridDist);
            int z = (int) (particles[i].z / gridDist);
            if (grid[x][y][z] != nullParticle) {
                q++;
            }
            grid[x][y][z] = particles[i];
        }

        double r;
        double f0;
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                for (int k = 0; k < gridLength; k++) {
                    /** 1 частица
                     * Проверяется матрица вокруг неё
                     * */
                    //todo нужна проверка на то, что сама ячейка содержит nullParticle

                    for (int i1 = i - 1; i1 < i + 1; i1++) {
                        for (int j1 = j - 1; j1 < j + 1; j1++) {
                            for (int k1 = k - 1; k1 < k + 1; k1++) {

                                if (i == i1 && j == j1 && k == k1)
                                    continue;

                                try {
                                    if (grid[i1][j1][k1] == nullParticle)
                                        continue;

                                    r = Math.sqrt(FPF((grid[i][j][k].x - grid[i1][j1][k1].x), 2)
                                            + FPF((grid[i][j][k].y - grid[i1][j1][k1].y), 2)
                                            + FPF((grid[i][j][k].z - grid[i1][j1][k1].z), 2));

                                    f0 = (double) 48 * (EPS / SIG) * (FPF((SIG / r), 13) - 0.5 * FPF((SIG / r), 7));
                                    grid[i][j][k].Fx = grid[i][j][k].Fx + (f0 * (grid[i][j][k].x - grid[i1][j1][k1].x) / r);
                                    grid[i][j][k].Fy = grid[i][j][k].Fy + (f0 * (grid[i][j][k].y - grid[i1][j1][k1].y) / r);
                                    grid[i][j][k].Fz = grid[i][j][k].Fz + (f0 * (grid[i][j][k].z - grid[i1][j1][k1].z) / r);
                                } catch (Exception ignored) {
                                    /**
                                     * Ошибки связаны с тем, что мы выходим за рамки матрицы, для крайних частиц.
                                     * Это позволяет сделать алгоритм целостным, и не портить рассчёты.
                                     * Возможно, удастся найти способ избеганий таких проблем.
                                     * */
                                }
                            }
                        }
                    }


                }
            }
        }
        System.out.println(q);
    }
}
