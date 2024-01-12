import Exceptions.InvalidIsbnException;
import Model.*;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception{
        BinaryFileHandler<ArrayList<Book>> bookBinaryFileHandler = new BinaryFileHandler<>();
        Book book1=new Book("123-456-78901-23-4","Can't Hurt Me","Biography","Lioncrest","12-01-2024",18.99,24.99,"David Goggins",20);
        Book book2=new Book("987-654-32109-87-6","Attack On Titan","Manga","Toei","12-10-2023",10.32,12.96,"Hajime Isayama",40);
        Book book3=new Book("456-789-01234-56-7","Demon Slayer","Manga","Ufotable","24-09-2024",7.99,11.99,"Koyoharu Gotouge",43);
        Book book4=new Book("890-123-45678-90-1","Your Next Five Moves","Self-Help","Lioncrest","17-10-2023",12.17,18.99,"Patrick Bet-David",23);
        Book book5=new Book("234-567-89012-34-5","Jujutsu Kaisen","Manga","Mappa","10-01-2024",7.99,11.99,"Gege Akutami",56);
        Book book6=new Book("567-890-12345-67-8","Atomic Habits","Self-Help","Lioncrest","13-05-2023",13.79,27.00,"James Clear",21);
        Book book7=new Book("012-345-67890-12-3","Dragon Ball Super","Manga","Toei","27-12-2023",8.49,9.99,"Akira Toriyama",3);
        Book book8=new Book("345-678-90123-45-6","Rich Dad Poor Dad","Finance","Lioncrest","15-01-2024",6.39,9.99,"Robert T.Kiyosaki",17);

        ArrayList<Book> list= new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);
        list.add(book6);
        list.add(book7);
        list.add(book8);

        bookBinaryFileHandler.writeObjectToFile(list,"books.dat",true);
    }
}
