
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        System.out.println();

        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме
        String YearlyTransactions = transactions.stream()
                .filter(trans -> trans.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .map(Transaction::toString)
                .collect(Collectors.joining(",\n"));

        System.out.println("Транзакции за 2011: " + YearlyTransactions);
        System.out.println();

        // 2. Вывести список неповторяющихся городов
        String NonrepeatingCities = transactions.stream()
                .map(trans -> trans.getTrader().getCity())
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.println("Список городов: " + NonrepeatingCities);
        System.out.println();

        // 3. Найти всех трейдеров из Кембриджа и отсортировать по именам
        String CambridgeFinder = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trans -> trans.getCity().equals("Cambridge"))
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println("Список трейдеров из Кембриджа: " + CambridgeFinder);
        System.out.println();

        // 4. Вернуть строку со всеми именами трейдеров в алфавитном порядке
        String SortedTraders = transactions.stream()
                .map(trans -> trans.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println("Список трейдеров в алфавитном порядке: " + SortedTraders);
        System.out.println();

        // 5. Проверка трейдеров из Милана
        Boolean AnyMilans = transactions.stream()
                .anyMatch(trans -> trans.getTrader().getCity().equals("Milan"));

        if (AnyMilans){
            System.out.println("Есть трейдеры из Милана");
        }
        else {
            System.out.println("Трейдеры из Милана отсутвуют");
        }

        System.out.println();

        // 6. Вывод суммы всех тразакций из Кембриджа
        int AllCambridgeTransactions = transactions.stream()
                .filter(trans -> trans.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println("Сумма всех транзакций: " + AllCambridgeTransactions);
        System.out.println();

        // 7. Максимальная сумма всех транзакций
        int MaxTransaction = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(0);
        System.out.println("Максимальная сумма транзакций: " + MaxTransaction);
        System.out.println();

        // 8. Минимальная сумма всех транзакций
        int MinTransaction = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min()
                .orElse(0);
        System.out.println("Минимальная сумма транзакций: " + MinTransaction);

    }
}
