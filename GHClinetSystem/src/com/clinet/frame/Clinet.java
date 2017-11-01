package com.clinet.frame;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.SystemColor;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;

import javax.swing.tree.TreeSelectionModel;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;


import javax.swing.BoxLayout;
import java.awt.Component;





public class Clinet {
    private JTree tr;
    private String[] headerL = { "���", "�������", "��ǰ����", "˾��", "״̬","����","����","����" }; 
    private Object[][] cellDataL = {};
    private String[] headers = { "���", "˾��", "���", "����", "�˴�","�ƻ�����","ʵ�ʷ���","���з���" }; 
    private Object[][] cellData = null;
    private Object[][] dataep1= {{"1","2036","221","��"}};
    DefaultTableModel tableModelL,tableModelep1;
    private JTable tableL;
	private JFrame frame;
	TableColumn column;
	private JPopupMenu m_popupMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clinet window = new Clinet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
    }
	

	/**
	 * Create the application.
	 */
	public Clinet() {
		initialize();
		
	}
	

	private void createPopupMenu() {  
        m_popupMenu = new JPopupMenu();  
          
        JMenuItem delMenItem = new JMenuItem();  
        delMenItem.setText("  ɾ��  ");  
        delMenItem.addActionListener(new java.awt.event.ActionListener() {  
            public void actionPerformed(java.awt.event.ActionEvent evt) {  
                //�ò�����Ҫ������  
            }  
        });  
        m_popupMenu.add(delMenItem);  
    }  
	
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {  
		  
	       mouseRightButtonClick(evt);  
	}  
	
	private void mouseRightButtonClick(java.awt.event.MouseEvent evt) {  
	       //�ж��Ƿ�Ϊ����BUTTON3��ť��BUTTON3Ϊ����Ҽ�  
	       if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {  
	           //ͨ�����λ���ҵ����Ϊ����е���  
	           int focusedRowIndex = tableL.rowAtPoint(evt.getPoint());  
	           if (focusedRowIndex == -1) {  
	               return;  
	           }  
	           //�������ѡ����Ϊ��ǰ�Ҽ��������  
	           tableL.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);  
	           //�����˵�  
	           m_popupMenu.show(tableL, evt.getX(), evt.getY());  
	       }  
	  
	   }  
	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize()  {
		frame = new JFrame();
		frame.setBounds(new Rectangle(1000, 800));	 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane cen=new JTabbedPane();
        frame.getContentPane().add(cen,BorderLayout.CENTER);
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
	
		JPanel p1 = new JPanel();
		
		FlowLayout fl_p1 = new FlowLayout(FlowLayout.LEFT);
		p1.setLayout(fl_p1);
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    JPanel p4 = new JPanel();
	    JPanel p5 = new JPanel();
	    JPanel p6 = new JPanel();
	    JPanel p7 = new JPanel();
	    
	    tabbedPane.add(p1,"�������");
	    tabbedPane.add(p2,"����Χ��");
	    tabbedPane.add(p3,"�澯����");
	    tabbedPane.add(p4,"���Ź���");
	    tabbedPane.add(p5,"���ȹ���");
	    tabbedPane.add(p6,"��ѯͳ��");
	    tabbedPane.add(p7,"ϵͳ����");
	    JPanel p11=new JPanel();
	    p11.setLayout(new BorderLayout());
	    JPanel p111=new JPanel();
	    p111.setForeground(SystemColor.desktop);
	    p111.setLayout(new FlowLayout());
	    JButton B1=new JButton("���ȫ��");
	    B1.setBorder(new EmptyBorder(0, 0, 0, 0));
	    B1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				JPanel a1=new All_See();
				JPanel pa1=new JPanel();
				JButton pb1=new JButton("x");
				JLabel pl1=new JLabel("���ȫ��");
				pa1.add(pl1);
				pa1.add(pb1);
				cen.add(a1);
				cen.setTabComponentAt(cen.indexOfComponent(a1), pa1);
				cen.setSelectedIndex(cen.getTabCount()-1);
				
				/*frame.validate();
				frame.repaint();
		*/
			}
		});
	    p111.add(B1);
	    JButton B2=new JButton("�Զ��峵���");
	    B2.setBorder(new EmptyBorder(0, 0, 0, 0));
	    B2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop1 t = new Pop1();
				t.getFrame().setVisible(true);
				
			}
		});
	    p111.add(B2);
	    JButton B3=new JButton("�Զ����˼��");
	    B3.setBorder(new EmptyBorder(0, 0, 0, 0));
	    B3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop2 t2 = new Pop2();
				t2.getFrame().setVisible(true);
				
			}
		});
	    p111.add(B3);
	    JButton Brm=new JButton("������");
	    Brm.setBorder(new EmptyBorder(0, 0, 0, 0));
	    Brm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				JPanel a2=new RM();
				cen.add(a2,"������");
				cen.setSelectedIndex(cen.getTabCount()-1);
				/*frame.validate();*/
				/*cen.repaint();*/
		
			}
		});
	    p111.add(Brm);
	    JButton ls=new JButton("��ʷ�ط�");
	    ls.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p111.add(ls);
	    p11.add(p111,BorderLayout.CENTER);
	    JLabel label = new JLabel("λ�ü��");
	    label.setBackground(SystemColor.text); 
	    label.setForeground(SystemColor.desktop);
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	    p11.add(label,BorderLayout.SOUTH);
	    p11.setBorder(BorderFactory.createEmptyBorder());
	    p11.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p1.add(p11);
	    JPanel p12=new JPanel();
	    p12.setLayout(new BorderLayout());
	    JPanel p112=new JPanel();
	    p112.setLayout(new FlowLayout());
	    JButton sp=new JButton("��Ƶ���");
	    sp.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p112.add(sp);
	    JButton ls2=new JButton("��ʷ��Ƶ");
	    ls2.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p112.add(ls2);
	    JButton ck=new JButton("�鿴ץͼ");
	    ck.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p112.add(ck);
	    JButton yy=new JButton("�������");
	    yy.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p112.add(yy);
	    JButton B4=new JButton("�����㲥");
	    B4.setBorder(new EmptyBorder(0, 0, 0, 0));
	    B4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop3 t3 = new Pop3();
				t3.getFrame().setVisible(true);
				
			}
		});
	    p112.add(B4);
	    p12.add(p112,BorderLayout.CENTER);
	    JLabel label1 = new JLabel("����Ƶ���");
	    label1.setHorizontalAlignment(SwingConstants.CENTER);
	    p12.add(label1,BorderLayout.SOUTH);
	    p12.setBorder(BorderFactory.createEmptyBorder());
	    p12.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p1.add(p12);
	    JPanel p21=new JPanel();
	    FlowLayout fl_p2 = new FlowLayout(FlowLayout.LEFT);
	    p2.setLayout(fl_p2);
	    p21.setLayout(new BorderLayout());
	    JPanel p211=new JPanel();
	    p211.setLayout(new FlowLayout());
	    JButton wl=new JButton("Χ������");
	    wl.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p211.add(wl);
	    JButton bmbd=new JButton("���Ű�");
	    bmbd.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p211.add(bmbd);
	    JButton clbd=new JButton("������");
	    clbd.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p211.add(clbd);
	    p21.add(p211,BorderLayout.CENTER);
	    JLabel label2 = new JLabel("����Χ��");
	    label2.setHorizontalAlignment(SwingConstants.CENTER);
	    p21.add(label2,BorderLayout.SOUTH);
	    p21.setBorder(BorderFactory.createEmptyBorder());
	    p21.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p2.add(p21);
	    JPanel p31=new JPanel();
	    FlowLayout fl_p3 = new FlowLayout(FlowLayout.LEFT);
	    p3.setLayout(fl_p3);
	    p31.setLayout(new BorderLayout());
	    JPanel p311=new JPanel();
	    p311.setLayout(new FlowLayout());
	    JButton gjcx=new JButton("�澯��ѯ");
	    gjcx.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p311.add(gjcx);
	    JButton qtgj=new JButton("�����澯");
	    qtgj.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p311.add(qtgj);
	    JButton hjtj=new JButton("�澯ͳ��");
	    hjtj.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p311.add(hjtj);
	    JButton hjsz=new JButton("�澯����");
	    hjsz.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p311.add(hjsz);
	    p31.add(p311,BorderLayout.CENTER);
	    JLabel label3 = new JLabel("�澯����");
	    label3.setHorizontalAlignment(SwingConstants.CENTER);
	    p31.add(label3,BorderLayout.SOUTH);
	    p31.setBorder(BorderFactory.createEmptyBorder());
	    p31.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p3.add(p31);
	    JPanel p41=new JPanel();
	    FlowLayout fl_p4 = new FlowLayout(FlowLayout.LEFT);
	    p4.setLayout(fl_p4);
	    p41.setLayout(new BorderLayout());
	    JPanel p411=new JPanel();
	    p411.setLayout(new FlowLayout());
	    JButton dxcx=new JButton("���Ų�ѯ");
	    dxcx.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p411.add(dxcx);
	    JButton B5=new JButton("���Ź���");
	    B5.setBorder(new EmptyBorder(0, 0, 0, 0));
	    B5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop5 t5 = new Pop5();
				t5.getFrame().setVisible(true);
				
			}
		});
	    p411.add(B5);
	    p41.add(p411,BorderLayout.CENTER);
	    JLabel label4 = new JLabel("���Ź���");
	    label4.setHorizontalAlignment(SwingConstants.CENTER);
	    p41.add(label4,BorderLayout.SOUTH);
	    p41.setBorder(BorderFactory.createEmptyBorder());
	    p41.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p4.add(p41);
	    JPanel p51=new JPanel();
	    FlowLayout fl_p5 = new FlowLayout(FlowLayout.LEFT);
	    p5.setLayout(fl_p5);
	    p51.setLayout(new BorderLayout());
	    JPanel p511=new JPanel();
	    p511.setLayout(new FlowLayout());
	    JButton yxsj=new JButton("����ʱ��");
	    yxsj.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p511.add(yxsj);
	    JButton zyjh=new JButton("��ҵ�ƻ�");
	    zyjh.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p511.add(zyjh);
	    p51.add(p511,BorderLayout.CENTER);
	    JLabel label5 = new JLabel("��Ӫ�Ű�");
	    label5.setHorizontalAlignment(SwingConstants.CENTER);
	    p51.add(label5,BorderLayout.SOUTH);
	    p51.setBorder(BorderFactory.createEmptyBorder());
	    p51.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p5.add(p51);
	    JPanel p52=new JPanel();
	    p52.setLayout(new BorderLayout());
	    JPanel p512=new JPanel();
	    p512.setLayout(new FlowLayout());
	    JButton czrz=new JButton("������־");
	    czrz.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p512.add(czrz);
	    JButton ddrz=new JButton("������־");
	    ddrz.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p512.add(ddrz);
	    JButton B6=new JButton("��������");
	    B6.setBorder(new EmptyBorder(0, 0, 0, 0));
        B6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop6 t6 = new Pop6();
				t6.getFrame().setVisible(true);
				
			}
		});
	    p512.add(B6);
	    JButton B7=new JButton("�л���·");
	    B7.setBorder(new EmptyBorder(0, 0, 0, 0));
        B7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop7 t7 = new Pop7();
				t7.getFrame().setVisible(true);
				
			}
		});
	    p512.add(B7);
	    p52.add(p512,BorderLayout.CENTER);
	    JLabel label6 = new JLabel("��Ӫ����");
	    label6.setHorizontalAlignment(SwingConstants.CENTER);
	    p52.add(label6,BorderLayout.SOUTH);
	    p52.setBorder(BorderFactory.createEmptyBorder());
	    p52.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p5.add(p52);
	    JPanel p61=new JPanel();
	    FlowLayout fl_p6 = new FlowLayout(FlowLayout.LEFT);
	    p6.setLayout(fl_p6);
	    p61.setLayout(new BorderLayout());
	    JPanel p611=new JPanel();
	    p611.setLayout(new FlowLayout());
	    JButton dw=new JButton("��λ��ѯ");
	    dw.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(dw);
	    JButton dl=new JButton("����վ��ѯ");
	    dl.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(dl);
	    JButton zl=new JButton("�����߲�ѯ");
	    zl.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(zl);
	    JButton gj=new JButton("�澯��ѯ");
	    gj.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(gj);
	    JButton qt=new JButton("�����澯");
	    qt.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(qt);
	    JButton dx=new JButton("���Ų�ѯ");
	    dx.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(dx);
	    JButton kq=new JButton("���ڲ�ѯ");
	    kq.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(kq);
	    JButton gl=new JButton("�����ѯ");
	    gl.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(gl);
	    JButton cc=new JButton("���β�ѯ");
	    cc.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p611.add(cc);
	    p61.add(p611,BorderLayout.CENTER);
	    JLabel label7 = new JLabel("��ѯ");
	    label7.setHorizontalAlignment(SwingConstants.CENTER);
	    p61.add(label7,BorderLayout.SOUTH);
	    p61.setBorder(BorderFactory.createEmptyBorder());
	    p61.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p6.add(p61);
	    JPanel p62=new JPanel();
	    p62.setLayout(new BorderLayout());
	    JPanel p621=new JPanel();
	    p621.setLayout(new FlowLayout());
	    JButton dc=new JButton("�����ձ�");
	    dc.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p621.add(dc);
	    JButton sj=new JButton("˾���ձ�");
	    sj.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p621.add(sj);
	    JButton cw=new JButton("�����ձ�");
	    cw.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p621.add(cw);
	    JButton dd=new JButton("�����ձ�");
	    dd.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p621.add(dd);
	    p62.add(p621,BorderLayout.CENTER);
	    JLabel label8 = new JLabel("�ձ�");
	    label8.setHorizontalAlignment(SwingConstants.CENTER);
	    p62.add(label8,BorderLayout.SOUTH);
	    p62.setBorder(BorderFactory.createEmptyBorder());
	    p62.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p6.add(p62);
	    JPanel p63=new JPanel();
	    p63.setLayout(new BorderLayout());
	    JPanel p631=new JPanel();
	    p631.setLayout(new FlowLayout());
	    JButton gj2=new JButton("�澯ͳ��");
	    gj2.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p631.add(gj2);
	    JButton kq2=new JButton("����ͳ��");
	    kq2.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p631.add(kq2);
	    JButton cl=new JButton("����̨��");
	    cl.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p631.add(cl);
	    JButton sj2=new JButton("˾��̨��");
	    sj2.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p631.add(sj2);
	    JButton bm=new JButton("����̨��");
	    bm.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p631.add(bm);
	    JButton gz=new JButton("������¼����");
	    gz.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p631.add(gz);
	    p63.add(p631,BorderLayout.CENTER);
	    JLabel label9 = new JLabel("ͳ��");
	    label9.setHorizontalAlignment(SwingConstants.CENTER);
	    p63.add(label9,BorderLayout.SOUTH);
	    p63.setBorder(BorderFactory.createEmptyBorder());
	    p63.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p6.add(p63);
	    JPanel p71=new JPanel();
	    FlowLayout fl_p7 = new FlowLayout(FlowLayout.LEFT);
	    p7.setLayout(fl_p7);
	    p71.setLayout(new BorderLayout());
	    JPanel p711=new JPanel();
	    p711.setLayout(new FlowLayout());
	    JButton B8=new JButton("�޸�����");
	    B8.setBorder(new EmptyBorder(0, 0, 0, 0));
        B8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pop8 t8 = new Pop8();
				t8.getFrame().setVisible(true);
				
			}
		});
	    p711.add(B8);
	    JButton xtsx=new JButton("ϵͳˢ��");
	    xtsx.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p711.add(xtsx);
	    JButton B9=new JButton("�л��û�");
	    B9.setBorder(new EmptyBorder(0, 0, 0, 0));
        B9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login t9 = new Login();
				t9.getFrame().setVisible(true);
				
			}
		}); 
        p711.add(B9);
        JButton yhsd=new JButton("�û�����");
        yhsd.setBorder(new EmptyBorder(0, 0, 0, 0));
        p711.add(yhsd);
	    JButton aqtc=new JButton("��ȫ�˳�");
	    aqtc.setBorder(new EmptyBorder(0, 0, 0, 0));
	    p711.add(aqtc);
	    p71.add(p711,BorderLayout.CENTER);
	    JLabel label10 = new JLabel("ϵͳ����");
	    label10.setHorizontalAlignment(SwingConstants.CENTER);
	    p71.add(label10,BorderLayout.SOUTH);
	    p71.setBorder(BorderFactory.createEmptyBorder());
	    p71.setBorder(BorderFactory.createLineBorder(Color.blue));
	    p7.add(p71);
	    
	    JPanel panel = new JPanel();
	   
	    frame.getContentPane().add(panel, BorderLayout.EAST);
	   
	    panel.setBorder(BorderFactory.createEmptyBorder());
	    panel.setBorder(BorderFactory.createLineBorder(Color.blue));
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    JLabel lablee1=new JLabel("�������иſ�");
	    lablee1.setBorder(BorderFactory.createEmptyBorder());
	    lablee1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	    lablee1.setHorizontalAlignment(SwingConstants.LEFT);
	    JPanel paneltemp=new JPanel();
	    paneltemp.setLayout(new BoxLayout(paneltemp, BoxLayout.Y_AXIS));
	    paneltemp.add(lablee1);
	    panel.add(paneltemp);
	    JLabel lablee2=new JLabel("����״̬");
	    JTextArea lja1=new JTextArea(5,5);
	    lja1.setEditable(false);
	    paneltemp.add(lablee2);
	    paneltemp.add(lja1);
	    JLabel lablee3=new JLabel("����ͳ��");
	    JTextArea lja2=new JTextArea(5,5);
	    lja2.setEditable(false);
	    paneltemp.add(lablee3);
	    paneltemp.add(lja2);
	    JLabel lablee4=new JLabel("������ϸ");
	    paneltemp.add(lablee4);
	    JTabbedPane botPanel=new JTabbedPane();
	    botPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    botPanel.setPreferredSize(new Dimension(350,400));
	    JPanel pp1=new JPanel();
	    JPanel pp2=new JPanel();
	    botPanel.add(pp1, "�����Ϣ");
	    botPanel.add(pp2, "����Ӫ��Ϣ");
	    paneltemp.add(botPanel);
	    
	    JTable table = new JTable(new DefaultTableModel(cellData,headers ));
	    
	    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	    int n=30;
	    tableModel.setRowCount(n);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(350, 400));
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pp1.add(scrollPane);
	    String[] headers2 = { "���", "˾��", "����", "��ʼʱ��", "����ʱ��" }; 
	    Object[][] cellData2 = null;
	    JTable table2 = new JTable(new DefaultTableModel(cellData2,headers2 ));
	    DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
	    int n1=30;
	    tableModel2.setRowCount(n1);
	    JScrollPane scrollPane2 = new JScrollPane(table2);
	    pp2.add(scrollPane2);
	    
	    JPanel panel_1 = new JPanel();
	    frame.getContentPane().add(panel_1, BorderLayout.WEST);
	    panel_1.setBorder(BorderFactory.createEmptyBorder());
	    panel_1.setBorder(BorderFactory.createLineBorder(Color.blue));
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
	    JLabel right1=new JLabel(" �����б�");
	    JPanel r1=new JPanel();
	    FlowLayout fl_r1 = new FlowLayout();
	    fl_r1.setAlignment(FlowLayout.LEFT);
	    r1.setLayout(fl_r1);
	    r1.add(right1);
	    right1.setBorder(BorderFactory.createEmptyBorder());
	    right1.setBorder(BorderFactory.createLineBorder(Color.blue));
	    panel_1.add(r1);
	    
	    DefaultMutableTreeNode TOP=new DefaultMutableTreeNode("���˹���");
	    TreeNode node1 = new TreeNode();
        node1.setName("����1");
        TOP.add(new DefaultMutableTreeNode(node1));
        
        
        TreeNode node2 = new TreeNode();
        node2.setName("�³��п�");
        TOP.add(new DefaultMutableTreeNode(node2));

        TreeNode node3 = new TreeNode();
        node3.setName("��վ���");
        TOP.add(new DefaultMutableTreeNode(node3));
        
        DefaultMutableTreeNode group1=new DefaultMutableTreeNode("�Ǽʹ���");
        TreeNode gnode1 = new TreeNode();
        gnode1.setName("JD");
        group1.add(new DefaultMutableTreeNode(gnode1));
        TOP.add(group1);
        
        DefaultMutableTreeNode group2=new DefaultMutableTreeNode("���繫��");
        TOP.add(group2);
        
        DefaultMutableTreeNode group21=new DefaultMutableTreeNode("������");
        TreeNode g21node1 = new TreeNode();
        g21node1.setName("261");
        group21.add(new DefaultMutableTreeNode(g21node1));
        group2.add(group21);
        
        TreeNode node6 = new TreeNode();
        node6.setName("����2");
        TOP.add(new DefaultMutableTreeNode(node6));
        
        TreeNode node7 = new TreeNode();
        node7.setName("����3");
        TOP.add(new DefaultMutableTreeNode(node7));
        
        TreeNode node8 = new TreeNode();
        node8.setName("�˳���·");
        TOP.add(new DefaultMutableTreeNode(node8));
        
        
        
        
        tr=new JTree(TOP);
       
        tr.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);  
        JScrollPane sr=new JScrollPane(tr, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        sr.setPreferredSize(new Dimension(350, 400));
        tr.addTreeSelectionListener(new TreeSelectionListener(){  
            public void valueChanged(TreeSelectionEvent e){
                tableL.setModel(Clinet.initTableModel());
            }  
        });
        panel_1.add(sr);
        
        JTextField jt1=new JTextField();
        jt1.setPreferredSize(new Dimension(200, 25));
        JLabel jl=new JLabel("��������");
        JButton jb1=new JButton("��ѯ");
        JPanel middle=new JPanel();
        middle.setLayout(new FlowLayout());
        middle.add(jl);
        middle.add(jt1);
        middle.add(jb1);
        panel_1.add(middle);
        
        tableModelL=new DefaultTableModel(cellDataL,headerL);
        tableModelep1=new DefaultTableModel(dataep1,headerL);
        tableL = new JTable(tableModelL);
        tableL.addMouseListener(new MouseAdapter() {
        	 public void mouseClicked(java.awt.event.MouseEvent evt) {  
                 jTable1MouseClicked(evt);  }
		});
        table.setEnabled(false);
        tableL.setEnabled(false);
        
        DefaultTableModel tableModelL = (DefaultTableModel) tableL.getModel();
       
        int nL=150;
	    tableModelL.setRowCount(nL);
        JScrollPane scrollPaneL = new JScrollPane(tableL);
        scrollPaneL.setPreferredSize(new Dimension(350, 400));
        panel_1.add(scrollPaneL);
        
        JPanel left=new JPanel();
        left.setLayout(new FlowLayout());
        panel_1.add(left);
        
        JPanel panel_2 = new JPanel();
        frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
        panel_2.setLayout(new BorderLayout(0, 0));
        JPanel bojp=new JPanel();
        bojp.setBorder(BorderFactory.createEmptyBorder());
        bojp.setBorder(BorderFactory.createLineBorder(Color.blue));
        JLabel bojl=new JLabel("XX���޹�˾");
        bojp.add(bojl);
        panel_2.add(bojp);
  }
	
	public class TreeNode {
  	  private String name="";
  	  public void setName(String name) {
  	        this.name = name;
  	    }
  	  public String getName() {
  	        return name;
  	    }
  	  public String toString()
  	    {
  	        return name;
  	    }
  	  
    }
	
	
	public static DefaultTableModel initTableModel() {
		DefaultTableModel tableModelL = new DefaultTableModel();
		
		Vector<Vector<Integer>> rows = new Vector<Vector<Integer>>();
		Vector<String> title = new Vector<String>();
		for(int i=0; i<5; i++) {
			title.addElement("column"+i);
		}
		for(int i=0; i<5; i++) {
			Vector<Integer> col = new Vector<Integer>();
			for(int j=0; j<5; j++) {
				col.addElement(j);
			}
			rows.addElement(col);
		}
		
		tableModelL.setDataVector(rows, title);
		return tableModelL;
	}
}
	
