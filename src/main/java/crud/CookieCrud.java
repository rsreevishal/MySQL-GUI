package crud;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class CookieCrud {
	private UserCrud userCrud;
	
	public CookieCrud() {
		userCrud = new UserCrud();
	}
	
	public User getSessionUser(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=0;i<cookies.length; i++) {
				if(cookies[i].getName().equals("user")) {
					if(cookies[i].getValue().length() > 0) {
						return userCrud.get(Integer.parseInt(cookies[i].getValue()));
					} else {
						return null;
					}
				}
			}
		}
		return null;
	}
	
	public void setSessionUser(User user, HttpServletResponse response) {
		response.addCookie(new Cookie("user", user.toString()));
	}
	
	public void deleteSessionUser(HttpServletResponse response) {
		Cookie cookie = new Cookie("user","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
