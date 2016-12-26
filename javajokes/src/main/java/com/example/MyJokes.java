package com.example;

public class MyJokes {

    private String joke;
    public MyJokes(){
        joke = new String();
        joke = "A guy is sitting at home when he hears a knock at the door. " +
                "He opens the door and sees a snail on the porch. He picks up " +
                "the snail and throws it as far as he can. Three years later there is a knock on the door. " +
                "He opens it and sees the same snail. The snail says: What the hell was that all about?";

    }
    public String getJoke() {
        return joke;
    }
}
