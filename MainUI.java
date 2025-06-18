import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

class MyFrame extends JFrame 
{
    JLabel l0,l1,l3,l4;
    JPanel p1,p2;
    JTextField text1;
    JButton b1,b2;
    Border border= BorderFactory.createLineBorder(Color.gray, 1);//greyyy
    Border border1= BorderFactory.createLineBorder(new Color(180, 120, 240), 3);//LILAC border
    Border border2= BorderFactory.createLineBorder(new Color(180, 120, 240), 1);// for textfields

    Object[][] data;
    int[] index= {0};

    TimeChartPanel p3;
    SwapChartPanel p4;
    DefaultTableModel tableModel;

    @SuppressWarnings("unused")
    public MyFrame() 
    {
        setTitle("Sorting Algorithms Comparative Analysis");
        getContentPane().setBackground(new Color(230,190,255,100));
        setLayout(null);

        l0=new JLabel("Enter Array Size ");//1st heading
        l0.setForeground(Color.black);
        l0.setFont(new Font("Ariel",Font.BOLD,14));
        l0.setBounds(10,0,250,20);
        add(l0);

        p1=new JPanel();
        p1.setBackground(new Color(255,218,225,100));
        p1.setLayout(null);
        p1.setBounds(5,20,1525,80);
        p1.setBorder(border);
        add(p1);

        l1= new JLabel("Input here- ");
        l1.setForeground(new Color(145,45,220));
        l1.setFont(new Font("Ariel",Font.BOLD,15));
        l1.setBounds(30,15,200,40);
        p1.add(l1);

        text1= new JTextField();
        text1.setFont(new Font("Ariel",Font.PLAIN,18));
        text1.setBounds(120,20,300,40);
        text1.setBorder(border2);
        p1.add(text1);

        b1= new JButton("Run");
        b1.setFont(new Font("Ariel",Font.BOLD,14));
        b1.setBounds(450,20,80,40);
        b1.setBackground(new Color(145,95,220));
        b1.setForeground(Color.white);
        b1.setBorder(border);
        b1.setFocusable(false);
        p1.add(b1);

        b2= new JButton("Reset");
        b2.setFont(new Font("Ariel",Font.BOLD,14));
        b2.setBounds(550,20,80,40);
        b2.setBackground(new Color(145,95,220));
        b2.setForeground(Color.white);
        b2.setBorder(border);
        b2.setFocusable(false);
        b2.setEnabled(false);
        p1.add(b2);

        ////table panel////
        p2= new JPanel();
        p2.setBackground(new Color(230,190,255,0));
        p2.setLayout(new BorderLayout());
        p2.setBounds(5,130,1525,300);
        p2.setBorder(border1);
        add(p2);

        String[] columnNames= {"Algorithm","Time taken","Comparisons","Swaps","Time Complexity","Space Complexity","Stable","In-place"};
        tableModel= new DefaultTableModel(columnNames, 0) 
        {
            @Override public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        JTable table= new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Ariel",Font.PLAIN,15));
        table.setSelectionBackground(new Color(140,90,220));
        table.setSelectionForeground(Color.white);
        table.setGridColor(new Color(200,190,235));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(5,5));
        table.setAutoCreateRowSorter(true);

        JTableHeader header= table.getTableHeader();
        header.setOpaque(true);
        header.setBackground(new Color(200, 200, 200));// light grayyyy
        header.setForeground(Color.BLACK);
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane tableScroll= new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(920, 180));
        tableScroll.getViewport().setBackground(new Color(250, 245, 255));
        tableScroll.setBorder(border);
        p2.add(tableScroll,BorderLayout.CENTER);

        Timer timer= new Timer(250, null);//to slow the output
        timer.addActionListener(evt -> 
        {
            if (index[0]< data.length) 
            {
                tableModel.addRow(data[index[0]]);
                index[0]++;
            } 
            else 
            {
                timer.stop();
                int[] times = Arrays.stream(data).mapToInt(row -> Integer.valueOf(row[1].toString().replace(" ns",""))).toArray();
                int[] swaps = Arrays.stream(data).mapToInt(row -> Integer.valueOf(row[3].toString())).toArray();

                p3.setTimes(times); p3.repaint();
                p4.setSwaps(swaps); p4.repaint();

                b1.setEnabled(true);
                b2.setEnabled(true);
            }
        });

        b1.addActionListener(e -> 
{
    tableModel.setRowCount(0);
    index[0] = 0;

    int size;
    try {
        size = Integer.parseInt(text1.getText().trim());
        if (size <= 0) throw new NumberFormatException();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid positive integer.");
        return;
    }

    b1.setEnabled(false);
    b2.setEnabled(false);

    new Thread(() -> {
        int[] array = SortManager.generateRandomArray(size);
        java.util.List<SortResult> results = SortManager.runAllSorts(array);

        data = new Object[results.size()][8];
        for (int i = 0; i < results.size(); i++) {
            SortResult r = results.get(i);
            data[i] = new Object[] {
                r.algorithmName,
                (r.timeTakenNs / 1_000_000) + " ns",
                String.valueOf(r.comparisons),
                String.valueOf(r.swaps),
                r.timeComplexity,
                r.spaceComplexity,
                r.stable ? "Yes" : "No",
                r.inPlace ? "Yes" : "No"
            };
        }

        SwingUtilities.invokeLater(timer::start);
    }).start();
});


        b2.addActionListener(e -> 
        {
            text1.setText("");
            
            tableModel.setRowCount(0);
            p3.setTimes(new int[0]);
            p3.repaint();
            p4.setSwaps(new int[0]);
            p4.repaint();

            b1.setEnabled(true);
            b2.setEnabled(false);
        });

        // Time chart
        l3= new JLabel("Execution Time Comparison ");//2nd heading
        l3.setForeground(Color.black);
        l3.setFont(new Font("Ariel",Font.BOLD,14));
        l3.setBounds(15,440,250,20);
        add(l3);

        p3= new TimeChartPanel();
        p3.setBackground(new Color(230,190,255,255));///lilac
        p3.setBounds(10,460,750,320);
        p3.setBorder(border1);
        add(p3);

        // Swap chart
        l4 = new JLabel("Swap Count Comparison ");//3rd heading
        l4.setForeground(Color.black);
        l4.setFont(new Font("Ariel", Font.BOLD, 14));
        l4.setBounds(780,440,250,20);
        add(l4);

        p4 = new SwapChartPanel();
        p4.setBackground(new Color(230,190,255,255));///again
        p4.setLayout(new BorderLayout());
        p4.setBounds(775,460,750,320);
        p4.setBorder(border1);
        add(p4);

        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//window centered
        setVisible(true);
    }
}


class TimeChartPanel extends JPanel 
{
    private int[] times= new int[0];
    private final String[] algorithms= {"Bubble Sort","Selection Sort","Insertion Sort","Merge Sort","Quick Sort","Counting Sort","Heap Sort","Radix Sort"};
    private final Color[] colors= 
    {
        new Color(140,90,220,180),
        new Color(170,110,230,180),
        new Color(140,90,220,180),
        new Color(170,110,230,180),
        new Color(140,90,220,180),
        new Color(170,110,230,180),
        new Color(140,90,220,180),
        new Color(170,110,230,180)
    };

    public void setTimes(int[] times) 
    {
        this.times= times;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if(times.length== 0) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width= getWidth(), height= getHeight();
        int padding= 45, labelPadding= 60;
        int barWidth= (width-2*padding-labelPadding)/ times.length- 15;
        int maxTime= Arrays.stream(times).max().orElse(1);
        int yDivisions= 5;

        g2.setColor(new Color(220,210,235,150));
        for(int i=0;i<= yDivisions;i++) 
        {
            int y= height-padding-(i * (height - 2 * padding)/ yDivisions);
            g2.drawLine(padding + labelPadding, y, width- padding, y);
            g2.setColor(new Color(90, 90, 90));
            g2.drawString((maxTime * i / yDivisions) + " ns", padding + 5, y + 5);
            g2.setColor(new Color(220, 210, 235, 150));
        }

        for(int i=0;i<times.length;i++) 
        {
            int x= padding+labelPadding+i* (barWidth + 15);
            int barHeight= (int) ((double) times[i]/ maxTime* (height-2* padding));
            int y= height - padding - barHeight;

            g2.setColor(colors[i % colors.length]);
            g2.fillRoundRect(x, y, barWidth, barHeight, 12, 12);

            g2.setColor(new Color(70, 70, 70));
            g2.drawString(times[i] + " ns", x + (barWidth - g2.getFontMetrics().stringWidth(times[i] + " ns"))/ 2, y-8);
            g2.drawString(algorithms[i], x + (barWidth - g2.getFontMetrics().stringWidth(algorithms[i]))/ 2, height-padding + 25);
        }
    }
}

// Swap chart class
class SwapChartPanel extends JPanel 
{
    private int[] swaps = new int[0];
    private final String[] algorithms ={"Bubble Sort","Selection Sort","Insertion Sort","Merge Sort","Quick Sort","Counting Sort","Heap Sort","Radix Sort"};
    private final Color[] colors = 
    {
        new Color(240,90,90,180),
        new Color(255,140,140,180),
        new Color(240,90,90,180),
        new Color(255,140,140,180),
        new Color(240,90,90,180),
        new Color(255,140,140,180),
        new Color(240,90,90,180),
        new Color(255,140,140,180)
    };

    public void setSwaps(int[] swaps) 
    {
        this.swaps= swaps;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (swaps.length== 0) return;

        Graphics2D g2= (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width= getWidth(), height = getHeight();
        int padding= 45, labelPadding = 60;
        int barWidth= (width - 2 * padding - labelPadding) / swaps.length - 15;
        int maxSwaps= Arrays.stream(swaps).max().orElse(1);
        int yDivisions= 5;

        g2.setColor(new Color(255, 220, 220, 150));
        for(int i=0;i<=yDivisions;i++) 
        {
            int y = height-padding- (i*(height-2*padding)/ yDivisions);
            g2.drawLine(padding + labelPadding, y, width - padding, y);
            g2.setColor(new Color(110, 20, 20));
            g2.drawString((maxSwaps * i/ yDivisions) + " swaps", padding + 5, y + 5);
            g2.setColor(new Color(255, 220, 220, 150));
        }

        for(int i=0;i<swaps.length;i++) 
        {
            int x= padding+labelPadding+i* (barWidth + 15);
            int barHeight= (int)((double)swaps[i]/ maxSwaps *(height - 2 * padding));
            int y= height-padding- barHeight;

            g2.setColor(colors[i % colors.length]);
            g2.fillRoundRect(x, y, barWidth, barHeight, 12, 12);

            g2.setColor(new Color(80, 20, 20));
            g2.drawString(swaps[i] + "",x+(barWidth - g2.getFontMetrics().stringWidth(swaps[i] + ""))/ 2, y-8);
            g2.drawString(algorithms[i],x+(barWidth - g2.getFontMetrics().stringWidth(algorithms[i]))/ 2, height- padding + 25);
        }
    }
}

public class MainUI
{
    public static void main(String[] args) 
    {
        new MyFrame();
    }
}
