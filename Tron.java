import bridges.base.NamedColor;
import bridges.games.NonBlockingGame;

public class Tron extends NonBlockingGame {
    public static void main(String[] args) {
        //create Tron object
        //remember to fill in your own assignment number, username, and api key
        Tron game = new Tron(0, "mkawasaki", "1298518207600", 32, 32);

        //start game
        // game.initialize();
        
        game.initialize();
        game.start();


        
    }

    public Tron(int assignmentNumber, String username, String apiKey, int numRows, int numCols) {
        super(assignmentNumber, username, apiKey, numRows, numCols);

        //set game title and description
        //YOUR CODE HERE:
        setTitle("Ultimate Tron Game");
        setDescription("Play against a friend!");

    }

    //Snake objects representing the two players
    private Snake snake1;
    private Snake snake2;

    //color of first and second snake respectively
    private NamedColor color1;
    private NamedColor color2;

    //starts at 0 and increments by 1 each frame
    private int frame;

    //is the game currently running?
    private boolean game;

    //this method is called every time the game restarts
    public void initialize() {
        //assign 'snake1' and 'snake2' to new Snake objects
        //snake1 should be in the top left corner moving right
        //snake2 should be in the bottom right corner moving left
        //YOUR CODE HERE:
        


        snake1 = new Snake("right", 0, 0);
        snake2 = new Snake("left", getBoardHeight()-1, getBoardWidth()-1);

        //assign 'color1' and 'color2' to whatever you want
        //YOUR CODE HERE:
        color1 = NamedColor.dodgerblue;
        color2 = NamedColor.peachpuff;

        frame = 0;
        game = true;

        //draw the background by setting the color of every square on the board
        //YOUR CODE HERE:

        for(int r = 0; r < getBoardHeight(); r++){
            for(int c = 0; c<getBoardWidth(); c++){
                setBGColor(r, c, NamedColor.beige);
            }
        }

    }

    //this method is called once per frame
    public void gameLoop() {
        //check if the game is currently running and the frame number is even
        //having this code only run every two frames keeps the snakes from moving too fast
        if(game == true && (frame%2 == 0)) {
            //set the direction of 'snake1' to "up" if the W key is pressed
            //repeat for the S, A, and D keys
            //YOUR CODE HERE:
            if(keyW()){
                snake1.setDirection("up");
            }

            if(keyA()){
                snake1.setDirection("left");
            }
            if(keyS()){
                snake1.setDirection("down");
            }
            if(keyD()){
                snake1.setDirection("right");
            }
            

            

            //set the direction of 'snake2' to "up" if the up arrow key is pressed
            //repeat for the other three arrow keys
            //YOUR CODE HERE:
            if(keyUp()){
                snake2.setDirection("up");
            }

            if(keyLeft()){
                snake2.setDirection("left");
            }
            if(keyDown()){
                snake2.setDirection("down");
            }
            if(keyRight()){
                snake2.setDirection("right");
            }
            


            //what is the type of these variables?
            boolean dead1 = snake1.move(snake2, getBoardHeight(), getBoardWidth());
            boolean dead2 = snake2.move(snake1, getBoardHeight(), getBoardWidth());

            if(dead1) {
                //stop the game and change 'color2' to the winning color
                //YOUR CODE HERE:
                game = false;
                color2 = NamedColor.darkred;
            }
            if(dead2) {
                //stop the game and change 'color1' to the winning color
                //YOUR CODE HERE:
                game = false;
                color2 = NamedColor.darkred;
            }
        }

        //if the game is not running and the space key is pressed, restart the game
        else if(keySpace()) {
            initialize();
        }

        //set every square in 'snake1' to 'color1'
        //YOUR CODE HERE:
        for(int i = 0; i < snake1.getSize(); i++){
            setBGColor(snake1.getRow(i), snake2.getCol(i), color1);
        }

        //set every square in 'snake2' to 'color2'
        //YOUR CODE HERE:
        for(int i = 0; i < snake2.getSize(); i++){
            setBGColor(snake2.getRow(i), snake2.getCol(i), color2);
        }

        


        //increment the frame number
        frame += 1;
    }
}