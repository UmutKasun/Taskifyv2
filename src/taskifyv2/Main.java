package taskifyv2;

// Programın çalıştığı ana sınıf burası
// Kullanıcıyla etkileşim burada oluyor
// Scanner ile menü üzerinden seçim yaptırıyoruz

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n1. Add task");
            System.out.println("2. List tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Exit");
            System.out.println("5. Delete task");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // 1 → Görev ekler
            // 2 → Görevleri listeler
            // 3 → Görev tamamlama
            // 4 → Programdan çıkış
            // 5 → Görev silme

            switch (choice) {   // Kullanıcının seçimine göre ilgili işlemi çağırıyoruz
                                // Her işlemden sonra liste veya dosya güncellenmiş oluyor
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    manager.addTask(title);
                    break;
                case 2:
                    manager.listTasks();
                    break;
                case 3:
                    System.out.print("Enter task number to complete: ");
                    int completeIndex = scanner.nextInt() - 1;
                    manager.completeTask(completeIndex);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                case 5:
                    manager.listTasks();
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    manager.deleteTask(deleteIndex);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
