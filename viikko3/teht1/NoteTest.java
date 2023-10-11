public class NoteTest {
    
    public static void main(String[] args) {
        Note note1 = new Note();
        Note note2 = new Note("This is a test", Color.BLUE);

        System.out.println(note1.StringNote());
        System.out.println(note2.StringNote());

        note1.setNoteContent("Hello test!");
        note1.setNoteBackgroundColor(Color.ORANGE);

        System.out.println(note1.getNoteContent() + " & " + note1.getNoteBackgroundColor().toString());
    }
}
