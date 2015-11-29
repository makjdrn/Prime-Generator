/**
 * Created by makjdrn on 2015-11-29.
 */
public class PrimeGenerator {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int numberlength = Integer.parseInt(args[1]);

        PrimeThread[] primeThread = new PrimeThread[k];

        for(int i = 0; i < k; i++) {
            primeThread[i] = new PrimeThread(i);
            primeThread[i].start();
        }
    }
}
