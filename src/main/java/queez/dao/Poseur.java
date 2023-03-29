package queez.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="poseur")
@DiscriminatorValue("poseur")
public class Poseur extends User   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column( updatable = false, nullable = false)
//	int id;
	
}
