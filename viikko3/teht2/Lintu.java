public class Lintu extends Elain{
    
    public Lintu(String nimi)
    {
        super(nimi);
    }

    //Lintu-luokan tulee ylikirjoittaa toimi()-metodi tulostaen "[nimi] lentää".

    @Override
    public void toimi(){
        System.out.println(this.nimi + " lentää");
    }
}
