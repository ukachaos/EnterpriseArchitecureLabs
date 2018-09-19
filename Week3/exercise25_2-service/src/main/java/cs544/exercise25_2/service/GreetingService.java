package cs544.exercise25_2.service;

public class GreetingService implements IGreeting {
    private int state;

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public synchronized int calc(char op, int num) {

        if (op == '+') {
            state += num;
        } else if (op == '-') {
            state -= num;
        } else if (op == '*') {
            state *= num;
        } else if (op == '/') {
            state /= num;
        }

        return state;
    }
}