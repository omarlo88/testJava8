import apple.laf.JRSUIConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.Integer.compare;

/*
* @Auteur Omar Lo
* */

public class Main {



    public static void main(String[] args) {

        /*
            Expérimentation de Java 8

            1) Arrays
        * */

        int[] tab = {1, 30, 5, 8,10};
        int[] tab1 = new int[] {1, 30, 5, 8,10};
        System.out.println(Arrays.toString(tab));
        int[] tab2 = new int[] {};
        String[] tabStr = {"A", "B", "D", "L","K"};
        String[] tabStr2 = new String[]{"A", "B", "D", "L","K"};

        int index = Arrays.binarySearch(tab, 1);

        boolean isExiste = Arrays.binarySearch(tab, 1) >= 0;

        System.out.println(Arrays.binarySearch(tab, 10));
        System.out.println(index);
        System.out.println(Arrays.binarySearch(tabStr, "B"));

        //int[] array = new int[20_000_000];
        int[] array = new int[5];
        Random random = new Random();

        Arrays.setAll(array, i -> random.nextInt(100));

        System.out.println(Arrays.toString(array));

        Arrays.parallelSetAll(array, i -> random.nextInt(2));

        System.out.println(Arrays.toString(array));

        /*for (int i:array
             ) {
            System.out.println(i);
        }*/

        Instant date = Instant.now();
        System.out.println(date);
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localDateTime);
        System.out.println(LocalDate.of(2019, 02, 28));

        Arrays.stream(tab).anyMatch(x -> x == 30);
        System.out.println(Arrays.stream(tab).anyMatch(x -> x == 300));

        Arrays.stream(tab).sorted().forEach(System.out::println);

        Arrays.stream(tab).boxed().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        Integer[] te = new Integer[]{24,3,5,34,56,1};
        System.out.println(" *** *** test Interface Comparator is functional interface *** ");
        Arrays.stream(te).sorted((a,b) -> compare(a,b)).forEach(System.out::println);
        Arrays.stream(te).sorted(Integer::compare).forEach(System.out::println);
        Arrays.stream(te).sorted(Comparator.comparingInt(a -> a)).forEach(System.out::println);
        Arrays.stream(te).sorted(Comparator.naturalOrder()).forEach(System.out::println);

        System.out.println(" *** *** test Interface Comparable not fuctional interface *** ");
        Arrays.stream(te).sorted((a,b) -> b.compareTo(a)).forEach(System.out::println);
        Arrays.stream(te).sorted(Integer::compareTo).forEach(System.out::println);

        Arrays.stream(tab).mapToObj(x -> new Integer(x)).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.println(" *********** test new !!");
        Arrays.stream(tab).mapToLong(Long::new).forEach(System.out::println);

        Stream.of(2,456,688,776).forEach(System.out::println);

        Stream.of(2,456,688,776, 2, 1,1,456).distinct().forEach(System.out::println);

        List<String> chaines = Arrays.asList("1", "2", "3", "4", "5");

        String chaine = chaines.stream().reduce("", String::concat);
        System.out.println("chaine " + chaine);

        int somme = Arrays.stream(tab).reduce(0, (x, y) -> x + y);
        System.out.println("Somme = " + somme);
        System.out.println(Arrays.stream(tab).sum());

        System.out.println(Arrays.stream(tab).reduce(Math::max).getAsInt());
        System.out.println(Arrays.stream(tab).reduce(Integer::max).getAsInt());
        System.out.println(Arrays.stream(tab).reduce((x, y) -> Math.max(x,y)).isPresent());

        Arrays.fill(tab, 2);
        Arrays.toString(tab);
        System.out.println(Arrays.toString(tab));

        Arrays.parallelSort(tab);
        System.out.println(Arrays.toString(tab));

        Arrays.parallelPrefix(tab, (x,y) -> x+y);
        System.out.println(Arrays.toString(tab));

        Arrays.stream(tab).map(Integer::new);

        Integer[] t = new Integer[]{24,3,5,34,56,1};

        Arrays.parallelSort(t, Comparator.reverseOrder());
        System.out.println(Arrays.toString(t));

        Function<Integer, Boolean> isExist = n -> n >= 0;
        Function<Integer, Boolean> isExist1 = n -> {return n >= 0;};
        System.out.println("isExist " + isExist.apply(4));


        List<String> fruits = Arrays.asList("melon", "abricot", "fraise", "cerise");
        afficherListe(fruits, (format, arguments) -> String.format(format,arguments));
        afficherListe(fruits, String::format);
        
        List<String> f = new ArrayList<>(fruits);

        f.removeIf(s -> s.equals("melon"));
        fruits.forEach(System.out::println);

        Integer[] valeurs = {10, 4, 2, 7, 5, 8, 1, 9, 3, 6};
        System.out.println(" *********** test valeurs comparator !!");
        Arrays.sort(valeurs, Integer::compareTo);
        System.out.println(Arrays.toString(valeurs));
        Arrays.sort(valeurs, Integer::compare);
        System.out.println(Arrays.toString(valeurs));
        Arrays.sort(valeurs, Comparator.comparingInt(Integer::intValue));
        System.out.println(Arrays.toString(valeurs));
        Arrays.sort(valeurs, Comparator.reverseOrder());
        System.out.println(Arrays.toString(valeurs));
        System.out.println(Arrays.deepToString(valeurs));

        int nimNumber = Arrays.stream(valeurs).min(Comparator.naturalOrder()).get();
        System.out.println("nimNumber: " + nimNumber);

        int[] valeurs1 = {10, 4, 2, 7, 5, 8, 1, 9, 3, 6};
        int nimNumber1 = IntStream.of(valeurs1).min().getAsInt();
        System.out.println("nimNumber1: " + nimNumber1);

        IntStream.of(valeurs1).min().ifPresent(System.out::println);

        IntStream.of(valeurs1)
                .distinct()
                .sorted()
                .limit(3)
                .forEach(System.out::println);

        IntStream.range(1, 100).forEach(System.out::println);
        int[] toArray = IntStream.range(1, 100).toArray();
        IntStream.range(1,100).boxed().collect(Collectors.toList());

        Arrays.sort(valeurs, Main::comparerEntierAscendant);
        System.out.println(Arrays.deepToString(valeurs));

        Arrays.sort(valeurs, (x,y)->comparerEntierAscendant(x,y));
        System.out.println(Arrays.deepToString(valeurs));

        Comparator<Integer> myComparator = (n1,n2) -> n2 - n1;
        Arrays.sort(valeurs, myComparator);
        System.out.println(Arrays.deepToString(valeurs));
        Arrays.sort(valeurs, (n1,n2) -> n2 - n1);
        System.out.println(Arrays.deepToString(valeurs));


        Arrays.sort(valeurs, (n1, n2) -> n2 - n1);
        System.out.println("(n1, n2) -> n2 -n1 : " + Arrays.deepToString(valeurs));


        DoubleFunction<String> formater = (x) -> String.format("%.3f", x);
        System.out.println(formater.apply(3.14116D));

        DoubleToIntFunction dtif = x -> (int) x;
        System.out.println(dtif.applyAsInt(3.14));



        List<String> names = new ArrayList<>();
        names.add("Mahesh ");
        names.add("Suresh ");
        names.add("Ramesh ");
        names.add("Naresh ");
        names.add("Kalpesh ");

        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
        System.out.println(names);
        names.sort(String::compareTo);
        System.out.println(names);
        Collections.sort(names, String::compareTo);
        System.out.println(names);


        // Test pour les interfaces fonctionnelles les lambdas pour faire des calculs arithmétiques

        MyOperation addition = (a, b) -> { return a + b;};
        MyOperation soustration = (int a, int b) -> a - b;
        MyOperation multiplication = (a, b) -> a * b;
        MyOperation division = (a, b) -> {
            if (b == 0) {
                throw new RuntimeException("Impossible de diviser par " + b);
            } else {
                return a / b;
            }
        };

        MyOperation division2 = (a, b) -> a / b;

        System.out.println("addition " + caluler(6, 8, addition));
        System.out.println("soustration " +caluler(6, 8, soustration));
        System.out.println("multiplication " +caluler(6, 8, multiplication));
        System.out.println("division " +caluler(8, 8, division));
        System.out.println("division2 " +caluler(8, 8, division2));

        System.out.println("**  Utilisation Consumer pour afficher les résultats **");
        Consumer<String> rest = a -> System.out.println(a);
        afficherRes(Integer.toString(caluler(6, 8, addition)), rest);
        afficherRes(Integer.toString(caluler(6, 8, soustration)), rest);
        afficherRes(Integer.toString(caluler(6, 8, multiplication)), rest);
        afficherRes(Integer.toString(caluler(6, 8, division)), rest);
        afficherRes(Integer.toString(caluler(6, 8, division2)), rest);

        System.out.println("**  Utilisation Consumer pour afficher les résultats **");
        System.out.println("**  ET écriture directement du consumer pas d'initialisation ");

        afficherResInt(caluler(6, 8, (a,b) -> a + b), a -> System.out.println(a));
        afficherResInt(caluler(6, 8, (a,b) -> a - b), a -> System.out.println(a));
        afficherResInt(caluler(6, 8, (x,y) -> x * y), x -> System.out.println(x));
        afficherResInt(caluler(6, 8, (a, b) -> {
            if (b == 0) {
                throw new RuntimeException("Impossible de diviser par " + b);
            } else {
                return a / b;
            }
        }), x -> System.out.println(x));

        BiFunction<Integer, Integer, Integer> ttt = (a, b) -> a + b;
        System.out.println("BiFuction " + calculer2(5,5, ttt));

        ToIntBiFunction<Integer, Integer> ttt3 = (a, b) -> a + b;
        System.out.println("ToIntBiFunction " + calculer3(5,5, ttt3));

        System.out.println("ToIntBiFunction.. " + calculer3(5,5, (a, b) -> a + b ));


        Arrays.asList(1,3,4,5,67,7,6,20,30).forEach(System.out::println);

        int[] ints1 = Arrays.asList(1, 3, 4, 5, 67, 7, 6, 20, 30)
                .stream()
                .filter(n -> n >= 20)
                .mapToInt(Integer::intValue)
                .toArray();
        //.forEach(System.out::println);

        System.out.println(" ***** ");
        Arrays.asList(1,3,4,5,67,7,6,20,30)
                .stream()
                //.sorted(Comparator.naturalOrder())
                .sorted(Comparator.reverseOrder())
                .map(a -> a * a)
                .mapToInt(Integer::intValue)
                .forEach(System.out::println);


        IntSummaryStatistics stats = Arrays.asList(1,3,4,5,67,7,6,20,30)
                .stream()
                //.mapToInt(a -> a.intValue())
                .mapToInt(x -> x)
                .summaryStatistics();

        System.out.println("** Plus grand nombre ** : " + stats);
        System.out.println("** Plus grand nombre ** : " + stats.getMax());
        System.out.println("** Plus grand nombre ** : " + stats.getCount());



        StringJoiner s = new StringJoiner(",");
        s.add("test").add("next");
        System.out.println("new StringJoiner(\",\")" + s.toString());

        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = stringList.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        System.out.println(filtered);

        Stream.of("abc", "", "bc", "abc", "efg", "abcd", "", "jkl")
                .map(a -> a.toUpperCase())
                .forEach(System.out::println);

        String[] strTab = {"abc", "", "bc", "abc", "efg", "abcd", "", "jkl"};

        int [] g = new int[]{1,2};
        int [] g1 = {1,2};
        int [] g2 = new int[2];

        Arrays.stream(strTab)
                .map(String::toUpperCase)
                //.collect(Collectors.joining("," ,"{", "}"))
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(System.out::println);

        Integer value = new Integer(10);
        Optional<Integer> a = Optional.ofNullable(value);
        System.out.println(a.isPresent()? a : 0);
        Optional<Integer> a2 = Optional.ofNullable(new Integer(2));
        System.out.println(a2.orElse(new Integer(0)));
        System.out.println(a2.orElse(0));
        System.out.println(a2.orElseGet(() -> new Integer(0)));


        System.out.println(ZoneId.systemDefault());

        LocalDate today = LocalDate.now();
        System.out.println(" next week-end: " + today.plus(1, ChronoUnit.WEEKS));

        LocalDate date1 = LocalDate.now();
        System.out.println("Current date: " + date1);

        //get the next tuesday
        LocalDate nextTuesday = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println("Next Tuesday on : " + nextTuesday);

        //get the second saturday of next month
        LocalDate firstInYear = LocalDate.of(date1.getYear(),date1.getMonth(), 1);
        System.out.println("firstInYear: " + firstInYear);
        LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(
                DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("Second Saturday on : " + secondSaturday);


        class  Person {
            private String name;
            private int age;

            Person( String name, int age ) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return this.name;
            }

            public int getAge() {
                return this.age;
            }
            public String toString() {
                return this.name+" ("+this.age+")";
            }
        }


        Person[] people = new Person[] {
                            new Person("mark", 12 ),
                            new Person("tet", 30 ),
                            new Person("mark", 5 ),
                            new Person("nene", 50 ),
                            new Person("mama", 40 ),
                            new Person("Mama", 40 )
        };

        Person[] men = Arrays.stream(people)
                .filter(p -> p.getAge() <= 30)
                .toArray(Person[]::new);

        Arrays.stream(men).forEach(System.out::println);



        Arrays.sort(people, (x,b) -> x.getAge() - b.getAge());
        System.out.println(Arrays.toString(people));
        System.out.println(" ***** ");

        Arrays.sort(people, (x,b) -> x.getName().compareTo(b.getName()));
        System.out.println(Arrays.toString(people));
        System.out.println(" ***** ");

        Arrays.sort(people, Comparator
                .comparingInt(Person::getAge).reversed()
                .thenComparing(Person::getName));

        System.out.println(Arrays.toString(people));

        Arrays.stream(people)
                //.filter(p -> p.getAge() == 0)
                .sorted(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Arrays.stream(people)
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Arrays.sort(people,(n,b) -> n.getName().compareTo(b.getName()));
        Arrays.sort(people, Comparator.comparing(Person::getName));

        Arrays.stream(people).sorted((l,k) -> l.getName().compareTo(k.getName()));
        Arrays.stream(people).sorted(Comparator.comparing(Person::getName));

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(() ->{

        });

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList())
                .forEach(System.out::println);


        int[] ints = {-9, 14, 37, 102};

        boolean isExiste1 = IntStream.of(ints).anyMatch(n -> n == 14);
        boolean isExiste2 = Arrays.stream(ints).anyMatch(x -> x == 14);
        boolean isExiste3 = Arrays.binarySearch(tab, 1) >= 0;

        System.out.println(isExiste2);
        System.out.println(IntStream.of(ints).anyMatch(n -> n == 14));



        // Déterminer la température la plus proche de Zéro

        double[] ts = {7,-10,13,8,4,-7.2,-12,-3.7,3.5,-9.6,6.5,-1.7,-6.2,7};

        System.out.println(DoubleStream.of(ts).min());
        System.out.println(Arrays.stream(ts).min().getAsDouble());

        double d = 0;
        for(int i = 0; i < ts.length; i++) {
            double n = ts[i];
            if(d == 0 || Math.abs(d) > Math.abs(n)
                    || Math.abs(d) == Math.abs(n) && d < n) {
                d = n;
            }
        }
        System.out.println(d);

        /*  Exo Java 8 pour les Macdonalds aux USA */

        class McDonald {
            private double latitude, longitude ;
            private String name, address, city, state ;

            public McDonald(double latitude, double longitude, String name, String address, String city, String state) {
                this.latitude = latitude;
                this.longitude = longitude;
                this.name = name;
                this.address = address;
                this.city = city;
                this.state = state;
            }

            public McDonald() {
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            @Override
            public String toString() {
                return "McDonald{" +
                        "latitude=" + latitude +
                        ", longitude=" + longitude +
                        ", name='" + name + '\'' +
                        ", address='" + address + '\'' +
                        ", city='" + city + '\'' +
                        ", state='" + state + '\'' +
                        '}';
            }
        }

        URI u = URI.create("http://java.sun.com/");
        try (InputStream in = u.toURL().openStream()) {
            //Files.copy(in, path);
        }catch (IOException e){
            System.out.println(e);
        }

        try(Stream<String> lines = Files.lines(Paths.get("/Users/omarlo/Desktop/Java JEE/mcdonalds.csv"))){
            // code ...
            lines.map(line ->{
                String[] sg = line.split(",");
                System.out.println(Arrays.toString(sg));
                return sg;
            });
        }catch (Exception e){
            System.out.println(e);
        }

        try {
            //Stream<String> lines = Files.lines(Paths.get("/Users/omarlo/Desktop/mcdonalds.csv")) ;
            //Stream<String> lines = Files.lines(Paths.get("/Users/omarlo/Desktop", "test.txt")) ;
            Stream<String> lines = Files.lines(Paths.get("/Users/omarlo/Desktop/Java JEE", "mcdonalds.csv")) ;
            List<McDonald> mcdos = lines.map(line ->{

                // -149.95038,61.13712,"McDonalds-Anchorage,AK","3828 W Dimond Blvd, Anchorage,AK, (907) 248-0597"
                // -72.84817,41.27988,"McDonalds-Branford,CT","424 W Main St, Branford,CT, (203) 488-9353"

                String [] linesString = line.split(",");
                McDonald m = new McDonald();
                m.setLatitude(Double.parseDouble(linesString[0]));
                m.setLongitude(Double.parseDouble(linesString[1]));
                m.setName(linesString[2].substring(2)+ linesString[3].substring(0, linesString[3].length() - 1));
                //System.out.println(linesString[2].substring(2)+ linesString[3].substring(0, linesString[3].length() - 1));
                m.setAddress(linesString[4].substring(1));
                m.setCity(linesString[5].trim());
                m.setState(linesString[6].trim());
                if (m.getState().endsWith("\"")) {
                    m.setState(m.getState().substring(0, m.getState().length() - 1)) ;
                }
                if (m.getState().contains(" ")) {
                    m.setState(m.getState().substring(0, m.getState().indexOf(" "))) ;
                }
                if (m.getState().length() > 2) {
                    m.setState(linesString[7].trim()) ;
                }
                return m;
            }).collect(Collectors.toList());

            System.out.println("Nombre de mcdos: " + mcdos.size());

            int size = mcdos.stream()
                    .map(McDonald::getCity)
                    .collect(Collectors.toSet())
                    .size();

            System.out.println("Nombre de villes ayant un mcdo: " + size);

            Map.Entry<String, Long> entry = mcdos.stream()
                    .collect(Collectors.groupingBy(
                            McDonald::getCity,
                            Collectors.counting())
                    )
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get();

            System.out.println("La ville ayant plus de mcdos: " + entry);
            System.out.println("La ville ayant plus de mcdos: " + entry.getKey());


        } catch (Exception e){
            System.out.println(e);
        }


        //Stream.generate(Math::random).forEach(System.out::println);
        //Stream.generate(() -> "Java").forEach(System.out::println);

        //Stream.iterate(0, i -> i + 1).forEach(System.out::println);

        Path pathDocsUtilisateur = Paths.get(System.getProperty("user.home"));
        System.out.println(pathDocsUtilisateur);

        try {
            Files.list(pathDocsUtilisateur).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Collector<JRSUIConstants.Widget, ?, TreeSet<JRSUIConstants.Widget>> intoSet =
                Collector.of(TreeSet::new, TreeSet::add,
                        (left, right) -> { left.addAll(right); return left; });

        //System.out.println(intoSet);

    }


    private static int comparerEntierAscendant(int n1, int n2){
        return n2 - n1;
    }

    private static void  afficherListe(List<String> list, MonFormateur monFormateur){
        int i = 0;
        for (String element : list) {
            i++;
            System.out.println(monFormateur.formater("%3d %s%n", i, element));
        }
    }

    @FunctionalInterface
    interface MonFormateur {
        String formater(String format, Object... arguments);
    }

    @FunctionalInterface
    interface MyOperation {
        int operation(int a, int b);
    }

    private static int caluler(int a, int b, MyOperation myOperation){
        return myOperation.operation(a, b);
    }

    private static int calculer2(int a, int b, BiFunction<Integer, Integer, Integer> biFunction){
        return biFunction.apply(a, b);
    }

    private static int calculer3(int a, int b, ToIntBiFunction<Integer, Integer> toIntBiFunction){
        return toIntBiFunction.applyAsInt(a, b);
    }


    private static void afficherRes(String res, Consumer<String> consumer){
        consumer.accept(res);
    }

    private static void afficherResInt(Integer res, Consumer<Integer> consumer){
        consumer.accept(res);
    }
}
