package scratch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import scratch.service.PauseService;

@RestController
public class ScratchController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PauseService pauseService;

    @GetMapping
    public void mainEndpoint() {
        log.info("Endpoint triggered");
        pauseService.pause();
    }
}
