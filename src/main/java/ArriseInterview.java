import java.util.LinkedList;
import java.util.Queue;

public class ArriseInterview {

    // 3 producers 4 consumers
    // queue of fix size let say 4
    // shared resource can be int
    // task is mimic producer consumer
    // producer is producing and consumer is consuming
    // if queue full producer should not take more elements
    // if queue empty consumer should stop consuming

    /**
     * BouncedQueue
     * Queue
     * capacity
     */
    public static class BouncedQueue<I extends Number> {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;

        public BouncedQueue(int capacity) {
            this.capacity = capacity;
        }

        // produce method check for size
        public synchronized void produce(int item) throws InterruptedException {
            while (queue.size() == capacity) {
                wait();
            }
            queue.offer(item);
            System.out.println(Thread.currentThread().getName() + " added to producer");
            notifyAll();
        }

        // consume check empty
        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            int item = queue.poll();
            System.out.println(Thread.currentThread().getName() + " polled out of queue");
            notifyAll();
            return item;
        }
    }

    // Producer will also have BoundedQueue
    // some kind of value to start
    public static class Producer implements Runnable {
        private final BouncedQueue<Integer> queue;
        private final int startValue;

        public Producer(BouncedQueue<Integer> queue, int startValue) {
            this.queue = queue;
            this.startValue = startValue;
        }


        @Override
        public void run() {
            int value = startValue;
            try {
                while (true) {
                    queue.produce(value++);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Consumer will consume BoundedQueue
    public static class QueueConsumer implements Runnable {
        private final BouncedQueue<Integer> queue;

        public QueueConsumer(BouncedQueue<Integer> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            try{
                while(true){
                    Integer item = queue.consume();
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        int producerCount = 3;
        int consumerCount = 4;
        int queueCapacity = 4;

        BouncedQueue<Integer> queue = new BouncedQueue<>(queueCapacity);

        for(int i =0 ; i< producerCount; i++){
            new Thread(new Producer(queue, i*100), "Producer: "+(i+1)).start();
        }

        for(int i =0 ; i< consumerCount; i++){
            new Thread(new QueueConsumer(queue), "Consumer: : "+(i+1)).start();
        }
    }

}
