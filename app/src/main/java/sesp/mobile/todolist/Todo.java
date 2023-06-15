package sesp.mobile.todolist;

public class Todo {

    public String id;
    public String activity;
    public String time;

    public Todo(String id, String activity, String time){
        this.id = id;
        this.activity = activity;
        this.time = time;
    }

    public Todo() {
    }
}
