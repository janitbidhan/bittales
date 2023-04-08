package tech.bittales.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bittales.backend.model.Feature;
import tech.bittales.backend.repository.FeatureRepository;

import java.util.List;

@RestController
@RequestMapping("/features")
public class FeaturesController {

    @Autowired
    private FeatureRepository featuresRepository;

    // Get all Feature
    @GetMapping("/")
    public List<Feature> getAllFeatures() {
        return featuresRepository.findAll();
    }

    // Get a feature by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable(value = "id") Long featureId) throws Exception {
        Feature feature = featuresRepository.findById(featureId)
                .orElseThrow(() -> new Exception("Feature not found with id: " + featureId));
        return ResponseEntity.ok().body(feature);
    }

    // Create a new feature
    @PostMapping("/")
    public ResponseEntity<Feature> createFeature(@RequestBody Feature feature) {
        Feature createdFeature = featuresRepository.save(feature);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeature);
    }

    // Update a feature by ID
    @PutMapping("/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable(value = "id") Long featureId,
                                                 @RequestBody Feature featureDetails) throws Exception {
        Feature feature = featuresRepository.findById(featureId)
                .orElseThrow(() -> new Exception("Feature not found with id: " + featureId));
        feature.setPersons(featureDetails.getPersons());
        feature.setAudioInrich(featureDetails.getAudioInrich());
        feature.setMute(featureDetails.getMute());
        feature.setCurrentTime(featureDetails.getCurrentTime());
        feature.setLearnFromOldEnabled(featureDetails.getLearnFromOldEnabled());
        final Feature updatedFeature = featuresRepository.save(feature);
        return ResponseEntity.ok(updatedFeature);
    }

    // Delete a feature by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable(value = "id") Long featureId) throws Exception {
        Feature feature = featuresRepository.findById(featureId)
                .orElseThrow(() -> new Exception("Feature not found with id: " + featureId));
        featuresRepository.delete(feature);
        return ResponseEntity.noContent().build();
    }
}
