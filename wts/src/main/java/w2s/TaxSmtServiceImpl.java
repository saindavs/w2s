package w2s;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxSmtServiceImpl {

	@Autowired
	private DocumentDao dao = null;

	public DocumentList getDocuments(String aoid, String ooid) throws ClassNotFoundException {

		return dao.getDocumentIds(aoid, ooid);

	}

}
