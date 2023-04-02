package queez.dao;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParticipationPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="apprenant")
	int apprenant_ID;
	
	@Column(name="quiz")
	int quiz_ID;

	@Override
	public int hashCode() {
		return Objects.hash(apprenant_ID, quiz_ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipationPK other = (ParticipationPK) obj;
		return apprenant_ID == other.apprenant_ID && quiz_ID == other.quiz_ID;
	}
	
	
}
