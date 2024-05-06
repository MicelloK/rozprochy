import Ice
import TaskList
        
if __name__ == '__main__':
    with Ice.initialize() as communicator:
        base = communicator.stringToProxy("TaskListService:default -p 10000")
        task_list_service = TaskList.TaskListServicePrx.checkedCast(base)
        while True:
            command = input('> ')
            if command == 'add':
                name = input('name: ')
                description = input('description: ')
                task = TaskList.Task(name, description)
                task_list_service.addTask(task)
            elif command == 'remove':
                task_id = int(input('id: '))
                task_list_service.removeTask(task_id)
            elif command == 'get':
                task_list = task_list_service.getTaskList()
                for task in task_list:
                    print(f"Name: {task.name}, Description: {task.description}")
                
            elif command == 'getI':
                ok, out_params = task_list_service.ice_invoke("getTaskList", Ice.OperationMode.Normal, bytes())
                decoded = out_params.decode('utf-8')
                print(decoded)