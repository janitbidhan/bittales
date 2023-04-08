package tech.bittales.backend.model;

import javax.persistence.*;
import java.util.List;
import tech.bittales.backend.model.Genre;

@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "jsonb")
    private String persons;

    @Column(name = "audioinrich", nullable = false)
    private Boolean audioInrich;

    @Column(name = "ismute", nullable = false)
    private Boolean isMute;

    @Column(name = "currenttime")
    private Float currentTime;


    @Column(name = "learnfromoldenabled", nullable = false)
    private Boolean learnFromOldEnabled;

    @ManyToOne
    @JoinColumn(name = "story_id", referencedColumnName = "id")
    private Stories story;

    @Column(name = "genres",columnDefinition = "jsonb")
    private String genres;

    // Constructors, getters, and setters

    public Feature() {
    }

    public Feature(Long id, String persons, Boolean audioInrich, Boolean isMute, Float currentTime, Boolean learnFromOldEnabled, Stories story, String genreIds) {
        this.id = id;
        this.persons = persons;
        this.audioInrich = audioInrich;
        this.isMute = isMute;
        this.currentTime = currentTime;
        this.learnFromOldEnabled = learnFromOldEnabled;
        this.story = story;
        this.genres = genreIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public Boolean getAudioInrich() {
        return audioInrich;
    }

    public void setAudioInrich(Boolean audioInrich) {
        this.audioInrich = audioInrich;
    }

    public Boolean getMute() {
        return isMute;
    }

    public void setMute(Boolean mute) {
        isMute = mute;
    }

    public Float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Float currentTime) {
        this.currentTime = currentTime;
    }


    public Boolean getLearnFromOldEnabled() {
        return learnFromOldEnabled;
    }

    public void setLearnFromOldEnabled(Boolean learnFromOldEnabled) {
        this.learnFromOldEnabled = learnFromOldEnabled;
    }

    public Stories getStory() {
        return story;
    }

    public void setStory(Stories story) {
        this.story = story;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
