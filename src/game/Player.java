package game;

//import game.rooms.Lobby;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Player {
//    Room lobby = new Lobby();
//    private JSONRead jsonReader = new JSONRead();
    private String currentRoom;
    private ArrayList<String> inventory;
    private int moveCount;
    private boolean hasWon = false;

    public Player() {
        currentRoom = "lobby";
        inventory = new ArrayList<>();
        moveCount = 0;

    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public void addToInventory(String item){
        inventory.add(item);
    }

    public void wins() {
        this.hasWon = true;
    }

    public boolean checkWin() {
        return hasWon;
    }

}