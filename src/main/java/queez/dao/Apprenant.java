package queez.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("apprenant")
public class Apprenant extends User  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(mappedBy = "apprenant")
	List<Participation> participations;


	public List<Participation> getParticipations() {
		return participations;
	}


	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	
	
}
