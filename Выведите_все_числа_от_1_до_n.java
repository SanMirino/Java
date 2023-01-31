import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, currentNum = 1;
        System.out.println("Введите натуральное число n > 1:");
        Scanner in = new Scanner(System.in); // инициализируем сканнер и задаём ему имя "in"
        while (true){ // запускаем цикл, который будет работать пока введённое значение не целочисленное
            if(in.hasNextInt()){ // проверяем, что значение с консоли является натуральным значением
                n = in.nextInt(); // записываем значение
                if(n > 1){ // проверяем, подходит ли число по условию
                    break; // если подходит, то закрываем цикл
                }
                else System.out.println("Ошибка. Введенное число <= 1.");
            }
            else System.out.println("Ошибка. Введенное значение не является натуральным числом.");
            in.nextLine();
        }
        System.out.println("Числа от 1 до " + n + ":");
        while (currentNum <= n){ // запускаем цикл, который будет работать пока текущее число <= n
            if(currentNum != n) System.out.print(currentNum + ", "); // если число не последнее, то добавляем запятую
            else System.out.print(currentNum + ". "); // иначе точку
            currentNum++; // в конце итерации добавляем к текущему числу + 1
        }
    }
}