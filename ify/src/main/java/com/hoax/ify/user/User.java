package com.hoax.ify.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "{ify.constraints.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;

    @NotNull(message = "{ify.constraints.displayName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String displayName;

    @NotNull(message = "{ify.constraints.password.NotNull.message}")
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{ify.constraints.password.Pattern.message}")
    private String password;

    public User(String username, String displayName, String password) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
    }
}
