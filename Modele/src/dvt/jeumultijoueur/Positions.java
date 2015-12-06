package dvt.jeumultijoueur;

public class Positions {
    private int positionX,positionY;
    private int limitLeftX,limitRightX,limitTopY,limitBotY;
    private int speed=5;
    
    public Positions(int positionX,int positionY,int limitLeftX,int limitRightX,int limitTopY,int limitBotY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.limitLeftX = limitLeftX;
        this.limitRightX = limitRightX;
        this.limitTopY = limitTopY;
        this.limitBotY = limitBotY; 
    }
    
    public void moveRight() {
        if(this.positionX+speed<=limitRightX-100) {
            this.positionX = this.positionX+speed;
        }
    } 

    public void moveLeft() {
        if(this.positionX-speed>=limitLeftX) {
            this.positionX = this.positionX-speed;
        }
    }    

    public void moveBot() {
        if(this.positionY+speed<=limitBotY-100) {
            this.positionY = this.positionY+speed;
        }
    }     
 
    public void moveTop() {
        if(this.positionY-speed>=limitTopY) {
            this.positionY = this.positionY-speed;
        }
    }    
    
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
    public int getPositionX() {
        return positionX;
    }
    
    public int getPositionY() {
        return positionY;
    }
}
