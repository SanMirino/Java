import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Statements {

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static void programMenu(){ // метод отвечающий за главное меню и все его функции
        int choice = 0; // инициализируем целочисленную переменную, в которую будет записывать ввод в консоль
        while (choice != 6){ // запуская цикл, который будет работать пока пользователь не введёт "5"([Выход])
            if (choice == 0){ // если выбор = 0, то..
                outMenu(1); // вызываем метод, который выводит главное меню
                choice = scanInput(); // присваиваем переменной "choice", результат работы метода "scanInput"
            }
            // устанавливаем переменную для выбора
            switch (choice) {
                case (1) -> {
                    Task.outList("Задания", false, false,
                            "", ""); // Выводим полный список заданий
                    choice = 0;
                }
                case (2) -> {
                    getTaskEditMenu(); // Выводим меню для редактирования
                    choice = 0;
                }
                case (3) -> {
                    getTaskListsMenu(); // Выводим меню для вывода выполненных/невыполненных заданий
                    choice = 0;
                }
                case (4) -> {
                    getTaskRequestMenu(); // Выводим меню для вывода выполненных/невыполненных заданий по дате
                    choice = 0;
                }
                case (5) -> {
                    getSelectedTaskDescription(); // Вызов метода для получения описания задания
                    choice = 0;
                }
            }
        }
    }
    public static void getTaskEditMenu() { // Метод меню для редактирования
        do{
            outMenu(2); // Выводим меню метода
            int choice = getChoice(1, 5); // Получаем выбор от 1 до 5
            if(choice == 5) return; // Если 5 - [Выход], то выходим из метода
            switch (choice) {
                case (1) -> addNewTask(); // Метод для добавления задач
                case (2) -> deleteTask(); // Метод для удаления задач
                case (3) -> editTask(); // Метод для редактирования задач
                case (4) -> editTaskMark(); // Метод для изменения отметки о выполнении задачи
            }
            outMenu(0); // Вывод подменю с выбором о продолжении действий
        } while (getChoice(0, 1) == 1); // Цикл с постусловием, который будет выполняться, пока выбор != 0
    }
    public static void addNewTask() { // Метод для добавления задач
        Scanner in = new Scanner(System.in);
        String taskCompletionDate, taskCompletionTime, taskName, taskDescription;
        LocalDateTime taskCompletionDateTime;

        while (true) {
            System.out.println("Введите дату выполнения задачи: ");
            taskCompletionDate = checkInputDate(); // Проверяем дату на валидность, если валидна, то записываем
            System.out.println("Введите время выполнения задачи: ");
            taskCompletionTime = checkInputTime(); // Проверяем время на валидность, если валидно, то записываем
            taskCompletionDateTime = LocalDateTime.parse(taskCompletionDate + " "
                    + taskCompletionTime, dateTimeFormatter); // Собираем дату + время
            if (taskCompletionDateTime.compareTo(LocalDateTime.now()) < 0){ // Проверяем даты (LocalDateTime.now() - текущая дата и время
                System.err.println("Дата и время выполнения не могу быть раньше даты создания задания.");
                continue; // Запускаем новую итерацию, то есть, записываем переменные заново
            }
            System.out.println("Введите название задачи: ");
            taskName = in.nextLine();
            System.out.println("Введите описание задачи: ");
            taskDescription = in.nextLine();
            break; // Заканчиваем цикл
        }
        Task.tasksArrayList.add(new Task(Task.getNewId(), LocalDateTime.now(), taskCompletionDateTime, taskName,
                taskDescription, false)); // Добавляем новую задачу в коллекцию объектов "Task"
        System.out.println("\nЗадача успешно добавлена.");
    }
    private static void deleteTask() { // Метод для удаления задач
        Task.outList("Задания", false, false,
                "", ""); // Выводим полный список задач
        if(!Task.tasksArrayList.isEmpty()){ // Проверяем, что список не пустой
            System.out.println("Введите номер задания для удаления:");
            int selectedId = scanInput();
            Task selectedTask = Task.findById(selectedId); // Ищем объект "Task" по полю "id"
            if (selectedTask != null) { // Если объект был найден
                Task.tasksArrayList.remove(Task.findById(selectedId)); // Удаляем его из коллекции объектов
                System.out.println("\nЗадача успешно удалена.");
            }
            else{
                System.err.println("Неверный номер задания.");
            }
        }
    }
    private static void editTask() { // Метод для редактирования задач
        Task.outList("Задания", false, false,
                "", ""); // Выводим полный список задач
        if(!Task.tasksArrayList.isEmpty()) { // Проверяем, что список не пустой
            Scanner in = new Scanner(System.in);
            int newTaskId;
            LocalDateTime taskCreateDateTime, taskCompletionDateTime;
            String taskName, taskDescription;
            boolean taskCompletionMark;
            System.out.println("Введите номер задания для редактирования:");
            Task selectedTask;
            while (true) {
                int selectedId = scanInput();
                selectedTask = Task.findById(selectedId);
                if (selectedTask == null)
                    System.err.println("Неверный номер задания."); // Если задание не найдено, то выводим сообщение
                else break; // Иначе закрываем цикл
            }
            while (true) {
                System.out.println("Введите номер задачи: ");
                newTaskId = scanInput();
                if (Task.findById(newTaskId) != null) {
                    System.err.println("Ошибка. Новый номер задания не уникален.");
                    continue;
                }
                System.out.println("Введите новую дату создания задачи: ");
                String taskCreateDate = checkInputDate();
                System.out.println("Введите новое время создания задачи: ");
                String taskCreateTime = checkInputTime();
                taskCreateDateTime = LocalDateTime.parse(taskCreateDate + " " + taskCreateTime,
                        dateTimeFormatter);
                System.out.println("Введите новую дату выполнения задачи: ");
                String taskCompletionDate = checkInputDate();
                System.out.println("Введите новое время выполнения задачи: ");
                String taskCompletionTime = checkInputTime();
                taskCompletionDateTime = LocalDateTime.parse(taskCompletionDate + " " + taskCompletionTime,
                        dateTimeFormatter);
                if (taskCompletionDateTime.compareTo(taskCreateDateTime) < 0) {
                    System.err.println("Дата и время выполнения не могу быть раньше даты создания задания.");
                    continue;
                }
                System.out.println("Введите новое название задачи: ");
                taskName = in.nextLine();
                System.out.println("Введите новое описание задачи: ");
                taskDescription = in.nextLine();
                System.out.println("Введите новую отметку о выполнении (1 - '+'), (2 - '-'): ");
                taskCompletionMark = getChoice(1, 2) == 1;
                break;
            }
            // Изменяем поля выбранного объекта "Task"
            selectedTask.setTaskId(newTaskId);
            selectedTask.setTaskCreateDateTime(taskCreateDateTime);
            selectedTask.setTaskCompletionDateTime(taskCompletionDateTime);
            selectedTask.setTaskName(taskName);
            selectedTask.setTaskDescription(taskDescription);
            selectedTask.setTaskCompletionMark(taskCompletionMark);
        }
    }
    private static void editTaskMark() { // Метод для изменения отметки о выполнении задачи
        if(!Task.tasksArrayList.isEmpty()) {
            do {
                Task.outList("Задания", false, false,
                        "", "");
                System.out.println("Выберете задание для изменения отметки ");
                int selectedId = scanInput();
                Task selectedTask = Task.findById(selectedId);
                if (selectedTask != null) {
                    selectedTask.setTaskCompletionMark(!selectedTask.isTaskCompletionMark());
                    System.out.println("Отметка задания №" + selectedId + " изменена.");
                }
                else{
                    System.err.println("Неверный номер задания.");
                }
                outMenu(0);
            } while (getChoice(0, 1) == 1);
        } else System.out.println("Список заданий пуст.");
    }
    public static void getTaskListsMenu() {
        do{
            outMenu(3);
            int choice = getChoice(1, 3);
            if(choice == 3) return;
            switch (choice) {
                case (1) -> Task.outList("Выполненные задания", true, false,
                        "", ""); // Выводим список выполненных заданий (isCompletionList = true)
                case (2) -> Task.outList("Невыполненные задания", false, true,
                        "", ""); // Выводим список невыполненных заданий (isNotCompletionList = true)
            }
            outMenu(0);
        } while (getChoice(0, 1) == 1);
    }
    private static void getTaskRequestMenu() { // Метод для вывода выполненных/невыполненных заданий по дате
        String selectedCreateDate, selectedCompletionDate;
        do{
            outMenu(4);
            int choice = getChoice(1, 4); // Записываем выбор из списка меню
            if(choice == 4) return;
            System.out.println("Дата создания - 1, Дата выполнения - 2");
            int selectedDate = getChoice(1, 2); // Записываем выбор даты
            switch (choice) {
                case (1) -> {
                    if(selectedDate == 1){
                        System.out.println("Введите дату создания:");
                        selectedCreateDate = checkInputDate(); // Проверяем дату на валидность
                        Task.outList("Задания на дату создания: " + selectedCreateDate,
                                false, false, selectedCreateDate,
                                ""); // Выводим полный список заданий для указанной даты создания
                    }
                    else {
                        System.out.println("Введите дату выполнения:");
                        selectedCompletionDate = checkInputDate();
                        Task.outList("Задания на дату выполнения: " + selectedCompletionDate,
                                false, false,
                                "", selectedCompletionDate);
                    }
                }
                case (2) -> {
                    if(selectedDate == 1){
                        System.out.println("Введите дату создания:");
                        selectedCreateDate = checkInputDate();
                        Task.outList("Выполненные задания на дату создания: " + selectedCreateDate,
                                true, false,
                                selectedCreateDate, "");
                    }
                    else {
                        System.out.println("Введите дату выполнения:");
                        selectedCompletionDate = checkInputDate();
                        Task.outList("Выполненные задания на дату выполнения: " + selectedCompletionDate,
                                true, false,
                                "", selectedCompletionDate);
                    }
                }
                case (3) -> {
                    if(selectedDate == 1){
                        System.out.println("Введите дату создания:");
                        selectedCreateDate = checkInputDate();
                        Task.outList("Невыполненные задания на дату создания: " + selectedCreateDate,
                                false, true, selectedCreateDate,
                                "");
                    }
                    else {
                        System.out.println("Введите дату выполнения:");
                        selectedCompletionDate = checkInputDate();
                        Task.outList("Невыполненные задания на дату выполнения: " + selectedCompletionDate,
                                false, true,
                                "", selectedCompletionDate);
                    }
                }
            }
            outMenu(0);
        } while (getChoice(0, 1) == 1);
    }
    public static void getSelectedTaskDescription(){ // Метода для получения описания задания
        if(!Task.tasksArrayList.isEmpty()) {
            do {
                Task.outList("Задания", false, false,
                        "", "");
                System.out.println("Выберете задание для вывода его описания: ");
                int selectedId = scanInput();
                Task selectedTask = Task.findById(selectedId);
                if (selectedTask != null) {
                    System.out.println("Задание №" + selectedTask.getTaskId() + " | " + selectedTask.getTaskName()
                            + "\nОписание:\n" + selectedTask.getTaskDescription());

                }
                else{
                    System.err.println("Неверный номер задания.");
                }
                outMenu(0);
            } while (getChoice(0, 1) == 1);
        } else System.out.println("Список заданий пуст.");
    }
    public static void outMenu(int id){ // Метод для вывода главного меню
        switch (id) {
            case (1) -> System.out.println("""            
                        Главное меню программы.
                        1. Вывести список всех заданий.
                        2. Добавление/Удаление/Редактирование заданий.
                        3. Вывести список выполненных/невыполненных заданий.
                        4. Запросы.
                        5. Посмотреть детальную информацию о задании.
                        6. [Выход]
                        """);
            case (2) -> System.out.println("""            
                        Добавление/Удаление/Редактирование заданий:
                        1. Добавить задание.
                        2. Удалить задание.
                        3. Редактировать задание.
                        4. Изменить отметку об выполнении у заказа.
                        5. [Назад]
                        """);
            case (3) -> System.out.println("""            
                        Выберите список для вывода:
                        1. Выполненные задания.
                        2. Невыполненные задания.
                        3. [Назад]
                        """);
            case (4) -> System.out.println("""            
                        Выберите запрос:
                        1. Просмотреть список ВСЕХ заданий на указанную дату.
                        2. Просмотреть список ВЫПОЛНЕННЫХ заданий на указанную дату.
                        3. Просмотреть список НЕВЫПОЛНЕННЫХ заданий на указанную дату.
                        4. [Назад]
                        """);
            case (0) -> System.out.println("\nПовторить действие? (1 - Да) (0 - Нет)");
        }
    }
    public static int scanInput(){ // метод для сканирования данных из консоли
        Scanner in = new Scanner(System.in); // инициализируем сканнер и задаём ему имя "in"
        while (!in.hasNextInt()) in.next(); // запускаем цикл, который будет работать пока введённое значение не целочисленное
        return in.nextInt(); // если значение целочисленное, то возвращаем это значение
    }
    public static int getChoice(int startIndex, int endIndex){ /* Метод для обработки ввода при выборе из списка меню
                                                           В данный метод передаем первый индекс (startIndex) из списка
                                                           и последний индекс из списка (endIndex)*/
        int choice;
        System.out.println("Выбор: ");
        while (true){
            choice = scanInput();
            if(choice >= startIndex && choice <= endIndex){ // Проверяем, что введенное значение находится в заданном промежутке
                return choice;
            }
            else System.err.println("Неверный ввод!");
        }
    }
    public static String checkInputDate() { // Метод для валидации введенной даты
        Scanner in = new Scanner(System.in);
        String inputDate;
        while (true){
            inputDate = in.nextLine();
            try { // Пытаемся перевести строку в LocalDateTime
                LocalDateTime.parse(inputDate + " 00:00", dateTimeFormatter);
            } catch (DateTimeParseException e) { // Если ошибка, то выводим сообщение на экран и запускаем новую итерацию
                System.err.println("Неверный формат даты. Пример ввода даты: 01-01-2001");
                continue;
            }
            return inputDate;
        }
    }
    public static String checkInputTime() { // Метод для валидации введенного времени
        Scanner in = new Scanner(System.in);
        String inputTime;
        while (true){
            inputTime = in.nextLine();
            try {
                LocalDateTime.parse("01-01-2001 " + inputTime, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.err.println("Неверный формат времени. Пример ввода времени: 12:33");
                continue;
            }
            return inputTime;
        }
    }
}
