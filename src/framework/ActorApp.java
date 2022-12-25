package framework;

import framework.supervisor.DefaultSuperVisor;
import framework.supervisor.SuperVisor;

public class ActorApp {

    private static SuperVisor _superVisor;

    public static SuperVisor _getSuperVisor() {
        return _superVisor;
    }

    public static void _setSuperVisor(SuperVisor superVisor) {
        _superVisor = superVisor;
    }

    public SuperVisor superVisor() {
        return _superVisor;
    }

    public static ActorApp run(String actorPath) {
        return new ActorApp(actorPath);
    }

    public static ActorApp run(SuperVisor superVisor) {
        return new ActorApp(superVisor);
    }

    private ActorApp(String actorPath) {
        _superVisor = DefaultSuperVisor.actors(actorPath);
    }

    private ActorApp(SuperVisor superVisor) {
        _superVisor = superVisor;
    }

}