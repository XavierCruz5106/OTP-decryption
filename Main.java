import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  private static void decrypt(File encryptedFile, File keys) throws IOException {
      File decryptedFile = new File("decrypted");
      BufferedWriter bw = new BufferedWriter(new FileWriter(decryptedFile, false));

      Scanner input = new Scanner(encryptedFile);
      Scanner keysInpt = new Scanner(keys);

      while (input.hasNextLine() && keysInpt.hasNextLine()) {
          String[] encryptedChars = input.nextLine().split(" ");
          String[] keysArr = keysInpt.nextLine().split(" ");

          for (int i = 0; i < encryptedChars.length; i++) {
              if (encryptedChars[i].isEmpty() || keysArr[i].isEmpty()) continue;
              int encryptedChar = Integer.parseInt(encryptedChars[i]);
              int key = Integer.parseInt(keysArr[i]);

              int decryptedChar = encryptedChar ^ key;
              bw.write((char) decryptedChar);
          }
          bw.write("\n");
      }
      input.close();
      keysInpt.close();
      bw.close();
  }

  public static void main(String[] args) throws IOException {
    if (args.length != 2){
      System.out.println("Please supply the encrypted file and the keys");
    }

    File encryptedFile = new File(args[0]);
    File keys = new File(args[1]);

    decrypt(encryptedFile, keys);
  }
}