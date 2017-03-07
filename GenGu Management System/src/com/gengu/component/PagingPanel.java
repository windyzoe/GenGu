package com.gengu.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceConstants.FocusKind;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import com.gengu.common.Constants;
import com.gengu.controller.TableController;
import com.gengu.ui.MainFrame;

public class PagingPanel extends JPanel
{
	private JButton jBPageUp;
	private JButton jBPageDown;
	private JToggleButton jBStart;
	private JToggleButton jB2;
	private JToggleButton jB3;
	private JToggleButton jB4;
	private JToggleButton jB5;
	private JToggleButton jB6;
	private JToggleButton jBEnd;

	private JLabel jLAllRows;

	private int pageNumber;
	
	private int currentPage;
	
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final PagingPanel single = new PagingPanel();
	

	/**
	 * ����ģʽ
	 * 
	 * @return
	 */
	public static PagingPanel getInstance()
	{
		return single;
	}
	/**
	 * Create the panel.
	 */
	private PagingPanel()
	{
		initLayout();
		addListeners();
		setPanel(0);
	}

	private void initLayout()
	{
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel label = new JLabel("�ܹ�");
		add(label);

		jLAllRows = new JLabel("0");
		add(jLAllRows);

		JLabel label_1 = new JLabel("����¼");
		this.add(label_1);

		Component horizontalStrut = Box.createHorizontalStrut(36);
		this.add(horizontalStrut);

		jBPageUp = new JButton("��һҳ");
		modifyButton(jBPageUp);
		this.add(jBPageUp);

		jBStart = new JToggleButton("1");
		modifyButton(jBStart);
		this.add(jBStart);

		jB2 = new JToggleButton("2");
		modifyButton(jB2);
		this.add(jB2);

		jB3 = new JToggleButton("3");
		modifyButton(jB3);
		this.add(jB3);

		jB4 = new JToggleButton("4");
		modifyButton(jB4);
		this.add(jB4);

		jB5 = new JToggleButton("5");
		modifyButton(jB5);
		this.add(jB5);

		jB6 = new JToggleButton("6");
		modifyButton(jB6);
		this.add(jB6);

		jBEnd = new JToggleButton("7");
		modifyButton(jBEnd);
		this.add(jBEnd);

		jBPageDown = new JButton("��һҳ");
		modifyButton(jBPageDown);
		this.add(jBPageDown);
	}

	/**
	 * ���ڼ�¼��������ʼ����ҳ��
	 * 
	 * @param allRows
	 */
	public void setPanel(int allRows)
	{
		System.out.println(" init paging");
		this.pageNumber = allRows % Constants.ROWSIZE == 0 ? allRows / Constants.ROWSIZE : allRows / Constants.ROWSIZE + 1;
		this.jLAllRows.setText(allRows+"");
		this.currentPage=1;
		setButton(currentPage);
	}

	/**���ڼ�¼�����͵�ǰҳ��,����������ҳ��
	 * @param allRows
	 * @param currentPage
	 */
	public void setPanel(int allRows, int currentPage)
	{
		this.pageNumber = allRows % Constants.ROWSIZE == 0 ? allRows / Constants.ROWSIZE : allRows / Constants.ROWSIZE + 1;
		this.currentPage=currentPage;
		this.jLAllRows.setText(allRows+"");
		setButton(currentPage);
	}

	/**���°�ť����ʾ��Ϣ
	 * @param currentPage
	 */
	private void setButton(int currentPage)
	{
		initAllButtons();// ��հ�ťѡ��
		renameButton(currentPage);
		switch (pageNumber)
		{
		case 0:
			jB2.setVisible(false);
			jB3.setVisible(false);
			jB4.setVisible(false);
			jB5.setVisible(false);
			jB6.setVisible(false);
			jBEnd.setVisible(false);
			break;
		case 1:
			jB2.setVisible(false);
			jB3.setVisible(false);
			jB4.setVisible(false);
			jB5.setVisible(false);
			jB6.setVisible(false);
			jBEnd.setVisible(false);
			break;
		case 2:
			jB3.setVisible(false);
			jB4.setVisible(false);
			jB5.setVisible(false);
			jB6.setVisible(false);
			jBEnd.setVisible(false);
			break;
		case 3:
			jB4.setVisible(false);
			jB5.setVisible(false);
			jB6.setVisible(false);
			jBEnd.setVisible(false);
			break;
		case 4:
			jB5.setVisible(false);
			jB6.setVisible(false);
			jBEnd.setVisible(false);
			break;
		case 5:
			jB6.setVisible(false);
			jBEnd.setVisible(false);
			break;
		case 6:
			jBEnd.setVisible(false);
			break;
		case 7:
			break;
		default:
			jBStart.setText(1 + "...");
			jBEnd.setText("..." + pageNumber);
			if (jB2.getText().equals("2"))
			{
				jBStart.setText(1 + "");
			}
			if (jB6.getText().equals(pageNumber - 1 + ""))
			{
				jBEnd.setText(pageNumber + "");
			}
			break;
		}
		switch (currentPage)
		{
		case 1:
			jBStart.setSelected(true);
			break;
		default:

			if (jB4.getText().equals(currentPage + ""))
			{
				jB4.setSelected(true);
			} else if (jB6.getText().equals(currentPage + ""))
			{
				jB6.setSelected(true);
			} else if (jB5.getText().equals(currentPage + ""))
			{
				jB5.setSelected(true);
			} else if (jB3.getText().equals(currentPage + ""))
			{
				jB3.setSelected(true);
			} else if (jB2.getText().equals(currentPage + ""))
			{
				jB2.setSelected(true);
			} else if (currentPage == pageNumber)
			{
				jBEnd.setSelected(true);
			}
			break;
		}
		this.repaint();
	}

	private void renameButton(int currentPage)
	{
		if (pageNumber <= 7)
		{
			return;
		}
		if (pageNumber - currentPage > 3 && currentPage > 4)
		{
			jB6.setText(currentPage + 2 + "");
			jB5.setText(currentPage + 1 + "");
			jB4.setText(currentPage + "");
			jB3.setText(currentPage - 1 + "");
			jB2.setText(currentPage - 2 + "");
		} else if (pageNumber - currentPage <= 3)
		{
			jB6.setText(pageNumber - 1 + "");
			jB5.setText(pageNumber - 2 + "");
			jB4.setText(pageNumber - 3 + "");
			jB3.setText(pageNumber - 4 + "");
			jB2.setText(pageNumber - 5 + "");
		}
	}

	private void initAllButtons()
	{
		jBStart.setVisible(true);
		jB2.setVisible(true);
		jB3.setVisible(true);
		jB4.setVisible(true);
		jB5.setVisible(true);
		jB6.setVisible(true);
		jBEnd.setVisible(true);
		jBStart.setSelected(false);
		jB2.setSelected(false);
		jB3.setSelected(false);
		jB4.setSelected(false);
		jB5.setSelected(false);
		jB6.setSelected(false);
		jBEnd.setSelected(false);
		jBStart.setText("1");
		jB2.setText("2");
		jB3.setText("3");
		jB4.setText("4");
		jB5.setText("5");
		jB6.setText("6");
		jBEnd.setText("7");
	}

	private void addListeners()
	{
		jBStart.addActionListener(buttonListener);
		jB2.addActionListener(buttonListener);
		jB3.addActionListener(buttonListener);
		jB4.addActionListener(buttonListener);
		jB5.addActionListener(buttonListener);
		jB6.addActionListener(buttonListener);
		jBEnd.addActionListener(buttonListener);
		jBPageDown.addActionListener(pageUpDownbuttonListener);
		jBPageUp.addActionListener(pageUpDownbuttonListener);
	}

	/**
	 * ��Ӵ���ҳ���ְ�ť�¼�,�¼�������������
	 * 1 ��ҳUI��ˢ��
	 * 2 �б��ˢ��
	 */
	private ActionListener buttonListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//��õ���ķ�ҳ��ֵֵ
			JToggleButton tempButton = (JToggleButton) e.getSource();
			String strIndex = tempButton.getText();
			System.out.println(strIndex);
			if (strIndex.startsWith("..."))
			{
				strIndex = pageNumber + "";
			} else if (strIndex.endsWith("..."))
			{
				strIndex = "1";
			}
			int nowPage = Integer.parseInt(strIndex);
			if (nowPage==currentPage)
			{
				tempButton.setSelected(true);
				return;
			}
			currentPage=nowPage;
			//���·�ҳUI
			setButton(currentPage);
			new TableController().pagingControl(currentPage);
		}
	};
	/**
	 * ��һҳ,��һҳ�İ�ť�¼�
	 */
	private ActionListener pageUpDownbuttonListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//��õ���ķ�ҳ��ֵֵ
			JButton tempButton = (JButton) e.getSource();
			String strIndex = tempButton.getText();
			if (strIndex.equals("��һҳ")&&currentPage!=1)
			{
				currentPage=currentPage-1;
			}else if(strIndex.equals("��һҳ")&&currentPage!=pageNumber){
				currentPage=currentPage+1;
			}else {
				return;
			}
			//���·�ҳUI
			setButton(currentPage);
			new TableController().pagingControl(currentPage);
		}
	};

	/**�ı䰴ť��״
	 * @param jb
	 */
	private void modifyButton(JButton jb)
	{
		jb.setBackground(new Color(255, 255, 255));
		jb.putClientProperty(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.NONE);
		jb.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,
				EnumSet.of(SubstanceConstants.Side.RIGHT,
						SubstanceConstants.Side.BOTTOM));
		jb.putClientProperty(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
		jb.putClientProperty(SubstanceLookAndFeel.COLORIZATION_FACTOR, 0.5);
	}

	/**�ı䰴ť��״
	 * @param jb
	 */
	private void modifyButton(JToggleButton jb)
	{
		jb.setBackground(new Color(255, 255, 255));
		jb.putClientProperty(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.NONE);
		jb.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,
				EnumSet.of(SubstanceConstants.Side.RIGHT,
						SubstanceConstants.Side.BOTTOM));
		jb.putClientProperty(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
		jb.putClientProperty(SubstanceLookAndFeel.COLORIZATION_FACTOR, 0.5);
	}
}
