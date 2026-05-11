package puzzleGame.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    // 自定义颜色方案（与登录/注册界面保持一致）
    private static final Color BG_TOP = new Color(25, 25, 112);
    private static final Color BG_BOTTOM = new Color(75, 0, 130);
    private static final Color ACCENT = new Color(65, 105, 225);
    private static final Color ACCENT_HOVER = new Color(100, 149, 237);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color MENU_BG = new Color(30, 30, 100);
    private static final Color MENU_HOVER = new Color(50, 50, 140);

    public GameJFrame() {
        //游戏界面，窗体
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenubar();

        //初始化数据
        initDare();

        //初始化图片
        initImage();

        //让界面显示
        this.setVisible(true);
        //创建一个图片ImageIcon的对象

    }
    //创建二维数组
    int[][] matrix = new int[4][4];
    int x =0;
    int y =0;
    int step = 0;

    JMenuItem replayItem = new JMenuItem("重新开始");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    //定义变量展示路径
    String path ="images/lusi";
    //D:\IDEA\idea_learning\javaLearning02\images\lusi\all.png

    //定义一个二位数组
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };


    //打乱图像
    private void initDare() {
        //0~15或1~16都可以 0空，16空
        int[] arr= {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random rand=new Random();
        for(int i=arr.length-1;i>=0;i--){
            int j=rand.nextInt(arr.length);
            int temp=arr[j];
            arr[j]=arr[i];
            arr[i]=temp;
        }

        int index = 0;
        for(int i=0;i< arr.length;i++){
            if(arr[i]==0){
               x=i/4;
               y=i%4;
            }
            matrix[i/4][i%4] = arr[i];
      }
//       for (int i=0;i<4;i++){
//           for (int j=0;j<4;j++){
//               System.out.print(matrix[i][j]+" ");
//           }
//           System.out.println();
//       }
    }

    //初始化图片
    private void initImage() {
       //清空已有图片
       this.getContentPane().removeAll();

       if(victory()){
           //显示胜利
           JLabel winjLabel = new JLabel(new ImageIcon("images/win.png"));
           winjLabel.setBounds(150,150,400,400);
           this.add(winjLabel);

       }

       //美化步数显示
       JLabel stepCount = new JLabel("  步数: " + step) {
           @Override
           protected void paintComponent(Graphics g) {
               Graphics2D g2 = (Graphics2D) g.create();
               g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               // 绘制半透明圆角背景
               g2.setColor(new Color(0, 0, 0, 120));
               g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
               g2.dispose();
               super.paintComponent(g);
           }
       };
       stepCount.setFont(new Font("微软雅黑", Font.BOLD, 22));
       stepCount.setForeground(new Color(255, 215, 0)); // 金色文字
       stepCount.setBounds(20, 5, 160, 35);
       stepCount.setOpaque(false);
       this.add(stepCount);

         // 定义常量，便于调整
         int tileSize = 150;          // 每个小图片显示尺寸
         int gap = 5;                // 图片间距
         int rows = 4, cols = 4;      // 4x4 网格

         // 计算起始坐标（假设容器大小足够显示拼图，可自行替换为容器宽高）
         int totalWidth = cols * (tileSize + gap) - gap;
         int totalHeight = rows * (tileSize + gap) - gap;
         int startX = (getWidth() - totalWidth) / 2;   // 水平居中
         int startY = (getHeight() - totalHeight) / 2; // 垂直居中

         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                //获取当前要加载的图片序号
                int number = matrix[i][j];
                if(number == 0){
                    continue;
                }
                //创建一个JLabel的对象
                ImageIcon originalIcon = new ImageIcon(path+"\\part_"+number+".png");
                Image scaledImage = originalIcon.getImage().getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
                JLabel jLabel = new JLabel(new ImageIcon(scaledImage));
                //指定图片位置
                setLayout(null);

                // 根据行列计算坐标
                int x = startX + j * (tileSize + gap);
                int y = startY + i * (tileSize + gap);

                jLabel.setBounds(x, y, tileSize, tileSize);
                //给图片添加边框
                jLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 1));
                //把管理容器放到菜单中

                //添加背景图
                //ImageIcon bg = new ImageIcon("D:\\IDEA\\idea_learning\\javaLearning02\\images\\lusi\\background.png");
                //JLabel background = new JLabel(bg);
                //background.setBounds(0, 0,1400,1400);
                //this.add(background);
                this.getContentPane().add(jLabel);
            }
        }
        this.getContentPane().repaint();

    }

    private void initJMenubar() {
        //初始化菜单
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, MENU_BG, getWidth(), 0, BG_TOP);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        jMenuBar.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));

        //创建菜单上面的两个选择对象
        JMenu functionJMenu = createStyledMenu("功能");
        JMenu aboutJMenu = createStyledMenu("关于我们");
        //创建选项下面的条目对象


        //把每一个选项添加到菜单里
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        // 美化菜单项
        styleMenuItem(replayItem);
        styleMenuItem(reLoginItem);
        styleMenuItem(closeItem);
        styleMenuItem(accountItem);

        //添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //给界面设置菜单
        this.setJMenuBar(jMenuBar);

    }

    private JMenu createStyledMenu(String text) {
        JMenu menu = new JMenu(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed() || getModel().isSelected()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(MENU_HOVER);
                    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 8, 8);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        menu.setOpaque(false);
        menu.setForeground(TEXT_COLOR);
        menu.setFont(new Font("微软雅黑", Font.BOLD, 14));
        menu.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));
        // 美化下拉弹出菜单
        menu.getPopupMenu().setBorder(BorderFactory.createLineBorder(new Color(100, 100, 180), 1));
        menu.getPopupMenu().setBackground(MENU_BG);
        return menu;
    }

    private void styleMenuItem(JMenuItem item) {
        item.setOpaque(true);
        item.setBackground(MENU_BG);
        item.setForeground(TEXT_COLOR);
        item.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        item.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // 悬停效果
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                item.setBackground(MENU_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                item.setBackground(MENU_BG);
            }
        });
    }

    private void initJFrame() {
        this.setSize(700,780);
        //设置界面标题
        this.setTitle("拼图单机版1.0v");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置，只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);

        // 设置渐变背景
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, BG_TOP, 0, getHeight(), BG_BOTTOM);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(null);
        this.setContentPane(bgPanel);
        this.setLayout(null);

        this.addKeyListener(this);

    }
    //按下不松时调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有图片删除
            this.getContentPane().removeAll();
            //D:\IDEA\idea_learning\javaLearning02\images\lusi\all.png
            JLabel all =new JLabel(new ImageIcon(path+"\\all.png"));
            all.setBounds(0,0,700,780);
            this.add(all);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利
        if(victory()){
            return;
        }

        //对应上下左右 进行判断
        //左：37 右：39 上：38 下：40
        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("向左移动");
            if(y==3){
                return;
            }
            matrix[x][y]=matrix[x][y+1];
            matrix[x][y+1]=0;
            y++;
            //计数器自增
            step++;
            initImage();
        }else if(code == 38){
            System.out.println("向上移动");
            if(x==3){
                return;
            }
            matrix[x][y]=matrix[x+1][y];
            matrix[x+1][y]=0;
            //x位置改变+1
            x++;
            step++;
            initImage();

        }else if(code == 39){
            System.out.println("向右移动");
            if(y==0){
                return;
            }
            matrix[x][y]=matrix[x][y-1];
            matrix[x][y-1]=0;
            y--;
            step++;
            initImage();
        }else if(code == 40){
            System.out.println("向下移动");
            if(x==0){
                return;
            }
            matrix[x][y]=matrix[x-1][y];
            matrix[x-1][y]=0;
            x--;
            step++;
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            matrix = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0},
            };
            initImage();
        }

    }

    //判断数组matrix与win是否一致
    public boolean victory(){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j] != win[i][j]){
                    //只要有一个数据不一样就错
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        if(obj == replayItem){
             //再次打乱数组
            step=0;
            initDare();
            initImage();
        }else if(obj == reLoginItem){
            this.setVisible(false);
            //打开登陆界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.exit(0);
        }else if(obj == accountItem){

        }
    }
}
