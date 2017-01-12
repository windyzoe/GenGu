package com.gengu.component;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.gengu.common.Constants;

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
	
	private JLabel jLPageIndex;
	
	private int pageNumber;
	/**
	 * Create the panel.
	 */
	public PagingPanel()
	{
		initLayout();
		addListeners();
	}

	private void initLayout()
	{
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel label = new JLabel("总共");
		add(label);

		jLPageIndex = new JLabel("0");
		add(jLPageIndex);

		JLabel label_1 = new JLabel("条记录");
		this.add(label_1);

		Component horizontalStrut = Box.createHorizontalStrut(36);
		this.add(horizontalStrut);

		jBPageUp = new JButton("上一页");
		this.add(jBPageUp);

		jBStart = new JToggleButton("1");
		this.add(jBStart);

		jB2 = new JToggleButton("2");
		this.add(jB2);

		jB3 = new JToggleButton("3");
		this.add(jB3);
		
		jB4 = new JToggleButton("4");
		this.add(jB4);
		
		jB5 = new JToggleButton("5");
		this.add(jB5);

		jB6 = new JToggleButton("6");
		this.add(jB6);

		jBEnd = new JToggleButton("7");
		this.add(jBEnd);

		jBPageDown = new JButton("下一页");
		this.add(jBPageDown);
	}

	/**
	 * 基于记录总条数初始化分页面
	 * 
	 * @param allRows
	 */
	public void setPanel(int allRows)
	{
		this.pageNumber = allRows % Constants.ROWSIZE == 0 ? allRows / Constants.ROWSIZE : allRows / Constants.ROWSIZE + 1;
		setButton(1);
	}
	private void setButton(int currentPage)
	{
		initAllButtons();//清空按钮选择
		renameButton(currentPage);
		switch (pageNumber)
		{
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
			jBStart.setText(1+"...");
			jBEnd.setText("..."+pageNumber);
			break;
		}
		switch (currentPage)
		{
		case 1:
			jBStart.setSelected(true);
			break;
		default:
			if (currentPage==pageNumber)
			{
				jBEnd.setSelected(true);
				break;
			}
			if (jB4.getText().equals(currentPage+""))
			{
				jB4.setSelected(true);
			}else if (jB6.getText().equals(currentPage+"")) {
				jB6.setSelected(true);
			}
			else if (jB5.getText().equals(currentPage+"")) {
				jB5.setSelected(true);
			}
			else if (jB3.getText().equals(currentPage+"")) {
				jB3.setSelected(true);
			}
			else if (jB2.getText().equals(currentPage+"")) {
				jB2.setSelected(true);
			}
			break;
		}
		
	}
	private void renameButton(int currentPage)
	{
		if (pageNumber<=7)
		{
			return;
		}
		if (pageNumber-currentPage>3 && currentPage>4)
		{
			jB6.setText(currentPage+2+"");
			jB5.setText(currentPage+1+"");
			jB4.setText(currentPage+"");
			jB3.setText(currentPage-1+"");
			jB2.setText(currentPage-2+"");
		}else if (pageNumber-currentPage<=3)
		{
			jB6.setText(pageNumber-1+"");
			jB5.setText(pageNumber-2+"");
			jB4.setText(pageNumber-3+"");
			jB3.setText(pageNumber-4+"");
			jB2.setText(pageNumber-5+"");
		}
	}
	private void initAllButtons()
	{
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
	}
	private ActionListener buttonListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JToggleButton tempButton = (JToggleButton) e.getSource();
			String strIndex = tempButton.getText();
			System.out.println(strIndex);
			int index = Integer.parseInt(strIndex);
			setButton(index);
		}
	};
}
