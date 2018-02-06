package bot_part;

public class Redactor {
    public static String cut(String str, int size){
        String temp = str.substring(0,size);
        String[] array = temp.split("\\. ");

        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i<array.length - 1; i++)
            sb.append(array[i]).append(". ");

        return sb.toString();
    }
}
