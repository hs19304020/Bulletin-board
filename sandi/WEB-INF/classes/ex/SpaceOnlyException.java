package ex;

import javax.servlet.ServletException;

public class SpaceOnlyException extends ServletException {
	public SpaceOnlyException(String mess,Throwable e) {
		super(mess,e);
	}
}