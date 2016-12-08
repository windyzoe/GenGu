package com.gengu.test;
  
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;  
  
public class testForSwing extends JFrame{  
    private JProgressBar bar = new JProgressBar(0, 100);  
    private JTextArea area = new JTextArea();  
      
    public testForSwing() {  
        setTitle("swingworker�ͽ�������С����");  
        add(bar, BorderLayout.CENTER);  
        add(area, BorderLayout.SOUTH);  
        pack();  
        setVisible(true);  
        start();  
    }  
    private void start() {  
        ProGressWork work = new ProGressWork();  
        work.addPropertyChangeListener(new PropertyChangeListener(){  
            @Override  
            public void propertyChange(PropertyChangeEvent evt) {  
                System.out.print(evt.getNewValue());  
            }  
        });  
        work.execute();  
    }  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
                new testForSwing();  
            }  
        });  
    }  
      
    class ProGressWork extends SwingWorker<List<Work>, Work> {  
        @Override  
        protected List<Work> doInBackground() throws Exception {  
            int i = 0;  
            List<Work> list = new ArrayList<Work>();  
            //ģ���ʱ����  
            //������100����������  
            while (i < 100) {  
                i++;  
                /*******ģ�⿪ʼһ���¹���*******/  
                Work w = new Work(i);  
                list.add(w);  
                publish(w);  
                setProgress(100 * list.size() / 100);  
                Thread.sleep(1000);  
                /*******ģ����ɴ����*******/  
            }  
            return list;  
        }  
        //����publist��ʱ������  
        //ע��������"������"  
        @Override  
        protected void process(List<Work> works) {  
            for (Work work : works) {  
                bar.setValue(work.getId());  
            }  
        }  
        @Override  
        protected void done() {  
            area.append("������ȫ�����");  
        }  
    }  
    class Work {  
        //�������  
        private int id;  
        public Work(int id) {  
            this.id = id;  
        }  
        public int getId() {  
            return id;  
        }  
    }  
}  