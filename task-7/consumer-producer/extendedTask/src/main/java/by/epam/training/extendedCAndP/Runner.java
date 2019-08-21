package by.epam.training.extendedCAndP;


public class Runner {
    public final static int HALF_POOL_SIZE = 5;
    public final static int RANDOM_BORDER = 10;

    public static void main(String[] args) {
        final int numberOfConsumer = 2;
        final int numberOfProducer = 2;
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Number of consumer:");
        numberOfConsumer = scanner.nextInt();
        System.out.println("Number of producer:");
        numberOfProducer = scanner.nextInt();*/

    }

    private static int random(){
        int max = RANDOM_BORDER;
        return (int) (Math.random() * ++max);
    }
}