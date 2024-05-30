import java.util.LinkedList;
import java.util.Queue;

public class Market implements market, MarketBehaviour {
    private Queue<String> queue;
    private Queue<String> orders;

    public Market() {
        this.queue = new LinkedList<>();
        this.orders = new LinkedList<>();
    }

    // QueueBehaviour methods
    @Override
    public void enterQueue(String person) {
        queue.add(person);
        System.out.println(person + " entered the queue.");
    }

    @Override
    public void leaveQueue() {
        if (!queue.isEmpty()) {
            String person = queue.poll();
            System.out.println(person + " left the queue.");
        } else {
            System.out.println("The queue is empty.");
        }
    }

    @Override
    public String peekQueue() {
        return queue.peek();
    }

    @Override
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    // MarketBehaviour methods
    @Override
    public void acceptOrder(String order) {
        orders.add(order);
        System.out.println("Order accepted: " + order);
    }

    @Override
    public void releaseOrder() {
        if (!orders.isEmpty()) {
            String order = orders.poll();
            System.out.println("Order released: " + order);
        } else {
            System.out.println("No orders to release.");
        }
    }

    @Override
    public boolean hasOrders() {
        return !orders.isEmpty();
    }

    // Update method
    public void update() {
        // Process people in the queue
        if (!isQueueEmpty()) {
            String person = peekQueue();
            System.out.println("Processing " + person + " in the queue.");
            leaveQueue();
        } else {
            System.out.println("No people in the queue to process.");
        }

        // Process orders
        if (hasOrders()) {
            System.out.println("Processing orders.");
            releaseOrder();
        } else {
            System.out.println("No orders to process.");
        }
    }

    public static void main(String[] args) {
        Market market = new Market();
        
        // Test QueueBehaviour
        market.enterQueue("Alice");
        market.enterQueue("Bob");
        market.update();
        
        // Test MarketBehaviour
        market.acceptOrder("Order1");
        market.acceptOrder("Order2");
        market.update();
        market.update();
        market.update();
    }
}


public interface market {
    void enterQueue(String person);
    void leaveQueue();
    String peekQueue();
    boolean isQueueEmpty();
}

public interface MarketBehaviour {
    void acceptOrder(String order);
    void releaseOrder();
    boolean hasOrders();
}
