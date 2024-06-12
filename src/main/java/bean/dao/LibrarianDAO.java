package bean.dao;

import bean.dto.Librarian;

public interface LibrarianDAO {
	Librarian getLibrarianById(int id);
	
	String updateLibrarian(Librarian librarian);
	//void insertLibrarian(Librarian librarian);
	
}
