package game;

import java.util.Scanner;

import static game.AsciiArts.asciiArtLose;

class ActionParser {
    private String[] moveString;
    private String moveChoice;
    private boolean validInput = false;

    private Scanner scanner = new Scanner(System.in);
    private TextParser parser = new TextParser();
    
    public boolean playerMove(Player player) throws Exception{
        boolean quit = false;

        while (!quit && !player.checkWin()) {
            while (!validInput) {
                System.out.println("Enter your action (example: move east, take <item name>) > ");
                String action = scanner.nextLine();
                moveString = parser.parseInput(action);
                moveChoice = moveString[0].toLowerCase();
                switch (moveChoice) {
                    case "move":
                        validInput = Moves.move(player, moveString[1].toLowerCase());
                        break;
                    case "take":
                        validInput = Moves.take(player, moveString[1]);
                        break;
                    case "use":
                        validInput = Moves.use(player, moveString[1]);
                        break;
                    case "pull":
                        Puzzle roomPuzzle = RoomFactory.getRoom(player).getPuzzle();
                        validInput = Moves.puzzle(roomPuzzle, moveString);
                        System.out.println(roomPuzzle.toString());

                        if (validInput) {
                            player.setMoveCount(player.getMoveCount() + 1);

                            if (roomPuzzle.isSolved() && RoomFactory.getRoom(player).getRoomItems().contains("key")) {
                                player.addToInventory("key");
                                RoomFactory.getRoom(player).deleteRoomItem("key");
                                System.out.println("You solved the puzzle! You grab the key.");
                            }
                        }
                        break;
                    case "look":
                        validInput = Moves.look(player);
                        break;
                    case "restart":
                        validInput = Moves.restart();
                        break;
                    case "quit":
                        quit = true;
                        validInput = true;
                        asciiArtLose();
                        break;
                    default:
                        System.out.println("Sorry that is not a valid input\nIf you need help just type help!");
                }
            }
            if (!quit) validInput = false;
        }

        return quit;
    }

}