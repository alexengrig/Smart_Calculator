class Cat {
    private static int counter = 0;
    private String name;
    private int age;

    public Cat(String name, int age) {
        if (++counter > 5) {
            System.out.println("You have too many cats");
        }
        this.name = name;
        this.age = age;
    }

    public static int getNumberOfCats() {
        return counter;
    }
}