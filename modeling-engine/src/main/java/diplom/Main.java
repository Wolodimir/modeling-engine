package diplom;

import diplom.library.PowerAlgorithms;
import diplom.library.StaticGrid;
import diplom.output.Output;
import diplom.threads.*;

import java.io.File;
import java.io.IOException;

import static diplom.Data.*;
import static java.lang.Math.pow;

public class Main {

    public static void start() throws Exception {
        // Начальные действия, создать объекты, дать им начальные координаты и скорости
        // инициализировать переменные
        Data.initStaticVariables();
        Space.initialCoords(D);
        PowerAlgorithms.calcPowers();
        //Запуск разных вычислительных алгоритмов
        //StaticGrid.gridAlgorythm();
        emptySimulation();
    }
    /**
     * Вычисления скоростей и координат были объединены с силами,
     * в связи с этим понадобился метод, который только шагает по времени.
     * */
    public static void emptySimulation() throws IOException, InterruptedException {

        File file1 = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        file1.delete();
        File file = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        int k = 0;//счётчик шагов моделирования

        for (double t = 0; t < time; t += dt) {

            PowerAlgorithms.upTheCycles();
            //threadingCulcPowers();

            System.out.println("---------" + k + "---------");
            k++;

            //if (k > 900000) {
            if (k % 1 == 0){
                Output.csvForGraphics(file, k);
            }
            //}

            if (k == steps) {
                System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - hhhh) / 1000 + "s");
                break;
            }
        }
    }

    //Метод, запускающий потоки с методом upTheCycles
    static public void threadingCulcPowers() throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        Thread4 thread4 = new Thread4();
        Thread5 thread5 = new Thread5();
        Thread6 thread6 = new Thread6();
        Thread7 thread7 = new Thread7();
        Thread8 thread8 = new Thread8();
        Thread9 thread9 = new Thread9();
        Thread10 thread10 = new Thread10();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();
    }


}
