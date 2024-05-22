
package dao;

import java.io.Serializable;

public class User implements Serializable {
    
    private int idUser;
    private String nomUser;
    private String login;
    private String password;

    public User() {
    }

    public User(int idUser, String nmUser, String login, String password) {
        this.idUser = idUser;
        this.nomUser = nmUser;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    
    
    public int getIdUser() {
        return idUser;
    }

    public String getNmUser() {
        return nomUser;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNmUser(String nmUser) {
        this.nomUser = nmUser;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nmUser=" + nomUser + ", login=" + login + ", password=" + password + '}';
    }
    
    
    
}
