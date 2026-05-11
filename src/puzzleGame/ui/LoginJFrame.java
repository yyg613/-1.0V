package puzzleGame.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class LoginJFrame extends JFrame {
    //登录界面

    // 自定义颜色方案
    private static final Color BACKGROUND_START = new Color(25, 25, 112); // 深蓝色
    private static final Color BACKGROUND_END = new Color(75, 0, 130);   // 紫色
    private static final Color BUTTON_COLOR = new Color(65, 105, 225);   // 皇家蓝
    private static final Color BUTTON_HOVER = new Color(100, 149, 237);  // 矢车菊蓝
    private static final Color INPUT_BACKGROUND = new Color(255, 255, 255, 200);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color LABEL_COLOR = new Color(220, 220, 255);

    public LoginJFrame() {
    //在页面里添加信息
    //比如长宽高
        this.setSize(500,430);
        //设置界面标题
        this.setTitle("登录界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // 设置全局UI样式
        setUIFont(new Font("微软雅黑", Font.PLAIN, 14));
        
        // 创建主面板（渐变背景）
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, BACKGROUND_START, getWidth(), getHeight(), BACKGROUND_END);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        
        // 创建表单面板
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // 标题标签
        JLabel titleLabel = new JLabel("拼图游戏登录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(titleLabel, gbc);
        
        // 用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        usernameLabel.setForeground(LABEL_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        formPanel.add(usernameLabel, gbc);
        
        // 用户名输入框
        JTextField usernameField = createStyledTextField("请输入用户名");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        formPanel.add(usernameField, gbc);
        
        // 密码标签
        JLabel passwordLabel = new JLabel("密  码:");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        passwordLabel.setForeground(LABEL_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        formPanel.add(passwordLabel, gbc);
        
        // 密码输入框
        JPasswordField passwordField = createStyledPasswordField("请输入密码");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        formPanel.add(passwordField, gbc);
        
        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        
        // 登录按钮
        JButton loginButton = createStyledButton("登  录");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || username.equals("请输入用户名")) {
                JOptionPane.showMessageDialog(this, "请输入用户名");
                return;
            }
            if (password.isEmpty() || password.equals("请输入密码")) {
                JOptionPane.showMessageDialog(this, "请输入密码");
                return;
            }

            this.dispose();
            new GameJFrame();
        });
        buttonPanel.add(loginButton);
        
        // 注册按钮
        JButton registerButton = createStyledButton("注  册");
        registerButton.addActionListener(e -> {
            this.dispose();
            new RegisterJFrame();
        });
        buttonPanel.add(registerButton);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(buttonPanel, gbc);
        
        // 添加表单面板到主面板
        mainPanel.add(formPanel);
        
        // 设置内容面板
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }
    
    // 创建样式化文本框
    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(200, 200, 255, 150));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15));
                g2.dispose();
            }
        };
        
        textField.setOpaque(false);
        textField.setBackground(INPUT_BACKGROUND);
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        textField.setBorder(new EmptyBorder(8, 15, 8, 15));
        textField.setForeground(Color.DARK_GRAY);
        
        // 添加占位符效果
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.DARK_GRAY);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        
        return textField;
    }
    
    // 创建样式化密码框
    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(200, 200, 255, 150));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15));
                g2.dispose();
            }
        };
        
        passwordField.setOpaque(false);
        passwordField.setBackground(INPUT_BACKGROUND);
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        passwordField.setBorder(new EmptyBorder(8, 15, 8, 15));
        passwordField.setForeground(Color.DARK_GRAY);
        
        // 添加占位符效果
        passwordField.setText(placeholder);
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String text = new String(passwordField.getPassword());
                if (text.equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.DARK_GRAY);
                    passwordField.setEchoChar('•');
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                String text = new String(passwordField.getPassword());
                if (text.isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        
        return passwordField;
    }
    
    // 创建样式化按钮
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // 绘制按钮背景
                if (getModel().isPressed()) {
                    g2.setColor(BUTTON_COLOR.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(BUTTON_HOVER);
                } else {
                    g2.setColor(BUTTON_COLOR);
                }
                
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 25, 25));
                
                // 绘制按钮文字
                g2.setColor(TEXT_COLOR);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                
                g2.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                // 不绘制默认边框
            }
        };
        
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("微软雅黑", Font.BOLD, 16));
        button.setForeground(TEXT_COLOR);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // 添加悬停效果
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.repaint();
            }
        });
        
        return button;
    }
    
    // 设置全局字体
    private void setUIFont(Font font) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
            }
        }
    }
}
