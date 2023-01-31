import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Instant startTime;
        int searchNum = 216;
        int n = 100000000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) { // Запускаем цикл для заполнения элементом массива
            array[i] = (int) (Math.random() * 300); // Заполняем массив случайными целыми числами от 0 до 300
        }
        Arrays.sort(array); // Сортируем массив (сортировка TimSort)
        startTime = Instant.now(); // Записываем текущее время
        selectionMethod(searchNum, array); // Вызываем метод перебора значений и передаём в него число для поиска и массив
        System.out.println("Время затраченное перебором: " + Duration.between(startTime, Instant.now()).toMillis() + " мс");
        startTime = Instant.now(); // Записываем текущее время
        binarySearch(searchNum, array); // Вызываем метод бинарного поиска и передаём в него число для поиска и массив
        System.out.println("Время затраченное бинарным поиском: " + Duration.between(startTime, Instant.now()).toMillis() + " мс");
    }
    public static void selectionMethod(double searchNum, int[] array) {
        for (int i = 0; i < array.length; i++){
            if (array[i] == searchNum){
                //System.out.println("Значение найдено по индексу: " + i);
                return;
            }
        }
    }
    private static int binarySearch(int searchNum, int[] array, int bottom, int top) {
        int mid = (bottom + top) / 2; // Определяем середину
        if (top < bottom){
            return -1;
        }
        if (array[mid] == searchNum){
            return mid;
        }
        else if (searchNum < array[mid]){ // Выполняем поиск в левой части
            return binarySearch(searchNum, array, bottom, mid - 1);
        }
        else{ // Выполняем поиск в правой части
            return binarySearch(searchNum, array, mid + 1, top);
        }
    }
    public static int binarySearch(int searchNum, int[] array){
        return binarySearch(searchNum, array, 0, array.length);
    }
}
