import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Dog {
	int dog_id;
    String dog_name;
	int dog_age;
	int dog_weigth;
	String gender;
	String breed;
	String color;
	String dog_owner;

    static int count = 0;

    static final String url = "jdbc:mysql://localhost:3306/dogs_db";
    static final String user = "root";
    static final String password = "qaz1234567";

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet; 

	Dog(String dog_name, int dog_age, int dog_weigth, String gender, 
		String breed, String color, String dog_owner) {
		this.dog_name = dog_name;
		this.dog_age = dog_age;		
		this.dog_weigth = dog_weigth;		
		this.gender = gender;
		this.breed = breed;
		this.color = color;
		this.dog_owner = dog_owner;
	}

    void remove() {
        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            statement = connection.createStatement();
        
            dog_id--;
            statement.executeUpdate("delete from dogs where dog_name = " + "'" + dog_name + "'");
                       
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } 
    } 
    
    static void clearTable() {   
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate("delete from dogs");
            count = 0;           

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } 
    }

	void save() {
        if (dog_id == 0) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                statement = connection.createStatement();
                count++;
                dog_id = count;
                statement.executeUpdate(
                "insert into dogs values(" + dog_id + ",'" + dog_name + "'," + dog_age + ", " + dog_weigth + ",'" + gender +
                "', '"+ breed + "', '" + color + "', '" + dog_owner + "')");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        } else {
            try {
                connection = DriverManager.getConnection(url, user, password);
                statement = connection.createStatement();
                statement.executeUpdate(
                "update dogs set dog_age = " + dog_age + ",dog_weigth = " + dog_weigth + 
                ", dog_owner = '" + dog_owner +  "' where dog_id = " + dog_id);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
	}

    static void getById(int id) {   
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from dogs where dog_id = " + id);

            while (resultSet.next()) {
                String name = resultSet.getString("dog_name");
                int dog_id = resultSet.getInt("dog_id");
                int age = resultSet.getInt("dog_age");
                String breed = resultSet.getString("breed");

                System.out.println(dog_id + " " + name + " " + age + " " + breed);
            }
            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    } 
}

class DogsToMySQL {
	public static void main(String[] args) {
		Dog.clearTable();

        Dog bobik = new Dog("Bobik", 3, 3, "Male", "Husky", "Black", "Petrov");
        System.out.println("Bobik id - " + bobik.dog_id);
   
		bobik.save();
        System.out.println("Bobik id - " + bobik.dog_id);
  
		Dog bim = new Dog("Bim", 4, 2, "Male", "Husky", "While", "Ivanov");
		bim.save();

        bim.dog_age = 7;

        bim.save();

        bim.dog_owner = "Sidorov";
        bim.save();

        Dog.getById(1);
        Dog.getById(2); 
    } 
}
	
