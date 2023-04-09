package tech.bittales.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bittales.backend.model.Feature;
import tech.bittales.backend.model.Stories;
import tech.bittales.backend.repository.FeatureRepository;
//import tech.bittales.backend.service.AudioService;

import java.util.List;

@RestController
@RequestMapping("/features")
public class FeaturesController {

    @Autowired
    private FeatureRepository featuresRepository;

//    @Autowired
//    private AudioService audioService;

    // Get all Feature
    @GetMapping("/")
    public List<Feature> getAllFeatures() {
        List<Feature> features = featuresRepository.findAll();

//        // Loop through each Feature
//        for (Feature feature : features) {
//            Stories story = feature.getStory();
//            if (story != null && story.getGid() != null) {
//                // Get the audio file from Google Drive using the gid
////                String audioFile = getAudioFileFromGoogleDrive(story.getGid());
//
//                // If the audio file was successfully retrieved, update the audioLink
//                if (audioFile != null) {
//                    story.setAudioLink("/audio/" + story.getName().replaceAll("\\s+", "") + ".wav");
//                    // Save the audio file to local storage
////                    saveAudioFileLocally(audioFile, story.getName());
//                }
//            }
//        }
//
//        // Convert the modified features list back to JSON and return it
//        ObjectMapper mapper = new ObjectMapper();
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(features);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return features;
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
