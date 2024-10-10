package task_package;

import java.sql.*;
import java.util.Scanner;

/**
 * PoisePMS is a project management system that interacts with a MySQL database
 * to manage projects and associated people.
 */
public class PoisePMS {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/PoisePMS";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Read projects");
                System.out.println("2. Read people");
                System.out.println("3. Add new project");
                System.out.println("4. Update project");
                System.out.println("5. Delete project");
                System.out.println("6. Finalise project");
                System.out.println("7. Find incomplete projects");
                System.out.println("8. Find past due projects");
                System.out.println("9. Find project by number or name");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> readProjects(connection);
                    case 2 -> readPeople(connection);
                    case 3 -> addNewProject(connection, scanner);
                    case 4 -> updateProject(connection, scanner);
                    case 5 -> deleteProject(connection, scanner);
                    case 6 -> finaliseProject(connection, scanner);
                    case 7 -> findIncompleteProjects(connection);
                    case 8 -> findPastDueProjects(connection);
                    case 9 -> findProject(connection, scanner);
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and prints all projects from the database.
     *
     * @param connection The database connection
     * @throws SQLException if a database access error occurs
     */
    private static void readProjects(Connection connection) throws SQLException {
        String query = "SELECT * FROM Project";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("Project ID: " + resultSet.getInt("project_id"));
                System.out.println("Project Name: " + resultSet.getString("project_name"));
                System.out.println("Deadline: " + resultSet.getDate("deadline"));
                System.out.println("Start Date: " + resultSet.getDate("start_date"));
                System.out.println("Completion Date: " + resultSet.getDate("completion_date"));
                System.out.println("Finalised: " + resultSet.getBoolean("finalised"));
                System.out.println("Engineer ID: " + resultSet.getInt("engineer_id"));
                System.out.println("Manager ID: " + resultSet.getInt("manager_id"));
                System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println();
            }
        }
    }

    /**
     * Reads and prints all people associated with projects from the database.
     *
     * @param connection The database connection
     * @throws SQLException if a database access error occurs
     */
    private static void readPeople(Connection connection) throws SQLException {
        readTable(connection, "StructuralEngineer");
        readTable(connection, "ProjectManager");
        readTable(connection, "Architect");
        readTable(connection, "Customer");
    }

    /**
     * Reads and prints all entries from the specified table.
     *
     * @param connection The database connection
     * @param tableName  The name of the table to read from
     * @throws SQLException if a database access error occurs
     */
    private static void readTable(Connection connection, String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println(tableName + " ID: " + resultSet.getInt(1));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Contact Details: " + resultSet.getString("contact_details"));
                System.out.println();
            }
        }
    }

    /**
     * Adds a new project to the database.
     *
     * @param connection The database connection
     * @param scanner    The scanner for user input
     * @throws SQLException if a database access error occurs
     */
    private static void addNewProject(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter project name: ");
        String projectName = scanner.nextLine();
        System.out.println("Enter deadline (YYYY-MM-DD): ");
        String deadline = scanner.nextLine();
        System.out.println("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.println("Enter engineer ID: ");
        int engineerId = scanner.nextInt();
        System.out.println("Enter manager ID: ");
        int managerId = scanner.nextInt();
        System.out.println("Enter architect ID: ");
        int architectId = scanner.nextInt();
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String query = "INSERT INTO Project (project_name, deadline, start_date, completion_date, finalised, engineer_id, manager_id, architect_id, customer_id) VALUES (?, ?, ?, NULL, FALSE, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, projectName);
            preparedStatement.setString(2, deadline);
            preparedStatement.setString(3, startDate);
            preparedStatement.setInt(4, engineerId);
            preparedStatement.setInt(5, managerId);
            preparedStatement.setInt(6, architectId);
            preparedStatement.setInt(7, customerId);
            preparedStatement.executeUpdate();
            System.out.println("Project added successfully.");
        }
    }

    /**
     * Updates an existing project in the database.
     *
     * @param connection The database connection
     * @param scanner    The scanner for user input
     * @throws SQLException if a database access error occurs
     */
    private static void updateProject(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter project ID to update: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Enter new project name: ");
        String projectName = scanner.nextLine();
        System.out.println("Enter new deadline (YYYY-MM-DD): ");
        String deadline = scanner.nextLine();
        System.out.println("Enter new start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.println("Enter new engineer ID: ");
        int engineerId = scanner.nextInt();
        System.out.println("Enter new manager ID: ");
        int managerId = scanner.nextInt();
        System.out.println("Enter new architect ID: ");
        int architectId = scanner.nextInt();
        System.out.println("Enter new customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String query = "UPDATE Project SET project_name = ?, deadline = ?, start_date = ?, engineer_id = ?, manager_id = ?, architect_id = ?, customer_id = ? WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, projectName);
            preparedStatement.setString(2, deadline);
            preparedStatement.setString(3, startDate);
            preparedStatement.setInt(4, engineerId);
            preparedStatement.setInt(5, managerId);
            preparedStatement.setInt(6, architectId);
            preparedStatement.setInt(7, customerId);
            preparedStatement.setInt(8, projectId);
            preparedStatement.executeUpdate();
            System.out.println("Project updated successfully.");
        }
    }

    /**
     * Deletes a project from the database.
     *
     * @param connection The database connection
     * @param scanner    The scanner for user input
     * @throws SQLException if a database access error occurs
     */
    private static void deleteProject(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter project ID to delete: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String query = "DELETE FROM Project WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectId);
            preparedStatement.executeUpdate();
            System.out.println("Project deleted successfully.");
        }
    }

    /**
     * Finalises a project by marking it as completed and setting the completion date.
     *
     * @param connection The database connection
     * @param scanner    The scanner for user input
     * @throws SQLException if a database access error occurs
     */
    private static void finaliseProject(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter project ID to finalise: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Enter completion date (YYYY-MM-DD): ");
        String completionDate = scanner.nextLine();

        String query = "UPDATE Project SET finalised = TRUE, completion_date = ? WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, completionDate);
            preparedStatement.setInt(2, projectId);
            preparedStatement.executeUpdate();
            System.out.println("Project finalised successfully.");
        }
    }

    /**
     * Finds and prints all projects that are not yet completed.
     *
     * @param connection The database connection
     * @throws SQLException if a database access error occurs
     */
    private static void findIncompleteProjects(Connection connection) throws SQLException {
        String query = "SELECT * FROM Project WHERE finalised = FALSE";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("Project ID: " + resultSet.getInt("project_id"));
                System.out.println("Project Name: " + resultSet.getString("project_name"));
                System.out.println("Deadline: " + resultSet.getDate("deadline"));
                System.out.println("Start Date: " + resultSet.getDate("start_date"));
                System.out.println("Completion Date: " + resultSet.getDate("completion_date"));
                System.out.println("Finalised: " + resultSet.getBoolean("finalised"));
                System.out.println("Engineer ID: " + resultSet.getInt("engineer_id"));
                System.out.println("Manager ID: " + resultSet.getInt("manager_id"));
                System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println();
            }
        }
    }

    /**
     * Finds and prints all projects that are past their due date.
     *
     * @param connection The database connection
     * @throws SQLException if a database access error occurs
     */
    private static void findPastDueProjects(Connection connection) throws SQLException {
        String query = "SELECT * FROM Project WHERE deadline < CURDATE() AND finalised = FALSE";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("Project ID: " + resultSet.getInt("project_id"));
                System.out.println("Project Name: " + resultSet.getString("project_name"));
                System.out.println("Deadline: " + resultSet.getDate("deadline"));
                System.out.println("Start Date: " + resultSet.getDate("start_date"));
                System.out.println("Completion Date: " + resultSet.getDate("completion_date"));
                System.out.println("Finalised: " + resultSet.getBoolean("finalised"));
                System.out.println("Engineer ID: " + resultSet.getInt("engineer_id"));
                System.out.println("Manager ID: " + resultSet.getInt("manager_id"));
                System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println();
            }
        }
    }

    /**
     * Finds and prints a project based on its project number or project name.
     *
     * @param connection The database connection
     * @param scanner    The scanner for user input
     * @throws SQLException if a database access error occurs
     */
    private static void findProject(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Search by: 1. Project Number 2. Project Name");
        int searchOption = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String query = "";
        if (searchOption == 1) {
            System.out.println("Enter project number: ");
            int projectId = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            query = "SELECT * FROM Project WHERE project_id = " + projectId;
        } else if (searchOption == 2) {
            System.out.println("Enter project name: ");
            String projectName = scanner.nextLine();
            query = "SELECT * FROM Project WHERE project_name LIKE '%" + projectName + "%'";
        } else {
            System.out.println("Invalid option. Returning to menu.");
            return;
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("Project ID: " + resultSet.getInt("project_id"));
                System.out.println("Project Name: " + resultSet.getString("project_name"));
                System.out.println("Deadline: " + resultSet.getDate("deadline"));
                System.out.println("Start Date: " + resultSet.getDate("start_date"));
                System.out.println("Completion Date: " + resultSet.getDate("completion_date"));
                System.out.println("Finalised: " + resultSet.getBoolean("finalised"));
                System.out.println("Engineer ID: " + resultSet.getInt("engineer_id"));
                System.out.println("Manager ID: " + resultSet.getInt("manager_id"));
                System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println();
            }
        }
    }
}

