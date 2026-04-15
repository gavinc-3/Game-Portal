public class Answer {
    
    String label;
    int id;


    Answer(String label, int id) {
        this.label = label; 
        this.id = id;
    }

    public String toString() {
        return label;  
    }

    public int toID() {
        return id;
    }

}