package fileParser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import fileParser.serviceExecutor.Producer;
import fileParserDTO.OrderDetails;

/*@author:tanugoyal
 * This Class uses executor Framework and parse the file
 * this is the main class of the application
 * */
public class Executor {
 
	

	
	/*this method decides the number of threads and start execution of input files concurrently.
	 * Currently assumption is made that each thread will process only 4 files and hence based on that 
	 * number of threads are decided (this can be changed)
	 * future objects combine the result and output it
	 * 	 * */

	public List<OrderDetails> execute(String[] args) {
		int numberOfThreads = args.length / 4 == 0 ? 1 : args.length / 4;
		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		final Collection<List<String>> result = splitListBySize(Arrays.asList(args), 4);
		/* (Arrays.asList(args), 4) */;
		List<CompletableFuture<String>> comp = new ArrayList<>();
		List<OrderDetails> allOrders = new ArrayList<OrderDetails>();
		for (List<String> partionedList : result) {
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				try {
					List<OrderDetails> order =createAndStartProducers(partionedList);
					order.stream().forEach(x ->allOrders.add(x));
				} catch (Exception e) {

				}
				return "Complete";
			}, service).handle((res, ex) -> {
				if (ex != null)
					return "Exception:" + ex;
				return res;
			});
			comp.add((CompletableFuture<String>) future);

		}

		CompletableFuture<Void> combinedFuture = CompletableFuture
				.allOf(comp.toArray(new CompletableFuture[comp.size()]));

		try {
			combinedFuture.get();
			List<OrderDetails> allOrdersInfo = allOrders.stream().sorted(Comparator.comparing(OrderDetails::getFileName).thenComparing(OrderDetails::getOrderId)).collect(Collectors.toList());
			allOrdersInfo.forEach(x -> {
				System.out.println(x.toString());
			});
		} catch (Exception e) {
		} finally {
			if (null != service)
				service.shutdown();
		}
		return allOrders;
	}
	
	/*this method starts processing sublist and reads file
	 * */
	private  List<OrderDetails> createAndStartProducers(List<String> filePath) {
		List<OrderDetails> allOrders = new ArrayList<OrderDetails>();
		 Producer producer = new Producer(); 
		for (int i = 0; i < filePath.size(); i++) {
			List<OrderDetails> order =producer.startProducer(filePath.get(i));
			order.stream().forEach(x ->allOrders.add(x));
		}
		return allOrders;
	}

	/*this method partitions the file into the sublist
	 * */
	public static Collection<List<String>> splitListBySize(List<String> list, int size) {

		if (!list.isEmpty() && size > 0) {
			final AtomicInteger counter = new AtomicInteger(0);
			return list.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size)).values();
		}
		return null;
	}

}