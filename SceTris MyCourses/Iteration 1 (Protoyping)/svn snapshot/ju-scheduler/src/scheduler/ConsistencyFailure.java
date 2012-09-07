package scheduler;

/**
 * @author scravy
 *
 */
public class ConsistencyFailure {
	private int type;
	private Object subject;
	
	public ConsistencyFailure(int type, Object subject) {
		super();
		this.type = type;
		this.subject = subject;
	}

	public int getType() {
		return type;
	}

	public Object getSubject() {
		return subject;
	}
}
