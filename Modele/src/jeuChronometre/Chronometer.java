package jeuChronometre;

public final class Chronometer {
    private long begin, end;
    
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public String getChrono() {
    	// Je pense pas que le jeu depassera l'heure Haha ! Inutile de travailler inutilement
    	String tmp = getHours()<10 ? "0"+Integer.toString(getHours()) : ""+Integer.toString(getHours()); 
    	tmp = (getMinutes() % 60)<10 ? tmp+":0"+(getMinutes() % 60) : tmp+":"+(getMinutes() % 60);   	
    	tmp = (getSeconds() % 60)<10 ? tmp+":0"+(getSeconds() % 60) : tmp+":"+(getSeconds() % 60);  
    	return tmp;
    }
    
    public long getMilliseconds() {
        return end-begin;
    }
 
    public int getSeconds() {
        return (int)((end - begin) / 1000.0);
    }
 
    public int getMinutes() {
        return (int)((end - begin) / 60000.0);
    }
 
    public int getHours() {
        return (int)((end - begin) / 3600000.0);
    }
}
