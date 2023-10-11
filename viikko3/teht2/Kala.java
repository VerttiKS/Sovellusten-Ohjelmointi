public class Kala extends Elain{
    
    public Kala(String nimi)
    {
        super(nimi);
    }

    //Kala-luokan tulee ylikirjoittaa toimi()-metodi tulostaen "[nimi] ui".

    @Override
    public void toimi(){
        System.out.println(this.nimi + " ui");
    }
}
