enum Color {
    RED, GREEN, BLUE, YELLOW, ORANGE, PURPLE
}


public class Note {
    
    
    //Kaksi yksityistä muuttujaa: String content (sisältö) ja Color backgroundColor (taustaväri, jossa Color on jokin itse keksimäsi yksinkertainen enum-tietotyyppi eri väreille, kuten RED, GREEN, BLUE jne.).
    private String content;
    private Color backgroundColor;
    
    //Parametriton konstruktori, joka alustaa sisällön tyhjäksi merkkijonoksi ja taustavärin oletusväriksi.
    public Note() {
        this.content = "";
        this.backgroundColor = Color.RED;
    }
    
    
    //Parametrillinen konstruktori, joka ottaa sisällön ja taustavärin argumentteina.
    public Note(String content, Color backgroundColor){
        this.content = content;
        this.backgroundColor = backgroundColor;
    }
    
    
    //Getterit ja setterit molemmille muuttujille.
    public String getNoteContent(){
        return this.content;
    }

    public Color getNoteBackgroundColor(){
        return this.backgroundColor;
    }

    public void setNoteContent(String content){
        this.content = content;
        return;
    }

    public void setNoteBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return;
    }
    
    
    //toString-metodi, joka palauttaa merkkijonon muodossa sisällön ja taustavärin.
    public String StringNote(){
        return ("Content:" + this.content +  " Color:" + this.backgroundColor.toString());
    }

}