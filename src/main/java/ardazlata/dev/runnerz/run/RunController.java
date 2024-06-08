package ardazlata.dev.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    public List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Run> findById(@PathVariable Integer id) {
        return runRepository.findById(id)
                .map(run -> new ResponseEntity<>(run, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("")
    public ResponseEntity<Run> createRun(@Valid @RequestBody Run run) {
        runRepository.save(run);
        return new ResponseEntity<>(run, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Run> updateRun(@PathVariable Integer id, @RequestBody Run run) {
        return runRepository.findById(id)
                .map(existingRun -> {
                    Run updatedRun = new Run(id, run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location());
                    runRepository.save(updatedRun);
                    return new ResponseEntity<>(updatedRun, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRun(@PathVariable Integer id) {
        return runRepository.findById(id)
                .map(run -> {
                    runRepository.delete(run);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
