package snakeladder.models;

import java.util.UUID;

public class Player {
    String id;
    String name;

    public Player(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
