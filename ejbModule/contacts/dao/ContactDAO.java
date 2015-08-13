package contacts.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import contacts.entities.Contact;

@Stateless
public class ContactDAO {
	@PersistenceContext EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Contact> getContactsByCivilite(String civ) {
		return em.createNamedQuery("Contact.getContactsByCivilite").setParameter("civ", civ).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getContactsByNom(String nom) {
		return em.createNamedQuery("Contact.getContactsByNom").setParameter("nom", nom).getResultList();
	}

	public void addContact(String civ, String nom, String prenom) {
		Contact c = new Contact();
		c.setCivilite(civ.substring(0, 1).toUpperCase() + civ.substring(1, civ.length()));
		c.setNom(nom.toUpperCase());
		c.setPrenom(prenom.substring(0, 1).toUpperCase() + prenom.substring(1, prenom.length()));
		em.persist(c);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getContactsByNomPrenom(String nom, String prenom) {
		return em.createNamedQuery("Contact.getContactsByNomPrenom")
				.setParameter("nom", nom)
				.setParameter("prenom", prenom).getResultList();
	}

	public void removeContacts(String nom, String prenom) {
		for (Contact c : getContactsByNomPrenom(nom, prenom)) {
			em.remove(c);
		}
	}
	
	public void modifyContact(String nom, String prenom, String newCiv, String newNom, String newPrenom){
		Contact c = getContactsByNomPrenom(nom, prenom).get(0);
		c.setCivilite(newCiv.substring(0, 1).toUpperCase() + newCiv.substring(1, newCiv.length()));
		c.setNom(newNom.toUpperCase());
		c.setPrenom(newPrenom.substring(0, 1).toUpperCase() + newPrenom.substring(1, newPrenom.length()));
		em.merge(c);
	}

}
