package tech.bittales.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.bittales.backend.model.Feature;
import tech.bittales.backend.repository.FeatureRepository;

import java.util.List;

@Service
public class FeatureService {
    private FeatureRepository featureRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public List<Feature> getFeaturesByStoryId(Long storyId) {
        return featureRepository.findByStoryId(storyId);
    }

    public Feature saveFeature(Feature feature) {
        return featureRepository.save(feature);
    }
}
