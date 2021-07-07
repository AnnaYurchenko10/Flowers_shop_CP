package sample;

import java.util.Arrays;

public class HashTable {
    private Flowers[] dataTable;
    static private ListFlowers listFlowers = new ListFlowers();
    static public int n=0;
    public HashTable() {
        dataTable = new Flowers[6];
    }

    private int hashFunc(int number) {
        return number%dataTable.length;
    }//хеш-функция

    public boolean checkFull()//проверка на переполнение элементами хеш-таблицы
    {
        return dataTable.length/n<1.5;
    }

    public void createHashTable()//увеличение хеш-таблицы
    {
        listFlowers.clear();//очистка списка
        for(Flowers flowers:dataTable)
        {
            if(flowers != null) {
                listFlowers.add(flowers);//добавление объектов из хеш-таблицы
            }
        }

        Arrays.fill(dataTable,null);//обнуление данных хеш-таблицы

        dataTable = Arrays.copyOf(dataTable, n*3/2);//увеличение размера таблицы в 1,5 раза

        Flowers current = listFlowers.getFirst();//получение первого элемента списка
        while (current!=null) {//пока не конец списка
            int key = hashFunc(current.getNumber());//вычисление хеш-функции по номеру артикула цветка
            int i = 1;//первая попытка опробывания
            while (true) {
                if (dataTable[key] == null) {//если ячейка пуста по заданному ключу
                    dataTable[key] = current;//добавление в ячейку по ключу
                    break;//выход из цикла
                } else {//если коллизия
                    key = (key + i * i) % dataTable.length;//вычисление ключа с помощью квадрата опробывания
                    i++;//увеличение попытки опробывания
                }

            }
            current = current.next;//переход к следующему элементу списка
        }

    }

    public void insert(Flowers data) {//добавление
        n++;
        int key = hashFunc(data.getNumber());
        int i=1;
        while (true) {
            if (dataTable[key] == null) {
                dataTable[key] = data;
                break;
            }
            else {
                key = (key + i*i) % dataTable.length;
                i++;
            }
        }
    }

    public void delete(Integer _key) {//удаление
        n--;
        int key = hashFunc(_key);
        int i=1;
        if (dataTable[key] == null) {
            return;
        }
        while (true) {
            if (dataTable[key] != null) {
                if (dataTable[key].getNumber().equals(_key)) {
                    dataTable[key].setNumber(-1);
                    break;
                }
            }
            else {
                key = (key + i*i) % dataTable.length;
                i++;
            }

        }
    }

    public void getElements()
    {
        for (int i = 0; i < dataTable.length; i++)
            if (dataTable[i] != null && !(dataTable[i].getNumber().equals(-1)))
            {
                flowersController.getList().add(dataTable[i]);
            }


    }

    public Flowers search(Integer _key)//поиск
    {
        int key = hashFunc(_key);
        int i=1;
        if (dataTable[key] == null) {
            return null;
        }
        while (true) {
            if (dataTable[key] != null) {
                if (dataTable[key].getNumber().equals(_key)) return dataTable[key];
            }
            else {
                key = (key + i*i) % dataTable.length;
                i++;
            }

        }
    }

    public Flowers searchFlowers(String name)
    {
        for (Flowers flowers : dataTable) {
            if (flowers == null) {
                continue;
            }
            if (flowers.getName().equals(name)) {
                return flowers;
            }
        }
        return null;
    }

    public void clear() {
        Arrays.fill(dataTable, null);
    }
}
