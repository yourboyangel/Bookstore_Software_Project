package Model;

import java.io.*;

public class BinaryFileHandler<T> {
    public void writeObjectToFile(T object, String filePath, boolean append) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, append))) {
            outputStream.writeObject(object);
            System.out.println("Object written to file.");
        } catch (IOException ex) {
            System.out.println("Error writing object to file!");
        }
    }

    public T readObjectFromFile(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            T object = (T) inputStream.readObject();
            System.out.println("Object read from file.");
            return object;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error reading object from file.");
            return null;
        }
    }
}
