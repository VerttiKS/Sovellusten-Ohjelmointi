import java.util.ArrayList;

public class Elainmain {
    public static void main(String[] args) {

        Elain elain1 = new Elain("TestElain");
        Lintu elain2 = new Lintu("Haukka");
        Kala elain3 = new Kala("Kilpikonna");

        elain1.heraa();
        elain1.toimi();
        elain1.lepaa();


        elain2.heraa();
        elain2.toimi();
        elain2.lepaa();

        elain3.heraa();
        elain3.toimi();
        elain3.lepaa();

        
        ArrayList<Elain> elaimet = new ArrayList<>();
        elaimet.add(new Lintu("TestLintu"));
        elaimet.add(new Kala("TestKala"));

        for( Elain e : elaimet)
        {
            e.heraa();
        }

        for( Elain e : elaimet)
        {
            e.toimi();
        }

        for( Elain e : elaimet)
        {
            e.lepaa();
        }

    }
}

