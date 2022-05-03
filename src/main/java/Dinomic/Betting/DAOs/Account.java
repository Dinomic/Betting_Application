package Dinomic.Betting.DAOs;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @SequenceGenerator(name = "SEQ_ACCOUNTS", sequenceName = "SEQ_ACCOUNTS", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNTS")
    @Column(name = "ACCOUNT_ID", nullable = false)
    @Id
    private Long accountId;

    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String username;

    @Column(name = "ACCOUNT_PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ENABLED", nullable = false)
    private Boolean isEnabled = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_DETAIL_ID")
    private AccountDetail accountDetail;

    public Account(){

    }

    public Account(Long accountId, String username, String password, Boolean isEnabled, List<Authority> authorities) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long id) {
        this.accountId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public AccountDetail getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }
}

