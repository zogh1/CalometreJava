/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.DirtyWordsList;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author wassim
 */
public class DirtyWordsApi {
    
    
    public DirtyWordsList listOfBadWords(){
		// create Object Mapper
		      ObjectMapper mapper = new ObjectMapper();
                      URL url = getClass().getResource("dirtywords.json");

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
		// read JSON file and map/convert to java POJO
		try {
		    DirtyWordsList dw = mapper.readValue(new File(url.getPath()), DirtyWordsList.class);
		    return dw;
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
	}
    
}
