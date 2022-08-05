package fileParser.DAO;

import fileParser.CustomException.CustomException;

public interface ParserType {

	public String getTypeOfParser(String filetoRead) throws CustomException;	
	
	
}
