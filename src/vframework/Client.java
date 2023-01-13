package vframework;

import vframework.exceptions.ClientInitiationException;

public class Client{
    private static ActorProxyFactory _actorFactory = null;

    private Client(String namespace){
        _actorFactory = new ActorProxyFactory(namespace);
    }

    public static Client init(String namespace){ return new Client(namespace); }

    public ActorProxyFactory actorFactory(){ return _actorFactory; }

    public static ActorProxyFactory _getActorFactory(){
        if(_actorFactory == null){
            throw new ClientInitiationException();
        }
        return _actorFactory;
    }
}
