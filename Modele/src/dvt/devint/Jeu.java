package dvt.devint;

import static dvt.jeuchronometre.ConstantesJeu.OPTIMAL_TIME;

public abstract class Jeu extends Fenetre {
    private static final long serialVersionUID = 1L;

    /**
     * Permet de creer et initialiser la fenetre de jeu
     */
    public Jeu() {
        init();
        this.pack(); 
        this.requestFocusInWindow();
        this.setVisible(true);
    }
    
    /**
     * La loop du jeu qui permet de garder un FPS (frame per seconds) constant peu importe le PC
     */
    public void loop() {
        long lastLoopTime,timeLoop;

        reset();
        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            
            update();
            render();

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void render();
    public abstract void reset();
}
