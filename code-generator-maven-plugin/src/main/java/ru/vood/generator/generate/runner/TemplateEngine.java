package ru.vood.generator.generate.runner;

public enum TemplateEngine {

    FREE_MARKER(FreeMarkerEngine.class),
    FREE_MARKER_DATABASE(FreeMarkerDatabaseEngine.class),
    VELOCITY(VelocityEngine.class),
    ERROR(ErrorEngine.class);

    private final Class clazz;

    <T extends RunnerEngine> TemplateEngine(Class<T> runnerEngine) {
        clazz = runnerEngine;
    }

    public Class getClazz() {
        return clazz;
    }
}


