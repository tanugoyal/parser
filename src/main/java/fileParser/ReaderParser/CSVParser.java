package fileParser.ReaderParser;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import fileParser.CustomException.CustomException;
import fileParserDTO.OrderDetails;

/*@author:tanugoyal
 * This Class Parses CSV File and map to OrderDetails
 * */
public class CSVParser {

	public List<OrderDetails> csvParser(String fileToRead) throws CustomException {
		List<OrderDetails> list = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(fileToRead))) {
			List<String[]> readLine = reader.readAll();
			int count=0;
			for(int i=0 ;i<readLine.size();i++) {
				list.add(setOrderDetails(readLine.get(i),fileToRead,count++));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException("Error occured for file"+(new File(fileToRead)).getName()+"for"+e.getMessage());
		}
	}
	
	public OrderDetails setOrderDetails(String[] str,String fileToRead,int line) throws CustomException {
		OrderDetails orderInfo = new OrderDetails();
		try {
		  	orderInfo.setOrderId((Integer.valueOf(str[0])));
	    	orderInfo.setAmount(new BigDecimal(str[1]));
	    	orderInfo.setCurrency(str[2]);
	    	orderInfo.setComment(str[3]);
	    	orderInfo.setFileName((new File(fileToRead)).getName());
	    	orderInfo.setLine(String.valueOf(line));
	    	orderInfo.setResult("ok");
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException("Error occured for file"+(new File(fileToRead)).getName()+"for"+e.getMessage());
		}
    
  
		return orderInfo;
		
	}
}
