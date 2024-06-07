package ardazlata.dev.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RunRepository {

    private final Map<Integer, Run> runMap = new HashMap<>();

    /**
     * Returns a list of all runs.
     */
    public List<Run> findAll() {
        return new ArrayList<>(runMap.values());
    }

    /**
     * Finds a run by its ID.
     *
     * @param id the ID of the run to find.
     * @return the run with the specified ID or null if not found.
     */
    public Run findById(Integer id) {
        return runMap.get(id);
    }

    @PostConstruct
    private void init() {
        runMap.put(1, new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR));
        runMap.put(2, new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 3, Location.OUTDOOR));
    }
}
