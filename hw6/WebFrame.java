package CS151.HW6;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class WebFrame extends JFrame {
	
	public static void main(String[] args) {
    	if (args.length == 1) {
			 String file = args[0];
		        SwingUtilities.invokeLater(new Runnable() {
					public void run() {
		                new WebFrame(file);
		            }
		        });
		}
    	else System.out.println("The program requires the Input file to fetch the link URL to get the html files. "); 
    }

	public WebFrame(String file) {
        super("WebLoader");
        JFrame frame = this;
        FILENAME = file;
        if(!FILENAME.contains(".")) {
        	JOptionPane.showMessageDialog(frame, "A file of .txt type is required as an input to fetch URL links for getting html files.");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        model = new DefaultTableModel(new String[]{"url", "status"}, 0);
        getSiteLink();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        singleThreadButton = new JButton("Single Fetch");
        concurrentButton = new JButton("Multiple Fetch");
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);

        runningLabel = new JLabel("Running:");
        completedLabel = new JLabel("Completed:");
        elapsedLabel = new JLabel("Elapsed:");

        threadNumInputField = new JTextField("4", INPUT_FIELD_WIDTH);
        threadNumInputField.setMaximumSize(threadNumInputField.getPreferredSize());

        progressBar = new JProgressBar(0, model.getRowCount());

        addActionListeners();

        panel.add(scrollPane);
        panel.add(singleThreadButton);
        panel.add(concurrentButton);
        panel.add(threadNumInputField);
        panel.add(runningLabel);
        panel.add(completedLabel);
        panel.add(elapsedLabel);
        panel.add(progressBar);
        panel.add(stopButton);

        add(panel);

        pack();
        setVisible(true);
    }


    private static final int MULTIPLE_THREADS = 0;
    private static final int SINGLE_THREAD = 1;
    private WebLauncherThread launcher = null;

    private void addActionListeners() {
    	singleThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (launcher != null) launcher.interrupt();
                startLauncher(SINGLE_THREAD);
                startFetchAnimation();
            }
        });

        concurrentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (launcher != null) launcher.interrupt();
                startLauncher(MULTIPLE_THREADS);
                startFetchAnimation();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (launcher != null) launcher.interrupt();
                launcher = null;
                stopFetchAnimation();
            }
        });
    }

    private void startLauncher(int threadsNum) {
        if (threadsNum == MULTIPLE_THREADS) {
            try {
                threadsNum = Integer.parseInt(threadNumInputField.getText());
            } catch (NumberFormatException ignore) {
                threadsNum = SINGLE_THREAD;     // will run only 1 thread if user entered not int
            }
        }
        launcher = new WebLauncherThread(threadsNum, model.getRowCount(), this);
        launcher.start();
    }

   
    public void changeTableData(final String data, final int row) {
        model.setValueAt(data, row, 1);
    }

    public String getUrl(int row) {
         return (String) model.getValueAt(row, 0);
    }


    public void startFetchAnimation() {
        startTime = System.currentTimeMillis();
        singleThreadButton.setEnabled(false);
        concurrentButton.setEnabled(false);
        stopButton.setEnabled(true);
        progressBar.setValue(0);
        for (int i = 0; i < model.getRowCount(); i++)  //reset model
            model.setValueAt("", i, 1);

    }

    public void stopFetchAnimation() {
        singleThreadButton.setEnabled(true);
        concurrentButton.setEnabled(true);
        stopButton.setEnabled(false);

    }
    // the last method called from WebWorker
    public void releaseWorker(String result, int row) {
        changeTableData(result, row);
        threadsComplete.incrementAndGet();
        threadsRunning.decrementAndGet();
        workersCounter.release();
        UIProgressUpdate();
    }

    private void UIProgressUpdate() {
        final long elapsedTime = System.currentTimeMillis() - startTime;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runningLabel.setText("Running:" + threadsRunning);
                completedLabel.setText("Complete:" + threadsComplete);
                progressBar.setValue(threadsComplete.get());
                elapsedLabel.setText("Elapsed:" + elapsedTime);
            }
        });
    }

    public class WebLauncherThread extends Thread {
        private ArrayList<WebWorker> workers = new ArrayList<WebWorker>();
        private int urlNum;
        private WebFrame frame;

        public WebLauncherThread(int numOfThreads, int urlNum, WebFrame frame) {
            threadsRunning = new AtomicInteger(0);
            threadsComplete = new AtomicInteger(0);
            workersCounter = new Semaphore(numOfThreads);
            this.urlNum = urlNum;
            this.frame = frame;
        }
        
        private void initWorkers() throws InterruptedException {
            for (int i = 0; i < urlNum; i++) {
                if (isInterrupted()) throw new InterruptedException();
                WebWorker worker = new WebWorker(getUrl(i), i, frame);
                workers.add(worker);
            }
        }

        public void run() {
            threadsRunning.incrementAndGet();
            try {
                initWorkers();
                startWorkers();
            } catch (InterruptedException e) {
                interruptAllWorkers();
            }
            threadsRunning.decrementAndGet();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    stopFetchAnimation();
                }
            });
        }



        private void startWorkers() throws InterruptedException {
            for (WebWorker worker : workers) {
                if (isInterrupted()) throw new InterruptedException();
                workersCounter.acquire();
                threadsRunning.incrementAndGet();
                worker.start();
            }

            for (WebWorker worker : workers)
                worker.join();

        }

        private void interruptAllWorkers() {
            for (WebWorker w : workers) w.interrupt();
        }
    }

    private void getSiteLink() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));

            while (true) {
                String line = br.readLine();
                if (line == null) break;
                model.addRow(new String[]{line, ""});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    private static String FILENAME = null;
    private static int INPUT_FIELD_WIDTH = 4;
    private DefaultTableModel model;
    private JTable table;
    private JPanel panel;
    private JButton singleThreadButton;
    private JButton concurrentButton;
    private JButton stopButton;
    private JLabel completedLabel;
    private JLabel runningLabel;
    private JLabel elapsedLabel;
    private JTextField threadNumInputField;
    private JProgressBar progressBar;
    private long startTime;
    private AtomicInteger threadsRunning;
    private AtomicInteger threadsComplete;
    private Semaphore workersCounter;

}