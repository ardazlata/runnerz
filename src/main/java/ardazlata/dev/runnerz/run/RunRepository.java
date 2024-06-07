package ardazlata.dev.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

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
     * @return an Optional containing the run with the specified ID or an empty Optional if not found.
     */
    public Optional<Run> findById(Integer id) {
        return Optional.ofNullable(runMap.get(id));
    }

    @PostConstruct
    private void init() {
        runMap.put(1, new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
        runMap.put(2, new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3, Location.OUTDOOR));
    }

    /**
     * Prints the details of a run based on its ID.
     * @param id the ID of the run to print details for.
     */
    public void printRunDetails(Integer id) {
        Optional<Run> runOptional = findById(id);
        runOptional.ifPresent(run -> System.out.println("Run found: " + run));
    }

    /**
     * Handles a run based on its ID. If not found, provides a default run.
     * @param id the ID of the run to handle.
     */
    public void handleRun(Integer id) {
        Optional<Run> runOptional = findById(id);
        Run run = runOptional.orElseGet(() -> new Run(0, "Default Run", LocalDateTime.now(), LocalDateTime.now(), 0, Location.INDOOR));
        System.out.println("Run: " + run);
    }
}
