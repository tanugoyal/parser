package fileParser.ReaderParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import fileParser.CustomException.CustomException;
import fileParserDTO.OrderDetails;


/*@author:tanugoyal
 * This Class Parses Json File and map to OrderDetails
 * */
public class JsonParser {
	public  List<OrderDetails> jsonParser(String fileToRead) throws Exception {
		OrderDetails orderDetail = null;
		List<OrderDetails> lst = new ArrayList<>();
		ObjectMapper mapper= new ObjectMapper();
		File file=new File(fileToRead);    //creates a new file instance  
		FileReader fr=new FileReader(file);  
		 //reads the file  
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		String line;  
		int LineId =0;
		try {
			while((line=br.readLine())!=null)  
			{  
				
				orderDetail = mapper.readValue(line, OrderDetails.class);
				orderDetail.setLine(String.valueOf(LineId++));
				orderDetail.setFileName((new File(fileToRead)).getName());
				orderDetail.setResult("ok");
				lst.add(orderDetail);
			}
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException("Error occured for file"+(new File(fileToRead)).getName()+"for"+e.getMessage());
		}finally {
			fr.close();
			br.close();
		}
		return lst;
	}

}
