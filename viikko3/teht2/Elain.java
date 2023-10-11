public class Elain {
    
    //Yksityinen muuttuja String nimi.
    protected String nimi;

    public Elain(){
        this.nimi = "nimetön";
    }

    public Elain(String nimi){
        this.nimi = nimi;
    }

    
    //Metodi herää(), joka tulostaa "[nimi] herää".
    public void heraa(){
        System.out.println(nimi + " herää");
    }
    
    //Metodi lepää(), joka tulostaa "[nimi] lepää".
    public void lepaa(){
        System.out.println(nimi + " lepää");
    }
    
    //Metodi toimi(), joka tulostaa "[nimi] toimii".
    public void toimi(){
        System.out.println(nimi + " toimii");
    }
}
