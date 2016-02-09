import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arthur Shamsiev on 09.02.16.
 * Using IntelliJ IDEA
 * Project - wild
 */
public class FileWatcher {

    private final List<String> filesList = initializeFoundFilesList();

    private CopyOnWriteArrayList<String> initializeFoundFilesList() {
        return new CopyOnWriteArrayList<>();
    }

    private final static File TARGET_DIR = new File(System.getProperty("user.home") + "/target");

    public void start() {

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(
                this::findNewFiles,
                0, 1, TimeUnit.SECONDS
        );
    }

    private void findNewFiles() {
        String[] fileNames = TARGET_DIR.list();
        for (String fileName : fileNames) {
            if (!filesList.contains(fileName)) {
                System.out.println("Adding " + fileName);
                filesList.add(fileName);
            }
        }
    }
}
