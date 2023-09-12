package com.bts.business;

import java.time.LocalDateTime;
import java.util.Objects;




public class User {
	private int UserId;
    private String name;
    private String email;
    private String password;
    private String role;
    private int roleId,projectId;
    private LocalDateTime lastLoggedIn;

    public User(int UserId, String name, String email, String password, int roleId, int projectId) {
        this.UserId= UserId;
    	this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.projectId = projectId;
    }

    public User(String email, String password) {
		// TODO Auto-generated constructor stub
    	
    	this.email=email;
    	this.password=password;
	}

	public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public LocalDateTime getLastLoggedIn() {
        return lastLoggedIn;
    }
	
	

    @Override
	public int hashCode() {
		return Objects.hash(UserId, email, lastLoggedIn, name, password, projectId, role, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return UserId == other.UserId && Objects.equals(email, other.email)
				&& Objects.equals(lastLoggedIn, other.lastLoggedIn) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && projectId == other.projectId
				&& Objects.equals(role, other.role) && roleId == other.roleId;
	}

	public void setLastLoggedIn(LocalDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }
    
    public int getUserId() {
        return UserId;
    }

	  
}
