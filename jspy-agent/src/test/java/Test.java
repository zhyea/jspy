public class Test {


    //http://blog.csdn.net/wwwxxdddx/article/details/51878772
    //http://blog.csdn.net/wwwxxdddx/article/details/45064219


    public static void main(String[] args) {
        Person robin = new Person("robin", 16);
        System.out.println("########################################");
        robin.act();
        robin.think();
        System.out.println("########################################");
    }


    private static class Person {

        String name;

        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void act() {
            System.out.println(name + " is running.");
        }

        public void think() {
            System.out.println(name + " is thinking");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
