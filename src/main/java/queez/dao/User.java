package queez.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;



@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@Entity
@Table(name="users")
@DiscriminatorColumn(name ="user_type")
public class User  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( updatable = false, nullable = false)
	int id;
	
	@Column(nullable = false)
	String nom;
	@Column(nullable = false)
	String prenom;
	
	@Column(nullable = false)
	String login;
	@Column(nullable = false)
	String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Transient
	public String getUserType() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	
	public User() {
		
	}
	
	
	
	
	
	
//	public boolean verifyPassword(String password) {
//		return Password.check(password, this.password).withBcrypt();
//	}
}
