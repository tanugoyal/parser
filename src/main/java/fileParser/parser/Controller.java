package fileParser.parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Controller {


    private static BlockingQueue<String> queue;
    private static Collection<Thread> producerThreadCollection, allThreadCollection;

    public static void main(String[] args) {
        producerThreadCollection = new ArrayList<Thread>();
        allThreadCollection = new ArrayList<Thread>();
        queue = new LinkedBlockingDeque<String>(args.length);

        createAndStartProducers(args);
        createAndStartConsumers(args);

        for(Thread t: allThreadCollection){
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Controller finished");
    }

    private static void createAndStartProducers(String[] filePath){
        for(int i = 0; i < filePath.length; i++){
            Producer producer = new Producer(filePath[i], queue);
            Thread producerThread = new Thread(producer,"producer-"+i);
            producerThreadCollection.add(producerThread);
            producerThread.start();
        }
        allThreadCollection.addAll(producerThreadCollection);
    }

    private static void createAndStartConsumers(String[] filePath){
        for(int i = 0; i < filePath.length; i++){
            Thread consumerThread = new Thread(new Consumer(queue),"consumer-"+i);
            allThreadCollection.add(consumerThread);
            consumerThread.start();
        }
    }

    public static boolean isProducerAlive(){
        for(Thread t: producerThreadCollection){
            if(t.isAlive())
                return true;
        }
        return false;
    }
}
