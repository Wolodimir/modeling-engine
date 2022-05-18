package diplom.library;

import diplom.Particle;
import diplom.Space;
import diplom.output.Output;

import java.io.File;
import java.io.IOException;

import static diplom.Data.*;
import static diplom.library.ExternalFunctions.FPF;
import static java.lang.Math.pow;

/**
 * Данный класс содержит в себе наработки по применению алгоритма "Статическая сеть"
 * Вычисления данного алгоритма нужно настраивать.
 * */
@Deprecated
public class StaticGrid {

    //Переменные ниже нужны только для применения алгоритма "Статическая сеть"
    public static Particle nullParticle = new Particle(0, 0, 0, 0, 0, 0);
    public static int gridLength = (int) Math.cbrt(N) + 15;
    public static Particle[][][] grid = new Particle[gridLength][gridLength][gridLength];
    public static double gridDist = 3E-9;

    //Метод, для применения алгоритма "Статическая сеть"
    public static void gridAlgorythm() throws IOException, InterruptedException {
        File file1 = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        file1.delete();
        File file = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        int k = 0;//счётчик шагов моделирования

        for (double t = 0; t < time; t += dt) {
            for (int i = 0; i < N; i++) {
                particles[i].FxPrev = particles[i].Fx;
                particles[i].FyPrev = particles[i].Fy;
                particles[i].FzPrev = particles[i].Fz;
            }

            for (int i = 0; i < N; i++) {
                //sign(x2-x1)*F/m1
                particles[i].x = particles[i].x + particles[i].Vx * dt + (particles[i].FxPrev * pow(dt, 2) / (2 * m));
                particles[i].y = particles[i].y + particles[i].Vy * dt + (particles[i].FyPrev * pow(dt, 2) / (2 * m));
                particles[i].z = particles[i].z + particles[i].Vz * dt + (particles[i].FzPrev * pow(dt, 2) / (2 * m));

                Space.borderConditions(i);
            }
            StaticGrid.caclPowers();

            for (int i = 0; i < N; i++) {//определение скорости частиц
                particles[i].Vx = particles[i].Vx + 0.5 * ((particles[i].Fx + particles[i].FxPrev) / m) * dt;
                particles[i].Vy = particles[i].Vy + 0.5 * ((particles[i].Fy + particles[i].FyPrev) / m) * dt;
                particles[i].Vz = particles[i].Vz + 0.5 * ((particles[i].Fz + particles[i].FzPrev) / m) * dt;
            }

            System.out.println("---------" + k + "---------");
            k++;

            //if (k > 900000) {
            if (k % 1 == 0){
                Output.csvFor3D(file, k);
            }
            //}

            if (k == steps) {
                System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - hhhh) / 1000 + "s");
                break;
            }
        }
    }

    public static void caclPowers() {
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
