package taskifyv2;

import java.io.*;
import java.util.ArrayList;

// TaskManager sınıfı görev listesini yönetir
// Tüm görevleri ArrayList içinde tutuyorum
// Yeni görev eklendiğinde veya değişiklik olduğunda otomatik olarak .txt dosyasına yazılıyor


public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final String filePath = "tasks.txt";

    public TaskManager() {
        readFromFile();
    }

    // Yeni görev eklenir ve hemen dosyaya yazılır
    public void addTask(String title) {
        tasks.add(new Task(title, false));
        writeToFile();
    }

    // Görevleri sıralar, yapılmışsa [✓] ekler, yapılmamışsa [ ] ekler
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ". " + t.getTitle() + (t.isCompleted() ? " [✓]" : " [ ]"));
        }
    }

    // Verilen index’teki görevi tamamlanmış olarak işaretler
    // Ardından .txt dosyasını günceller
    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
            writeToFile();
        }
    }

    // Listeden seçilen görevi siler
    // Daha sonra .txt dosyasına yeniden yazar
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            writeToFile();
        }
    }

    // Görev listesini tek tek satır satır yazmak için kullanılır
    // Her görev “başlık;durum” formatında yazılır
    private void writeToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                writer.println(t.formatForFile());
            }
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }

    // Program ilk açıldığında .txt dosyasını satır satır okur
    // Okunan satırlardan görev nesneleri oluşturulup listeye eklenir
    private void readFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String title = parts[0];
                    boolean completed = parts[1].equals("1");
                    tasks.add(new Task(title, completed));
                }
            }
        } catch (IOException e) {
            System.out.println("Dosyadan okuma hatası: " + e.getMessage());
        }
    }
}
