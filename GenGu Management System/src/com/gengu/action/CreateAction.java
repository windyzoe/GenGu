package com.gengu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class manage the create(Purchase/Customer/Sale/Order) action
 * @author XUZH
 */
public class CreateAction
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final CreateAction single = new CreateAction();
	/**
	 * ����ģʽ
	 * @return
	 */
	public static CreateAction getInstance()
	{
		return single;
	}
	/**
	 * ˽�й�����
	 */
	private CreateAction()
	{
	}
    public class CreatePurchaseOrderActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {
        }
    }
}
