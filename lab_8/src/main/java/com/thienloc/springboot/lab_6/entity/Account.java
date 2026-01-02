package com.thienloc.springboot.lab_6.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "Username", length = 50)
    private String username;
    
    @Column(name = "Password", nullable = false)
    private String password;
    
    @Column(name = "Fullname")
    private String fullname;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Photo")
    private String photo;
    
    @Column(name = "Activated")
    private Boolean activated = true;
    
    @Column(name = "Admin")
    private Boolean admin = false;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Order> orders;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Account account = (Account) o;
        return getUsername() != null && Objects.equals(getUsername(), account.getUsername());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}