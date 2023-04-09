package tech.bittales.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "stories")
public class Stories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String genre;

    @Column(name = "audiolink", nullable = false)
    private String audioLink;

    @Column(name = "usercreated", nullable = false)
    private Boolean userCreated;

    @Column(name = "linkbook")
    private String linkBook;

    @Column(name = "gid")
    private String gid;
    // Constructors, getters, and setters

    public Stories() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public Boolean getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Boolean userCreated) {
        this.userCreated = userCreated;
    }

    public String getLinkBook() {
        return linkBook;
    }

    public void setLinkBook(String linkBook) {
        this.linkBook = linkBook;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Stories(String name, String genre, String audioLink, Boolean userCreated, String linkBook, String gid) {
        this.name = name;
        this.genre = genre;
        this.audioLink = audioLink;
        this.userCreated = userCreated;
        this.linkBook = linkBook;
        this.gid = gid;
    }
}
