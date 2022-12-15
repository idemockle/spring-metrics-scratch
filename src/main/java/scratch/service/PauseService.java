package scratch.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PauseService {

    private static final String[] EVENTS = {"11111", "22222"};

    @Autowired
    private MeterRegistry meterRegistry;

//    @Timed(histogram = true, extraTags = )
    public void pause() {
        Timer.Sample sample = Timer.start(meterRegistry);

        int randIdx = Math.random() > .5 ? 1 : 0;

        try {
            Thread.sleep(200 + (int) (50 * (randIdx + 1) * (Math.random() - .5)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sample.stop(Timer.builder("custom.service.event")
                         .tag("event", EVENTS[randIdx])
                         .publishPercentiles(.95, .99)
                         .publishPercentileHistogram()
                         .register(meterRegistry));

    }
}
