import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws IOException {

        //-----------------------------------------------------//FUNCTIONS//-----------------------------------------------
        System.out.println("---------------------------------------------FUNCTION CHAINING----------------------------------------------------");
        //Function wich receive String and return integer
        Function<String,Integer>  wordCounter = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.split(" ").length;
            }
        };

        //Function wich receive Integer and return String
        Function<Object,String> textNumber = new Function<Object, String>() {
            @Override
            public String apply(Object integer) {
                switch ((Integer) integer)
                {
                    case 1: return "one";
                    case 2: return "two";
                    case 3: return "three";
                    default: return "unknown";
                }
            }
        };

        Function<String,String> toUpperCase = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return o.toUpperCase();
            }
        };

        // String s = textNumber.andThen(toUpperCase).apply("qqw qww");

        Integer res =wordCounter.apply("word first then ...");

        String res2 = textNumber.apply(1);

        System.out.println("function WORD COUNTER "+res);

        System.out.println("function TEXT TO  NUMBER "+res2);

        //System.out.println("function UPPERCASE "+s);


        //CHAINING FUNCTION

        //textNumber use the result of wordCounter to create its own result
        String chainingres1 = wordCounter.andThen(textNumber).apply("Lorem ipsum");
        System.out.println("chaining function 1 "+chainingres1);

        //wordDetector use the result of textNumber to create its own result
        Integer chainingres2 = wordCounter.compose(textNumber).apply(1);
        System.out.println("chaining function 2 "+chainingres2);

        //-----------------------------------------------------//PREDICATE CLASSES//-----------------------------------------------
        //Get input and ouput boolean
        Predicate<String> sizeChecker = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() < 50;
            }
        };

        Predicate<String> wordChecker = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("ipsum");
            }
        };

        boolean b = sizeChecker.negate().and(wordChecker).test("Lorem chaining function chainingres2 ction chainingres2ction chainingres2ction chainingres2");
        System.out.println("Predicate "+b);


        //-----------------------------------------------------//SUPPLIER/CONSUMER//-----------------------------------------------
        System.out.println("---------------------------------------------SUPPLIER/CONSUMER/----------------------------------------------------");

        //FUNCTIONAL INTERFACES TO WHICH WE CAN ASSIGN LAMBDAS EXPRESSION TO PROCESS DATA

        /**
         *@ Doesnt get input but create an output
         * */
        Supplier<Calendar> calendarSupplier = new Supplier<Calendar>() {
            @Override
            public Calendar get() {
                return Calendar.getInstance();
            }
        };

        /**
         *@ get input but doesnt create an output
         * */
       Consumer<Date> dateIncrement = new Consumer<Date>() {
           @Override
           public void accept(Date date) {
               Calendar c = Calendar.getInstance();
               c.setTime(date);
               c.add(Calendar.DAY_OF_YEAR,1);
               System.out.println("Tomorrow "+c.getTime());
           }
       };

       Consumer<Date> dayPrinter = new Consumer<Date>() {
           @Override
           public void accept(Date date) {

               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy EEEE");
               System.out.println("Today "+sdf.format(date));

           }
       };

        dateIncrement.andThen(dayPrinter).accept(new Date());



        //-----------------------------------------------------//LAMBDAS//-----------------------------------------------
        System.out.println("---------------------------------------------LAMBDAS----------------------------------------------------");

        /**
         *
         * @USED TO SHORTEN THE CODE AND TO SIMPLIFY THE USAGE OF FUNCTIONS, PREDICATE,CONSUMER,SUPPLIER INTERFACES
         * @ALL INTERFACES METHODS MAY BE CHANGED WITH "->"
         * @JVM WILL UNDERSTAND WHICH METHOD TO USE
         * @"->" detect the proper interface and invoke the proper method
         *
         * */

        //JVM understand that it should use a predicate

        Predicate<String> wordCheckerLambda = s -> s.contains("Download");

        boolean result = wordCheckerLambda.test("Successfully download");

        System.out.println("lambda 1 =>"+result);

        //Shorten function
        Function<String, Integer> wordCounterLambda = (String s) -> {return  s.split(" ").length;};
        //OR
        Function<String, Integer> wordCounterLambda2 = s -> {return  s.split(" ").length;};



        Integer rslt = wordCounterLambda2.apply("ERWWW WDDEE");

        System.out.println("lambda 2 =>"+rslt);



        //Shorten Supplier
        Supplier<Calendar> supplierLambda = () -> Calendar.getInstance();

        Calendar c = supplierLambda.get();

        System.out.println("lambda 3 =>"+c.getTime());

        //-----------------------------------------------------//(::) OPERATOR//-----------------------------------------------
        System.out.println("---------------------------------------------::----------------------------------------------------");
        //IS USED TO EXECUTE CLASS'S METHOD

        Supplier<MyDate> supplier = MyDate::new;

        MyDate myDate = supplier.get();


        Function<Date, String> dayName = myDate :: getCurrentDayName;

        System.out.println("FUNCTION=> "+dayName.apply(new Date()));


        Supplier<String> supplierDayName = myDate :: getCurrentDayName;

        System.out.println("SUPPLIER=> "+supplierDayName.get());

        Predicate<Date> predicateIsWe = myDate :: isWeekEndDay;

        System.out.println("PREDICATE=> "+predicateIsWe.test(myDate.getTomorrowDate(new Date())));


        //-----------------------------------------------------//OPTIONAL CLASS AND CHAINING FUNCTION//-----------------------------------------------
        System.out.println("---------------------------------------------OPTIONAL CLASS AND CHAINING FUNCTION----------------------------------------------------");

        //By that function we are trying to return the second word of a string
        Function<String,String> getSecondWord = (String s) -> {return s.split(" ").length > 1 ? s.split(" ")[1] : null ;};

        //By that function we are trying to count letters of a string
        Function<String,Integer> getCountLetter = s -> s.length();

        //Output of the first function become input of the second
        Integer count = getSecondWord.andThen(getCountLetter).apply("Elimane Fofana");

        System.out.println("COUNT "+count);

        //That optional class is a way to avoid nullpointer exception
        Optional<String> myOptional = Optional.ofNullable(getSecondWord.apply("Elimane out"));

        boolean present = myOptional.isPresent();

//        String optionalRes = myOptional.isPresent() ? "second word is : "+myOptional.get() : "No value";

        if(myOptional.isPresent()) {
            myOptional.ifPresent(System.out::println);
        }else {
           String opres = myOptional.orElse("No value");
           System.out.println("OPTIONAL "+opres);
        }
        //ifpresent method use a consumer as argument
        Optional.ofNullable(getSecondWord.apply("Eli Mane")).ifPresent(System.out::println);
        Optional.ofNullable(getSecondWord.apply("Eli Mane")).map(getCountLetter).ifPresent(System.out::println);

        //-----------------------------------------------------//STREAMS//-----------------------------------------------
        System.out.println("---------------------------------------------STREAMS----------------------------------------------------");
        /**
         *
         *  STREAM IS A STRUCTURE FOR PROCESSING A COLLECTION IN FUNCTIONAL STYLE,
         *  ORIGINAL COLLECTION IS NOT MODIFIED,
         *  STREAM MAY BE PROCESSED ONLY ONCE,
         *  AFTER GETTING AN OUTPUT YOU CAN'T USE IT AGAIN
         *
         **/

        String path = "/Users/Elimane/Documents/File/file.txt";
        File file = new File(path);
//        Stream.iterate(0, n->n+2)
//                .peek(num -> System.out.println("Peeked at:"+num))
//                .limit(5)
//                .forEach(System.out::println);

//        List<String> errors = Files.lines(Paths.get(path))
//                .filter(l -> l.startsWith("ERROR"))
//                .limit(10)
//                .collect(toList());

        List<String> errors = Files.lines(Paths.get(path))
                .filter(l -> l.startsWith("ERROR"))
                .limit(10)
                .collect(toList());


        Optional.ofNullable(errors).ifPresent(System.out::println);

        System.out.println("LINES "+Tests.getErrorLines(path));

        System.out.println("---------------------------------------------CONVERT COLLECTION INTO STREAMS----------------------------------------------------");

        Set<Integer> list = new HashSet<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Conversion into stream
        Stream<Integer> stream = list.stream();

        //To display number list in ascending order
        stream.sorted((first,second) -> second - first).forEach(System.out::println);

        //To display number list in descending order
        //stream.sorted((f,s) -> f - s).forEach(System.out::println);
    }
    }

