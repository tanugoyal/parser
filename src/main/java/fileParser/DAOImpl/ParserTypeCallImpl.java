package fileParser.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import fileParser.CustomException.CustomException;
import fileParser.DAO.ParserTypeCall;
import fileParser.ReaderParser.CSVParser;
import fileParser.ReaderParser.JsonParser;
import fileParserDTO.OrderDetails;

/*@author:tanugoyal
 * This Class decides which type of file parser need to be used Based on extention
 * */
public class ParserTypeCallImpl implements ParserTypeCall{

	@Override
	public List<OrderDetails> getResultOfParser(String fileToRead,String extention) throws CustomException {
		// TODO Auto-generated method stub
	
		List<OrderDetails> result=new ArrayList<>();
		if(extention.equalsIgnoreCase("csv")) {
    		CSVParser csv= new CSVParser();
    		try {
				result=csv.csvParser(fileToRead);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
//				throw new CustomException("Error occured for file"+(new File(fileToRead)).getName()+"for"+e.getMessage());
			}
    		
    	}else if(extention.equalsIgnoreCase("json")) {
    		JsonParser json = new JsonParser();
    		try {
				result = json.jsonParser(fileToRead);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
    	}
		
		return result;
	}

}
