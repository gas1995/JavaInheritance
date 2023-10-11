package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldMatchesContainsQuery() {
        Task task = new Task(1);
        SimpleTask simpleTask = new SimpleTask(10, "Задача для разработчика");

        String[] subtasks = {"Задача для тестировщика","Тестировать Задача","Настроить джиру"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                100,
                "Задача разработчика",
                "Магазин Задача",
                "21.12.2023"
        );
        Todos todos = new Todos();

        todos.add(task);
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        boolean[] expected = { false, true, true, true };
        boolean[] actual = new boolean[4];

        Task[] arrayTask = todos.findAll();

        for (int i = 0; i < arrayTask.length; i++) {
            actual[i] = arrayTask[i].matches("Задача");
        }

        Assertions.assertArrayEquals(expected,actual);

        Meeting meeting1 = new Meeting(13,
                "разработчика",
                "Магазин Задача",
                "21.12.2023" );

        boolean actualBool = meeting1.matches("Задача");
        boolean expectedBool = true;
        Assertions.assertEquals(actualBool, expectedBool); // этот подтест для покрытия :)

    }

    @Test
    public void shouldMatchesNoContainsQuery() {
        SimpleTask simpleTask = new SimpleTask(10, "Таск для разработчика");

        String[] subtasks = {"Задачи для тестировщика","Протестировать задачу","Настроить джиру"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                100,
                "Задачи разработчика",
                "Магазин игрушек",
                "21.12.2023"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        boolean[] expected = { false, false, false };
        boolean[] actual = new boolean[3];

        Task[] arrayTask = todos.findAll();

        for (int i = 0; i < arrayTask.length; i++) {
            actual[i] = arrayTask[i].matches("Задача");
        }

        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void shouldSearchContainsQuery() {
        SimpleTask simpleTask = new SimpleTask(10, "Задача для разработчика");

        String[] subtasks = {"Задача для тестировщика","Протестировать задачу","Настроить джиру"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                100,
                "Задача разработчика",
                "Магазин игрушек",
                "21.12.2023"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.search("Задача");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldNoSearchContainsQuery() {
        SimpleTask simpleTask = new SimpleTask(10, "Таск для разработчика");

        String[] subtasks = {"Задачи для тестировщика","Протестировать задачу","Настроить джиру"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                100,
                "Задачи разработчика",
                "Магазин игрушек",
                "21.12.2023"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Задача");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testForGetter(){
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        int[] expected = { 5, 55, 555 };
        int[] actual = {simpleTask.getId(), epic.getId(), meeting.getId()};
        Assertions.assertArrayEquals(expected, actual);

    }

}
