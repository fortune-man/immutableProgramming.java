package immutableClass;

public class ImmutableObject {
    String name = "JooHyeongKim";
    String newName = name.replace("Kim","");
    final String nameSmallLetter = "joohyeong";
//    String nameSmallLetter = "jooheyongkim"; // variable nameSmallLetter is already defined


    public static void main(String[] args) {
        ImmutableObject immutableObject = new ImmutableObject();
        System.out.println(immutableObject.name);
        System.out.println(immutableObject.newName);
        System.out.println(immutableObject.nameSmallLetter);
    }
}
