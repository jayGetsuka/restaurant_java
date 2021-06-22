
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Food {

    int id;
    String name;
    String type;
    String nutrient[];
    String spicy;
    int price;

    Food() {

    }

    Food(int id, String name, String type, String nutrient, String spicy, int price) {
        this.id = id;
        this.name = name;
        this.type = type;

        this.nutrient = nutrient.split(",");
        this.spicy = spicy;
        this.price = price;
    }

    Food(String name, String type, String nutrient, String spicy, int price) {
        int idCount = this.id++;
        this.id = idCount;
        this.name = name;
        this.type = type;
        this.nutrient = nutrient.split(",");
        this.spicy = spicy;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.type;
    }

    public String[] getNutrient() {
        return this.nutrient;
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getSpicy() {
        return this.spicy;
    }

    void addMenu() {
        try {
            File file = new File("food.dat");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("created file success. !!");
                    addMenu();
                } else {
                    System.out.println("create file error.");
                }

            } else {
                try {
                    OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream("food.dat", true), StandardCharsets.UTF_8);
                    String nutrients = "";
                    for (String nutrient : this.nutrient) {
                        nutrients += ";" + nutrient;
                    }

                    write.write(this.name + ";" + this.type + nutrients + ";" + this.spicy + ";" + this.price + System.lineSeparator());

                    //write.append("\n" + food.getName() + ";" + food.getCategory() + ";" + food.getNutrient() +";" +  food.getPrice());
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showMenu() {
        int id = 0;
        try {
            String[] lines = Files.readAllLines(new File("food.dat").toPath()).toArray(new String[0]);
            for (String line : lines) {
                String[] data = line.split(";");
                int index = data.length;
                ++id;
                System.out.println("\nid: " + id);
                System.out.println("name:" + data[0]);
                System.out.println("type: " + data[1]);
                System.out.print("nutrient: ");
                for (int i = 2; i < (index - 2); i++) {
                    if (!(i == (index - 3))) {
                        System.out.print(data[i] + ",");
                    } else {
                        System.out.print(data[i]);
                    }

                }
                System.out.println("");
                System.out.println("spicy: " + data[index - 2]);
                System.out.println("price: " + data[index - 1] + "\n");
                // etc...
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bAddFoodActionPerformed(Boolean check) {
        if (check) {
            System.out.println("Writen file success !!!");
        } else {
            System.out.println("Writen file Error !!!");
        }
    }

    public <E> void addNewFood(E inputFood) {
        try {
            File file = new File("food.dat");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("created file success. !!");
                    addNewFood(inputFood);
                } else {
                    System.out.println("create file error.");
                }

            } else {
                if (inputFood instanceof Food) {
                    Food food = (Food) inputFood;

                    try {
                        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream("food.dat", true), StandardCharsets.UTF_8);
                        String nutrients = "";
                    for (String nutrient : food.getNutrient()) {
                        nutrients += ";" + nutrient;
                    }

                    write.write(food.getName() + ";" + food.getCategory() + nutrients + ";" + food.getSpicy() + ";" + food.getPrice() + System.lineSeparator());
                    write.close();
                        bAddFoodActionPerformed(true);

                    } catch (IOException e) {
                        bAddFoodActionPerformed(false);
                        System.out.println("Error problem " + e);
                    }
                } else {
                    String food = (String) inputFood;
                    try {
                        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream("food.dat", true), StandardCharsets.UTF_8);
                        write.write(food + System.lineSeparator());
                        
                        write.close();
                        bAddFoodActionPerformed(true);
                    } catch (IOException e) {
                        bAddFoodActionPerformed(false);
                        e.printStackTrace();
                    }

                }

            }
        } catch (IOException e) {
            System.out.println("Read error!!");
        }
    }

    public static void main(String[] args) {
        /*
        Food food = new Food("ลูกชิ้น", "ย่าง", "โปรตีน", "ไม่เผ็ด", 20);
        food.addMenu();
       */
        Food food1 = new Food();
        //food1.addNewFood("ก๋วยจั๊บ;ก๋วยเตี๋ยว;โปรตีน;คาร์โบไฮเดรต;ไม่เผ็ด;45");
        food1.addNewFood(new Food("ก๋วยจั๊บ", "ก๋วยเตี๋ยว", "โปรตีน,คาร์โบไฮเดรต", "ไม่เผ็ด", 50));
        food1.showMenu();
    }
}
