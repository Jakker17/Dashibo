package com.dashibo.be.common.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class CustomUser implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String displayName;

    private String password;

    private String username;

    private Boolean enabled = true;

    private Boolean accountNonExpired = true;

    private Boolean accountNonLocked = true;

    private Boolean credentialsNonExpired = true;
    //TODO: prevest na enum hodnoty role
    private String role;

    public CustomUser(String email,
                      String displayName,
                      String password,
                      String username,
                      Boolean enabled,
                      Boolean accountNonExpired,
                      Boolean accountNonLocked,
                      Boolean credentialsNonExpired,
                      Roles role) {
        this.email = email;
        this.displayName = displayName;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.role = role.label;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public enum Roles {
        ADMIN("ADMIN"), USER("USER");

        public final String label;

        Roles(String label) {
            this.label = label;
        }
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setRole(Roles role) {
        this.role = role.label;
    }
}
