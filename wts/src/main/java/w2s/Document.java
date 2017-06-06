package w2s;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Document {

	String documentId;
	String grospay;
	String year;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getGrospay() {
		return grospay;
	}

	public void setGrospay(String grospay) {
		this.grospay = grospay;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
