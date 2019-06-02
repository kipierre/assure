package org.cephas.acdia.model;



import org.cephas.acdia.model.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by admin on 08-05-19.
 */
@Entity
@Table(name = "seminaires")
public class Seminaire extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    private String theme;


    private String name;
    @NotNull
    @Lob
    @Column(name="description")
    private String description;

    @Column(name="type", length=100, nullable=false)
    private String type;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;


    @OneToMany(mappedBy = "seminaire", fetch = FetchType.LAZY)
    private Set<InscriptionSeminaire> inscriptionSeminaires = new LinkedHashSet<>();

    public Seminaire() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Set<InscriptionSeminaire> getInscriptionSeminaires() {
        return inscriptionSeminaires;
    }

    public void setInscriptionSeminaires(Set<InscriptionSeminaire> inscriptionSeminaires) {
        this.inscriptionSeminaires = inscriptionSeminaires;
    }
}
