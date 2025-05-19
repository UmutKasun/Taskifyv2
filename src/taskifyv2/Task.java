package taskifyv2;

// Bu sınıf her bir görev nesnesini temsil ediyor
// Görevin başlığını (title) ve tamamlanma durumunu (isCompleted) tutuyorum


// Görevi temsil eder
public class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // formatForFile() → Görevi .txt dosyasına yazarken uygun formatta string’e çeviriyoruz
    // Örneğin: "Alışveriş yap;0" gibi

    public String formatForFile() {
        return title + ";" + (isCompleted ? "1" : "0");
    }
}
