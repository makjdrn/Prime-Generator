import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by makjdrn on 2015-11-29.
 */
public class PrimeThread extends Thread {
    private int numberlength = 128;
    private BigInteger number;
    private int i;

    public PrimeThread(int s) {
        i = s+1;
    }

    public void run() {
        while (!(CheckIfPrime())) {
            System.out.println("Thread: " + i + " Number: " + number);
            currentThread().stop();
        }
    }

    private synchronized boolean CheckIfPrime() {
        synchronized (this) {
            Random r = new SecureRandom();
            number = new BigInteger(numberlength, r);
            BigInteger nsgrt = sqrt(number);
            BigInteger i = new BigInteger("6");
            while (i.compareTo(nsgrt) == -1) {
                if (number.mod(i.subtract(BigInteger.ONE)).equals(BigInteger.ZERO) || number.mod(i.add(BigInteger.ONE)).equals(BigInteger.ZERO))
                    return false;
                i = i.add(new BigInteger("1"));
            }
            return true;
        }
    }

    private BigInteger sqrt(BigInteger number) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(number.shiftRight(5).add(new BigInteger("8")).toString());
        while(b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if(mid.multiply(mid).compareTo(number) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }
}
