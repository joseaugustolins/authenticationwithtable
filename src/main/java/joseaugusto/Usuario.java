package joseaugusto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {


    @NotBlank(message="required")
    @Id
    private String login;
    @NotBlank(message="required")
    private String nome;


    @NotBlank(message="required")
    private String senha;

    @ManyToMany
    @JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "nomeRole"))
    List<Role> roles;


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Usuario(){

    }

    public Usuario(@NotBlank(message = "required") String login, @NotBlank(message = "required") String nome, @NotBlank(message = "required") String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
