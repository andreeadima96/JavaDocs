package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.demoobjects.Animal;
import ro.teamnet.zerotohero.reflection.demoobjects.Dog;
import ro.teamnet.zerotohero.reflection.demoobjects.MyClass;
import ro.teamnet.zerotohero.reflection.demoobjects.MyEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.lang.Class;
import java.lang.reflect.*;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */

public class ClassReflectionDemoMain {

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //TODO get the class for a String object, and print it
        System.out.println("sir".getClass());

        //TODO get the class of an Enum, and print it
       System.out.println(MyEnum.JAVA.getClass());

        //TODO get the class of a collection, and print it
        ArrayList l = new ArrayList();
        System.out.println(l.getClass());

        //TODO get the class of a primitive type, and print it

        System.out.println(int.class);

        //TODO get and print the class for a field of primitive type
        Field f = MyClass.class.getField("field");
        System.out.println(f.getDeclaringClass());

        //TODO get and print the class for a primitive type, using the wrapper class
        System.out.println(f.getType());

        //TODO get the class for a specified class name
        System.out.println(Class.forName("java.lang.Boolean"));

        //TODO get the superclass of a class, and print it
        System.out.println(Dog.class.getSuperclass());

        //TODO get the superclass of the superclass above, and print it
        System.out.println(Dog.class.getSuperclass().getSuperclass());

        //TODO get and print the declared classes within some other class

        //System.out.println(Animal.class.getDeclaredClasses().length);
        Class[] classes = Animal.class.getDeclaredClasses();
        for (int i = 0; i < classes.length; i++) {
            System.out.println("Class = " + classes[i].getName());
        }
        //TODO print the number of constructors of a class
        System.out.println(Animal.class.getConstructors().length);

        //TODO get and invoke a public constructor of a class
        Constructor constr = Animal.class.getConstructors()[0];
        constr.newInstance();

        //TODO get and print the class of one private field

        Field pf = MyClass.class.getDeclaredField("privateField");
        System.out.println(pf.getDeclaringClass());

		//TODO set and print the value of one private field for an object
        pf.setAccessible(true);
        MyClass obj = new MyClass();
        pf.set(obj,new Integer(4));

        System.out.println(pf.get(obj));

        //TODO get and print only the public fields class
        Field[] fields = MyClass.class.getFields();
        for(int i = 0; i < fields.length; i++ )
            System.out.println("Field " + fields[i].getName());

        //TODO get and invoke one public method of a class

        Method m = Animal.class.getMethod("eat");
        Animal a = new Animal();
        m.invoke(a);

        //TODO get and invoke one inherited method of a class
        Method im = Dog.class.getMethod("eat");
		Animal b = new Animal("Roco");
		im.invoke(b);

        //TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        int j;
        for(j =1; j<= 10; j++ ){
            a.play();
        }
        System.out.println(System.currentTimeMillis());
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
		//what do you observe?
        Method p = Dog.class.getMethod("play");
        for(j = 1; j<=100;j++)
            p.invoke(b);
        System.out.println(System.currentTimeMillis());
    }
}
