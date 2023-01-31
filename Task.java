import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); // Шаблон даты и времени
    public static ArrayList<Task> tasksArrayList = new ArrayList<>(); // Коллекция объектов "Task"

    // Поля объекта
    private int taskId;
    private LocalDateTime taskCreateDateTime;
    private LocalDateTime taskCompletionDateTime;
    private String taskName;
    private String taskDescription;
    private boolean taskCompletionMark;

    public Task(int taskId, LocalDateTime taskCreateDateTime, LocalDateTime taskCompletionDate, String taskName,
                String taskDescription, boolean taskCompletionMark) { // Конструктор объекта
        this.taskId = taskId;
        this.taskCreateDateTime = LocalDateTime.parse(taskCreateDateTime.format(dateTimeFormatter), dateTimeFormatter);
        this.taskCompletionDateTime = taskCompletionDate;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskCompletionMark = taskCompletionMark;
    }
    // Геттеры (методы для получения полей объекта) и Сеттеры (для установки полей объекта)
    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public LocalDateTime getTaskCreateDateTime() {
        return taskCreateDateTime;
    }
    public void setTaskCreateDateTime(LocalDateTime taskCreateDateTime) {
        this.taskCreateDateTime = taskCreateDateTime;
    }

    public LocalDateTime getTaskCompletionDateTime() {
        return taskCompletionDateTime;
    }
    public void setTaskCompletionDateTime(LocalDateTime taskCompletionDateTime) {
        this.taskCompletionDateTime = taskCompletionDateTime;
    }

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isTaskCompletionMark() {
        return taskCompletionMark;
    }
    public void setTaskCompletionMark(boolean taskCompletionMark) {
        this.taskCompletionMark = taskCompletionMark;
    }

    // Необязательный метод, созданный для упрощения тестирования программы. Добавляет 5 объектов в коллекцию
    public static void setData(){
        tasksArrayList.add(new Task(1, LocalDateTime.parse("12-12-2022 15:00", dateTimeFormatter), LocalDateTime.parse("28-12-2022 15:00", dateTimeFormatter),
                "Задача №1", "Описание задачи №1", false));
        tasksArrayList.add(new Task(2, LocalDateTime.parse("10-12-2022 15:00", dateTimeFormatter), LocalDateTime.parse("14-12-2022 15:00", dateTimeFormatter),
                "Задача №2", "Описание задачи №2", true));
        tasksArrayList.add(new Task(3, LocalDateTime.parse("12-12-2022 15:00", dateTimeFormatter), LocalDateTime.parse("16-12-2022 15:00", dateTimeFormatter),
                "Задача №3", "Описание задачи №3", true));
        tasksArrayList.add(new Task(4, LocalDateTime.parse("12-12-2022 15:00", dateTimeFormatter), LocalDateTime.parse("26-12-2022 15:00", dateTimeFormatter),
                "Задача №4", "Описание задачи №4", false));
        tasksArrayList.add(new Task(5, LocalDateTime.parse("12-12-2022 15:00", dateTimeFormatter), LocalDateTime.parse("28-12-2022 15:00", dateTimeFormatter),
                "Задача №5", "Описание задачи №5", false));
    }

    public static Task findById(int id) { // Метод для поиска по номеру
        ArrayList<Task> findList = new ArrayList<>(); // Создаем коллекцию с результатами поиска
        for(Task item : tasksArrayList){ // Обход коллекции
            if(item.getTaskId() == id){ // Если номер из параметров найден у поля объекта из коллекции
                findList.add(item); // то добавляем его в коллекцию
            }
        }
        if (findList.size() == 0) return null; // Если ничего не нашли, то возвращаем NULL
        else return findList.get(0); // Иначе возвращаем объект
    }

    public static int getNewId(){ // Метод для получения нового номера
        for (int i = 0; i < tasksArrayList.size() - 1; i++) { // Цикл для определения свободных номеров
            if (tasksArrayList.get(i + 1).getTaskId() - tasksArrayList.get(i).getTaskId() > 1 &&
                    findById(tasksArrayList.get(i).getTaskId() + 1) == null) {
                return tasksArrayList.get(i).getTaskId() + 1;
            }
        }
        return tasksArrayList.size();
    }

    // Метод для вывода списка заданий
    public static void outList(String title, boolean isCompletionTaskList, boolean isNotCompletionTaskList,
                               String selectedCreateDate, String selectedCompletionDate){
        if(!tasksArrayList.isEmpty()){ // Проверяем пустой ли список
            String mark;
            int lineLength = 110; // Длина линий таблицы
            boolean isEmptyList = true; // Логическая переменная для определения пустого списка
            StringBuilder lineBuilder = new StringBuilder(); // Стрингбилдер для создания линий таблицы
            while (lineBuilder.length() != lineLength){ // Пока линия != необходимой длине
                if(lineBuilder.length() == (lineLength - title.length()) / 2){ // Если дошли до середины линии
                    lineBuilder.append("|   ").append(title).append("   |"); // то вставляем заголовок
                }
                lineBuilder.append("-");
            }
            System.out.println(lineBuilder); // Выводим полученную линию
            lineBuilder.setLength(0); // Очищаем стрингбилдер
            System.out.printf("%-10s%-25s%-25s%-25s%-30s%n","№", "Дата создания", "Дата выполнения", "Название",
                    "Метка о выполнении"); // Выводим заголовки столбцов
            while (lineBuilder.length() != lineLength){ // Формируем вторую линию
                lineBuilder.append("-");
            }
            System.out.println(lineBuilder); // Выводим полученную линию
            if (isCompletionTaskList){ // Если в параметры метод было указано ИСТИНА для получения списка выполненных заданий
                for (Task task: tasksArrayList){ // Цикл для вывода списка заданий
                    if (task.isTaskCompletionMark()){ // Проверяем поля "Отметка о выполнении"
                        if(selectedCreateDate.equals("") && selectedCompletionDate.equals("")){ // Если дата не была указана в параметрах
                            isEmptyList = false; // Если была найдена хоть одна запись, то устанавливаем ложь
                            // Выводим текущею строку таблицы
                            System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                    task.getTaskCreateDateTime().format(dateTimeFormatter),
                                    task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "+");
                        }
                        else if (!selectedCreateDate.equals("")){ // Если была указана дата создания
                            if(task.getTaskCreateDateTime() // Сравниваем дату объекта и дату из параметров
                                    .toLocalDate()
                                    .atStartOfDay()
                                    .compareTo(ChronoLocalDateTime
                                            .from(LocalDateTime.parse(selectedCreateDate + " 00:00", dateTimeFormatter))) == 0){
                                isEmptyList = false;
                                System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                        task.getTaskCreateDateTime().format(dateTimeFormatter),
                                        task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "+");
                            }
                        }
                        else {
                            if(task.getTaskCompletionDateTime()
                                    .toLocalDate()
                                    .atStartOfDay()
                                    .compareTo(ChronoLocalDateTime
                                            .from(LocalDateTime.parse(selectedCompletionDate + " 00:00", dateTimeFormatter))) == 0){
                                isEmptyList = false;
                                System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                        task.getTaskCreateDateTime().format(dateTimeFormatter),
                                        task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "+");
                            }
                        }
                    }
                }
                if(isEmptyList){
                    System.out.println("Список заданий пуст!");
                }
            }
            if (isNotCompletionTaskList){
                for (Task task: tasksArrayList){
                    if (!task.isTaskCompletionMark()){
                        if(selectedCreateDate.equals("") && selectedCompletionDate.equals("")){
                            isEmptyList = false;
                            System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                    task.getTaskCreateDateTime().format(dateTimeFormatter),
                                    task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "-");
                        }
                        else if (!selectedCreateDate.equals("")){
                            if(task.getTaskCreateDateTime()
                                    .toLocalDate()
                                    .atStartOfDay()
                                    .compareTo(ChronoLocalDateTime
                                            .from(LocalDateTime.parse(selectedCreateDate + " 00:00", dateTimeFormatter))) == 0){
                                isEmptyList = false;
                                System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                        task.getTaskCreateDateTime().format(dateTimeFormatter),
                                        task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "-");
                            }
                        }
                        else {
                            if(task.getTaskCompletionDateTime()
                                    .toLocalDate()
                                    .atStartOfDay()
                                    .compareTo(ChronoLocalDateTime
                                            .from(LocalDateTime.parse(selectedCompletionDate + " 00:00", dateTimeFormatter))) == 0){
                                isEmptyList = false;
                                System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                        task.getTaskCreateDateTime().format(dateTimeFormatter),
                                        task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "-");
                            }
                        }
                    }
                }
                if(isEmptyList){
                    System.out.println("Список заданий пуст!");
                }
            }
            else if (!isCompletionTaskList){
                for (Task task: tasksArrayList){ // цикл для вывода списка заданий
                    if (task.isTaskCompletionMark()) mark = "+";
                    else mark = "-";
                    if (!selectedCreateDate.equals("")){
                        if(task.getTaskCreateDateTime()
                                .toLocalDate()
                                .atStartOfDay()
                                .compareTo(ChronoLocalDateTime
                                        .from(LocalDateTime.parse(selectedCreateDate + " 00:00", dateTimeFormatter))) == 0){
                            isEmptyList = false;
                            System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                    task.getTaskCreateDateTime().format(dateTimeFormatter),
                                    task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "-");
                        }
                    }
                    else if(!selectedCompletionDate.equals("")){
                        if(task.getTaskCompletionDateTime()
                                .toLocalDate()
                                .atStartOfDay()
                                .compareTo(ChronoLocalDateTime
                                        .from(LocalDateTime.parse(selectedCompletionDate + " 00:00", dateTimeFormatter))) == 0){
                            isEmptyList = false;
                            System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                    task.getTaskCreateDateTime().format(dateTimeFormatter),
                                    task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), "-");
                        }
                    }
                    else {
                        isEmptyList = false;
                        System.out.printf("%-10s%-25s%-25s%-25s%-30s%n", task.getTaskId(),
                                task.getTaskCreateDateTime().format(dateTimeFormatter),
                                task.getTaskCompletionDateTime().format(dateTimeFormatter), task.getTaskName(), mark);
                    }
                }
                if(isEmptyList){
                    System.out.println("Список заданий пуст!");
                }
            }
            // Выводим подвал таблицы
            System.out.println(lineBuilder);
        }
        else {
            System.out.println("Список заданий пуст!");
        }
    }

}
