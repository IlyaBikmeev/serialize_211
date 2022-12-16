import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Program {
    public static void main(String[] args) {
        //Сериализация          Объект -> байты
        //Десериализация        байты -> объект

        Person person = new Person("Ivan Ivanov", 45, true);
        System.out.printf("Person original:\n%s\n", person);
        serializePerson(person, "person.bin");

        Person deserializedPerson = deserializePerson("person.bin");
        System.out.printf("Person deserialized:\n%s\n", deserializedPerson);
    }

    //Принимаем объект, хотим его записать в файл.
    private static void serializePerson(Person person, String fileName) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(fileName))) {

            objectOutputStream.writeObject(person);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Принимаем на вход имя файла, хотим получить готовый объект.
    private static Person deserializePerson(String fileName) {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (Person) objectInputStream.readObject();
        }
        catch(IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
