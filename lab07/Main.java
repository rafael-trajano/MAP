import java.util.*;

// INTERFACES DEFINIDAS NO LABORATÓRIO 

interface MyMap<K, V> {
    void clear();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    boolean equals(Object o);
    V get(Object key);
    boolean isEmpty();
    V put(K key, V value);
    V remove(Object key);
    int size();
    Collection<V> values();
}

interface MyList<E> {
    boolean add(E e);
    void clear();
    boolean contains(Object o);
    boolean equals(Object o);
    E get(int index);
    boolean isEmpty();
    Iterator<E> iterator();
    boolean remove(int index);
    int size();
    Object[] toArray();
}

// IMPLEMENTAÇÕES DOS ADAPTERS

class MapToListAdapter<E> implements MyList<E> {
    private MyMap<Integer, E> map;

    public MapToListAdapter(MyMap<Integer, E> map) {
        this.map = map;
    }

    @Override
    public boolean add(E e) {
        map.put(map.size(), e);
        return true;
    }

    @Override
    public void clear() { map.clear(); }

    @Override
    public boolean contains(Object o) { return map.containsValue(o); }

    @Override
    public boolean equals(Object o) { return map.equals(o); }

    @Override
    public E get(int index) { return map.get(index); }

    @Override
    public boolean isEmpty() { return map.isEmpty(); }

    @Override
    public Iterator<E> iterator() { return map.values().iterator(); }

    @Override
    public boolean remove(int index) {
        return map.remove(index) != null;
    }

    @Override
    public int size() { return map.size(); }

    @Override
    public Object[] toArray() { return map.values().toArray(); }
}

class ListToMapAdapter<V> implements MyMap<Integer, V> {
    private MyList<V> list;

    public ListToMapAdapter(MyList<V> list) {
        this.list = list;
    }

    @Override
    public void clear() { list.clear(); }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer)) return false;
        int idx = (Integer) key;
        return idx >= 0 && idx < list.size();
    }

    @Override
    public boolean containsValue(Object value) { return list.contains(value); }

    @Override
    public boolean equals(Object o) { return list.equals(o); }

    @Override
    public V get(Object key) {
        return (containsKey(key)) ? list.get((Integer) key) : null;
    }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public V put(Integer key, V value) {
        
        if (containsKey(key)) {
            V old = list.get(key);
            list.remove(key);
            
            list.add(value); 
            return old;
        }
        list.add(value);
        return null;
    }

    @Override
    public V remove(Object key) {
        if (containsKey(key)) {
            V old = list.get((Integer) key);
            list.remove((Integer) key);
            return old;
        }
        return null;
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public Collection<V> values() {
        return Arrays.asList((V[]) list.toArray());
    }
}

class SimpleMap<K, V> implements MyMap<K, V> {
    private Map<K, V> internal = new HashMap<>();
    public void clear() { internal.clear(); }
    public boolean containsKey(Object k) { return internal.containsKey(k); }
    public boolean containsValue(Object v) { return internal.containsValue(v); }
    public boolean equals(Object o) { return internal.equals(o); }
    public V get(Object k) { return internal.get(k); }
    public boolean isEmpty() { return internal.isEmpty(); }
    public V put(K k, V v) { return internal.put(k, v); }
    public V remove(Object k) { return internal.remove(k); }
    public int size() { return internal.size(); }
    public Collection<V> values() { return internal.values(); }
}

class SimpleList<E> implements MyList<E> {
    private List<E> internal = new ArrayList<>();
    public boolean add(E e) { return internal.add(e); }
    public void clear() { internal.clear(); }
    public boolean contains(Object o) { return internal.contains(o); }
    public boolean equals(Object o) { return internal.equals(o); }
    public E get(int i) { return internal.get(i); }
    public boolean isEmpty() { return internal.isEmpty(); }
    public Iterator<E> iterator() { return internal.iterator(); }
    public boolean remove(int i) { return internal.remove(i) != null; }
    public int size() { return internal.size(); }
    public Object[] toArray() { return internal.toArray(); }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTE 1: MAP AGINDO COMO LISTA ===");
        MyMap<Integer, String> mapaOriginal = new SimpleMap<>();
        MyList<String> adaptadoParaLista = new MapToListAdapter<>(mapaOriginal);

        adaptadoParaLista.add("Java");
        adaptadoParaLista.add("Design Patterns");
        adaptadoParaLista.add("Adapter");

        System.out.println("Tamanho da lista (Map): " + adaptadoParaLista.size());
        System.out.println("Elemento no índice 1: " + adaptadoParaLista.get(1));
        System.out.println("Contém 'Java'? " + adaptadoParaLista.contains("Java"));

        System.out.println("\n=== TESTE 2: LISTA AGINDO COMO MAPA ===");
        MyList<String> listaOriginal = new SimpleList<>();
        MyMap<Integer, String> adaptadoParaMapa = new ListToMapAdapter<>(listaOriginal);

        adaptadoParaMapa.put(0, "Elemento A");
        adaptadoParaMapa.put(1, "Elemento B");

        System.out.println("Tamanho do mapa (List): " + adaptadoParaMapa.size());
        System.out.println("Valor da chave 0: " + adaptadoParaMapa.get(0));
        System.out.println("Existe a chave 1? " + adaptadoParaMapa.containsKey(1));
        
        adaptadoParaMapa.remove(0);
        System.out.println("Tamanho após remover chave 0: " + adaptadoParaMapa.size());
    }
}
