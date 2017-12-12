package pl.edu.agh.iet.dts.ui.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.iet.dts.ui.persistence.domain.Preferences;

/**
 * @author Bartłomiej Grochal
 */
public interface PreferencesRepository extends MongoRepository<Preferences, String> { }
