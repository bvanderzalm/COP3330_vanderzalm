public class App {
    public static void main(String[] args) {

        Encrypter e = new Encrypter();

        String x = "1234";
        System.out.println("Before encryption: " +x);
        String y = e.encrypt(x);
        System.out.println("After encryption: " +y);

        System.out.println();
        Decrypter d = new Decrypter();

        String a = "0189";
        System.out.println("Before decryption: " +a);
        String b = d.decrypt(a);
        System.out.println("After decryption: " +b);
    }
}
