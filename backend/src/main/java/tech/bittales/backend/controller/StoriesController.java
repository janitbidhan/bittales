package tech.bittales.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bittales.backend.model.Stories;
import tech.bittales.backend.repository.StoryRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stories")
public class StoriesController {
    @Autowired
    private StoryRepository storyRepository;

    @GetMapping("")
    public List<Stories> getAllStories() {
        return storyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stories> getStoryById(@PathVariable Long id) {
        Optional<Stories> Stories = storyRepository.findById(id);
        return Stories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Stories> createStory(@RequestBody Stories Stories) {
        Stories newStory = storyRepository.save(Stories);
        return ResponseEntity.created(URI.create("/api/stories/" + newStory.getId())).body(newStory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stories> updateStory(@PathVariable Long id, @RequestBody Stories storyDetails) {
        Optional<Stories> optionalStory = storyRepository.findById(id);
        if (optionalStory.isPresent()) {
            Stories Stories = optionalStory.get();
            Stories.setName(storyDetails.getName());
            Stories.setGenre(storyDetails.getGenre());
            Stories.setAudioLink(storyDetails.getAudioLink());
            storyRepository.save(Stories);
            return ResponseEntity.ok(Stories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable Long id) {
        Optional<Stories> optionalStory = storyRepository.findById(id);
        if (optionalStory.isPresent()) {
            storyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
