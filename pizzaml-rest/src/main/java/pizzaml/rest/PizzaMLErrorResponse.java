package pizzaml.rest;

public class PizzaMLErrorResponse {
	
	private int status;
	private String message;
	private long timestamp;
	
	public PizzaMLErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PizzaMLErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
