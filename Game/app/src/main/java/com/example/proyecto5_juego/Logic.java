package com.example.proyecto5_juego;

import java.util.Random;

public class Logic {
    public int statusPlayer;
    public int statusCpu;
    public int result;
    private static SingletonHistory mStatusTracker = SingletonHistory.getInstance();

    private String[] results = {"You Lost", "You Tie", "You Won"};
    public String[] imageChoices = {"@drawable/rock", "@drawable/paper", "@drawable/scissors"};
    public String[] imageResults = {"@drawable/lose", "@drawable/handshake", "@drawable/trophy"};

    public Logic(int statusPlayer){

        this.statusCpu = generate();
        this.statusPlayer = statusPlayer;
        resultOfGame();
    }

    public int resultOfGame(){
        switch (statusCpu)  {
            case 0:
                switch ( statusPlayer){
                    case 0: result = 1; break;     //You tie
                    case 1: result = 2; break;     //You won
                    case 2: result = 0; break;     //You lost
                }
                break;

            case 1:
                switch ( statusPlayer ){
                    case 0: result = 0; break;     //You lost
                    case 1: result = 1; break;     //You tie
                    case 2: result = 2; break;     //You won
                }
                break;

            case 2:
                switch ( statusPlayer ){
                    case 0: result = 2; break;     //You won
                    case 1: result = 0; break;     //You lost
                    case 2: result = 1; break;     //You tie
                }
                break;
        }


        return result;
    }

    @Override
    public String toString(){
        return results[result];
    }

    public int generate(){
        int min = 0;
        int max = 3;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        return value;
    }

}
