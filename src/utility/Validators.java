package utility;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="Validators")
@SessionScoped

public class Validators {

		String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String register() {
			return "thanks?faces-redirect=true";
		}


	
}
