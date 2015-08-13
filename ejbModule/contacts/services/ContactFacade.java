package contacts.services;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebService;

import contacts.dao.ContactDAO;
import contacts.entities.Contact;

@Stateless
@Startup
@WebService
public class ContactFacade {
	
	@EJB private ContactDAO dao;
	
	public Contact[] getContactsByCivilite(@WebParam(name="civilite") String civ){
		return dao.getContactsByCivilite(civ).toArray(new Contact[0]);
	}
	
	public Contact[] getContactsByNom(@WebParam(name="nom") String nom){
		return dao.getContactsByNom(nom).toArray(new Contact[0]);
	}
	
	public void addContact(@WebParam(name="civilite") String civ,
							@WebParam(name="nom") String nom,
							@WebParam(name="prenom") String prenom){
		dao.addContact(civ, nom, prenom);
	}
	
	public void removeContactByNom(@WebParam(name="nom") String nom,
									@WebParam(name="prenom") String prenom){
		dao.removeContacts(nom, prenom);
	}
	
	public void modifyContact( @WebParam(name="nom") String nom,  @WebParam(name="prenom") String prenom,
								String newCiv, String newNom, String newPrenom){
		dao.modifyContact(nom, prenom, newCiv, newNom, newPrenom);
}
	
}
