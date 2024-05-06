module TaskList {
    struct Task {
        string name;
        string description;
    };

    sequence<Task> TaskSeq;

    interface TaskListService {
        void addTask(Task task);
        void removeTask(int index);
        TaskSeq getTaskList();
    };
};