package algorithms.search;

public class MazeState extends AState {

    public MazeState(String state) {
        this.state = state;
    }

    public MazeState(String state, double cost) {
        this.state = state;
        this.cost = cost;
    }

    public int getRow(){
        String withoutB = state.substring(1,state.length()-1);
        String [] stParts = withoutB.split(",");
        return Integer.parseInt(stParts[0]);
    }

    public int getColumn(){
        String withoutB = state.substring(1,state.length()-1);
        String [] stParts = withoutB.split(",");
        return Integer.parseInt(stParts[1]);
    }

}
