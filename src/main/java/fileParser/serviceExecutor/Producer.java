package fileParser.serviceExecutor;

import java.util.ArrayList;
import java.util.List;

import fileParser.DAO.ParserType;
import fileParser.DAO.ParserTypeCall;
import fileParser.DAOImpl.ParserTypeCallImpl;
import fileParser.DAOImpl.ParserTypeImpl;
import fileParserDTO.OrderDetails;

/*Here processing of the file is started and results are collected into the list.
 * */
public class Producer {
 
	public List<OrderDetails> startProducer(String fileToRead) {
		List<OrderDetails> result = new ArrayList<>();
		ParserTypeCall typeimpl = new ParserTypeCallImpl();
		ParserType callImpl = new ParserTypeImpl();
		try {
			String extention = callImpl.getTypeOfParser(fileToRead);
			result = typeimpl.getResultOfParser(fileToRead, extention);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return result;
	}
}
