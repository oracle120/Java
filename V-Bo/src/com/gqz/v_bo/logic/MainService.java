package com.gqz.v_bo.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.gqz.v_bo.bean.Task;
import com.gqz.v_bo.ui.IWeiboActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;


public class MainService extends Service implements Runnable {

	// �������
	private static Queue<Task> tasks = new LinkedList<Task>();

	private static ArrayList<Activity> appActivities = new ArrayList<Activity>();

	// �Ƿ������߳�
	private boolean isRun;

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Task.WEIBO_LOGIN:// �û���¼

				// ����UI

				IWeiboActivity activity = (IWeiboActivity) getActivityByName("LoginActivity");
				activity.refresh(msg.obj);

				break;

			default:
				break;
			}

		};
	};

	/**
	 * ���һ��Activity����
	 * 
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		appActivities.add(activity);
	}

	/**
	 * ����������������
	 * 
	 * @param t
	 */
	public static void newTask(Task t) {
		tasks.add(t);
	}

	/**
	 * ����Activity ��Name ��ȡActivity�����
	 * 
	 * @param name
	 * @return
	 */
	private Activity getActivityByName(String name) {

		if (!appActivities.isEmpty()) {
			for (Activity activity : appActivities) {
				if (null != activity) {
					if (activity.getClass().getName().indexOf(name) > 0) {
						return activity;
					}
				}
			}
		}

		return null;

	}

	@Override
	public void onCreate() {

		isRun = true;
		Thread thread = new Thread(this);
		thread.start();

		super.onCreate();
	}

	public void run() {

		while (isRun) {
			Task task = null;
			if (!tasks.isEmpty()) {
				task = tasks.poll();// ִ���������Ѹ����������������Ƴ�
				if (null != task) {
					doTask(task);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}

		}

	}

	// ��������
	private void doTask(Task t) {
		Message msg = handler.obtainMessage();
		msg.what = t.getTaskId();

		switch (t.getTaskId()) {
		case Task.WEIBO_LOGIN:
			System.out.println("doTask >>>>>  user Login");
			msg.obj = "��¼�ɹ�";
			break;

		default:
			break;
		}

		handler.sendMessage(msg);

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
