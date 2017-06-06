package w2s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequestMapping("")
public class TaxStatementController {

	@Autowired
	TaxSmtServiceImpl taxSmtServiceImpl = null;

	@RequestMapping(value = {"/taxs"}, method = RequestMethod.GET)
	public DocumentList getDocIds( @RequestParam(value="aoid", required=false) String aoid, 
            @RequestParam(value="ooid", required=false) String ooid) {
		DocumentList docList = null;
		try {
			 docList = taxSmtServiceImpl.getDocuments(aoid, ooid);
		    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docList;
	}

}
