package framework;

import framework.supervisor.DefaultSupervisor;
import framework.supervisor.Supervisor;

public class ActorApp {

    private static Supervisor _supervisor;

    public static Supervisor _getSuperVisor() {
        return _supervisor;
    }

    public static void _setSuperVisor(Supervisor superVisor) {
        _supervisor = superVisor;
    }

    public Supervisor superVisor() {
        return _supervisor;
    }

    public static ActorApp run(String actorPath) {
        return new ActorApp(actorPath);
    }

    public static ActorApp run(Supervisor superVisor) {
        return new ActorApp(superVisor);
    }

    private ActorApp(String actorPath) {
        _supervisor = DefaultSupervisor.actors(actorPath);
    }

    private ActorApp(Supervisor superVisor) {
        _supervisor = superVisor;
    }

}