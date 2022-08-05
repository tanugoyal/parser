package fileParser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;


import fileParser.DAO.parserType;
import fileParser.DAO.parserTypeCall;
import fileParser.DAOImpl.parserTypeCallImpl;
import fileParser.DAOImpl.parserTypeImpl;
import fileParserDTO.OrderDetails;

public class Producer implements Runnable {
	private String fileToRead;
	private BlockingQueue<String> queue;

	public Producer(String filePath, BlockingQueue<String> q) {
		fileToRead = filePath;
		queue = q;
	}

	@Override
	public void run() {
		List<OrderDetails> result = new ArrayList<>();
		parserTypeCall typeimpl = new parserTypeCallImpl();
		parserType callImpl = new parserTypeImpl();
		try {
			String extention = callImpl.getTypeOfParser(fileToRead);
			result = typeimpl.getResultOfParser(fileToRead, extention);
			result.forEach(x -> {
				try {
					queue.put(x.toString());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			

//			System.out.println(Thread.currentThread().getName() + " added \"" + result.toString()
//					+ "\" to queue, queue size: " + queue.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
