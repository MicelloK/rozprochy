import TaskList.Task;
import TaskList.TaskListService;
import com.zeroc.Ice.Current;

import java.util.ArrayList;

public class TaskListServiceImpl implements TaskListService {
    private final ArrayList<Task> tasks;

    public TaskListServiceImpl() {
        tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task, Current current) {
        tasks.add(task);
        System.out.println("Added task: " + task.name);
    }

    @Override
    public void removeTask(int index, Current current) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Removed task at index " + index + ": " + removedTask.name);
        } else {
            System.out.println("Invalid index");
        }
    }

    @Override
    public Task[] getTaskList(Current current) {
        Task[] taskArray = new Task[tasks.size()];
        tasks.toArray(taskArray);
        System.out.println("Got tasks list");
        return taskArray;
    }
}
