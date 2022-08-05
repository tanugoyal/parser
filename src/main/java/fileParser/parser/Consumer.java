package fileParser.parser;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> q){
        queue = q;
    }

    public void run(){
 
        while(true){
            String line = queue.poll();

            if(line == null && !Controller.isProducerAlive())
                return;
 
            
            if(line != null){
                System.out.println("Result Output "+line);
                //Do something with the line here like see if it contains a string
            }

        }
    }
}
