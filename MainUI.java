import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*; 
public class MainUI
{
    public static void main(String[] args) 
{
        SwingUtilities.invokeLater(() -> new MainUI().createAndShowGUI());
    }

    private void createAndShowGUI()
    {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}
        JFrame frame = new JFrame("Sorting Algorithm Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(980, 700);
        frame.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(245, 240, 255));
        inputPanel.setBorder(new RoundedBorder(new Color(145, 95, 220), 1, 10));
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        JLabel inputLabel = new JLabel("Enter number of elements:");
        
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        inputLabel.setForeground(new Color(90, 60, 160));
        JTextField inputField = new JTextField(8);
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        inputField.setBorder(new LineBorder(new Color(180, 140, 230), 1, true));
        JButton runButton = new JButton("Run");
        JButton resetButton = new JButton("Reset");
        runButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        runButton.setBackground(new Color(145, 95, 220));
        runButton.setForeground(Color.white);
        resetButton.setBackground(new Color(145, 95, 220));
        resetButton.setForeground(Color.white);
        runButton.setFocusPainted(false);
        resetButton.setFocusPainted(false);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(runButton);
        inputPanel.add(resetButton);
        String[] columnNames = {"Algorithm", "Time", "Comparisons", "Swaps", "Time Complexity", "Space Complexity", "Stable", "In-place"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setSelectionBackground(new Color(140, 90, 230));
        table.setSelectionForeground(Color.white);
        table.setGridColor(new Color(200, 190, 235));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setAutoCreateRowSorter(true);
        JTableHeader header = table.getTableHeader();
        header.setOpaque(true);
        header.setBackground(new Color(200, 200, 200));
        header.setForeground(Color.BLACK);
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 1; i <= 3; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        for (int i = 6; i <= 7; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new CompoundBorder(
                new RoundedBorder(new Color(140, 90, 230), 2, 15),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        tablePanel.setBackground(new Color(245, 240, 255));
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(920, 180));
        tableScroll.getViewport().setBackground(new Color(250, 245, 255));
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(tableScroll, BorderLayout.CENTER);
        ChartPanel timeChartPanel = new ChartPanel();
        timeChartPanel.setPreferredSize(new Dimension(440, 280));
        JPanel timeChartContainer = createChartContainer("Execution Time Comparison", timeChartPanel);
        SwapChartPanel swapChartPanel = new SwapChartPanel();
        swapChartPanel.setPreferredSize(new Dimension(440, 280));
        JPanel swapChartContainer = createChartContainer("Swap Count Comparison", swapChartPanel);
        JPanel chartsContainer = new JPanel();
        chartsContainer.setLayout(new BoxLayout(chartsContainer, BoxLayout.X_AXIS));
        chartsContainer.setBackground(new Color(245, 240, 255));
        chartsContainer.add(timeChartContainer);
        chartsContainer.add(Box.createRigidArea(new Dimension(20, 0)));
        chartsContainer.add(swapChartContainer);
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(new Color(245, 240, 255));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(inputPanel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(tablePanel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        chartsContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(chartsContainer);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        JScrollPane mainScrollPane = new JScrollPane(mainContentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(mainScrollPane, BorderLayout.CENTER);
        runButton.addActionListener(e ->
        {
            tableModel.setRowCount(0);
            String inputText = inputField.getText().trim();
            int size;
            try
            {
                size = Integer.parseInt(inputText);
                if (size <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid positive integer for size.");
                return;
            }
            runButton.setEnabled(false);
            resetButton.setEnabled(false);
            new Thread(() -> {
                int[] A = SortManager.generateRandomArray(size);
                java.util.List<SortResult> results = SortManager.runAllSorts(A);
                Object[][] data = new Object[results.size()][8];
                int[] times = new int[results.size()];
                int[] swaps = new int[results.size()];
                for (int i = 0; i < results.size(); i++)
                {
                    SortResult r = results.get(i);
                    data[i][0] = r.algorithmName;
                    data[i][1] = (r.timeTakenNs / 1_000_000) + " ns";
                    data[i][2] = r.comparisons;
                    data[i][3] = r.swaps;
                    data[i][4] = r.timeComplexity;
                    data[i][5] = r.spaceComplexity;
                    data[i][6] = r.stable ? "Yes" : "No";
                    data[i][7] = r.inPlace ? "Yes" : "No";
                    times[i] = (int) (r.timeTakenNs / 1_000_000);
                    swaps[i] = r.swaps;
                }
                SwingUtilities.invokeLater(() -> {
                    for (Object[] row : data)
                        tableModel.addRow(row);
                    timeChartPanel.setTimes(times);
                    timeChartPanel.repaint();
                    swapChartPanel.setSwaps(swaps);
                    swapChartPanel.repaint();
                    runButton.setEnabled(true);
                    resetButton.setEnabled(true);
                });
            }).start();
        });
        resetButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            timeChartPanel.setTimes(new int[8]);
            timeChartPanel.repaint();
            swapChartPanel.setSwaps(new int[8]);
            swapChartPanel.repaint();
            inputField.setText("");
        });
        frame.setVisible(true);
    }
    private JPanel createChartContainer(String title, JPanel chart)
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new CompoundBorder(
                new TitledBorder(new LineBorder(new Color(150, 100, 230), 1, true), title),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        panel.setBackground(new Color(245, 240, 255));
        panel.add(chart, BorderLayout.CENTER);
        return panel;
    }
    static class RoundedBorder extends AbstractBorder
    {
        private final Color color;
        private final int thickness;
        private final int radius;
        public RoundedBorder(Color color, int thickness, int radius)
        {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
        {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x + thickness / 2, y + thickness / 2,
                    width - thickness, height - thickness, radius, radius);
            g2.dispose();
        }
    }

    static class ChartPanel extends JPanel
    {
        private final String[] algorithns = {"Bubble Sort", "Selection Sort", "Merge Sort", "Quick Sort","Insertion Sort", "Heap Sort", "Counting Sort", "Radix Sort"};
        private int[] times = new int[algorithns.length];

        public void setTimes(int[] times)
        {
            if (times.length == algorithns.length)
                this.times = times;
        }
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int width = getWidth() - 40;
            int height = getHeight() - 60;
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int maxTime = Arrays.stream(times).max().orElse(1);
            if (maxTime == 0) maxTime = 1;
            int barWidth = width / times.length - 12;
            int xStart = 30;
            int yStart = height + 20;
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.setColor(new Color(140, 90, 230));
            for (int i = 0; i < times.length; i++)
            {
                int barHeight = (int) ((times[i] / (double) maxTime) * (height - 40));
                int x = xStart + i * (barWidth + 12);
                int y = yStart - barHeight;
                g2.fillRect(x, y, barWidth, barHeight);
                g2.setColor(Color.black);
                String timeStr = times[i] + " ns";
                int strWidth = g2.getFontMetrics().stringWidth(timeStr);
                g2.drawString(timeStr, x + (barWidth - strWidth) / 2, y - 6);
                String label = algorithns[i];
                int labelWidth = g2.getFontMetrics().stringWidth(label);
                g2.drawString(label, x + (barWidth - labelWidth) / 2, yStart + 15);
                g2.setColor(new Color(140, 90, 230));
            }
        }
    }

    static class SwapChartPanel extends JPanel
    {
        private final String[] algorithns = {"Bubble Sort", "Selection Sort", "Merge Sort", "Quick Sort","Insertion Sort", "Heap Sort", "Counting Sort", "Radix Sort"};
        private int[] swaps = new int[algorithns.length];
        public void setSwaps(int[] swaps)
        {
            if (swaps.length == algorithns.length)
                this.swaps = swaps;
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int width = getWidth() - 40;
            int height = getHeight() - 60;
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int maxSwaps = Arrays.stream(swaps).max().orElse(1);
            if (maxSwaps == 0) maxSwaps = 1;
            int barWidth = width / swaps.length - 12;
            int xStart = 30;
            int yStart = height + 20;
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.setColor(new Color(140, 90, 230));
            for (int i = 0; i < swaps.length; i++)
            {
                int barHeight = (int) ((swaps[i] / (double) maxSwaps) * (height - 40));
                int x = xStart + i * (barWidth + 12);
                int y = yStart - barHeight;
                g2.fillRect(x, y, barWidth, barHeight);
                g2.setColor(Color.black);
                String swapStr = String.valueOf(swaps[i]);
                int strWidth = g2.getFontMetrics().stringWidth(swapStr);
                g2.drawString(swapStr, x + (barWidth - strWidth) / 2, y - 6);
                String label = algorithns[i];
                int labelWidth = g2.getFontMetrics().stringWidth(label);
                g2.drawString(label, x + (barWidth - labelWidth) / 2, yStart + 15);
                g2.setColor(new Color(140, 90, 230));
            }
        }
    }
}