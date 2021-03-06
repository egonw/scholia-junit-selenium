package opendata.scholia.Pages;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import opendata.scholia.Pages.Abstract.ScholiaContentPage;
import opendata.scholia.Tests.SPARQLWidgetTest;
import opendata.scholia.util.ConfigManager;
import opendata.scholia.util.PageType;
import opendata.scholia.util.model.Pair;

public class PageFactory {
	  
	  private static final Logger logger = LogManager.getLogger(PageFactory.class);
      static private PageFactory _instance = null;
      
	  private HashMap<String, ScholiaContentPage> pagePool = new HashMap<String, ScholiaContentPage>();
	
	  
	   //use getShape method to get object of type shape 
	   public ScholiaContentPage getPage(String url) {
		   
		  ScholiaContentPage scholiaPage = pagePool.get(url); 	
		  if(scholiaPage!=null) return scholiaPage;
		  
		  try {
			    Pair<Class,String> resultPair = PageType.getPageType(new URL(url));
			    Class classtype = resultPair.getFirst();
			    String pageTypeId = resultPair.getSecond();
			    
			    logger.debug("Page type identified" + classtype);
			    
			    scholiaPage = (ScholiaContentPage) classtype.getDeclaredConstructor()
						.newInstance();
			    scholiaPage.setURL(url);
			    scholiaPage.setPageTypeId(pageTypeId);
				//scholiaContentPage.setDriver(driver);
				pagePool.put(url, scholiaPage);
				return scholiaPage;
		  } catch (MalformedURLException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				logger.error("URL " + url + " is invalid, skipping");
		  }
		  
	      
	      return null;
	   }
	   
	   static public PageFactory instance(){
	        if (_instance == null) {
	            _instance = new PageFactory();
	        }
	        return _instance;
	    }

		public List<ScholiaContentPage> pageList() {
			List<ScholiaContentPage> list = new ArrayList<ScholiaContentPage>(pagePool.values());
			return list;
		}
	   

}
