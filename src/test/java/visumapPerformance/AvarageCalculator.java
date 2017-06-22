package visumapPerformance;

public class AvarageCalculator {

    private int sum;
    private int i;
    private int avarage;

    public AvarageCalculator(){
        this.i = 0;
        this.sum = 0;
        this.avarage = 0;
    }

    public void addTime(int time){
        this.sum += time;
        this.i++;
    }

    public int getAvarage(){
        if(i == 0) return 0;
        return sum / i;
    }
}
