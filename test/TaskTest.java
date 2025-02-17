

import static org.junit.jupiter.api.Assertions.*;
import model.enums.TaskStatus;
import model.Epic;
import model.SubTask;
import model.Task;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    public void taskEqualToEachOther() {
        Task task1 = new Task("Task1", "Description1");
        Task task2 = new Task("Task1", "Description1");
        Assertions.assertEquals(task1, task2);
    }

    @Test
    public void epicEqualToEachOther() {
        Epic epic1 = new Epic("Epic1", "Description1");
        Epic epic2 = new Epic("Epic1", "Description1");
        Assertions.assertEquals(epic1, epic2);
    }

    @Test
    public void taskManagerClassAlwaysReturnsInitializedManager() {
        TaskManager taskManager = new Managers().getDefault();
        Assertions.assertNotNull(taskManager);
    }

    @Test
    public void testAddTask(){
        TaskManager taskManager = new Managers().getDefault();

        Task task1 = new Task("Task1", "Description1");
        Task task2 = new Task("Task1", "Description1");

        taskManager.add(task1);
        taskManager.add(task2);

        Assertions.assertEquals(task1, taskManager.getTaskById(task1.getId()));
        Assertions.assertEquals(task2, taskManager.getTaskById(task2.getId()));
    }

    @Test
    public void testTaskIdConflict(){
        TaskManager taskManager = new Managers().getDefault();
        Task task5 = new Task("Task5", "Description5");
        Task task6 = new Task("Task6", "Description6");
        Task task7 = new Task("Task7", "Description7");

        taskManager.add(task5);
        taskManager.add(task6);
        taskManager.add(task7);

        Assertions.assertNotEquals(task5.getId(), task7.getId());
        Assertions.assertNotEquals(task5.getId(), task6.getId());
    }

    @Test
    public void testTaskImmutability(){
        TaskManager taskManager = new Managers().getDefault();
        Task task5 = new Task("Task5", "Description5");

        taskManager.add(task5);

        Task updatedTask = taskManager.getTaskById(task5.getId());

        Assertions.assertEquals(task5.getDescription(), updatedTask.getDescription());
        Assertions.assertEquals(task5.getStatus(), updatedTask.getStatus());
    }

    @Test
    public void testCheckClearTaskHashMap(){
        TaskManager taskManager = new Managers().getDefault();

        Task task1 = new Task("Task1", "Description1");
        Task task2 = new Task("Task2", "Description2");
        Task task3 = new Task("Task3", "Description3");

        taskManager.add(task1);
        taskManager.add(task2);
        taskManager.add(task3);

        taskManager.removeAllTasks();

        Assertions.assertEquals(0, taskManager.allTasks().size());
    }

    @Test
    public void testCheckClearEpicHashMap(){
        TaskManager taskManager = new Managers().getDefault();

        Epic epic1 = new Epic("Epic1", "Description1");
        Epic epic2 = new Epic("Epic2", "Description2");
        Epic epic3 = new Epic("Epic3", "Description3");

        taskManager.add(epic1);
        taskManager.add(epic2);
        taskManager.add(epic3);

        taskManager.removeAllEpics();

        Assertions.assertEquals(0, taskManager.allEpics().size());
    }



}