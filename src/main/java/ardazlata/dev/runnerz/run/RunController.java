package ardazlata.dev.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
