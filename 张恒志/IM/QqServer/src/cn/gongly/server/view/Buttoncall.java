package cn.gongly.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Buttoncall implements ActionListener {
    private String[] a = {"1a", "2a", "3a", "4a", "5a" };
    private JButton[] jbutton = new JButton[5];
    private JLabel[] jlabel = new JLabel[jbutton.length];
    //用于记录按钮的点击数
    private Map<String, Integer> countMap = new HashMap<String, Integer>();
    /**
     * 创建一个新的实例 Buttoncall.
     */
    public Buttoncall() {
        // 创建窗口
        JFrame j = new JFrame();
        JPanel jpa = new JPanel();// 定义面板组建
        /**
         * 初始化按钮，及其点击次数（默认0）
         */
        for (int i = 0; i < jbutton.length; i++) {
            jbutton[i] = new JButton(a[i]);// 定义按钮组建
            jpa.add(jbutton[i]);// 将按钮添加到面板之中
            jlabel[i] = new JLabel("您已点击" + a[i] + "的次数是：0");
            jpa.add(jlabel[i]);
            jbutton[i].addActionListener(this);
        }
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(jpa);
        j.setTitle("测试使用");
        j.setVisible(true);
        j.setSize(250, 250);
    }
    /**
     * 单击事件处理.
     * <p>Title: actionPerformed</p>
     * <p>Description: </p>
     * @param actionevent
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionevent) {
        //获取单击事件组件 名称 如1a
        String buttonName = actionevent.getActionCommand();
        //在数组中的位置
        int index = Arrays.asList(a).indexOf(buttonName);
        if (index != -1) {
            int count = 1;
            //判断map中是否存在当前按钮
            if (countMap.containsKey(buttonName)) {
                count = countMap.get(buttonName);
                count += 1;
            }
            //记录当前按钮点击次数
            countMap.put(buttonName, count);
            jlabel[index].setText("您已点击" + buttonName + "的次数是：" + count);
            if (count % 10 == 0) {
                JOptionPane.showMessageDialog(null, count + "是10的倍数，所以我来提示你！");
            }
        }
    }
                                                        
    public static void main(String[] args) {
        new Buttoncall();
    }
}