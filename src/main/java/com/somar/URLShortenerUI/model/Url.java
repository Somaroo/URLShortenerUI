package com.somar.URLShortenerUI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Url implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", unique=true, nullable=false, precision=20)
    private long id;
    @Lob

    private String url;

    private String urlShort;

    private LocalDateTime urlDate;

    private long urlCallNumber;


}
