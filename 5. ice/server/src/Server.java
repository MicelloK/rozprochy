import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.CommunicatorDestroyedException;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Server {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("TaskListAdapter", "default -p 10000");
            adapter.add(new TaskListServiceImpl(), Util.stringToIdentity("TaskListService"));
            adapter.activate();
            System.out.println("Server started, listening on port 10000");
            communicator.waitForShutdown();
        } catch (CommunicatorDestroyedException e) {
            // communicator already destroyed
        }
    }
}
