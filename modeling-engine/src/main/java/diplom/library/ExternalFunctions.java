package diplom.library;

public class ExternalFunctions {

    /**
     * Все методы, которые выполняют вспомогательную работу размещаются здесь.
     * Сейчас здесь заменители стандартных методов возведения в степень и
     * модуля числа. Эти работают немного быстрее.
     * */

    public static double OldApproximatePower(double b, double e) {
        long i = Double.doubleToLongBits(b);
        i = (long) (4606853616395542500L + e * (i - 4606853616395542500L));
        return Double.longBitsToDouble(i);
    }

    public static double BinaryPower(double b, long e) {
        double v = 1d;
        while (e > 0) {
            if ((e & 1) != 0) {
                v *= b;
            }
            b *= b;
            e >>= 1;
        }
        return v;
    }

    public static double FPF(double b, double e) {
        if (b == 1d || e == 0d) {
            return 1d;
        }

        double absExp = Math.abs(e);
        long eIntPart = (long) absExp;
        double eFractPart = absExp - eIntPart;
        double result = OldApproximatePower(b, eFractPart) * BinaryPower(b, eIntPart);
        if (e < 0d) {
            return 1d / result;
        }
        return result;
    }

    public static double absExt(double value) {
        return Double.longBitsToDouble(
                Double.doubleToRawLongBits(value) & 0x7fffffffffffffffL);
    }

}
