import java.io.*;
import java.util.*;

public class Main {
    public static class Toy {
        static int lastId = 1;

        public int id;
        public String name;
        public int cnt;
        public int freq;

        // Конструктор
        public Toy()
        {
            id = lastId;
            lastId++;
        }
    }

    List<Toy> lst = new ArrayList<Toy>();
    BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );

    public void enter( Toy s, boolean full )
    {
        try
        {
            if (full) {
                System.out.print("Введите имя: ");
                s.name = in.readLine();
                System.out.print("Введите количество: ");
                s.cnt = Integer.parseInt(in.readLine());
            }
            System.out.print("Введите вероятность: ");
            s.freq = Integer.parseInt(in.readLine());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Индексы
    int findbyid( int v )
    {
        int i = 0;
        for( ; i < lst.size(); i++ )
        {
            Toy s = lst.get(i);
            if (s.id == v)
                return i;
        }
        return -1;
    }

    void print( Toy s )
    {
        System.out.println( s.id + ": " + s.name + " (кол. " + s.cnt + ")" + s.freq + "%" );
    }

    void printList()
    {
        if (lst.size() == 0)
            System.out.println( "  Список пуст" );

        for( int i = 0; i < lst.size(); i++ )
            print( lst.get(i) );
    }

    Random rnd = new Random();

    void run()
    {
        try {

            while (true) {
                System.out.println("Текущий список");
                printList();
                System.out.println("1. Добавить");
                System.out.println("2. Удалить");
                System.out.println("3. Играть");
                System.out.println("0. Выход");
                System.out.print("Введите: ");

                String t, t2;
                t = in.readLine();

                if (t.equals("0"))
                    break;

                if (t.equals("1")) {
                    Toy s = new Toy();
                    enter(s, true);
                    lst.add(s);
                }
                if (t.equals("2")) {
                    System.out.print("Введите ИД: ");
                    int id = Integer.parseInt( in.readLine() );

                    int i = findbyid(id);
                    if (i >= 0)
                        enter(lst.get(i), false);
                }
                if (t.equals("3")) {
                    if (lst.size() == 0)
                    {
                        System.out.println( "Нет игрушек" );
                        continue;
                    }

                    int freq = rnd.nextInt() % 100;
                    int i;

                    for( i = 0; i < lst.size(); i++ )
                    {
                        Toy toy = lst.get(i);

                        if (freq < toy.freq * toy.cnt)
                        {
                            System.out.println( "Приз: " + toy.name );
                            toy.cnt--;
                            if (toy.cnt <= 0)
                                lst.remove(i);
                            i = -1;
                            break;
                        }
                    }

                    if (i >= 0)
                        System.out.println("Не выиграли");
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        (new Main()).run();
    }
}
