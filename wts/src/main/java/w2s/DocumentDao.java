package w2s;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class DocumentDao {

	public DocumentList getDocumentIds(String aoid, String ooid)
			throws ClassNotFoundException {
		List<Document> docList = new ArrayList<Document>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		DocumentList documentList = new DocumentList();
		try {
			String query = " WITH   W2_MOBILE_LOOKUP_CTE AS(  SELECT MAX(EMP_DOC_ID) AS EMP_DOC_ID,MAX(CO_DOC_ID) AS CO_DOC_ID,DATA_YEAR_NB,"+ 
				       " DATA_QTR_NB,     "+                               
				       " COUNTRY_TYP_C,    "+                                  
				       " AOID_NB,          "+                                  
				       " OOID_NB                      "+                      
				 " FROM DAA1.PA04.W2_MOBILE_LOOKUP_MMLO"+ 
				" WHERE AOID_NB     = '"+aoid+"'"+ 
				   " AND  OOID_NB    = '"+ooid+"'"+ 
				   " AND DATA_QTR_NB = '4'"+ 
				" GROUP BY AOID_NB,OOID_NB,DATA_YEAR_NB,DATA_QTR_NB,COUNTRY_TYP_C "+ 
				" )"+ 

				" SELECT EE.EMP_DOC_ID  EMP_DOC_ID, "+ 
				" DATA_YEAR_NB DATA_YEAR_NB  "+
				     " ,EE.DEC_VAL_NB  DEC_VAL_NB "+ 

				  " FROM W2_MOBILE_LOOKUP_CTE A , DAA1.PA04.W2_MOBILE_EE_MMEE EE "+ 
				" WHERE A.EMP_DOC_ID = EE.EMP_DOC_ID "+ 
				" AND   EE.ENTITY_ID        = '1' WITH UR";

			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection(
					"jdbc:db2://apl1.mf.adp.com:5021/DAA1", "spodili",
					"sreenu41");
			stmt = con.createStatement();


			rs = stmt.executeQuery(query);
			while (rs.next()) {

				Document document = new Document();
				document.setDocumentId(rs.getString("EMP_DOC_ID"));
				document.setYear(rs.getString("DATA_YEAR_NB"));
				document.setGrospay(rs.getString("DEC_VAL_NB"));
				docList.add(document);
			}
			
			
			
			documentList.setDocList(docList);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {

			}
		}
		/*
		 * session = factory.openSession();
		 * 
		 * List<Document> docList = new LinkedList<Document>();
		 * 
		 * 
		 * @SuppressWarnings({ "rawtypes", "deprecation" }) Query query =
		 * session .createSQLQuery(
		 * "SELECT MAX(EMP_DOC_ID) EMP_DOC_ID, MAX(CO_DOC_ID) CO_DOC_ID, DATA_YEAR_NB, DATA_QTR_NB,"
		 * +
		 * "   COUNTRY_TYP_C, AOID_NB, OOID_NB  FROM DAA1.PA04.W2_MOBILE_LOOKUP_MMLO "
		 * +
		 * "WHERE AOID_NB  = :aoid AND  OOID_NB  = :ooid AND DATA_QTR_NB = '4'"
		 * +
		 * "GROUP BY AOID_NB,OOID_NB,DATA_YEAR_NB,DATA_QTR_NB,COUNTRY_TYP_CWITH UR"
		 * ) .setString("aoid", aoid).setString("ooid", ooid);
		 * 
		 * @SuppressWarnings("unchecked") List<Object[]> categoryTableList =
		 * (List<Object[]>) query.list(); for (Object[] categoryValues :
		 * categoryTableList) {
		 * 
		 * Document document = new Document();
		 * document.setDocumentId(categoryValues[0].toString());
		 * document.setGrospay(categoryValues[1].toString());
		 * docList.add(document); }
		 */

		return documentList;
	}

}
