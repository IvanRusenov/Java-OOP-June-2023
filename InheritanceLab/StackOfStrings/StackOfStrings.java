package InheritanceLab04StackOfStrings;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {

    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    //Public method: push(String item): void

    public void push(String item){

        this.data.add(item);

    }
    //• Public method: pop(): String
    public String pop(){
       return this.data.remove(data.size()-1);

    }
    //• Public method: peek(): String
    public String peek(){

        return this.data.get(data.size()-1);

    }
    //• Public method: isEmpty(): boolean

    public boolean isEmpty(){

        return this.data.isEmpty();

    }
}
