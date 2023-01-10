package com.bosko.abstract_assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String ipAddress;

    @Column(nullable = false, length = 15)
    private String path;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", path='" + path + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
