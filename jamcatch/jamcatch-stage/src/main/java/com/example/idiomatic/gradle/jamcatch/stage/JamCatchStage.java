package com.example.idiomatic.gradle.jamcatch.stage;

import com.example.idiomatic.gradle.javarca.model.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JamCatchStage implements Stage {
    private static final Logger LOG = LoggerFactory.getLogger(JamCatchStage.class);

    public JamCatchStage() {}

    @Override
    public String define() {
        LOG.debug("Constructing stage for JamCatch");
        return """
                x . . J . . . . . . 0 0 0 0 0 x
                x . . . . . . . . . . . . . . x
                x . . : : : : : : : : : . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . . . . . . . . . . . x
                x . . . . p . . . . . . . . . x
                x X X X X X X X X X X X X X X x
                """;
    }
}
