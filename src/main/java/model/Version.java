package model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VERSION")
@EnableAutoConfiguration
public class Version {

    @Id
    @Column(name = "ID_VERSION")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VERSION")
    @NotNull
    private String version;

    public String getVersion() {
        return version;
    }
}