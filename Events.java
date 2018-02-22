import objectdraw.*;
import java.awt.*;
import java.util.Random;

/**
 * Exam 1 - Programming
 * Monty Hall Problem
 * ---- Erika Sklaver ----
 * ---- Section 2 ----
 */
public class Events extends FrameWindowController {

    // the width of each door
    private static final int DOOR_WIDTH = 50;

    // the height of each door
    private static final int DOOR_HEIGHT = 100;

    // the spacing between each of the three doors
    private static final int DOOR_SPACING = 90;
    
    // the number of doors (for randomly selecting the correct door)
    private static final int NUM_DOORS = 3;

    // the starting position of the ball
    private static final Location BALL_POS = new Location(300, 300);

    // the size of the ball
    private static final int BALL_DIAMETER = 20;

    // location of the score display
    private static final Location TEXT_POS = new Location(150, 80);

    // the color of the ball
    private static final Color BALL_COLOR = Color.RED;

    // the color of a door (either closed, revealed as a goat, or revealed as a car)
    private static final Color CLOSED_DOOR_COLOR = Color.GRAY;
    private static final Color GOAT_DOOR_COLOR = Color.BLUE;
    private static final Color CAR_DOOR_COLOR = Color.GREEN;
    
    private FilledRect door1;
    private FilledRect door2;
    private FilledRect door3;
    
    
    private Text display; 
    
    private int carCounter = 0;
    private int totalCounter = 0; 
    
    private FilledOval ball; 
    
    private Location lastPoint; 
    private boolean ballGrabbed; 
    
    private Random generator = new Random(); 
   
    private int correctDoor; 
    private int goatDoors; 
   
    public void begin() {
         
        door1 = new FilledRect(canvas.getWidth()/2-DOOR_WIDTH/2-DOOR_SPACING, canvas.getHeight()/2-DOOR_HEIGHT/2, DOOR_WIDTH, DOOR_HEIGHT, canvas);
        door2 = new FilledRect(canvas.getWidth()/2-DOOR_WIDTH/2, canvas.getHeight()/2-DOOR_HEIGHT/2, DOOR_WIDTH, DOOR_HEIGHT, canvas);
        door3 = new FilledRect(canvas.getWidth()/2-DOOR_WIDTH/2+DOOR_SPACING, canvas.getHeight()/2-DOOR_HEIGHT/2, DOOR_WIDTH, DOOR_HEIGHT, canvas);
        
        door1.setColor(CLOSED_DOOR_COLOR); 
        door2.setColor(CLOSED_DOOR_COLOR);
        door3.setColor(CLOSED_DOOR_COLOR);
        
        display = new Text("Cars won: "+ carCounter + " of " +totalCounter, TEXT_POS, canvas); 
        
        ball = new FilledOval(BALL_POS, BALL_DIAMETER, BALL_DIAMETER, canvas); 
        ball.setColor(BALL_COLOR);
        
        correctDoor = generator.nextInt(NUM_DOORS); 
        
    }
        
    
    public void onMousePress(Location point) {
        lastPoint = point;
        ballGrabbed = ball.contains(point); 
    }

    
    public void onMouseRelease(Location point) {
        if (door1.contains(point)&& ballGrabbed){
            ball.hide(); 
            if (correctDoor == 1){
                door3.setColor(GOAT_DOOR_COLOR); 
            }else if (correctDoor == 2){
                door2.setColor(GOAT_DOOR_COLOR); 
            }else{ 
                goatDoors = generator.nextInt(2); 
                if (goatDoors == 0){
                    door2.setColor(GOAT_DOOR_COLOR);
                }else{
                    door3.setColor(GOAT_DOOR_COLOR); 
                }
            }
        }
        
        if (door2.contains(point)&& ballGrabbed){
            ball.hide(); 
            if (correctDoor == 0){
                door3.setColor(GOAT_DOOR_COLOR); 
            }else if (correctDoor == 2){
                door1.setColor(GOAT_DOOR_COLOR); 
            }else{ 
                goatDoors = generator.nextInt(2); 
                if (goatDoors == 0){
                    door1.setColor(GOAT_DOOR_COLOR);
                }else{
                    door3.setColor(GOAT_DOOR_COLOR); 
                }
            }
        }
        
        if (door3.contains(point)&& ballGrabbed){
            ball.hide(); 
            if (correctDoor == 0){
                door2.setColor(GOAT_DOOR_COLOR); 
            }else if (correctDoor == 1){
                door1.setColor(GOAT_DOOR_COLOR); 
            }else{ 
                goatDoors = generator.nextInt(2); 
                if (goatDoors == 0){
                    door1.setColor(GOAT_DOOR_COLOR);
                }else{
                    door2.setColor(GOAT_DOOR_COLOR); 
                }
            }
        }
        
        if (!door1.contains(point) && !door2.contains(point) && !door3.contains(point)){
            ball.moveTo(BALL_POS);
        }
    
}

    
    public void onMouseClick(Location point) {
        if ((door1.contains(point) || door2.contains(point) || door3.contains(point))){
            
            totalCounter++;
            display.setText("Cars won: "+ carCounter + " of " +totalCounter); 
        }
        
          if (correctDoor == 0 && (door1.contains(point) || door2.contains(point) || door3.contains(point))){
              door1.setColor(CAR_DOOR_COLOR);
            }else if (correctDoor == 1 && (door1.contains(point) || door2.contains(point) || door3.contains(point))){
                door2.setColor(CAR_DOOR_COLOR); 
            }else if (correctDoor == 2 && (door1.contains(point) || door2.contains(point) || door3.contains(point))){
                door3.setColor(CAR_DOOR_COLOR);
        
    }
    
    if (correctDoor == 0 && door1.contains(point)){
        
        carCounter++;
        display.setText("Cars won: "+ carCounter + " of " +totalCounter);
    }else if (correctDoor == 1 && door2.contains(point)){
       
        carCounter++;
        display.setText("Cars won: "+ carCounter + " of " +totalCounter);
    }else if (correctDoor == 2 && door3.contains(point)){
        
        carCounter++;
        display.setText("Cars won: "+ carCounter + " of " +totalCounter);
    }
    
}

    
    public void onMouseEnter(Location point) {
        
    }

    
    public void onMouseExit(Location point) {
        door1 = new FilledRect(canvas.getWidth()/2-DOOR_WIDTH/2-DOOR_SPACING, canvas.getHeight()/2-DOOR_HEIGHT/2, DOOR_WIDTH, DOOR_HEIGHT, canvas);
        door2 = new FilledRect(canvas.getWidth()/2-DOOR_WIDTH/2, canvas.getHeight()/2-DOOR_HEIGHT/2, DOOR_WIDTH, DOOR_HEIGHT, canvas);
        door3 = new FilledRect(canvas.getWidth()/2-DOOR_WIDTH/2+DOOR_SPACING, canvas.getHeight()/2-DOOR_HEIGHT/2, DOOR_WIDTH, DOOR_HEIGHT, canvas);
        
        door1.setColor(CLOSED_DOOR_COLOR); 
        door2.setColor(CLOSED_DOOR_COLOR);
        door3.setColor(CLOSED_DOOR_COLOR);
        
        //display = new Text("Cars won: "+ carCounter + " of " +totalCounter, TEXT_POS, canvas); 
        
//         ball = new FilledOval(BALL_POS, BALL_DIAMETER, BALL_DIAMETER, canvas); 
//         ball.setColor(BALL_COLOR);

ball.show();
ball.moveTo(BALL_POS); 
ball.sendToFront();
        
        correctDoor = generator.nextInt(NUM_DOORS); 
        
    }

    
    public void onMouseMove(Location point) {
        
    }

    
    public void onMouseDrag(Location point) {
        if (ballGrabbed){
            ball.move(point.getX()-lastPoint.getX(), point.getY()-lastPoint.getY()); 
            lastPoint = point;
        }
    }

}
