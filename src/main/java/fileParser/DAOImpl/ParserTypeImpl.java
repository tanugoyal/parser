package fileParser.DAOImpl;


import org.apache.commons.io.FilenameUtils;

import fileParser.DAO.ParserType;

/*@author:tanugoyal
 * This Class gives extention of the given file
 * */
public class ParserTypeImpl implements ParserType {

	@Override
	public String getTypeOfParser(String fileToRead) {
		// TODO Auto-generated method stub
		
		return FilenameUtils.getExtension(fileToRead);
    	
	}

}
