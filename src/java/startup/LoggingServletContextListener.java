package startup;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;

@WebListener
public class LoggingServletContextListener implements ServletContextListener {
    private FileHandler fileHandler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        // Get the absolute path to the webapp root
        String appPath = ctx.getRealPath("/");
        if (appPath == null) {
            appPath = System.getProperty("user.dir");
        }

        File logFile = new File(appPath, "marketplace-app.log");
        try {
            // Create parent directory if needed
            File parent = logFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            // Set up JUL FileHandler
            fileHandler = new FileHandler(logFile.getAbsolutePath(), true);
            fileHandler.setFormatter(new SimpleFormatter());

            Logger root = Logger.getLogger("");
            // (Optional) remove console handler:
            // for (Handler h : root.getHandlers()) if (h instanceof ConsoleHandler) root.removeHandler(h);
            root.addHandler(fileHandler);

            root.info("=== Logging initialized to " + logFile.getAbsolutePath() + " ===");
        } catch (IOException e) {
            Logger.getLogger(LoggingServletContextListener.class.getName())
                  .log(Level.SEVERE, "Could not create log file at " + logFile.getAbsolutePath(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
}
