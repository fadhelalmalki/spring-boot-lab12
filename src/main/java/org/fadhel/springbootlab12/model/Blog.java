package org.fadhel.springbootlab12.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "Body is required")
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JsonIgnore
    private User user;
}
