package fileParser.DAO;

import java.util.List;

import fileParser.CustomException.CustomException;
import fileParserDTO.OrderDetails;

public interface ParserTypeCall {

	public List<OrderDetails> getResultOfParser(String fileToRead,String ext) throws CustomException;
	
}
